package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;
import vazkii.botania.common.item.relic.ItemRelic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.client.model.ModelRelicArmor;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRelicArmorSet extends ItemManasteelArmor implements IRelic{
	
	private static final String TAG_SOULBIND = "soulbind";

	Achievement achievement;
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItem entity = new EntityItemUnbreakable(world, location.posX, location.posY, location.posZ, itemstack);
		if(location instanceof EntityItem) {
		    entity.delayBeforeCanPickup = 40;
		}
		entity.motionX = location.motionX;
		entity.motionY = location.motionY;
		entity.motionZ = location.motionZ;
		return entity;
	}
	
	public ItemRelicArmorSet(int type, String name) {
		super(type, name, BotaniaAPI.terrasteelArmorMaterial);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
		models[slot] = new ModelRelicArmor(slot);
		return models[slot];
	}

	@Override
	public String getArmorTextureAfterInk(ItemStack stack, int slot) {
		return ConfigHandler.enableArmorModels ? LibReference.MODEL_HESTIACHASTITY : slot == 2 ? LibReference.MODEL_RELIC : LibReference.MODEL_RELIC;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == ModItems.manaResource && par2ItemStack.getItemDamage() == 4 ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		UUID uuid = new UUID(getUnlocalizedName().hashCode(), 0);
		multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Terrasteel modifier " + type, (double) getArmorDisplay(null, new ItemStack(this), type) / 20, 0));
		return multimap;
	}

	static ItemStack[] armorset;

	@Override
	public ItemStack[] getArmorSetStacks() {
		if(armorset == null)
			armorset = new ItemStack[] {
				new ItemStack(com.meteor.extrabotany.common.item.ModItems.hestiachastity),
				new ItemStack(com.meteor.extrabotany.common.item.ModItems.hermestravelclothing),
				new ItemStack(com.meteor.extrabotany.common.item.ModItems.aphroditegrace),
				new ItemStack(com.meteor.extrabotany.common.item.ModItems.vrangerboots)
		};

		return armorset;
	}

	@Override
	public boolean hasArmorSetItem(EntityPlayer player, int i) {
		ItemStack stack = player.inventory.armorInventory[3 - i];
		if(stack == null)
			return false;

		switch(i) {
		case 0: return stack.getItem() == com.meteor.extrabotany.common.item.ModItems.hestiachastity;
		case 1: return stack.getItem() == com.meteor.extrabotany.common.item.ModItems.hermestravelclothing;
		case 2: return stack.getItem() == com.meteor.extrabotany.common.item.ModItems.aphroditegrace;
		case 3: return stack.getItem() == com.meteor.extrabotany.common.item.ModItems.vrangerboots;
		}

		return false;
	}

	@Override
	public String getArmorSetName() {
		return StatCollector.translateToLocal("botania.armorset.armorrelic.name");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		if(player instanceof EntityPlayer)
			updateRelic(stack, (EntityPlayer) player);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		addBindInfo(list, stack, player);
		if(GuiScreen.isShiftKeyDown())
			addInformationAfterShift(stack, player, list, adv);
	}

	public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
		if(GuiScreen.isShiftKeyDown()) {
			String bind = getSoulbindUsernameS(stack);
			if(bind.isEmpty())
				addStringToTooltips(StatCollector.translateToLocal("botaniamisc.relicUnbound"), list);
			else {
				addStringToTooltips(String.format(StatCollector.translateToLocal("botaniamisc.relicSoulbound"), bind), list);
				if(!isRightPlayer(player, stack))
					addStringToTooltips(String.format(StatCollector.translateToLocal("botaniamisc.notYourSagittarius"), bind), list);
			}

			if(stack.getItem() == vazkii.botania.common.item.ModItems.aesirRing)
				addStringToTooltips(StatCollector.translateToLocal("botaniamisc.dropIkea"), list);

			if(stack.getItem() == vazkii.botania.common.item.ModItems.dice) {
				addStringToTooltips("", list);
				String name = stack.getUnlocalizedName() + ".poem";
				for(int i = 0; i < 4; i++)
					addStringToTooltips(EnumChatFormatting.ITALIC + StatCollector.translateToLocal(name + i), list);
			}
			addStringToTooltips(StatCollector.translateToLocal("botania.armorset.terrasteel.desc0"), list);
			addStringToTooltips(StatCollector.translateToLocal("botania.armorset.terrasteel.desc1"), list);
			addStringToTooltips(StatCollector.translateToLocal("botania.armorset.terrasteel.desc2"), list);
		} else addStringToTooltips(StatCollector.translateToLocal("botaniamisc.shiftinfo"), list);
	}
	
	static void addStringToTooltips(String s, List<String> tooltip) {
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}

	public boolean shouldDamageWrongPlayer() {
		return true;
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}

	public static String getSoulbindUsernameS(ItemStack stack) {
		return ItemNBTHelper.getString(stack, TAG_SOULBIND, "");
	}
	
    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent event) {
    	 EntityPlayer player = (EntityPlayer) event.player;
	        for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof IRelic) {
	            	if(!ItemRelic.isRightPlayer(player, stack))
	            		player.attackEntityFrom(damageSource(), 2);
	            }
	    }
    }

	public static void updateRelic(ItemStack stack, EntityPlayer player) {
		if(stack == null || !(stack.getItem() instanceof IRelic))
			return;

		String soulbind = getSoulbindUsernameS(stack);
		if(soulbind.isEmpty()) {
			player.addStat(((IRelic) stack.getItem()).getBindAchievement(), 1);
			bindToPlayer(player, stack);
			soulbind = getSoulbindUsernameS(stack);
		}

		if(!isRightPlayer(player, stack) && player.ticksExisted % 10 == 0 && (!(stack.getItem() instanceof ItemRelic) || ((ItemRelic) stack.getItem()).shouldDamageWrongPlayer()))
			player.attackEntityFrom(damageSource(), 2);
	}

	public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
		bindToUsernameS(player.getCommandSenderName(), stack);
	}

	public static void bindToUsernameS(String username, ItemStack stack) {
		ItemNBTHelper.setString(stack, TAG_SOULBIND, username);
	}

	public static boolean isRightPlayer(EntityPlayer player, ItemStack stack) {
		return isRightPlayer(player.getCommandSenderName(), stack);
	}

	public static boolean isRightPlayer(String player, ItemStack stack) {
		return getSoulbindUsernameS(stack).equals(player);
	}

	public static DamageSource damageSource() {
		return new DamageSource("botania-relic");
	}

	@Override
	public void bindToUsername(String playerName, ItemStack stack) {
		bindToUsernameS(playerName, stack);
	}

	@Override
	public String getSoulbindUsername(ItemStack stack) {
		return getSoulbindUsernameS(stack);
	}

	@Override
	public Achievement getBindAchievement() {
		return achievement;
	}

	@Override
	public void setBindAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return BotaniaAPI.rarityRelic;
	}

	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		
	}

}

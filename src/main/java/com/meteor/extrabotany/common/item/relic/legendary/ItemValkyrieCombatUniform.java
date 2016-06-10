package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;
import vazkii.botania.common.item.relic.ItemRelic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.thaumcraft.IRunicArmor;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.handler.PropertyHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ItemValkyrieCombatUniform extends ItemManasteelArmor implements IRelic, IRunicArmor{
	
	private static final String TAG_SOULBIND = "soulbind";

	Achievement achievement;

	public ItemValkyrieCombatUniform(String name) {
		super(1, name, BotaniaAPI.elementiumArmorMaterial);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		UUID uuid = new UUID(getUnlocalizedName().hashCode(), 0);
		multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier " + type, (double) getArmorDisplay(null, new ItemStack(this), type) / 20, 0));
		multimap.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier" + type, 20, 0));
		return multimap;
	}
	
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
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		if(player instanceof EntityPlayer)
			updateRelic(stack, (EntityPlayer) player);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		addBindInfo(list, stack, player);
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
	
	@Override
	public int getRunicCharge(ItemStack itemstack){
		return 20;
	}
	
	public static boolean hasVCU(EntityPlayer player){
		boolean bool = false;
		for(ItemStack stack : player.inventory.armorInventory) {
            if(stack != null && stack.getItem() instanceof ItemValkyrieCombatUniform) {
            	if(ItemRelic.isRightPlayer(player, stack)){
            		bool = true;
            	}else bool = false;
            }	
		}
		return bool;
	}
	
	@SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent event) {
    	EntityPlayer player = (EntityPlayer) event.player;
    	if(hasVCU(player)){
    		List<IMob> mobs = player.worldObj.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - 4, player.posY - 4, player.posZ - 4, player.posX + 5, player.posY + 5, player.posZ + 5));
    		if(mobs.isEmpty())
    			PropertyHandler.addShieldAmount(1F, player);
    		else if(mobs.size() > 2)
    			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 15, 1));
    	}
	}

}

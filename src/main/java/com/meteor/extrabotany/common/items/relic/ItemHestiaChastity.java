package com.meteor.extrabotany.common.items.relic;

import java.util.Collection;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.api.item.IBaubleRender.Helper;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.client.model.armor.ModelArmorTerrasteel;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelArmor;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelHelm;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemHestiaChastity extends ItemRelicArmorSet implements IManaDiscountArmor, IAncientWillContainer, IManaGivingItem, ILensEffect{
	private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
	private static final String TAG_HOME_ID = "homeID";
	
	private static final String TAG_ANCIENT_WILL = "AncientWill";
	static IIcon willIcon;
	public ItemHestiaChastity(String name) {
		super(0, LibItemName.HESTIACHASTITY);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addArmorSetDescription(ItemStack stack, List<String> list) {
		super.addArmorSetDescription(stack, list);
		for(int i = 0; i < 6; i++)
			if(hasAncientWill(stack, i))
				addStringToTooltip(StatCollector.translateToLocal("botania.armorset.will" + i + ".desc"), list);
	}
	
	@SubscribeEvent
	public void onPlayerAttacked(LivingHurtEvent event) {
        if(!(event.entity instanceof EntityPlayerMP)) {
            return;
        }
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof ItemHestiaChastity) {
	    			EntityManaBurst burst = getBurst(player, stack);
					player.worldObj.spawnEntityInWorld(burst);	
	            }
			}
	}
	
	public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
		EntityManaBurst burst = new EntityManaBurst(player);
		float motionModifier = 25F;
		burst.setColor(0xFFFFFF);
		burst.setMana(1);
		burst.setStartingMana(1);
		burst.setMinManaLoss(100);
		burst.setManaLossPerTick(1F);
		burst.setGravity(0F);
		burst.setMotion(burst.motionX * motionModifier, burst.motionY * motionModifier, burst.motionZ * motionModifier);
		ItemStack lens = stack.copy();
		ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.getCommandSenderName());
		burst.setSourceLens(lens);
		return burst;
	}
	@Override
	public void apply(ItemStack arg0, BurstProperties arg1) {

		
	}

	@Override
	public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
		return dead;
	}
	@Override
	public void updateBurst(IManaBurst burst, ItemStack stack) {
		EntityThrowable entity = (EntityThrowable) burst;
		AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(1, 1, 1);
		String attacker = ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, "");
		int homeID = ItemNBTHelper.getInt(stack, TAG_HOME_ID, -1);
		if(homeID == -1) {
			AxisAlignedBB axis1 = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(5, 5, 5);
			List<EntityLivingBase> entities = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis1);
			for(EntityLivingBase living : entities) {
				if(living instanceof EntityPlayer || !(living instanceof IMob) || living.hurtTime != 0)
					continue;
				homeID = living.getEntityId();
				ItemNBTHelper.setInt(stack, TAG_HOME_ID, homeID);
				break;
			}
		}
		List<EntityLivingBase> entities = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
		if(homeID != -1) {
			Entity home = entity.worldObj.getEntityByID(homeID);
			if(home != null) {
				Vector3 vecEntity = Vector3.fromEntityCenter(home);
				Vector3 vecThis = Vector3.fromEntityCenter(entity);
				Vector3 vecMotion = vecEntity.sub(vecThis);
				Vector3 vecCurrentMotion = new Vector3(entity.motionX, entity.motionY, entity.motionZ);
				vecMotion.normalize().multiply(vecCurrentMotion.mag());
				burst.setMotion(vecMotion.x, vecMotion.y, vecMotion.z);
			}
		}
		for(EntityLivingBase living : entities) {
			if(living instanceof EntityPlayer && (((EntityPlayer) living).getCommandSenderName().equals(attacker) || MinecraftServer.getServer() != null && !MinecraftServer.getServer().isPVPEnabled()))
				continue;
			if(living.hurtTime == 0) {
				int cost = 1;
				int mana = burst.getMana();
				if(mana >= cost) {
					burst.setMana(mana - cost);
					float damage = 4F;
					if(!burst.isFake() && !entity.worldObj.isRemote) {
						EntityPlayer player = living.worldObj.getPlayerEntityByName(attacker);

						living.attackEntityFrom(player == null ? DamageSource.magic : DamageSource.causePlayerDamage(player), damage);
						entity.setDead();
						break;
					}
					
				}
			}
		}
	}
	
	@Override
	public boolean doParticles(IManaBurst burst, ItemStack stack) {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		willIcon = IconHelper.forName(par1IconRegister, "willFlame");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		super.onArmorTick(world, player, stack);
		if(hasArmorSet(player)) {
			int food = player.getFoodStats().getFoodLevel();
			if(food > 0 && food < 18 && player.shouldHeal() && player.ticksExisted % 80 == 0)
				player.heal(1F);
			ManaItemHandler.dispatchManaExact(stack, player, 1, true);
		}
	}

	@Override
	public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
		return hasArmorSet(player) ? 0.2F : 0F;
	}

	@Override
	public void addAncientWill(ItemStack stack, int will) {
		ItemNBTHelper.setBoolean(stack, TAG_ANCIENT_WILL + will, true);
	}

	@Override
	public boolean hasAncientWill(ItemStack stack, int will) {
		return hasAncientWill_(stack, will);
	}

	public static boolean hasAncientWill_(ItemStack stack, int will) {
		return ItemNBTHelper.getBoolean(stack, TAG_ANCIENT_WILL + will, false);
	}
	
	public static boolean hasAnyWill(ItemStack stack) {
		for(int i = 0; i < 6; i++)
			if(hasAncientWill_(stack, i))
				return true;

		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderOnPlayer(ItemStack stack, RenderPlayerEvent event) {
		if(hasAnyWill(stack) && !((ItemTerrasteelArmor) stack.getItem()).hasPhantomInk(stack)) {
			GL11.glPushMatrix();
			float f = willIcon.getMinU();
			float f1 = willIcon.getMaxU();
			float f2 = willIcon.getMinV();
			float f3 = willIcon.getMaxV();
			Helper.translateToHeadLevel(event.entityPlayer);
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
			GL11.glRotatef(90F, 0F, 1F, 0F);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			GL11.glTranslatef(-0.26F, 0.15F, -0.39F);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, willIcon.getIconWidth(), willIcon.getIconHeight(), 1F / 16F);
			GL11.glPopMatrix();
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event) {
		Entity attacker = event.source.getEntity();
		if(attacker instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) attacker;
			if(hasArmorSet(player)) {
				boolean crit = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null;
				ItemStack stack = player.inventory.armorItemInSlot(3);
				if(crit && stack != null && stack.getItem() instanceof ItemHestiaChastity) {
					boolean ahrim = hasAncientWill(stack, 0);
					boolean dharok = hasAncientWill(stack, 1);
					boolean guthan = hasAncientWill(stack, 2);
					boolean torag = hasAncientWill(stack, 3);
					boolean verac = hasAncientWill(stack, 4);
					boolean karil = hasAncientWill(stack, 5);

					if(ahrim)
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 1));
					if(dharok)
						event.ammount *= 1F + (1F - player.getHealth() / player.getMaxHealth()) * 0.5F;
					if(guthan)
						player.heal(event.ammount * 0.25F);
					if(torag)
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
					if(verac)
						event.source.setDamageBypassesArmor();
					if(karil)
						event.entityLiving.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 1));
				}
			}
		}
	}

}

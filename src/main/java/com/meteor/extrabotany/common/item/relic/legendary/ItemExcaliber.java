package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;
import vazkii.botania.common.item.relic.ItemRelic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;
import com.meteor.extrabotany.common.util.EnchHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemExcaliber extends ItemManasteelSword implements IRelic, ILensEffect, IManaUsingItem {
	//copied
	private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
	private static final String TAG_HOME_ID = "homeID";
	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_EXCALIBER", 3, -1, 6.2F, 20F, 40);
	Achievement achievement;
	public ItemExcaliber() {
		super(toolMaterial, LibItemName.EXCALIBER);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return BotaniaAPI.rarityRelic;
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
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			ItemRelic.updateRelic(par1ItemStack, player);
			if(ItemRelic.isRightPlayer(player, par1ItemStack)) {
				PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
				float check = haste == null ? 0.16666667F : haste.getAmplifier() == 1 ? 0.5F : 0.2F;
				if(player.getCurrentEquippedItem() == par1ItemStack && player.swingProgress == check && !par2World.isRemote) {
					EntityManaBurst burst = getBurst(player, par1ItemStack);
					par2World.spawnEntityInWorld(burst);
					par2World.playSoundAtEntity(player, "botania:terraBlade", 0.4F, 1.4F);
				}
			}
		}
	}
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		ItemRelic.addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
	}
	@Override
	public void bindToUsername(String playerName, ItemStack stack) {
		ItemRelic.bindToUsernameS(playerName, stack);
	}
	@Override
	public String getSoulbindUsername(ItemStack stack) {
		return ItemRelic.getSoulbindUsernameS(stack);
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
	public boolean usesMana(ItemStack stack) {
		return true;
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 16, 0));
		multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 0.4, 1));
		return multimap;
	}
	public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
		EntityManaBurst burst = new EntityManaBurst(player);
		float motionModifier = 25F;
		burst.setColor(0xFFFF20);
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
	public void apply(ItemStack stack, BurstProperties props) {
		// NO-OP
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
					float damage = 6F * EnchHelper.getDivineFavorBuff(stack) * (1/EnchHelper.getDivineMarkBuff(stack)) + toolMaterial.getDamageVsEntity();
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
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 200;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

}

package com.meteor.extrabotany.common.items.relic;

import java.util.List;
import java.util.Random;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.lib.LibItemName;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibItemNames;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemExcaliber extends ItemManasteelSword implements IRelic, ILensEffect, IManaUsingItem {
	//copied
	private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
	private static final String TAG_HOME_ID = "homeID";
	private static final String TAG_WEAPONS_SPAWNED = "weaponsSpawned";
	private static final String TAG_CHARGING = "charging";
	private static final int WEAPON_TYPES = 12;
	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_EXCALIBER", 3, -1, 6.2F, 6F, 40);
	Achievement achievement;
	public ItemExcaliber() {
		super(toolMaterial, LibItemName.EXCALIBER);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return BotaniaAPI.rarityRelic;
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
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 10, 0));
		multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 0.3, 1));
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
					float damage = 6F + toolMaterial.getDamageVsEntity();
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
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		setCharging(par1ItemStack, true);
		return par1ItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time) {
		int spawned = getWeaponsSpawned(stack);
		if(spawned == 1) {
			setCharging(stack, false);
			setWeaponsSpawned(stack, 0);
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		int spawned = getWeaponsSpawned(stack);

		if(count == getMaxItemUseDuration(stack) && spawned < 1 && !player.worldObj.isRemote && ManaItemHandler.requestManaExact(stack, player, 150, true)) {
			Vector3 look = new Vector3(player.getLookVec());
			look.y = 0;
			look.normalize().negate().multiply(2);
			int div = spawned / 5;
			int mod = spawned % 5;

			Vector3 pl = look.copy().add(Vector3.fromEntityCenter(player)).add(0, 1.6, div * 0.1);

			Random rand = player.worldObj.rand;
			Vector3 axis = look.copy().normalize().crossProduct(new Vector3(-1, 0, -1)).normalize();
			Vector3 axis1 = axis.copy();

			double rot = mod * Math.PI / 4 - Math.PI / 2;

			axis1.multiply(div * 3.5 + 5).rotate(rot, look);
			if(axis1.y < 0)
				axis1.y = -axis1.y;

			Vector3 end = pl.copy().add(axis1);

			EntityBabylonWeapon weapon = new EntityBabylonWeapon(player.worldObj, player);
			weapon.posX = end.x;
			weapon.posY = end.y;
			weapon.posZ = end.z;
			weapon.rotationYaw = player.rotationYaw;
			weapon.setVariety(rand.nextInt(WEAPON_TYPES));
			weapon.setDelay(spawned);
			weapon.setRotation(MathHelper.wrapAngleTo180_float(-player.rotationYaw + 180));

			player.worldObj.spawnEntityInWorld(weapon);
			player.worldObj.playSoundAtEntity(weapon, "botania:babylonSpawn", 1F, 1F + player.worldObj.rand.nextFloat() * 3F);
			setWeaponsSpawned(stack, spawned + 1);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 200;
	}


	public static boolean isCharging(ItemStack stack) {
		return ItemNBTHelper.getBoolean(stack, TAG_CHARGING, false);
	}

	public static int getWeaponsSpawned(ItemStack stack) {
		return ItemNBTHelper.getInt(stack, TAG_WEAPONS_SPAWNED, 0);
	}

	public static void setCharging(ItemStack stack, boolean charging) {
		ItemNBTHelper.setBoolean(stack, TAG_CHARGING, charging);
	}

	public static void setWeaponsSpawned(ItemStack stack, int count) {
		ItemNBTHelper.setInt(stack, TAG_WEAPONS_SPAWNED, count);
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

}

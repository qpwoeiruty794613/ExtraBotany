package com.meteor.extrabotany.common.item.relic;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.item.relic.ItemRelic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.lib.LibItemName;

public class ItemVPowerBattleaxe extends ItemRelicAdv implements IManaUsingItem{
	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_VPOWERBATTLEAXE", 3, -1, 6.2F, 6F, 40);
	private static final String TAG_WEAPONS_SPAWNED = "weaponsSpawned";
	private static final String TAG_CHARGING = "charging";
	private static final int WEAPON_TYPES = 12;
	public ItemVPowerBattleaxe() {
		super(LibItemName.VPOWERBATTLEAXE);
		
	}
	
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 18, 0));
		multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", -0.2, 1));
		return multimap;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(ItemRelic.isRightPlayer(par3EntityPlayer, par1ItemStack)){
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		setCharging(par1ItemStack, true);
		}
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
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public boolean usesMana(ItemStack arg0) {
		return false;
	}
}

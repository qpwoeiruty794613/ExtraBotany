package com.meteor.extrabotany.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

import com.meteor.extrabotany.common.effect.ModPotionEffect;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletExploding;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletGold;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletHighVelocity;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMeteor;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMusket;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSilver;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSnowball;
import com.meteor.extrabotany.common.handler.MathHandler;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGun extends ItemMods{

	public ItemGun(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}
	
	public int selectBullet(EntityPlayer player){
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,0)))
			a = 6;//explo
		else a = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,1)))
			b = 3;//gold
		else b = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,2)))
			c = 5;//high
		else c = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,3)))
			d = 4;//meteor
		else d = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,4)))
			e = 1;
		else e = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,5)))
			f = 2;	
		else f = 0;
		int bullet;
		bullet = Math.max(MathHandler.max(a, b, c), MathHandler.max(d, e, f));
		return bullet;
	}
	
	public void consumeBullet(EntityPlayer player){
		if(!player.worldObj.isRemote){
		switch(selectBullet(player)){
		case 1:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 4).getItem());
			break;
		case 2:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 5).getItem());
			break;
		case 3:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 1).getItem());
			break;
		case 4:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 3).getItem());
			break;
		case 5:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 2).getItem());
			break;
		case 6:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 0).getItem());
			break;
			}	
		}
	}
	
	public void summonBullet(EntityPlayer player){
		EntityBulletExploding s1 = new EntityBulletExploding(player.worldObj, player);
		EntityBulletGold s2 = new EntityBulletGold(player.worldObj, player);
		EntityBulletHighVelocity s3 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletMeteor s4 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMusket s5 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletSilver s6 = new EntityBulletSilver(player.worldObj, player);
		if(!player.worldObj.isRemote){
			switch(selectBullet(player)){
			case 1:
				player.worldObj.spawnEntityInWorld(s5);
				break;
			case 2:
				player.worldObj.spawnEntityInWorld(s6);
				break;
			case 3:
				player.worldObj.spawnEntityInWorld(s2);
				break;
			case 4:
				player.worldObj.spawnEntityInWorld(s4);
				break;
			case 5:
				player.worldObj.spawnEntityInWorld(s3);
				break;
			case 6:
				player.worldObj.spawnEntityInWorld(s1);
				break;
			}	
		}
	}
	
	public void shoot(EntityPlayer player){
		selectBullet(player);
		consumeBullet(player);
		summonBullet(player);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
	    return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}

}

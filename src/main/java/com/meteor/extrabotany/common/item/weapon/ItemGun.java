package com.meteor.extrabotany.common.item.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.meteor.extrabotany.api.extrabotany.item.IGun;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletExploding;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletGold;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletHighVelocity;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMeteor;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMusket;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSilver;
import com.meteor.extrabotany.common.handler.MathHandler;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGun extends ItemMods implements IGun{

	public ItemGun(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}
	
	ItemStack s1 = new ItemStack(ModItems.bullet,1,0);//explo
	ItemStack s2 = new ItemStack(ModItems.bullet,1,2);//high
	ItemStack s3 = new ItemStack(ModItems.bullet,1,3);//meteor
	ItemStack s4 = new ItemStack(ModItems.bullet,1,1);//gold
	ItemStack s5 = new ItemStack(ModItems.bullet,1,5);//silver
	ItemStack s6 = new ItemStack(ModItems.bullet,1,4);//musket
	
	@Override
	public ItemStack selectBullet(EntityPlayer player){
		if(player.inventory.hasItemStack(s1))
			return s1;
		else if(player.inventory.hasItemStack(s2))
			return s2;
		else if(player.inventory.hasItemStack(s3))
			return s3;
		else if(player.inventory.hasItemStack(s4))
			return s4;
		else if(player.inventory.hasItemStack(s5))
			return s5;
		else if(player.inventory.hasItemStack(s6))
			return s6;
		else return s6;
	}
	
	public void consumeBullet(EntityPlayer player){
		if(!player.worldObj.isRemote){
			player.inventory.consumeInventoryItem(selectBullet(player).getItem());
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
			switch(selectBullet(player).getItemDamage()){
			case 0:
				player.worldObj.spawnEntityInWorld(s1);
				break;
			case 1:
				player.worldObj.spawnEntityInWorld(s4);
				break;
			case 2:
				player.worldObj.spawnEntityInWorld(s2);
				break;
			case 3:
				player.worldObj.spawnEntityInWorld(s3);
				break;
			case 4:
				player.worldObj.spawnEntityInWorld(s6);
				break;
			case 5:
				player.worldObj.spawnEntityInWorld(s5);
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

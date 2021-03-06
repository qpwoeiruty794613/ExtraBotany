package com.meteor.extrabotany.common.item.relic;

import com.meteor.extrabotany.common.entity.bullet.EntityBulletSnowball;
import com.meteor.extrabotany.common.item.relic.legendary.ItemHermesTravelClothing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemGunSnowballCannon extends ItemGunRelic{

	public ItemGunSnowballCannon(String name) {
		super(name);
	}
	
	@Override
	public int selectBullet(EntityPlayer player){
		if(player.inventory.hasItem(Items.snowball))
		return 1;
		else return 0;
	}
	
	@Override
	public void consumeBullet(EntityPlayer player){
		if(selectBullet(player) == 1)
			player.inventory.consumeInventoryItem(Items.snowball);
	}
	
	@Override
	public void summonBullet(EntityPlayer player){
		EntityBulletSnowball snowball = new EntityBulletSnowball(player.worldObj, player);
		if(selectBullet(player) == 1)
			player.worldObj.spawnEntityInWorld(snowball);
	}
	
	@Override
	public int getReloadSpeed(){
		return 16;
	}
	
	@Override
	public int getReloadAmount(){
		return 16;
	}

}

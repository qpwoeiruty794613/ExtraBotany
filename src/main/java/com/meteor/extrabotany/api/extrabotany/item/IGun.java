package com.meteor.extrabotany.api.extrabotany.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IGun {
	
	public int selectBullet(EntityPlayer player);
	
	public void consumeBullet(EntityPlayer player);
	
	public void summonBullet(EntityPlayer player);
}

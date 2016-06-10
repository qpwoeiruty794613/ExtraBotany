package com.meteor.extrabotany.common.item.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemGunPistol extends ItemGun{

	public ItemGunPistol(String name) {
		super(name);
	}
	
	@Override
	public int getReloadSpeed(){
		return 16;
	}
	
	@Override
	public int getReloadAmount(){
		return 6;
	}
}

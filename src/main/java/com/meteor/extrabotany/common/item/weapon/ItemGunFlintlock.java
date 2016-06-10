package com.meteor.extrabotany.common.item.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemGunFlintlock extends ItemGun{

	public ItemGunFlintlock(String name) {
		super(name);
	}
	
	@Override
	public int getReloadSpeed(){
		return 22;
	}
	
	@Override
	public int getReloadAmount(){
		return 6;
	}
}

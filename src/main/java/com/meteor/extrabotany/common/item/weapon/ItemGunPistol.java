package com.meteor.extrabotany.common.item.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.meteor.extrabotany.api.ItemGun;

public class ItemGunPistol extends ItemGun{

	public ItemGunPistol(String name) {
		super(name);
	}
	
	int shootspeed = 16;
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingTick(stack, player, count);
			if(count <= this.getMaxItemUseDuration(stack)- shootspeed && count % shootspeed == 0){
					shoot(player);
			}
	}
}

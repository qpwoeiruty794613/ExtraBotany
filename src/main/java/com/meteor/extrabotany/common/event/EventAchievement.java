package com.meteor.extrabotany.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventAchievement {
	@SubscribeEvent
	public void onItemPickedUp(ItemPickupEvent event) {
		EntityItem e = event.pickedUp;
		ItemStack stack = event.pickedUp.getEntityItem();
		EntityPlayer player = (EntityPlayer) event.player;
        if(stack != null){
        		
	    }
    }
}

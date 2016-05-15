package com.meteor.extrabotany.common.event;

import net.minecraftforge.event.entity.item.ItemExpireEvent;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventUnbreakable {
	
	 @SubscribeEvent
	    public void onExpire(ItemExpireEvent event) {
	      if(event.entityItem instanceof EntityItemUnbreakable) {
	        event.setCanceled(true);
	      }
	    }
}

package com.meteor.extrabotany.common.event;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

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

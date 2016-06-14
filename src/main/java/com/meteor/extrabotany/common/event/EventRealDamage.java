package com.meteor.extrabotany.common.event;

import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.meteor.extrabotany.api.ExtraBotanyAPI;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventRealDamage {
	@SubscribeEvent
	 public void HurtEvent(LivingHurtEvent event) { 
	    if(ExtraBotanyAPI.isRealDamage(event.source)){
	    	event.entityLiving.setHealth(event.entityLiving.getHealth() - event.ammount);
	    	if(event.ammount >= event.entityLiving.getHealth())
	    		event.entityLiving.attackEntityFrom(ExtraBotanyAPI.damageSource[0], 5F);
	    	event.ammount = 0;
	    }
	        
	}
}

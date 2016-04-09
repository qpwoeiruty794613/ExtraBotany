package com.meteor.extrabotany.common.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.meteor.extrabotany.common.effects.ModPotionEffect;
import com.meteor.extrabotany.common.items.relic.ItemVRangerBoots;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHighDamageResistance {
	@SubscribeEvent
	 public void HurtEvent(LivingHurtEvent event) { 
	        if(!(event.entity instanceof EntityPlayerMP)) {
	            return;
	        }
	        EntityPlayer player = (EntityPlayer) event.entity;
	        if(player.isPotionActive(ModPotionEffect.slowparticlesorting)){
	        	if(event.ammount >= (float) (player.getMaxHealth()*0.2/(1+ModPotionEffect.slowparticlesorting.getEffectiveness()*0.1)) && event.ammount <= (float)(player.getMaxHealth()*3*(1+ModPotionEffect.slowparticlesorting.getEffectiveness()*0.1))){
	        		event.ammount = (float) (player.getMaxHealth()*0.2);
	        	}
	        }
	        
	            
	}
}



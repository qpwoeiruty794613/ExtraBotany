package com.meteor.extrabotany.common.event;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.meteor.extrabotany.common.entity.bullet.EntityBulletGold;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBulletGold {
	@SubscribeEvent
	 public void HurtEvent(LivingDeathEvent event) { 
	        if(event.entity instanceof EntityPlayer){
	        	return;
	        }
	        EntityLiving l = (EntityLiving)event.entityLiving;
	        if(event.source.getSourceOfDamage() instanceof EntityBulletGold)
	        	l.dropItem(Items.gold_nugget, l.worldObj.rand.nextInt(3));                 
	}
}

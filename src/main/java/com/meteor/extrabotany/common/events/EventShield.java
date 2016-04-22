package com.meteor.extrabotany.common.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventShield implements IShieldHandler{

	@SubscribeEvent
	public void onPlayerAttacked(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			if(getShieldAmount(player) > 0F){
				if(event.ammount <= getShieldAmount(player)){
				setShieldAmount(getShieldAmount(player) - event.ammount, player);
				event.ammount = 0F;
				}else if(event.ammount > getShieldAmount(player)){
					event.ammount -= getShieldAmount(player);
					setShieldAmount(0F, player);
				}
			}
		}
		
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	if (event.entity instanceof EntityPlayer && ShieldHandler.get((EntityPlayer) event.entity) == null)
	if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ShieldHandler.EXT_PROP_NAME) == null)
	event.entity.registerExtendedProperties(ShieldHandler.EXT_PROP_NAME, new ShieldHandler((EntityPlayer) event.entity));
	}

	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
	}
}

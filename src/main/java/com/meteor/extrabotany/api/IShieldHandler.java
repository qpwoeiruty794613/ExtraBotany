package com.meteor.extrabotany.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IShieldHandler {
	public float setShieldAmount(float shield, EntityPlayer player);
	/*
		if(shield <= getMaxShieldAmount(player))
		this.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
		this.currentShield = getMaxShieldAmount(player);
		return shield;
	 */
	
    public float getShieldAmount(EntityPlayer player);
    /*	
     	return ShieldHandler.currentShield; 
     */
    
    public float addShieldAmount(float shield, EntityPlayer player);
    /*
     	if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
		this.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
		this.currentShield = getMaxShieldAmount(player);
		return shield;
     */
    
    public float getMaxShieldAmount(EntityPlayer player);
    /*
     	return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
     */
}

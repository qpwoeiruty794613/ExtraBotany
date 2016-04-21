package com.meteor.extrabotany.api;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

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

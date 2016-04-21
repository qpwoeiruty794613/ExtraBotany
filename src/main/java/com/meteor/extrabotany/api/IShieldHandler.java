package com.meteor.extrabotany.api;

import com.meteor.extrabotany.common.handler.ShieldHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public interface IShieldHandler {
	public float setShieldAmount(float shield, EntityPlayer player);
	//	ShieldHandler.currentShield = shield;
	//  return shield;
	
    public float getShieldAmount(EntityPlayer player);
	//	return ShieldHandler.currentShield;
    
}

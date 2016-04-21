package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public interface IShieldHandler {
	
	public float setShieldAmount(float shield, EntityPlayer player);

    public float getShieldAmount(EntityPlayer player);
    
}

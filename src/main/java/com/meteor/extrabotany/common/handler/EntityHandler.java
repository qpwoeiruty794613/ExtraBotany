package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class EntityHandler {
	public static void knockBack(EntityLiving target, Entity attacker, float knockback, float buff){
		double d1 = attacker.posX - target.posX;
        double d0;

        for (d0 = attacker.posZ - target.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
        {
            d1 = (Math.random() - Math.random()) * 0.01D;
        }

        target.attackedAtYaw = (float)(Math.atan2(d0, d1) * 180.0D / Math.PI) - target.rotationYaw;
        target.knockBack(attacker, knockback, d1, d0);
	}
	
	public static void knockBack(EntityPlayer target, Entity attacker, float knockback, float buff){
		double d1 = attacker.posX - target.posX;
        double d0;

        for (d0 = attacker.posZ - target.posZ; d1 * d1 * buff + d0 * d0 * buff < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.02D * buff)
        {
            d1 = (Math.random() - Math.random()) * 0.02D * buff;
        }

        target.attackedAtYaw = (float)(Math.atan2(d0, d1) * 180.0D / Math.PI) - target.rotationYaw;
        target.knockBack(attacker, knockback, d1, d0);
	}
	
}

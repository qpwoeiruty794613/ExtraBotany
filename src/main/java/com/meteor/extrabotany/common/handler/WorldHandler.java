package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.Entity;

import com.meteor.extrabotany.common.world.ExplosionMagic;

public class WorldHandler {
	
	public static ExplosionMagic createMagicExplosion(Entity entity, double x, double y, double z, float power, boolean dam)
    {
        return newMagicExplosion(entity, x, y, z, power, false, dam);
    }

    public static ExplosionMagic newMagicExplosion(Entity entity, double x, double y, double z, float power, boolean p_72885_9_, boolean p_72885_10_)
    {
    	ExplosionMagic explosion = new ExplosionMagic(entity.worldObj, entity, x, y, z, power);
        explosion.isFlaming = p_72885_9_;
        explosion.isSmoking = p_72885_10_;
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(entity.worldObj, explosion)) return explosion;
        explosion.doExplosionA();
        explosion.doExplosionB(true);
        return explosion;
    }
}

package com.meteor.extrabotany.common.entity.bullet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBulletHighVelocity extends EntityBullet{

	public EntityBulletHighVelocity(World world)
	{
	    super(world);
	    this.setDamage(7F);
	}

	public EntityBulletHighVelocity(World world, EntityLivingBase entity)
	{
	    super(world, entity);
	}


	public EntityBulletHighVelocity(World world, double x, double y, double z)
	{
	    super(world, x, y, z);
	}
    
}

package com.meteor.extrabotany.common.entity.bullet;

import net.minecraft.entity.EntityLivingBase;
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

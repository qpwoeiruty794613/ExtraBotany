package com.meteor.extrabotany.common.entity.bullet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityBulletMusket extends EntityBullet{
	
	public EntityBulletMusket(World world)
	{
	    super(world);
	    this.setDamage(6F);
	}

	public EntityBulletMusket(World world, EntityLivingBase entity)
	{
	    super(world, entity);
	}

	public EntityBulletMusket(World world, double x, double y, double z)
	{
	    super(world, x, y, z);
	}
}

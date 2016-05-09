package com.meteor.extrabotany.common.entity.bullet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityBulletGold extends EntityBullet{
	
	public EntityBulletGold(World world)
	{
	    super(world);
	    this.setDamage(7F);
	}

	public EntityBulletGold(World world, EntityLivingBase entity)
	{
	    super(world, entity);
	}

	public EntityBulletGold(World world, double x, double y, double z)
	{
	    super(world, x, y, z);
	}

}

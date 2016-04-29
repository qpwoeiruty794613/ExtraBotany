package com.meteor.extrabotany.common.entity.bullet;

import vazkii.botania.common.Botania;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBulletSilver extends EntityBullet{
	
	public EntityBulletSilver(World world)
	{
	    super(world);
	    this.setDamage(9F);
	}

	public EntityBulletSilver(World world, EntityLivingBase entity)
	{
	    super(world, entity);
	}

	public EntityBulletSilver(World world, double x, double y, double z)
	{
	    super(world, x, y, z);
	}

}

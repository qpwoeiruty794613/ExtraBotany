package com.meteor.extrabotany.common.entity.bullet;

import vazkii.botania.common.Botania;

import com.meteor.extrabotany.api.ItemGun;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
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

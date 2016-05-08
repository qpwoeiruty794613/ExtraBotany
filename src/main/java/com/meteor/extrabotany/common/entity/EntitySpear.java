package com.meteor.extrabotany.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityBabylonWeapon;

public class EntitySpear extends EntityBabylonWeapon {

	public EntitySpear(World world) {
		super(world);
	}
	
	public EntitySpear(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}
	
	@Override
	protected void onImpact(MovingObjectPosition pos) {
		EntityLivingBase thrower = getThrower();
		if(pos.entityHit == null || pos.entityHit != thrower) {
			worldObj.createExplosion(this, posX, posY, posZ, 2F, false);
			setDead();
		}
	}
}

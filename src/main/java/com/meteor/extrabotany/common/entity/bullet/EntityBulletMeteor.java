package com.meteor.extrabotany.common.entity.bullet;

import vazkii.botania.common.Botania;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBulletMeteor extends EntityBullet{
	
	public EntityBulletMeteor(World world)
	{
	    super(world);
	    this.setDamage(7F);
	}

	public EntityBulletMeteor(World world, EntityLivingBase entity)
	{
	    super(world, entity);
	}

	public EntityBulletMeteor(World world, double x, double y, double z)
	{
	    super(world, x, y, z);
	}
    
    @Override
	public void onImpact(MovingObjectPosition object)
    {
        if (object.entityHit != null)
        {
        	object.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
            
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

    }
}

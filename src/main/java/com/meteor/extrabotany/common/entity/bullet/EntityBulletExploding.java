package com.meteor.extrabotany.common.entity.bullet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBulletExploding extends EntityBullet{
	
	public EntityBulletExploding(World world)
	{
	    super(world);
	}

	public EntityBulletExploding(World world, EntityLivingBase entity)
	{
	    super(world, entity);
	    
	}

	public EntityBulletExploding(World world, double x, double y, double z)
	{
	    super(world, x, y, z);
	}
	
	public static float damage = 4;
	public static int explosionPower = 2;
	public static boolean canExplode = true;
	public static boolean canDestroy = false;
	public static boolean canPass = false;
   
	@Override
	public void onImpact(MovingObjectPosition object)
    {
        if (object.entityHit != null)
        {
        	object.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
        	if(this.isBurning())
        		object.entityHit.setFire(5);
            if(this.canExplode)
            	this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionPower, this.canDestroy);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {	
        		this.setDead();
        
        }
    }
   
}

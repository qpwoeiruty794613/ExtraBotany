package com.meteor.extrabotany.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityTV extends EntityLiving{
	EntityPlayer player;
	public EntityTV(World world) {
		super(world);
	    setSize(0.6F, 0.6F);
	    this.experienceValue = 0;
	}
	
	public EntityTV(World world, EntityPlayer player){
		super(world);
		this.player = player;
	}
	
	@Override
    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40);
    }
	
    @Override
    public boolean isAIEnabled()
    {
        return false;
    }
    
    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    @Override
    public void fall(float p_70069_1_) {
    	
    }
    
    @Override
    public void onLivingUpdate()
    {

    }
    
    @Override
    public void onUpdate()
    {
      	super.onUpdate();
      	if(this.ticksExisted > 400){
      		this.setDead();
      		}
    	 Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
         Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
         vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
         vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        if (!this.worldObj.isRemote)
        {
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            EntityLivingBase entitylivingbase = this;

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity1 = (Entity)list.get(j);

                if (entity1.canBeCollidedWith() && entity1 != entitylivingbase)
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }

        if (movingobjectposition != null)
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal)
            {
                this.setInPortal();
            }
            else
            {
                this.onImpact(movingobjectposition);
            }
        }
    }

	private void onImpact(MovingObjectPosition object) {
		if(!(object.entityHit instanceof Entity22)){
			if (object.entityHit != null)
	        {	
	            this.worldObj.createExplosion(this, this.posX, this.posY + 0.2, this.posZ, 2, false);
	        }
			
	        if (!this.worldObj.isRemote)
	        {	
	        	this.setDead();   
	        }
		}
	}

}

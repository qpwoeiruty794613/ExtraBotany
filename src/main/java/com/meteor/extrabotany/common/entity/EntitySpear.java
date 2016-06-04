package com.meteor.extrabotany.common.entity;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.ItemKingKey;

public class EntitySpear extends EntityThrowable {
	
	public EntitySpear(World world) {
		super(world);
	}
	
	public EntitySpear(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}
	
    @Override
    protected float getGravityVelocity(){
        return 0.18F;
    }
    
    @Override
	protected void entityInit() {
		setSize(0F, 0F);
		dataWatcher.addObject(20, 0);//delay
    }
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		double y = this.motionY;
		
		EntityLivingBase thrower = getThrower();
		if(!worldObj.isRemote && (thrower == null || !(thrower instanceof EntityPlayer) || thrower.isDead)) {
			setDead();
			return;
		}
		EntityPlayer player = (EntityPlayer) thrower;
		if(!worldObj.isRemote) {
			AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(posX, posY, posZ, lastTickPosX, lastTickPosY, lastTickPosZ).expand(2, 2, 2);
			List<EntityLivingBase> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
			for(EntityLivingBase living : entities) {
				if(living == thrower)
					continue;

				if(living.hurtTime == 0) {
					if(player != null){
						living.attackEntityFrom(DamageSource.causePlayerDamage(player), 8F);
						living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 40, 8));
					}else {
						living.attackEntityFrom(DamageSource.generic, 8F);
					}
					onImpact(new MovingObjectPosition(living));
					return;
				}
			}
		}
		
		setDelay(Math.max(getDelay() - 1 , 0));
		
		if(getDelay() > 0){
			this.motionY = 0;
		}else {
			this.motionY = y;
		}
		
		this.motionX = 0;
		this.motionZ = 0;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition pos) {
		EntityLivingBase thrower = getThrower();
		if(pos.entityHit == null || pos.entityHit != thrower) {
			worldObj.createExplosion(this, posX, posY, posZ, 1F, false);
			setDead();
		}
	}
	
	public int getDelay(){
		return dataWatcher.getWatchableObjectInt(20);
	}
	
	public void setDelay(int i){
		dataWatcher.updateObject(20, i);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setInteger("delay", getDelay());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setDelay(cmp.getInteger("delay"));
	}
	
}

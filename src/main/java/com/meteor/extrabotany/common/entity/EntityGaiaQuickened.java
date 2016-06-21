package com.meteor.extrabotany.common.entity;

import java.util.List;

import com.meteor.extrabotany.common.handler.EntityHandler;

import vazkii.botania.common.Botania;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGaiaQuickened extends Entity{
	
	private static String TAG_ATK = "atk";
	private static String TAG_EVIL = "evil";
	
	private static int range = 8;
	
	EntityLivingBase thrower;
	
	public EntityGaiaQuickened(EntityLivingBase thrower, boolean evil, float dam) {
		super(thrower.worldObj);
		setSize(0F, 0F);
		setEvil(evil);
		setATK(dam);
		this.thrower = thrower;
	}

	public EntityGaiaQuickened(World world) {
		super(world);
		setSize(0F, 0F);
	}
	
	@Override
	public void onUpdate() {
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		super.onUpdate();
		
		for(int i = 0; i < 24; i++)
			Botania.proxy.wispFX(worldObj, posX - range + Math.random() * range * 2 * (1 + i/30), posY - range + Math.random() * range * 2 * (1 + i/30), posZ - range + Math.random() * range * 2 * (1 + i/30), 1.8F, 1.4F, 0.6F, 0.4F, -0.015F, 2);
		
		if(!worldObj.isRemote) {
			
			if(ticksExisted > 160)
				setDead();
			
			AxisAlignedBB area = AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range);
			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, area);
			List<EntityLiving> livings = worldObj.getEntitiesWithinAABB(EntityLiving.class, area);
			if(ticksExisted % 5 == 0){
				if(isEvil()){
					for(EntityPlayer player : players) {
						player.attackEntityFrom(thrower == null? DamageSource.generic:DamageSource.causeMobDamage(thrower), getATK());
						player.motionX *= 0.35F;
						player.motionY *= 0.35F;
						player.motionZ *= 0.35F;
					}
				}else {
					for(EntityLiving living : livings){
						living.attackEntityFrom(thrower == null? DamageSource.generic:DamageSource.causeMobDamage(thrower), getATK());
						living.motionX *= 0.35F;
						living.motionY *= 0.35F;
						living.motionZ *= 0.35F;
					}
				}
			}
		}
		
	}
	
	public boolean isEvil(){
		return dataWatcher.getWatchableObjectByte(25) == 1;
	}
	
	public void setEvil(boolean evil) {
		dataWatcher.updateObject(25, (byte) (evil ? 1 : 0));
	}
	
	public float getATK(){
		return dataWatcher.getWatchableObjectFloat(26);
	}
	
	public void setATK(float f){
		dataWatcher.updateObject(26, f);
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(25, (byte) 0);
		dataWatcher.addObject(26, 0F);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound cmp) {
		setATK(cmp.getFloat(TAG_ATK));
		setEvil(cmp.getBoolean(TAG_EVIL));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound cmp) {
		cmp.setBoolean(TAG_EVIL, isEvil());
		cmp.setFloat(TAG_ATK, getATK());
	}

}

package com.meteor.extrabotany.common.entity.gaia;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityMagicMissile;

public class EntityMagicMissileII extends EntityMagicMissile{
	int time = 0;
	double lockX, lockY = -1, lockZ;
	private static final String TAG_ATK = "ATK";
	public EntityMagicMissileII(EntityLivingBase thrower, boolean evil) {
		super(thrower, evil);
	}
	
	public EntityMagicMissileII(World world) {
		super(world);
		setSize(0F, 0F);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(27, 0F);
	}
	
	public float getATK(){
		return dataWatcher.getWatchableObjectFloat(27);
	}
	
	public void setATK(float atk){
		dataWatcher.updateObject(27, atk);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setFloat(TAG_ATK, getATK());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setATK(cmp.getFloat(TAG_ATK));
	}

	
	@Override
	public void onUpdate() {
		double lastTickPosX = this.lastTickPosX;
		double lastTickPosY = this.lastTickPosY;
		double lastTickPosZ = this.lastTickPosZ;

		super.onUpdate();

		if(!worldObj.isRemote && (!getTarget() || time > 40)) {
			setDead();
			return;
		}

		boolean evil = isEvil();
		Vector3 thisVec = Vector3.fromEntityCenter(this);
		Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
		Vector3 diff = thisVec.copy().sub(oldPos);
		Vector3 step = diff.copy().normalize().multiply(0.05);
		int steps = (int) (diff.mag() / step.mag());
		Vector3 particlePos = oldPos.copy();

		Botania.proxy.setSparkleFXCorrupt(evil);
		for(int i = 0; i < steps; i++) {
			Botania.proxy.sparkleFX(worldObj, particlePos.x, particlePos.y, particlePos.z, 1F, evil ? 0F : 0.4F, 1F, 0.8F, 2);
			if(worldObj.rand.nextInt(steps) <= 1)
				Botania.proxy.sparkleFX(worldObj, particlePos.x + (Math.random() - 0.5) * 0.4, particlePos.y + (Math.random() - 0.5) * 0.4, particlePos.z + (Math.random() - 0.5) * 0.4, 1F, evil ? 0F : 0.4F, 1F, 0.8F, 2);

			particlePos.add(step);
		}
		Botania.proxy.setSparkleFXCorrupt(false);

		EntityLivingBase target = getTargetEntity();
		if(target != null) {
			if(lockY == -1) {
				lockX = target.posX;
				lockY = target.posY;
				lockZ = target.posZ;
			}

			Vector3 targetVec = evil ? new Vector3(lockX, lockY, lockZ) : Vector3.fromEntityCenter(target);
			Vector3 diffVec = targetVec.copy().sub(thisVec);
			Vector3 motionVec = diffVec.copy().normalize().multiply(evil ? 0.5 : 0.6);
			motionX = motionVec.x;
			motionY = motionVec.y;
			if(time < 10)
				motionY = Math.abs(motionY);
			motionZ = motionVec.z;

			List<EntityLivingBase> targetList = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(posX - 0.5, posY - 0.5, posZ - 0.5, posX + 0.5, posY + 0.5, posZ + 0.5));
			if(targetList.contains(target) && target != null) {
				EntityLivingBase thrower = getThrower();
				if(thrower != null) {
					EntityPlayer player = thrower instanceof EntityPlayer ? (EntityPlayer) thrower : null;
					target.attackEntityFrom(player == null ? DamageSource.causeMobDamage(thrower) : DamageSource.causePlayerDamage(player), getATK());
				} else {
					target.attackEntityFrom(DamageSource.generic, getATK());
				}
				
				setDead();
			}

			if(evil && diffVec.mag() < 1)
				setDead();
		}

		time++;
	}
}

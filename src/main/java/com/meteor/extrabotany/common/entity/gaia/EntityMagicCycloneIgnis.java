package com.meteor.extrabotany.common.entity.gaia;

import java.util.List;

import vazkii.botania.common.Botania;
import vazkii.botania.common.item.relic.ItemRelic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMagicCycloneIgnis extends Entity{
	private static float X = 1F;
	private static float Z = 1F;
	
	private static final String TAG_ANGEL_X = "angelX";
	private static final String TAG_ANGEL_Z = "angelZ";
	public EntityMagicCycloneIgnis(World world) {
		super(world);
		setSize(0F, 0F);
	}
	
	public static void setAngel(float angelX, float angelZ){
		X = angelX;
		Z = angelZ;
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setFloat(this.TAG_ANGEL_X, X);
		nbt.setFloat(this.TAG_ANGEL_Z, Z);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		float x = nbt.getFloat(this.TAG_ANGEL_X);
		float z = nbt.getFloat(this.TAG_ANGEL_Z);
		setAngel(x,z);
	}
	
	public static boolean spawn(World world, double x, double y ,double z, float angelX, float angelZ){
		EntityMagicCycloneIgnis i = new EntityMagicCycloneIgnis(world);
		i.setAngel(angelX, angelZ);
		i.setPosition(x, y, z);
		world.spawnEntityInWorld(i);
		return true;
	}
	
	@Override
	public void onUpdate() {
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		super.onUpdate();
		if(this.ticksExisted >= 440)
			this.setDead();
		
		float range = 1.5F;
		for(int i = 0; i < 81; i++){
			double rx = range * i * 0.02 * this.X;
			double rz = range * i * 0.02 * this.Z;
			Botania.proxy.wispFX(worldObj, posX - range + Math.random() * rx, posY + i * 0.05, posZ + range - Math.random() * rz, 2.55F, 0.2F, 0.2F, 0.22F, -0.015F, 1);
			if(i == 80)
				break;
		}
		
		if(!worldObj.isRemote) {
			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));
			for(EntityPlayer player : players) {
				if(ticksExisted % 20 == 0)
					player.setFire(5);
					player.attackEntityFrom(DamageSource.magic, 5.5F);
					player.attackEntityFrom(ItemRelic.damageSource(), 4.5F);
			}
		}
	}

	@Override
	protected void entityInit() {
		
	}

}

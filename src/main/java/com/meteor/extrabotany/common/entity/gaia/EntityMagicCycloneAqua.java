package com.meteor.extrabotany.common.entity.gaia;

import java.util.List;

import com.meteor.extrabotany.common.handler.EntityHandler;
import com.meteor.extrabotany.common.util.Sound;
import com.meteor.extrabotany.common.util.SoundHelper;

import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.relic.ItemRelic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMagicCycloneAqua extends Entity{
	private static float X = 1F;
	private static float Z = 1F;
	
	private static final String TAG_ANGEL_X = "angelX";
	private static final String TAG_ANGEL_Z = "angelZ";
	public EntityGaiaIII summoner;
	public EntityMagicCycloneAqua(World world) {
		super(world);
		setSize(0F, 0F);
	}
	
	public static boolean spawn(World world, double x, double y ,double z, float angelX, float angelZ){
		EntityMagicCycloneAqua i = new EntityMagicCycloneAqua(world);
		i.setAngel(angelX, angelZ);
		i.setPosition(x, y, z);
		world.spawnEntityInWorld(i);
		return true;
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
	
	@Override
	public void onUpdate() {
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		super.onUpdate();
		if(this.ticksExisted >= 440)
			this.setDead();
		
		float range = 1F;
		for(int i = 0; i < 81; i++){
			double rx = range * i * 0.02 * this.X;
			double rz = range * i * 0.02 * this.Z;
			Botania.proxy.wispFX(worldObj, posX - range + Math.random() * rx, posY + i * 0.05, posZ + range - Math.random() * rz, 0.46F, 2.08F, 2.29F, 0.22F, -0.015F, 1);
			if(i == 80)
				break;
		}
		
		if(!worldObj.isRemote) {	
			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));
			for(EntityPlayer player : players) {
				if(ticksExisted % 10 == 0)
					player.attackEntityFrom(DamageSource.magic, 1.5F);
					player.attackEntityFrom(ItemRelic.damageSource(), 1.5F);
					EntityHandler.knockBack(player, this, 10F, 8F);
					if(players.size() > 0)
						SoundHelper.playSoundAtEntity(worldObj, Sound.ATTACK_FROST, this, 0.8F + (float) Math.random() * 0.2F);
			}
		}
	}

	@Override
	protected void entityInit() {
		
	}

}

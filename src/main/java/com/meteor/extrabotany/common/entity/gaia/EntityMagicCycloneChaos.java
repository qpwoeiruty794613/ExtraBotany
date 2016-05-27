package com.meteor.extrabotany.common.entity.gaia;

import java.awt.Color;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.handler.EntityHandler;

public class EntityMagicCycloneChaos extends Entity{
	private static float X = 1F;
	private static float Z = 1F;
	
	private static final String TAG_ANGEL_X = "angelX";
	private static final String TAG_ANGEL_Z = "angelZ";
	public EntityGaiaIII summoner;
	public EntityMagicCycloneChaos(World world) {
		super(world);
		setSize(0F, 0F);
	}
	
	public static boolean spawn(World world, double x, double y ,double z, float angelX, float angelZ){
		EntityMagicCycloneChaos i = new EntityMagicCycloneChaos(world);
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
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setFloat(this.TAG_ANGEL_X, X);
		nbt.setFloat(this.TAG_ANGEL_Z, Z);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
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
		float ran = 24F;
		Color color = new Color(0x6E086C);
		for(int i = 0; i < 81; i++){
			double rx = range * i * 0.02 * this.X;
			double rz = range * i * 0.02 * this.Z;
			Botania.proxy.wispFX(worldObj, posX - range + Math.random() * rx, posY + i * 0.05, posZ + range - Math.random() * rz, color.getRed()/255F, color.getGreen()/255F, color.getBlue()/255F, 0.22F, -0.015F, 1);
			if(i == 80)
				break;
		}
		
		if(!worldObj.isRemote) {	
			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));
			for(EntityPlayer player : players) {
				EntityHandler.knockBack(player, this, 10F, 8F);
				if(ticksExisted % 15 == 0)
					player.attackEntityFrom(DamageSource.magic, 7F);
				else if(ticksExisted % 24 == 0)
					player.attackEntityFrom(ItemRelic.damageSource(), 7F);
				if(players.size() > 0)
					worldObj.playSoundAtEntity(this, "botania:attack.shadow", 0.6F, 0.8F + (float) Math.random() * 0.2F);
			}
		}
	}

	@Override
	protected void entityInit() {
		
	}
}

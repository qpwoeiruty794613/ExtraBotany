package com.meteor.extrabotany.common.entity;

import java.util.List;

import com.meteor.extrabotany.common.effects.ModPotionEffect;

import vazkii.botania.common.Botania;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMagicLandmineII extends Entity {

	public EntityGaiaIII summoner;

	public EntityMagicLandmineII(World par1World) {
		super(par1World);
		setSize(0F, 0F);
	}

	@Override
	public void onUpdate() {
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		super.onUpdate();

		float range = 2.5F;

		float r = 0.2F;
		float g = 0F;
		float b = 0.2F;

		//Botania.proxy.wispFX(worldObj, posX, posY, posZ, r, g, b, 0.6F, -0.2F, 1);
		for(int i = 0; i < 6; i++)
			Botania.proxy.wispFX(worldObj, posX - range + Math.random() * range * 2, posY, posZ - range + Math.random() * range * 2, r, g, b, 0.4F, -0.015F, 1);

		if(ticksExisted >= 55) {
			worldObj.playSoundEffect(posX, posY, posZ, "botania:gaiaTrap", 0.3F, 1F);

			float m = 0.35F;
			g = 0.4F;
			for(int i = 0; i < 25; i++)
				Botania.proxy.wispFX(worldObj, posX, posY + 1, posZ, r, g, b, 0.5F, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * m);

			if(!worldObj.isRemote) {
				List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));
				for(EntityPlayer player : players) {
					player.attackEntityFrom(summoner == null ? DamageSource.generic : DamageSource.causeMobDamage(summoner), 10);
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 20, 0));
					player.addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 0));
					player.addPotionEffect(new PotionEffect(ModPotionEffect.residualpain.getId(), 60, 0));
					PotionEffect wither = new PotionEffect(Potion.wither.id, 50, 3);
					wither.getCurativeItems().clear();
					player.addPotionEffect(wither);
				}
			}

			setDead();
		}
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}
}

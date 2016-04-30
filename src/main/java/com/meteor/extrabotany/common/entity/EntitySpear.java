package com.meteor.extrabotany.common.entity;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

public class EntitySpear extends EntityBabylonWeapon {

	public EntitySpear(World world) {
		super(world);
	}
	
	public EntitySpear(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}
	
	@Override
	protected void onImpact(MovingObjectPosition pos) {
		EntityLivingBase thrower = getThrower();
		if(pos.entityHit == null || pos.entityHit != thrower) {
			worldObj.createExplosion(this, posX, posY, posZ, 2F, false);
			setDead();
		}
	}
}

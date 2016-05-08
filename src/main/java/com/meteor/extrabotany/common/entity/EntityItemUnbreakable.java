package com.meteor.extrabotany.common.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemUnbreakable extends EntityItem {

	 public EntityItemUnbreakable(World worldIn, double x, double y, double z) {
	   super(worldIn, x, y, z);
	   isImmuneToFire = true;
	 }

	 public EntityItemUnbreakable(World worldIn, double x, double y, double z, ItemStack stack) {
	   super(worldIn, x, y, z, stack);
	   isImmuneToFire = true;
	 }

	 public EntityItemUnbreakable(World worldIn) {
	   super(worldIn);
	   isImmuneToFire = true;
	 }

	 @Override
	 public boolean attackEntityFrom(DamageSource source, float amount) {
	   if(source.getDamageType().equals(DamageSource.outOfWorld.damageType)) {
	     return true;
	   }
	    return false;
	 }
}

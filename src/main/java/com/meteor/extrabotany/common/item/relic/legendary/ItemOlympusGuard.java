package com.meteor.extrabotany.common.item.relic.legendary;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelicBauble;
import baubles.api.BaubleType;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;

public class ItemOlympusGuard extends ItemRelicBauble{

	public ItemOlympusGuard(String name) {
		super(name);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItem entity = new EntityItemUnbreakable(world, location.posX, location.posY, location.posZ, itemstack);
		if(location instanceof EntityItem) {
		    entity.delayBeforeCanPickup = 40;
		}
		entity.motionX = location.motionX;
		entity.motionY = location.motionY;
		entity.motionZ = location.motionZ;
		return entity;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

}

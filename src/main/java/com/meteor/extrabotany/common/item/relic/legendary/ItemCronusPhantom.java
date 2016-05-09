package com.meteor.extrabotany.common.item.relic.legendary;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelicBauble;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.item.ModItems;

public class ItemCronusPhantom extends ItemRelicBauble{

	public ItemCronusPhantom(String name) {
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
	
	public static ItemStack getCronusPhantom(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isCronusPhantom(stack1) ? stack1 : isCronusPhantom(stack2) ? stack2 : null;
	}

	private static boolean isCronusPhantom(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.cronusphantom || stack.getItem() == ModItems.olympusguard);
	}

}

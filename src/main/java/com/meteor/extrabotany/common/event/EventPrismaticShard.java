package com.meteor.extrabotany.common.event;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventPrismaticShard {
	@SubscribeEvent
	 public void HarvestEvent(BlockEvent.HarvestDropsEvent event) { 
		if(event.isSilkTouching)
			return;
		else if(event.block.getMaterial() == Material.rock){
			int p = event.harvester.worldObj.rand.nextInt(1000 - event.fortuneLevel * 65);
			if(p == 300){
				EntityItem i = new EntityItem(event.world, event.harvester.posX, event.harvester.posY, event.harvester.posZ, new ItemStack(ModItems.material, 1, 0));
				i.delayBeforeCanPickup = 10;
				event.world.spawnEntityInWorld(i);
				}
		}
	}
}

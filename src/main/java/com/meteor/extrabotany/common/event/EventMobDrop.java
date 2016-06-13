package com.meteor.extrabotany.common.event;

import java.util.Random;

import com.meteor.extrabotany.common.item.ModItems;

import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventMobDrop {
	@SubscribeEvent
	public void onMobDeath(LivingDeathEvent event) {
		if(event.entity instanceof IMob){
			if(!event.entity.worldObj.isRemote){
				Random r = event.entity.worldObj.rand;
				if(Math.random() > 0.7)
					event.entity.entityDropItem(new ItemStack(ModItems.material, 1 + r.nextInt(3), 13), 1F);
				if(Math.random() > 0.92)
					event.entity.entityDropItem(new ItemStack(ModItems.material, 1 + r.nextInt(3), 14), 1F);
				if(Math.random() < 0.14)
					event.entity.entityDropItem(new ItemStack(ModItems.material, 1 + r.nextInt(3), 15), 1F);
			}
		}
    }
}

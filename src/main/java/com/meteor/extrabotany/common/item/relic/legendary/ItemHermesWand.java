package com.meteor.extrabotany.common.item.relic.legendary;

import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.handler.EntityHandler;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHermesWand extends ItemRelicAdv implements IManaUsingItem{

	public ItemHermesWand(String name) {
		super(name);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote){
			EntityTeleportPearl p = new EntityTeleportPearl(world, player);
			world.spawnEntityInWorld(p);
			if(!ItemHermesTravelClothing.hasHermesTravelClothing(player))
				ManaItemHandler.requestManaExact(stack, player, 100, true);
		}
		return stack;
	}
	
	@Override
	public boolean usesMana(ItemStack stack) {
		return true;
	}

}

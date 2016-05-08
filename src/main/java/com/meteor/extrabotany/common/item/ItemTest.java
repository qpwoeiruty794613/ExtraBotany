package com.meteor.extrabotany.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemTest extends ItemMods{

	public ItemTest(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		list.add(StatCollector.translateToLocal("item.test.desc"));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {		
		return stack;
	}

}

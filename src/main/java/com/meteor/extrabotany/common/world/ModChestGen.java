package com.meteor.extrabotany.common.world;

import com.meteor.extrabotany.common.item.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ModChestGen {
	public static void init(){
		addAll(new ItemStack(ModItems.boxs), 1, 1, 1, false);
		addAll(new ItemStack(ModItems.dungeonbox), 1, 2, 1, false);
		addAll(new ItemStack(ModItems.material, 1, 12), 1, 2, 2, false);
		addAll(new ItemStack(ModItems.gunboomstick), 1, 2, 1, false);
		addAll(new ItemStack(ModItems.gunshotgun), 1, 2, 1, false);
		addAll(new ItemStack(ModItems.gunflintlock), 1, 2, 1, false);
	}
	
	public static void addAll(ItemStack itemstack, int min, int max, int stacksize, boolean lib){
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(itemstack, min, max, stacksize));
		if(lib)
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(itemstack, min, max, stacksize));
	}
}

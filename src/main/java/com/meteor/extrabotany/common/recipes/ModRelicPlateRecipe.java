package com.meteor.extrabotany.common.recipes;

import java.util.List;

import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.items.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class ModRelicPlateRecipe {
	//Astral Force Recipe
	public static boolean craft1(List<EntityItem> items) {
		if(items.size() != 3)
			return false;

		ItemStack item1 = null;
		ItemStack item2 = null;
		ItemStack item3 = null;

		for(EntityItem item : items) {
			ItemStack stack = item.getEntityItem();
			if(stack.stackSize != 1)
				return false;
			if(stack.getItem() == ModItems.blankcard)
				item1 = stack;
			else if(stack.getItem() == ModItems.gaiaquartz)
				item2 = stack;
			else if(stack.getItem() == ModItems.gaiaessence)
				item3 = stack;
			else return false;
		}

		return item1 != null && item2 != null && item3 != null;
	}
	
	//D20 Recipe
	public static boolean craft2(List<EntityItem> items) {
		if(items.size() != 3)
			return false;

		ItemStack item1 = null;
		ItemStack item2 = null;
		ItemStack item3 = null;
		for(EntityItem item : items) {
			ItemStack stack = item.getEntityItem();
			if(stack.stackSize != 1)
				return false;
			if(stack.getItem() instanceof IRelic)
				item1 = stack;
			else if(stack.getItem() == ModItems.empty_dice)
				item2 = stack;
			else if(stack.getItem() == ModItems.gaiaessence)
				item3 = stack;
			else return false;
		}

		return item1 != null && item2 != null && item3 != null;
	}
}

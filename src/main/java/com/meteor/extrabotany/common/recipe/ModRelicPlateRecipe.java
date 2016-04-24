package com.meteor.extrabotany.common.recipe;

import java.util.List;

import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.item.ModItems;

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
			if(stack == new ItemStack(ModItems.material,1,1))
				item1 = stack;
			else if(stack == new ItemStack(ModItems.material,1,7))
				item2 = stack;
			else if(stack == new ItemStack(ModItems.material,1,2))
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
			else if(stack == new ItemStack(ModItems.material,1,10))
				item2 = stack;
			else if(stack == new ItemStack(ModItems.material,1,2))
				item3 = stack;
			else return false;
		}

		return item1 != null && item2 != null && item3 != null;
	}

}

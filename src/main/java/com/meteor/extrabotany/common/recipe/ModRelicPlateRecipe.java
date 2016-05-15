package com.meteor.extrabotany.common.recipe;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.item.ModItems;

public class ModRelicPlateRecipe{
	public static ItemStack itemA;
	public static ItemStack itemB;
	public static ItemStack itemC;
	
	public static ItemStack[] recipeItems;
	
	public static boolean Recipe(List<EntityItem> items){
		if(items.size() != 3)
			return false;
		
		itemA = null;
		itemB = null;
		itemC = null;
		
		for(EntityItem item : items) {
			ItemStack stack = item.getEntityItem();
			if(stack.stackSize != 1)
				return false;
			if(stack == recipeItems[0])
				itemA = stack;
			else if(stack == recipeItems[1])
				itemB = stack;
			else if(stack == recipeItems[2])
				itemC = stack;
			else return false;
		}	
		return itemA != null && itemB != null && itemC != null;
	}
	
}

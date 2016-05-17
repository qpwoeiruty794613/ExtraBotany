package com.meteor.extrabotany.common.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.StatCollector;

public class CraftingHandler {
	public static int countCrafting = 0;
	public static int countFurnace = 0;
	
	public static void RemoveCrafting(Item item) {
		
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		
		Iterator<IRecipe> remover = recipes.iterator();
		
			while(remover.hasNext()) {
				ItemStack itemstack = remover.next().getRecipeOutput();
				if(itemstack != null && itemstack.getItem() == item) {
					remover.remove();
					countCrafting++;
					FMLLog.log(Level.INFO, "Removed" + " crafting recipes of " + StatCollector.translateToLocal(itemstack.getUnlocalizedName() + ".name") + ".");
				}
			}
		
	}

	public static void RemoveFurnace (ItemStack itemstack) {
		
		Map<ItemStack, ItemStack> recipe = FurnaceRecipes.smelting().getSmeltingList();
		
		for (Iterator<Map.Entry<ItemStack, ItemStack>> entries = recipe.entrySet().iterator(); entries.hasNext(); ) {
			Map.Entry<ItemStack, ItemStack> entry = entries.next();
			ItemStack result = entry.getValue();
			if (ItemStack.areItemStacksEqual(result, itemstack)) {
				entries.remove();
				countFurnace++;
				FMLLog.log(Level.INFO, "Removed" + " furnace recipes of " + StatCollector.translateToLocal(itemstack.getUnlocalizedName() + ".name") + ".");
			}
		}
		
	}
	
}

package com.meteor.extrabotany.common.recipe;

import com.meteor.extrabotany.common.item.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;

public class ModManaInfusionRecipe {
	
	public static RecipeManaInfusion blankCardRecipe;
	
	public static void init() {
		
		blankCardRecipe = BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.material,1,1), new ItemStack(Items.paper), 5000);

	}
}

package com.meteor.extrabotany.common.recipes;


import com.meteor.extrabotany.common.items.ModItems;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipe {
	

	
	public static void init(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.empty_dice), new Object[] {"AAA","ABA","AAA", 'A', new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 18), 'B', ModItems.gaiaessence});
	}
}

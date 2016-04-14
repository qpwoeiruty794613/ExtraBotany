package com.meteor.extrabotany.common.recipes;


import com.meteor.extrabotany.common.blocks.ModBlocks;
import com.meteor.extrabotany.common.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipe {
	

	
	public static void init(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.gaiaquartz, 8), new Object[] {"AAA","ABA","AAA", 'A', new ItemStack(vazkii.botania.common.item.ModItems.quartz, 1, 1), 'B', ModItems.gaiaessence});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gaiatotembase), new Object[] {"AA","AA", 'A', ModItems.gaiaquartz});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gaiatotembase, 2), new Object[] {"A","A", 'A', ModBlocks.gaiatotem});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gaiatotem, 2), new Object[] {"A","A", 'A', ModBlocks.gaiatotembase});
	}
}

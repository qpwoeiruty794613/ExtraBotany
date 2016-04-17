package com.meteor.extrabotany.common.recipes;


import com.meteor.extrabotany.common.blocks.ModBlocks;
import com.meteor.extrabotany.common.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipe {
	

	
	public static void init(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.gaiaquartz, 8), 
				new Object[] {"AAA","ABA","AAA", 'A', new ItemStack(vazkii.botania.common.item.ModItems.quartz, 1, 1), 'B', ModItems.gaiaessence});
	}
}

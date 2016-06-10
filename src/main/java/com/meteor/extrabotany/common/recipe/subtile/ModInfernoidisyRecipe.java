package com.meteor.extrabotany.common.recipe.subtile;

import net.minecraft.init.Blocks;
import vazkii.botania.common.block.ModBlocks;

import com.meteor.extrabotany.api.ExtraBotanyAPI;

public class ModInfernoidisyRecipe {
	public static void init() {
		ExtraBotanyAPI.registerInfernoidisyRecipe(Blocks.cobblestone, Blocks.netherrack, 0);
		ExtraBotanyAPI.registerInfernoidisyRecipe(Blocks.sand, Blocks.soul_sand, 0);
		ExtraBotanyAPI.registerInfernoidisyRecipe(ModBlocks.livingrock, Blocks.stone, 0);
		ExtraBotanyAPI.registerInfernoidisyRecipe(ModBlocks.livingwood, Blocks.log, 0);
		ExtraBotanyAPI.registerInfernoidisyRecipe(Blocks.snow, Blocks.water, 0);
		ExtraBotanyAPI.registerInfernoidisyRecipe(Blocks.obsidian, ModBlocks.blazeBlock, 0);
		ExtraBotanyAPI.registerInfernoidisyRecipe(Blocks.packed_ice, Blocks.ice, 0);
	}
}

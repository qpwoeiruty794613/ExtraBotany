package com.meteor.extrabotany.common.recipe.subtile;

import net.minecraft.init.Blocks;

import com.meteor.extrabotany.api.ExtraBotanyAPI;

public class ModStonesiaRecipe {
	public static void init() {
		ExtraBotanyAPI.registerStonesiaRecipe(Blocks.stone, 1, 0);
		ExtraBotanyAPI.registerStonesiaRecipe(Blocks.cobblestone, 1, 0);
		ExtraBotanyAPI.registerStonesiaRecipe("oreIron", 256, 0);
	}
}

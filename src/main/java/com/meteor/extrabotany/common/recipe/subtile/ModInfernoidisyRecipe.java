package com.meteor.extrabotany.common.recipe.subtile;

import net.minecraft.init.Blocks;

import com.meteor.extrabotany.api.ExtraBotanyAPI;

public class ModInfernoidisyRecipe {
	public static void init() {
		ExtraBotanyAPI.registerInfernoidisyRecipe(Blocks.netherrack, Blocks.cobblestone, 0);
	}
}

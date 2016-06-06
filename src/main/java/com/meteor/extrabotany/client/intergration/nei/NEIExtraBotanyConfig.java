package com.meteor.extrabotany.client.intergration.nei;

import com.meteor.extrabotany.client.intergration.nei.recipe.RecipeHandlerInfernoidisy;
import com.meteor.extrabotany.common.lib.LibReference;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIExtraBotanyConfig implements IConfigureNEI{

	@Override
	public String getName() {
		return LibReference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return LibReference.VERSION;
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new RecipeHandlerInfernoidisy());
		API.registerUsageHandler(new RecipeHandlerInfernoidisy());
	}

}

package com.meteor.extrabotany.common;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.ModMultiBlocks;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileStonesia;
import com.meteor.extrabotany.common.command.ModCommands;
import com.meteor.extrabotany.common.enchantment.ModEnchantment;
import com.meteor.extrabotany.common.entity.ModEntities;
import com.meteor.extrabotany.common.event.ModEvents;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.GuiHandler;
import com.meteor.extrabotany.common.integration.Intergration;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.potion.PotionEffectMods;
import com.meteor.extrabotany.common.recipe.ModRecipe;
import com.meteor.extrabotany.common.world.ModWorldGen;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigHandler(event);
		PotionEffectMods.init();
		ModBlocks.init();
		ModItems.init();
		ModEnchantment.init();
		ModEntities.init();
		ModRecipe.init();
		ModAchievement.init();
		ModMultiBlocks.init();
		Intergration.preInit(event);
		ModWorldGen.init();
		LexiconModData.init();
	}
	
	public void init(FMLInitializationEvent event) {
		ModEvents.init();
		Intergration.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(ExtraBotany.instance, new GuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		Intergration.postInit(event);
	}
	
    public void serverStarting(FMLServerStartingEvent event)
    {
    	new ModCommands(event);
    }
}

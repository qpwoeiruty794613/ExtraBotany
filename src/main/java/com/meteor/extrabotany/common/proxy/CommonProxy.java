package com.meteor.extrabotany.common.proxy;



import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.core.handler.InternalMethodHandler;






import com.meteor.extrabotany.common.blocks.ModBlocks;
import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
import com.meteor.extrabotany.common.entity.ModEntities;
import com.meteor.extrabotany.common.events.ModEvents;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.recipes.ModManaInfusionRecipe;
import com.meteor.extrabotany.common.recipes.ModPetalRecipe;
import com.meteor.extrabotany.common.recipes.ModRecipe;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		BotaniaAPI.internalHandler = new InternalMethodHandler();
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		ModPetalRecipe.init();
		ModRecipe.init();
		ModManaInfusionRecipe.init();
		LexiconModData.init();
	}
	
	public void init(FMLInitializationEvent event) {
		ModEvents.init();
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}

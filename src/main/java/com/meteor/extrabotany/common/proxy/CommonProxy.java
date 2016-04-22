package com.meteor.extrabotany.common.proxy;



import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.core.handler.InternalMethodHandler;



import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.blocks.ModBlocks;
import com.meteor.extrabotany.common.blocks.ModMutilBlocks;
import com.meteor.extrabotany.common.commands.CommandGetShieldAmount;
import com.meteor.extrabotany.common.commands.ModCommands;
import com.meteor.extrabotany.common.effects.ModPotionEffect;
import com.meteor.extrabotany.common.effects.PotionEffectMods;
import com.meteor.extrabotany.common.enchantment.ModEnchantment;
import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
import com.meteor.extrabotany.common.entity.ModEntities;
import com.meteor.extrabotany.common.events.ModEvents;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.recipes.ModManaInfusionRecipe;
import com.meteor.extrabotany.common.recipes.ModPetalRecipe;
import com.meteor.extrabotany.common.recipes.ModRecipe;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		BotaniaAPI.internalHandler = new InternalMethodHandler();
		new ConfigHandler(event);
		ModBlocks.init();
		ModItems.init();
		ModEnchantment.init();
		ModEntities.init();
		ModPetalRecipe.init();
		ModRecipe.init();
		ModAchievement.init();
		ModManaInfusionRecipe.init();
		ModPotionEffect.init();
		LexiconModData.init();
		ModMutilBlocks.init();
	}
	
	public void init(FMLInitializationEvent event) {
		ModEvents.init();
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
    public void serverStarting(FMLServerStartingEvent event)
    {
    	new ModCommands(event);
    }
}

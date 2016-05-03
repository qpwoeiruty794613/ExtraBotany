package com.meteor.extrabotany.common;

import net.minecraft.item.ItemRecord;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.ModMutilBlocks;
import com.meteor.extrabotany.common.command.ModCommands;
import com.meteor.extrabotany.common.effect.ModPotionEffect;
import com.meteor.extrabotany.common.effect.PotionEffectMods;
import com.meteor.extrabotany.common.enchantment.ModEnchantment;
import com.meteor.extrabotany.common.entity.ModEntities;
import com.meteor.extrabotany.common.event.ModEvents;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.recipe.ModManaInfusionRecipe;
import com.meteor.extrabotany.common.recipe.ModPetalRecipe;
import com.meteor.extrabotany.common.recipe.ModRecipe;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigHandler(event);
		ModBlocks.init();
		ModItems.init();
		ModEnchantment.init();
		ModEntities.init();
		ModRecipe.init();
		ModAchievement.init();
		PotionEffectMods.init();
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
    
    public void playRecordClientSided(World world, int x, int y, int z, ItemRecord record) {
		// NO-OP
	}
}

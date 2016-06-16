package com.meteor.extrabotany.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.ModMultiBlocks;
import com.meteor.extrabotany.common.command.ModCommands;
import com.meteor.extrabotany.common.enchantment.ModEnchantment;
import com.meteor.extrabotany.common.entity.FakePlayer;
import com.meteor.extrabotany.common.entity.ModEntities;
import com.meteor.extrabotany.common.event.ModEvents;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.GuiHandler;
import com.meteor.extrabotany.common.integration.Intergration;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.potion.ModPotions;
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
		ModPotions.init();
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
		new FakePlayer();
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
    
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
    
    public static void storeEntityData(String name, NBTTagCompound compound)
    {
    	extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
    	return extendedEntityData.remove(name);
    }
    
}

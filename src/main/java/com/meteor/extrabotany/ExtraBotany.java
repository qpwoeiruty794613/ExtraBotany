package com.meteor.extrabotany;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;

import com.meteor.extrabotany.common.CommonProxy;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = LibReference.MOD_ID, name = LibReference.MOD_NAME, version = LibReference.VERSION, dependencies = LibReference.DEPENDENCIES)
public class ExtraBotany {
	@Instance(LibReference.MOD_ID)
	public static ExtraBotany instance;

	@SidedProxy(serverSide = LibReference.PROXY_COMMON, clientSide = LibReference.PROXY_CLIENT)
	public static CommonProxy proxy;
	
	public static boolean arsmagicaLoaded = false;
	public static boolean candycraftLoaded = false;
	
	public static KnowledgeType extraKnowledge;
	
	public static final ExtraBotanyCreativeTab tabExtraBotany = new ExtraBotanyCreativeTab(); 
	public static Set<String> subtilesForCreativeMenu = new LinkedHashSet();
	
	public static void addSubTileToCreativeMenu(String key) {
		subtilesForCreativeMenu.add(key);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		arsmagicaLoaded = Loader.isModLoaded("Ars Magica 2");
		candycraftLoaded = Loader.isModLoaded("CandyCraft");
		extraKnowledge = BotaniaAPI.registerKnowledgeType("extra", EnumChatFormatting.DARK_AQUA, false);
		proxy.preInit(event);
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
	
	@EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
    	proxy.serverStarting(event);
    }

}

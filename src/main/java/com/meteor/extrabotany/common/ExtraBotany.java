package com.meteor.extrabotany.common;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;

import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataGreen;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataPurple;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataRed;
import com.meteor.extrabotany.client.render.entity.RenderTeleportPearl;
import com.meteor.extrabotany.client.render.tile.RenderTileRelicPlate;
import com.meteor.extrabotany.common.blocks.tile.TileRelicPlate;
import com.meteor.extrabotany.common.commands.CommandGetShieldAmount;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;
import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.lib.LibReference;
import com.meteor.extrabotany.common.proxy.CommonProxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
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

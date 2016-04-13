package com.meteor.extrabotany.common;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;

import com.meteor.extrabotany.client.proxy.ClientProxy;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataGreen;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataPurple;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataRed;
import com.meteor.extrabotany.client.render.entity.RenderTeleportPearl;
import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;
import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibReference;
import com.meteor.extrabotany.common.proxy.CommonProxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLycorisradiataRed.class, new RenderLycorisradiataRed());
		RenderingRegistry.registerEntityRenderingHandler(EntityLycorisradiataGreen.class, new RenderLycorisradiataGreen());
		RenderingRegistry.registerEntityRenderingHandler(EntityLycorisradiataPurple.class, new RenderLycorisradiataPurple());
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportPearl.class, new RenderTeleportPearl(1.0F));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

}

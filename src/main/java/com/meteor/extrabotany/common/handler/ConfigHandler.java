package com.meteor.extrabotany.common.handler;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	private static Configuration config;
	
	public static int efficiencySunshinelily;
	public static int efficiencyMoonlightlily;
	public static int efficiencyBlueenchantress;
	public static int efficiencyCandyflower;
	public static int efficiencyGeminiorchid;
	public static int efficiencyOminiviolet;
	public static int efficiencyPyschobloom;
	public static int efficiencyStonesia;
	
	public static int pyschobloomMax;
	
	public static int enchGaiaBlessing;
	public static int enchDivineFavor;
	public static int enchDivineMark;
	
	public static int extraShieldAmount;
	
	public static boolean disableShieldRender;
	public static boolean disableEasterEgg;

	public ConfigHandler(FMLPreInitializationEvent event)
	{
		config = new Configuration(new File(new File(event.getModConfigurationDirectory(), "extrameteorp"), LibReference.MOD_NAME+".cfg"));
	    config.load();
	    registerConfig();
	    config.save();
	}

	private static void registerConfig()
	{		
		enchGaiaBlessing = config.get(LibReference.CATEGORY_ID, "enchGaiaBlessing", 203, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "ench.desc")).getInt();
		enchDivineFavor = config.get(LibReference.CATEGORY_ID, "enchDivineFavor", 204, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "ench.desc")).getInt();
		enchDivineMark = config.get(LibReference.CATEGORY_ID, "enchDivineMark", 205, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "ench.desc")).getInt();
		
		extraShieldAmount = config.get(LibReference.CATEGORY_COMMON, "extraShieldAmount", 0, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "extraShieldAmount.desc")).getInt();
		
		efficiencySunshinelily = config.get(LibReference.CATEGORY_COMMON, "efficiencySunshinelily", 3, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencySunshinelily.desc")).getInt();
		efficiencyMoonlightlily = config.get(LibReference.CATEGORY_COMMON, "efficiencyMoonlightlily", 3, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyMoonlightlily.desc")).getInt();
		efficiencyBlueenchantress = config.get(LibReference.CATEGORY_COMMON, "efficiencyBlueenchantress", 100, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyBlueenchantress.desc")).getInt();
		efficiencyCandyflower = config.get(LibReference.CATEGORY_COMMON, "efficiencyCandyflower", 5, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyCandyflower.desc")).getInt();
		efficiencyGeminiorchid = config.get(LibReference.CATEGORY_COMMON, "efficiencyGeminiorchid", 2, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyGeminiorchid.desc")).getInt();
		efficiencyOminiviolet = config.get(LibReference.CATEGORY_COMMON, "efficiencyOminiviolet", 20, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyOminiviolet.desc")).getInt();
		efficiencyStonesia = config.get(LibReference.CATEGORY_COMMON, "efficiencyStonesia", 8, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyStonesia.desc")).getInt();
		efficiencyPyschobloom = config.get(LibReference.CATEGORY_COMMON, "efficiencyPyschobloom", 3, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "efficiencyPyschobloom.desc")).getInt();
		
		pyschobloomMax = config.get(LibReference.CATEGORY_COMMON, "pyschobloomMax", 9, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "pyschobloomMax.desc")).getInt();
		
		disableShieldRender = config.get(LibReference.CATEGORY_CLIENT, "disableShieldRender", false, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "disableShieldRender.desc")).getBoolean();
		disableEasterEgg = config.get(LibReference.CATEGORY_CLIENT, "disableShieldRender", false, StatCollector.translateToLocal(LibReference.PREFIX_CONFIG + "disableEasterEgg.desc")).getBoolean();
	}
}

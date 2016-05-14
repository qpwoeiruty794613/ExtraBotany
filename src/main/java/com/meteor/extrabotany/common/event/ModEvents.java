package com.meteor.extrabotany.common.event;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class ModEvents {
	public static void init() {
		MinecraftForge.EVENT_BUS.register(new EventKnowledgeTypeUnlock());
	    FMLCommonHandler.instance().bus().register(new EventKnowledgeTypeUnlock());
		MinecraftForge.EVENT_BUS.register(new EventHighDamageResistance());
	    FMLCommonHandler.instance().bus().register(new EventHighDamageResistance());
		MinecraftForge.EVENT_BUS.register(new EventShield());
	    FMLCommonHandler.instance().bus().register(new EventShield());	     
		MinecraftForge.EVENT_BUS.register(new EventBulletGold());
	    FMLCommonHandler.instance().bus().register(new EventBulletGold());	
		MinecraftForge.EVENT_BUS.register(new EventUnbreakable());
	    FMLCommonHandler.instance().bus().register(new EventUnbreakable());	
	    MinecraftForge.EVENT_BUS.register(new EventGaiaIII());
	    FMLCommonHandler.instance().bus().register(new EventGaiaIII());	
	}
}

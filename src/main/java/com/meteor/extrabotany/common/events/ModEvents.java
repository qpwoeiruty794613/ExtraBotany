package com.meteor.extrabotany.common.events;

import com.meteor.extrabotany.common.items.relic.ItemHestiaChastity;
import com.meteor.extrabotany.common.lib.LibItemName;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class ModEvents {
	public static void init() {
		MinecraftForge.EVENT_BUS.register(new EventKnowledgeTypeUnlock());
	    FMLCommonHandler.instance().bus().register(new EventKnowledgeTypeUnlock());
		MinecraftForge.EVENT_BUS.register(new EventHighDamageResistance());
	    FMLCommonHandler.instance().bus().register(new EventHighDamageResistance());
	}
}

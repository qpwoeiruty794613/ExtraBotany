package com.meteor.extrabotany.common.items;

import java.util.Arrays;

import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.core.BotaniaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMod extends Item{
	private String name;
	
	public ItemMod(String name){
		this
		.setUnlocalizedName(name)
		.setCreativeTab(CreativeTabs.tabMaterials)
		.setMaxStackSize(64)
		.setTextureName(LibReference.MOD_ID + ":" + name);
		this.name = name;
		GameRegistry.registerItem(this, name);
	}
	public String getName() {
        return name;
    }
}

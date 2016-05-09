package com.meteor.extrabotany.common.item;

import net.minecraft.item.Item;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemMods extends Item{
	private String name;
	
	public ItemMods(String name){
		this
		.setUnlocalizedName(name)
		.setCreativeTab(ExtraBotany.tabExtraBotany)
		.setMaxStackSize(64)
		.setTextureName(LibReference.MOD_ID + ":" + name);
		this.name = name;
		GameRegistry.registerItem(this, name);
	}
	public String getName() {
        return name;
    }
}

package com.meteor.extrabotany.common.items;

import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMods extends Item{
	private String name;
	
	public ItemMods(String name){
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

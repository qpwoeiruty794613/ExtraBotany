package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMods extends Block{
	private String name;
	public BlockMods(Material material, String name) {
		super(material);
		this.name = name;
		this.setBlockTextureName(LibReference.MOD_ID + ":" + name)
		.setBlockName(name);
		GameRegistry.registerBlock(this, name);
	}
	
	public String getName() {
        return name;
    }
}

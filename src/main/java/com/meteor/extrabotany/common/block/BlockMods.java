package com.meteor.extrabotany.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMods extends Block{
	private String name;
	private boolean isBeaconBase;
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
	
	public void setBeaconBase(boolean bool){
		this.isBeaconBase = bool;
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return isBeaconBase;
	}
}

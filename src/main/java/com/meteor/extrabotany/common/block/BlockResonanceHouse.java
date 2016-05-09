package com.meteor.extrabotany.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.block.tile.TileResonanceHouse;
import com.meteor.extrabotany.common.lib.LibBlockName;

public class BlockResonanceHouse extends BlockModContainer{

	public BlockResonanceHouse(Material par2Material) {
		super(par2Material);
		setHardness(3F);
		setResistance(10F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockName.RESONANCEHOUSE);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileResonanceHouse();
	}

}

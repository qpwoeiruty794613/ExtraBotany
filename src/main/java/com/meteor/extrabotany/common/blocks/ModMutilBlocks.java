package com.meteor.extrabotany.common.blocks;

import com.meteor.extrabotany.common.blocks.tile.TileRelicPlate;

import vazkii.botania.api.lexicon.multiblock.MultiblockSet;

public class ModMutilBlocks {
	
	public static MultiblockSet artifaconia;
	
	public static void init() {
		artifaconia = TileRelicPlate.makeMultiblockSet();
	}
}

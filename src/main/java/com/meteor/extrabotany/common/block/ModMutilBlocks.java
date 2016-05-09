package com.meteor.extrabotany.common.block;

import vazkii.botania.api.lexicon.multiblock.MultiblockSet;

import com.meteor.extrabotany.common.block.tile.TileRelicPlate;

public class ModMutilBlocks {
	
	public static MultiblockSet relicPlate;
	
	public static void init() {
		relicPlate = TileRelicPlate.makeMultiblockSet();
	}
}

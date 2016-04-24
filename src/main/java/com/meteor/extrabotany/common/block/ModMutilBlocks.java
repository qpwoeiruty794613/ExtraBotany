package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import com.meteor.extrabotany.common.entity.EntityGaiaIII;

import vazkii.botania.api.lexicon.multiblock.MultiblockSet;

public class ModMutilBlocks {
	
	public static MultiblockSet relicPlate;
	
	public static void init() {
		relicPlate = TileRelicPlate.makeMultiblockSet();
	}
}

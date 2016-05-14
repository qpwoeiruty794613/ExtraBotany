package com.meteor.extrabotany.common.block;

import vazkii.botania.api.lexicon.multiblock.MultiblockSet;

import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import com.meteor.extrabotany.common.entity.EntityGaiaIII;

public class ModMutilBlocks {
	
	public static MultiblockSet relicPlate;
	public static MultiblockSet gaia;
	
	public static void init() {
		relicPlate = TileRelicPlate.makeMultiblockSet();
		gaia = EntityGaiaIII.makeMultiblockSet();
	}
}

package com.meteor.extrabotany.common.blocks.subtile.generating;

import com.meteor.extrabotany.common.lexicon.LexiconModData;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.generating.SubTileDaybloom;

public class SubTileSunshinelily extends SubTileDaybloom{
	
	@Override
	public int getMaxMana() {
		return 200;
	}
	
  	@Override
	public int getValueForPassiveGeneration() {
		return 3;
	}
  	
  	@Override
  	public int getDelayBetweenPassiveGeneration() {
 		return 4;
  	}
  	
	@Override
	public boolean isPassiveFlower() {
		return false;
	}
  	
 	@Override
  	public LexiconEntry getEntry() {
  		return LexiconModData.sunshinelily;
  	}
 	
	@Override
	public int getColor() {
		return 0xE19823;
	}  	
}


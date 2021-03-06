package com.meteor.extrabotany.common.block.subtile.generating;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.generating.SubTileNightshade;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

public class SubTileMoonlightlily extends SubTileNightshade{
	
	@Override
	public int getMaxMana() {
		return 200;
	}
	
  	@Override
	public int getValueForPassiveGeneration() {
		return ConfigHandler.efficiencyMoonlightlily;
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
  		return LexiconModData.moonlightlily;
  	}
 	
	@Override
	public int getColor() {
		return 0x8D15E1;
	}
}

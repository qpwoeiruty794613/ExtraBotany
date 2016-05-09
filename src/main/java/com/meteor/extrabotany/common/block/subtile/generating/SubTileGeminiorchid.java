package com.meteor.extrabotany.common.block.subtile.generating;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.MathHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lib.LibData;

public class SubTileGeminiorchid extends SubTileGenerating{
	private static final int DELAY = 20;

	@Override
	public void onUpdate() {
		super.onUpdate();
				if(mana < getMaxMana() && ticksExisted % DELAY == 0) {
					int temA, temB;
					int bA, bB, bC, bD;
					bA = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord + 1, supertile.yCoord, supertile.zCoord + 1));
					bB = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord + 1, supertile.yCoord, supertile.zCoord - 1));
					bC = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord - 1, supertile.yCoord, supertile.zCoord + 1));
					bD = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord - 1, supertile.yCoord, supertile.zCoord - 1));
					temA = Math.min(MathHandler.min(bA, bB, bC), bD);
					temB = Math.max(MathHandler.max(bA, bB, bC), bD);
					mana +=	(int) ((temB - temA)/1000 * ConfigHandler.efficiencyGeminiorchid + supertile.getWorldObj().rand.nextInt(4));
				}
		}
	
	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 20;
	}

	@Override
	public int getMaxMana() {
		return 400;
	}

	@Override
	public int getColor() {
		return 0xEFD139;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.geminiorchid;
	}
}

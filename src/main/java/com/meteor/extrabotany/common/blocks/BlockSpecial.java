package com.meteor.extrabotany.common.blocks;

import java.util.Arrays;

import com.meteor.extrabotany.common.lib.LibBlockName;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.BlockSpecialFlower;




public class BlockSpecial extends BlockSpecialFlower {
		static {
			BotaniaAPI.subtilesForCreativeMenu.addAll(Arrays.asList(new String[] {			
					// Generating
					LibBlockName.CANDY_FLOWER,
					LibBlockName.BLUE_ENCHANTRESS,
					LibBlockName.SUNSHINE_LILY,
					LibBlockName.MOONLIGHT_LILY
			}));
		}
}

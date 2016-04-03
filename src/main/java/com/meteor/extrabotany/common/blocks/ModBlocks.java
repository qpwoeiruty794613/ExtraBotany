package com.meteor.extrabotany.common.blocks;

import com.meteor.extrabotany.common.ExtraBotany;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileBlueenchantress;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileCandyflower;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileMoonlightlily;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileSunshinelily;
import com.meteor.extrabotany.common.lib.LibBlockName;

import vazkii.botania.api.BotaniaAPI;
import net.minecraft.block.Block;

public class ModBlocks {
	
	public static Block specialFlower;
	
	public static void init() {
		specialFlower = new BlockSpecial();
		initTileEntities();
	}
	
	private static void initTileEntities() {
		BotaniaAPI.registerSubTile(LibBlockName.CANDY_FLOWER, SubTileCandyflower.class);
		BotaniaAPI.registerSubTile(LibBlockName.SUNSHINE_LILY, SubTileSunshinelily.class);
		BotaniaAPI.registerSubTile(LibBlockName.MOONLIGHT_LILY, SubTileMoonlightlily.class);
		if(ExtraBotany.arsmagicaLoaded = true){
		BotaniaAPI.registerSubTile(LibBlockName.BLUE_ENCHANTRESS, SubTileBlueenchantress.class);
		}
	}
}

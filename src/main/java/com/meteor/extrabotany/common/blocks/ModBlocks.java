package com.meteor.extrabotany.common.blocks;

import com.meteor.extrabotany.common.ExtraBotany;
import com.meteor.extrabotany.common.blocks.decor.BlockElvenQuartzSlab;
import com.meteor.extrabotany.common.blocks.decor.BlockElvenQuartzStairs;
import com.meteor.extrabotany.common.blocks.decor.BlockGaiaQuartzSlab;
import com.meteor.extrabotany.common.blocks.decor.BlockGaiaQuartzStairs;
import com.meteor.extrabotany.common.blocks.subtile.functional.SubTileNecrofluer;
import com.meteor.extrabotany.common.blocks.subtile.functional.SubTileNumeronDandelife;
import com.meteor.extrabotany.common.blocks.subtile.functional.SubTileWoodienia;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileBlueenchantress;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileCandyflower;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileMoonlightlily;
import com.meteor.extrabotany.common.blocks.subtile.generating.SubTileSunshinelily;
import com.meteor.extrabotany.common.blocks.tile.TileRelicPlate;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class ModBlocks {
	
	public static Block specialFlower;
	//Gaia Quartz
	public static Block gaiaquartz;
	public static Block gaiaquartzslabfull;
	public static Block gaiaquartzslab;
	public static Block gaiaquartzstairs;
	//Elven Quartz
	public static Block elvenquartz;
	public static Block elvenquartzslabfull;
	public static Block elvenquartzslab;
	public static Block elvenquartzstairs;
	
	public static Block relicplate;
	
	public static void init() {
		relicplate = new BlockRelicPlate();
		specialFlower = new BlockSpecial();
		//Gaia Quartz
		gaiaquartz = new BlockMods(Material.iron, LibBlockName.GAIAQUARTZ);
		gaiaquartzslab = new BlockGaiaQuartzSlab(false);
		gaiaquartzslabfull = new BlockGaiaQuartzSlab(true);
		gaiaquartzstairs = new BlockGaiaQuartzStairs();
		//Elven Quartz
		elvenquartz = new BlockMods(Material.iron, LibBlockName.ELVENQUARTZ);
		elvenquartzslab = new BlockElvenQuartzSlab(false);
		elvenquartzslabfull = new BlockElvenQuartzSlab(true);
		elvenquartzstairs = new BlockElvenQuartzStairs();
		
		
		((BlockModSlab) gaiaquartzslab).register();
		((BlockModSlab) gaiaquartzslabfull).register();
		((BlockModSlab) elvenquartzslab).register();
		((BlockModSlab) elvenquartzslabfull).register();
		
		registerTile(TileRelicPlate.class, LibBlockName.RELICPLATE);
		initTileEntities();
	}
	
	private static void initTileEntities() {
		BotaniaAPI.registerSubTile(LibBlockName.WOODIENIA, SubTileWoodienia.class);
		BotaniaAPI.registerSubTile(LibBlockName.NECRO_FLUER, SubTileNecrofluer.class);
		BotaniaAPI.registerSubTile(LibBlockName.NUMERON_DANDELIFE, SubTileNumeronDandelife.class);
		BotaniaAPI.registerSubTile(LibBlockName.CANDY_FLOWER, SubTileCandyflower.class);
		BotaniaAPI.registerSubTile(LibBlockName.SUNSHINE_LILY, SubTileSunshinelily.class);
		BotaniaAPI.registerSubTile(LibBlockName.MOONLIGHT_LILY, SubTileMoonlightlily.class);
		if(ExtraBotany.arsmagicaLoaded = true){
		BotaniaAPI.registerSubTile(LibBlockName.BLUE_ENCHANTRESS, SubTileBlueenchantress.class);
		}		
		
	}
	
	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntity(clazz, LibReference.PREFIX_MOD + key);
	}
}

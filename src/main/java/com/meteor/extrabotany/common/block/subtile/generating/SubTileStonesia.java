package com.meteor.extrabotany.common.block.subtile.generating;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

public class SubTileStonesia extends SubTileGenerating{
	
	private static final int RANGE = 3;
	private static final int DELAY = 40;
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.stonesia;
	}
	
	@Override
	public int getColor() {
		return 0x8F8F8F;
	}
	
	int burnTime = 0;
	private static final String TAG_BURN_TIME = "burnTime";
	
	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		super.writeToPacketNBT(cmp);
		cmp.setInteger(TAG_BURN_TIME, burnTime);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		super.readFromPacketNBT(cmp);
		burnTime = cmp.getInteger(TAG_BURN_TIME);
	}
		
	private static final int[][] POSITIONS = new int[][] {
		{ -1, 0, -1 },
		{ -1, 0, 0 },
		{ -1, 0, 1 },
		{ 0, 0, 1 },
		{ 1, 0, 1 },
		{ 1, 0, 0 },
		{ 1, 0, -1 },
		{ 0, 0, -1 },
	};

	int positionAt = 0;

	@Override
	public void onUpdate() {
		super.onUpdate();

		positionAt++;
		if(positionAt == POSITIONS.length)
			positionAt = 0;
		
		if(burnTime == 0){

			int[] acoords = POSITIONS[positionAt];
			ChunkCoordinates coords = new ChunkCoordinates(supertile.xCoord + acoords[0], supertile.yCoord + acoords[1], supertile.zCoord + acoords[2]);
			World world = supertile.getWorldObj();
			if(!world.isAirBlock(coords.posX, coords.posY, coords.posZ)) {
				Block block = world.getBlock(coords.posX, coords.posY, coords.posZ);
				int meta = world.getBlockMetadata(coords.posX, coords.posY, coords.posZ);
				RecipeStonesia recipe = null;
				for(RecipeStonesia recipe_ : ExtraBotanyAPI.stonesiaRecipes)
					if(recipe_.matches(world, coords.posX, coords.posY, coords.posZ, this, block, meta)) {
						recipe = recipe_;
						break;
					}
	
				if(recipe != null) {
					Botania.proxy.sparkleFX(supertile.getWorldObj(), coords.posX + Math.random(), coords.posY + Math.random(), coords.posZ + Math.random(), 1F, 1F, 1F, (float) Math.random(), 5);
						if(recipe.set(world,coords.posX, coords.posY, coords.posZ, this)) {
							burnTime +=recipe.getMana();
							for(int i = 0; i < 25; i++) {
								double x = coords.posX + Math.random();
								double y = coords.posY + Math.random() + 0.5;
								double z = coords.posZ + Math.random();
								Botania.proxy.wispFX(supertile.getWorldObj(), x, y, z, 1F, 1F, 1F, (float) Math.random() / 2F);
						}
					}
				}
			}
		}else if(burnTime > 0){
			burnTime--;
		}
	}
	
	@Override
	public int getValueForPassiveGeneration() {
		return ConfigHandler.efficiencyStonesia;
	}
	
	@Override
	public boolean canGeneratePassively() {
		return burnTime > 0;
	}

	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 1;
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
	
	@Override
	public int getMaxMana() {
		return 500;
	}
}

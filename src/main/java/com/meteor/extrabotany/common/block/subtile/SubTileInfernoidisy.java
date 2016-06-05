package com.meteor.extrabotany.common.block.subtile;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeInfernoidisy;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;

public class SubTileInfernoidisy extends SubTileEntity {

	private static final String TAG_POSITION = "position";
	private static final String TAG_TICKS_REMAINING = "ticksRemaining";

	private static final int TOTAL_TIME = 800;
	private static final int TIME_PER = TOTAL_TIME / 8;

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
	int[] ticksRemaining = new int[] { TIME_PER, TIME_PER, TIME_PER, TIME_PER, TIME_PER, TIME_PER, TIME_PER, TIME_PER };

	@Override
	public void onUpdate() {
		super.onUpdate();

		positionAt++;
		if(positionAt == POSITIONS.length)
			positionAt = 0;

		int[] acoords = POSITIONS[positionAt];
		ChunkCoordinates coords = new ChunkCoordinates(supertile.xCoord + acoords[0], supertile.yCoord + acoords[1], supertile.zCoord + acoords[2]);
		World world = supertile.getWorldObj();
		if(!world.isAirBlock(coords.posX, coords.posY, coords.posZ)) {
			Block block = world.getBlock(coords.posX, coords.posY, coords.posZ);
			int meta = world.getBlockMetadata(coords.posX, coords.posY, coords.posZ);
			RecipeInfernoidisy recipe = null;
			for(RecipeInfernoidisy recipe_ : ExtraBotanyAPI.infernoidisyRecipes)
				if(recipe_.matches(world, coords.posX, coords.posY, coords.posZ, this, block, meta)) {
					recipe = recipe_;
					break;
				}


			if(recipe != null) {
				ticksRemaining[positionAt] = ticksRemaining[positionAt] - 1;

				Botania.proxy.sparkleFX(supertile.getWorldObj(), coords.posX + Math.random(), coords.posY + Math.random(), coords.posZ + Math.random(), 2.5F, 0.5F, 0.5F, (float) Math.random(), 5);

				if(ticksRemaining[positionAt] <= 0) {
					ticksRemaining[positionAt] = TIME_PER;

					if(recipe.set(world,coords.posX, coords.posY, coords.posZ, this)) {
						for(int i = 0; i < 25; i++) {
							double x = coords.posX + Math.random();
							double y = coords.posY + Math.random() + 0.5;
							double z = coords.posZ + Math.random();

							Botania.proxy.wispFX(supertile.getWorldObj(), x, y, z, 1F, 1F, 1F, (float) Math.random() / 2F);
						}
						if(ConfigHandler.blockBreakParticles)
							supertile.getWorldObj().playAuxSFX(2001, coords.posX, coords.posY, coords.posZ, Block.getIdFromBlock(recipe.getOutput()) + (recipe.getOutputMeta() << 12));
					}
				}
			} else ticksRemaining[positionAt] = TIME_PER;
		}
	}

	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), 1);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		positionAt = cmp.getInteger(TAG_POSITION);

		if(supertile.getWorldObj() != null && !supertile.getWorldObj().isRemote)
			for(int i = 0; i < ticksRemaining.length; i++)
				ticksRemaining[i] = cmp.getInteger(TAG_TICKS_REMAINING + i);
	}

	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		cmp.setInteger(TAG_POSITION, positionAt);
		for(int i = 0; i < ticksRemaining.length; i++)
			cmp.setInteger(TAG_TICKS_REMAINING + i, ticksRemaining[i]);
	}
}

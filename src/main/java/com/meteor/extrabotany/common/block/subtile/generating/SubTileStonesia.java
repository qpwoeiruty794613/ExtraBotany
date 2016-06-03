package com.meteor.extrabotany.common.block.subtile.generating;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

public class SubTileStonesia extends SubTileGenerating{
	
	private static final int RANGE = 3;
	private static final int DELAY = 40;
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.stonesia;
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
		
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(redstoneSignal == 0) {
			if(linkedCollector != null) {
				if(burnTime == 0 && ticksExisted % DELAY == 0) {
					ChunkCoordinates chunk = toChunkCoordinates();
					World world = supertile.getWorldObj();
					for(int x = -RANGE; x < RANGE+1; x++) {
						for(int z = -RANGE; z < RANGE+1; z++) {
							int chunkx = chunk.posX+x;
							int chunkz = chunk.posZ+z;
							int chunky = chunk.posY;
							while(true) {
								if(chunky>255) break;
								Block block = world.getBlock(chunkx, chunky, chunkz);
								if(getBlockBurnTime(block) > 0)
									burnTime += getBlockBurnTime(block);
									world.func_147480_a(chunkx, chunky, chunkz, false);
									Botania.proxy.sparkleFX(world, chunkx + 0.5F, chunky, chunkz + 0.5F, 1.99F, 0.97F, 0.20F, 4F, 10);
								}
								chunky++;
							}
						}
						sync();
					}else if(burnTime > 0){
						if(supertile.getWorldObj().rand.nextInt(10) == 0)
				            supertile.getWorldObj().spawnParticle("flame", supertile.xCoord + 0.4 + Math.random() * 0.2, supertile.yCoord + 0.65, supertile.zCoord + 0.4 + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
						
						burnTime--;
					}	
				}
		}
	}
	
	public static int getBlockBurnTime(Block block) {
		if(block == Blocks.stone || block == Blocks.cobblestone) return 1;
		else return 0;
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

package com.meteor.extrabotany.common.blocks.subtile.functional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;

public class SubTileWoodienia extends SubTileFunctional{
		
		private static final int RANGE = 7;
		private static final int DELAY = 20;
		
		@Override
		public boolean acceptsRedstone() {
			return true;
		}
		
		@Override
		public void onUpdate() {
			super.onUpdate();		
			if(redstoneSignal == 0 && mana > 0 && ticksExisted % DELAY == 0) {
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
							int meta = world.getBlockMetadata(chunkx, chunky, chunkz);
							if(block instanceof BlockLog) {
								mana--;
								world.func_147480_a(chunkx, chunky, chunkz, true);
								Botania.proxy.sparkleFX(world, chunkx + 0.5F, chunky, chunkz + 0.5F, 1.99F, 0.97F, 0.20F, 4F, 10);
								return;
							}
							chunky++;
						}
					}
				}
			}
		}
		
		@Override
		public int getMaxMana() {
			return 300;
		}
		
		@Override
		public RadiusDescriptor getRadius() {
			return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
		}
		
		@Override
		public int getColor() {
			return 0x664422;
		}
}

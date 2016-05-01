package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.lexicon.LexiconModData;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.relic.ItemRelic;

public class SubTileIcebirdium extends SubTileFunctional{
	private static final int RANGE = 3;
	private static final int DELAY = 5;
		
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
		
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.icebirdium;
	}
		
	@Override
	public void onUpdate() {
		super.onUpdate();	
		if(mana > 5 && ticksExisted % DELAY == 0){
			if(redstoneSignal == 15){
				List<EntityLivingBase> livings = supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
				for(EntityLivingBase living : livings) {
					living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 5, 5));
				}
			}
		
			if(redstoneSignal == 0) {
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
								if(block == Blocks.water) {
									mana -= 3;
									world.setBlockToAir(chunkx, chunky, chunkz);
									world.setBlock(chunkx, chunky, chunkz, Blocks.ice);
								Botania.proxy.sparkleFX(world, chunkx + 0.5F, chunky, chunkz + 0.5F, 1.89F, 2.55F, 2.55F, 4F, 10);
								return;
								}
								chunky++;
							}
						}
					}
				}
			}
	}
		
	@Override
	public int getMaxMana() {
		return 400;
	}
		
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
		
	@Override
	public int getColor() {
		return 0x5AF4F6;
	}
}

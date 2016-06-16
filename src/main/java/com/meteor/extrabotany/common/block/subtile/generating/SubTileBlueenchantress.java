package com.meteor.extrabotany.common.block.subtile.generating;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.generating.SubTilePassiveGenerating;
import vazkii.botania.common.lib.LibMisc;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeBlueenchantress;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.valentin4311.candycraftmod.CandyCraft;

public class SubTileBlueenchantress extends SubTilePassiveGenerating{
	private static final String TAG_BURN_TIME = "burnTime";

	int burnTime;

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(burnTime == 0) {
			if(mana < getMaxMana() && !supertile.getWorldObj().isRemote) {			
				ChunkCoordinates chunk = toChunkCoordinates();
				World world = supertile.getWorldObj();
				for(int x = -1; x < 2; x++) {
					for(int z = -1; z < 2; z++) {
						int chunkx = chunk.posX+x;
						int chunkz = chunk.posZ+z;
						int chunky = chunk.posY;
						while(true) {
							if(chunky != supertile.yCoord) break;
							Block block = world.getBlock(chunkx, chunky, chunkz);
							int meta = world.getBlockMetadata(chunkx, chunky, chunkz);
							RecipeBlueenchantress recipe = null;
							for(RecipeBlueenchantress recipe_ : ExtraBotanyAPI.blueenchantressRecipes)
								if(recipe_.matches(world, chunkx, chunky, chunkz, this, block, meta)) {
									recipe = recipe_;
									break;
								}
				
							if(recipe != null) {
								if(recipe.getMana() > 0 && meta == 0){
									int waterAround = 0;
									for(ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS)
										if(supertile.getWorldObj().getBlock(chunkx + dir.offsetX, supertile.yCoord, chunkz + dir.offsetZ) == recipe.getInput())
											waterAround++;
	
										if(waterAround < 2)
											world.setBlockToAir(chunkx, supertile.yCoord, chunkz);
									
									if(recipe.set(world, chunkx, chunky, chunkz, this)){
										burnTime += recipe.getMana();
										supertile.getWorldObj().setBlockToAir(chunkx, chunky, chunkz);
										sync();
										playSound();
										return;
									}
								}
							}
							chunky++;
						}
					}
					
				}
			}else {
				if(supertile.getWorldObj().rand.nextInt(8) == 0){
					doBurnParticles();
					}
				burnTime--;
			}
		}
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.blueenchantress;
	}

	public void doBurnParticles() {
		Botania.proxy.wispFX(supertile.getWorldObj(), supertile.xCoord + 0.55 + Math.random() * 0.2 - 0.1, supertile.yCoord + 0.9 + Math.random() * 0.2 - 0.1, supertile.zCoord + 0.5, 0.7F, 0.05F, 0.05F, (float) Math.random() / 6, (float) -Math.random() / 60);
	}

	@Override
	public boolean isPassiveFlower() {
		return false;
	}
	
	public void playSound() {
		supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "botania:thermalily", 0.2F, 1F);
	}

	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 1;
	}

	@Override
	public int getValueForPassiveGeneration() {
		return ConfigHandler.efficiencyBlueenchantress;
	}

	@Override
	public int getMaxMana() {
		return 1000;
	}
	
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
	public boolean canGeneratePassively() {
		return burnTime > 0;
	}
	
	@Override
	public int getColor() {
		return 0x0479CC;
	}
}

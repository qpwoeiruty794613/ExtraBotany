package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.FishingHooks;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;

public class SubTileVolatilily extends SubTileFunctional{

	private static final int COST = 50;
	private static final int RANGE = 3;
	private static final int[][] OFFSETS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 } };
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		List<int[]> offsets = Arrays.asList(OFFSETS);
		Collections.shuffle(offsets);

		for(int[] offsetArray : offsets) {
			int[] positions = {
					supertile.xCoord + offsetArray[0],
					supertile.zCoord + offsetArray[1]
			};
		
		if(supertile.getWorldObj().getBlock(positions[0], supertile.yCoord - 1, positions[1]) != Blocks.water){
			return;
			}
		}
		
		if(redstoneSignal == 0 && ticksExisted % 200 == 0 && mana >= COST) {
			Random rand = supertile.getWorldObj().rand;
			int r = rand.nextInt(5);
			ItemStack stack;
			do {
				if(r == 4)
				stack = ((WeightedRandomFishable)WeightedRandom.getRandomItem(rand, EntityFishHook.field_146039_d)).func_150708_a(rand);
				else stack = FishingHooks.getRandomFishable(rand, 0.5F);
				
			} while(stack == null);
		
			int bound = RANGE * 2 + 1;
			EntityItem entity = new EntityItem(supertile.getWorldObj(), supertile.xCoord - RANGE + rand.nextInt(bound) , supertile.yCoord + 1, supertile.zCoord - RANGE + rand.nextInt(bound), stack);
			entity.motionX = 0;
			entity.motionY = 0;
			entity.motionZ = 0;
			
			if(!supertile.getWorldObj().isRemote)
				supertile.getWorldObj().spawnEntityInWorld(entity);
			mana -= COST;
			sync();
		}
	}

	@Override
	public int getColor() {
		return 0x000000;
	}

	@Override
	public int getMaxMana() {
		return COST;
	}

	@Override
	public boolean acceptsRedstone() {
		return true;
	}

	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
}

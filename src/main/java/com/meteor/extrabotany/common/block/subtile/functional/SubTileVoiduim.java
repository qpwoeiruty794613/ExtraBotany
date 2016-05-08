package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileVoiduim extends SubTileFunctional{
	
	private static final int DELAY = 200;
	private static final int RANGE = 3;
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	
		if(!supertile.getWorldObj().canBlockSeeTheSky(supertile.xCoord, supertile.yCoord, supertile.zCoord))
			return;

		if(redstoneSignal == 0 && (supertile.getWorldObj().getBlock(supertile.xCoord, supertile.yCoord - 1, supertile.zCoord) == Blocks.bedrock || supertile.getWorldObj().getBlock(supertile.xCoord, supertile.yCoord - 2, supertile.zCoord) == Blocks.bedrock)){
			if(ticksExisted % DELAY == 0){
				ItemStack stack = getOreToPut();
				Random rand = supertile.getWorldObj().rand;
				int bound = RANGE + 1;
				EntityItem entity = new EntityItem(supertile.getWorldObj(), supertile.xCoord - RANGE + rand.nextInt(bound) , supertile.yCoord + 1, supertile.zCoord - RANGE + rand.nextInt(bound), stack);
				entity.motionX = 0;
				entity.motionY = 0;
				entity.motionZ = 0;
				
				if(!supertile.getWorldObj().isRemote)
					supertile.getWorldObj().spawnEntityInWorld(entity);
				mana -= getMaxMana();
				sync();
			}
				
		}
		
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public int getMaxMana() {
		return 1200;
	}
	
	@Override
	public int getColor() {
		return 0x461A47;
	}
	
	public ItemStack getOreToPut() {
		Collection<WeightedRandom.Item> values = new ArrayList();
		Map<String, Integer> map = getOreMap();
		for(String s : map.keySet())
			values.add(new StringRandomItem(map.get(s), s));

		String ore = ((StringRandomItem) WeightedRandom.getRandomItem(supertile.getWorldObj().rand, values)).s;

		List<ItemStack> ores = OreDictionary.getOres(ore);
		
		for(ItemStack stack : ores) {
			Item item = stack.getItem();
			return stack;
		}

		return getOreToPut();
	}
	
	public Map<String, Integer> getOreMap() {
		return BotaniaAPI.oreWeights;
	}
	
	static {
		
		
		
	}
	
	public static void addOreWeight(String ore, int weight) {
		BotaniaAPI.oreWeights.put(ore, weight);
	}
	
	public static int getOreWeight(String ore) {
		return BotaniaAPI.oreWeights.get(ore);
	}
	
	private static class StringRandomItem extends WeightedRandom.Item {

		public String s;

		public StringRandomItem(int par1, String s) {
			super(par1);
			this.s = s;
		}

	}
}

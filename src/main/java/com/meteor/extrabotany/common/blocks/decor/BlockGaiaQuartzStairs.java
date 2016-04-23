package com.meteor.extrabotany.common.blocks.decor;

import com.meteor.extrabotany.common.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.decor.stairs.BlockModStairs;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockGaiaQuartzStairs extends BlockModStairs {

	public BlockGaiaQuartzStairs() {
		super(ModBlocks.gaiaquartz, 0, "quartzStairs" + "Gaia");
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}
}

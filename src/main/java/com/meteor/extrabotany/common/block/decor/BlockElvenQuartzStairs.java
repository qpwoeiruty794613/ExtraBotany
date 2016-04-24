package com.meteor.extrabotany.common.block.decor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.decor.stairs.BlockModStairs;
import vazkii.botania.common.lexicon.LexiconData;

import com.meteor.extrabotany.common.block.ModBlocks;

public class BlockElvenQuartzStairs extends BlockModStairs {

	public BlockElvenQuartzStairs() {
		super(ModBlocks.elvenquartz, 0, "quartzStairs" + "Elven");
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}
}

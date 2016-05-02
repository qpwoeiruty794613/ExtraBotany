package com.meteor.extrabotany.common.block.subtile.generating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.subtile.generating.SubTilePassiveGenerating;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lib.LibMisc;
import am2.AMCore;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lib.LibData;

public class SubTileGeminiorchid extends SubTileGenerating{
	private static final int DELAY = 20;

	@Override
	public void onUpdate() {
		super.onUpdate();
				if(mana < getMaxMana() && ticksExisted % DELAY == 0) {
					int temA, temB;
					int bA, bB, bC, bD;
					bA = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord + 1, supertile.yCoord, supertile.zCoord + 1));
					bB = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord + 1, supertile.yCoord, supertile.zCoord - 1));
					bC = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord - 1, supertile.yCoord, supertile.zCoord + 1));
					bD = LibData.getBlockTemperture(supertile.getWorldObj().getBlock(supertile.xCoord - 1, supertile.yCoord, supertile.zCoord - 1));
					temA = Math.min(Math.min(bA, bB), Math.min(bC, bD));
					temB = Math.max(Math.max(bA, bB), Math.max(bC, bD));
					mana +=	(int) ((temB - temA)/1000 * ConfigHandler.efficiencyGeminiorchid + supertile.getWorldObj().rand.nextInt(4));
				}
		}
	
	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 20;
	}

	@Override
	public int getMaxMana() {
		return 400;
	}

	@Override
	public int getColor() {
		return 0xEFD139;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.geminiorchid;
	}
}

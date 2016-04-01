package com.meteor.extrabotany.common.blocks.subtile.generating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import am2.AMCore;
import am2.blocks.liquid.BlockLiquidEssence;

import com.meteor.extrabotany.common.lexicon.LexiconModData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;
import vazkii.botania.common.block.subtile.generating.SubTilePassiveGenerating;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

public class SubTileBlueenchantress extends SubTileHydroangeas{

	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.blueenchantress;
	}

	@Override
	public void doBurnParticles() {
		Botania.proxy.wispFX(supertile.getWorldObj(), supertile.xCoord + 0.55 + Math.random() * 0.2 - 0.1, supertile.yCoord + 0.9 + Math.random() * 0.2 - 0.1, supertile.zCoord + 0.5, 0.7F, 0.05F, 0.05F, (float) Math.random() / 6, (float) -Math.random() / 60);
	}

	@Override
	public boolean isPassiveFlower() {
		return false;
	}

	@Override
	public Material getMaterialToSearchFor() {
		return AMCore.proxy.blocks.liquidEssence.getMaterial();
	}

	@Override
	public void playSound() {
		supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "botania:thermalily", 0.2F, 1F);
	}

	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 1;
	}

	@Override
	public int getBurnTime() {
		return 400;
	}

	@Override
	public int getValueForPassiveGeneration() {
		return 100;
	}

	@Override
	public int getMaxMana() {
		return 1000;
	}


}

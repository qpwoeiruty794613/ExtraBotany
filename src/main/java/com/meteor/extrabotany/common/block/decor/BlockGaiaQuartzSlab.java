package com.meteor.extrabotany.common.block.decor;

import java.util.Random;

import com.meteor.extrabotany.common.block.ModBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockGaiaQuartzSlab extends BlockLivingSlab{
	public BlockGaiaQuartzSlab(boolean par2) {
		super(par2, ModBlocks.gaiaquartz, 0);
		setHardness(0.8F);
		setResistance(10F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public BlockSlab getFullBlock() {
		return (BlockSlab) ModBlocks.gaiaquartzslabfull;
	}

	@Override
	public BlockSlab getSingleBlock() {
		return (BlockSlab) ModBlocks.gaiaquartzslab;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.decorativeBlocks;
	}

}

package com.meteor.extrabotany.common.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.common.block.decor.BlockTinyPotato;

import com.meteor.extrabotany.client.lib.LibRenderID;
import com.meteor.extrabotany.common.block.tile.TileManaTinyPotato;
import com.meteor.extrabotany.common.item.ItemBlockManaTinyPotato;
import com.meteor.extrabotany.common.lib.LibBlockName;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockManaTinyPotato extends BlockModContainer{
	
	public BlockManaTinyPotato(){
		super(Material.cloth);
		setHardness(0.25F);
		setBlockName(LibBlockName.MANATINYPOTATO);
		float f = 1F / 16F * 6F;
		setBlockBounds(f, 0, f, 1F - f, f, 1F - f);
	}
	
	@Override
	public Block setBlockName(String par1Str) {
		GameRegistry.registerBlock(this, ItemBlockManaTinyPotato.class, par1Str);
		return super.setBlockName(par1Str);
	}
	
	@Override
	public int getRenderType() {
		return LibRenderID.idManaTinyPotato;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileManaTinyPotato();
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if(tile instanceof TileManaTinyPotato) {
			((TileManaTinyPotato) tile).interact();
			par1World.spawnParticle("heart", par2 + minX + Math.random() * (maxX - minX), par3 + maxY, par4 + minZ + Math.random() * (maxZ - minZ), 0, 0 ,0);
		}
		return true;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList();
		TileEntity tile = world.getTileEntity(x, y, z);

		if(tile != null) {
			ItemStack stack = new ItemStack(this);
			String name = ((TileManaTinyPotato) tile).name;
			if(!name.isEmpty())
				stack.setStackDisplayName(name);
			list.add(stack);
		}

		return list;
	}
	
	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.hardened_clay.getIcon(0, 0);
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int l1 = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		par1World.setBlockMetadataWithNotify(par2, par3, par4, l1, 2);
		if (par6ItemStack.hasDisplayName())
			((TileManaTinyPotato) par1World.getTileEntity(par2, par3, par4)).name = par6ItemStack.getDisplayName();
	}

	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		if(!par6EntityPlayer.capabilities.isCreativeMode)
			dropBlockAsItem(par1World, par2, par3, par4, par5, 0);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

}

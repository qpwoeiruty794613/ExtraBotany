package com.meteor.extrabotany.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import com.meteor.extrabotany.common.lib.LibBlockName;

public class BlockRelicPlate extends BlockModContainer implements ILexiconable {

	public static IIcon overlay;
	IIcon[] icons;

	public BlockRelicPlate() {
		super(Material.iron);
		setBlockBounds(0F, 0F, 0F, 1F, 3F / 16F, 1F);
		setHardness(3F);
		setResistance(10F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockName.RELICPLATE);

		BotaniaAPI.blacklistBlockFromMagnet(this, Short.MAX_VALUE);
	}

	@Override
	public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
		ItemStack stack = player.getCurrentEquippedItem();
		if(stack != null && stack.getItem() == ModItems.manaResource && stack.getItemDamage() < 3) {
			if(player == null || !player.capabilities.isCreativeMode) {
				stack.stackSize--;
				if(stack.stackSize == 0 && player != null)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}

			ItemStack target = stack.copy();
			target.stackSize = 1;
			EntityItem item = new EntityItem(worldObj, x + 0.5, y + 0.5, z + 0.5, target);
			item.delayBeforeCanPickup = 40;
			item.motionX = item.motionY = item.motionZ = 0;
			if(!worldObj.isRemote)
				worldObj.spawnEntityInWorld(item);

			return true;
		}

		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[3];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
		overlay = IconHelper.forBlock(par1IconRegister, this, "overlay");
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return icons[Math.min(2, par1)];
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.terrasteel;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TileRelicPlate plate = (TileRelicPlate) par1World.getTileEntity(par2, par3, par4);
		int val = (int) ((double) plate.getCurrentMana() / (double) TileRelicPlate.MAX_MANA * 15.0);
		if(plate.getCurrentMana() > 0)
			val = Math.max(val, 1);

		return val;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileRelicPlate();
	}

}

package com.meteor.extrabotany.common.item;

import java.awt.Color;
import java.util.List;

import com.meteor.extrabotany.common.lib.LibItemName;

import vazkii.botania.api.mana.ICreativeManaProvider;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemGaiaTablet extends ItemMod implements IManaItem, ICreativeManaProvider, IManaTooltipDisplay {
	
	//copied from ItemManaTablet
	IIcon[] icons;
	
	//just change here
	private static final int MAX_MANA = 2000000;

	private static final String TAG_MANA = "mana";
	private static final String TAG_CREATIVE = "creative";
	private static final String TAG_ONE_USE = "oneUse";
	
	public ItemGaiaTablet() {
		super();
		setMaxStackSize(1);
		setMaxDamage(1000);
		setUnlocalizedName(LibItemName.GAIATABLET);
		setNoRepair();
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par2CreativeTabs = CreativeTabs.tabMaterials;
		
		// Empty tablet
		par3List.add(new ItemStack(par1, 1));

		// Full tablet
		ItemStack fullPower = new ItemStack(par1, 1);
		setMana(fullPower, MAX_MANA);
		par3List.add(fullPower);

		// Creative Tablet
		ItemStack creative = new ItemStack(par1, 1);
		setMana(creative, MAX_MANA);
		setStackCreative(creative);
		par3List.add(creative);
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		float mana = getMana(par1ItemStack);
		return par2 == 1 ? Color.HSBtoRGB(0.499F,  mana / MAX_MANA, 1F) : 0xFFFFFF;
	}

	@Override
	public int getDamage(ItemStack stack) {
		if(super.getDamage(stack) != 0)
			super.setDamage(stack, 0);

		return 0;
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return icons[Math.min(1, pass)];
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(isStackCreative(par1ItemStack))
			par3List.add(StatCollector.translateToLocal("botaniamisc.creative"));
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}

	public static void setMana(ItemStack stack, int mana) {
		ItemNBTHelper.setInt(stack, TAG_MANA, mana);
	}

	public static void setStackCreative(ItemStack stack) {
		ItemNBTHelper.setBoolean(stack, TAG_CREATIVE, true);
	}

	public static boolean isStackCreative(ItemStack stack) {
		return ItemNBTHelper.getBoolean(stack, TAG_CREATIVE, false);
	}

	@Override
	public int getMana(ItemStack stack) {
		return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
	}

	@Override
	public int getMaxMana(ItemStack stack) {
		return isStackCreative(stack) ? MAX_MANA + 1000 : MAX_MANA;
	}

	@Override
	public void addMana(ItemStack stack, int mana) {
		if(!isStackCreative(stack))
			setMana(stack, Math.min(getMana(stack) + mana, MAX_MANA));
	}

	@Override
	public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
		return !ItemNBTHelper.getBoolean(stack, TAG_ONE_USE, false);
	}

	@Override
	public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
		return !isCreative(stack);
	}

	@Override
	public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
		return true;
	}

	@Override
	public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
		return true;
	}

	@Override
	public boolean isNoExport(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isCreative(ItemStack stack) {
		return isStackCreative(stack);
	}

	@Override
	public float getManaFractionForDisplay(ItemStack stack) {
		return (float) getMana(stack) / (float) getMaxMana(stack);
	}
	

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return !isStackCreative(stack);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0 - getManaFractionForDisplay(stack);
	}
}

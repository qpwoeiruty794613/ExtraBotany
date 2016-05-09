package com.meteor.extrabotany.common.item.basic;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.client.core.helper.IconHelper;

import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBullet extends ItemMods{
	final int types = 6;
	IIcon[] icons;
	public ItemBullet(String name) {
		super(name);
		setHasSubtypes(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < types; i++)
				par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[types];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forName(par1IconRegister, LibItemName.BULLET_NAMES[i]);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "item." + LibItemName.BULLET_NAMES[Math.min(types - 1, par1ItemStack.getItemDamage())];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}
}

package com.meteor.extrabotany.common.item.basic;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;

import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMaterial extends ItemMods{
	final int types = 16;
	IIcon[] icons;
	public ItemMaterial(String name) {
		super(name);
		setHasSubtypes(true);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(stack.getItemDamage() == 13)
				player.setHealth(Math.min(player.getHealth() + 1F * stack.stackSize, player.getMaxHealth()));
				player.inventory.clearInventory(ModItems.material, 13);
			if(stack.getItemDamage() == 14)
				PropertyHandler.addShieldAmount(1F * stack.stackSize, player);
				player.inventory.clearInventory(ModItems.material, 14);
			if(stack.getItemDamage() == 15)
				ManaItemHandler.dispatchMana(stack, player, 100 * stack.stackSize, true);
				player.inventory.clearInventory(ModItems.material, 15);
		}
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
			icons[i] = IconHelper.forName(par1IconRegister, LibItemName.MATERIAL_NAMES[i]);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "item." + LibItemName.MATERIAL_NAMES[Math.min(types - 1, par1ItemStack.getItemDamage())];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}

}

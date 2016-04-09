package com.meteor.extrabotany.common;

import java.util.List;

import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibReference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtraBotanyCreativeTab extends CreativeTabs{
	List list;
	
	public ExtraBotanyCreativeTab() {
		super(LibReference.MOD_ID);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.excaliber);
	}

	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}
	

	@Override
	public void displayAllReleventItems(List list) {
		this.list = list;
		//Mana Using Item
		addItem(ModItems.gaiatablet);
		//Relics
		addItem(ModItems.dice20);
		addItem(ModItems.excaliber);
		addItem(ModItems.excaliberfake);
		addItem(ModItems.hestiachastity);
		addItem(ModItems.maxwelldemon);
		addItem(ModItems.vhandgun);
		addItem(ModItems.vpowerbattleaxe);
		addItem(ModItems.vrangerboots);
		//Test
		addItem(ModItems.itemtest);
		//Basic
		addItem(ModItems.astralforce);
		addItem(ModItems.blankcard);
		addItem(ModItems.empty_dice);
		addItem(ModItems.gaiaessence);
		addItem(ModItems.prismaticshard);
		addItem(ModItems.petal_green);
		addItem(ModItems.petal_purple);
		addItem(ModItems.petal_red);
		//Spawn Card
		addItem(ModItems.lycorisgreen);
		addItem(ModItems.lycorispurple);
		addItem(ModItems.lycorisred);
		addItem(ModItems.lycorisrandom);

	}
	
	private void addItem(Item item) {
		item.getSubItems(item, this, list);
	}

	private void addBlock(Block block) {
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, list);
	}

	private void addStack(ItemStack stack) {
		list.add(stack);
	}
}

package com.meteor.extrabotany.common;

import java.util.List;

import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.meteor.extrabotany.common.blocks.ModBlocks;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibReference;

public class ExtraBotanyCreativeTab extends CreativeTabs{
	List list;
	
	public ExtraBotanyCreativeTab() {
		super(LibReference.MOD_ID);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return ItemBlockSpecialFlower.ofType(LibBlockName.NUMERON_DANDELIFE);
	}

	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}
	
	@Override
	public void displayAllReleventItems(List list) {
		this.list = list;
		//Flowers
		addBlock(ModBlocks.specialFlower);
		//Mana Using Item
		addItem(ModItems.gaiatablet);
		addItem(ModItems.teleportpearl);
		//Relics
		addItem(ModItems.dice20);
		addItem(ModItems.hestiachastity);
		addItem(ModItems.hermestravelclothing);
		addItem(ModItems.aphroditegrace);
		addItem(ModItems.vrangerboots);
		addItem(ModItems.maxwelldemon);
		addItem(ModItems.excaliber);
		addItem(ModItems.excaliberfake);
		addItem(ModItems.vhandgun);
		addItem(ModItems.vpowerbattleaxe);
		addItem(ModItems.athenabless);
		addItem(ModItems.cronusphantom);
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
		//Blocks
		addItem(ModItems.gaiaquartz);
		addBlock(ModBlocks.gaiaquartz);
		addBlock(ModBlocks.gaiaquartzstairs);
		addBlock(ModBlocks.gaiaquartzslab);
		addItem(ModItems.elvenquartz);
		addBlock(ModBlocks.elvenquartz);
		addBlock(ModBlocks.elvenquartzstairs);
		addBlock(ModBlocks.elvenquartzslab);
		//Baubles
		addItem(ModItems.dog);
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

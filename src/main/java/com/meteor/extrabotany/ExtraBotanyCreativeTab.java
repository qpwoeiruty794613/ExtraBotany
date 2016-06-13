package com.meteor.extrabotany;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.enchantment.ModEnchantment;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibReference;

public class ExtraBotanyCreativeTab extends CreativeTabs{
	List list;
	
	public ExtraBotanyCreativeTab() {
		super(LibReference.MOD_ID);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.pylon));
	}

	@Override
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}
	
	ItemStack ship = new ItemStack(ModItems.theseusship);
	
	@Override
	public void displayAllReleventItems(List list) {
		this.list = list;
		//Flowers
		addBlock(ModBlocks.specialFlower);
		//Mana Using Item
		addItem(ModItems.manapotato);
		addItem(ModItems.gaiatablet);
		addItem(ModItems.teleportpearl);
		addBlock(ModBlocks.pylon);
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
		addItem(ModItems.lokighostrick);
		addItem(ModItems.olympusguard);
		addItem(ModItems.cthulhueye);
		addItem(ModItems.hermeswand);
		addItem(ModItems.theseusship);
		addItem(ModItems.eternalslience);
		//Test
		addItem(ModItems.itemtest);
		//Basic
		addItem(ModItems.material);
		addItem(ModItems.boxs);
		addItem(ModItems.dungeonbox);
		addItem(ModItems.pill);
		//Spawn Card
		addItem(ModItems.lycorisgreen);
		addItem(ModItems.lycorispurple);
		addItem(ModItems.lycorisred);
		addItem(ModItems.lycorisrandom);		
		//Blocks
		addBlock(ModBlocks.gaiaquartz);
		addBlock(ModBlocks.gaiaquartzstairs);
		addBlock(ModBlocks.gaiaquartzslab);
		addBlock(ModBlocks.elvenquartz);
		addBlock(ModBlocks.elvenquartzstairs);
		addBlock(ModBlocks.elvenquartzslab);
		//Baubles
		addItem(ModItems.dog);
		addItem(ModItems.gaiawise);
		//Bullets
		addItem(ModItems.bullet);
		//Weapons
		addItem(ModItems.gunphoenixblaster);
		addItem(ModItems.guntacticalshotgun);
		addItem(ModItems.snowballlauncher);
		addItem(ModItems.gunboomstick);
		addItem(ModItems.gunflintlock);
		addItem(ModItems.gunpistol);
		addItem(ModItems.gunshotgun);
		addItem(ModItems.scissorpurple);
		addItem(ModItems.scissorred);
		//Others
		addItem(ModItems.recordA);
		addItem(ModItems.recordB);
		addItem(ModItems.recordC);
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

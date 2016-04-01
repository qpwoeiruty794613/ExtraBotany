package com.meteor.extrabotany.common.items;

import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibOreDictName;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	public static Item prismaticshard;
	public static Item blankcard;
	public static Item gaiaessence;
	public static Item astralforce;
	
	public static void init() {
		prismaticshard = new ItemMod(LibItemName.PRISMATICSHARD);
		blankcard = new ItemMod(LibItemName.BLANKCARD);
		gaiaessence = new ItemMod(LibItemName.GAIAESSENCE);
		astralforce = new ItemMod(LibItemName.ASTRALFORCE);
		
		OreDictionary.registerOre(LibOreDictName.GAIA_ESSENCE, new ItemStack(gaiaessence));
		OreDictionary.registerOre(LibOreDictName.PRISMATIC_SHARD, new ItemStack(prismaticshard));
		OreDictionary.registerOre(LibOreDictName.BLANK_CARD, new ItemStack(blankcard));
		OreDictionary.registerOre(LibOreDictName.ASTRAL_FORCE, new ItemStack(astralforce));
	}
}

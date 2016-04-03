package com.meteor.extrabotany.common.items;

import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
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
	public static Item gaiatablet;
	public static Item petal_red;
	public static Item petal_green;
	public static Item petal_purple;
	
	public static void init() {
		prismaticshard = new ItemMods(LibItemName.PRISMATICSHARD);
		blankcard = new ItemMods(LibItemName.BLANKCARD);
		gaiaessence = new ItemMods(LibItemName.GAIAESSENCE);
		astralforce = new ItemMods(LibItemName.ASTRALFORCE);
		
		gaiatablet = new ItemGaiaTablet();
		petal_red = new ItemMods(LibItemName.PETALRED);
		petal_green = new ItemMods(LibItemName.PETALGREEN);
		petal_purple = new ItemMods(LibItemName.PETALPURPLE);
		

		
		OreDictionary.registerOre(LibOreDictName.GAIA_ESSENCE, new ItemStack(gaiaessence));
		OreDictionary.registerOre(LibOreDictName.PRISMATIC_SHARD, new ItemStack(prismaticshard));
		OreDictionary.registerOre(LibOreDictName.BLANK_CARD, new ItemStack(blankcard));
		OreDictionary.registerOre(LibOreDictName.ASTRAL_FORCE, new ItemStack(astralforce));
	}
	

}

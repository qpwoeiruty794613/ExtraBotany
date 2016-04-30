package com.meteor.extrabotany.common.recipe;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibOreDictName;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibOreDict;

public class ModPetalRecipe {
	
	public static final String white = LibOreDict.PETAL[0], orange = LibOreDict.PETAL[1], magenta = LibOreDict.PETAL[2], lightBlue = LibOreDict.PETAL[3], yellow = LibOreDict.PETAL[4], lime = LibOreDict.PETAL[5], pink = LibOreDict.PETAL[6], gray = LibOreDict.PETAL[7], lightGray = LibOreDict.PETAL[8], cyan = LibOreDict.PETAL[9], purple = LibOreDict.PETAL[10], blue = LibOreDict.PETAL[11], brown = LibOreDict.PETAL[12], green = LibOreDict.PETAL[13], red = LibOreDict.PETAL[14], black = LibOreDict.PETAL[15];
	public static final String runeWater = LibOreDict.RUNE[0], runeFire = LibOreDict.RUNE[1], runeEarth = LibOreDict.RUNE[2], runeAir = LibOreDict.RUNE[3], runeSpring = LibOreDict.RUNE[4], runeSummer = LibOreDict.RUNE[5], runeAutumn = LibOreDict.RUNE[6], runeWinter = LibOreDict.RUNE[7], runeMana = LibOreDict.RUNE[8], runeLust = LibOreDict.RUNE[9], runeGluttony = LibOreDict.RUNE[10], runeGreed = LibOreDict.RUNE[11], runeSloth = LibOreDict.RUNE[12], runeWrath = LibOreDict.RUNE[13], runeEnvy = LibOreDict.RUNE[14], runePride = LibOreDict.RUNE[15];
	public static final String redstoneRoot = LibOreDict.REDSTONE_ROOT;
	public static final String pixieDust = LibOreDict.PIXIE_DUST;
	public static final String gaiaSpirit = LibOreDict.LIFE_ESSENCE;
	public static final String manaPowder = LibOreDict.MANA_POWDER;
	public static final String gaiaEssence = LibOreDictName.GAIA_ESSENCE;
	public static final String blankCard = LibOreDictName.BLANK_CARD;
	public static final String quartzGaia = LibOreDictName.QUARTZ_GAIA;
	public static final String quartzElementium = LibOreDictName.QUARTZ_ELEMENTIUM;
	public static final String petalRed = LibOreDictName.LYCORIS_RED;
	public static final String petalGreen = LibOreDictName.LYCORIS_GREEN;
	public static final String petalPurple = LibOreDictName.LYCORIS_PURPLE;
	public static final String shard = LibOreDictName.PRISMATIC_SHARD;
	public static final String dog = LibOreDictName.DOG;
	
	public static RecipePetals blueenchantressRecipe;
	public static RecipePetals candyflowerRecipe;
	public static RecipePetals moonlightlilyRecipe;
	public static RecipePetals sunshinelilyRecipe;
	public static RecipePetals geminiorchidRecipe;
	public static RecipePetals ominivioletRecipe;
	
	public static RecipePetals necrofleurRecipe;
	public static RecipePetals numerondandelifeRecipe;
	public static RecipePetals woodieniaRecipe;
	public static RecipePetals numeronbalsamRecipe;
	public static RecipePetals icebirdiumRecipe;
	public static RecipePetals volatililyRecipe;
	public static RecipePetals judasvowRecipe;
	
	public static void init() {
		blueenchantressRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.BLUE_ENCHANTRESS), blue, blue, blue, blue, cyan, gaiaEssence, manaPowder, runeWater);
		candyflowerRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.CANDY_FLOWER), red, orange, yellow, blue, green, purple, manaPowder, pixieDust);
		sunshinelilyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.SUNSHINE_LILY), orange, orange, orange, yellow, blankCard, manaPowder, runeGreed, pixieDust);
		moonlightlilyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.MOONLIGHT_LILY), purple, purple, purple, gray, blankCard, manaPowder, runeGreed, pixieDust);
		geminiorchidRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.GEMINIORCHID), orange, orange, yellow, yellow, manaPowder, runeMana);
		ominivioletRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.OMNIVIOLET), red, orange, yellow, blue, green, purple, runeEarth);
		
		necrofleurRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.NECRO_FLUER), purple, purple, magenta, magenta, blankCard, quartzElementium, manaPowder, redstoneRoot);
		numerondandelifeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.NUMERON_DANDELIFE), petalGreen, petalGreen, petalRed, blankCard, shard, runeMana, redstoneRoot);
		woodieniaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.WOODIENIA), brown, brown, brown, gray, blankCard, quartzElementium, manaPowder, redstoneRoot);
		numeronbalsamRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.NUMERON_BALSAM), red, red, red, magenta, pink, blankCard, runeFire);
		icebirdiumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.ICEBIRDIUM), lightBlue, lightBlue, lightBlue, cyan, blue, runeWater, runeWinter);
		volatililyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.VOLATILILY), white, white, black, black, redstoneRoot, dog, runeWater);
		judasvowRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockName.JUDASVOW), red, red, orange, yellow, yellow, blankCard, runeAutumn, runeMana, shard);
	}
}

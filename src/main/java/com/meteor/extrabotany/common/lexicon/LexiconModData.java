package com.meteor.extrabotany.common.lexicon;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibLexiconName;
import com.meteor.extrabotany.common.recipe.ModPetalRecipe;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.crafting.ModPetalRecipes;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.ALexiconEntry;
import vazkii.botania.common.lexicon.BLexiconEntry;
import vazkii.botania.common.lexicon.DLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class LexiconModData {
	//Generating 
	public static LexiconEntry candyflower;
	public static LexiconEntry blueenchantress;
	public static LexiconEntry sunshinelily;
	public static LexiconEntry moonlightlily;
	//Functional
	public static LexiconEntry necrofleur;
	public static LexiconEntry numerondandelife;
	public static LexiconEntry woodienia;
	//For fun
	public static LexiconEntry infinitymana;
	//Relics
	public static LexiconEntry hestiachastity;
	public static LexiconEntry maxwelldemon;
	public static LexiconEntry athenabless;
	public static LexiconEntry aphroditegrace;
	public static LexiconEntry dice20;
	public static LexiconEntry excaliber;
	public static LexiconEntry excaliberfake;
	public static LexiconEntry vpowerbattleaxe;
	public static LexiconEntry vhandgun;
	public static LexiconEntry vrangerboots;
	
	public static void init() {
		//Generating
		candyflower = new ELexiconEntry(LibLexiconName.GFLOWER_CANDYFLOWER, BotaniaAPI.categoryGenerationFlowers);
		candyflower.setLexiconPages(new PageText("0"), new PageText("1"),
				new PagePetalRecipe("2", ModPetalRecipe.candyflowerRecipe));
		candyflower.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.CANDY_FLOWER));
		
		blueenchantress = new ELexiconEntry(LibLexiconName.GFLOWER_BLUEENCHANTRESS, BotaniaAPI.categoryGenerationFlowers);
		blueenchantress.setLexiconPages(new PageText("0"),
				new PagePetalRecipe("1", ModPetalRecipe.blueenchantressRecipe));
		blueenchantress.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.BLUE_ENCHANTRESS));
		
		sunshinelily = new ELexiconEntry(LibLexiconName.GFLOWER_SUNSHINELILY, BotaniaAPI.categoryGenerationFlowers);
		sunshinelily.setLexiconPages(new PageText("0"), new PageText("1"),
				new PagePetalRecipe("2", ModPetalRecipe.sunshinelilyRecipe));
		sunshinelily.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.SUNSHINE_LILY));
		
		moonlightlily = new ELexiconEntry(LibLexiconName.GFLOWER_MOONLIGHTLILY, BotaniaAPI.categoryGenerationFlowers);
		moonlightlily.setLexiconPages(new PageText("0"), new PageText("1"),
				new PagePetalRecipe("2", ModPetalRecipe.moonlightlilyRecipe));
		moonlightlily.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.MOONLIGHT_LILY));
		//Functional
		necrofleur = new ELexiconEntry(LibLexiconName.FFLOWER_NECROFLEUR, BotaniaAPI.categoryFunctionalFlowers);
		necrofleur.setLexiconPages(new PageText("0"),
				new PagePetalRecipe("1", ModPetalRecipe.necrofleurRecipe));
		necrofleur.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.NECRO_FLUER));
		
		numerondandelife = new ELexiconEntry(LibLexiconName.FFLOWER_NUMERONDANDELIFE, BotaniaAPI.categoryFunctionalFlowers);
		numerondandelife.setLexiconPages(new PageText("0"),
				new PagePetalRecipe("1", ModPetalRecipe.numerondandelifeRecipe));
		numerondandelife.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.NUMERON_DANDELIFE));
		
		woodienia = new ELexiconEntry(LibLexiconName.FFLOWER_WOODIENIA, BotaniaAPI.categoryFunctionalFlowers);
		woodienia.setLexiconPages(new PageText("0"),
				new PagePetalRecipe("1", ModPetalRecipe.woodieniaRecipe));
		woodienia.setIcon(ItemBlockSpecialFlower.ofType(LibBlockName.WOODIENIA));
		//For Fun
		infinitymana = new DLexiconEntry(LibLexiconName.D_INFINITYMANA, BotaniaAPI.categoryBasics);
		infinitymana.setIcon(new ItemStack(vazkii.botania.common.block.ModBlocks.pool));
		//Relics
		hestiachastity = new ELexiconEntry(LibLexiconName.R_HESTIACHASTITY, BotaniaAPI.categoryAlfhomancy);
		hestiachastity.setIcon(new ItemStack(ModItems.hestiachastity));
		hestiachastity.setLexiconPages(new PageText("0"));
		
		maxwelldemon = new ELexiconEntry(LibLexiconName.R_MAXWELLDEMON, BotaniaAPI.categoryAlfhomancy);
		maxwelldemon.setIcon(new ItemStack(ModItems.maxwelldemon));
		maxwelldemon.setLexiconPages(new PageText("0"));
		
		athenabless = new ELexiconEntry(LibLexiconName.R_ATHENABLESS, BotaniaAPI.categoryAlfhomancy);
		athenabless.setIcon(new ItemStack(ModItems.athenabless));
		athenabless.setLexiconPages(new PageText("0"));
		
		aphroditegrace = new ELexiconEntry(LibLexiconName.R_APHRODITEGRACE, BotaniaAPI.categoryAlfhomancy);
		aphroditegrace.setIcon(new ItemStack(ModItems.aphroditegrace));
		aphroditegrace.setLexiconPages(new PageText("0"));
		
		dice20 = new ELexiconEntry(LibLexiconName.R_DICE20, BotaniaAPI.categoryAlfhomancy);
		dice20.setIcon(new ItemStack(ModItems.dice20));
		dice20.setLexiconPages(new PageText("0"));
		
		excaliber = new ELexiconEntry(LibLexiconName.R_EXCALIBER, BotaniaAPI.categoryAlfhomancy);
		excaliber.setIcon(new ItemStack(ModItems.excaliber));
		excaliber.setLexiconPages(new PageText("0"));
		
		excaliberfake = new ELexiconEntry(LibLexiconName.R_EXCALIBERFAKE, BotaniaAPI.categoryAlfhomancy);
		excaliberfake.setIcon(new ItemStack(ModItems.excaliberfake));
		excaliberfake.setLexiconPages(new PageText("0"));
		
		vpowerbattleaxe = new ELexiconEntry(LibLexiconName.R_VPOWERBATTLEAXE, BotaniaAPI.categoryAlfhomancy);
		vpowerbattleaxe.setIcon(new ItemStack(ModItems.vpowerbattleaxe));
		vpowerbattleaxe.setLexiconPages(new PageText("0"));
		
		vhandgun = new ELexiconEntry(LibLexiconName.R_VHANDGUN, BotaniaAPI.categoryAlfhomancy);
		vhandgun.setIcon(new ItemStack(ModItems.vhandgun));
		vhandgun.setLexiconPages(new PageText("0"));
		
		vrangerboots = new ELexiconEntry(LibLexiconName.R_VRANGERBOOTS, BotaniaAPI.categoryAlfhomancy);
		vrangerboots.setIcon(new ItemStack(ModItems.vrangerboots));
		vrangerboots.setLexiconPages(new PageText("0"));
	}
}

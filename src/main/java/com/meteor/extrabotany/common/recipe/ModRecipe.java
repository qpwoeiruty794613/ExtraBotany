package com.meteor.extrabotany.common.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.lib.LibOreDict;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibOreDictName;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipe {
	
	public static IRecipe baubleDog0;
	public static IRecipe baubleDog1;
	public static IRecipe baubleDog2;
	public static IRecipe baubleDog3;
	public static IRecipe elvenQuartz;
	public static IRecipe gaiaQuartz;
	public static IRecipe goldString;
	public static IRecipe bullet0;
	public static IRecipe bullet1;
	public static IRecipe bullet2;
	public static IRecipe bullet3;
	public static IRecipe bullet4;
	public static IRecipe olympus;
	
	public static void init(){
		ModManaInfusionRecipe.init();
		ModPetalRecipe.init();
		int recipeListSize = CraftingManager.getInstance().getRecipeList().size();
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.teleportpearl, 16), 
				new Object[] {"AAA","ABA","AAA", 'A', new ItemStack(vazkii.botania.common.item.ModItems.manaBottle), 'B', Items.ender_pearl});
		//Olympus
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.olympusguard), new Object[] {
			new ItemStack(ModItems.athenabless), 
			new ItemStack(ModItems.cronusphantom), 
			new ItemStack(ModItems.lokighostrick)});
		olympus = BotaniaAPI.getLatestAddedRecipe(); 
		//Bullet
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bullet,8,4), new Object[] {
			new ItemStack(vazkii.botania.common.item.ModItems.manaResource), 
			new ItemStack(Items.gunpowder), 
			new ItemStack(Items.paper)});
		bullet0 = BotaniaAPI.getLatestAddedRecipe(); 
		
		addOreDictRecipe(new ItemStack(ModItems.bullet, 8, 2),
				"AAA","ABA","AAA",
				'A', new ItemStack(ModItems.bullet,1,4),
				'B', new ItemStack(vazkii.botania.common.item.ModItems.manaResource,1,8));
		bullet1 = BotaniaAPI.getLatestAddedRecipe(); 
		
		addOreDictRecipe(new ItemStack(ModItems.bullet, 8, 3),
				"AAA","ABA","AAA",
				'A', new ItemStack(ModItems.bullet,1,4),
				'B', new ItemStack(vazkii.botania.common.item.ModItems.manaResource,1,4));
		bullet2 = BotaniaAPI.getLatestAddedRecipe(); 
		
		addOreDictRecipe(new ItemStack(ModItems.bullet, 8, 5),
				"AAA","ABA","AAA",
				'A', new ItemStack(ModItems.bullet,1,4),
				'B', new ItemStack(vazkii.botania.common.item.ModItems.manaResource,1,7));
		bullet3 = BotaniaAPI.getLatestAddedRecipe(); 
		
		addOreDictRecipe(new ItemStack(ModItems.bullet, 8, 0),
				"AAA","ABA","AAA",
				'A', new ItemStack(ModItems.bullet,1,4),
				'B', new ItemStack(Blocks.tnt));
		bullet4 = BotaniaAPI.getLatestAddedRecipe(); 
		
		//For Basics
		addOreDictRecipe(new ItemStack(ModItems.material, 8, 9),
				"AAA","ABA","AAA",
				'A', LibOreDict.MANA_STRING,
				'B', new ItemStack(Items.gold_ingot));
		goldString = BotaniaAPI.getLatestAddedRecipe(); 
		//Recipes for quartz
		addOreDictRecipe(new ItemStack(ModItems.material, 8, 7),
			"AAA","ABA","AAA",
			'A', LibOreDictName.QUARTZ_ELEMENTIUM,
			'B', LibOreDictName.GAIA_ESSENCE);
		gaiaQuartz = BotaniaAPI.getLatestAddedRecipe();
		addOreDictRecipe(new ItemStack(ModBlocks.gaiaquartz, 1),
				"AA","AA",
				'A', LibOreDictName.QUARTZ_GAIA);
		addOreDictRecipe(new ItemStack(ModBlocks.gaiaquartzstairs, 4),
				"A  ","AA ","AAA",
				'A', new ItemStack(ModBlocks.gaiaquartz));
		addOreDictRecipe(new ItemStack(ModBlocks.gaiaquartzstairs, 4),
				"  A"," AA","AAA",
				'A', new ItemStack(ModBlocks.gaiaquartz));
		addOreDictRecipe(new ItemStack(ModBlocks.gaiaquartzslab, 6),
				"AAA",
				'A', new ItemStack(ModBlocks.gaiaquartz));
		addOreDictRecipe(new ItemStack(ModItems.material, 8, 7),
				"AAA","ABA","AAA",
				'A', new ItemStack(vazkii.botania.common.item.ModItems.quartz,1,1),
				'B', LibOreDict.ELEMENTIUM);
		elvenQuartz = BotaniaAPI.getLatestAddedRecipe();
		addOreDictRecipe(new ItemStack(ModBlocks.elvenquartz, 1),
				"AA","AA",
				'A', LibOreDictName.QUARTZ_ELEMENTIUM);
		addOreDictRecipe(new ItemStack(ModBlocks.elvenquartzstairs, 4),
				"A  ","AA ","AAA",
				'A', new ItemStack(ModBlocks.elvenquartz));
		addOreDictRecipe(new ItemStack(ModBlocks.elvenquartzstairs, 4),
				"  A"," AA","AAA",
				'A', new ItemStack(ModBlocks.elvenquartz));
		addOreDictRecipe(new ItemStack(ModBlocks.elvenquartzslab, 6),
				"AAA",
				'A', new ItemStack(ModBlocks.elvenquartz));
		//For Baubles
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 0),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.BLANK_CARD);
		baubleDog0 = BotaniaAPI.getLatestAddedRecipe();
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 1),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.ASTRAL_FORCE);
		baubleDog1 = BotaniaAPI.getLatestAddedRecipe();
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 2),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.QUARTZ_ELEMENTIUM);
		baubleDog2 = BotaniaAPI.getLatestAddedRecipe();
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 3),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.QUARTZ_GAIA);
		baubleDog3 = BotaniaAPI.getLatestAddedRecipe();
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}
}

package com.meteor.extrabotany.common.recipes;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.lib.LibOreDict;

import com.meteor.extrabotany.common.blocks.ModBlocks;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibOreDictName;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipe {
	
	public static void init(){
		ModManaInfusionRecipe.init();
		ModPetalRecipe.init();
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.teleportpearl, 16), 
				new Object[] {"AAA","ABA","AAA", 'A', new ItemStack(vazkii.botania.common.item.ModItems.manaBottle), 'B', Items.ender_pearl});
		//For Basics
		addOreDictRecipe(new ItemStack(ModItems.material, 8, 9),
				"AAA","ABA","AAA",
				'A', LibOreDict.MANA_STRING,
				'B', new ItemStack(Items.gold_ingot));
		//Recipes for quartz
		addOreDictRecipe(new ItemStack(ModItems.material, 8, 7),
			"AAA","ABA","AAA",
			'A', LibOreDictName.QUARTZ_ELEMENTIUM,
			'B', LibOreDictName.GAIA_ESSENCE);
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
				'A', LibOreDict.QUARTZ[1],
				'B', LibOreDict.ELEMENTIUM);
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
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 1),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.ASTRAL_FORCE);
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 2),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.QUARTZ_ELEMENTIUM);
		addOreDictRecipe(new ItemStack(ModItems.dog, 1, 3),
				"AAA","ABA","AAA",
				'A', LibOreDictName.STRING_GOLD,
				'B', LibOreDictName.QUARTZ_GAIA);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}
}

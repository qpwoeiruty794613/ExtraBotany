package com.meteor.extrabotany.api;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;

import com.meteor.extrabotany.api.extrabotany.recipe.RecipeInfernoidisy;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.ModItems;

public class ExtraBotanyAPI {
	
	public static ModItems ModItems = new ModItems();
	public static ModBlocks ModBlocks = new ModBlocks();
	public static ClientProxy proxy = new ClientProxy();
	
	public static Set<Item> diplopbambooBlacklist = new LinkedHashSet<Item>();
	
	public static List<RecipeInfernoidisy> infernoidisyRecipes = new ArrayList<RecipeInfernoidisy>();
	public static List<RecipeStonesia> stonesiaRecipes = new ArrayList<RecipeStonesia>();
	
	public static DamageSource[] damageSource = {
		new DamageSource("realDamage"),
		new DamageSource("realDamageHoly"),
		new DamageSource("realDamageCursed"),
		new DamageSource("realDamageGaia"),
		new DamageSource("magicDamage"),
		new DamageSource("magicDamageMissile"),
		new DamageSource("magicDamageLandmine"),
	};
	
	public static boolean isRealDamage(DamageSource s){
		return s.toString().startsWith("realDamage");
	}
	
	public static boolean isMagicDamage(DamageSource s){
		return s.toString().startsWith("magicDamage");
	}
	
	public static void addShield(float shield, EntityPlayer player){
		PropertyHandler.addShieldAmount(shield, player);
	}
	
	public static void setShield(float shield, EntityPlayer player){
		PropertyHandler.setShieldAmount(shield, player);
	}
	
	public static void getShield(EntityPlayer player){
		PropertyHandler.getShieldAmount(player);
	}
	
	public static void getMaxShield(EntityPlayer player){
		PropertyHandler.getMaxShieldAmount(player);
	}
	
	public static void blacklistItemFromDiplopBamboo(Item item){
		diplopbambooBlacklist.add(item);
	}
	
	public static boolean isItemBlacklistedFromDiplopBamboo(Item item){
		return diplopbambooBlacklist.contains(item);
	}
	
	public static RecipeInfernoidisy registerInfernoidisyRecipe(Object input, Block output, int outputMeta) {
		RecipeInfernoidisy recipe = new RecipeInfernoidisy(input, output, outputMeta);
		infernoidisyRecipes.add(recipe);
		return recipe;
	}
	
	public static RecipeStonesia registerStonesiaRecipe(Object input, int mana, int outputMeta) {
		RecipeStonesia recipe = new RecipeStonesia(input, mana, outputMeta);
		stonesiaRecipes.add(recipe);
		return recipe;
	}
	
	public static RecipeInfernoidisy removeInfernoidisyRecipe(Object input, Block output, int outputMeta) {
		RecipeInfernoidisy recipe = new RecipeInfernoidisy(input, output, outputMeta);
		infernoidisyRecipes.remove(recipe);
		return recipe;
	}
	
	public static RecipeStonesia removeStonesiaRecipe(Object input, int mana, int outputMeta) {
		RecipeStonesia recipe = new RecipeStonesia(input, mana, outputMeta);
		stonesiaRecipes.remove(recipe);
		return recipe;
	}

}

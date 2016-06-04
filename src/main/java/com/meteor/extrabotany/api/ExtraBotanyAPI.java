package com.meteor.extrabotany.api;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.ModItems;

public class ExtraBotanyAPI {
	
	public static ModItems ModItems = new ModItems();
	public static ModBlocks ModBlocks = new ModBlocks();
	public static ClientProxy proxy = new ClientProxy();
	
	public static Set<Item> diplopbambooBlacklist = new LinkedHashSet<Item>();
	
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

}

package com.meteor.extrabotany.api;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.item.ModItems;

public class ExtraBotanyAPI {
	
	public static ModItems ModItems = new ModItems();
	public static ModBlocks ModBlocks = new ModBlocks();
	public static ClientProxy proxy = new ClientProxy();
	
	public static Set<Item> diplopbambooBlacklist = new LinkedHashSet<Item>();
	
	public static void addShield(float shield, EntityPlayer player){
		ShieldHandler.addShieldAmount(shield, player);
	}
	
	public static void setShield(float shield, EntityPlayer player){
		ShieldHandler.setShieldAmount(shield, player);
	}
	
	public static void getShield(EntityPlayer player){
		ShieldHandler.getShieldAmount(player);
	}
	
	public static void getMaxShield(EntityPlayer player){
		ShieldHandler.getMaxShieldAmount(player);
	}
	
	public static void blacklistItemFromDiplopBamboo(Item item){
		diplopbambooBlacklist.add(item);
	}
	
	public static boolean isItemBlacklistedFromDiplopBamboo(Item item){
		return diplopbambooBlacklist.contains(item);
	}

}

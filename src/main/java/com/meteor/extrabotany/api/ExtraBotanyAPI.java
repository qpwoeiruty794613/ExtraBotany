package com.meteor.extrabotany.api;

import net.minecraft.entity.player.EntityPlayer;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.item.ModItems;

public class ExtraBotanyAPI {
	
	public static ModItems Items = new ModItems();
	public static ModBlocks Blocks = new ModBlocks();
	public static ClientProxy proxy = new ClientProxy();
	
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
}

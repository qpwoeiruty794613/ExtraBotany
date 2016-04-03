package com.meteor.extrabotany.common.lib;

import java.util.List;

import com.google.common.collect.Lists;
import com.meteor.extrabotany.common.ExtraBotany;
import com.meteor.extrabotany.common.handler.IDataHandler;
import com.valentin4311.candycraftmod.CandyCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class LibData {
	
    public static int getCandyBurnTime(ItemStack stack){
    	if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();
            if (item == Items.cookie) return 16;
            if (item == Items.sugar) return 12;
            if (item == Items.cake) return 256;
            if(ExtraBotany.candycraftLoaded = true){
            	if (item == CandyCraft.ChewingGum) return 20;
            	if (item == CandyCraft.Lollipop) return 20;
            	if (item == CandyCraft.CranberryFish) return 24;
            	if (item == CandyCraft.CranberryFishCooked) return 32;
            	if (item == CandyCraft.Dragibus) return 16;
            	if (item == CandyCraft.Gummy) return 16;
            	if (item == CandyCraft.GummyBall) return 16;
            	if (item == CandyCraft.HoneyComb) return 32;
            	if (item == CandyCraft.PEZDust) return 16;
            	if (item == CandyCraft.CandiedCherry) return 20;
            	if (item == CandyCraft.ChocolateCoins) return 20;
            	if (item == CandyCraft.Licorice) return 24;
            	if (item == CandyCraft.CranberryScale) return 16;
            	if (item == CandyCraft.SugarCrystal) return 8;
            	if (item == CandyCraft.CottonCandy) return 24;
            	if (item == CandyCraft.HoneyShard) return 12;
            	if (item == CandyCraft.MarshmallowFlower) return 24;
            	if (item == CandyCraft.WaffleNugget) return 4;
            	if (item == CandyCraft.Waffle) return 32;
            	if (item == CandyCraft.PEZ) return 32;
            	if (item == CandyCraft.NougatPowder) return 24;
            	}
			return LibRegistry.getFuelValue(stack);		
		}
	
    }
    
   
}

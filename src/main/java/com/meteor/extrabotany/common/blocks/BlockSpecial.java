package com.meteor.extrabotany.common.blocks;

import java.util.Arrays;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.meteor.extrabotany.common.ExtraBotany;
import com.meteor.extrabotany.common.lib.LibBlockName;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class BlockSpecial extends BlockSpecialFlower {
	static {
		ExtraBotany.subtilesForCreativeMenu.addAll(Arrays.asList(new String[] {			
			// Generating
			LibBlockName.CANDY_FLOWER,
			LibBlockName.BLUE_ENCHANTRESS,
			LibBlockName.SUNSHINE_LILY,
			LibBlockName.MOONLIGHT_LILY,
			LibBlockName.OMNIVIOLET,
			//Functional
			LibBlockName.NUMERON_DANDELIFE,
			LibBlockName.NECRO_FLUER,
			LibBlockName.WOODIENIA,	
			LibBlockName.VOLATILILY,
		}));
	}	
		
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(String s : ExtraBotany.subtilesForCreativeMenu) {
			par3List.add(ItemBlockSpecialFlower.ofType(s));
		}
	}
}

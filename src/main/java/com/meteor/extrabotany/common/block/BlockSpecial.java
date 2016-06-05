package com.meteor.extrabotany.common.block;

import java.util.Arrays;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.lib.LibBlockName;

public class BlockSpecial extends BlockSpecialFlower {
	static {
		ExtraBotany.subtilesForCreativeMenu.addAll(Arrays.asList(new String[] {			
			// Generating
			LibBlockName.CANDY_FLOWER,
			LibBlockName.BLUE_ENCHANTRESS,
			LibBlockName.SUNSHINE_LILY,
			LibBlockName.MOONLIGHT_LILY,
			LibBlockName.OMNIVIOLET,
			LibBlockName.GEMINIORCHID,
			LibBlockName.STONESIA,
			LibBlockName.PYSCHOBLOOM,
			//Functional
			LibBlockName.NUMERON_DANDELIFE,
			LibBlockName.NECRO_FLUER,
			LibBlockName.WOODIENIA,	
			LibBlockName.VOLATILILY,
			LibBlockName.ICEBIRDIUM,
			LibBlockName.NUMERON_BALSAM,
			LibBlockName.JUDASVOW,
			LibBlockName.ARTIFACONIA,
			LibBlockName.VOIDUIM,
			LibBlockName.DIPLOPBAMBOO,
			LibBlockName.INFERNOIDISY,
			LibBlockName.LAUNCHISH,
		}));
	}	
		
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(String s : ExtraBotany.subtilesForCreativeMenu) {
			par3List.add(ItemBlockSpecialFlower.ofType(s));
		}
	}
}

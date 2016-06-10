package com.meteor.extrabotany.common.enchantment;

import vazkii.botania.api.item.IRelic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

public class ModEnchantment {
	
	public static Enchantment gaiablessing;
	public static Enchantment divineFavor;
	public static Enchantment divineMark;
	
	public static void init(){
		gaiablessing = new EnchGaiaBlessing();
		divineFavor = new EnchDivineFavor();
		divineMark = new EnchDivineMark();
	}

}

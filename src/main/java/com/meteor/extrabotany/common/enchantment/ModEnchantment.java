package com.meteor.extrabotany.common.enchantment;

import net.minecraft.enchantment.Enchantment;

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

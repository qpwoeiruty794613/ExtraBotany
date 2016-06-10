package com.meteor.extrabotany.common.util;

import com.meteor.extrabotany.common.enchantment.ModEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchHelper {
	
	public static int getEnchLevel(Enchantment e, ItemStack stack){
		return EnchantmentHelper.getEnchantmentLevel(e.effectId, stack);
	}
	
	public static void setEnch(Enchantment e, int l, ItemStack stack){
		stack.addEnchantment(e, l);
	}
	
	public static float getDivineFavorBuff(ItemStack stack){
		return (1 + getEnchLevel(ModEnchantment.divineFavor, stack)*0.2F);
	}
	
	public static float getDivineMarkBuff(ItemStack stack){
		return (1 - getEnchLevel(ModEnchantment.divineMark, stack)*0.233F);
	}
	
}

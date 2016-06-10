package com.meteor.extrabotany.common.enchantment;

import com.meteor.extrabotany.common.handler.ConfigHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;

public class EnchDivineFavor extends Enchantment{

	public EnchDivineFavor() {
		super(ConfigHandler.enchDivineFavor, 2, EnumHelper.addEnchantmentType("relic"));
		this.setName("divinefavor");
	}

}

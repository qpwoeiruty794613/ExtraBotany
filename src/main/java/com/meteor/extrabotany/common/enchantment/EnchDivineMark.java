package com.meteor.extrabotany.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.util.EnumHelper;

import com.meteor.extrabotany.common.handler.ConfigHandler;

public class EnchDivineMark extends Enchantment{

	public EnchDivineMark() {
		super(ConfigHandler.enchDivineMark, 2, EnumHelper.addEnchantmentType("relic"));
		this.setName("divinemark");
	}
	
	@Override
    public int getMaxLevel()
    {
        return 3;
    }
}

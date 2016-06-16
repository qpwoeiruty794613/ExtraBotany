package com.meteor.extrabotany.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.util.EnumHelper;

import com.meteor.extrabotany.common.handler.ConfigHandler;

public class EnchDivineFavor extends Enchantment{

	public EnchDivineFavor() {
		super(ConfigHandler.enchDivineFavor, 2, EnumHelper.addEnchantmentType("relic"));
		this.setName("divinefavor");
	}
	
	@Override
    public int getMaxLevel()
    {
        return 5;
    }

}

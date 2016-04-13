package com.meteor.extrabotany.common.effects;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionFastParticleSorting extends PotionEffectMods{

	public PotionFastParticleSorting(String name, ResourceLocation icon) {
		super(name, false, icon);
	}
	
	private static final String __OBFID = "CL_00001525";

    public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_)
    {
        return this.id == Potion.weakness.id ? (double)(-0.5F * (float)(p_111183_1_ + 1)) : 1D +(0.1D * (double)(p_111183_1_ + 1));
    }

}

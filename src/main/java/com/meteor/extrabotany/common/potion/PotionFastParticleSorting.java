package com.meteor.extrabotany.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class PotionFastParticleSorting extends PotionEffectMods{
	public PotionFastParticleSorting(String name, ResourceLocation icon) {
		super(name, false, icon);
	}

	@Override
	public void performEffect(EntityLivingBase entity, int level) { 

	}

	@Override
	public boolean isReady(int duration, int level)
	{
		return true;
	}
}

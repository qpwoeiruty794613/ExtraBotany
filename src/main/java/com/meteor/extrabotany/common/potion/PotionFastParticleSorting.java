package com.meteor.extrabotany.common.potion;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lib.LibPotionEffectName;

import vazkii.botania.common.brew.potion.PotionMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class PotionFastParticleSorting extends PotionMods{
	
	public PotionFastParticleSorting() {
		super(ConfigHandler.idPotionFPS, LibPotionEffectName.FASTPARTICLESORTING, false, 0xFF0A0A, 0);
	}
	
}

package com.meteor.extrabotany.common.effect;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;

import com.meteor.extrabotany.common.lib.LibPotionEffectName;
import com.meteor.extrabotany.common.lib.LibReference;

public class ModPotionEffect {
	public static Potion slowparticlesorting;
	public static Potion fastparticlesorting;
	public static Potion residualpain;

	public static void init() {
		slowparticlesorting = new PotionEffectMods(LibPotionEffectName.SLOWPARTICLESORTING, false,LibReference.POTION_SLOWPARTICLESORTING).setPotionName("potion." + LibPotionEffectName.SLOWPARTICLESORTING);
		fastparticlesorting = new PotionEffectMods(LibPotionEffectName.FASTPARTICLESORTING, false,LibReference.POTION_FASTPARTICLESORTING).setPotionName("potion." + LibPotionEffectName.FASTPARTICLESORTING).func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.15D, 2);
		residualpain = new PotionResidualPain(LibPotionEffectName.RESIDUALPAIN, LibReference.POTION_RESIDUALPAIN).setPotionName("potion." + LibPotionEffectName.RESIDUALPAIN);
	}
}

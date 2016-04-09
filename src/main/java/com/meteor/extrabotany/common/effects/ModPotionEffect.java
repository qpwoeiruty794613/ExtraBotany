package com.meteor.extrabotany.common.effects;

import net.minecraft.entity.SharedMonsterAttributes;

import com.meteor.extrabotany.common.lib.LibPotionEffectName;
import com.meteor.extrabotany.common.lib.LibReference;

public class ModPotionEffect {
	public static PotionEffectMods slowparticlesorting;
	public static PotionEffectMods fastparticlesorting;
	public static void init() {
		slowparticlesorting = (PotionEffectMods) new PotionEffectMods(LibPotionEffectName.SLOWPARTICLESORTING, LibReference.POTION_SLOWPARTICLESORTING).setPotionName("potion." + LibPotionEffectName.SLOWPARTICLESORTING);
		fastparticlesorting = (PotionEffectMods) new PotionEffectMods(LibPotionEffectName.FASTPARTICLESORTING, LibReference.POTION_FASTPARTICLESORTING).setPotionName("potion." + LibPotionEffectName.FASTPARTICLESORTING).func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.15D, 2);
	}
}

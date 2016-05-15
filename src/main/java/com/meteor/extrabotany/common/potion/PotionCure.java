package com.meteor.extrabotany.common.potion;

import java.util.Collection;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionCure extends PotionEffectMods{
	
	public PotionCure(String name, ResourceLocation icon) {
		super(name, false, icon);
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int level) { 
		
		boolean flag = false;

		Collection<PotionEffect> potions = entity.getActivePotionEffects();
		
		for (PotionEffect potion : potions) {
			int id = potion.getPotionID();
			if (ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})) {
				entity.removePotionEffect(id);
				flag = true;
			}
			
			break;
		}
	}

	@Override
	public boolean isReady(int duration, int level)
	{
		return true;
	}
}

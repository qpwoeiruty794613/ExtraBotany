package com.meteor.extrabotany.common.potion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.meteor.extrabotany.common.lib.LibPotionEffectName;
import com.meteor.extrabotany.common.lib.LibReference;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionEffectMods extends Potion{
	private static int nextID = 32;
	public final ResourceLocation mIcon;
	
	public static PotionSlowParticleSorting slowparticlesorting;
	public static PotionFastParticleSorting fastparticlesorting;
	public static PotionResidualPain residualpain;
	public static PotionCure cure;
	public static PotionDurance durance;
	
	public static void init() {
			try {
				Field potionTypesField = Potion.class.getDeclaredField("potionTypes");

				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(potionTypesField, potionTypesField.getModifiers() & ~Modifier.FINAL);
				
				nextID = Potion.potionTypes.length;
				Potion[] newPotionTypes = new Potion[Potion.potionTypes.length + 32];

				for (int i = 0; i < Potion.potionTypes.length; i++)
					newPotionTypes[i] = Potion.potionTypes[i];

				potionTypesField.set(null, newPotionTypes);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slowparticlesorting = new PotionSlowParticleSorting(LibPotionEffectName.SLOWPARTICLESORTING,LibReference.POTION_SLOWPARTICLESORTING);
			fastparticlesorting = new PotionFastParticleSorting(LibPotionEffectName.FASTPARTICLESORTING,LibReference.POTION_FASTPARTICLESORTING);
			cure = new PotionCure(LibPotionEffectName.CURE, LibReference.POTION_CURE);
			residualpain = new PotionResidualPain(LibPotionEffectName.RESIDUALPAIN, LibReference.POTION_RESIDUALPAIN);
			durance = (PotionDurance) new PotionDurance(LibPotionEffectName.DURANCE, LibReference.POTION_DURANCE).func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -1.0D, 2);
	}
	
	public PotionEffectMods(String name, Boolean isbadEffect, ResourceLocation icon) {
		super(nextID++, false, 0);
		this.setPotionName("potion."+name);
		this.mIcon = icon;

	}

	@Override
	public void performEffect(EntityLivingBase entity, int level) { 

	}

	@Override
	public boolean isReady(int duration, int level)
	{
		return true;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
	{
		mc.getTextureManager().bindTexture(mIcon);
		
		x += 6;
		y += 7;
		final int width = 18, height = 18;
		
		Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(x + width, y + height, 0.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(x + width, y, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(x, y, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
	}
}

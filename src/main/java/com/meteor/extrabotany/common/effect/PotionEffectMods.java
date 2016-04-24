package com.meteor.extrabotany.common.effect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionEffectMods extends Potion{
	private static int nextID;
	public final ResourceLocation mIcon;
	
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
		init();
	}
	
	public PotionEffectMods(String name, Boolean isbadEffect, ResourceLocation icon) {
		super(nextID++, false, 0);
		this.setPotionName(name);
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

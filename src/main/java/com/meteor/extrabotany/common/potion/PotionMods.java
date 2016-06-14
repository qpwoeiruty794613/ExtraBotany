package com.meteor.extrabotany.common.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.brew.potion.PotionMod;

import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionMods extends Potion{
	
	private static final ResourceLocation resource = LibReference.POTION_GUI;

	public PotionMods(int id, String name, boolean badEffect, int color,
			int iconIndex) {
		super(id, badEffect, color);
		setPotionName("extrabotany.potion." + name);
		setIconIndex(iconIndex % 8, iconIndex / 8);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(resource);

		return super.getStatusIconIndex();
	}
	
	public boolean hasEffect(EntityLivingBase entity) {
		return hasEffect(entity, this);
	}

	public boolean hasEffect(EntityLivingBase entity, Potion potion) {
		return entity.getActivePotionEffect(potion) != null;
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

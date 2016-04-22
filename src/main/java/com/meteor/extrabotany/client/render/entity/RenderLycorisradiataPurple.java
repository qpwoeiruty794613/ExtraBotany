package com.meteor.extrabotany.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.meteor.extrabotany.client.model.ModelLycorisradiata;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;

public class RenderLycorisradiataPurple extends RenderLiving{
	
	private static final ResourceLocation texture = new ResourceLocation("extrabotany", "textures/models/Lycorisradiata_PURPLE.png");

	public RenderLycorisradiataPurple() {
	    super(new ModelLycorisradiata(), 0.5F);
	}

	protected void preRender(EntityLycorisradiataPurple par1EntityFlowerCyan, float par2) {
	    this.shadowSize = 0.0F;
	}

	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
	    preRender((EntityLycorisradiataPurple)par1EntityLiving, par2);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
	    return texture;
	}
}

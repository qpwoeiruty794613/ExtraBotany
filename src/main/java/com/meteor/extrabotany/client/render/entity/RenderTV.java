package com.meteor.extrabotany.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.meteor.extrabotany.client.model.ModelLycorisradiata;
import com.meteor.extrabotany.client.model.ModelTV;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityTV;

public class RenderTV extends RenderLiving{
	
	private static final ResourceLocation texture = new ResourceLocation("extrabotany", "textures/models/tv.png");

	public RenderTV() {
	    super(new ModelTV(), 0.5F);
	}

	protected void preRender(EntityTV par1EntityFlowerCyan, float par2) {
	    this.shadowSize = 0.0F;
	}

	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
		preRender((EntityTV)par1EntityLiving, par2);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

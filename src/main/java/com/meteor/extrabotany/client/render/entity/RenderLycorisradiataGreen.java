package com.meteor.extrabotany.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.meteor.extrabotany.client.model.ModelLycorisradiata;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;

public class RenderLycorisradiataGreen extends RenderLiving{
	
	private static final ResourceLocation texture = new ResourceLocation("extrabotany", "textures/models/Lycorisradiata_GREEN.png");

	public RenderLycorisradiataGreen() {
	    super(new ModelLycorisradiata(), 0.5F);
	}

	protected void preRender(EntityLycorisradiataGreen par1EntityFlowerCyan, float par2) {
	    this.shadowSize = 0.0F;
	}

	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
		preRender((EntityLycorisradiataGreen)par1EntityLiving, par2);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

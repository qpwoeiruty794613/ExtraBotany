package com.meteor.extrabotany.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class Render22 extends RenderBiped
{
    private static final ResourceLocation Textures = new ResourceLocation("extrabotany", "textures/models/22.png");
    public Render22() {
		super(new ModelBiped(0.5F), 0F);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return Textures;
	}
}

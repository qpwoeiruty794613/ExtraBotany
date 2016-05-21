package com.meteor.extrabotany.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.ARBShaderObjects;

import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.helper.ShaderHelper;

import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;

public class RenderGaiaIII extends RenderBiped {

	public static float DEFAULT_GRAIN_INTENSITY = 0.05F;
	public static float DEFAULT_DISFIGURATION = 0.025F;

	public static float grainIntensity = DEFAULT_GRAIN_INTENSITY;
	public static float disfiguration = DEFAULT_DISFIGURATION;

	public static ShaderCallback callback = new ShaderCallback() {

		@Override
		public void call(int shader) {
			// Frag Uniforms
			int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB(shader, "disfiguration");
			ARBShaderObjects.glUniform1fARB(disfigurationUniform, disfiguration);

			// Vert Uniforms
			int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB(shader, "grainIntensity");
			ARBShaderObjects.glUniform1fARB(grainIntensityUniform, grainIntensity);
		}
	};

	public static ShaderCallback defaultCallback = new ShaderCallback() {

		@Override
		public void call(int shader) {
			// Frag Uniforms
			int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB(shader, "disfiguration");
			ARBShaderObjects.glUniform1fARB(disfigurationUniform, DEFAULT_DISFIGURATION);

			// Vert Uniforms
			int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB(shader, "grainIntensity");
			ARBShaderObjects.glUniform1fARB(grainIntensityUniform, DEFAULT_GRAIN_INTENSITY);
		}
	};

	public RenderGaiaIII() {
		super(new ModelBiped(0.5F), 0F);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		EntityGaiaIII dopple = (EntityGaiaIII) par1Entity;
		BossBarHandler.setCurrentBoss(dopple);

		int invulTime = dopple.getInvulTime();
		if(invulTime > 0) {
			grainIntensity = invulTime > 20 ? 1F : invulTime * 0.05F;
			disfiguration = grainIntensity * 0.3F;
		} else {
			disfiguration = (0.025F + dopple.hurtTime * ((1F - 0.15F) / 20F)) / 2F;
			grainIntensity = 0.05F + dopple.hurtTime * ((1F - 0.15F) / 10F);
		}

		ShaderHelper.useShader(ShaderHelper.doppleganger, callback);
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
		ShaderHelper.releaseShader();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return Minecraft.getMinecraft().thePlayer.getLocationSkin();
	}
}

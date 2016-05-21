package com.meteor.extrabotany.client.render;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.core.helper.ShaderHelper;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderShield{
	public static final ResourceLocation shieldBar = LibReference.BAR_SHIELD;
	private static ResourceLocation textureShield = LibReference.SHIELD;
	
	@SubscribeEvent
	public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;
		if(event.type == ElementType.ALL) {
			profiler.startSection("botania-hud");
			MovingObjectPosition pos = mc.objectMouseOver;

			profiler.startSection("shieldBar");
			EntityPlayer player = mc.thePlayer;
			int shield = (int) ShieldHandler.getShieldAmount(player);
			int maxShield = (int) player.getMaxHealth();
			boolean creative = false;
			renderManaInvBar(event.resolution, creative, shield, maxShield);
			profiler.endSection();
			GL11.glColor4f(1F, 1F, 1F, 1F);
			}
	}
	
	private void renderManaInvBar(ScaledResolution res, boolean hasCreative, int shield, int maxShield) {
		Minecraft mc = Minecraft.getMinecraft();
		int width = 182;
		int x = res.getScaledWidth() / 2 - width / 2;
		int y = res.getScaledHeight() - ConfigHandler.shieldBarHeight + 2;

		if(!hasCreative) {
			if(maxShield == 0)
				width = 0;
			else width *= (double) shield / (double) maxShield;
		}

		if(width == 0) {
			if(shield > 0)
				width = 1;
			else return;
		}

		Color color = new Color(Color.HSBtoRGB(1.64F, (float) Math.min(1F, Math.sin(System.currentTimeMillis() / 200D) * 0.5 + 1.64F), 1.64F));
		GL11.glColor4ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue(), (byte) (255 - color.getRed()));
		mc.renderEngine.bindTexture(shieldBar);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.drawTexturedModalRect(x, y, 0, 0, 251, width, 5);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void translateToFootLevel(EntityPlayer player) {
		GL11.glTranslated(0, 2F, 0);
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderShield(EntityPlayer player, float partialTicks) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textureShield);
		
		if(player != null)
		translateToFootLevel(player);
		GL11.glTranslatef(0, -0.5F, 0);

		Tessellator tes = Tessellator.instance;
		ShaderHelper.useShader(ShaderHelper.halo);
		tes.startDrawingQuads();
		tes.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.5F);
		tes.addVertexWithUV(-0.85, 0, -0.85, 0, 0);
		tes.addVertexWithUV(-0.85, 0, 0.85, 0, 1);
		tes.addVertexWithUV(0.85, 0, 0.85, 1, 1);
		tes.addVertexWithUV(0.85, 0, -0.85, 1, 0);
		tes.draw();
		ShaderHelper.releaseShader();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

}

package com.meteor.extrabotany.client.render.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.item.ModItems;

public class RenderTeleportPearl extends Render{
	 private float amount;

	 public RenderTeleportPearl(float amount)
	 {
		 this.amount = amount;
	 }

	 public void doRender(EntityTeleportPearl p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	 {
	    GL11.glPushMatrix();
	    this.bindEntityTexture(p_76986_1_);
	    GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
	    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	    float f2 = this.amount;
	    GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
	    IIcon iicon = ModItems.teleportpearl.getIconFromDamage(0);
	    Tessellator tessellator = Tessellator.instance;
	    float f3 = iicon.getMinU();
	    float f4 = iicon.getMaxU();
	    float f5 = iicon.getMinV();
	    float f6 = iicon.getMaxV();
	    float f7 = 1.0F;
	    float f8 = 0.5F;
	    float f9 = 0.25F;
	    GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    tessellator.addVertexWithUV((double)(0.0F - f8), (double)(0.0F - f9), 0.0D, (double)f3, (double)f6);
	    tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0F - f9), 0.0D, (double)f4, (double)f6);
	    tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0F - f9), 0.0D, (double)f4, (double)f5);
	    tessellator.addVertexWithUV((double)(0.0F - f8), (double)(1.0F - f9), 0.0D, (double)f3, (double)f5);
	    tessellator.draw();
	    GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	    GL11.glPopMatrix();
	 }

	 protected ResourceLocation getEntityTexture(EntityTeleportPearl p_110775_1_)
	 {
		 return TextureMap.locationItemsTexture;
	 }

	 protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	 {
	     return this.getEntityTexture((EntityTeleportPearl)p_110775_1_);
	 }

	 public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	 {
	     this.doRender((EntityTeleportPearl)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	 }
	 
}

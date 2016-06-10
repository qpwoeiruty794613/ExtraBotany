package com.meteor.extrabotany.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.helper.ShaderHelper;

import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderShield{
	public static final ResourceLocation shieldBar = LibReference.BAR_SHIELD;
	private static ResourceLocation textureShield = LibReference.SHIELD;
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onDrawScreenPre(RenderGameOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;

		if(event.type == ElementType.HEALTH) {
			profiler.startSection("shieldBar");
			profiler.endSection();
		}
	}
	
	@SubscribeEvent
	public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;
		if(event.type == ElementType.ALL) {
			profiler.startSection("shieldBar");
			boolean creative = false;
			renderShield(event.resolution, creative);
			profiler.endSection();
			GL11.glColor4f(1F, 1F, 1F, 1F);
		}
		GameSettings gs = Minecraft.getMinecraft().gameSettings;
	}
	
	/*
	Old code
	private void renderShieldInvBar(ScaledResolution res, boolean hasCreative, int shield, int maxShield) {
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
	*/
	private void renderShield(ScaledResolution res, boolean hasCreative) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int scaledWidth = scaledresolution.getScaledWidth();
        int scaledHeight = scaledresolution.getScaledHeight();
        int xBasePos = scaledWidth / 2 - 91;
        int yBasePos = scaledHeight - 39;
        
        boolean highlight = mc.thePlayer.hurtResistantTime / 3 % 2 == 1;

        if (mc.thePlayer.hurtResistantTime < 10)
        {
            highlight = false;
        }
        
        mc.getTextureManager().bindTexture(shieldBar);
        
        float s1 = PropertyHandler.getShieldAmount(mc.thePlayer);
        float ss1 = s1/2;
        
        for(int r = 0; r < s1/20; r++){
        	int ra = r * 10;
        	if(s1 > 0){
                for(int i = 0; i < Math.min(ss1 - ra, 10); i++){
    	        	if(s1 > 1 + ra * 2)
    	        		this.drawTexturedModalRect(xBasePos + 8 * Math.min(ss1 % 1 == 0 ? i : ss1 > 10 + ra ? i : Math.max(i-1, 0), 9), yBasePos - ra, 0, highlight ? 9 : 0, 9, 9);
    	        	if(ss1 % 1 != 0 && ss1 <= 10 + ra)
    	        		this.drawTexturedModalRect(xBasePos + 8 * Math.min(i , 9), yBasePos - ra, 9, highlight ? 9 : 0, 9, 9);
    	        }
            }
        }  
        
        /*
        Ugly code
        if(s1 > 0){
            for(int i = 0; i < Math.min(ss1, 10); i++){
	        	if(s1 > 1)
	        		this.drawTexturedModalRect(xBasePos + 8 * Math.min(ss1 % 1 == 0 ? i : ss1 > 10 ? i : Math.max(i-1, 0), 9), yBasePos, 0, 0, 9, 9);
	        	if(ss1 % 1 != 0 && ss1 <= 10)
	        		this.drawTexturedModalRect(xBasePos + 8 * Math.min(i , 9), yBasePos, 9, 0, 9, 9);
	        }
        }
        if(s1 > 20){
	        for(int i = 0; i < Math.min(ss1 - 10, 10); i++){
	        	if(s1 > 21)
	        		this.drawTexturedModalRect(xBasePos + 8 * Math.min(ss1 % 1 == 0 ? i : ss1 > 20 ? i : Math.max(i-1, 0), 9), yBasePos - 10, 0, 0, 9, 9);
	        	if(ss1 % 1 != 0 && ss1 <= 20)
	        		this.drawTexturedModalRect(xBasePos + 8 * Math.min(i , 9), yBasePos - 10, 9, 0, 9, 9);
	        }
        }
        */
	}
	
	double zLevel = 0;

	public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
	{
		float f = 0.00390625F;
		float f1 = 0.00390625F;
	    Tessellator tessellator = Tessellator.instance;
	    tessellator.startDrawingQuads();
	    tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (par3 + 0) * f), (double) ((float) (par4 + par6) * f1));
	    tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (par3 + par5) * f), (double) ((float) (par4 + par6) * f1));
	    tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (par3 + par5) * f), (double) ((float) (par4 + 0) * f1));
	    tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (par3 + 0) * f),(double) ((float) (par4 + 0) * f1));
	    tessellator.draw();
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

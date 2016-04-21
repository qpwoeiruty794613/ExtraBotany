package com.meteor.extrabotany.client.render;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.item.ModItems;
import baubles.common.lib.PlayerHandler;

import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.lib.LibReference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderShield implements IShieldHandler{
	public static final ResourceLocation shieldBar = LibReference.BAR_SHIELD;
	@SubscribeEvent
	public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;
		if(event.type == ElementType.ALL) {
			profiler.startSection("botania-hud");
			MovingObjectPosition pos = mc.objectMouseOver;

			profiler.startSection("shieldBar");
			EntityPlayer player = mc.thePlayer;
			int shield = (int) this.getShieldAmount(player);
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
	  
	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
	}

}

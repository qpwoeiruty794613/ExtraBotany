package com.meteor.extrabotany.common.lexicon.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.lexicon.LexiconRecipeMappings.EntryData;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageRecipe;
import vazkii.botania.common.lexicon.page.PageText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PageDisplay extends PageRecipe{
	
	ItemStack stack;
	int relativeMouseX, relativeMouseY;
	ItemStack tooltipStack, tooltipContainerStack;
	boolean tooltipEntry;

	static boolean mouseDownLastTick = false;

	public PageDisplay(String unlocalizedName, ItemStack stack) {
		super(unlocalizedName);
		this.stack = stack;
	}

	@Override
	public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
		relativeMouseX = mx;
		relativeMouseY = my;
		
		int width = gui.getWidth() - 30;
		int height = gui.getHeight();
		int x = gui.getLeft() + 16;
		int y = gui.getTop() + height - 40;
		
		renderItem(gui, x + 5, y * 0.4, stack, true);
		
		PageText.renderText(x, y, width, height, getUnlocalizedName());

		if(tooltipStack != null) {
			List<String> tooltipData = tooltipStack.getTooltip(Minecraft.getMinecraft().thePlayer, false);
			List<String> parsedTooltip = new ArrayList();
			boolean first = true;

			for(String s : tooltipData) {
				String s_ = s;
				if(!first)
					s_ = EnumChatFormatting.GRAY + s;
				parsedTooltip.add(s_);
				first = false;
			}

			vazkii.botania.client.core.helper.RenderHelper.renderTooltip(mx, my, parsedTooltip);

			int tooltipY = 8 + tooltipData.size() * 11;

			if(tooltipEntry) {
				vazkii.botania.client.core.helper.RenderHelper.renderTooltipOrange(mx, my + tooltipY, Arrays.asList(EnumChatFormatting.GRAY + StatCollector.translateToLocal("botaniamisc.clickToRecipe")));
				tooltipY += 18;
			}

			if(tooltipContainerStack != null)
				vazkii.botania.client.core.helper.RenderHelper.renderTooltipGreen(mx, my + tooltipY, Arrays.asList(EnumChatFormatting.AQUA + StatCollector.translateToLocal("botaniamisc.craftingContainer"), tooltipContainerStack.getDisplayName()));
		}

		tooltipStack = tooltipContainerStack = null;
		tooltipEntry = false;
		GL11.glDisable(GL11.GL_BLEND);
		mouseDownLastTick = Mouse.isButtonDown(0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderItem(IGuiLexiconEntry gui, double xPos, double yPos, ItemStack stack, boolean accountForContainer) {
		RenderItem render = new RenderItem();
		boolean mouseDown = Mouse.isButtonDown(0);

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glPushMatrix();
		GL11.glTranslated(xPos, yPos, 0);
		GL11.glScalef(6F, 6F, 6F);
		render.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, 0, 0);
		render.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, 0, 0);
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();

		int xpi = (int) xPos;
		int ypi = (int) yPos;
		if(relativeMouseX >= xpi && relativeMouseY >= ypi && relativeMouseX <= xpi + 16 && relativeMouseY <= ypi + 16) {
			tooltipStack = stack;

			EntryData data = LexiconRecipeMappings.getDataForStack(tooltipStack);
			ItemStack book = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();

			if(data != null && (data.entry != gui.getEntry() || data.page != gui.getPageOn()) && book != null && book.getItem() instanceof ILexicon && ((ILexicon) book.getItem()).isKnowledgeUnlocked(book, data.entry.getKnowledgeType())) {
				tooltipEntry = true;

				if(!mouseDownLastTick && mouseDown && GuiScreen.isShiftKeyDown()) {
					GuiLexiconEntry newGui = new GuiLexiconEntry(data.entry, (GuiScreen) gui);
					newGui.page = data.page;
					Minecraft.getMinecraft().displayGuiScreen(newGui);
				}
			} else tooltipEntry = false;

			if(accountForContainer) {
				ItemStack containerStack = stack.getItem().getContainerItem(stack);
				if(containerStack != null && containerStack.getItem() != null)
					tooltipContainerStack = containerStack;
			}
		}

		GL11.glDisable(GL11.GL_LIGHTING);
	}

}

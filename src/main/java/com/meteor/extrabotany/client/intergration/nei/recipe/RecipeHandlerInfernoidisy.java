package com.meteor.extrabotany.client.intergration.nei.recipe;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.lib.gui.GuiDraw;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeInfernoidisy;
import com.meteor.extrabotany.common.lib.LibBlockName;

public class RecipeHandlerInfernoidisy extends TemplateRecipeHandler {

	public class CachedPureDaisyRecipe extends CachedRecipe {

		public List<PositionedStack> inputs = new ArrayList<PositionedStack>();
		public PositionedStack output;
		public List<PositionedStack> otherStacks = new ArrayList<PositionedStack>();

		public CachedPureDaisyRecipe(RecipeInfernoidisy recipe) {
			if(recipe == null)
				return;
			inputs.add(new PositionedStack(ItemBlockSpecialFlower.ofType(LibBlockName.INFERNOIDISY), 71, 23));

			if(recipe.getInput() instanceof String)
				inputs.add(new PositionedStack(OreDictionary.getOres((String) recipe.getInput()), 42, 23));
			else inputs.add(new PositionedStack(new ItemStack((Block) recipe.getInput()), 42, 23));

			output = new PositionedStack(new ItemStack(recipe.getOutput()), 101, 23);
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, inputs);
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			return otherStacks;
		}

		@Override
		public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) {
			if(ingredients == inputs) {
				for(PositionedStack stack : ingredients)
					if(stack.contains(ingredient))
						return true;
			}

			return super.contains(ingredients, ingredient);
		}

	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("extrabotany.nei.infernoidisy");
	}

	@Override
	public String getGuiTexture() {
		return LibResources.GUI_NEI_BLANK;
	}

	@Override
	public int recipiesPerPage() {
		return 2;
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(70, 22, 18, 18), "botania.infernoidisy"));
	}

	@Override
	public void drawBackground(int recipe) {
		super.drawBackground(recipe);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GuiDraw.changeTexture(LibResources.GUI_PURE_DAISY_OVERLAY);
		GuiDraw.drawTexturedModalRect(45, 10, 0, 0, 65, 44);
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("botania.infernoidisy")) {
			for(RecipeInfernoidisy recipe : ExtraBotanyAPI.infernoidisyRecipes) {
				if(recipe == null)
					continue;

				arecipes.add(new CachedPureDaisyRecipe(recipe));
			}
		} else super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(RecipeInfernoidisy recipe : ExtraBotanyAPI.infernoidisyRecipes) {
			if(recipe == null)
				continue;

			if(NEIServerUtils.areStacksSameTypeCrafting(new ItemStack(recipe.getOutput()), result))
				arecipes.add(new CachedPureDaisyRecipe(recipe));
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for(RecipeInfernoidisy recipe : ExtraBotanyAPI.infernoidisyRecipes) {
			if(recipe == null)
				continue;

			CachedPureDaisyRecipe crecipe = new CachedPureDaisyRecipe(recipe);
			if(crecipe.contains(crecipe.getIngredients(), ingredient) || crecipe.contains(crecipe.getOtherStacks(), ingredient))
				arecipes.add(crecipe);
		}
	}
}

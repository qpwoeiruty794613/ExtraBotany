package com.meteor.extrabotany.client.intergration.nei.recipe;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibReference;

public class RecipeHandlerStonesia extends TemplateRecipeHandler {

	public class CachedStonesiaRecipe extends CachedRecipe {

		public List<PositionedStack> inputs = new ArrayList<PositionedStack>();
		public PositionedStack output;
		public int manaOutput;

		public CachedStonesiaRecipe(RecipeStonesia recipe) {
			if(recipe == null)
				return;
			inputs.add(new PositionedStack(ItemBlockSpecialFlower.ofType(LibBlockName.STONESIA), 71, 23));

			if(recipe.getInput() instanceof String)
				inputs.add(new PositionedStack(OreDictionary.getOres((String) recipe.getInput()), 42, 23));
			else inputs.add(new PositionedStack(new ItemStack((Block) recipe.getInput()), 42, 23));
			
			manaOutput = recipe.getMana() * ConfigHandler.efficiencyStonesia;
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
		return StatCollector.translateToLocal("extrabotany.nei.stonesia");
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
		transferRects.add(new RecipeTransferRect(new Rectangle(70, 22, 18, 18), "botania.stonesia"));
	}

	@Override
	public void drawBackground(int recipe) {
		super.drawBackground(recipe);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GuiDraw.changeTexture(LibReference.GUI_RECIPE_OVERLAY);
		GuiDraw.drawTexturedModalRect(45, 10, 0, 0, 65, 44);
		HUDHandler.renderManaBar(32, 45, 0x0000FF, 0.75F, ((CachedStonesiaRecipe) arecipes.get(recipe)).manaOutput, TilePool.MAX_MANA / 10);
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("botania.stonesia")) {
			for(RecipeStonesia recipe : ExtraBotanyAPI.stonesiaRecipes) {
				if(recipe == null)
					continue;

				arecipes.add(new CachedStonesiaRecipe(recipe));
			}
		} else super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(RecipeStonesia recipe : ExtraBotanyAPI.stonesiaRecipes) {
			if(recipe == null)
				continue;

			arecipes.add(new CachedStonesiaRecipe(recipe));
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for(RecipeStonesia recipe : ExtraBotanyAPI.stonesiaRecipes) {
			if(recipe == null)
				continue;

			CachedStonesiaRecipe crecipe = new CachedStonesiaRecipe(recipe);
			if(crecipe.contains(crecipe.getIngredients(), ingredient) || crecipe.contains(crecipe.getOtherStacks(), ingredient))
				arecipes.add(crecipe);
		}
	}
}

package com.meteor.extrabotany.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.TilePylon;

import com.meteor.extrabotany.client.lib.LibRenderID;
import com.meteor.extrabotany.client.render.tile.RenderTileAncientPylon;
import com.meteor.extrabotany.common.block.tile.TileAncientPylon;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderAncientPylon implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.7F, -0.5F);
		RenderTileAncientPylon.green = metadata == 1;
		RenderTileAncientPylon.pink = metadata == 2;
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileAncientPylon(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public int getRenderId() {
		return LibRenderID.idAncientPylon;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}

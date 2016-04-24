package com.meteor.extrabotany.common.integration.hugetools;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

import com.meteor.extrabotany.api.hugetools.HugeItemIcon;
import com.meteor.extrabotany.api.hugetools.HugeItemRenderer;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRender {
	public static void initHugeItemRender(){
		Minecraft mc = FMLClientHandler.instance().getClient();
		HugeItemRenderer hugeItemRenderer = new HugeItemRenderer(mc.gameSettings, mc.getTextureManager());
		MinecraftForgeClient.registerItemRenderer(ModItems.excaliber, hugeItemRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.excaliberfake, hugeItemRenderer);
		new HugeItemIcon(ModItems.excaliber.getIconFromDamage(0), 0.3125F, 0.1875F);
		new HugeItemIcon(ModItems.excaliberfake.getIconFromDamage(0), 0.3125F, 0.1875F);
	}
}

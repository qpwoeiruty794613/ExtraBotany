package com.meteor.extrabotany.common.integration.hugetools;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

import com.meteor.extrabotany.api.hugetools.HugeItemRenderer;
import com.meteor.extrabotany.client.render.item.GunRenderer;
import com.meteor.extrabotany.client.render.item.ItemIcon;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRender {
	public static void initHugeItemRender(){
		Minecraft mc = FMLClientHandler.instance().getClient();
		GunRenderer gunRenderer = new GunRenderer(mc.gameSettings, mc.getTextureManager());
		
		MinecraftForgeClient.registerItemRenderer(ModItems.excaliber, new HugeItemRenderer(mc.gameSettings, mc.getTextureManager(), 3.5F));
		MinecraftForgeClient.registerItemRenderer(ModItems.heliacalclaymore, new HugeItemRenderer(mc.gameSettings, mc.getTextureManager(), 2.0F));
		
		MinecraftForgeClient.registerItemRenderer(ModItems.gunphoenixblaster, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunboomstick, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunflintlock, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunpistol, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunshotgun, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.guntacticalshotgun, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.snowballlauncher, gunRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.theseusship, gunRenderer);
	}
}

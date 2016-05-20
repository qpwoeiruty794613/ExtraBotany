package com.meteor.extrabotany.common.integration.hugetools;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

import com.meteor.extrabotany.api.hugetools.HugeItemIcon;
import com.meteor.extrabotany.api.hugetools.HugeItemRenderer;
import com.meteor.extrabotany.client.render.item.GunRenderer;
import com.meteor.extrabotany.client.render.item.ItemIcon;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRender {
	public static void initHugeItemRender(){
		Minecraft mc = FMLClientHandler.instance().getClient();
		HugeItemRenderer hugeItemRenderer = new HugeItemRenderer(mc.gameSettings, mc.getTextureManager());
		GunRenderer gunRenderer = new GunRenderer(mc.gameSettings, mc.getTextureManager());
		
		MinecraftForgeClient.registerItemRenderer(ModItems.excaliber, hugeItemRenderer);
		new ItemIcon(ModItems.excaliber.getIconFromDamage(0), 1.2F, 0.6F);
		
		MinecraftForgeClient.registerItemRenderer(ModItems.gunphoenixblaster, gunRenderer);
		new ItemIcon(ModItems.gunphoenixblaster.getIconFromDamage(0), 1.2F, 0.6F);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunboomstick, gunRenderer);
		new ItemIcon(ModItems.gunboomstick.getIconFromDamage(0), 1.2F, 0.6F);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunflintlock, gunRenderer);
		new ItemIcon(ModItems.gunflintlock.getIconFromDamage(0), 1.2F, 0.6F);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunpistol, gunRenderer);
		new ItemIcon(ModItems.gunpistol.getIconFromDamage(0), 1.2F, 0.6F);
		MinecraftForgeClient.registerItemRenderer(ModItems.gunshotgun, gunRenderer);
		new ItemIcon(ModItems.gunshotgun.getIconFromDamage(0), 1.2F, 0.6F);
		MinecraftForgeClient.registerItemRenderer(ModItems.guntacticalshotgun, gunRenderer);
		new ItemIcon(ModItems.guntacticalshotgun.getIconFromDamage(0), 1.2F, 0.6F);
		MinecraftForgeClient.registerItemRenderer(ModItems.snowballlauncher, gunRenderer);
		new ItemIcon(ModItems.snowballlauncher.getIconFromDamage(0), 1.2F, 0.6F);
	}
}

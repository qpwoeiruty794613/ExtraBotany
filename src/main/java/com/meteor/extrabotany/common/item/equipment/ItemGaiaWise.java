package com.meteor.extrabotany.common.item.equipment;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.handler.EntityHandler;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ItemGaiaWise extends ItemBauble implements ICosmeticBauble{

	public ItemGaiaWise(String name) {
		super(name);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		this.setMaxStackSize(1);
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if(!player.worldObj.isRemote){
			if(getGaiaWise(player) != null){
				List<IMob> livings = player.worldObj.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - 2, player.posY - 2, player.posZ - 2, player.posX + 3, player.posY + 3, player.posZ + 3));
				for(IMob living:livings) {
					if(Math.random() < 0.14 && ManaItemHandler.requestManaExact(getGaiaWise(player), player, 1, true))
						EntityHandler.knockBack((EntityLiving)living, player, 2F, 2F);
				}
			}
		}
	}

	@Override
	public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event,
			RenderType type) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
		if(type != RenderType.HEAD){
			Helper.rotateIfSneaking(event.entityPlayer);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			GL11.glTranslatef(-0.5F, -0.8F, 0.15F);
			GL11.glScalef(0.255F, 0.255F, 0.255F);
			GL11.glTranslatef(0.9F, 1.9F, 0F);
			IIcon icon = this.getIconFromDamage(0);
			float f = icon.getMinU();
			float f1 = icon.getMaxU();
			float f2 = icon.getMinV();
			float f3 = icon.getMaxV();
			ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 16F);
		}
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}
	
	public static ItemStack getGaiaWise(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isGaiaWise(stack1) ? stack1 : isGaiaWise(stack2) ? stack2 : null;
	}

	private static boolean isGaiaWise(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.gaiawise);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
			return EntityGaiaIII.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, true);
	}

}

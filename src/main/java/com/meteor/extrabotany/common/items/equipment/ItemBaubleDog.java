package com.meteor.extrabotany.common.items.equipment;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderPlayerEvent;
import baubles.api.BaubleType;

import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.relauncher.SideOnly;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemBaubleDog extends ItemBauble implements ICosmeticBauble{
	private static final int SUBTYPES = 4;
	IIcon[] icons;
	public ItemBaubleDog() {
		super(LibItemName.BAUBLEDOG);
		setHasSubtypes(true);
	}
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[SUBTYPES];
		for(int i = 0; i < SUBTYPES; i++)
			icons[i] = IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < SUBTYPES; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public IIcon getIconFromDamage(int dmg) {
		return icons[Math.min(SUBTYPES - 1, dmg)];
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
	}


	@Override
	public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, RenderType type) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
			Helper.translateToHeadLevel(event.entityPlayer);
			if(type == RenderType.HEAD) {
				faceTranslate();
				scale(0.5F);
				GL11.glTranslatef(0.3F, 0.7F, 0.5F);
				renderIcon(stack.getItemDamage());
			}
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.AMULET;
	}
	
	public void faceTranslate() {
		GL11.glRotatef(90F, 0F, 1F, 0F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glTranslatef(-0.4F, 0.1F, -0.25F);
	}

	public void chestTranslate() {
		GL11.glRotatef(180F, 1F, 0F, 0F);
		GL11.glTranslatef(-0.5F, -0.7F, 0.15F);
	}

	public void scale(float f) {
		GL11.glScalef(f, f, f);
	}

	public void renderIcon(int i) {
		IIcon icon = icons[i];
		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 4F);
	}
}

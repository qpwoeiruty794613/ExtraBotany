package com.meteor.extrabotany.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpawnCardLycorisRed extends Item {

	String texture;

	public ItemSpawnCardLycorisRed(String texture)
	{
		this.texture = texture;
		this.maxStackSize = 16;
		setUnlocalizedName("ExtraBotany.SpawnCardLycorisdiataRed");
		setCreativeTab(ExtraBotany.tabExtraBotany);
		GameRegistry.registerItem(this, texture);
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(StatCollector.translateToLocal("item.ExtraBotany.SpawnLycorisRed.desc"));
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World world, EntityPlayer entityplayer) {
		if (!entityplayer.capabilities.isCreativeMode) {
			par1ItemStack.stackSize -= 1;
		}

		
		if (!world.isRemote) {
			EntityLycorisradiataRed entityspawning = new EntityLycorisradiataRed(world);
			entityspawning.setPosition(entityplayer.posX + 0.0D, entityplayer.posY + 0.0D, entityplayer.posZ + 0.0D);
			world.spawnEntityInWorld(entityspawning);
		}

		return par1ItemStack;
		}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 1;
	}	

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("extrabotany:" + this.texture);
	}
}

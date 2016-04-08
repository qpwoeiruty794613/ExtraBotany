package com.meteor.extrabotany.common.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpawnCardLycorisRandom extends Item {

	String texture;

	public ItemSpawnCardLycorisRandom(String texture)
	{
		this.texture = texture;
		this.maxStackSize = 16;
		setUnlocalizedName("ExtraBotany.SpawnCardLycorisdiataRandom");
		setCreativeTab(ExtraBotany.tabExtraBotany);
		GameRegistry.registerItem(this, texture);
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(StatCollector.translateToLocal("item.ExtraBotany.SpawnLycorisRandom.desc"));
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World world, EntityPlayer entityplayer) {
		if (!entityplayer.capabilities.isCreativeMode) {
			par1ItemStack.stackSize -= 1;
		}

		
		if (!world.isRemote) {
			EntityLycorisradiataGreen entityspawning = new EntityLycorisradiataGreen(world);
			EntityLycorisradiataRed entityspawning2 = new EntityLycorisradiataRed(world);
			EntityLycorisradiataPurple entityspawning3 = new EntityLycorisradiataPurple(world);
			Random rand = new Random();
			if(rand.nextInt(3) == 0){
				entityspawning.setPosition(entityplayer.posX + 0.0D, entityplayer.posY + 0.0D, entityplayer.posZ + 0.0D);
				world.spawnEntityInWorld(entityspawning);
			}
			if(rand.nextInt(3) == 1){
				entityspawning2.setPosition(entityplayer.posX + 0.0D, entityplayer.posY + 0.0D, entityplayer.posZ + 0.0D);
				world.spawnEntityInWorld(entityspawning2);
			}
			if(rand.nextInt(3) == 2){
				entityspawning3.setPosition(entityplayer.posX + 0.0D, entityplayer.posY + 0.0D, entityplayer.posZ + 0.0D);
				world.spawnEntityInWorld(entityspawning3);
			}

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

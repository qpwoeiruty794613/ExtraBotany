package com.meteor.extrabotany.common.item;

import java.util.List;

import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityEnderAirBottle;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import com.meteor.extrabotany.common.entity.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ItemGaianecklaceBroken extends ItemMods{
	public ItemGaianecklaceBroken(String name) {
		super(name);
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(StatCollector.translateToLocal("item.ExtraBotany.SpawnLycorisGreen.desc"));
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World world, EntityPlayer entityplayer) {
		if (!entityplayer.capabilities.isCreativeMode) {
			par1ItemStack.stackSize -= 1;
		}

		
		if (!world.isRemote) {
			int par4 = (int) entityplayer.posX;
			int par5 = (int) entityplayer.posY;
			int par6 = (int) entityplayer.posZ;
			EntityGaiaIII e = new EntityGaiaIII(world);
			e.setPosition(par4 + 0.5, par5 + 3, par6 + 0.5);
			e.setInvulTime(e.SPAWN_TICKS);
			e.setHealth(1F);
			e.setSource(par4, par5, par6);
			e.setMobSpawnTicks(e.MOB_SPAWN_TICKS);
			world.spawnEntityInWorld(e);
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

}

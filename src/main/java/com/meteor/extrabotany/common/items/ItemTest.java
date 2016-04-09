package com.meteor.extrabotany.common.items;

import java.util.List;

import com.meteor.extrabotany.common.effects.ModPotionEffect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityManaBurst;

public class ItemTest extends ItemMods{

	public ItemTest(String name) {
		super(name);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.removePotionEffect(ModPotionEffect.slowparticlesorting.getId());
		return par1ItemStack;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		list.add(StatCollector.translateToLocal("item.test.desc"));
	}

}

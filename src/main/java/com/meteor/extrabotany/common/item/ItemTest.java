package com.meteor.extrabotany.common.item;

import java.util.List;

import com.meteor.extrabotany.common.entity.Entity22;
import com.meteor.extrabotany.common.entity.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.EntityTV;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemTest extends ItemMods{

	public ItemTest(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		list.add(StatCollector.translateToLocal("item.test.desc"));
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
			return EntityGaiaIII.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, false);
	}

}

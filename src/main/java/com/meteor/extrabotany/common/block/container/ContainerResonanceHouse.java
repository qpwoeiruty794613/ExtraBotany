package com.meteor.extrabotany.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import com.meteor.extrabotany.common.block.tile.TileResonanceHouse;

public class ContainerResonanceHouse extends Container{
	
	public ContainerResonanceHouse(InventoryPlayer par1InventoryPlayer, TileResonanceHouse tileEntity){
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return false;
	}

}

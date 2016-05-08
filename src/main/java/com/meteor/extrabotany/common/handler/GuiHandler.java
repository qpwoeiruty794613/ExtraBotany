package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.meteor.extrabotany.client.gui.GuiResonanceHouse;
import com.meteor.extrabotany.client.lib.LibGuiID;
import com.meteor.extrabotany.common.block.container.ContainerResonanceHouse;
import com.meteor.extrabotany.common.block.tile.TileResonanceHouse;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            switch(ID)
            {
            case LibGuiID.GUI_RESONANCEHOUSE:
                    return new ContainerResonanceHouse(player.inventory, (TileResonanceHouse)world.getTileEntity(x,y,z));
            }
            return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
            switch(ID)
            {
            case LibGuiID.GUI_RESONANCEHOUSE:
                    return new GuiResonanceHouse(player.inventory, (TileResonanceHouse) world.getTileEntity(x,y,z));
            }
            return null;
    }
}

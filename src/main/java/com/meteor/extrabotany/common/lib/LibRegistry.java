package com.meteor.extrabotany.common.lib;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;
import com.meteor.extrabotany.api.extrabotany.handler.IDataHandler;

public class LibRegistry {
private static List<IDataHandler> fuelHandlers = Lists.newArrayList();
    
    public static void registerFuelHandler(IDataHandler handler)
    {
        fuelHandlers.add(handler);
    }
    public static int getFuelValue(ItemStack itemStack)
    {
        int fuelValue = 0;
        for (IDataHandler handler : fuelHandlers)
        {
            fuelValue = Math.max(fuelValue, handler.getData(itemStack));
        }
        return fuelValue;
    }
}

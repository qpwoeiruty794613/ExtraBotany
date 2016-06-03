package com.meteor.extrabotany.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventUpdateReward {
	@SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {

    }
}

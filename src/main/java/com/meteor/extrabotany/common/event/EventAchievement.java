package com.meteor.extrabotany.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibBlockName;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventAchievement {
	@SubscribeEvent
	public void onItemPickedUp(ItemPickupEvent event) {
		ItemStack stack = event.pickedUp.getEntityItem();
		EntityPlayer player = (EntityPlayer) event.player;
        if(stack != null){
        		
	    }
    }
}

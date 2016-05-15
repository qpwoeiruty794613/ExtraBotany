package com.meteor.extrabotany.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventPunish {
	
	@SubscribeEvent
	public void onPickedUp(ItemPickupEvent event) {
			ItemStack stack = event.pickedUp.getEntityItem();
			EntityPlayer player = event.player;
			String b = LibItemName.BINDING;
			ItemStack s1 = new ItemStack(ModItems.excaliber);
			ItemRelic.bindToUsernameS(b, s1);
			if(stack.isItemEqual(s1)){
				player.attackEntityFrom(ItemRelic.damageSource(), 9999F);
		}
	}
}

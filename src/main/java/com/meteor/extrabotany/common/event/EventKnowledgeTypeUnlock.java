package com.meteor.extrabotany.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.KnowledgeType;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventKnowledgeTypeUnlock implements ILexicon{

	@SubscribeEvent
	public void onItemPickedUp(ItemPickupEvent event) {
			ItemStack stack = event.pickedUp.getEntityItem();
			EntityPlayer player = (EntityPlayer) event.player;
            if(stack != null && stack == new ItemStack(ModItems.material, 1, 1)){
            	ItemStack stack1 = event.player.getHeldItem();
	            	if(stack1 != null && stack1.getItem() == vazkii.botania.common.item.ModItems.lexicon){
	      	 	        ILexicon l = (ILexicon) stack1.getItem();
	            		if(!l.isKnowledgeUnlocked(stack1, ExtraBotany.extraKnowledge)){
	            			l.unlockKnowledge(stack1, ExtraBotany.extraKnowledge);   
	            			player.addChatMessage(new ChatComponentTranslation("botaniamisc.knowledgeUnlock").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
	            		}
	            	}
	          	}
            }
	
	@Override
	public boolean isKnowledgeUnlocked(ItemStack stack, KnowledgeType knowledge) {
		return false;
	}

	@Override
	public void unlockKnowledge(ItemStack stack, KnowledgeType knowledge) {
		
	}
}

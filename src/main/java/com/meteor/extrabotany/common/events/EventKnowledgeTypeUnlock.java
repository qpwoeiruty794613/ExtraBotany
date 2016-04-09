package com.meteor.extrabotany.common.events;

import com.meteor.extrabotany.common.ExtraBotany;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.items.relic.ItemVRangerBoots;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.KnowledgeType;

public class EventKnowledgeTypeUnlock implements ILexicon{


	@SubscribeEvent
	public void onItemPickedUp(ItemPickupEvent event) {
			ItemStack stack = event.pickedUp.getEntityItem();
			EntityPlayer player = (EntityPlayer) event.player;
            if(stack != null && stack.getItem() == ModItems.blankcard){
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

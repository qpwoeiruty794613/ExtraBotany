package com.meteor.extrabotany.common.items.relic;

import java.awt.Button;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.items.ItemTeleportPearl;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemHermesTravelClothing extends ItemRelicArmorSet implements IManaUsingItem{
	public ItemHermesTravelClothing(String name) {
		super(1, LibItemName.HERMESTRAVELCLOTHING);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Armor modifier", 0.15, 1));
		return multimap;
	}
	
	@SubscribeEvent
	 public void PlayerHurtEvent(LivingHurtEvent event) { 
	        if(!(event.entity instanceof EntityPlayerMP)) {
	            return;
	        }
	        EntityPlayer player = (EntityPlayer) event.entity;
	        for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof ItemHermesTravelClothing) {
	            	if(ItemRelic.isRightPlayer(player, stack)){
	            	int rand = new Random().nextInt(3);
	            	if(rand == 2 && ManaItemHandler.requestManaExact(stack, player, 100, true)){
	            		event.setCanceled(true);
	            		}
	            	}
	            }
	        }
	 }

    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event) {
    	 EntityPlayer player = (EntityPlayer) event.entity;
	        for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof ItemHermesTravelClothing) {
	            	if(ItemRelic.isRightPlayer(player, stack))
	            	if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
	            		event.setCanceled(true);
	            	}
	            }
	        }
    }
    
    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent event) {
    	 EntityPlayer player = (EntityPlayer) event.player;
	        for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof ItemHermesTravelClothing) {
	            	if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
	            		if(ItemRelic.isRightPlayer(player, stack))
	            		player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 10, 0));
	            	}
	            }
	        }
    }
    
	@SubscribeEvent
	 public void PlayerAttackEvent(LivingAttackEvent event) { 
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
	        EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
	        for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof ItemHermesTravelClothing) {
	            	if(player.isPotionActive(Potion.invisibility.getId()))
	            		if(ItemRelic.isRightPlayer(player, stack))
	            		event.setCanceled(true);
	            }
	        }
	    }
	 }
	
	@Override
	public boolean usesMana(ItemStack stack) {
		return true;
	}

}

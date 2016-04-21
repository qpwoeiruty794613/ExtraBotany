package com.meteor.extrabotany.common.items.relic;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.events.EventKnowledgeTypeUnlock;
import com.meteor.extrabotany.common.events.EventShield;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemAthenaBless extends ItemRelicBauble implements IShieldHandler{
	public static List<String> damageNegations = new ArrayList();

	public ItemAthenaBless(){
		super(LibItemName.ATHENABLESS);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		damageNegations.add(ItemRelic.damageSource().getDamageType());
	}
	
	@SubscribeEvent
	public void onPlayerAttacked(LivingAttackEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(getAthenaBless(player) != null)
				if(ItemRelic.isRightPlayer(player, getAthenaBless(player)) && damageNegations.contains(event.source.damageType))
				event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			if(getAthenaBless(player) != null)
				if(ItemRelic.isRightPlayer(player, getAthenaBless(player)))
				if(getShieldAmount(player) < player.getMaxHealth()){
					if(getShieldAmount(player)+event.ammount/5 > player.getMaxHealth()){
						setShieldAmount(player.getMaxHealth(), player);
					}else{
						setShieldAmount(getShieldAmount(player) + event.ammount/5, player);
					}
				}
			}
	}
	

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public static ItemStack getAthenaBless(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isAthenaBless(stack1) ? stack1 : isAthenaBless(stack2) ? stack2 : null;
	}

	private static boolean isAthenaBless(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.athenabless);
	}

	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		ShieldHandler.currentShield = shield;
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
}

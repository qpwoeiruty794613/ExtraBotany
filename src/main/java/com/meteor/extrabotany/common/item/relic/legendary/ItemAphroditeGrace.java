package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class ItemAphroditeGrace extends ItemRelicArmorSet implements IShieldHandler{
	public ItemAphroditeGrace(int type, String name) {
		super(2, LibItemName.APHRODITEGRACE);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onPlayerAttacked(LivingHurtEvent event) {
        if(!(event.entity instanceof EntityPlayerMP)) {
            return;
        }	
        	boolean flag = false;
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			for(ItemStack stack : player.inventory.armorInventory) {
	            if(stack != null && stack.getItem() instanceof ItemAphroditeGrace) {
	            	if(ItemRelic.isRightPlayer(player, stack))
	            	if(event.ammount >= 6.0F){
	            		
	            		addShieldAmount(event.ammount/2, player);

	            		Collection<PotionEffect> potions = player.getActivePotionEffects();
	            		
	            		for (PotionEffect potion : potions) {
	    					int id = potion.getPotionID();
	    					if (ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})) {
	    						player.removePotionEffect(id);
	    						flag = true;
	    					}
	    					break;
	            	}
	            }
			}
		}
	}
	
	public static boolean hasAphroditeGrace(EntityPlayer player){
		boolean bool = false;
		for(ItemStack stack : player.inventory.armorInventory) {
            if(stack != null && stack.getItem() instanceof ItemAphroditeGrace) {
            	if(ItemRelic.isRightPlayer(player, stack)){
            		bool = true;
            		}else bool = false;
            	}	
		}
		return bool;
	}
	
	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
	}

}

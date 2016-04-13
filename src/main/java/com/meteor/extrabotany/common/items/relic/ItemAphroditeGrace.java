package com.meteor.extrabotany.common.items.relic;

import java.util.Collection;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.common.entity.EntityManaBurst;

public class ItemAphroditeGrace extends ItemRelicArmorSet{
	public ItemAphroditeGrace(String name) {
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
	            	if(event.ammount >= 4.0F){
	            		player.setAbsorptionAmount(player.getAbsorptionAmount() + 2.0F);

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

}

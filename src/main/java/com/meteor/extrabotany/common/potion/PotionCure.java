package com.meteor.extrabotany.common.potion;

import java.util.Collection;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lib.LibPotionEffectName;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class PotionCure extends PotionMods{
	
	public PotionCure() {
		super(ConfigHandler.idPotionC, LibPotionEffectName.CURE, false, 0xF39716, 4);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void LivingEvent(LivingEvent.LivingUpdateEvent event) { 
		if(event.entityLiving.isPotionActive(ModPotions.cure)){
			Collection<PotionEffect> potions = event.entityLiving.getActivePotionEffects();
			boolean flag = false;
			for (PotionEffect potion : potions) {
				int id = potion.getPotionID();
				if (ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})) {
					event.entityLiving.removePotionEffect(id);
					flag = true;
				}
				break;
			}
		}
	}
	
}

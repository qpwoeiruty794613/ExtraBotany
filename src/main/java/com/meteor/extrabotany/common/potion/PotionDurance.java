package com.meteor.extrabotany.common.potion;

import java.util.Collection;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lib.LibPotionEffectName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionDurance extends PotionMods{

	public PotionDurance() {
		super(ConfigHandler.idPotionD, LibPotionEffectName.DURANCE, false, 0x9C64A6, 3);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void LivingEvent(LivingEvent.LivingUpdateEvent event) { 
		if(event.entityLiving.isPotionActive(ModPotions.durance)){
			event.entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed).setBaseValue(0);;
		}
	}

}

package com.meteor.extrabotany.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.item.relic.legendary.ItemCronusPhantom;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionResidualPain extends PotionEffectMods{
	private int level;
	
	public PotionResidualPain(String name, ResourceLocation icon) {
		super(name, true, icon);
		MinecraftForge.EVENT_BUS.register(this);
	    FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int level) { 
			this.level = level;
	}
	
	@Override
	public boolean isReady(int duration, int level)
	{
		return true;
	}
	
	public int getLevel(){
		return level;
	}
	
	@SubscribeEvent
	 public void EntityHurtEvent(LivingHurtEvent event) { 
	        if(event.entityLiving.isPotionActive(PotionEffectMods.residualpain)){
	    		int levelnow = getLevel()+1;
	        	event.ammount += levelnow;
	        	event.entityLiving.removePotionEffect(PotionEffectMods.residualpain.getId());
	        	event.entityLiving.addPotionEffect(new PotionEffect(PotionEffectMods.residualpain.getId(), 100, levelnow));
	        }
	        
	        if(event.source.getSourceOfDamage() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
				if(ItemCronusPhantom.getCronusPhantom(player) != null)
					if(ItemRelic.isRightPlayer(player, ItemCronusPhantom.getCronusPhantom(player))){
							if(event.entityLiving.isPotionActive(PotionEffectMods.residualpain.getId()) == false){
								event.entityLiving.addPotionEffect(new PotionEffect(PotionEffectMods.residualpain.getId(), 100, 0));		
					}
							
				}
			}
		}
}

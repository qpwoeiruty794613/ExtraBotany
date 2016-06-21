package com.meteor.extrabotany.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibBlockName;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventAchievement{
	@SubscribeEvent
	public void onItemPickedUp(ItemPickupEvent event) {
		EntityItem e = event.pickedUp;
		ItemStack stack = event.pickedUp.getEntityItem();
		EntityPlayer player = (EntityPlayer) event.player;
        if(stack != null){
        	if(stack.getItem() == ModItems.material && stack.getItemDamage() == 1)
        		player.addStat(ModAchievement.O_blank, 1);
        	if(stack.getItem() instanceof ItemBlockSpecialFlower){
				String type = ItemBlockSpecialFlower.getType(stack);
				if(type.equals(LibBlockName.ANNOYOBLOOM))
						event.player.addStat(ModAchievement.F_annoy, 1);
				else if(type.equals(LibBlockName.ARTIFACONIA))
			    		event.player.addStat(ModAchievement.F_artifa, 1);
				else if(type.equals(LibBlockName.DIPLOPBAMBOO))
			   			event.player.addStat(ModAchievement.F_diplop, 1);
				else if(type.equals(LibBlockName.ICEBIRDIUM))
		    			event.player.addStat(ModAchievement.F_ice, 1);
				else if(type.equals(LibBlockName.LAUNCHISH))
			    		event.player.addStat(ModAchievement.F_launch, 1);
				else if(type.equals(LibBlockName.NECRO_FLUER))
			    		event.player.addStat(ModAchievement.F_necro, 1);
				else if(type.equals(LibBlockName.NUMERON_BALSAM))
			    		event.player.addStat(ModAchievement.F_numeb, 1);
				else if(type.equals(LibBlockName.NUMERON_DANDELIFE))
			    		event.player.addStat(ModAchievement.F_numed, 1);
				else if(type.equals(LibBlockName.VOIDUIM))
			    		event.player.addStat(ModAchievement.F_void, 1);
				else if(type.equals(LibBlockName.VOLATILILY))
			    		event.player.addStat(ModAchievement.F_vola, 1);
				else if(type.equals(LibBlockName.WOODIENIA))
			    		event.player.addStat(ModAchievement.F_woo, 1);
				else if(type.equals(LibBlockName.BLUE_ENCHANTRESS))
			    		event.player.addStat(ModAchievement.F_blue, 1);
				else if(type.equals(LibBlockName.CANDY_FLOWER))
			    		event.player.addStat(ModAchievement.F_candy, 1);
				else if(type.equals(LibBlockName.GEMINIORCHID))
			    		event.player.addStat(ModAchievement.F_gemini, 1);
				else if(type.equals(LibBlockName.MOONLIGHT_LILY))
			    		event.player.addStat(ModAchievement.F_moon, 1);
				else if(type.equals(LibBlockName.OMNIVIOLET))
			    		event.player.addStat(ModAchievement.F_omni, 1);
				else if(type.equals(LibBlockName.PYSCHOBLOOM))
			    		event.player.addStat(ModAchievement.F_pyscho, 1);
				else if(type.equals(LibBlockName.STONESIA))
			   			event.player.addStat(ModAchievement.F_stone, 1);
				else if(type.equals(LibBlockName.SUNSHINE_LILY))
			    		event.player.addStat(ModAchievement.F_sun, 1);
				else if(type.equals(LibBlockName.INFERNOIDISY))
			    		event.player.addStat(ModAchievement.F_infer, 1);
				else if(type.equals(LibBlockName.JUDASVOW))
			    		event.player.addStat(ModAchievement.F_judas, 1);
				else if(type.equals(LibBlockName.MANALINKUIM))
			    		event.player.addStat(ModAchievement.F_mana, 1);
			}
	    }
    }
}

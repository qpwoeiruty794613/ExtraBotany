package com.meteor.extrabotany.common.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import com.meteor.extrabotany.common.entity.EntityElven;
import com.meteor.extrabotany.common.handler.ElvenHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventElven {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityElven && ElvenHandler.get((EntityElven) event.entity) == null)
		if (event.entity instanceof EntityElven && event.entity.getExtendedProperties(ElvenHandler.TAG_ELVEN_ABILITY) == null)
		event.entity.registerExtendedProperties(ElvenHandler.TAG_ELVEN_ABILITY, new ElvenHandler((EntityElven) event.entity));
	}
}

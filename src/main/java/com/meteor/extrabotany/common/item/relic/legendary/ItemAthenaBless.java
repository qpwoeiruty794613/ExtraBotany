package com.meteor.extrabotany.common.item.relic.legendary;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.item.relic.ItemRelicBauble;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemAthenaBless extends ItemRelicBauble{

	public ItemAthenaBless(){
		super(LibItemName.ATHENABLESS);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItem entity = new EntityItemUnbreakable(world, location.posX, location.posY, location.posZ, itemstack);
		if(location instanceof EntityItem) {
		    entity.delayBeforeCanPickup = 40;
		}
		entity.motionX = location.motionX;
		entity.motionY = location.motionY;
		entity.motionZ = location.motionZ;
		return entity;
	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			if(getAthenaBless(player) != null)
				if(ItemRelic.isRightPlayer(player, getAthenaBless(player)))
					if(ItemHermesTravelClothing.hasHermesTravelClothing(player))
					PropertyHandler.addShieldAmount(event.ammount/5, player);
					else PropertyHandler.addShieldAmount(event.ammount/7, player);
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
		return stack != null && (stack.getItem() == ModItems.athenabless || stack.getItem() == ModItems.olympusguard);
	}
}

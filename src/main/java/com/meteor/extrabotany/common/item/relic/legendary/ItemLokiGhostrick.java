package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.item.relic.ItemRelicBauble;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemLokiGhostrick extends ItemRelicBauble{
	public static List<String> damageNegations = new ArrayList();
	public ItemLokiGhostrick(String name) {
		super(name);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		damageNegations.add(ItemRelic.damageSource().getDamageType());
		damageNegations.add(DamageSource.anvil.getDamageType());
		damageNegations.add(DamageSource.cactus.getDamageType());
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}
	
	@SubscribeEvent
	public void onPlayerAttacked(LivingAttackEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(getLokiGhostrick(player) != null)
				if(ItemRelic.isRightPlayer(player, getLokiGhostrick(player)) && damageNegations.contains(event.source.damageType))
				event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event) {
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			if(getLokiGhostrick(player) != null)
				if(ItemRelic.isRightPlayer(player, getLokiGhostrick(player))){
						if(player.worldObj.rand.nextInt(7) == 3)
							event.ammount *= 2;
					}
				}
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

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}
	
	public static ItemStack getLokiGhostrick(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isLokiGhostrick(stack1) ? stack1 : isLokiGhostrick(stack2) ? stack2 : null;
	}

	private static boolean isLokiGhostrick(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.lokighostrick || stack.getItem() == ModItems.olympusguard);
	}
}

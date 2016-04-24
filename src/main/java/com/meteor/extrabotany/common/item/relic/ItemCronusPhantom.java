package com.meteor.extrabotany.common.item.relic;

import com.meteor.extrabotany.common.effect.ModPotionEffect;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemCronusPhantom extends ItemRelicBauble{

	public ItemCronusPhantom(String name) {
		super(name);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}
	
	public static ItemStack getCronusPhantom(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isCronusPhantom(stack1) ? stack1 : isCronusPhantom(stack2) ? stack2 : null;
	}

	private static boolean isCronusPhantom(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.cronusphantom);
	}

}

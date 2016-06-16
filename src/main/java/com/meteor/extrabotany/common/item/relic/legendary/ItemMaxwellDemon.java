package com.meteor.extrabotany.common.item.relic.legendary;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.lib.LibObfuscation;

import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.potion.ModPotions;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class ItemMaxwellDemon extends ItemRelicAdv implements IManaUsingItem{
	
	public ItemMaxwellDemon(){
		super(LibItemName.MAXWELLDEMON);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(isRightPlayer(player, stack)){
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
		}
		return stack;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingTick(stack, player, count);

		if(ManaItemHandler.requestManaExact(stack, player, 10, true)) {
			if(count % 5 == 0)
			if(count == 5)
				if(!player.worldObj.isRemote){
					ReflectionHelper.setPrivateValue(EntityPlayer.class, player, 20, LibObfuscation.ITEM_IN_USE_COUNT);
					player.addPotionEffect(new PotionEffect(ModPotions.slowparticlesorting.getId(), 1200, 0));
					player.addPotionEffect(new PotionEffect(ModPotions.fastparticlesorting.getId(), 400, 0));
					if(player.getAbsorptionAmount() == 0F)
					player.setAbsorptionAmount(10F);
				}
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.eat;
	}

	@Override
	public boolean usesMana(ItemStack stack) {
		return true;
	}
}

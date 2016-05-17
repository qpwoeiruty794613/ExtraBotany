package com.meteor.extrabotany.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.lib.LibReference;
import com.meteor.extrabotany.common.potion.PotionEffectMods;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemPill extends ItemFood{

	public ItemPill(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_, String name) {
		super(p_i45339_1_, p_i45339_2_, p_i45339_3_);
		this.setUnlocalizedName(name)
		.setTextureName(LibReference.MOD_ID + ":" + name);
		GameRegistry.registerItem(this, name);
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
    {
		if(!player.worldObj.isRemote)
			player.addPotionEffect(new PotionEffect(PotionEffectMods.cure.getId(), 200, 0));
        	player.setHealth(player.getMaxHealth());
    }
	
}

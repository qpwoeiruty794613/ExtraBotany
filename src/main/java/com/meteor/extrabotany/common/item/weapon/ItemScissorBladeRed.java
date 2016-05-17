package com.meteor.extrabotany.common.item.weapon;

import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemScissorBladeRed extends ItemSword{

	public ItemScissorBladeRed(ToolMaterial p_i45356_1_, String name) {
		super(p_i45356_1_);	
		this
		.setUnlocalizedName(name)
		.setCreativeTab(ExtraBotany.tabExtraBotany)
		.setMaxStackSize(1)
		.setTextureName(LibReference.MOD_ID + ":" + name);
		GameRegistry.registerItem(this, name);	
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player)
    {
		if(target.isPotionActive(Potion.weakness)){
			target.removePotionEffect(Potion.weakness.getId());
			target.attackEntityFrom(ItemRelic.damageSource(), 12);
		}else{
			target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 100, 0));
		}
        return true;
    }
}

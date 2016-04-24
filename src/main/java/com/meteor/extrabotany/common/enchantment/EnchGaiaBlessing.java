package com.meteor.extrabotany.common.enchantment;

import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.common.entity.EntityDoppleganger;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.item.relic.ItemVRangerBoots;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EnchGaiaBlessing extends Enchantment implements IManaDiscountArmor{
    public EnchGaiaBlessing()
    {
        super(ConfigHandler.enchGaiaBlessing, 2,
                EnumEnchantmentType.armor);
        this.setName("gaiablessing");
        MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 5;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 20;
    }

    @Override
    public int getMaxLevel()
    {
        return 5;
    }
    
    ItemStack itemstack;
    @SubscribeEvent
    public void PlayerHurt(LivingHurtEvent event)
    {
    	if(!(event.entity instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) event.entity;
        for(ItemStack stack : player.inventory.armorInventory) {
        	this.itemstack = stack;
            if(stack != null && EnchantmentHelper.getEnchantmentLevel(ModEnchantment.gaiablessing.effectId, stack) > 0) {
            	if(event.source.getSourceOfDamage() instanceof EntityDoppleganger)
            		event.ammount *= Math.pow(0.88, EnchantmentHelper.getEnchantmentLevel(ModEnchantment.gaiablessing.effectId, stack)) ;
            	}
        	}
    	}
    
	@Override
	public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
		return (float) (0.02 * EnchantmentHelper.getEnchantmentLevel(ModEnchantment.gaiablessing.effectId, itemstack));
	}
}

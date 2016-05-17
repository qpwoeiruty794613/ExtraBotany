package com.meteor.extrabotany.common.item;

import java.util.List;

import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.entity.Entity22;
import com.meteor.extrabotany.common.entity.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.EntityGaiaIIIPhantom;
import com.meteor.extrabotany.common.entity.EntityTV;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemTest extends ItemMods{

	public ItemTest(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
		list.add(StatCollector.translateToLocal("item.test.desc"));
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player)
    {
		if (target instanceof EntityMob && ((EntityMob)target).getHeldItem() != null){
			if (!player.worldObj.isRemote){
				EntityItem item = new EntityItem(player.worldObj);
				ItemStack dropstack = ((EntityMob)target).getHeldItem().copy();
				if (dropstack.getMaxDamage() > 0)
					dropstack.setItemDamage((int)Math.floor(dropstack.getMaxDamage() * (0.8f + (player.worldObj.rand.nextFloat() * 0.19f))));
				item.setEntityItemStack(dropstack);
				item.setPosition(target.posX, target.posY, target.posZ);
				player.worldObj.spawnEntityInWorld(item);
			}
			((EntityMob)target).setCurrentItemOrArmor(0, null);
			((EntityMob)target).setAttackTarget(player);
		}
        return true;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		return stack;	
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
			return EntityGaiaIII.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, true);
	}

}

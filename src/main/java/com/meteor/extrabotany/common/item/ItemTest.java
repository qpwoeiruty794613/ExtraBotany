package com.meteor.extrabotany.common.item;

import java.util.List;

import vazkii.botania.client.core.helper.IconHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.entity.EntityElven;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTest extends ItemMods{
	public static IIcon spear;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		spear = IconHelper.forName(par1IconRegister, "spear");
	}

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
		target.setHealth(1F);
        return true;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote){
			EntityElven.spawn(player, player.posX, player.posY, player.posZ);
		}
		return stack;	
	}

}

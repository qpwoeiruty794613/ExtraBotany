package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityGaiaQuickened;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHeliacalClaymore extends ItemSword{
	
	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_HELIACAL", 3, -1, 6.2F, 4F, 20);
	
	public static String TAG_COOLDOWN = "cooldown";

	public ItemHeliacalClaymore() {
		super(toolMaterial);
		setUnlocalizedName(LibItemName.HELIACALCLAYMORE);
		setTextureName(LibReference.MOD_ID + ":" + LibItemName.HELIACALCLAYMORE);
		setCreativeTab(ExtraBotany.tabExtraBotany);
		GameRegistry.registerItem(this, LibItemName.HELIACALCLAYMORE);
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player){
		if(Math.random() > 0.84F && getCooldown(stack) == 0){
			EntityGaiaQuickened g = new EntityGaiaQuickened(player, false, 2F + toolMaterial.getDamageVsEntity());
			g.setPosition(target.posX, target.posY, target.posZ);
			setCooldown(stack, 200);
			player.worldObj.spawnEntityInWorld(g);
		}
		return target.attackEntityFrom(DamageSource.causeMobDamage(player), toolMaterial.getDamageVsEntity());
    }
	
	@Override
	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(getCooldown(stack) > 0)
			setCooldown(stack, getCooldown(stack)-1);
	}
	
	public int getCooldown(ItemStack stack){
		return ItemNBTHelper.getInt(stack, TAG_COOLDOWN, 0);
	}
	
	public void setCooldown(ItemStack stack, int i){
		ItemNBTHelper.setInt(stack, TAG_COOLDOWN, i);
	}

}

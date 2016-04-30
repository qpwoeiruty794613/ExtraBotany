package com.meteor.extrabotany.common.item.relic.legendary;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.effect.ModPotionEffect;
import com.meteor.extrabotany.common.lib.LibItemName;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemMaxwellDemon extends ItemRelic implements IManaUsingItem{
	public static IIcon dasBootIcon;
	
	public ItemMaxwellDemon(){
		super(LibItemName.MAXWELLDEMON);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.canEat(false) && isRightPlayer(player, stack)){
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
		}
		return stack;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingTick(stack, player, count);

		if(ManaItemHandler.requestManaExact(stack, player, 400, true)) {
			if(count % 5 == 0)
			if(count == 5)
				if(player.canEat(false))
					ReflectionHelper.setPrivateValue(EntityPlayer.class, player, 20, LibObfuscation.ITEM_IN_USE_COUNT);
					player.addPotionEffect(new PotionEffect(ModPotionEffect.slowparticlesorting.getId(), 1200, 0));
					player.addPotionEffect(new PotionEffect(ModPotionEffect.fastparticlesorting.getId(), 400, 0));
				if(player.getAbsorptionAmount() == 0F)
					player.setAbsorptionAmount(10F);
				
			
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return isBoot(stack) ? EnumAction.drink : EnumAction.eat;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = IconHelper.forItem(par1IconRegister, this);
		dasBootIcon = IconHelper.forName(par1IconRegister, "dasBoot");
	}

	@Override
	public IIcon getIconIndex(ItemStack par1ItemStack) {
		return isBoot(par1ItemStack) ? dasBootIcon : super.getIconIndex(par1ItemStack);
	}

	private boolean isBoot(ItemStack par1ItemStack) {
		String name = par1ItemStack.getDisplayName().toLowerCase().trim();
		return name.equals("das boot");
	}

	@Override
	public boolean usesMana(ItemStack stack) {
		return true;
	}
}

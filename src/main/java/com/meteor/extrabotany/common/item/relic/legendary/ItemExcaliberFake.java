package com.meteor.extrabotany.common.item.relic.legendary;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.common.item.relic.ItemRelic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import com.meteor.extrabotany.common.lib.LibItemName;

public class ItemExcaliberFake extends ItemRelicAdv{
	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_EXCALIBER", 3, -1, 6.2F, 6F, 40);
	public ItemExcaliberFake() {
		super(LibItemName.EXCALIBERFAKE);
	}
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			ItemRelic.updateRelic(par1ItemStack, player);
			if(ItemRelic.isRightPlayer(player, par1ItemStack)) {
				PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
				float check = haste == null ? 0.16666667F : haste.getAmplifier() == 1 ? 0.5F : 0.2F;			
			}
		}
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 10, 0));
		multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 0.3, 1));
		return multimap;
	}

}

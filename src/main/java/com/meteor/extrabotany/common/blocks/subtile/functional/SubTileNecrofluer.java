package com.meteor.extrabotany.common.blocks.subtile.functional;

import java.util.Collection;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.api.IShieldHandler;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class SubTileNecrofluer extends SubTileFunctional{
	private static final int RANGE = 1;
	private static String placerName;
	private static final int DELAY = 20;
	
	@Override
	public int getColor() {
		return 0x858585;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			this.placerName = player.getDisplayName();
			}		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();		
		if(redstoneSignal > 0)
			return;
		
		List<EntityLivingBase> livings = supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
		for(EntityLivingBase living : livings) {
			if(mana < 50)
				return;
			
				if(living instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) living;
					if(player.getDisplayName() == this.placerName)
						return;
				}
				
				if(living instanceof EntityLiving){
					if(ticksExisted % DELAY == 0){
						EntityLiving l = (EntityLiving) living;
							l.attackEntityFrom(ItemRelic.damageSource(), 5F);
							l.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 40 , 2));
							l.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 40 , 2));
							mana -= 40;
						}
					}
			}
		
	}
	
	@Override
	public int getMaxMana() {
		return 800;
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
}

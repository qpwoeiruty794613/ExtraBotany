package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.lexicon.LexiconModData;

public class SubTileNecrofluer extends SubTileFunctional{
	private static final int RANGE = 1;
	private static final int DELAY = 20;
	
	@Override
	public int getColor() {
		return 0x858585;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.necrofleur;
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
				
				if(living instanceof EntityLiving){
					if(ticksExisted % DELAY == 0){
						EntityLiving l = (EntityLiving) living;
							l.attackEntityFrom(ItemRelic.damageSource(), 5F);
							l.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40 , 2));
							l.addPotionEffect(new PotionEffect(Potion.blindness.id, 40 , 2));
							mana -= 25;
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

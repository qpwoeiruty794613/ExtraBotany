package com.meteor.extrabotany.common.blocks.subtile.functional;

import java.util.List;

import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.item.relic.ItemRelic;

public class SubTileArtifaconia extends SubTileFunctional{
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		boolean did = false;
		List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
		int slowdown = getSlowdownFactor();
		
		for(EntityItem item : items) {
			ItemStack stack = item.getEntityItem();
			if(stack != null) {
				if(items == ModItems.empty_dice)
					continue;
					if(mana >=150000 && stack.stackSize>0 && !supertile.getWorldObj().isRemote){
						stack.stackSize--;
						supertile.getWorldObj().spawnEntityInWorld(new EntityItem(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, new ItemStack(ModItems.dice20)));
						if(stack.stackSize == 0){
							item.setDead();
						}
						mana -= 150000;
						did = true;
						if(supertile.getWorldObj().isRemote) {
						item.setDead();
						}
					}
					if(items instanceof ItemRelic)
						continue;
						if(mana >=50000 && stack.stackSize>0 && !supertile.getWorldObj().isRemote){
							stack.stackSize--;
							supertile.getWorldObj().spawnEntityInWorld(new EntityItem(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, stack));
							if(stack.stackSize == 0){
								item.setDead();
							}
							mana -= 50000;
							did = true;
							if(supertile.getWorldObj().isRemote) {
							item.setDead();
							}
					
					
				}
				
		}

		if(did)
			sync();
		}
	}
	

	
	@Override
	public int getColor() {
		return 0x818181;
	}
	
	public int getDelay() {
		return 20;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}

	@Override
	public int getMaxMana() {
		return 200000;
	}

	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.blueenchantress;
	}
}

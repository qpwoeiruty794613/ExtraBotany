package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.BlockSpecialFlower;

public class SubTileLaunchish extends SubTileFunctional{
	
	private static final int RANGE = 0;
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		
		List<Entity> es = supertile.getWorldObj().getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - 0.5F, supertile.yCoord, supertile.zCoord - 0.5F, supertile.xCoord + 0.5F, supertile.yCoord + 2, supertile.zCoord + 0.5F));
		for(Entity e : es){
			if(e.motionY < 2 && mana > 10)
				e.motionY += (1.4F * (redstoneSignal * 0.02F+1));
				mana -=10;
		}
		
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public int getMaxMana() {
		return 200;
	}
	
}

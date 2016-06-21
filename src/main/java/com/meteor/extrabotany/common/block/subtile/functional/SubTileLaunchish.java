package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileLaunchish extends SubTileFunctional{
	
	private static final int RANGE = 0;
	
	@Override
	public int getColor() {
		return 0x22BF27;
	}
	
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

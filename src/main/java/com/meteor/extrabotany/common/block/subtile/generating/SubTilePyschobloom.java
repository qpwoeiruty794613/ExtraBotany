package com.meteor.extrabotany.common.block.subtile.generating;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTilePyschobloom extends SubTileGenerating{
	
	private static final int RANGE = 4;
	private static final int DELAY = 20;
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		List<EntityLivingBase> livings = supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
		if(linkedCollector != null)
			if(livings.size() > 0 && ticksExisted % DELAY == 0)
				mana += Math.min(livings.size()/3 + supertile.getWorldObj().rand.nextInt(3), 9);
	}
	
	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 10;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
	
	@Override
	public int getMaxMana() {
		return 500;
	}
	
}

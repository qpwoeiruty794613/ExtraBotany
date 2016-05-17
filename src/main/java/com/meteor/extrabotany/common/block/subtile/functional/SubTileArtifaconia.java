package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

public class SubTileArtifaconia extends SubTileFunctional{
	
	private static final int RANGE = 4;
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(mana == getMaxMana()) {
			int slowdown = getSlowdownFactor();
			List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
			for(EntityItem item : items) {
				if(item.age >= (59 + slowdown) && !item.isDead && item.getEntityItem().getItem() instanceof IRelic) {

					List<EntityPlayer> players = supertile.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
					for(EntityPlayer player : players) {
							IRelic r = (IRelic)item.getEntityItem().getItem();
								if(redstoneSignal == 15 && r.getSoulbindUsername(item.getEntityItem()) == player.getDisplayName()){
									EntityItem f = new EntityItemUnbreakable(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, new ItemStack(ModItems.material, 1, 11));
									f.delayBeforeCanPickup = 40;
									if(!supertile.getWorldObj().isRemote){
										supertile.getWorldObj().spawnEntityInWorld(f);
									}
									item.setDead();
								}else if(redstoneSignal == 0 && r.getSoulbindUsername(item.getEntityItem()) != player.getDisplayName()){
									if(((IRelic)item).getSoulbindUsername(item.getEntityItem()) != LibItemName.BINDING)
										r.bindToUsername(player.getDisplayName(), item.getEntityItem());
										mana-=getMaxMana();
							}
					}
				}
			}
		}
	}
	
	@Override
	public int getColor() {
		return 0x5AF4F6;
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public int getMaxMana() {
		return 10000;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
}

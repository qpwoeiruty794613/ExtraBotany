package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibMisc;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;

public class SubTileDiplopbamboo extends SubTileFunctional{
	
	private static final int RANGE = 1;
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.diplopbamboo;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
			int slowdown = getSlowdownFactor();
			List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
			if(items.size() == 1)
				for(EntityItem item:items){
					if(item.getEntityItem().stackSize == 1){
							if(item.age >= (59 + slowdown) && !item.isDead && !supertile.getWorldObj().isRemote){
									supertile.getWorldObj().func_147480_a(supertile.xCoord, supertile.yCoord, supertile.zCoord, false);
									EntityItem i = new EntityItem(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, item.getEntityItem().copy());
									supertile.getWorldObj().spawnEntityInWorld(i);
							}
						}	
					}
	}
	
	@Override
	public int getColor() {
		return 0x664422;
	}
	
	@Override
	public int getMaxMana() {
		return 6000;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
}

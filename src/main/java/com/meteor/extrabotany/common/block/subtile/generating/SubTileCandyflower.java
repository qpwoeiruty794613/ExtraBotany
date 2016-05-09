package com.meteor.extrabotany.common.block.subtile.generating;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.ModBlocks;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lib.LibData;

public class SubTileCandyflower extends SubTileGenerating{
	private static final String TAG_BURN_TIME = "burnTime";
	private static final int FUEL_CAP = 32000;
	private static final int RANGE = 3;
	
	int burnTime = 0;
	
	@Override
	public void onUpdate() {
		super.onUpdate();

		if(linkedCollector != null) {
			if(burnTime == 0) {
				if(mana < getMaxMana()) {
					boolean didSomething = false;

					int slowdown = getSlowdownFactor();
					
					List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
					for(EntityItem item : items) {
						if(item.age >= (59 + slowdown) && !item.isDead) {
							ItemStack stack = item.getEntityItem();
							if(stack.getItem().hasContainerItem(stack))
								continue;

							int burnTime = stack == null || stack.getItem() == Item.getItemFromBlock(ModBlocks.spreader) ? 0 : LibData.getCandyBurnTime(stack);
							if(burnTime > 0 && stack.stackSize > 0) {
								this.burnTime = Math.min(FUEL_CAP, burnTime) / 2;

								if(!supertile.getWorldObj().isRemote) {
									stack.stackSize--;
									supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "botania:endoflame", 0.2F, 1F);

									if(stack.stackSize == 0)
										item.setDead();

									didSomething = true;
								} else {
						            item.worldObj.spawnParticle("largesmoke", item.posX, item.posY + 0.1, item.posZ, 0.0D, 0.0D, 0.0D);
						            item.worldObj.spawnParticle("flame", item.posX, item.posY, item.posZ, 0.0D, 0.0D, 0.0D);
								}
								break;
							}
						}
					}

					if(didSomething)
						sync();
				}
			} else {
				if(supertile.getWorldObj().rand.nextInt(10) == 0)
		            supertile.getWorldObj().spawnParticle("flame", supertile.xCoord + 0.4 + Math.random() * 0.2, supertile.yCoord + 0.65, supertile.zCoord + 0.4 + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);

				burnTime--;
			}
		}
	}

	@Override
	public int getMaxMana() {
		return 500;
	}

	@Override
	public int getValueForPassiveGeneration() {
		return ConfigHandler.efficiencyCandyflower;
	}

	@Override
	public int getColor() {
		return 0x8BA500;
	}

	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}

	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.candyflower;
	}

	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		super.writeToPacketNBT(cmp);

		cmp.setInteger(TAG_BURN_TIME, burnTime);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		super.readFromPacketNBT(cmp);

		burnTime = cmp.getInteger(TAG_BURN_TIME);
	}

	@Override
	public boolean canGeneratePassively() {
		return burnTime > 0;
	}

	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 1;
	}
}

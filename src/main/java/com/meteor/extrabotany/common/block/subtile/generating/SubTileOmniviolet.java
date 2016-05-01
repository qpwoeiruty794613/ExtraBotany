package com.meteor.extrabotany.common.block.subtile.generating;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lib.LibData;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;

public class SubTileOmniviolet extends SubTileGenerating{
	private static final String TAG_BURN_TIME = "burnTime";
	private static final int FUEL_CAP = 32000;
	private static final int RANGE = 2;
	int burnTime = 0;
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.ominiviolet;
	}
	
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
							
							int burnTime = stack == null || stack.getItem() == Item.getItemFromBlock(ModBlocks.spreader) ? 0 : LibData.getBookBurnTime(stack);
							if(burnTime > 0 && stack.stackSize > 0) {
								this.burnTime = (int) (Math.min(FUEL_CAP, burnTime) * (1+bookCases() * 0.05));

								if(!supertile.getWorldObj().isRemote) {
									stack.stackSize--;
									Botania.proxy.sparkleFX(supertile.getWorldObj(), supertile.xCoord + 0.5F, supertile.yCoord +0.5F, supertile.zCoord + 0.5F, 1.99F, 0.97F, 0.20F, 3F, 10);
									supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "botania:endoflame", 0.2F, 1F);
									if(stack.stackSize == 0)
										item.setDead();
									
									didSomething = true;
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
	public float bookCases()
	{
	    float temp = 0F;
	    for (int j = -1; j <= 1; j++)
	    {
	      for (int k = -1; k <= 1; k++)
	      {
	        if (((j != 0) || (k != 0)) && (supertile.getWorldObj().isAirBlock(supertile.xCoord + k, supertile.yCoord, supertile.zCoord + j)) && 
	        		(supertile.getWorldObj().isAirBlock(supertile.xCoord + k, supertile.yCoord + 1, supertile.zCoord + j))) {
	          temp += ForgeHooks.getEnchantPower(supertile.getWorldObj(), supertile.xCoord + k * 2, supertile.yCoord, supertile.zCoord + j * 2);
	          temp += ForgeHooks.getEnchantPower(supertile.getWorldObj(), supertile.xCoord + k * 2, supertile.yCoord + 1, supertile.zCoord + j * 2);

	          if ((k != 0) && (j != 0))
	          {
	            temp += ForgeHooks.getEnchantPower(supertile.getWorldObj(), supertile.xCoord + k * 2, supertile.yCoord, supertile.zCoord + j);
	            temp += ForgeHooks.getEnchantPower(supertile.getWorldObj(), supertile.xCoord + k * 2, supertile.yCoord + 1, supertile.zCoord + j);
	            temp += ForgeHooks.getEnchantPower(supertile.getWorldObj(), supertile.xCoord + k, supertile.yCoord, supertile.zCoord + j * 2);
	            temp += ForgeHooks.getEnchantPower(supertile.getWorldObj(), supertile.xCoord + k, supertile.yCoord + 1, supertile.zCoord + j * 2);
	          }
	        }
	      }
	    }
	    return temp;
	}
	
	@Override
	public int getMaxMana() {
		return 1000;
	}

	@Override
	public int getValueForPassiveGeneration() {
		return 20;
	}

	@Override
	public int getColor() {
		return 0x93099A;
	}

	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
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

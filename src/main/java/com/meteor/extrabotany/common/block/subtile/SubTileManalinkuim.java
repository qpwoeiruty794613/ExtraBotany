package com.meteor.extrabotany.common.block.subtile;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.SubTileEntity;

public class SubTileManalinkuim extends SubTileEntity{
	
	public static final int RANGE = 24;

	private static final String TAG_MANA = "mana";

	private static final String TAG_COLLECTOR_X = "collectorX";
	private static final String TAG_COLLECTOR_Y = "collectorY";
	private static final String TAG_COLLECTOR_Z = "collectorZ";
	
	private static final String TAG_POOL_X = "poolX";
	private static final String TAG_POOL_Y = "poolY";
	private static final String TAG_POOL_Z = "poolZ";
	
	protected int mana;

	public int redstoneSignal = 0;

	int sizeLastCheck = -1;
	protected TileEntity linkedCollector = null;
	TileEntity linkedPool = null;
	public int knownMana = -1;
	
	ChunkCoordinates cachedCollectorCoordinates = null;
	ChunkCoordinates cachedPoolCoordinates = null;
	
	@Override
	public void onUpdate() {
		super.onUpdate();

		redstoneSignal = 0;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			int redstoneSide = supertile.getWorldObj().getIndirectPowerLevelTo(supertile.xCoord + dir.offsetX, supertile.yCoord + dir.offsetY, supertile.zCoord + dir.offsetZ, dir.ordinal());
			redstoneSignal = Math.max(redstoneSignal, redstoneSide);
		}
		
		linkCollector();
		linkPool();
		
		if(redstoneSignal == 0){
			if(linkedPool != null) {
				IManaPool pool = (IManaPool) linkedPool;
				int manaInPool = pool.getCurrentMana();
				int manaMissing = getMaxMana() - mana;
				int manaToRemove = Math.min(manaMissing, manaInPool);
				pool.recieveMana(-manaToRemove);
				addMana(manaToRemove);
			}
			emptyManaIntoCollector();
		}
		
		if(supertile.getWorldObj().isRemote) {
			double particleChance = 1F - (double) mana / (double) getMaxMana() / 3.5F;
			Color color = new Color(getColor());
			if(Math.random() > particleChance)
				BotaniaAPI.internalHandler.sparkleFX(supertile.getWorldObj(), supertile.xCoord + 0.3 + Math.random() * 0.5, supertile.yCoord + 0.5 + Math.random()  * 0.5, supertile.zCoord + 0.3 + Math.random() * 0.5, color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, (float) Math.random(), 5);
		}
		
	}
	
	public int getColor() {
		return 0x3356F0;
	}
	
	public int getMaxMana() {
		return 1000;
	}
	
	public void linkCollector() {
		boolean needsNew = false;
		if(linkedCollector == null) {
			needsNew = true;

			if(cachedCollectorCoordinates != null) {
				needsNew = false;
				if(supertile.getWorldObj().blockExists(cachedCollectorCoordinates.posX, cachedCollectorCoordinates.posY, cachedCollectorCoordinates.posZ)) {
					needsNew = true;
					TileEntity tileAt = supertile.getWorldObj().getTileEntity(cachedCollectorCoordinates.posX, cachedCollectorCoordinates.posY, cachedCollectorCoordinates.posZ);
					if(tileAt != null && tileAt instanceof IManaCollector && !tileAt.isInvalid()) {
						linkedCollector = tileAt;
						needsNew = false;
					}
					cachedCollectorCoordinates = null;
				}
			}
		} else {
			TileEntity tileAt = supertile.getWorldObj().getTileEntity(linkedCollector.xCoord, linkedCollector.yCoord, linkedCollector.zCoord);
			if(tileAt != null && tileAt instanceof IManaCollector)
				linkedCollector = tileAt;
		}

		if(needsNew && ticksExisted == 1) { // New flowers only
			IManaNetwork network = BotaniaAPI.internalHandler.getManaNetworkInstance();
			int size = network.getAllCollectorsInWorld(supertile.getWorldObj()).size();
			if(BotaniaAPI.internalHandler.shouldForceCheck() || size != sizeLastCheck) {
				ChunkCoordinates coords = new ChunkCoordinates(supertile.xCoord, supertile.yCoord, supertile.zCoord);
				linkedCollector = network.getClosestCollector(coords, supertile.getWorldObj(), RANGE);
				sizeLastCheck = size;
			}
		}
	}

	public void linkPool() {
		boolean needsNew = false;
		if(linkedPool == null) {
			needsNew = true;

			if(cachedPoolCoordinates != null) {
				needsNew = false;
				if(supertile.getWorldObj().blockExists(cachedPoolCoordinates.posX, cachedPoolCoordinates.posY, cachedPoolCoordinates.posZ)) {
					needsNew = true;
					TileEntity tileAt = supertile.getWorldObj().getTileEntity(cachedPoolCoordinates.posX, cachedPoolCoordinates.posY, cachedPoolCoordinates.posZ);
					if(tileAt != null && tileAt instanceof IManaPool && !tileAt.isInvalid()) {
						linkedPool = tileAt;
						needsNew = false;
					}
					cachedPoolCoordinates = null;
				}
			}
		} else {
			TileEntity tileAt = supertile.getWorldObj().getTileEntity(linkedPool.xCoord, linkedPool.yCoord, linkedPool.zCoord);
			if(tileAt != null && tileAt instanceof IManaPool)
				linkedPool = tileAt;
		}

		if(needsNew && ticksExisted == 1) { // Only for new flowers
			IManaNetwork network = BotaniaAPI.internalHandler.getManaNetworkInstance();
			int size = network.getAllPoolsInWorld(supertile.getWorldObj()).size();
			if(BotaniaAPI.internalHandler.shouldForceCheck() || size != sizeLastCheck) {
				ChunkCoordinates coords = new ChunkCoordinates(supertile.xCoord, supertile.yCoord, supertile.zCoord);
				linkedPool = network.getClosestPool(coords, supertile.getWorldObj(), RANGE);
				sizeLastCheck = size;
			}
		}
	}
	
	public void addMana(int mana) {
		this.mana = Math.min(getMaxMana(), this.mana + mana);
	}

	public void emptyManaIntoCollector() {
		if(linkedCollector != null && isValidBinding()) {
			IManaCollector collector = (IManaCollector) linkedCollector;
			if(!collector.isFull() && mana > 0) {
				int manaval = Math.min(mana, collector.getMaxMana() - collector.getCurrentMana());
				mana -= manaval;
				collector.recieveMana(manaval);
			}
		}
	}

	@Override
	public boolean onWanded(EntityPlayer player, ItemStack wand) {
		if(player == null)
			return false;

		if(!player.worldObj.isRemote)
			sync();

		knownMana = mana;
		player.worldObj.playSoundAtEntity(player, "botania:ding", 0.1F, 1F);

		return super.onWanded(player, wand);
	}
	
	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		mana = cmp.getInteger(TAG_MANA);

		int x = cmp.getInteger(TAG_COLLECTOR_X);
		int y = cmp.getInteger(TAG_COLLECTOR_Y);
		int z = cmp.getInteger(TAG_COLLECTOR_Z);

		cachedCollectorCoordinates = y < 0 ? null : new ChunkCoordinates(x, y, z);
		
		int px = cmp.getInteger(TAG_POOL_X);
		int py = cmp.getInteger(TAG_POOL_Y);
		int pz = cmp.getInteger(TAG_POOL_Z);

		cachedPoolCoordinates = py < 0 ? null : new ChunkCoordinates(px, py, pz);
	}

	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		cmp.setInteger(TAG_MANA, mana);
		cmp.setInteger(TAG_TICKS_EXISTED, ticksExisted);

		if(cachedCollectorCoordinates != null) {
			cmp.setInteger(TAG_COLLECTOR_X, cachedCollectorCoordinates.posX);
			cmp.setInteger(TAG_COLLECTOR_Y, cachedCollectorCoordinates.posY);
			cmp.setInteger(TAG_COLLECTOR_Z, cachedCollectorCoordinates.posZ);
		} else {
			int x = linkedCollector == null ? 0 : linkedCollector.xCoord;
			int y = linkedCollector == null ? -1 : linkedCollector.yCoord;
			int z = linkedCollector == null ? 0 : linkedCollector.zCoord;

			cmp.setInteger(TAG_COLLECTOR_X, x);
			cmp.setInteger(TAG_COLLECTOR_Y, y);
			cmp.setInteger(TAG_COLLECTOR_Z, z);
		}
		
		if(cachedPoolCoordinates != null) {
			cmp.setInteger(TAG_POOL_X, cachedPoolCoordinates.posX);
			cmp.setInteger(TAG_POOL_Y, cachedPoolCoordinates.posY);
			cmp.setInteger(TAG_POOL_Z, cachedPoolCoordinates.posZ);
		} else {
			int x = linkedPool == null ? 0 : linkedPool.xCoord;
			int y = linkedPool == null ? -1 : linkedPool.yCoord;
			int z = linkedPool == null ? 0 : linkedPool.zCoord;

			cmp.setInteger(TAG_POOL_X, x);
			cmp.setInteger(TAG_POOL_Y, y);
			cmp.setInteger(TAG_POOL_Z, z);
		}
	}

	@Override
	public ChunkCoordinates getBinding() {
		if(linkedCollector == null)
			return null;
		return new ChunkCoordinates(linkedCollector.xCoord, linkedCollector.yCoord, linkedCollector.zCoord);
	}

	@Override
	public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
		return true;
	}

	@Override
	public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
		int range = 24;
		range *= range;

		double dist = (x - supertile.xCoord) * (x - supertile.xCoord) + (y - supertile.yCoord) * (y - supertile.yCoord) + (z - supertile.zCoord) * (z - supertile.zCoord);
		if(range >= dist) {
			TileEntity tile = player.worldObj.getTileEntity(x, y, z);
			if(tile instanceof IManaCollector) {
				linkedCollector = tile;
				return true;
			}
		}

		return false;
	}


	public boolean isValidBinding() {
		return linkedCollector != null && !linkedCollector.isInvalid() && supertile.getWorldObj().getTileEntity(linkedCollector.xCoord, linkedCollector.yCoord, linkedCollector.zCoord) == linkedCollector;
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res) {
		String name = StatCollector.translateToLocal("tile.botania:flower." + getUnlocalizedName() + ".name");
		int color = getColor();
		BotaniaAPI.internalHandler.drawComplexManaHUD(color, knownMana, getMaxMana(), name, res, BotaniaAPI.internalHandler.getBindDisplayForFlowerType(this), isValidBinding());
	}

	@Override
	public boolean isOvergrowthAffected() {
		return true;
	}
	
}

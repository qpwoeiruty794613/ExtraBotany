package com.meteor.extrabotany.common.block.tile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.core.handler.ManaNetworkHandler;

public class TileManaTinyPotato extends TileTinyPotato implements IManaPool, ISparkAttachable, IThrottledPacket{

	public static final int MAX_MANA = 20000;
	
	private static final String TAG_MANA = "mana";
	private static final String TAG_OUTPUTTING = "outputting";
	private static final String TAG_MANA_CAP = "manaCap";
	
	int mana;
	int soundTicks = 0;
	public int manaCap = -1;
	public boolean isDoingTransfer = false;
	public int ticksDoingTransfer = 0;
	int ticks = 0;
	
	boolean outputting = false;
	boolean sendPacket = false;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		boolean wasDoingTransfer = isDoingTransfer;
		isDoingTransfer = false;
		
		if(!ManaNetworkHandler.instance.isPoolIn(this) && !isInvalid())
			ManaNetworkEvent.addPool(this);

		if(soundTicks > 0)
			soundTicks--;
		
		if(sendPacket && ticks % 10 == 0) {
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
			sendPacket = false;
		}
		
		if(isDoingTransfer)
			ticksDoingTransfer++;
		else {
			ticksDoingTransfer = 0;
			if(wasDoingTransfer)
				VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
		}

		ticks++;
	}

	@Override
	public boolean canRecieveManaFromBursts() {
		return true;
	}

	@Override
	public boolean isFull() {
		Block blockBelow = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
		return blockBelow != ModBlocks.manaVoid && getCurrentMana() >= manaCap;
	}

	@Override
	public void recieveMana(int arg0) {
		this.mana = Math.max(0, Math.min(getCurrentMana() + mana, manaCap));
		worldObj.func_147453_f(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
		markDispatchable();
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		ManaNetworkEvent.removePool(this);
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		invalidate();
	}


	@Override
	public int getCurrentMana() {
		return mana;
	}

	@Override
	public void markDispatchable() {
		sendPacket = true;
	}

	@Override
	public boolean areIncomingTranfersDone() {
		return false;
	}

	@Override
	public void attachSpark(ISparkEntity arg0) {
		
	}

	@Override
	public boolean canAttachSpark(ItemStack arg0) {
		return true;
	}

	@Override
	public ISparkEntity getAttachedSpark() {
		List<ISparkEntity> sparks = worldObj.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1));
		if(sparks.size() == 1) {
			Entity e = (Entity) sparks.get(0);
			return (ISparkEntity) e;
		}

		return null;
	}

	@Override
	public int getAvailableSpaceForMana() {
		return Math.max(0, manaCap - getCurrentMana());
	}

	@Override
	public boolean isOutputtingPower() {
		return outputting;
	}
	
	@Override
	public void writeCustomNBT(NBTTagCompound cmp) {
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_MANA, mana);
		cmp.setBoolean(TAG_OUTPUTTING, outputting);

		cmp.setInteger(TAG_MANA_CAP, manaCap);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp) {
		super.readCustomNBT(cmp);
		mana = cmp.getInteger(TAG_MANA);
		outputting = cmp.getBoolean(TAG_OUTPUTTING);

		if(cmp.hasKey(TAG_MANA_CAP))
			manaCap = cmp.getInteger(TAG_MANA_CAP);
	}
}

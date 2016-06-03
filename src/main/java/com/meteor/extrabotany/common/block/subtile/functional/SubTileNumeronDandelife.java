package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

public class SubTileNumerondandelife extends SubTileFunctional{
	private static final int RANGE = 3;
	private static int isEnable = 0;
	private static final String TAG_ISENABLE = "enable";
	private static final int DELAY = 20;
	
	@Override
	public int getColor() {
		return 0x07C527;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.numerondandelife;
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
		player.addChatMessage(new ChatComponentTranslation("+1s").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();		
		if(redstoneSignal > 0)
			return;
		
		if(supertile.xCoord % 6 == 0 && supertile.zCoord % 6 == 0)
			isEnable = 1;
		else isEnable = 0;
		
		if(isEnable == 0)
			return;
		
		List<EntityPlayer> players = supertile.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
		for(EntityPlayer player : players) {
			if(mana > 1 && PropertyHandler.getShieldAmount(player) < PropertyHandler.getMaxShieldAmount(player)){
				if(ticksExisted % DELAY == 0){
					mana -= 20;
					PropertyHandler.addShieldAmount(3F, player);
				}
			}
		}	
	}
	
	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		super.writeToPacketNBT(cmp);
		cmp.setInteger(TAG_ISENABLE, isEnable);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		super.readFromPacketNBT(cmp);
		isEnable = cmp.getInteger(TAG_ISENABLE);
	}
	
	@Override
	public int getMaxMana() {
		return 400;
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}

}

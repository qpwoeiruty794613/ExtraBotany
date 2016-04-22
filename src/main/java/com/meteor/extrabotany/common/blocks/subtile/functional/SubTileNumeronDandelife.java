package com.meteor.extrabotany.common.blocks.subtile.functional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class SubTileNumeronDandelife extends SubTileFunctional implements IShieldHandler{
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
			
				if(mana > 1 && getShieldAmount(player) < getMaxShieldAmount(player)){
					if(ticksExisted % DELAY == 0){
						mana -= 30;
						addShieldAmount(3F, player);
						}
					}
				
				Collection<PotionEffect> potions = player.getActivePotionEffects();
				boolean flag = false;
        		for (PotionEffect potion : potions) {
					int id = potion.getPotionID();
					if (ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})) {
						player.removePotionEffect(id);
						flag = true;
					}
					break;
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

	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
	}
}

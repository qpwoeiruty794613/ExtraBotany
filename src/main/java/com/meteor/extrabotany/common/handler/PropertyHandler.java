package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.meteor.extrabotany.common.CommonProxy;
import com.meteor.extrabotany.common.lib.LibReference;

public class PropertyHandler implements IExtendedEntityProperties{
	
	private static EntityPlayer player;
	
	public static void setPlayer(EntityPlayer p){
		player = p;
	}

	public static void setShieldAmount(float shield, EntityPlayer p) {
		setPlayer(p);
		player.getDataWatcher().updateObject(26, (int)(Math.min(shield, getMaxShieldAmount(player)))); 
	}

	public static float getShieldAmount(EntityPlayer p) {
		setPlayer(p);
		return player.getDataWatcher().getWatchableObjectInt(26);
	}

	public static void addShieldAmount(float shield, EntityPlayer p) {
		setPlayer(p);
		player.getDataWatcher().updateObject(26, (int)(Math.min(getShieldAmount(player) + shield, getMaxShieldAmount(player))));
	}

	public static float getMaxShieldAmount(EntityPlayer p) {
		setPlayer(p);
		player.getDataWatcher().updateObject(27, (int)(player.getMaxHealth() + ConfigHandler.extraShieldAmount));
		return player.getDataWatcher().getWatchableObjectInt(27);
	}

	public final static String EXT_PROP_NAME = "Shield";
	public final static String TAG_CURRENT_SHIELD = "CurrentShield";
	public final static String TAG_MAX_SHIELD = "MaxShield";
	public static float currentShield;
	public static float maxShield;

	public PropertyHandler(EntityPlayer player)
	{	
		this.player = player;
		player.getDataWatcher().addObject(26, 0);
		player.getDataWatcher().addObject(27, 0);
		this.currentShield = 0;
		this.maxShield = 20;
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PropertyHandler.EXT_PROP_NAME, new PropertyHandler(player));
	}
	
	public static final PropertyHandler get(EntityPlayer player)
	{
		return (PropertyHandler) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger(this.TAG_CURRENT_SHIELD, (int)(getShieldAmount(this.player)));
		properties.setInteger(this.TAG_MAX_SHIELD, (int)(getMaxShieldAmount(this.player)));
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.player.getDataWatcher().updateObject(26, properties.getInteger(this.TAG_CURRENT_SHIELD));
		this.player.getDataWatcher().updateObject(27, properties.getInteger(this.TAG_MAX_SHIELD));
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}
	
	private static String getSaveKey(EntityPlayer player) {
		return player.getDisplayName() + ":" + EXT_PROP_NAME;
	}
	
	public static void saveProxyData(EntityPlayer player) {
		PropertyHandler playerData = PropertyHandler.get(player);
		NBTTagCompound savedData = new NBTTagCompound();

		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}
	
	public static void loadProxyData(EntityPlayer player) {
		PropertyHandler playerData = PropertyHandler.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

		if(savedData != null) {
			playerData.loadNBTData(savedData);
		}
	}

}

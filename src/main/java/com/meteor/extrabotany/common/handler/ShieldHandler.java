package com.meteor.extrabotany.common.handler;

import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.events.EventShield;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ShieldHandler implements IShieldHandler, IExtendedEntityProperties{

	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
		this.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
		this.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return this.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
		this.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
		this.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		this.maxShield = player.getMaxHealth() + ConfigHandler.extraShieldAmount;
		return this.maxShield;
	}

	public final static String EXT_PROP_NAME = "Shield";
	private final EntityPlayer player;
	public static float currentShield = 0F;
	public static float maxShield = 0F;

	public ShieldHandler(EntityPlayer player)
	{
	this.player = player;
	this.currentShield = 0F;
	this.maxShield = 0F;
	}

	public static final void register(EntityPlayer player)
	{
	player.registerExtendedProperties(ShieldHandler.EXT_PROP_NAME, new ShieldHandler(player));
	}
	
	public static final ShieldHandler get(EntityPlayer player)
	{
	return (ShieldHandler) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
	NBTTagCompound properties = new NBTTagCompound();

	properties.setInteger("CurrentShield", (int) this.currentShield);
	properties.setInteger("MaxShield", (int) this.maxShield);
	compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
	NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
	this.currentShield = properties.getInteger("CurrentShield");
	this.maxShield = properties.getInteger("MaxShield");
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}

}

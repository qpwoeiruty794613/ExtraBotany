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
		this.currentShield = shield;
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return this.currentShield;
	}

	public final static String EXT_PROP_NAME = "Shield";
	private final EntityPlayer player;
	public static float currentShield = 0F;

	public ShieldHandler(EntityPlayer player)
	{
	this.player = player;
	this.currentShield = 0F;
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
	compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
	NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
	this.currentShield = properties.getInteger("CurrentShield");
	System.out.println("[TUT PROPS] Mana from NBT: " + this.currentShield);
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}

}

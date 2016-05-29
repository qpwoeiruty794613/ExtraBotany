package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ShieldHandler implements IExtendedEntityProperties{

	public static void setShieldAmount(float shield, EntityPlayer player) {
		ShieldHandler sh = ShieldHandler.get(player);
		sh.currentShield = Math.min(shield, getMaxShieldAmount(player));
	}

	public static float getShieldAmount(EntityPlayer player) {
		ShieldHandler sh = ShieldHandler.get(player);
		return sh.currentShield;
	}

	public static void addShieldAmount(float shield, EntityPlayer player) {
		ShieldHandler sh = ShieldHandler.get(player);
		sh.currentShield = Math.min(getShieldAmount(player) + shield, getMaxShieldAmount(player));
	}

	public static float getMaxShieldAmount(EntityPlayer player) {
		ShieldHandler sh = ShieldHandler.get(player);
		sh.maxShield = Math.min(player.getMaxHealth() + ConfigHandler.extraShieldAmount, 100F);
		return sh.maxShield;
	}

	public final static String EXT_PROP_NAME = "Shield";
	public final static String TAG_CURRENT_SHIELD = "CurrentShield";
	public final static String TAG_MAX_SHIELD = "MaxShield";
	private static EntityPlayer player;
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
	
		properties.setInteger(this.TAG_CURRENT_SHIELD, (int) this.currentShield);
		properties.setInteger(this.TAG_MAX_SHIELD, (int) this.maxShield);
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.currentShield = properties.getInteger(this.TAG_CURRENT_SHIELD);
		this.maxShield = properties.getInteger(this.TAG_MAX_SHIELD);
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}

}

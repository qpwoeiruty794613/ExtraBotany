package com.meteor.extrabotany.common.handler;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.meteor.extrabotany.common.entity.EntityElven;

public class ElvenHandler implements IExtendedEntityProperties{
	
	public static float extraATK = 0F;
	public static float extraASPD = 0F;
	public static float extraRange = 0;
	
	public static boolean skillMissile = false;
	public static boolean skillLightning = false;
	public static boolean skillSpear = false;
	
	public static int levelSkill = 0;
	
	public static double lastPosX = 0;
	public static double lastPosY = 0;
	public static double lastPosZ = 0;
	
	public static final String TAG_ELVEN_ABILITY = "elven";
	
	private static final String TAG_EXTRA_ATK = "extra_ATK";
	private static final String TAG_EXTRA_ASPD = "extra_ASPD";
	private static final String TAG_EXTRA_RANGE = "extra_RANGE";
	private static final String TAG_SKILL_MISSILE = "skill_missile";
	private static final String TAG_SKILL_LIGHTNING = "skill_lightning";
	private static final String TAG_SKILL_SPEAR = "skill_spear";
	private static final String TAG_LEVEL_SKILL = "level_skill";
	private static final String TAG_LASTPOS_X = "lastpos_x";
	private static final String TAG_LASTPOS_Y = "lastpos_y";
	private static final String TAG_LASTPOS_Z = "lastpos_z";
	
	private static EntityElven elven;
	public ElvenHandler(EntityElven e){
		this.elven = e;
		this.extraATK = 0F;
		this.extraASPD = 0F;
		this.skillMissile = false;
		this.skillLightning = false;
		this.skillSpear = false;
		this.levelSkill = 0;
		this.lastPosX = 0;
		this.lastPosZ = 0;
		this.lastPosY = 0;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setFloat(TAG_EXTRA_ATK, this.extraATK);
		properties.setFloat(TAG_EXTRA_ASPD, this.extraASPD);
		properties.setFloat(TAG_EXTRA_RANGE, this.extraRange);
		
		properties.setBoolean(TAG_SKILL_LIGHTNING, this.skillLightning);
		properties.setBoolean(TAG_SKILL_SPEAR, this.skillSpear);
		properties.setBoolean(TAG_SKILL_MISSILE, this.skillMissile);
		
		properties.setInteger(TAG_LEVEL_SKILL, this.levelSkill);
		
		properties.setDouble(TAG_LASTPOS_X, this.lastPosX);
		properties.setDouble(TAG_LASTPOS_Y, this.lastPosY);
		properties.setDouble(TAG_LASTPOS_Z, this.lastPosZ);
		compound.setTag(TAG_ELVEN_ABILITY, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(TAG_ELVEN_ABILITY);
		this.extraASPD = properties.getFloat(TAG_EXTRA_ASPD);
		this.extraRange = properties.getFloat(TAG_EXTRA_RANGE);
		this.extraATK = properties.getFloat(TAG_EXTRA_ATK);
		
		this.skillLightning = properties.getBoolean(TAG_SKILL_LIGHTNING);
		this.skillMissile = properties.getBoolean(TAG_SKILL_MISSILE);
		this.skillSpear = properties.getBoolean(TAG_SKILL_SPEAR);
		
		this.levelSkill = properties.getInteger(TAG_LEVEL_SKILL);
		
		this.lastPosX = properties.getDouble(TAG_LASTPOS_X);
		this.lastPosY = properties.getDouble(TAG_LASTPOS_Y);
		this.lastPosZ = properties.getDouble(TAG_LASTPOS_Z);
	}

	@Override
	public void init(Entity entity, World world) {
		
	}
	
	public static final void register(EntityElven e)
	{
		e.registerExtendedProperties(ElvenHandler.TAG_ELVEN_ABILITY, new ElvenHandler(e));
	}
	
	public static final ElvenHandler get(EntityElven e)
	{
		return (ElvenHandler) e.getExtendedProperties(TAG_ELVEN_ABILITY);
	}
	
	public static float getExtraATK(){
		return extraATK;
	}
	
	public static float getExtraASPD(){
		return extraASPD;
	}
	
	public static float getExtraRange(){
		return extraRange;
	}
	
	public static boolean isMissileLearned(){
		return skillMissile;
	}
	
	public static boolean isLightningLearned(){
		return skillLightning;
	}
	
	public static boolean isSpearLearned(){
		return skillSpear;
	}
	
	public static int getSkillLevel(){
		return levelSkill;
	}
	
	public static double getLastPosX(){
		return lastPosX;
	}
	
	public static double getLastPosY(){
		return lastPosY;
	}
	
	public static double getLastPosZ(){
		return lastPosZ;
	}
	
	public static void setLastPos(double x, double y, double z){
		lastPosX = x;
		lastPosY = y;
		lastPosZ = z;
	}
	
	public static void setExtraATK(float f){
		extraATK = f;
	}
	
	public static void setExtraASPD(float f){
		extraASPD = f;
	}
	
	public static void setExtraRange(float f){
		extraRange = f;
	}
	
	public static void setMissileLearned(boolean b){
		skillMissile = b;
	}
	
	public static void setLightningLearned(boolean b){
		skillLightning = b;
	}
	
	public static void setSpearLearned(boolean b){
		skillSpear = b;
	}
	
	public static void setSkillLevel(int i){
		levelSkill = i;
	}

}

package com.meteor.extrabotany.common.entity;

import java.util.List;

import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneIgnis;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicMissileII;
import com.meteor.extrabotany.common.handler.ElvenHandler;

import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityElven extends EntityTameable{

	ElvenHandler eh = new ElvenHandler(this);
	
	public static final int affinity = 0;
	public static final float ATK = 3F;
	public static final float ASPD = 40F;
	public static final int level = 0;
	public static final float color_r = 2.3F;
	public static final float color_g = 2.3F;
	public static final float color_b = 1F;
	public static final float size = 3F;
	public static final float range = 4F;
	public static final int exp = 0;
	public static final int delay = 0;
	public static final int mana = 0;
	public static final int maxmana = 0;
	
	private static final String TAG_AFFINITY = "affinity";
	private static final String TAG_ATTACK = "ATK";
	private static final String TAG_ATTACKSPEED = "ASPD";
	private static final String TAG_SITTING = "isSitting";
	private static final String TAG_LEVEL = "level";
	private static final String TAG_COLOR_R = "r";
	private static final String TAG_COLOR_G = "g";
	private static final String TAG_COLOR_B = "b";
	private static final String TAG_SIZE = "size";
	private static final String TAG_OWNER = "owner";
	private static final String TAG_RANGE = "range";
	private static final String TAG_EXP = "exp";
	private static final String TAG_DELAY = "delay";
	private static final String TAG_MANA = "mana";
	private static final String TAG_MAX_MANA = "maxmana";
	
	private static EntityPlayer owner;

	public EntityElven(World world) {
		super(world);
		this.setSize(0F, 0F);
		this.experienceValue = 3000;
		this.setTamed(false);
		this.getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
		tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
	}
	
	public static boolean spawn(EntityPlayer player, double x, double y ,double z){
			EntityElven e = new EntityElven(player.worldObj);
			owner = player;
			e.setAffinity(affinity);
			e.setRange(range);
			e.setEXP(exp);
			e.setATK(ATK);
			e.setASPD(ASPD);
			e.setLevel(0);
			e.setSitting(false);
			e.setRGBS(color_r, color_g, color_b, size);
			e.setTamed(true);
			e.setOwnerName(player.getUniqueID().toString());
			player.worldObj.setEntityState(e, (byte)7);
			e.setMana(mana);
			e.setMaxMana(maxmana);
			e.setDelay(delay);
			e.setPosition(x,y,z);
			player.worldObj.spawnEntityInWorld(e);
		return true;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(18, 0);//affinity
		dataWatcher.addObject(19, 0F);//ATK
		dataWatcher.addObject(20, 0F);//ASPD
		dataWatcher.addObject(21, (byte) 0);//isSitting
		dataWatcher.addObject(22, 0);//level
		dataWatcher.addObject(23, 0F);//r
		dataWatcher.addObject(24, 0F);//g
		dataWatcher.addObject(25, 0F);//b
		dataWatcher.addObject(26, 0F);//s
		dataWatcher.addObject(27, 0F);//range
		dataWatcher.addObject(28, 0);//exp
		dataWatcher.addObject(29, 0);//delay
		dataWatcher.addObject(30, 0);//mana
		dataWatcher.addObject(31, 0);//max mana
	}
	
	public int getAffinity(){
		return dataWatcher.getWatchableObjectInt(18);
	}
	
	public float getATK(){
		return dataWatcher.getWatchableObjectFloat(19);
	}
	
	public float getASPD(){
		return dataWatcher.getWatchableObjectFloat(20);
	}
	
	public boolean isSitting(){
		return dataWatcher.getWatchableObjectByte(21) == 1;
	}
	
	public int getLevel(){
		return dataWatcher.getWatchableObjectInt(22);
	}
	
	public float getR(){
		return dataWatcher.getWatchableObjectFloat(23);
	}
	
	public float getG(){
		return dataWatcher.getWatchableObjectFloat(24);
	}
	
	public float getB(){
		return dataWatcher.getWatchableObjectFloat(25);
	}
	
	public float getSize(){
		return dataWatcher.getWatchableObjectFloat(26);
	}
	
	public String getOwnerName(){
		return dataWatcher.getWatchableObjectString(17);
	}
	
	public float getRange(){
		return dataWatcher.getWatchableObjectFloat(27);
	}
	
	public int getEXP(){
		return dataWatcher.getWatchableObjectInt(28);
	}
	
	public int getDelay(){
		return dataWatcher.getWatchableObjectInt(29);
	}
	
	public int getMana(){
		return dataWatcher.getWatchableObjectInt(30);
	}
	
	public int getMaxMana(){
		return dataWatcher.getWatchableObjectInt(31);
	}
	
	public void setAffinity(int affinity){
		dataWatcher.updateObject(18, affinity);
	}
	
	public void setATK(float atk){
		dataWatcher.updateObject(19, atk);
	}
	
	public void setASPD(float aspd){
		dataWatcher.updateObject(20, aspd);
	}
	
	public void setSitting(boolean b){
		dataWatcher.updateObject(21, (byte) (b ? 1 : 0));
	}
	
	public void setLevel(int level){
		dataWatcher.updateObject(22, level);
	}
	
	public void setRGBS(float r, float g, float b, float s){
		dataWatcher.updateObject(23, r);
		dataWatcher.updateObject(24, g);
		dataWatcher.updateObject(25, b);
		dataWatcher.updateObject(26, s);
	}
	
	public void setOwnerName(String string){
		dataWatcher.updateObject(17, string);
	}
	
	public void setRange(float range){
		dataWatcher.updateObject(27, range);
	}
	
	public void setEXP(int exp){
		dataWatcher.updateObject(28, exp);
	}
	
	public void setDelay(int delay){
		dataWatcher.updateObject(29, delay);
	}
	
	public void setMana(int mana){
		dataWatcher.updateObject(30, mana);
	}
	
	public void setMaxMana(int maxmana){
		dataWatcher.updateObject(31, maxmana);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger(TAG_AFFINITY, getAffinity());
		nbt.setFloat(TAG_ATTACK, getATK());
		nbt.setFloat(TAG_ATTACKSPEED, getASPD());
		nbt.setBoolean(TAG_SITTING, isSitting());
		nbt.setInteger(TAG_LEVEL, getLevel());
		nbt.setFloat(TAG_COLOR_R, getR());
		nbt.setFloat(TAG_COLOR_G, getG());
		nbt.setFloat(TAG_COLOR_B, getB());
		nbt.setFloat(TAG_SIZE, getSize());
		nbt.setFloat(TAG_RANGE, getRange());
		nbt.setInteger(TAG_EXP, getEXP());
		nbt.setInteger(TAG_DELAY, getDelay());
		nbt.setInteger(TAG_MANA, getMana());
		nbt.setInteger(TAG_MAX_MANA, getMaxMana());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setAffinity(nbt.getInteger(TAG_AFFINITY));
		setATK(nbt.getFloat(TAG_ATTACK));
		setASPD(nbt.getFloat(TAG_ATTACKSPEED));
		setSitting(nbt.getBoolean(TAG_SITTING));
		setLevel(nbt.getInteger(TAG_LEVEL));
		setRGBS(nbt.getFloat(TAG_COLOR_R), nbt.getFloat(TAG_COLOR_G), nbt.getFloat(TAG_COLOR_B), nbt.getFloat(TAG_SIZE));  
		setRange(nbt.getFloat(TAG_RANGE));
		setEXP(nbt.getInteger(TAG_EXP));
		setDelay(nbt.getInteger(TAG_DELAY));
		setMana(nbt.getInteger(TAG_MANA));
		setMaxMana(nbt.getInteger(TAG_MAX_MANA));
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40F);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();	
					
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		
		if(this.getOwner() != null){
			
			if(!isSitting()){
				this.posX = this.getOwner().posX;
				this.posY = this.getOwner().posY + 0.74F;
				this.posZ = this.getOwner().posZ;
			}else{
				this.posX = eh.getLastPosX();
				this.posY = eh.getLastPosY() + 0.74F;
				this.posZ = eh.getLastPosZ();
			}
		}else{
			this.posX = eh.getLastPosX();
			this.posY = eh.getLastPosY() + 0.74F;
			this.posZ = eh.getLastPosZ();
		}
		
		float ATK = getATK() + eh.getExtraATK();
		float RANGE = getRange() + eh.getExtraRange();
		float ASPD = getASPD() + eh.getExtraRange();
		
		//gain xp
		List<EntityXPOrb> xps = this.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(this.posX - RANGE, this.posY - RANGE, this.posZ - RANGE, this.posX + RANGE + 1, this.posY + RANGE + 1, this.posZ + RANGE + 1));
		for(EntityXPOrb xp : xps){
			if(getLevel() < 100)
				setEXP(getEXP() + xp.xpValue);
				xp.setDead();
		}
		
		List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.posX - RANGE, this.posY - RANGE, this.posZ - RANGE, this.posX + RANGE + 1, this.posY + RANGE + 1, this.posZ + RANGE + 1));
		
		//sparkle
		Botania.proxy.sparkleFX(this.worldObj, this.posX, this.posY, this.posZ, getR(), getG(), getB(), getSize(), 6);
		Botania.proxy.sparkleFX(this.worldObj, this.posX, this.posY, this.posZ, getR(), getG(), getB(), getSize()/10, 2);
		
		//gain affinity automatically
		if(ticksExisted % 3600 == 0)
			if(Math.random() > 0.7)
				if(getAffinity() < 30)
					setAffinity(getAffinity() + 1);
		
		//get mobs around
		List<EntityMob> mobs = this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(this.posX - getRange(), this.posY - getRange(), this.posZ - getRange(), this.posX + getRange() + 1, this.posY + getRange() + 1, this.posZ + getRange() + 1));
		int s = mobs.size();
		
		//cool down
		if(getDelay() > 0)
			setDelay(getDelay() - 1);
		
		//attack
		if(!this.worldObj.isRemote && !isSitting()){
			if(ticksExisted % (ASPD + 20 ) == 0){
				
				do{
				
					setDelay((int)(ASPD + 10));
				
					for(int i = 0; i < s + 1; i++){
						EntityMagicMissileII missile = new EntityMagicMissileII(this, false);
						missile.setPosition(this.posX + (Math.random() - 0.5 * 0.1), this.posY + 0.68F + (Math.random() - 0.5 * 0.1), this.posZ + (Math.random() - 0.5 * 0.1));
						missile.setATK(ATK);
						if(missile.getTarget()) {
							worldObj.playSoundAtEntity(this, "botania:missile", 0.6F, 0.8F + (float) Math.random() * 0.2F);
							worldObj.spawnEntityInWorld(missile);
						}
					}
				
				}while(s > 0 && getDelay() == 0 && ATK > 0 && ASPD > 0);
			}
		}
	}
	
	public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (this.isTamed())
        {
        	if(player == this.getOwner()){
        		if(!worldObj.isRemote)
        			eh.setLastPos(player.posX, player.posY, player.posZ);
        			setSitting(!isSitting());
        			this.isJumping = false;
        			this.setPathToEntity((PathEntity)null);
        			this.setTarget((Entity)null);
        			this.setAttackTarget((EntityLivingBase)null);
        			return true;
        	}
        }
        
        return super.interact(player);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}

}

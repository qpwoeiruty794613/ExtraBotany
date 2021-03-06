package com.meteor.extrabotany.common.entity.gaia;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

public class EntityGaiaIIIPhantom extends EntityMob{
	EntityGaiaIII summoner;
	public EntityGaiaIIIPhantom(World world) {
		super(world);
		setSize(0.6F, 1.8F);
		getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		isImmuneToFire = true;
		experienceValue = 0;
	}
	
	public EntityGaiaIIIPhantom(World world, EntityGaiaIII summoner) {
		super(world);
		this.summoner = summoner;
	}
	
	public static final int SPAWN_TICKS = 100;
	private static final float RANGE = 12F;
	private static final float MAX_HP = 20F;
	
	private static final String TAG_INVUL_TIME = "invulTime";
	
	public static boolean spawn(World par3World, double posX, double posY, double posZ, EntityGaiaIII summoner) {
		EntityGaiaIIIPhantom e = new EntityGaiaIIIPhantom(par3World, summoner);
		e.setPosition(posX + 0.5, posY + 3, posZ + 0.5);
		e.setInvulTime(SPAWN_TICKS);
		e.setHealth(1F);
		String b = LibItemName.BINDING;
		ItemStack s1 = new ItemStack(ModItems.excaliber);
		ItemRelic.bindToUsernameS(b, s1);
		ItemStack s2 = new ItemStack(ModItems.hestiachastity);
		ItemRelic.bindToUsernameS(b, s2);
		ItemStack s3 = new ItemStack(ModItems.hermestravelclothing);
		ItemRelic.bindToUsernameS(b, s3);
		ItemStack s4 = new ItemStack(ModItems.aphroditegrace);
		ItemRelic.bindToUsernameS(b, s4);
		ItemStack s5 = new ItemStack(ModItems.vrangerboots);
		ItemRelic.bindToUsernameS(b, s5);
		e.setCurrentItemOrArmor(0, s1);
		e.setCurrentItemOrArmor(1, s2);
		e.setCurrentItemOrArmor(2, s3);
		e.setCurrentItemOrArmor(3, s4);
		e.setCurrentItemOrArmor(4, s5);
		e.setEquipmentDropChance(0, 0);
		e.setEquipmentDropChance(1, 0);
		e.setEquipmentDropChance(2, 0);
		e.setEquipmentDropChance(3, 0);
		e.setEquipmentDropChance(4, 0);
		par3World.playSoundAtEntity(e, "mob.enderdragon.growl", 10F, 0.1F);
		par3World.spawnEntityInWorld(e);
		return true;
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, 0); // Invul Time
	}

	public int getInvulTime() {
		return dataWatcher.getWatchableObjectInt(20);
	}
	
	public void setInvulTime(int time) {
		dataWatcher.updateObject(20, time);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger(TAG_INVUL_TIME, getInvulTime());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		setInvulTime(par1nbtTagCompound.getInteger(TAG_INVUL_TIME));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MAX_HP);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2F);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}
	
	@Override
	public void onDeath(DamageSource p_70645_1_) {
		super.onDeath(p_70645_1_);
		EntityLivingBase entitylivingbase = func_94060_bK();
		if(entitylivingbase instanceof EntityPlayer) {
			entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(summoner), 6F);
		}
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
    {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6F);
    }
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		int invul = getInvulTime();
		if(invul > 0) {
			if(invul < SPAWN_TICKS)  {
				if(invul > SPAWN_TICKS / 2 && worldObj.rand.nextInt(SPAWN_TICKS - invul + 1) == 0)
					for(int i = 0; i < 2; i++)
						spawnExplosionParticle();
			}

			if(!worldObj.isRemote) {
				setHealth(getHealth() + (getMaxHealth() - 1F) / SPAWN_TICKS);
				setInvulTime(invul - 1);
			}
		}
		int range = 11;
		List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.posX + 0.5 - range, this.posY + 0.5 - range, this.posZ + 0.5 - range, this.posX + 0.5 + range, this.posY + 0.5 + range, this.posZ + 0.5 + range));
		if(players.isEmpty() && !worldObj.playerEntities.isEmpty())
			setDead();
		boolean peaceful = worldObj.difficultySetting == EnumDifficulty.PEACEFUL;
		if(!worldObj.isRemote && peaceful)
			setDead();
	}

}

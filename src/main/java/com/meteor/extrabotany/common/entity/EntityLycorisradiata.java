package com.meteor.extrabotany.common.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLycorisradiata extends EntityMob{
	public EntityLycorisradiata(World world) {
		super(world);
	    setSize(0.6F, 1.8F);
	    this.experienceValue = 0;
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1);
    }
	
    protected boolean isAIEnabled()
    {
        return false;
    }
    
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F )
            {
            	this.setFire(8);
            }
        }
    }
    
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    protected void fall(float p_70069_1_) {
    	
    }
    
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 15728880;
    }
    
    public float getBrightness(float p_70013_1_)
    {
        return 2.0F;
    }
    
    protected String getDeathSound() {
        return "dig.grass";
    }
}
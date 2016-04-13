package com.meteor.extrabotany.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTeleportPearl extends EntityThrowable
{
    private static final String __OBFID = "CL_00001725";

    public EntityTeleportPearl(World world)
    {
        super(world);
    }

    public EntityTeleportPearl(World world, EntityLivingBase entity)
    {
        super(world, entity);
    }

    @SideOnly(Side.CLIENT)
    public EntityTeleportPearl(World world, double posX, double posY, double posZ)
    {
        super(world, posX, posY, posZ);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition object)
    {
        if (object.entityHit != null)
        {

        }

        for (int i = 0; i < 32; ++i)
        {
            this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
        }

        if (!this.worldObj.isRemote)
        {
            if (this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)this.getThrower();

                if (entityplayermp.playerNetServerHandler.func_147362_b().isChannelOpen() && entityplayermp.worldObj == this.worldObj)
                {
                    EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
                    if (!MinecraftForge.EVENT_BUS.post(event))
                    { // Don't indent to lower patch size
                    if (this.getThrower().isRiding())
                    {
                        this.getThrower().mountEntity((Entity)null);
                    }

                    this.getThrower().setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
                    }
                }
            }

            this.setDead();
        }
     }
}

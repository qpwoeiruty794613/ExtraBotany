package com.meteor.extrabotany.common.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.item.ModItems;

public class EntityLycorisradiataGreen extends EntityLycorisradiata{

	public EntityLycorisradiataGreen(World world) {
		super(world);
	}
	
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        if (wasRecentlyHit)
        {
            int j = this.rand.nextInt(2);

            for (int k = 0; k < j; ++k)
            {	
                this.entityDropItem(new ItemStack(ModItems.material, 1, 4), this.rand.nextInt(2));
                this.entityDropItem(new ItemStack(ModItems.material, 1, 5), this.rand.nextInt(3));
                this.entityDropItem(new ItemStack(ModItems.material, 1, 6), this.rand.nextInt(2));
            }
        }
    }
}
package com.meteor.extrabotany.common.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.item.ModItems;

public class EntityLycorisradiataPurple extends EntityLycorisradiata{

	public EntityLycorisradiataPurple(World world) {
		super(world);
	}
	
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        if (wasRecentlyHit)
        {
            int j = this.rand.nextInt(2);

            for (int k = 0; k < j; ++k)
            {
                this.dropItem(new ItemStack(ModItems.material, 1, 4).getItem(), this.rand.nextInt(2));
                this.dropItem(new ItemStack(ModItems.material, 1, 5).getItem(), this.rand.nextInt(2));
                this.dropItem(new ItemStack(ModItems.material, 1, 6).getItem(), this.rand.nextInt(3));
            }
        }
    }
}
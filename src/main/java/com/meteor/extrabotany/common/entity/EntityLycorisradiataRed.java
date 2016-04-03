package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.items.ModItems;

import net.minecraft.world.World;

public class EntityLycorisradiataRed extends EntityLycorisradiata{

	public EntityLycorisradiataRed(World world) {
		super(world);
	}
    
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        if (p_70628_1_)
        {
            int j = this.rand.nextInt(2 + p_70628_2_);

            for (int k = 0; k < j; ++k)
            {
                this.dropItem(ModItems.petal_red, this.rand.nextInt(3));
                this.dropItem(ModItems.petal_green, this.rand.nextInt(2));
                this.dropItem(ModItems.petal_purple, this.rand.nextInt(2));
            }
        }
    }
}

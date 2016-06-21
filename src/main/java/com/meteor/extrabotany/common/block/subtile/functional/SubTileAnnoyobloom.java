package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.Random;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileAnnoyobloom extends SubTileFunctional{
	
	private static final Random rand = new Random();
	
	private static final int RANGE = 2;
	private static final int DELAY = 35;
	
	@Override
	public int getColor() {
		return 0xEEE31B;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();	
		World world = this.supertile.getWorldObj();
		if(!world.isRemote){
			if(mana > 0 && redstoneSignal == 0){
				if(ticksExisted % DELAY == 0){
					for(int i = 0;i < 6; i++){
						if(mana == 0)
							break;
						mana--;
						world.spawnEntityInWorld(getRandomFirework(world, supertile.xCoord + i/5 - 0.6F, supertile.yCoord + i/3, supertile.zCoord + i/5 - 0.6F));
					}
				}
			}
		}
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public int getMaxMana() {
		return 100;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
	
	public EntityFireworkRocket getRandomFirework(World world, float x, float y, float z)
    {
        ItemStack firework = new ItemStack(Items.fireworks);
        firework.stackTagCompound = new NBTTagCompound();
        NBTTagCompound expl = new NBTTagCompound();
        expl.setBoolean("Flicker", true);
        expl.setBoolean("Trail", true);

        int[] colors = new int[rand.nextInt(8) + 1];
        for (int i = 0; i < colors.length; i++)
        {
            colors[i] = ItemDye.field_150922_c[rand.nextInt(16)];
        }
        expl.setIntArray("Colors", colors);
        byte type = (byte) (rand.nextInt(3) + 1);
        type = type == 3 ? 4 : type;
        expl.setByte("Type", type);

        NBTTagList explosions = new NBTTagList();
        explosions.appendTag(expl);

        NBTTagCompound fireworkTag = new NBTTagCompound();
        fireworkTag.setTag("Explosions", explosions);
        fireworkTag.setByte("Flight", (byte) 1);
        firework.stackTagCompound.setTag("Fireworks", fireworkTag);

        EntityFireworkRocket e = new EntityFireworkRocket(world, x + 0.5, y + 0.5, z + 0.5, firework);
        return e;
    }
	
}

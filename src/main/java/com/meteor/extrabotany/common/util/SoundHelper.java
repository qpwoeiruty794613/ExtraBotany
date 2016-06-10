package com.meteor.extrabotany.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SoundHelper {
	
	public static void playSound(World world, Sound sound, int x, int y, int z, float fre){
		world.playSound(x,y,z, sound.getName(), sound.getVolume(), fre, true);
	}
	
	public static void playSoundAtEntity(World world, Sound sound, Entity entity, float fre){
		world.playSoundAtEntity(entity, sound.getName(), sound.getVolume(), fre);
	}

}

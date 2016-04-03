package com.meteor.extrabotany.common.entity;



import com.meteor.extrabotany.common.ExtraBotany;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;




public class ModEntities {
	


	public static void init() {

		
		int id = 0;
		EntityRegistry.registerModEntity(EntityLycorisradiataRed.class, "Lycorisradiata_Red", id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityLycorisradiataGreen.class, "Lycorisradiata_Green", id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityLycorisradiataPurple.class, "Lycorisradiata_Purple", id++, ExtraBotany.instance, 64, 10, true);

		EntityRegistry.addSpawn(EntityLycorisradiataRed.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.forest);
		EntityRegistry.addSpawn(EntityLycorisradiataGreen.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.plains);
		EntityRegistry.addSpawn(EntityLycorisradiataPurple.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.desert);


	}
	

}

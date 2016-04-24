package com.meteor.extrabotany.common.entity;



import vazkii.botania.common.lib.LibEntityNames;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.lib.LibEntityName;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class ModEntities {
	public static void init() {
		int id = 0;
		EntityRegistry.registerModEntity(EntityLycorisradiataRed.class, LibEntityName.LYCORISRED, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityLycorisradiataGreen.class, LibEntityName.LYCORISGREEN, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityLycorisradiataPurple.class, LibEntityName.LYCORISPURPLE, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityTeleportPearl.class, LibEntityName.TELEPORTPERAL, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityGaiaIII.class, "gaia3", id++, ExtraBotany.instance, 64, 3, true);
		
		EntityRegistry.registerModEntity(EntityMagicLandmineII.class, "landmine", id++, ExtraBotany.instance, 128, 40, false);
		EntityRegistry.addSpawn(EntityLycorisradiataRed.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.forest);
		EntityRegistry.addSpawn(EntityLycorisradiataGreen.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.plains);
		EntityRegistry.addSpawn(EntityLycorisradiataPurple.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.desert);
	}
}

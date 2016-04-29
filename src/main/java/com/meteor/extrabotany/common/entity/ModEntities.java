package com.meteor.extrabotany.common.entity;



import vazkii.botania.common.lib.LibEntityNames;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletExploding;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletGold;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletHighVelocity;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMeteor;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMusket;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSilver;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSnowball;
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
		
		EntityRegistry.registerModEntity(EntityBulletExploding.class, LibEntityName.BULLETEXPLODING, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletGold.class, LibEntityName.BULLETGOLD, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletHighVelocity.class, LibEntityName.BULLETHIGHVELOCITY, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletMeteor.class, LibEntityName.BULLETMETEOR, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletMusket.class, LibEntityName.BULLETMUSKET, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletSilver.class, LibEntityName.BULLETSILVER, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletSnowball.class, LibEntityName.BULLETSNOWBALL, id++, ExtraBotany.instance, 256, 3, true);
		
		EntityRegistry.registerModEntity(EntityMagicLandmineII.class, "landmine", id++, ExtraBotany.instance, 128, 40, false);
		EntityRegistry.addSpawn(EntityLycorisradiataRed.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.forest);
		EntityRegistry.addSpawn(EntityLycorisradiataGreen.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.plains);
		EntityRegistry.addSpawn(EntityLycorisradiataPurple.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.desert);
	}
}

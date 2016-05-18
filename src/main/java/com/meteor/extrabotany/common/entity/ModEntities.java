package com.meteor.extrabotany.common.entity;



import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletExploding;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletGold;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletHighVelocity;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMeteor;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMusket;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSilver;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSnowball;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIIIDark;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIIIPhantom;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneAqua;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneChaos;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneIgnis;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicLandmineII;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicMissileII;
import com.meteor.extrabotany.common.lib.LibEntityName;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void init() {
		int id = 0;
		EntityRegistry.registerModEntity(EntityLycorisradiataRed.class, LibEntityName.LYCORISRED, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityLycorisradiataGreen.class, LibEntityName.LYCORISGREEN, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityLycorisradiataPurple.class, LibEntityName.LYCORISPURPLE, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityTeleportPearl.class, LibEntityName.TELEPORTPERAL, id++, ExtraBotany.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityGaiaIII.class, LibEntityName.GAIAIII, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityGaiaIIIDark.class, LibEntityName.GAIAIIIDARK, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityGaiaIIIPhantom.class, LibEntityName.GAIAIIIPHANTOM, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityTV.class, LibEntityName.TV, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(Entity22.class, LibEntityName.BILIBILI, id++, ExtraBotany.instance, 256, 3, true);
		
		EntityRegistry.registerModEntity(EntityItemUnbreakable.class, LibEntityName.UNBREAKABLEITEM, id++, ExtraBotany.instance, 256, 3, true);
		
		EntityRegistry.registerModEntity(EntityBulletExploding.class, LibEntityName.BULLETEXPLODING, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletGold.class, LibEntityName.BULLETGOLD, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletHighVelocity.class, LibEntityName.BULLETHIGHVELOCITY, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletMeteor.class, LibEntityName.BULLETMETEOR, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletMusket.class, LibEntityName.BULLETMUSKET, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletSilver.class, LibEntityName.BULLETSILVER, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityBulletSnowball.class, LibEntityName.BULLETSNOWBALL, id++, ExtraBotany.instance, 256, 3, true);
		
		EntityRegistry.registerModEntity(EntitySpear.class, LibEntityName.SPEAR, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityMagicCycloneChaos.class, LibEntityName.MAGICCYCLONECHAOS, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityMagicCycloneAqua.class, LibEntityName.MAGICCYCLONEAQUA, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityMagicCycloneIgnis.class, LibEntityName.MAGICCYCLONEIGNIS, id++, ExtraBotany.instance, 256, 3, true);
		EntityRegistry.registerModEntity(EntityMagicLandmineII.class, LibEntityName.MAGICLANDMINEII, id++, ExtraBotany.instance, 128, 40, false);
		EntityRegistry.registerModEntity(EntityMagicMissileII.class, LibEntityName.MAGICMISSILEII, id++, ExtraBotany.instance, 128, 40, false);
		EntityRegistry.addSpawn(EntityLycorisradiataRed.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.forest);
		EntityRegistry.addSpawn(EntityLycorisradiataGreen.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.plains);
		EntityRegistry.addSpawn(EntityLycorisradiataPurple.class, 30, 1, 3, EnumCreatureType.monster, BiomeGenBase.desert);
	}
}

package com.meteor.extrabotany.common.item;

import vazkii.botania.common.item.record.ItemModRecord;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;
import com.meteor.extrabotany.common.item.basic.ItemBullet;
import com.meteor.extrabotany.common.item.basic.ItemMaterial;
import com.meteor.extrabotany.common.item.basic.ItemRecordA;
import com.meteor.extrabotany.common.item.basic.ItemRecordB;
import com.meteor.extrabotany.common.item.basic.ItemRecordC;
import com.meteor.extrabotany.common.item.equipment.ItemBaubleDog;
import com.meteor.extrabotany.common.item.relic.ItemGunPhoenixBlaster;
import com.meteor.extrabotany.common.item.relic.ItemGunSnowballCannon;
import com.meteor.extrabotany.common.item.relic.ItemGunTacticalShotgun;
import com.meteor.extrabotany.common.item.relic.legendary.ItemAphroditeGrace;
import com.meteor.extrabotany.common.item.relic.legendary.ItemAthenaBless;
import com.meteor.extrabotany.common.item.relic.legendary.ItemCronusPhantom;
import com.meteor.extrabotany.common.item.relic.legendary.ItemCthulhuEye;
import com.meteor.extrabotany.common.item.relic.legendary.ItemDice20;
import com.meteor.extrabotany.common.item.relic.legendary.ItemExcaliber;
import com.meteor.extrabotany.common.item.relic.legendary.ItemExcaliberFake;
import com.meteor.extrabotany.common.item.relic.legendary.ItemHermesTravelClothing;
import com.meteor.extrabotany.common.item.relic.legendary.ItemHestiaChastity;
import com.meteor.extrabotany.common.item.relic.legendary.ItemLokiGhostrick;
import com.meteor.extrabotany.common.item.relic.legendary.ItemMaxwellDemon;
import com.meteor.extrabotany.common.item.relic.legendary.ItemOlympusGuard;
import com.meteor.extrabotany.common.item.relic.legendary.ItemVHandgun;
import com.meteor.extrabotany.common.item.relic.legendary.ItemVPowerBattleaxe;
import com.meteor.extrabotany.common.item.relic.legendary.ItemVRangerBoots;
import com.meteor.extrabotany.common.item.weapon.ItemGun;
import com.meteor.extrabotany.common.item.weapon.ItemGunBoomstick;
import com.meteor.extrabotany.common.item.weapon.ItemGunFlintlock;
import com.meteor.extrabotany.common.item.weapon.ItemGunPistol;
import com.meteor.extrabotany.common.item.weapon.ItemGunShotgun;
import com.meteor.extrabotany.common.lib.LibEntityName;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibOreDictName;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	public static Item gaiatablet;
	public static Item dice20;
	public static Item excaliber;
	public static Item excaliberfake;
	public static Item vpowerbattleaxe;
	public static Item vhandgun;
	public static Item vrangerboots;
	public static Item lycorisgreen;
	public static Item lycorispurple;
	public static Item lycorisred;
	public static Item lycorisrandom;
	public static Item hestiachastity;
	public static Item maxwelldemon;
	public static Item athenabless;
	public static Item lokighostrick;
	public static Item aphroditegrace;
	public static Item cronusphantom;
	public static Item hermestravelclothing;
	public static Item cthulhueye;
	public static Item teleportpearl;
	public static Item gaianecklacebroken;
	public static Item olympusguard;
	public static Item dog;
	
	public static Item itemtest;
	public static Item material;
	public static Item bullet;
	
	public static Item gunphoenixblaster;
	public static Item guntacticalshotgun;
	public static Item snowballlauncher;
	public static Item gunboomstick;
	public static Item gunflintlock;
	public static Item gunshotgun;
	public static Item gunpistol;
	public static Item recordB;
	public static Item recordA;
	public static Item recordC;
	
	public static void init() {
		recordA = new ItemRecordA("A", LibItemName.RECORD_A);
		recordB = new ItemRecordB("B", LibItemName.RECORD_B);
		recordC = new ItemRecordC("C", LibItemName.RECORD_C);
		
		itemtest = new ItemTest("test");
		
		gunphoenixblaster = new ItemGunPhoenixBlaster(LibItemName.PHOENIXBLASTER);
		guntacticalshotgun = new ItemGunTacticalShotgun(LibItemName.TACTICALSHOTGUN);
		snowballlauncher = new ItemGunSnowballCannon(LibItemName.SNOWBALLLAUNCHER);
		gunboomstick = new ItemGunBoomstick(LibItemName.BOOMSTICK);
		gunflintlock = new ItemGunFlintlock(LibItemName.FLINTLOCK);
		gunshotgun = new ItemGunShotgun(LibItemName.SHOTGUN);
		gunpistol = new ItemGunPistol(LibItemName.PISTOL);

		material = new ItemMaterial(LibItemName.MATERIAL);
		bullet = new ItemBullet(LibItemName.BULLET);
		gaianecklacebroken = new ItemMods(LibItemName.GAIANECKLACEBROKEN);
				
		dog = new ItemBaubleDog();
		
		lokighostrick = new ItemLokiGhostrick(LibItemName.LOKIGHOSTRICK);
		cthulhueye = new ItemCthulhuEye();
		cronusphantom = new ItemCronusPhantom(LibItemName.CRONUSPHANTOM);
		athenabless = new ItemAthenaBless();
		maxwelldemon = new ItemMaxwellDemon();
		excaliber = new ItemExcaliber();
		excaliberfake = new ItemExcaliberFake();
		vpowerbattleaxe = new ItemVPowerBattleaxe();
		vhandgun = new ItemVHandgun();
		vrangerboots = new ItemVRangerBoots(3, LibItemName.VRANGERBOOTS);
		dice20 = new ItemDice20();
		hestiachastity = new ItemHestiaChastity(0, LibItemName.HESTIACHASTITY);
		aphroditegrace = new ItemAphroditeGrace(2, LibItemName.APHRODITEGRACE);
		olympusguard = new ItemOlympusGuard(LibItemName.OLYMPUSGUARD);
		hermestravelclothing = new ItemHermesTravelClothing(1, LibItemName.HERMESTRAVELCLOTHING);
		
		gaiatablet = new ItemGaiaTablet();
		teleportpearl = new ItemTeleportPearl(LibItemName.TELEPORTPEARL);
		
		lycorisgreen = new ItemSpawnCardLycorisGreen(LibEntityName.LYCORISGREEN);
		lycorisred = new ItemSpawnCardLycorisRed(LibEntityName.LYCORISRED);
		lycorispurple = new ItemSpawnCardLycorisPurple(LibEntityName.LYCORISPURPLE);
		lycorisrandom = new ItemSpawnCardLycorisRandom("lycorisrandom");
		
		//OreDict
		OreDictionary.registerOre(LibOreDictName.DOG, new ItemStack(dog, 1, 0));
		OreDictionary.registerOre(LibOreDictName.DOG, new ItemStack(dog, 1, 1));
		OreDictionary.registerOre(LibOreDictName.DOG, new ItemStack(dog, 1, 2));
		OreDictionary.registerOre(LibOreDictName.DOG, new ItemStack(dog, 1, 3));
		OreDictionary.registerOre(LibOreDictName.PRISMATIC_SHARD, new ItemStack(material, 1, 0));
		OreDictionary.registerOre(LibOreDictName.BLANK_CARD, new ItemStack(material, 1, 1));
		OreDictionary.registerOre(LibOreDictName.GAIA_ESSENCE, new ItemStack(material, 1, 2));
		OreDictionary.registerOre(LibOreDictName.ASTRAL_FORCE, new ItemStack(material, 1, 3));
		OreDictionary.registerOre(LibOreDictName.LYCORIS_RED, new ItemStack(material, 1, 4));
		OreDictionary.registerOre(LibOreDictName.LYCORIS_GREEN, new ItemStack(material, 1, 5));
		OreDictionary.registerOre(LibOreDictName.LYCORIS_PURPLE, new ItemStack(material, 1, 6));
		OreDictionary.registerOre(LibOreDictName.QUARTZ_GAIA, new ItemStack(material, 1, 7));
		OreDictionary.registerOre(LibOreDictName.QUARTZ_ELEMENTIUM, new ItemStack(material, 1, 8));
		OreDictionary.registerOre(LibOreDictName.STRING_GOLD, new ItemStack(material, 1, 9));
		OreDictionary.registerOre(LibOreDictName.DICE_EMPTY, new ItemStack(material, 1, 10));
	}

}

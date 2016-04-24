package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityLycorisradiata;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;
import com.meteor.extrabotany.common.item.equipment.ItemBaubleDog;
import com.meteor.extrabotany.common.item.relic.ItemAphroditeGrace;
import com.meteor.extrabotany.common.item.relic.ItemAthenaBless;
import com.meteor.extrabotany.common.item.relic.ItemCronusPhantom;
import com.meteor.extrabotany.common.item.relic.ItemDice20;
import com.meteor.extrabotany.common.item.relic.ItemExcaliber;
import com.meteor.extrabotany.common.item.relic.ItemExcaliberFake;
import com.meteor.extrabotany.common.item.relic.ItemHermesTravelClothing;
import com.meteor.extrabotany.common.item.relic.ItemHestiaChastity;
import com.meteor.extrabotany.common.item.relic.ItemMaxwellDemon;
import com.meteor.extrabotany.common.item.relic.ItemVHandgun;
import com.meteor.extrabotany.common.item.relic.ItemVPowerBattleaxe;
import com.meteor.extrabotany.common.item.relic.ItemVRangerBoots;
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
	public static Item aphroditegrace;
	public static Item cronusphantom;
	public static Item hermestravelclothing;
	public static Item teleportpearl;
	public static Item gaianecklacebroken;
	public static Item dog;
	
	public static Item itemtest;
	public static Item material;
	
	public static void init() {
		itemtest = new ItemTest("test");	

		material = new ItemMaterial(LibItemName.MATERIAL);
		gaianecklacebroken = new ItemMods(LibItemName.GAIANECKLACEBROKEN);
				
		dog = new ItemBaubleDog();
		
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
		hermestravelclothing = new ItemHermesTravelClothing(1, LibItemName.HERMESTRAVELCLOTHING);
		
		gaiatablet = new ItemGaiaTablet();
		teleportpearl = new ItemTeleportPearl(LibItemName.TELEPORTPEARL);
		
		lycorisgreen = new ItemSpawnCardLycorisGreen(LibEntityName.LYCORISGREEN);
		lycorisred = new ItemSpawnCardLycorisRed(LibEntityName.LYCORISRED);
		lycorispurple = new ItemSpawnCardLycorisPurple(LibEntityName.LYCORISPURPLE);
		lycorisrandom = new ItemSpawnCardLycorisRandom("lycorisrandom");
		
		//OreDict
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

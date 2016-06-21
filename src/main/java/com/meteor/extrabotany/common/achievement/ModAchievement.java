package com.meteor.extrabotany.common.achievement;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import vazkii.botania.common.achievement.AchievementTriggerer;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibAchievementName;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.FMLCommonHandler;

public class ModAchievement {
	//Relics
	public static Achievement Relic_aphroditegrace;
	public static Achievement Relic_dice20;
	public static Achievement Relic_athenabless;
	public static Achievement Relic_excaliber;
	public static Achievement Relic_excaliberfake;
	public static Achievement Relic_hestiachastity;
	public static Achievement Relic_maxwelldemon;
	public static Achievement Relic_vhandgun;
	public static Achievement Relic_vpowerbattleaxe;
	public static Achievement Relic_vrangerboots;
	
	public static Achievement Relic_cronusphantom;
	public static Achievement Relic_hermestravelclothing;
	public static Achievement Relic_phoenixblaster;
	public static Achievement Relic_snowballcannon;
	public static Achievement Relic_tacticalshotgun;
	public static Achievement Relic_olympusguard;
	public static Achievement Relic_cthulhueye;
	
	public static Achievement Relic_hermes;
	public static Achievement Relic_claymore;
	public static Achievement Relic_combat;
	public static Achievement Relic_slience;
	public static Achievement Relic_loki;
	public static Achievement Relic_angel;
	public static Achievement Relic_ship;
	
	
	//Gaia
	public static Achievement Gaia_gaia3Kill;
	public static Achievement Gaia_gaia3NoArmor;
	public static Achievement Gaia_gaia3DarkKill;
	public static Achievement Gaia_gaia3DarkNoArmor;
	
	//flower
	public static Achievement F_annoy;
	public static Achievement F_artifa;
	public static Achievement F_diplop;
	public static Achievement F_ice;
	public static Achievement F_launch;
	public static Achievement F_necro;
	public static Achievement F_numeb;
	public static Achievement F_numed;
	public static Achievement F_void;
	public static Achievement F_vola;
	public static Achievement F_woo;
	public static Achievement F_blue;
	public static Achievement F_candy;
	public static Achievement F_gemini;
	public static Achievement F_moon;
	public static Achievement F_omni;
	public static Achievement F_pyscho;
	public static Achievement F_stone;
	public static Achievement F_sun;
	public static Achievement F_infer;
	public static Achievement F_judas;
	public static Achievement F_mana;
	
	//others
	public static Achievement O_blank;
	
	public static void init(){
		
		O_blank = new AchievementMod("blankcard", 1, 0, new ItemStack(ModItems.material, 1, 1), null);
		
		F_annoy = new AchievementMod(LibBlockName.ANNOYOBLOOM, 0, 1, ItemBlockSpecialFlower.ofType(LibBlockName.ANNOYOBLOOM), O_blank);
		F_artifa = new AchievementMod(LibBlockName.ARTIFACONIA, 2, 1, ItemBlockSpecialFlower.ofType(LibBlockName.ARTIFACONIA), O_blank);
		F_diplop = new AchievementMod(LibBlockName.DIPLOPBAMBOO, 0, 2, ItemBlockSpecialFlower.ofType(LibBlockName.DIPLOPBAMBOO), O_blank);
		F_ice = new AchievementMod(LibBlockName.ICEBIRDIUM, 2, 2, ItemBlockSpecialFlower.ofType(LibBlockName.ICEBIRDIUM), O_blank);
		F_launch = new AchievementMod(LibBlockName.LAUNCHISH, 0, 3, ItemBlockSpecialFlower.ofType(LibBlockName.LAUNCHISH), O_blank);
		F_necro = new AchievementMod(LibBlockName.NECRO_FLUER, 2, 3, ItemBlockSpecialFlower.ofType(LibBlockName.NECRO_FLUER), O_blank);
		F_numeb = new AchievementMod(LibBlockName.NUMERON_BALSAM, 0, 4, ItemBlockSpecialFlower.ofType(LibBlockName.NUMERON_BALSAM), O_blank);
		F_numed = new AchievementMod(LibBlockName.NUMERON_DANDELIFE, 2, 4, ItemBlockSpecialFlower.ofType(LibBlockName.NUMERON_DANDELIFE), O_blank);
		F_void = new AchievementMod(LibBlockName.VOIDUIM, 0, 5, ItemBlockSpecialFlower.ofType(LibBlockName.VOIDUIM), O_blank);
		F_vola = new AchievementMod(LibBlockName.VOLATILILY, 2, 5, ItemBlockSpecialFlower.ofType(LibBlockName.VOLATILILY), O_blank);
		F_woo = new AchievementMod(LibBlockName.WOODIENIA, 0, 6, ItemBlockSpecialFlower.ofType(LibBlockName.WOODIENIA), O_blank);
		F_blue = new AchievementMod(LibBlockName.BLUE_ENCHANTRESS, 2, 6, ItemBlockSpecialFlower.ofType(LibBlockName.BLUE_ENCHANTRESS), O_blank);
		F_candy = new AchievementMod(LibBlockName.CANDY_FLOWER, 0, 7, ItemBlockSpecialFlower.ofType(LibBlockName.CANDY_FLOWER), O_blank);
		F_gemini = new AchievementMod(LibBlockName.GEMINIORCHID, 2, 7, ItemBlockSpecialFlower.ofType(LibBlockName.GEMINIORCHID), O_blank);
		F_moon = new AchievementMod(LibBlockName.MOONLIGHT_LILY, 0, 8, ItemBlockSpecialFlower.ofType(LibBlockName.MOONLIGHT_LILY), O_blank);
		F_omni = new AchievementMod(LibBlockName.OMNIVIOLET, 2, 8, ItemBlockSpecialFlower.ofType(LibBlockName.OMNIVIOLET), O_blank);
		F_pyscho = new AchievementMod(LibBlockName.PYSCHOBLOOM, 0, 9, ItemBlockSpecialFlower.ofType(LibBlockName.PYSCHOBLOOM), O_blank);
		F_stone = new AchievementMod(LibBlockName.STONESIA, 2, 9, ItemBlockSpecialFlower.ofType(LibBlockName.STONESIA), O_blank);
		F_sun = new AchievementMod(LibBlockName.SUNSHINE_LILY, 0, 10, ItemBlockSpecialFlower.ofType(LibBlockName.SUNSHINE_LILY), O_blank);
		F_infer = new AchievementMod(LibBlockName.INFERNOIDISY, 2, 10, ItemBlockSpecialFlower.ofType(LibBlockName.INFERNOIDISY), O_blank);
		F_judas = new AchievementMod(LibBlockName.JUDASVOW, 0, 11, ItemBlockSpecialFlower.ofType(LibBlockName.JUDASVOW), O_blank);
		F_mana = new AchievementMod(LibBlockName.MANALINKUIM, 2, 11, ItemBlockSpecialFlower.ofType(LibBlockName.MANALINKUIM), O_blank);
		
		Gaia_gaia3Kill = new AchievementMod(LibAchievementName.GAIA_KILL, -8, 8, vazkii.botania.common.item.ModItems.gaiaHead, null);
		Gaia_gaia3NoArmor = new AchievementMod(LibAchievementName.GAIA_NOARMOR, -6, 8, ModItems.dungeonbox, null);
		Gaia_gaia3DarkKill = new AchievementMod(LibAchievementName.GAIA_DARK, -8, 10, ModItems.dog, Gaia_gaia3Kill);
		Gaia_gaia3DarkNoArmor = new AchievementMod(LibAchievementName.GAIA_DARKNOARMOR, -6, 10, ModItems.boxs, Gaia_gaia3DarkNoArmor);
		
		if(ConfigHandler.relicsEnabled) {
			Relic_aphroditegrace = new AchievementMod(LibItemName.APHRODITEGRACE, -9, 12, ModItems.aphroditegrace, Gaia_gaia3Kill);
			Relic_dice20 = new AchievementMod(LibItemName.DICE20, -7, 12, ModItems.dice20, Gaia_gaia3Kill);
			Relic_athenabless = new AchievementMod(LibItemName.ATHENABLESS, -5, 12, ModItems.athenabless, Gaia_gaia3Kill);
			Relic_excaliberfake = new AchievementMod(LibItemName.EXCALIBERFAKE, -9, 13, ModItems.excaliberfake, Gaia_gaia3Kill);
			Relic_excaliber = new AchievementMod(LibItemName.EXCALIBER, -7, 13, ModItems.excaliber, Gaia_gaia3Kill);
			Relic_hestiachastity = new AchievementMod(LibItemName.HESTIACHASTITY, -5, 13, ModItems.hestiachastity, Gaia_gaia3Kill);
			Relic_maxwelldemon = new AchievementMod(LibItemName.MAXWELLDEMON, -9, 14, ModItems.maxwelldemon, Gaia_gaia3Kill);
			Relic_vhandgun = new AchievementMod(LibItemName.VHANDGUN, -7, 14, ModItems.vhandgun, Gaia_gaia3Kill);
			Relic_vpowerbattleaxe = new AchievementMod(LibItemName.VPOWERBATTLEAXE, -5, 14, ModItems.vpowerbattleaxe, Gaia_gaia3Kill);
			Relic_vrangerboots = new AchievementMod(LibItemName.VRANGERBOOTS, -9, 15, ModItems.vrangerboots, Gaia_gaia3Kill);
			Relic_cronusphantom = new AchievementMod(LibItemName.CRONUSPHANTOM, -7, 15, ModItems.cronusphantom, Gaia_gaia3Kill);
			Relic_hermestravelclothing = new AchievementMod(LibItemName.HERMESTRAVELCLOTHING, -5, 15, ModItems.hermestravelclothing, Gaia_gaia3Kill);
			Relic_phoenixblaster = new AchievementMod(LibItemName.PHOENIXBLASTER, -9, 16, ModItems.gunphoenixblaster, Gaia_gaia3Kill);
			Relic_snowballcannon = new AchievementMod(LibItemName.SNOWBALLLAUNCHER, -7, 16, ModItems.snowballlauncher, Gaia_gaia3Kill);
			Relic_tacticalshotgun = new AchievementMod(LibItemName.TACTICALSHOTGUN, -5, 16, ModItems.guntacticalshotgun, Gaia_gaia3Kill);
			Relic_olympusguard = new AchievementMod(LibItemName.OLYMPUSGUARD, -5, 17, ModItems.olympusguard, Gaia_gaia3Kill);
			Relic_cthulhueye = new AchievementMod(LibItemName.CTHULHUEYE, -7, 17, ModItems.cthulhueye, Gaia_gaia3Kill);	
			Relic_hermes = new AchievementMod(LibItemName.HERMESWAND, -9, 17, ModItems.hermeswand, Gaia_gaia3Kill);		
			Relic_claymore = new AchievementMod(LibItemName.HELIACALCLAYMORE, -5, 18, ModItems.heliacalclaymore, Gaia_gaia3Kill);		
			Relic_combat = new AchievementMod(LibItemName.VALKYRIECOMBATUNIFORM, -7, 18, ModItems.valkyriecombatuniform, Gaia_gaia3Kill);		
			Relic_ship = new AchievementMod(LibItemName.THESEUSSHIP, -9, 18, ModItems.theseusship, Gaia_gaia3Kill);		
			Relic_angel = new AchievementMod(LibItemName.ANGELWAND, -5, 19, ModItems.angelwand, Gaia_gaia3Kill);		
			Relic_loki = new AchievementMod(LibItemName.LOKIGHOSTRICK, -7, 19, ModItems.lokighostrick, Gaia_gaia3Kill);		
			Relic_slience = new AchievementMod(LibItemName.ETERNALSLIENCE, -9, 19, ModItems.eternalslience, Gaia_gaia3Kill);			
		}
		
		int pageIndex = AchievementPage.getAchievementPages().size();
		AchievementPage extrabotanyPage = new AchievementPage(LibReference.MOD_NAME, AchievementMod.achievements.toArray(new Achievement[AchievementMod.achievements.size()]));
		AchievementPage.registerAchievementPage(extrabotanyPage);
		
		FMLCommonHandler.instance().bus().register(new AchievementTriggerer());
	}
}

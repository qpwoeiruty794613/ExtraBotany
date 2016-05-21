package com.meteor.extrabotany.common.achievement;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import vazkii.botania.common.achievement.AchievementTriggerer;
import vazkii.botania.common.core.handler.ConfigHandler;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibAchievementName;
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
	
	//Gaia
	public static Achievement Gaia_gaia3Kill;
	public static Achievement Gaia_gaia3NoArmor;
	public static Achievement Gaia_gaia3DarkKill;
	public static Achievement Gaia_gaia3DarkNoArmor;
	
	public static void init(){
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
			Relic_olympusguard = new AchievementMod(LibItemName.OLYMPUSGUARD, -9, 17, ModItems.olympusguard, Gaia_gaia3Kill);
			Relic_cthulhueye = new AchievementMod(LibItemName.CTHULHUEYE, -7, 17, ModItems.cthulhueye, Gaia_gaia3Kill);		
		}
		
		Gaia_gaia3Kill = new AchievementMod(LibAchievementName.GAIA_KILL, -8, 8, vazkii.botania.common.item.ModItems.gaiaHead, null);
		Gaia_gaia3NoArmor = new AchievementMod(LibAchievementName.GAIA_NOARMOR, -6, 8, ModItems.dungeonbox, null);
		Gaia_gaia3DarkKill = new AchievementMod(LibAchievementName.GAIA_DARK, -8, 10, ModItems.dog, null);
		Gaia_gaia3DarkNoArmor = new AchievementMod(LibAchievementName.GAIA_DARKNOARMOR, -6, 10, ModItems.boxs, null);
		
		int pageIndex = AchievementPage.getAchievementPages().size();
		AchievementPage extrabotanyPage = new AchievementPage(LibReference.MOD_NAME, AchievementMod.achievements.toArray(new Achievement[AchievementMod.achievements.size()]));
		AchievementPage.registerAchievementPage(extrabotanyPage);
		
		FMLCommonHandler.instance().bus().register(new AchievementTriggerer());
	}
}

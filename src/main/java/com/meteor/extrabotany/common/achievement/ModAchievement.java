package com.meteor.extrabotany.common.achievement;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibAchievementName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.FMLCommonHandler;
import vazkii.botania.common.achievement.AchievementTriggerer;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lib.LibMisc;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

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
	
	public static void init(){
		if(ConfigHandler.relicsEnabled) {
			Relic_aphroditegrace = new AchievementMod(LibAchievementName.RELIC_APHRODITEGRACE, -9, 12, ModItems.aphroditegrace, null);
			Relic_dice20 = new AchievementMod(LibAchievementName.RELIC_DICE20, -7, 13, ModItems.dice20, null);
			Relic_athenabless = new AchievementMod(LibAchievementName.RELIC_ATHENABLESS, -5, 12, ModItems.athenabless, null);
			Relic_excaliberfake = new AchievementMod(LibAchievementName.RELIC_EXCALIBERFAKE, -9, 14, ModItems.excaliberfake, null);
			Relic_excaliber = new AchievementMod(LibAchievementName.RELIC_EXCALIBER, -7, 15, ModItems.excaliber, null);
			Relic_hestiachastity = new AchievementMod(LibAchievementName.RELIC_HESTIACHASTITY, -5, 14, ModItems.hestiachastity, null);
			Relic_maxwelldemon = new AchievementMod(LibAchievementName.RELIC_MAXWELLDEMON, -9, 16, ModItems.maxwelldemon, null);
			Relic_vhandgun = new AchievementMod(LibAchievementName.RELIC_VHANDGUN, -7, 17, ModItems.vhandgun, null);
			Relic_vpowerbattleaxe = new AchievementMod(LibAchievementName.RELIC_VPOWERBATTLEAXE, -5, 16, ModItems.vpowerbattleaxe, null);
			Relic_vrangerboots = new AchievementMod(LibAchievementName.RELIC_VRANGERBOOTS, -9, 18, ModItems.vrangerboots, null);
		}
		
		int pageIndex = AchievementPage.getAchievementPages().size();
		AchievementPage extrabotanyPage = new AchievementPage(LibReference.MOD_NAME, AchievementMod.achievements.toArray(new Achievement[AchievementMod.achievements.size()]));
		AchievementPage.registerAchievementPage(extrabotanyPage);
		
		FMLCommonHandler.instance().bus().register(new AchievementTriggerer());
	}
}

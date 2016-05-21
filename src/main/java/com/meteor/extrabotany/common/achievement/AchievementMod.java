package com.meteor.extrabotany.common.achievement;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import vazkii.botania.api.item.IRelic;

public class AchievementMod extends Achievement {

	public static List<Achievement> achievements = new ArrayList();

	public AchievementMod(String name, int x, int y, ItemStack icon, Achievement parent) {
		super("achievement.extrabotany:" + name, "extrabotany:" + name, x, y, icon, parent);
		achievements.add(this);
		registerStat();

		if(icon.getItem() instanceof IRelic)
			((IRelic) icon.getItem()).setBindAchievement(this);
	}

	public AchievementMod(String name, int x, int y, Item icon, Achievement parent) {
		this(name, x, y, new ItemStack(icon), parent);
	}

	public AchievementMod(String name, int x, int y, Block icon, Achievement parent) {
		this(name, x, y, new ItemStack(icon), parent);
	}
}

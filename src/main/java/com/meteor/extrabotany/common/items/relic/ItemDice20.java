package com.meteor.extrabotany.common.items.relic;

import java.util.ArrayList;
import java.util.List;

import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemDice20 extends ItemRelic{
	
	public static ItemStack[] relicStacks;
	
	public ItemDice20() {
		super(LibItemName.DICE20);
		relicStacks = new ItemStack[] {
				new ItemStack(ModItems.vhandgun),
				new ItemStack(ModItems.vpowerbattleaxe),//
				new ItemStack(vazkii.botania.common.item.ModItems.infiniteFruit),//
				new ItemStack(ModItems.excaliberfake),
				new ItemStack(vazkii.botania.common.item.ModItems.kingKey),//
				new ItemStack(ModItems.astralforce),//
				new ItemStack(vazkii.botania.common.item.ModItems.flugelEye),//
				new ItemStack(vazkii.botania.common.item.ModItems.thorRing),//
				new ItemStack(vazkii.botania.common.item.ModItems.lokiRing),//
				new ItemStack(vazkii.botania.common.item.ModItems.odinRing),//
				new ItemStack(ModItems.athenabless),//
				new ItemStack(ModItems.hestiachastity),
				new ItemStack(ModItems.maxwelldemon),//
				new ItemStack(ModItems.vrangerboots),
				new ItemStack(ModItems.cronusphantom),//
				new ItemStack(ModItems.aphroditegrace),
				new ItemStack(ModItems.hermestravelclothing),
				new ItemStack(ModItems.hestiachastity),//
				new ItemStack(ModItems.hestiachastity),
				new ItemStack(ModItems.astralforce),//

		};
	}

	private static final int[] SIDES_FOR_MOON_PHASES = new int[] {
		0, 0, 0, 0, 1, 0, 0, 0
	};
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(isRightPlayer(player, stack) && !player.worldObj.isRemote) {
			int moonPhase = world.provider.getMoonPhase(world.getWorldTime());
			int side = SIDES_FOR_MOON_PHASES[moonPhase];
			int relic = side;
				List<Integer> possible = new ArrayList();

				for(int i = 0; i < 20; i++)
					possible.add(world.rand.nextInt(20));
			
					relic = possible.get(world.rand.nextInt(possible.size()));
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
			
			if(relic + 1 == 6 || relic + 1 == 20) {
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.uselessDiceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, relicStacks[relic].copy()));
				return new ItemStack(ModItems.empty_dice).copy();
			}else{
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.diceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, relicStacks[relic].copy()));
				return new ItemStack(ModItems.empty_dice).copy();
			}
		}

		return stack;
	}

	@Override
	public boolean shouldDamageWrongPlayer() {
		return false;
	}
}

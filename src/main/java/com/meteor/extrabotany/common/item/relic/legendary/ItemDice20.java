package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.ArrayList;
import java.util.List;

import com.meteor.extrabotany.common.item.ModItems;
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
				new ItemStack(ModItems.vhandgun),//1
				new ItemStack(ModItems.vpowerbattleaxe),//2
				new ItemStack(vazkii.botania.common.item.ModItems.infiniteFruit),//3
				new ItemStack(ModItems.excaliberfake),//4
				new ItemStack(vazkii.botania.common.item.ModItems.kingKey),//5
				new ItemStack(ModItems.material,1,3),//6
				new ItemStack(vazkii.botania.common.item.ModItems.flugelEye),//7
				new ItemStack(vazkii.botania.common.item.ModItems.thorRing),//8
				new ItemStack(vazkii.botania.common.item.ModItems.lokiRing),//9
				new ItemStack(vazkii.botania.common.item.ModItems.odinRing),//10
				new ItemStack(ModItems.athenabless),//11
				new ItemStack(ModItems.hestiachastity),//12
				new ItemStack(ModItems.maxwelldemon),//13
				new ItemStack(ModItems.vrangerboots),//14
				new ItemStack(ModItems.cronusphantom),//15
				new ItemStack(ModItems.aphroditegrace),//16
				new ItemStack(ModItems.hermestravelclothing),//17
				new ItemStack(ModItems.maxwelldemon),//
				new ItemStack(ModItems.maxwelldemon),
				new ItemStack(ModItems.material,1,3),//

		};
	}

	private static final int[] SIDES_FOR_MOON_PHASES = new int[] {
		0, 0, 0, 0, 0, 0, 0, 0
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
			
			if(relic == 5 || relic == 19) {
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.uselessDiceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				return relicStacks[relic].copy();
			}else if(relic == 11){
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.diceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				return new ItemStack(ModItems.hestiachastity);
			}else if(relic == 15){
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.diceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				return new ItemStack(ModItems.aphroditegrace);
			} else if(relic == 16){
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.diceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				return new ItemStack(ModItems.hermestravelclothing);
			} 
			else
			{	
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.diceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				return relicStacks[relic].copy();
			}
		}

		return stack;
	}

	@Override
	public boolean shouldDamageWrongPlayer() {
		return false;
	}
}

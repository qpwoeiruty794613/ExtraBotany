package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import com.meteor.extrabotany.common.lib.LibItemName;

public class ItemDice20 extends ItemRelicAdv{
	
	public ItemDice20() {
		super(LibItemName.DICE20);
	}

	private static final int[] SIDES_FOR_MOON_PHASES = new int[] {
		1,2,3,4,0,-1,-2,-3
	};
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(isRightPlayer(player, stack) && !player.worldObj.isRemote) {
			Random rand = new Random();
			int moonPhase = world.provider.getMoonPhase(world.getWorldTime());
			int side = SIDES_FOR_MOON_PHASES[moonPhase];
			int r = (int) (rand.nextInt(10) + world.rand.nextInt(11) + side - Math.min(player.motionX + player.motionY + player.motionZ, 3) - (player.onGround ? 1:0) + (player.isBurning() ? 1:0));
			int relic = Math.min(Math.max(r, 0), 19);
			
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
					
					if(relic == 5 || relic == 19) 
						player.addChatMessage(new ChatComponentTranslation("botaniamisc.uselessDiceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
					else player.addChatMessage(new ChatComponentTranslation("botaniamisc.diceRoll", relic + 1).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
					
					switch(relic){
						case 0 :
							return new ItemStack(ModItems.theseusship);
						case 1:
							return new ItemStack(ModItems.hermeswand);
						case 2:
							return new ItemStack(vazkii.botania.common.item.ModItems.infiniteFruit);
						case 3:
							return new ItemStack(ModItems.excaliberfake);
						case 4:
							return new ItemStack(vazkii.botania.common.item.ModItems.kingKey);
						case 5:
							return new ItemStack(ModItems.material,1,3);
						case 6:
							return new ItemStack(vazkii.botania.common.item.ModItems.flugelEye);
						case 7:
							return new ItemStack(vazkii.botania.common.item.ModItems.thorRing);
						case 8:
							return new ItemStack(vazkii.botania.common.item.ModItems.lokiRing);
						case 9:
							return new ItemStack(vazkii.botania.common.item.ModItems.odinRing);
						case 10:
							return new ItemStack(ModItems.athenabless);
						case 11:
							return new ItemStack(ModItems.hestiachastity);
						case 12:
							return new ItemStack(ModItems.maxwelldemon);
						case 13:
							return new ItemStack(ModItems.vrangerboots);
						case 14:
							return new ItemStack(ModItems.cronusphantom);
						case 15:
							return new ItemStack(ModItems.aphroditegrace);
						case 16:
							return new ItemStack(ModItems.hermestravelclothing);
						case 17:
							return new ItemStack(ModItems.cthulhueye);
						case 18:
							return new ItemStack(ModItems.lokighostrick);
						case 19:
							return new ItemStack(ModItems.material,1,3);
					}
					
			}

		return stack;
	}

	@Override
	public boolean shouldDamageWrongPlayer() {
		return false;
	}
}

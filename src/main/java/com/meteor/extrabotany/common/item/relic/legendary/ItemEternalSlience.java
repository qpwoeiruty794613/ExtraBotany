package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockSnow;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ItemEternalSlience extends ItemRelicAdv implements IManaItem, IManaTooltipDisplay{
	
	private static final int MAX_MANA = 50000;

	private static final String TAG_MANA = "mana";
	private static final String TAG_ONE_USE = "oneUse";
	private static final String TAG_MODE = "mode";

	public ItemEternalSlience(String name) {
		super(name);
		setMaxStackSize(1);
		setMaxDamage(1000);
		setNoRepair();
	}
	
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		// Empty
		par3List.add(new ItemStack(par1, 1));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if(!player.worldObj.isRemote){
			if(count <= this.getMaxItemUseDuration(stack) - 30 && player.isSneaking()){
				setMode(stack, !isMode(stack));
				player.addChatMessage(new ChatComponentTranslation("botaniamisc.eternalslience" + isMode(stack)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				player.stopUsingItem();
			}
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
		super.onUpdate(stack, world, entity, i, b);
		if(!isMode(stack)){
			addMana(stack, 50);
		}else{
			if(entity instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) entity;
				int RANGE = 11;
				
				if(!player.isInWater()){
						int l = MathHelper.floor_double(player.posX);
						int j1 = MathHelper.floor_double(player.posY - 2.0D);
						int k1 = MathHelper.floor_double(player.posZ);
						for (int x = l - 2; x < l + 2; x++) {
							for (int z = k1 - 2; z < k1 + 2; z++) {
								if ((world.getBlock(x, j1, z) == Blocks.water) || (world.getBlock(x, j1, z) == Blocks.flowing_water)) {
									world.setBlock(x, j1, z, Blocks.ice, 0, 2);
								}
								if ((world.getBlock(x, j1, z) == Blocks.lava)) {
									world.setBlock(x, j1, z, Blocks.obsidian, 0, 2);
								}
							}		
						}
					}
			
				for(int x = -RANGE; x < RANGE+1; x++) {
					for(int z = -RANGE; z < RANGE+1; z++) {
						int chunkx = player.chunkCoordX+x;
						int chunkz = player.chunkCoordZ+z;
						int chunky = player.chunkCoordY;
						while(true) {
							if(chunky>255) break;
							Block block = world.getBlock(chunkx, chunky, chunkz);
							int meta = world.getBlockMetadata(chunkx, chunky, chunkz);
							if(block instanceof BlockFire || block instanceof BlockSnow) {
								world.setBlockToAir(chunkx, chunky, chunkz);
								return;
							}
							chunky++;
						}
					}
				}
			}
		}
	}
	
	public static boolean isMode(ItemStack stack){
		return ItemNBTHelper.getBoolean(stack, TAG_MODE, false);
	}
	
	public void setMode(ItemStack stack, boolean b){
		ItemNBTHelper.setBoolean(stack, TAG_MODE, b);
	}

	@Override
	public float getManaFractionForDisplay(ItemStack stack) {
		return (float) getMana(stack) / (float) getMaxMana(stack);
	}
	
	public static void setMana(ItemStack stack, int mana) {
		ItemNBTHelper.setInt(stack, TAG_MANA, mana);
	}

	@Override
	public void addMana(ItemStack stack, int mana) {
		setMana(stack, Math.min(getMana(stack) + mana, MAX_MANA));
	}

	@Override
	public boolean canExportManaToItem(ItemStack arg0, ItemStack arg1) {
		return true;
	}

	@Override
	public boolean canExportManaToPool(ItemStack arg0, TileEntity arg1) {
		return false;
	}

	@Override
	public boolean canReceiveManaFromItem(ItemStack arg0, ItemStack arg1) {
		return false;
	}

	@Override
	public boolean canReceiveManaFromPool(ItemStack arg0, TileEntity arg1) {
		return false;
	}

	@Override
	public int getMana(ItemStack stack) {
		return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
	}

	@Override
	public int getMaxMana(ItemStack stack) {
		return MAX_MANA;
	}

	@Override
	public boolean isNoExport(ItemStack arg0) {
		return false;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0 - getManaFractionForDisplay(stack);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.block;
	}
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
	}

	public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
		addStringToTooltip(StatCollector.translateToLocal("botaniamisc.eternalmode" + isMode(stack)), list);
		if(GuiScreen.isShiftKeyDown()) {
			String bind = getSoulbindUsernameS(stack);
			if(bind.isEmpty())
				addStringToTooltip(StatCollector.translateToLocal("botaniamisc.relicUnbound"), list);
			else {
				addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.relicSoulbound"), bind), list);
				if(!isRightPlayer(player, stack))
					addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.notYourSagittarius"), bind), list);
			}

			if(stack.getItem() == ModItems.aesirRing)
				addStringToTooltip(StatCollector.translateToLocal("botaniamisc.dropIkea"), list);

			if(stack.getItem() == ModItems.dice) {
				addStringToTooltip("", list);
				String name = stack.getUnlocalizedName() + ".poem";
				for(int i = 0; i < 4; i++)
					addStringToTooltip(EnumChatFormatting.ITALIC + StatCollector.translateToLocal(name + i), list);
			}
		} else addStringToTooltip(StatCollector.translateToLocal("botaniamisc.shiftinfo"), list);
	}
	
	static void addStringToTooltip(String s, List<String> tooltip) {
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}

}

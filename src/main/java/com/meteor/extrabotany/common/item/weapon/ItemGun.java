package com.meteor.extrabotany.common.item.weapon;

import java.util.List;

import vazkii.botania.common.core.helper.ItemNBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.meteor.extrabotany.api.extrabotany.item.IGun;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletExploding;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletGold;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletHighVelocity;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMeteor;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMusket;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSilver;
import com.meteor.extrabotany.common.handler.MathHandler;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGun extends ItemMods implements IGun{

	public ItemGun(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}
	
	public int selectBullet(EntityPlayer player){
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,0)))
			a = 6;//explo
		else a = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,1)))
			b = 3;//gold
		else b = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,2)))
			c = 5;//high
		else c = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,3)))
			d = 4;//meteor
		else d = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,4)))
			e = 1;
		else e = 0;
		if(player.inventory.hasItemStack(new ItemStack(ModItems.bullet,1,5)))
			f = 2;	
		else f = 0;
		int bullet;
		bullet = Math.max(MathHandler.max(a, b, c), MathHandler.max(d, e, f));
		return bullet;
	}
	
	public void consumeBullet(EntityPlayer player){
		if(!player.worldObj.isRemote){
		switch(selectBullet(player)){
		case 1:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 4).getItem());
			break;
		case 2:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 5).getItem());
			break;
		case 3:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 1).getItem());
			break;
		case 4:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 3).getItem());
			break;
		case 5:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 2).getItem());
			break;
		case 6:
			player.inventory.consumeInventoryItem(new ItemStack(ModItems.bullet, 1, 0).getItem());
			break;
			}	
		}
	}
	
	public void summonBullet(EntityPlayer player){
		EntityBulletExploding s1 = new EntityBulletExploding(player.worldObj, player);
		EntityBulletGold s2 = new EntityBulletGold(player.worldObj, player);
		EntityBulletHighVelocity s3 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletMeteor s4 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMusket s5 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletSilver s6 = new EntityBulletSilver(player.worldObj, player);
		if(!player.worldObj.isRemote){
			switch(selectBullet(player)){
			case 1:
				player.worldObj.spawnEntityInWorld(s5);
				break;
			case 2:
				player.worldObj.spawnEntityInWorld(s6);
				break;
			case 3:
				player.worldObj.spawnEntityInWorld(s2);
				break;
			case 4:
				player.worldObj.spawnEntityInWorld(s4);
				break;
			case 5:
				player.worldObj.spawnEntityInWorld(s3);
				break;
			case 6:
				player.worldObj.spawnEntityInWorld(s1);
				break;
			}	
		}
	}
	
	public void shoot(EntityPlayer player){
		selectBullet(player);
		consumeBullet(player);
		summonBullet(player);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
	    return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	public static final String TAG_MODE = "mode";
	public static final String TAG_RELOAD = "isReloading";
	public static final String TAG_AMOUNT = "reloadamount";
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingTick(stack, player, count);
		if(!player.worldObj.isRemote){
			if(!player.isSneaking()){
				if(isReload(stack) == false){
					if(selectBullet(player) == 0){
						player.addChatMessage(new ChatComponentTranslation("botaniamisc.bullet").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
						player.stopUsingItem();
					}else{
						if(count <= this.getMaxItemUseDuration(stack) - getReloadSpeed()){
							setReload(stack, true);
							setAmount(stack, getReloadAmount());
							player.stopUsingItem();
							player.addChatMessage(new ChatComponentTranslation("botaniamisc.reload").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
						}
					}
				}else if(count <= this.getMaxItemUseDuration(stack) - getShootSpeed() && isReload(stack) == true && getAmount(stack) > 0){
					if(isMode(stack) == false){
						shoot(player);
						setAmount(stack, getAmount(stack)-1);
						if(getAmount(stack) == 0){
							setReload(stack, false);
						}
					}else{
						for(int i = 0; i < getAmount(stack); i++){
							shoot(player);
						}
						setAmount(stack, 0);
						setReload(stack, false);
					}
					player.stopUsingItem();
				}
			}else if(count <= this.getMaxItemUseDuration(stack) - getReloadSpeed() && canMode()){
				setMode(stack, !isMode(stack));
				player.stopUsingItem();
			}
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return isReload(stack) ? EnumAction.bow : EnumAction.block;
	}
	
	public int getShootSpeed(){
		return 10;
	}
	
	public int getReloadSpeed(){
		return 20;
	}
	
	public int getReloadAmount(){
		return 1;
	}
	
	public boolean canMode(){
		return false;
	}
	
	public static boolean isReload(ItemStack stack){
		return ItemNBTHelper.getBoolean(stack, TAG_RELOAD, false);
	}
	
	public static void setReload(ItemStack stack, boolean b){
		ItemNBTHelper.setBoolean(stack, TAG_RELOAD, b);
	}
	
	public static int getAmount(ItemStack stack){
		return ItemNBTHelper.getInt(stack, TAG_AMOUNT, 0);
	}
	
	public static void setAmount(ItemStack stack, int i){
		ItemNBTHelper.setInt(stack, TAG_AMOUNT, i);
	}
	
	public static boolean isMode(ItemStack stack){
		return ItemNBTHelper.getBoolean(stack, TAG_MODE, false);
	}
	
	public static void setMode(ItemStack stack, boolean b){
		ItemNBTHelper.setBoolean(stack, TAG_MODE, b);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		if(canMode())
			addStringToTooltip(EnumChatFormatting.BLUE + StatCollector.translateToLocal("botaniamisc.gun" + isMode(stack) + ".desc"), list);
	}
	
	static void addStringToTooltip(String s, List<String> tooltip) {
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}

}

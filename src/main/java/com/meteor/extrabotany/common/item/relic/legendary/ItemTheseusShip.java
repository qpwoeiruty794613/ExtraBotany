package com.meteor.extrabotany.common.item.relic.legendary;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemRelic;

import com.meteor.extrabotany.common.enchantment.ModEnchantment;
import com.meteor.extrabotany.common.entity.FakePlayer;
import com.meteor.extrabotany.common.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import com.meteor.extrabotany.common.util.EnchHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTheseusShip extends ItemRelicAdv implements ILensEffect, IManaUsingItem{
	
	private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
	public static final String TAG_MODE = "mode";
	public static final String TAG_DELAY = "delay";

	public ItemTheseusShip(String name) {
		super(name);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
		super.onUpdate(stack, world, entity, i, b);
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(getDelay(stack) > 0)
				setDelay(stack, getDelay(stack) - 1);
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingTick(stack, player, count);
		int m = getMode(stack);
		if(!player.worldObj.isRemote){
			if(player.isSneaking()){
				if(count <= this.getMaxItemUseDuration(stack) - 40 && getDelay(stack) == 0){
					int mode = getMode(stack) == 3 ? 0 : getMode(stack) + 1;
					setMode(stack, mode);
					setDelay(stack, 20);
					player.addChatMessage(new ChatComponentTranslation("botaniamisc.theseussetMode" + getMode(stack)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
				}
			}else if(count % 3 == 0 && !player.isSneaking()){
					if(ItemRelic.isRightPlayer(player, stack)){
						int cost = (int) ((m == 3 ? 1 : m == 2 ? 2 : m == 1 ? 5 : 2) * EnchHelper.getDivineFavorBuff(stack) * EnchHelper.getDivineMarkBuff(stack));
						if(ManaItemHandler.requestManaExact(stack, player, cost, true)){
							player.worldObj.spawnEntityInWorld(getBurst(player, stack));
						}
					}		
				}
		}
	}
	
	public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
		EntityManaBurst burst = new EntityManaBurst(player);
		float motionModifier = 7F;
		int mode = getMode(stack);
		burst.setColor(mode == 3 ? 0xF3812B : mode == 2 ? 0x6DDC41 : mode == 1 ? 0x3786E6 : 0xDD40C3);
		burst.setMana(1);
		burst.setStartingMana(1);
		burst.setMinManaLoss(100);
		burst.setManaLossPerTick(10F);
		burst.setGravity(0F);
		burst.setMotion(burst.motionX * motionModifier, burst.motionY * motionModifier, burst.motionZ * motionModifier);
		ItemStack lens = stack.copy();
		ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.getCommandSenderName());
		burst.setSourceLens(lens);
		return burst;
	}

	@Override
	public boolean usesMana(ItemStack arg0) {
		return true;
	}

	@Override
	public void apply(ItemStack arg0, BurstProperties arg1) {
		
	}

	@Override
	public boolean collideBurst(IManaBurst arg0, MovingObjectPosition arg1,
			boolean arg2, boolean dead, ItemStack arg4) {
		return dead;
	}

	@Override
	public boolean doParticles(IManaBurst arg0, ItemStack arg1) {
		return true;
	}

	@Override
	public void updateBurst(IManaBurst burst, ItemStack stack) {
		EntityThrowable entity = (EntityThrowable) burst;
		AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(1, 1, 1);
		AxisAlignedBB axisbig = AxisAlignedBB.getBoundingBox(entity.posX - 0.2F, entity.posY - 0.2F, entity.posZ - 0.2F, entity.lastTickPosX + 0.2F, entity.lastTickPosY + 0.2F, entity.lastTickPosZ + 0.2F).expand(1, 1, 1);
		String attacker = ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, "");
		
		if(entity.ticksExisted > 3)
			entity.setDead();
		
		if(getMode(stack) == 0){
			List<IMob> mobs = entity.worldObj.getEntitiesWithinAABB(IMob.class, axis);
			for(IMob mob:mobs){
				EntityLiving m = (EntityLiving) mob;
				if(m.hurtTime == 0) {
					int cost = 1;
					int mana = burst.getMana();
					if(mana >= cost) {
						burst.setMana(mana - cost);
						float damage = 3F * EnchHelper.getDivineFavorBuff(stack);
						if(!burst.isFake() && !entity.worldObj.isRemote) {
							EntityPlayer player = m.worldObj.getPlayerEntityByName(attacker);
							m.attackEntityFrom(player == null ? DamageSource.magic : DamageSource.causePlayerDamage(player), damage);
							entity.setDead();
							break;
						}
					}
				}
			}
		}
		
		if(getMode(stack) == 1){
			List<EntityItem> items = entity.worldObj.getEntitiesWithinAABB(EntityItem.class, axisbig);
			for(EntityItem item : items){
				if(item.age > 59){
					ItemStack s = item.getEntityItem();
					if(s.getItemDamage() > 0){
						if(!burst.isFake() && !entity.worldObj.isRemote){
							s.setItemDamage(Math.max(0, (int) (s.getItemDamage() - 1 * EnchHelper.getDivineFavorBuff(stack))));
							entity.setDead();
							break;
						}
					}
				}
			}
		}
		
		if(getMode(stack) == 2){
			List<EntityLiving> livings = entity.worldObj.getEntitiesWithinAABB(EntityLiving.class, axis);
			for(EntityLiving l : livings){
				if(!(l instanceof IMob)){
					l.heal(3F * EnchHelper.getDivineFavorBuff(stack));
				}
			}
		}
		
		if(getMode(stack) == 3){
			List<EntityLivingBase> livings = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
			for(EntityLivingBase l : livings){
				if(!(l instanceof IMob) && (l != entity.getThrower())){
					l.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 1, 50));
					l.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 1, 50));
					if(l instanceof EntityPlayer)
						PropertyHandler.addShieldAmount(0.2F * EnchHelper.getDivineFavorBuff(stack), ((EntityPlayer)l));
				}
			}
		}
	}
	
	public static int getMode(ItemStack stack){
		return ItemNBTHelper.getInt(stack, TAG_MODE, 0);
	}
	
	public static void setMode(ItemStack stack, int i){
		ItemNBTHelper.setInt(stack, TAG_MODE, i);
	}
	
	public static int getDelay(ItemStack stack){
		return ItemNBTHelper.getInt(stack, TAG_DELAY, 0);
	}
	
	public static void setDelay(ItemStack stack, int i){
		ItemNBTHelper.setInt(stack, TAG_DELAY, i);
	}
	
	
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
	}

	public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
		addStringToTooltip(EnumChatFormatting.BLUE + StatCollector.translateToLocal("botaniamisc.theseusmode" + getMode(stack) + ".desc"), list);
		if(GuiScreen.isShiftKeyDown()) {
			String bind = getSoulbindUsernameS(stack);
			if(bind.isEmpty())
				addStringToTooltip(StatCollector.translateToLocal("botaniamisc.relicUnbound"), list);
			else {
				addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.relicSoulbound"), bind), list);
				if(!isRightPlayer(player, stack))
					addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.notYourSagittarius"), bind), list);
			}
		} else addStringToTooltip(StatCollector.translateToLocal("botaniamisc.shiftinfo"), list);
	}
	
	static void addStringToTooltip(String s, List<String> tooltip) {
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
	    return true;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

}

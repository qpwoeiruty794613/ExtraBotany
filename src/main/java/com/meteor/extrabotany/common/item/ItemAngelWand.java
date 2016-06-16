package com.meteor.extrabotany.common.item;

import java.util.ArrayList;
import java.util.List;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemAngelWand extends ItemMods implements IManaUsingItem{
	
	private static final float RANGE = 1.5F;
	
	private static final String TAG_TARGET = "target";
	private static final String TAG_TICKS_TILL_EXPIRE = "ticksTillExpire";
	private static final String TAG_DIST = "dist";

	public ItemAngelWand(String name) {
		super(name);
		setMaxStackSize(1);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity par3Entity, int p_77663_4_, boolean p_77663_5_) {
		if(!(par3Entity instanceof EntityPlayer))
			return;
		
		int ticksTillExpire = ItemNBTHelper.getInt(stack, TAG_TICKS_TILL_EXPIRE, 0);
		
		if(ticksTillExpire == 0) {
			ItemNBTHelper.setInt(stack, TAG_TARGET, -1);
			ItemNBTHelper.setDouble(stack, TAG_DIST, -1);
		}
		
		ticksTillExpire--;
		ItemNBTHelper.setInt(stack, TAG_TICKS_TILL_EXPIRE, ticksTillExpire);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		super.onItemRightClick(stack, world, player);
		
		int targetID = ItemNBTHelper.getInt(stack, TAG_TARGET, -1);
		double length = ItemNBTHelper.getDouble(stack, TAG_DIST, -1);
		
		Entity item = null;
		if(targetID != -1 && player.worldObj.getEntityByID(targetID) != null) {
			Entity taritem = player.worldObj.getEntityByID(targetID);
			
			boolean found = false;
			Vector3 target = Vector3.fromEntityCenter(player);
			List<Entity> entities = new ArrayList<Entity>();
			int distance = 1;
			while(entities.size() == 0 && distance < 45) {
				target.add(new Vector3(player.getLookVec()).multiply(distance));

				target.y += 0.5;
				entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(target.x - RANGE, target.y - RANGE, target.z - RANGE, target.x + RANGE, target.y + RANGE, target.z + RANGE));
				distance++;
				if(entities.contains(taritem))
					found = true;
			}

			if(found)
				item = player.worldObj.getEntityByID(targetID);
		}
		
		if(item == null) {
			Vector3 target = Vector3.fromEntityCenter(player);
			List<Entity> entities = new ArrayList<Entity>();
			int distance = 1;
			while(entities.size() == 0 && distance < 45) {
				target.add(new Vector3(player.getLookVec()).multiply(distance));

				target.y += 0.5;
				entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(target.x - RANGE, target.y - RANGE, target.z - RANGE, target.x + RANGE, target.y + RANGE, target.z + RANGE));
				distance++;
			}

			if(entities.size() > 0) {
				item = entities.get(0);
				length = 5.5D;
				if(item instanceof EntityItem)
					length = 2.0D;
			}
		}
		
		if(item != null) {
			if(BotaniaAPI.isEntityBlacklistedFromGravityRod(item.getClass()))
				return stack;
			
			if(ManaItemHandler.requestManaExactForTool(stack, player, 1, true)) {
				
				if(item instanceof EntityLivingBase) {
					EntityLivingBase targetEntity = (EntityLivingBase)item;
					Vector3 sourceVector = new Vector3(targetEntity.posX + 0.5, targetEntity.posY + 0.5, targetEntity.posZ + 0.5);
					Vector3 playerVector = Vector3.fromEntityCenter(player);
					Vector3 motion = sourceVector.copy().sub(playerVector).copy().normalize();

					player.motionX = motion.x * 1.4F;
					player.motionY = motion.y + 0.05;
					player.motionZ = motion.z * 1.4F;
				}
				
				ItemNBTHelper.setInt(stack, TAG_TARGET, item.getEntityId());
				ItemNBTHelper.setDouble(stack, TAG_DIST, length);
			}
		}
		
		if(item != null)
			ItemNBTHelper.setInt(stack, TAG_TICKS_TILL_EXPIRE, 5);
		
		return stack;
		
	}

	@Override
	public boolean usesMana(ItemStack arg0) {
		return true;
	}

}

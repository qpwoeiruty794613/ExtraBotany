package com.meteor.extrabotany.common.block.subtile;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;

import com.meteor.extrabotany.common.entity.EntitySpear;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

public class SubTileJudasvow extends SubTileFunctional implements IManaUsingItem{

	private static final int RANGE = 4;
	
	@Override
	public int getColor() {
		return 0xF5EC3D;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();	
		if(ticksExisted >= 100){
			this.supertile.getWorldObj().func_147480_a(this.supertile.xCoord, this.supertile.yCoord, this.supertile.zCoord, true);
			Botania.proxy.sparkleFX(this.supertile.getWorldObj(), this.supertile.xCoord + 0.5F, this.supertile.yCoord, this.supertile.zCoord + 0.5F, 1.99F, 0.97F, 0.20F, 4F, 10);
		}
		
		List<EntityLivingBase> livings = supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
		for(EntityLivingBase living : livings) {
			if(!(living instanceof EntityPlayer))
			living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 8));
		}
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(ManaItemHandler.requestManaExact(stack, player, 400, true)){
				List<EntityLivingBase> livings = supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
				for(EntityLivingBase living : livings) {
					if(!(living instanceof EntityPlayer)){
						EntitySpear weapon = new EntitySpear(player.worldObj, player);
						weapon.posX = living.posX;
						weapon.posY = living.posY + 12;
						weapon.posZ = living.posZ;
						weapon.rotationYaw = player.rotationYaw;
						weapon.setVariety(this.supertile.getWorldObj().rand.nextInt(12));
						weapon.setDelay(15);
						weapon.setRotation(MathHelper.wrapAngleTo180_float(-player.rotationYaw +180));
						player.worldObj.spawnEntityInWorld(weapon);
					}						
				}
			}
		}
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.judasvow;
	}

	@Override
	public boolean usesMana(ItemStack arg0) {
		return true;
	}
}

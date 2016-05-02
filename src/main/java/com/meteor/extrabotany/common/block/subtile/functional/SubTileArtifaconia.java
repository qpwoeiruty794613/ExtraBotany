package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.List;

import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileArtifaconia extends SubTileFunctional{
	
	private static final int RANGE = 4;
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(mana >= 2000) {
			int slowdown = getSlowdownFactor();
			List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
			for(EntityItem item : items) {
				if(item.age >= (59 + slowdown) && !item.isDead && item.getEntityItem().getItem() instanceof IRelic) {
					List<EntityPlayer> players = supertile.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
					for(EntityPlayer player : players) {
							IRelic r = (IRelic)item.getEntityItem().getItem();
							if(r.getSoulbindUsername(item.getEntityItem())!= player.getDisplayName()){
							r.bindToUsername(player.getDisplayName(), item.getEntityItem());
							mana-=2000;
							switch(supertile.getWorldObj().rand.nextInt(5)){
							case 0:
								supertile.getWorldObj().createExplosion(item, supertile.xCoord, supertile.yCoord, supertile.zCoord, 4, false);
								break;
							case 1:
								EntityLightningBolt l = new EntityLightningBolt(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord);
								supertile.getWorldObj().spawnEntityInWorld(l);
								EntityLightningBolt l1 = new EntityLightningBolt(supertile.getWorldObj(), supertile.xCoord + supertile.getWorldObj().rand.nextInt(10)/10, supertile.yCoord, supertile.zCoord + supertile.getWorldObj().rand.nextInt(10)/10);
								supertile.getWorldObj().spawnEntityInWorld(l1);
								EntityLightningBolt l2 = new EntityLightningBolt(supertile.getWorldObj(), supertile.xCoord - supertile.getWorldObj().rand.nextInt(10)/10, supertile.yCoord, supertile.zCoord - supertile.getWorldObj().rand.nextInt(10)/10);
								supertile.getWorldObj().spawnEntityInWorld(l2);
								break;
							case 2:
								EntityWolf w = new EntityWolf(supertile.getWorldObj());
								w.setPosition(supertile.xCoord + supertile.getWorldObj().rand.nextInt(10)/10, supertile.yCoord, supertile.zCoord + supertile.getWorldObj().rand.nextInt(10)/10);
								w.setAngry(true);
								EntityWolf w1 = new EntityWolf(supertile.getWorldObj());
								w1.setPosition(supertile.xCoord + supertile.getWorldObj().rand.nextInt(10)/10, supertile.yCoord, supertile.zCoord + supertile.getWorldObj().rand.nextInt(10)/10);
								w1.setAngry(true);
								EntityWolf w2 = new EntityWolf(supertile.getWorldObj());
								w2.setPosition(supertile.xCoord + supertile.getWorldObj().rand.nextInt(10)/10, supertile.yCoord, supertile.zCoord + supertile.getWorldObj().rand.nextInt(10)/10);
								w2.setAngry(true);
								supertile.getWorldObj().spawnEntityInWorld(w);
								supertile.getWorldObj().spawnEntityInWorld(w1);
								supertile.getWorldObj().spawnEntityInWorld(w2);
								break;
							case 3:
								EntityIronGolem g = new EntityIronGolem(supertile.getWorldObj());
								g.setPosition(supertile.xCoord + supertile.getWorldObj().rand.nextInt(10)/10, supertile.yCoord, supertile.zCoord + supertile.getWorldObj().rand.nextInt(10)/10);
								g.setAttackTarget(player);
								supertile.getWorldObj().spawnEntityInWorld(g);
								break;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public int getColor() {
		return 0x5AF4F6;
	}
	
	@Override
	public int getMaxMana() {
		return 30000;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
}

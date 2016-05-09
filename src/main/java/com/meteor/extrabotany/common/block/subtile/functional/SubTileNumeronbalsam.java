package com.meteor.extrabotany.common.block.subtile.functional;

import java.util.Collection;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;

import com.meteor.extrabotany.common.lexicon.LexiconModData;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class SubTileNumeronbalsam extends SubTileFunctional{
	private static final int RANGE = 11;
	private static final int DELAY = 5;
		
	@Override
	public int getColor() {
		return 0x0FA5757;
	}
		
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.numeronbalsam;
	}
		
	@Override
	public void onUpdate() {
		super.onUpdate();		
		if(redstoneSignal > 0)
			return;
			
		List<EntityLivingBase> livings = supertile.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord - RANGE, supertile.zCoord - RANGE, supertile.xCoord + RANGE + 1, supertile.yCoord + RANGE + 1, supertile.zCoord + RANGE + 1));
		for(EntityLivingBase living : livings) {
			if(ticksExisted % DELAY == 0){
				if(living instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) living;
					player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 100, 0));
					if(player.isBurning() == false){
						player.setFire(5);	
					}	
					mana--;
					Collection<PotionEffect> potions = player.getActivePotionEffects();
					boolean flag = false;
					for (PotionEffect potion : potions) {
						int id = potion.getPotionID();
						if (ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})) {
							player.removePotionEffect(id);
							mana--;
							flag = true;
							}
							break;
	        			}
					}
						
					living.setFire(5);			
					living.attackEntityFrom(DamageSource.lava, 1);
					
				}
			}	
		
		ChunkCoordinates chunk = toChunkCoordinates();
		World world = supertile.getWorldObj();
		for(int x = -RANGE; x < RANGE+1; x++) {
			for(int z = -RANGE; z < RANGE+1; z++) {
				int chunkx = chunk.posX+x;
				int chunkz = chunk.posZ+z;
				int chunky = chunk.posY;
				while(true) {
					if(chunky>255) break;
					Block block = world.getBlock(chunkx, chunky, chunkz);
					int meta = world.getBlockMetadata(chunkx, chunky, chunkz);
					if(block instanceof BlockFire) {
						mana--;
						world.setBlockToAir(chunkx, chunky, chunkz);
						Botania.proxy.sparkleFX(world, chunkx + 0.5F, chunky, chunkz + 0.5F, 2.5F, 0.87F, 0.87F, 4F, 10);
						return;
					}
					chunky++;
				}
			}
		}
		}
	
	@Override
	public int getMaxMana() {
		return 400;
	}
		
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
		
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RANGE);
	}
}

package com.meteor.extrabotany.common.item.relic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.meteor.extrabotany.common.entity.bullet.EntityBulletHighVelocity;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMeteor;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletMusket;
import com.meteor.extrabotany.common.entity.bullet.EntityBulletSilver;
import com.meteor.extrabotany.common.item.ModItems;

public class ItemGunTacticalShotgun extends ItemGunRelic{

	public ItemGunTacticalShotgun(String name) {
		super(name);
	}
	
	int shootspeed = 44;
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		super.onUsingTick(stack, player, count);
			if(count <= this.getMaxItemUseDuration(stack)- shootspeed && count % shootspeed == 0){
				if(ItemGunRelic.isRightPlayer(player, stack))
					shoot(player);
			}
	}
	
	@Override
	public ItemStack selectBullet(EntityPlayer player){
		if(player.inventory.hasItemStack(s2))
			return s2;
		else if(player.inventory.hasItemStack(s3))
			return s3;
		else if(player.inventory.hasItemStack(s5))
			return s5;
		else if(player.inventory.hasItemStack(s6))
			return s6;
		else return s6;
	}
	
	@Override
	public void summonBullet(EntityPlayer player){
		EntityBulletHighVelocity s3 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletHighVelocity s32 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletHighVelocity s33 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletHighVelocity s34 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletHighVelocity s35 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletHighVelocity s36 = new EntityBulletHighVelocity(player.worldObj, player);
		EntityBulletMeteor s4 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMeteor s42 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMeteor s43 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMeteor s44 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMeteor s45 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMeteor s46 = new EntityBulletMeteor(player.worldObj, player);
		EntityBulletMusket s5 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletMusket s52 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletMusket s53 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletMusket s54 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletMusket s55 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletMusket s56 = new EntityBulletMusket(player.worldObj, player);
		EntityBulletSilver s6 = new EntityBulletSilver(player.worldObj, player);
		EntityBulletSilver s62 = new EntityBulletSilver(player.worldObj, player);
		EntityBulletSilver s63 = new EntityBulletSilver(player.worldObj, player);
		EntityBulletSilver s64 = new EntityBulletSilver(player.worldObj, player);
		EntityBulletSilver s65 = new EntityBulletSilver(player.worldObj, player);
		EntityBulletSilver s66 = new EntityBulletSilver(player.worldObj, player);
		if(!player.worldObj.isRemote){
			switch(selectBullet(player).getItemDamage()){
			case 4:
				s5.setThrowableHeading(s5.motionX, s5.motionY, s5.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s5);
				s52.setThrowableHeading(s52.motionX + player.worldObj.rand.nextInt(5)/100, s52.motionY + player.worldObj.rand.nextInt(5)/100, s52.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s52);
				s53.setThrowableHeading(s53.motionX + player.worldObj.rand.nextInt(5)/100, s53.motionY + player.worldObj.rand.nextInt(5)/100, s53.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s53);
				s54.setThrowableHeading(s54.motionX + player.worldObj.rand.nextInt(5)/100, s54.motionY + player.worldObj.rand.nextInt(5)/100, s54.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s54);
				s55.setThrowableHeading(s55.motionX + player.worldObj.rand.nextInt(5)/100, s55.motionY + player.worldObj.rand.nextInt(5)/100, s55.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s55);
				s56.setThrowableHeading(s56.motionX + player.worldObj.rand.nextInt(5)/100, s56.motionY + player.worldObj.rand.nextInt(5)/100, s56.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s56);
				break;
			case 5:
				s6.setThrowableHeading(s6.motionX, s6.motionY, s6.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s6);
				s62.setThrowableHeading(s62.motionX + player.worldObj.rand.nextInt(5)/100, s62.motionY + player.worldObj.rand.nextInt(5)/100, s62.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s62);
				s63.setThrowableHeading(s63.motionX + player.worldObj.rand.nextInt(5)/100, s63.motionY + player.worldObj.rand.nextInt(5)/100, s63.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s63);
				s64.setThrowableHeading(s64.motionX + player.worldObj.rand.nextInt(5)/100, s64.motionY + player.worldObj.rand.nextInt(5)/100, s64.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s64);
				s65.setThrowableHeading(s65.motionX + player.worldObj.rand.nextInt(5)/100, s65.motionY + player.worldObj.rand.nextInt(5)/100, s65.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s65);
				s66.setThrowableHeading(s66.motionX + player.worldObj.rand.nextInt(5)/100, s66.motionY + player.worldObj.rand.nextInt(5)/100, s66.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s66);
				break;
			case 3:
				s4.setThrowableHeading(s4.motionX, s4.motionY, s4.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s4);
				s42.setThrowableHeading(s42.motionX + player.worldObj.rand.nextInt(5)/100, s42.motionY + player.worldObj.rand.nextInt(5)/100, s42.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s42);
				s43.setThrowableHeading(s43.motionX + player.worldObj.rand.nextInt(5)/100, s43.motionY + player.worldObj.rand.nextInt(5)/100, s43.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s43);
				s44.setThrowableHeading(s44.motionX + player.worldObj.rand.nextInt(5)/100, s44.motionY + player.worldObj.rand.nextInt(5)/100, s44.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s44);
				s45.setThrowableHeading(s45.motionX + player.worldObj.rand.nextInt(5)/100, s45.motionY + player.worldObj.rand.nextInt(5)/100, s45.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s45);
				s46.setThrowableHeading(s46.motionX + player.worldObj.rand.nextInt(5)/100, s46.motionY + player.worldObj.rand.nextInt(5)/100, s46.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s46);
				break;
			case 2:
				s3.setThrowableHeading(s3.motionX, s3.motionY, s3.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s3);
				s32.setThrowableHeading(s32.motionX + player.worldObj.rand.nextInt(5)/100, s32.motionY + player.worldObj.rand.nextInt(5)/100, s32.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s32);
				s33.setThrowableHeading(s33.motionX + player.worldObj.rand.nextInt(5)/100, s33.motionY + player.worldObj.rand.nextInt(5)/100, s33.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s33);
				s34.setThrowableHeading(s34.motionX + player.worldObj.rand.nextInt(5)/100, s34.motionY + player.worldObj.rand.nextInt(5)/100, s34.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s34);
				s35.setThrowableHeading(s35.motionX + player.worldObj.rand.nextInt(5)/100, s35.motionY + player.worldObj.rand.nextInt(5)/100, s35.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s35);
				s36.setThrowableHeading(s36.motionX + player.worldObj.rand.nextInt(5)/100, s36.motionY + player.worldObj.rand.nextInt(5)/100, s36.motionZ, 1.0F, 1.0F);
				player.worldObj.spawnEntityInWorld(s36);
				break;
			}	
		}
	}

}

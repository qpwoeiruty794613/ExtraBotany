package com.meteor.extrabotany.common.item.relic;

import com.meteor.extrabotany.common.entity.bullet.EntityBulletSnowball;
import com.meteor.extrabotany.common.item.relic.legendary.ItemHermesTravelClothing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemGunSnowballCannon extends ItemRelicAdv{

	public ItemGunSnowballCannon(String name) {
		super(name);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
	    return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		EntityBulletSnowball snowball = new EntityBulletSnowball(world, player);
		if(ItemRelic.isRightPlayer(player, stack)){
				 if(player.inventory.hasItem(Items.snowball)) {
					 	player.inventory.consumeInventoryItem(Items.snowball);
					 	world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));			 	
					 	if(!world.isRemote){
					 		world.spawnEntityInWorld(snowball);
					 	}
		      }
		}
		return stack;
	}

}

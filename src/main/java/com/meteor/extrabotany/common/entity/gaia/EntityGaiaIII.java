package com.meteor.extrabotany.common.entity.gaia;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.entity.EntityGaiaQuickened;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGaiaIII extends EntityCreature implements IBotaniaBossWithShader {
	private static com.meteor.extrabotany.common.item.ModItems instance = new com.meteor.extrabotany.common.item.ModItems();
	public static final int SPAWN_TICKS = 160;
	private static final float RANGE = 12F;
	private static final float MAX_HP = 1000F;

	public static final int MOB_SPAWN_START_TICKS = 20;
	public static final int MOB_SPAWN_END_TICKS = 80;
	public static final int MOB_SPAWN_BASE_TICKS = 800;
	public static final int MOB_SPAWN_TICKS = MOB_SPAWN_BASE_TICKS + MOB_SPAWN_START_TICKS + MOB_SPAWN_END_TICKS;
	public static final int MOB_SPAWN_WAVES = 10;
	public static final int MOB_SPAWN_WAVE_TIME = MOB_SPAWN_BASE_TICKS / MOB_SPAWN_WAVES;

	private static final String TAG_INVUL_TIME = "invulTime";
	private static final String TAG_AGGRO = "aggro";
	private static final String TAG_SOURCE_X = "sourceX";
	private static final String TAG_SOURCE_Y = "sourceY";
	private static final String TAG_SOURCE_Z = "sourcesZ";
	private static final String TAG_MOB_SPAWN_TICKS = "mobSpawnTicks";
	private static final String TAG_HARD_MODE = "hardMode";
	private static final String TAG_PLAYER_COUNT = "playerCount";

	private static final int[][] PYLON_LOCATIONS = new int[][] {
		{ 4, 1, 4 },
		{ 4, 1, -4 },
		{ -4, 1, 4 },
		{ -4, 1, -4 }
	};

	private static final List<String> CHEATY_BLOCKS = Arrays.asList(new String[] {
			"OpenBlocks:beartrap",
			"ThaumicTinkerer:magnet"
	});

	boolean spawnLandmines = false;
	boolean spawnPixies = false;
	boolean anyWithArmor = false;

	List<String> playersWhoAttacked = new ArrayList();

	private static boolean isPlayingMusic = false;

	public EntityGaiaIII(World par1World) {
		super(par1World);
		setSize(0.6F, 1.8F);
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
		isImmuneToFire = true;
		experienceValue = 1625;
	}

	public static MultiblockSet makeMultiblockSet() {
		Multiblock mb = new Multiblock();

		for(int[] p : PYLON_LOCATIONS)
			mb.addComponent(p[0], p[1] + 1, p[2], com.meteor.extrabotany.common.block.ModBlocks.pylon, 2);

		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				mb.addComponent(new BeaconComponent(new ChunkCoordinates(i - 1, 0, j - 1)));

		mb.addComponent(new BeaconBeamComponent(new ChunkCoordinates(0, 1, 0)));
		mb.setRenderOffset(0, -1, 0);

		return mb.makeSet();
	}

	public static boolean spawn(EntityPlayer player, ItemStack par1ItemStack, World par3World, int par4, int par5, int par6, boolean hard) {
		if(par3World.getTileEntity(par4, par5, par6) instanceof TileEntityBeacon && isTruePlayer(player)) {
			if(par3World.difficultySetting == EnumDifficulty.PEACEFUL) {
				if(!par3World.isRemote)
					player.addChatMessage(new ChatComponentTranslation("botaniamisc.peacefulNoob").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
				return false;
			}

			for(int[] coords : PYLON_LOCATIONS) {
				int x = par4 + coords[0];
				int y = par5 + coords[1];
				int z = par6 + coords[2];

				Block blockat = par3World.getBlock(x, y, z);
				int meta = par3World.getBlockMetadata(x, y, z);
				if(blockat != com.meteor.extrabotany.common.block.ModBlocks.pylon || meta != 2) {
					if(!par3World.isRemote)
						player.addChatMessage(new ChatComponentTranslation("botaniamisc.needsCatalysts").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
					return false;
				}
			}

			if(!hasProperArena(par3World, par4, par5, par6)) {
				for(int i = 0; i < 360; i += 8) {
					float r = 1F;
					float g = 0F;
					float b = 1F;
					float rad = i * (float) Math.PI / 180F;
					double x = par4 + 0.5 - Math.cos(rad) * RANGE;
					double y = par5 + 0.5;
					double z = par6 + 0.5 - Math.sin(rad) * RANGE;

					Botania.proxy.sparkleFX(par3World, x, y, z, r, g, b, 5F, 120);
				}

				if(!par3World.isRemote)
					player.addChatMessage(new ChatComponentTranslation("botaniamisc.badArena").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
				return false;
			}

			par1ItemStack.stackSize--;

			if(par3World.isRemote)
				return true;

			EntityGaiaIII e = new EntityGaiaIII(par3World);
			e.setPosition(par4 + 0.5, par5 + 3, par6 + 0.5);
			e.setInvulTime(SPAWN_TICKS);
			e.setHealth(1F);
			e.setSource(par4, par5, par6);
			e.setMobSpawnTicks(MOB_SPAWN_TICKS);
			String b = LibItemName.BINDING;
			ItemStack s1 = new ItemStack(instance.excaliber);
			ItemRelic.bindToUsernameS(b, s1);
			ItemStack s2 = new ItemStack(instance.hestiachastity);
			ItemRelic.bindToUsernameS(b, s2);
			ItemStack s3 = new ItemStack(instance.hermestravelclothing);
			ItemRelic.bindToUsernameS(b, s3);
			ItemStack s4 = new ItemStack(instance.aphroditegrace);
			ItemRelic.bindToUsernameS(b, s4);
			ItemStack s5 = new ItemStack(instance.vrangerboots);
			ItemRelic.bindToUsernameS(b, s5);
			e.setCurrentItemOrArmor(0, s1);
			e.setCurrentItemOrArmor(1, s2);
			e.setCurrentItemOrArmor(2, s3);
			e.setCurrentItemOrArmor(3, s4);
			e.setCurrentItemOrArmor(4, s5);
			e.setEquipmentDropChance(0, 0);
			e.setEquipmentDropChance(1, 0);
			e.setEquipmentDropChance(2, 0);
			e.setEquipmentDropChance(3, 0);
			e.setEquipmentDropChance(4, 0);
			e.setHardMode(hard);

			List<EntityPlayer> players = e.getPlayersAround();
			int playerCount = 0;
			for(EntityPlayer p : players)
				if(isTruePlayer(p))
					playerCount++;

			e.setPlayerCount(playerCount);
			e.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(MAX_HP * playerCount);

			par3World.playSoundAtEntity(e, "mob.enderdragon.growl", 10F, 0.1F);
			par3World.spawnEntityInWorld(e);
			return true;
		}
		return false;
	}

	private static boolean hasProperArena(World world, int sx, int sy, int sz) {
		int heightCheck = 3;
		int heightMin = 2;
		int range = (int) Math.ceil(RANGE);
		for(int i = -range; i < range + 1; i++)
			for(int j = -range; j < range + 1; j++) {
				if(Math.abs(i) == 4 && Math.abs(j) == 4 || vazkii.botania.common.core.helper.MathHelper.pointDistancePlane(i, j, 0, 0) > RANGE)
					continue; // Ignore pylons and out of circle

				int x = sx + i;
				int z = sz + j;
				int air = 0;

				yCheck: {
					for(int k = heightCheck + heightMin + 1; k >= -heightCheck; k--) {
						int y = sy + k;
						boolean isAir = world.getBlock(x, y, z).getCollisionBoundingBoxFromPool(world, x, y, z) == null;
						if(isAir)
							air++;
						else {
							if(k > heightCheck)
								continue;
							else if(air > 2)
								break yCheck;
							air = 0;
						}
					}

					return false;
				}
			}

		return true;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, 0); // Invul Time
		dataWatcher.addObject(21, (byte) 0); // Aggro
		dataWatcher.addObject(22, 0); // TP Delay
		dataWatcher.addObject(23, 0); // Source X
		dataWatcher.addObject(24, 0); // Source Y
		dataWatcher.addObject(25, 0); // Source Z
		dataWatcher.addObject(26, 0); // Ticks spawning mobs
		dataWatcher.addObject(27, (byte) 0); // Hard Mode
		dataWatcher.addObject(28, 0); // Player count
	}

	public int getInvulTime() {
		return dataWatcher.getWatchableObjectInt(20);
	}

	public boolean isAggored() {
		return dataWatcher.getWatchableObjectByte(21) == 1;
	}

	public int getTPDelay() {
		return dataWatcher.getWatchableObjectInt(22);
	}

	public ChunkCoordinates getSource() {
		int x = dataWatcher.getWatchableObjectInt(23);
		int y = dataWatcher.getWatchableObjectInt(24);
		int z = dataWatcher.getWatchableObjectInt(25);
		return new ChunkCoordinates(x, y, z);
	}

	public int getMobSpawnTicks() {
		return dataWatcher.getWatchableObjectInt(26);
	}

	public boolean isHardMode() {
		return dataWatcher.getWatchableObjectByte(27) == 1;
	}

	public int getPlayerCount() {
		return dataWatcher.getWatchableObjectInt(28);
	}

	public void setInvulTime(int time) {
		dataWatcher.updateObject(20, time);
	}

	public void setAggroed(boolean aggored) {
		dataWatcher.updateObject(21, (byte) (aggored ? 1 : 0));
	}

	public void setTPDelay(int delay) {
		dataWatcher.updateObject(22, delay);
	}

	public void setSource(int x, int y, int z) {
		dataWatcher.updateObject(23, x);
		dataWatcher.updateObject(24, y);
		dataWatcher.updateObject(25, z);
	}

	public void setMobSpawnTicks(int ticks) {
		dataWatcher.updateObject(26, ticks);
	}

	public void setHardMode(boolean hardMode) {
		dataWatcher.updateObject(27, (byte) (hardMode ? 1 : 0));
	}

	public void setPlayerCount(int count) {
		dataWatcher.updateObject(28, count);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger(TAG_INVUL_TIME, getInvulTime());
		par1nbtTagCompound.setBoolean(TAG_AGGRO, isAggored());
		par1nbtTagCompound.setInteger(TAG_MOB_SPAWN_TICKS, getMobSpawnTicks());

		ChunkCoordinates source = getSource();
		par1nbtTagCompound.setInteger(TAG_SOURCE_X, source.posX);
		par1nbtTagCompound.setInteger(TAG_SOURCE_Y, source.posY);
		par1nbtTagCompound.setInteger(TAG_SOURCE_Z, source.posZ);

		par1nbtTagCompound.setBoolean(TAG_HARD_MODE, isHardMode());
		par1nbtTagCompound.setInteger(TAG_PLAYER_COUNT, getPlayerCount());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		setInvulTime(par1nbtTagCompound.getInteger(TAG_INVUL_TIME));
		setAggroed(par1nbtTagCompound.getBoolean(TAG_AGGRO));
		setMobSpawnTicks(par1nbtTagCompound.getInteger(TAG_MOB_SPAWN_TICKS));

		int x = par1nbtTagCompound.getInteger(TAG_SOURCE_X);
		int y = par1nbtTagCompound.getInteger(TAG_SOURCE_Y);
		int z = par1nbtTagCompound.getInteger(TAG_SOURCE_Z);
		setSource(x, y, z);

		setHardMode(par1nbtTagCompound.getBoolean(TAG_HARD_MODE));
		if(par1nbtTagCompound.hasKey(TAG_PLAYER_COUNT))
			setPlayerCount(par1nbtTagCompound.getInteger(TAG_PLAYER_COUNT));
		else setPlayerCount(1);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity e = par1DamageSource.getEntity();
		if((par1DamageSource.damageType.equals("player") || e instanceof EntityPixie) && e != null && isTruePlayer(e) && getInvulTime() == 0) {
			EntityPlayer player = (EntityPlayer) e;
			if(!playersWhoAttacked.contains(player.getCommandSenderName()))
				playersWhoAttacked.add(player.getCommandSenderName());

			float dmg = par2;
			boolean crit = false;
			if(e instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) e;
				crit = p.fallDistance > 0.0F && !p.onGround && !p.isOnLadder() && !p.isInWater() && !p.isPotionActive(Potion.blindness) && p.ridingEntity == null;
			}

			int cap = (int) (this.getMaxHealth() * 0.12F);
			return super.attackEntityFrom(par1DamageSource, Math.min(par2, cap));
		}
		return false;
	}

	private static final Pattern FAKE_PLAYER_PATTERN = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");
	public static boolean isTruePlayer(Entity e) {
		if(!(e instanceof EntityPlayer))
			return false;

		EntityPlayer player = (EntityPlayer) e;

		String name = player.getCommandSenderName();
		return !(player instanceof FakePlayer || FAKE_PLAYER_PATTERN.matcher(name).matches());
	}

	@Override
	protected void damageEntity(DamageSource par1DamageSource, float par2) {
		super.damageEntity(par1DamageSource, par2);

		Entity attacker = par1DamageSource.getEntity();
		if(attacker != null) {
			Vector3 thisVector = Vector3.fromEntityCenter(this);
			Vector3 playerVector = Vector3.fromEntityCenter(attacker);
			Vector3 motionVector = thisVector.copy().sub(playerVector).copy().normalize().multiply(0.75);

			if(getHealth() > 0) {
				motionX = -motionVector.x;
				motionY = 0.5;
				motionZ = -motionVector.z;
				setTPDelay(4);
				spawnPixies = isAggored();
			}

			setAggroed(true);
		}
	}

	@Override
	public void onDeath(DamageSource p_70645_1_) {
		super.onDeath(p_70645_1_);
		EntityLivingBase entitylivingbase = func_94060_bK();
		if(entitylivingbase instanceof EntityPlayer) {
			((EntityPlayer) entitylivingbase).addStat(ModAchievement.Gaia_gaia3Kill, 1);
			if(!anyWithArmor)
				((EntityPlayer) entitylivingbase).addStat(ModAchievement.Gaia_gaia3NoArmor, 1);
		}
		
		EntityGaiaIIIDark.spawn(this.worldObj, getSource().posX,getSource().posY,getSource().posZ, true, this);

		worldObj.playSoundAtEntity(this, "random.explode", 20F, (1F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.spawnParticle("hugeexplosion", posX, posY, posZ, 1D, 0D, 0D);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MAX_HP);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		
	}

	@Override
	public void setDead() {
		ChunkCoordinates source = getSource();
		Botania.proxy.playRecordClientSided(worldObj, source.posX, source.posY, source.posZ, null);
		isPlayingMusic = false;
		super.setDead();
	}

	public List<EntityPlayer> getPlayersAround() {
		ChunkCoordinates source = getSource();
		float range = 15F;
		List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(source.posX + 0.5 - range, source.posY + 0.5 - range, source.posZ + 0.5 - range, source.posX + 0.5 + range, source.posY + 0.5 + range, source.posZ + 0.5 + range));
		return players;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		boolean rankII = false;
		boolean rankIII = false;
		
		if(this.getHealth() <= this.getMaxHealth()*0.5)
			rankII = true;
		else rankII = false;
		if(this.getHealth() <= this.getMaxHealth()*0.25)
			rankIII = true;
		else rankIII = false;
		
		int maxTries = rankIII ? 35 : rankII ? 55 : 90;

		if(ridingEntity != null) {
			if(ridingEntity.riddenByEntity != null)
				ridingEntity.riddenByEntity = null;
			ridingEntity = null;
		}

		boolean peaceful = worldObj.difficultySetting == EnumDifficulty.PEACEFUL;
		if(!worldObj.isRemote && peaceful)
			setDead();

		if(!worldObj.isRemote) {
			int radius = 1;
			int posXInt = MathHelper.floor_double(posX);
			int posYInt = MathHelper.floor_double(posY);
			int posZInt = MathHelper.floor_double(posZ);
			for(int i = -radius; i < radius + 1; i++)
				for(int j = -radius; j < radius + 1; j++)
					for(int k = -radius; k < radius + 1; k++) {
						int xp = posXInt + i;
						int yp = posYInt + j;
						int zp = posZInt + k;
						if(isCheatyBlock(worldObj, xp, yp, zp)) {
							Block block = worldObj.getBlock(xp, yp, zp);
							List<ItemStack> items = block.getDrops(worldObj, xp, yp, zp, 0, 0);
							for(ItemStack stack : items) {
								if(ConfigHandler.blockBreakParticles)
									worldObj.playAuxSFX(2001, xp, yp, zp, Block.getIdFromBlock(block) + (worldObj.getBlockMetadata(xp, yp, zp) << 12));
								worldObj.spawnEntityInWorld(new EntityItem(worldObj, xp + 0.5, yp + 0.5, zp + 0.5, stack));
							}
							worldObj.setBlockToAir(xp, yp, zp);
						}
					}
		}

		ChunkCoordinates source = getSource();
		boolean hard = isHardMode();
		float range = RANGE + 3F;
		List<EntityPlayer> players = getPlayersAround();
		int playerCount = getPlayerCount();

		if(worldObj.isRemote && !isPlayingMusic && !isDead && !players.isEmpty()) {
			Botania.proxy.playRecordClientSided(worldObj, source.posX, source.posY, source.posZ, (ItemRecord) com.meteor.extrabotany.common.item.ModItems.recordA);
			isPlayingMusic = true;
		}

		range = RANGE;
		for(int i = 0; i < 360; i += 8) {
			float r = 0.6F;
			float g = 0F;
			float b = 0.2F;
			float m = 0.15F;
			float mv = 0.35F;

			float rad = i * (float) Math.PI / 180F;
			double x = source.posX + 0.5 - Math.cos(rad) * range;
			double y = source.posY + 0.5;
			double z = source.posZ + 0.5 - Math.sin(rad) * range;

			Botania.proxy.wispFX(worldObj, x, y, z, r, g, b, 0.5F, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * mv, (float) (Math.random() - 0.5F) * m);
		}

		if(players.isEmpty() && !worldObj.playerEntities.isEmpty())
			setDead();
		else {
			for(EntityPlayer player : players) {
				if(player.inventory.armorInventory[0] != null || player.inventory.armorInventory[1] != null || player.inventory.armorInventory[2] != null || player.inventory.armorInventory[3] != null)
					anyWithArmor = true;

				List<PotionEffect> remove = new ArrayList();
				Collection<PotionEffect> active = player.getActivePotionEffects();
				for(PotionEffect effect : active)
					if(effect.getDuration() < 200 && effect.getIsAmbient() && !ReflectionHelper.<Boolean, Potion>getPrivateValue(Potion.class, Potion.potionTypes[effect.getPotionID()], LibObfuscation.IS_BAD_EFFECT))
						remove.add(effect);

				active.removeAll(remove);

				player.capabilities.isFlying = player.capabilities.isFlying && player.capabilities.isCreativeMode;

				if(vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(player.posX, player.posY, player.posZ, source.posX + 0.5, source.posY + 0.5, source.posZ + 0.5) >= range) {
					Vector3 sourceVector = new Vector3(source.posX + 0.5, source.posY + 0.5, source.posZ + 0.5);
					Vector3 playerVector = Vector3.fromEntityCenter(player);
					Vector3 motion = sourceVector.copy().sub(playerVector).copy().normalize();

					player.motionX = motion.x;
					player.motionY = 0.2;
					player.motionZ = motion.z;
				}
			}
		}

		if(isDead)
			return;

		int invul = getInvulTime();
		int mobTicks = getMobSpawnTicks();
		boolean spawnMissiles = hard && ticksExisted % 15 < 4;

		if(invul > 10) {
			Vector3 pos = Vector3.fromEntityCenter(this).subtract(new Vector3(0, 0.2, 0));
			for(int i = 0; i < PYLON_LOCATIONS.length; i++) {
				int[] arr = PYLON_LOCATIONS[i];
				int x = arr[0];
				int y = arr[1];
				int z = arr[2];

				Vector3 pylonPos = new Vector3(source.posX + x, source.posY + y, source.posZ + z);
				double worldTime = ticksExisted;
				worldTime /= 5;

				float rad = 0.75F + (float) Math.random() * 0.05F;
				double xp = pylonPos.x + 0.5 + Math.cos(worldTime) * rad;
				double zp = pylonPos.z + 0.5 + Math.sin(worldTime) * rad;

				Vector3 partPos = new Vector3(xp, pylonPos.y, zp);
				Vector3 mot = pos.copy().sub(partPos).multiply(0.04);

				float r = 0.7F + (float) Math.random() * 0.3F;
				float g = (float) Math.random() * 0.3F;
				float b = 0.7F + (float) Math.random() * 0.3F;

				Botania.proxy.wispFX(worldObj, partPos.x, partPos.y, partPos.z, r, g, b, 0.25F + (float) Math.random() * 0.1F, -0.075F - (float) Math.random() * 0.015F);
				Botania.proxy.wispFX(worldObj, partPos.x, partPos.y, partPos.z, r, g, b, 0.4F, (float) mot.x, (float) mot.y, (float) mot.z);
			}
		}

		if(invul > 0 && mobTicks == MOB_SPAWN_TICKS) {
			if(invul < SPAWN_TICKS)  {
				if(invul > SPAWN_TICKS / 2 && worldObj.rand.nextInt(SPAWN_TICKS - invul + 1) == 0)
					for(int i = 0; i < 2; i++)
						spawnExplosionParticle();
			}

			if(!worldObj.isRemote) {
				setHealth(getHealth() + (getMaxHealth() - 1F) / SPAWN_TICKS);
				setInvulTime(invul - 1);
			}
			motionY = 0;
		} else {
			if(isAggored()) {
				boolean dying = getHealth() / getMaxHealth() < 0.2;
				if(dying && mobTicks > 0) {
					motionX = 0;
					motionY = 0;
					motionZ = 0;

					int reverseTicks = MOB_SPAWN_TICKS - mobTicks;
					if(reverseTicks < MOB_SPAWN_START_TICKS) {
						motionY = 0.2;
						setInvulTime(invul + 1);
					}

					if(reverseTicks > MOB_SPAWN_START_TICKS * 2 && mobTicks > MOB_SPAWN_END_TICKS && mobTicks % MOB_SPAWN_WAVE_TIME == 0 && !worldObj.isRemote) {
						for(int pl = 0; pl < playerCount; pl++)
							for(int i = 0; i < 3 + worldObj.rand.nextInt(2); i++) {
								EntityLiving entity = null;
								switch(worldObj.rand.nextInt(2)) {
								case 0 : {
									entity = new EntityZombie(worldObj);
									((EntityZombie) entity).setCurrentItemOrArmor(0, new ItemStack(ModItems.terraSword));
									((EntityZombie) entity).setCurrentItemOrArmor(1, new ItemStack(ModItems.terrasteelHelm));
									((EntityZombie) entity).setCurrentItemOrArmor(2, new ItemStack(ModItems.terrasteelChest));
									((EntityZombie) entity).setCurrentItemOrArmor(3, new ItemStack(ModItems.terrasteelLegs));
									((EntityZombie) entity).setCurrentItemOrArmor(4, new ItemStack(ModItems.terrasteelBoots));
									((EntityZombie) entity).setEquipmentDropChance(0, 0);
									((EntityZombie) entity).setEquipmentDropChance(1, 0);
									((EntityZombie) entity).setEquipmentDropChance(2, 0);
									((EntityZombie) entity).setEquipmentDropChance(3, 0);
									((EntityZombie) entity).setEquipmentDropChance(4, 0);
									if(worldObj.rand.nextInt(hard ? 3 : 12) == 0)
										entity = new EntityWitch(worldObj);

									break;
								}
								case 1 : {
									entity = new EntitySkeleton(worldObj);
									((EntitySkeleton) entity).setCurrentItemOrArmor(0, new ItemStack(Items.bow));
									((EntitySkeleton) entity).setCurrentItemOrArmor(0, new ItemStack(Items.bow));
									((EntitySkeleton) entity).setCurrentItemOrArmor(1, new ItemStack(ModItems.elementiumHelm));
									((EntitySkeleton) entity).setCurrentItemOrArmor(2, new ItemStack(ModItems.elementiumChest));
									((EntitySkeleton) entity).setCurrentItemOrArmor(3, new ItemStack(ModItems.elementiumLegs));
									((EntitySkeleton) entity).setCurrentItemOrArmor(4, new ItemStack(ModItems.elementiumBoots));
									((EntitySkeleton) entity).setEquipmentDropChance(0, 0);
									((EntitySkeleton) entity).setEquipmentDropChance(1, 0);
									((EntitySkeleton) entity).setEquipmentDropChance(2, 0);
									((EntitySkeleton) entity).setEquipmentDropChance(3, 0);
									((EntitySkeleton) entity).setEquipmentDropChance(4, 0);
									if(worldObj.rand.nextInt(8) == 0) {
										((EntitySkeleton) entity).setSkeletonType(1);
										((EntitySkeleton) entity).setCurrentItemOrArmor(0, new ItemStack(hard ? ModItems.elementiumSword : Items.stone_sword));
									}
									break;
								}
								case 3 : {
									if(!players.isEmpty())
										for(int j = 0; j < 1 + worldObj.rand.nextInt(hard ? 8 : 5); j++) {
											EntityPixie pixie = new EntityPixie(worldObj);
											pixie.setProps(players.get(rand.nextInt(players.size())), this, 1, 8);
											pixie.setPosition(posX + width / 2, posY + 2, posZ + width / 2);
											worldObj.spawnEntityInWorld(pixie);
										}
								}
								}

								if(entity != null) {
									range = 6F;
									entity.setPosition(posX + 0.5 + Math.random() * range - range / 2, posY - 1, posZ + 0.5 + Math.random() * range - range / 2);
									worldObj.spawnEntityInWorld(entity);
								}
							}

						if(hard && ticksExisted % 3 < 2) {
							for(int i = 0; i < playerCount; i++)
								spawnMissile();
							spawnMissiles = false;
						}
					}

					setMobSpawnTicks(mobTicks - 1);
					setTPDelay(10);
				} else if(getTPDelay() > 0 && !worldObj.isRemote) {
					if(invul > 0)
						setInvulTime(invul - 1);

					setTPDelay(getTPDelay() - 1);
					if(getTPDelay() == 0 && getHealth() > 0) {
						int tries = 0;
						while(!teleportRandomly() && tries < maxTries)
							tries++;
						if(tries >= maxTries)
							teleportTo(source.posX + 0.5, source.posY + 1.6, source.posZ + 0.5);

						if(spawnLandmines) {
							int count = dying && hard ? 7 : 6;
							for(int i = 0; i < count; i++) {
								int x = source.posX - 10 + rand.nextInt(20);
								int z = source.posZ - 10 + rand.nextInt(20);
								int y = worldObj.getTopSolidOrLiquidBlock(x, z);

								EntityMagicLandmineII landmine = new EntityMagicLandmineII(worldObj);
								landmine.setPosition(x + 0.5, y, z + 0.5);
								landmine.summoner = this;
								worldObj.spawnEntityInWorld(landmine);
							}

						}
							
						if(!players.isEmpty())
							for(int pl = 0; pl < playerCount; pl++)
								for(int i = 0; i < (spawnPixies ? worldObj.rand.nextInt(hard ? 6 : 3) : 1); i++) {
									EntityPixie pixie = new EntityPixie(worldObj);
									pixie.setProps(players.get(rand.nextInt(players.size())), this, 1, 8);
									pixie.setPosition(posX + width / 2, posY + 2, posZ + width / 2);
									worldObj.spawnEntityInWorld(pixie);
								}

						setTPDelay(hard ? (dying ? 45 : 55) : (dying ? 50 : 70));
						spawnLandmines = true;
						spawnPixies = false;
					}
				}

				if(spawnMissiles)
					spawnMissile();
			} else {
				range = 3F;
				players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));
				if(!players.isEmpty())
					damageEntity(DamageSource.causePlayerDamage(players.get(0)), 0);
			}
		}
		
		Collection<PotionEffect> potions = this.getActivePotionEffects();
		boolean flag = false;
		for (PotionEffect potion : potions) {
			int id = potion.getPotionID();
			if (ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "field_76418_K", "J"})) {
				this.removePotionEffect(id);
				flag = true;
				}
				break;
		}
	
		Botania.proxy.sparkleFX(this.worldObj, this.posX, this.posY, this.posZ, 1.99F, 0.97F, 0.20F, 2F + this.hurtTime * 3F * (this.getHealth()/this.getMaxHealth()) * Math.max(0, this.worldObj.rand.nextInt(4)- 2), 6);
		
		if(ticksExisted % 80 == 0 && rankII && onGround){
			spawnCyclone();
		}
		
		if(getTPDelay() > 0 && onGround && ticksExisted % 100 == 0 && rankIII && Math.random() > 0.55){
			EntityGaiaQuickened g = new EntityGaiaQuickened(this, true, 16F);
			g.setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(g);
		}
	}

	void spawnMissile() {
		if(!worldObj.isRemote) {
			EntityMagicMissileII missile = new EntityMagicMissileII(this, true);
			missile.setPosition(posX + (Math.random() - 0.5 * 0.1), posY + 2.4 + (Math.random() - 0.5 * 0.1), posZ + (Math.random() - 0.5 * 0.1));
			missile.setATK(4F);
			if(missile.getTarget()) {
				worldObj.playSoundAtEntity(this, "botania:missile", 0.6F, 0.8F + (float) Math.random() * 0.2F);
				worldObj.spawnEntityInWorld(missile);
			}
		}
	}
	
	void spawnCyclone() {
		if(!worldObj.isRemote) {
			List<EntityMagicCycloneIgnis> ignis = this.worldObj.getEntitiesWithinAABB(EntityMagicCycloneIgnis.class, AxisAlignedBB.getBoundingBox(this.posX - RANGE - 24, this.posY - RANGE - 24, this.posZ - RANGE - 24, this.posX + RANGE + 25, this.posY + RANGE + 25, this.posZ + RANGE + 25));
			List<EntityMagicCycloneAqua> aqua = this.worldObj.getEntitiesWithinAABB(EntityMagicCycloneAqua.class, AxisAlignedBB.getBoundingBox(this.posX - RANGE - 24, this.posY - RANGE - 24, this.posZ - RANGE - 24, this.posX + RANGE + 25, this.posY + RANGE + 25, this.posZ + RANGE + 25));
			if((ignis.size() + aqua.size()) <= 9){
				if(Math.random() >= 0.5)
					EntityMagicCycloneIgnis.spawn(worldObj, this.posX, this.posY, this.posZ, (float) (1.0F + Math.random() * 1F), (float) (1.0F + Math.random() * 1F));
					worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "botania:summon", 1.0F, 1.0F);
				}
				else {
					EntityMagicCycloneAqua.spawn(worldObj, this.posX + 2.0F - Math.random() * 4F, this.posY, this.posZ + 2.0F - Math.random() * 4F, (float) (1.0F + Math.random() * 1F), (float) (1.0F + Math.random() * 1F));
					worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "botania:summon", 1.0F, 1.0F);
				}
			}
	}

	public static boolean isCheatyBlock(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		String name = Block.blockRegistry.getNameForObject(block);
		return CHEATY_BLOCKS.contains(name);
	}

	// EntityEnderman code below ============================================================================

	protected boolean teleportRandomly() {
		double d0 = posX + (rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = posY + (rand.nextInt(64) - 32);
		double d2 = posZ + (rand.nextDouble() - 0.5D) * 64.0D;
		return teleportTo(d0, d1, d2);
	}

	protected boolean teleportTo(double par1, double par3, double par5) {
		double d3 = posX;
		double d4 = posY;
		double d5 = posZ;
		posX = par1;
		posY = par3;
		posZ = par5;
		boolean flag = false;
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(posY);
		int k = MathHelper.floor_double(posZ);

		if(worldObj.blockExists(i, j, k)) {
			boolean flag1 = false;

			while(!flag1 && j > 0) {
				Block block = worldObj.getBlock(i, j - 1, k);

				if(block.getMaterial().blocksMovement())
					flag1 = true;
				else {
					--posY;
					--j;
				}
			}

			if(flag1) {
				setPosition(posX, posY, posZ);

				if(worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox))
					flag = true;

				// Prevent out of bounds teleporting
				ChunkCoordinates source = getSource();
				if(vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(posX, posY, posZ, source.posX, source.posY, source.posZ) > 12)
					flag = false;
			}
		}

		if (!flag) {
			setPosition(d3, d4, d5);
			return false;
		} else  {
			short short1 = 128;

			for(int l = 0; l < short1; ++l)  {
				double d6 = l / (short1 - 1.0D);
				float f = (rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (posX - d3) * d6 + (rand.nextDouble() - 0.5D) * width * 2.0D;
				double d8 = d4 + (posY - d4) * d6 + rand.nextDouble() * height;
				double d9 = d5 + (posZ - d5) * d6 + (rand.nextDouble() - 0.5D) * width * 2.0D;
				worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
			}
			if(!this.worldObj.isRemote){
				List<EntityGaiaIIIPhantom> livings = this.worldObj.getEntitiesWithinAABB(EntityGaiaIIIPhantom.class, AxisAlignedBB.getBoundingBox(this.posX - RANGE - 24, this.posY - RANGE - 24, this.posZ - RANGE - 24, this.posX + RANGE + 25, this.posY + RANGE + 25, this.posZ + RANGE + 25));
				if(livings.size() <= 1 && this.getHealth() <= this.getMaxHealth() * 0.75)
					EntityGaiaIIIPhantom.spawn(this.worldObj, this.posX, this.posY, this.posZ, this);
					worldObj.playSoundEffect(d3, d4, d5, "botania:summon", 1.0F, 1.0F);
			}
			worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getBossBarTexture() {
		return LibReference.BAR_BOSS;
	}

	@SideOnly(Side.CLIENT)
	private static Rectangle barRect;
	@SideOnly(Side.CLIENT)
	private static Rectangle hpBarRect;

	@Override
	@SideOnly(Side.CLIENT)
	public Rectangle getBossBarTextureRect() {
		if(barRect == null)
			barRect = new Rectangle(0, 0, 185, 15);
		return barRect;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Rectangle getBossBarHPTextureRect() {
		if(hpBarRect == null)
			hpBarRect = new Rectangle(0, barRect.y + barRect.height, 181, 7);
		return hpBarRect;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void bossBarRenderCallback(ScaledResolution res, int x, int y) {
		GL11.glPushMatrix();
		int px = x + 160;
		int py = y + 12;

		Minecraft mc = Minecraft.getMinecraft();
		ItemStack stack = new ItemStack(Items.skull, 1, 3);
		mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
		net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack, px, py);
		net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();

		boolean unicode = mc.fontRenderer.getUnicodeFlag();
		mc.fontRenderer.setUnicodeFlag(true);
		mc.fontRenderer.drawStringWithShadow("" + getPlayerCount(), px + 15, py + 4, 0xFFFFFF);
		mc.fontRenderer.setUnicodeFlag(unicode);
		GL11.glPopMatrix();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBossBarShaderProgram(boolean background) {
		return background ? 0 : ShaderHelper.dopplegangerBar;
	}

	@SideOnly(Side.CLIENT)
	private ShaderCallback shaderCallback;

	@Override
	@SideOnly(Side.CLIENT)
	public ShaderCallback getBossBarShaderCallback(boolean background, int shader) {
		if(shaderCallback == null)
			shaderCallback = new ShaderCallback() {

			@Override
			public void call(int shader) {
				int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB(shader, "grainIntensity");
				int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB(shader, "hpFract");

				float time = getInvulTime();
				float grainIntensity = time > 20 ? 1F : Math.max(isHardMode() ? 0.5F : 0F, time / 20F);

				ARBShaderObjects.glUniform1fARB(grainIntensityUniform, grainIntensity);
				ARBShaderObjects.glUniform1fARB(hpFractUniform, getHealth() / getMaxHealth());
			}

		};

		return background ? null : shaderCallback;
	}

	public static class BeaconComponent extends MultiblockComponent {

		public BeaconComponent(ChunkCoordinates relPos) {
			super(relPos, com.meteor.extrabotany.common.block.ModBlocks.elvenquartz, 0);
		}

		@Override
		public boolean matches(World world, int x, int y, int z) {
			return world.getBlock(x, y, z).isBeaconBase(world, x, y, z, x - relPos.posX, y - relPos.posY, z - relPos.posZ);
		};

	}

	public static class BeaconBeamComponent extends MultiblockComponent {

		public BeaconBeamComponent(ChunkCoordinates relPos) {
			super(relPos, Blocks.beacon, 0);
		}

		@Override
		public boolean matches(World world, int x, int y, int z) {
			return world.getTileEntity(x, y, z) instanceof TileEntityBeacon;
		}
	}
}

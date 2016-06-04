package com.meteor.extrabotany.common.entity;

import java.lang.ref.WeakReference;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

import com.mojang.authlib.GameProfile;

public class FakePlayer {
	private static GameProfile gameProfile;
    private static WeakReference<EntityPlayerMP> fakePlayer;

    public FakePlayer()
    {
        gameProfile = new GameProfile(UUID.fromString("C3F2EF82-E759-53EA-9D69-0D6E394A00B8"), "[ExtraBotany]");
        fakePlayer = new WeakReference<EntityPlayerMP>(null);
    }

    public static WeakReference<EntityPlayerMP> getFakePlayer(WorldServer server)
    {
        if (fakePlayer.get() == null)
        {
            fakePlayer = new WeakReference<EntityPlayerMP>(FakePlayerFactory.get(server, gameProfile));
        }
        else
        {
            fakePlayer.get().worldObj = server;
        }
        return fakePlayer;
    }
}

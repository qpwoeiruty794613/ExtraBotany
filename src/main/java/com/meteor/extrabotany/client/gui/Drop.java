package com.meteor.extrabotany.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.proxy.ClientProxy;

public class Drop extends Gui{
    private int x;
    private int waveX;
    private int y;
    private int index;
    private boolean dead;
    private int friction;
    private int frictionTemp;
    private long initTime;
    private int rotation;


    public Drop(int screenW, int screenH) {
        this.initTime = Minecraft.getSystemTime();
        this.index = ClientProxy.jingleTheBells ? GuiDrop.RNG.nextInt(2)*16 : 100;
        this.friction = this.frictionTemp = GuiDrop.RNG.nextInt(5);
        this.x = GuiDrop.RNG.nextInt(screenW + 16) - 16;
        this.y = -16;
    }

    public void draw(int screenW, int screenH, int mouseX, int mouseY) {
        if (this.isDead()) return;

        int posX = mouseX - this.waveX;
        int posY = mouseY - this.y;
        if (Math.abs(posX) < 50 && Math.abs(posY) < 50) {
            int str = (int) Math.round((1f - ((1F / (float) (50)) * Math.sqrt(posX * posX + posY * posY))) * 3F);
            this.x += posX > 0 ? -str : str;
            this.y += posY > 0 ? -str : str;
            this.waveX += posX > 0 ? -str : str;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) this.waveX + 7.5F, (float) this.y + 7.5F, 0);
        GL11.glRotatef(this.rotation, 0, 0, 1);
        GL11.glTranslatef(-7.5F, -7.5F, 0);
        drawTexturedModalRect(0, 0, this.index, 0, 16, 16);
        GL11.glScalef(0.35F, 0.35F, 0.35F);
        GL11.glPopMatrix();

        if (this.frictionTemp-- <= 0) {
            this.frictionTemp = this.friction;
            this.y++;
        }
        this.waveX = this.x - (int) (Math.sin((float) (Minecraft.getSystemTime() - this.initTime) / (200F + 100F * this.friction)) * 20F);
        this.rotation += 2;
        if (this.y > screenH) this.setDead();

    }

    public void setDead() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }
}

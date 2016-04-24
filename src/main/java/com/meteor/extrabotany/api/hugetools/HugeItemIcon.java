package com.meteor.extrabotany.api.hugetools;

import net.minecraft.util.IIcon;

public class HugeItemIcon implements IIcon {

    private IIcon baseIcon;
    private float myX;
    private float myY;

    public HugeItemIcon(IIcon blockIcon, float x, float y)
    {
        this.baseIcon = blockIcon;
        this.myX = x;
        this.myY = y;
    }

    public int getIconWidth()
    {
        return (this.baseIcon.getIconWidth() / 2);
    }

    public int getIconHeight()
    {
        return (this.baseIcon.getIconHeight() / 2);
    }

    public float getMinU()
    {
        float f = this.baseIcon.getMaxU() - this.baseIcon.getMinU();
        return (this.baseIcon.getMinU() + f * this.myX);
    }

    public float getMaxU()
    {
        float f = this.baseIcon.getMaxU() - this.baseIcon.getMinU();
        return (this.baseIcon.getMinU() + f * (this.myX + 0.5F));
    }

    public float getInterpolatedU(double par1)
    {
        float f = getMaxU() - getMinU();
        return (getMinU() + f * (float)par1 / 16.0F);
    }

    public float getMinV()
    {
        float f = this.baseIcon.getMaxV() - this.baseIcon.getMinV();
        return (this.baseIcon.getMinV() + f * this.myY);
    }

    public float getMaxV()
    {
        float f = this.baseIcon.getMaxV() - this.baseIcon.getMinV();
        return (this.baseIcon.getMinV() + f * (this.myY + 0.5F));
    }

    public float getInterpolatedV(double par1)
    {
        float f = getMaxV() - getMinV();
        return (getMinV() + f * (float)par1 / 16.0F);
    }

    public String getIconName()
    {
        return this.baseIcon.getIconName();
    }
}

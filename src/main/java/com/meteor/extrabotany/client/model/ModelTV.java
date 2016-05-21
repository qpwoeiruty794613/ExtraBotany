package com.meteor.extrabotany.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTV extends ModelBase
{
	  ModelRenderer head1;

	  public ModelTV(){
	    this.textureWidth = 64;
	    this.textureHeight = 32;

	    this.head1 = new ModelRenderer(this, 0, 0);
	    this.head1.addBox(-5.0F, -12.0F, 0.0F, 16, 17, 0);
	    this.head1.setRotationPoint(0.0F, 23.0F, 0.0F);
	    this.head1.setTextureSize(64, 32);
	    this.head1.mirror = true;
	    setRotation(this.head1, 0.0F, 0.0F, 0.0F);
	  }

	  public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7){
	    super.render(entity, par2, par3, par4, par5, par6, par7);
	    setRotationAngles(par2, par3, par4, par5, par6, par7);
	    this.head1.render(par7);
	  }

	  private void setRotation(ModelRenderer model, float x, float y, float z){
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }

	  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
	  {
		  
	  }
}

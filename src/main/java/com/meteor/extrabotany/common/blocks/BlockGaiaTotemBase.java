package com.meteor.extrabotany.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockGaiaTotemBase extends BlockMods{

	public BlockGaiaTotemBase(Material material, String name) {
		super(material, name);
	}
	
	   @SideOnly(Side.CLIENT)
	    private IIcon TOTEM_TOP;
	    @SideOnly(Side.CLIENT)
	    private IIcon TOTEM_SIDE;
		 
		 @SideOnly(Side.CLIENT)
		    public IIcon getIcon(int side, int meta)
		    {	
			 	if(side == 1){
				return TOTEM_TOP;
				}else if (side == 0){
					return TOTEM_TOP;
				}else {
					return TOTEM_SIDE;
				}
			 
		    }
		 
		 @SideOnly(Side.CLIENT)
		    public void registerBlockIcons(IIconRegister register)
		    {
			 
			 this.TOTEM_TOP = register.registerIcon(this.getTextureName() + "_" + "top");
			 this.TOTEM_SIDE = register.registerIcon(this.getTextureName() + "_" + "line");
			 
		    }

}

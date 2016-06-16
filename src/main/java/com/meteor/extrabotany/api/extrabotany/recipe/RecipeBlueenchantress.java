package com.meteor.extrabotany.api.extrabotany.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.subtile.SubTileEntity;

public class RecipeBlueenchantress {
	private static final Map<String, List<ItemStack>> oreMap = new HashMap();

	Object input;
	int mana;
	int outputMeta;

	public RecipeBlueenchantress(Object input, int mana, int outputMeta) {
		this.input = input;
		this.mana = mana;
		this.outputMeta = outputMeta;

		if(input != null && !(input instanceof String || input instanceof Block))
			throw new IllegalArgumentException("input must be an oredict String or a Block.");
	}

	public boolean matches(World world, int x, int y, int z, SubTileEntity pureDaisy, Block block, int meta) {
		if(input instanceof Block)
			return block == input;

		ItemStack stack = new ItemStack(block, 1, meta);
		String oredict = (String) input;
		return isOreDict(stack, oredict);
	}

	public boolean isOreDict(ItemStack stack, String entry) {
		if(stack == null || stack.getItem() == null)
			return false;

		List<ItemStack> ores;
		if(oreMap.containsKey(entry))
			ores = oreMap.get(entry);
		else {
			ores = OreDictionary.getOres(entry);
			oreMap.put(entry, ores);
		}

		for(ItemStack ostack : ores) {
			ItemStack cstack = ostack.copy();
			if(cstack.getItemDamage() == Short.MAX_VALUE)
				cstack.setItemDamage(stack.getItemDamage());

			if(stack.isItemEqual(cstack))
				return true;
		}

		return false;
	}

	public boolean set(World world, int x, int y, int z, SubTileEntity pureDaisy) {
		if(!world.isRemote)
			world.func_147480_a(x, y, z, false);
		return true;
	}

	public Object getInput() {
		return input;
	}

	public int getMana() {
		return mana;
	}

	public int getOutputMeta() {
		return outputMeta;
	}
}

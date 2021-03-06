package com.uberverse.arkcraft.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.uberverse.arkcraft.common.config.ModuleItemBalance;
import com.uberverse.arkcraft.init.ARKCraftItems;
import com.uberverse.arkcraft.common.block.tile.TileEntityCropPlotNew.CropPlotType;

public class ARKCraftSeed extends Item
{

	private CropPlotType type;
	public ARKCraftSeed(CropPlotType type)
	{
		this.setMaxStackSize(16);
		this.setMaxDamage(ModuleItemBalance.CROP_PLOT.SECONDS_FOR_SEED_TO_DECOMPOSE); // 5
		this.type = type.SMALL;																				// minutes
																						// of
																						// damage
																						// at
																						// 1
																						// a
																						// second
	}

	public static ItemStack getBerryForSeed(ItemStack stack)
	{
		if (stack != null)
		{
			if (stack.getItem() instanceof ARKCraftSeed)
			{
				if (stack.getItem() == (Item) ARKCraftItems.amarBerrySeed) { return new ItemStack(
						ARKCraftItems.amarBerry); }
				if (stack.getItem() == (Item) ARKCraftItems.azulBerrySeed) { return new ItemStack(
						ARKCraftItems.azulBerry); }
				if (stack.getItem() == (Item) ARKCraftItems.mejoBerrySeed) { return new ItemStack(
						ARKCraftItems.mejoBerry); }
				if (stack.getItem() == (Item) ARKCraftItems.narcoBerrySeed) { return new ItemStack(
						ARKCraftItems.narcoBerry); }
				if (stack.getItem() == (Item) ARKCraftItems.tintoBerrySeed) { return new ItemStack(
						ARKCraftItems.tintoBerry); }
				if (stack.getItem() == (Item) ARKCraftItems.stimBerrySeed) { return new ItemStack(
						ARKCraftItems.stimBerrySeed); }
			}
		}
		return null;
	}

	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 *
	 * @param tooltip
	 *            All lines to display in the Item's tooltip. This is a List of
	 *            Strings.
	 * @param advanced
	 *            Whether the setting "Advanced tooltips" is enabled
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		tooltip.add("Decomposes in " + (getMaxDamage() - itemStack.getItemDamage()) + " seconds");
	}
	public CropPlotType getType() {
		return type;
	}
}

package com.uberverse.arkcraft.common.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.common.block.tile.TileEntityCropPlotNew;
import com.uberverse.arkcraft.common.item.ARKCraftSeed;

/**
 * @author wildbill22
 */
public class BlockCropPlot extends BlockContainer
{
	public static final int GROWTH_STAGES = 5; // 0 - 5
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, GROWTH_STAGES);
	private int renderType = 3; // default value
	private boolean isOpaque = false;
	private int ID;

	public BlockCropPlot(Material mat, int ID)
	{
		super(mat);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		// this.setTickRandomly(true);
		float f = 0.35F; // Height
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		this.setHardness(0.5F);
		this.ID = ID;

		this.disableStats();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCropPlotNew();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!playerIn.isSneaking())
		{
			if(playerIn.getHeldItem() != null && playerIn.getHeldItem().getItem() instanceof ARKCraftSeed && ((Integer)state.getValue(AGE)) == 0){
				ItemStack s = playerIn.getHeldItem().splitStack(1);
				s = TileEntityHopper.func_174918_a((IInventory) worldIn.getTileEntity(pos), s, null);
				if(s != null){
					if(!playerIn.inventory.addItemStackToInventory(s)){
						EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), s);
						worldIn.spawnEntityInWorld(item);
					}
				}
			}else{
				playerIn.openGui(ARKCraft.instance(), ID, worldIn, pos.getX(), pos.getY(),
						pos.getZ());
			}
			return true;
		}
		return false;
	}

	public void setRenderType(int renderType)
	{
		this.renderType = renderType;
	}

	@Override
	public int getRenderType()
	{
		return renderType;
	}

	public void setOpaque(boolean opaque)
	{
		opaque = isOpaque;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return isOpaque;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer) state.getValue(AGE)).intValue();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AGE });
	}

	/**
	 * Returns randomly, about 1/2 of the fertilizer and berries
	 */
	/*@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileInventoryCropPlot)
		{
			TileInventoryCropPlot tiCropPlot = (TileInventoryCropPlot) tileEntity;
			for (int i = 0; i < TileInventoryCropPlot.FERTILIZER_SLOTS_COUNT; ++i)
			{
				if (rand.nextInt(2) == 0)
				{
					ret.add(tiCropPlot
							.getStackInSlot(TileInventoryCropPlot.FIRST_FERTILIZER_SLOT + i));
				}
			}
			if (rand.nextInt(2) == 0)
			{
				ret.add(tiCropPlot.getStackInSlot(TileInventoryCropPlot.BERRY_SLOT));
			}
		}
		return ret;
	}*/
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile != null)InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tile);
	}
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntityCropPlotNew te = (TileEntityCropPlotNew) worldIn.getTileEntity(pos);
		te.setType(stack.getMetadata());
	}
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
		list.add(new ItemStack(itemIn, 1, 2));
	}
}

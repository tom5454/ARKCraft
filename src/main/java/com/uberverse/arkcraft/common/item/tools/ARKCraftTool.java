package com.uberverse.arkcraft.common.item.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
<<<<<<< HEAD
<<<<<<< HEAD
import net.minecraft.util.MathHelper;
=======
>>>>>>> refs/remotes/BubbleTrouble14/master
=======
import net.minecraft.util.MathHelper;
>>>>>>> refs/remotes/BubbleTrouble14/master
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Predicate;
import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.client.event.ClientEventHandler;
import com.uberverse.arkcraft.client.proxy.ClientProxy;
import com.uberverse.arkcraft.init.ARKCraftItems;
import com.uberverse.lib.Utils;

public abstract class ARKCraftTool extends ItemTool{
	private static final String DAMAGE_NBT_NAME = "damage";
	boolean arkMode;
	public static  int count = 0;
	public final ToolType toolType;
	private static final Predicate<IBlockState> WOOD_PREDICATE = new Predicate<IBlockState>() {

		@Override
		public boolean apply(IBlockState blockState) {
			return blockState.getBlock() == Blocks.log || blockState.getBlock() == Blocks.log2;
		}
	};
	private static final Predicate<IBlockState> IRON_ORE_PREDICATE = new Predicate<IBlockState>() {

		@Override
		public boolean apply(IBlockState blockState) {
			return blockState.getBlock() == Blocks.iron_ore;
		}
	};

	@SuppressWarnings("rawtypes")
	public ARKCraftTool(float attackDamage, ToolMaterial material, Set effectiveBlocks, ToolType toolType) {
		super(attackDamage, material, effectiveBlocks);
		setHasSubtypes(true);
		this.toolType = toolType;
		initToolClasses();
	}

	@SuppressWarnings("rawtypes")
	public ARKCraftTool(String name, float attackDamage, ToolMaterial material, Set effectiveBlocks, ToolType toolType){
		super(attackDamage, material, effectiveBlocks);
		this.setUnlocalizedName(name);
		setHasSubtypes(true);
		this.toolType = toolType;
		initToolClasses();
	}
	private void initToolClasses(){
		Collection c = getToolClasses(new ItemStack(this));
		for(Object o : c){
			if(o != null){
				setHarvestLevel(o.toString(), toolMaterial.getHarvestLevel());
			}
		}
	}
<<<<<<< HEAD
	//For the ItemDrop
	private void entityDropItem(World worldIn, BlockPos pos, Block block, EntityPlayer playerIn, ItemStack itemStackIn) {
		if (itemStackIn.stackSize > 0 && itemStackIn.getItem() != null) {
			Float offset = worldIn.rand.nextFloat();
			EntityItem entityitem = new EntityItem(worldIn, pos.getX() + offset, pos.getY() + block.getBlockBoundsMaxY(),
					pos.getZ() + offset, itemStackIn);
			entityitem.setDefaultPickupDelay();
			if (playerIn.captureDrops) {
				playerIn.capturedDrops.add(entityitem);
			} else {
				worldIn.spawnEntityInWorld(entityitem);
			}
		}
	}
<<<<<<< HEAD
=======
=======
>>>>>>> refs/remotes/BubbleTrouble14/master

	 //For the ItemDrop
	 private void entityDropItem(World worldIn, BlockPos pos, Block block, EntityPlayer playerIn, ItemStack itemStackIn) {
			if (itemStackIn.stackSize != 0 && itemStackIn.getItem() != null) {
				Float offset = worldIn.rand.nextFloat();
				EntityItem entityitem = new EntityItem(worldIn, pos.getX() + offset, pos.getY() + block.getBlockBoundsMaxY(),
						pos.getZ() + offset, itemStackIn);
				entityitem.setDefaultPickupDelay();
				if (playerIn.captureDrops) {
					playerIn.capturedDrops.add(entityitem);
				} else {
					worldIn.spawnEntityInWorld(entityitem);
				}
			}
		} 
<<<<<<< HEAD
>>>>>>> refs/remotes/BubbleTrouble14/master
=======
>>>>>>> refs/remotes/BubbleTrouble14/master

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
	{
		if (arkMode != ClientEventHandler.arkMode())
		{
			if(playerIn instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) playerIn;
				IBlockState blockState = worldIn.getBlockState(pos);
				if (WOOD_PREDICATE.apply(blockState))
				{
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/BubbleTrouble14/master
					this.destroyBlocks(worldIn, pos, player, stack, WOOD_PREDICATE);
					System.out.println("How many wood blocks ? " + count);
					int wood = calcOutput(count, toolType.getPickaxeModifier(), 1);
					int thatch = calcOutput(count, toolType.getPickaxeModifier(), 1);
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.wood, wood));//(int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getHatchetModifier())
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.thatch, thatch));//(int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getPickaxeModifier())

					//	System.out.println(" Wood: " + wood + " Thatch: " + thatch);
					count = 0;

<<<<<<< HEAD
=======
					this.destroyBlocks(worldIn, pos, player, stack);
					System.out.println("How many wood blocks ? " + count);
					
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.wood, (int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getPrimaryModifier())));	
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.thatch, (int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getPrimaryModifier())));	
=======

				//	this.destroyBlocks(worldIn, pos, player, stack);
					System.out.println("How many wood blocks ? " + count);
					
				//	entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.wood, (int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getPrimaryModifier())));	
				//	entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.thatch, (int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getPrimaryModifier())));	
>>>>>>> refs/remotes/BubbleTrouble14/master
					
				//	System.out.println(" Wood: " + wood + " Thatch: " + thatch);
					count = 0;
					
<<<<<<< HEAD
>>>>>>> refs/remotes/BubbleTrouble14/master
=======

>>>>>>> refs/remotes/BubbleTrouble14/master
				}else if (blockState.getBlock() == Blocks.stone)
				{
					damageTool(stack, playerIn);
					int multiplier = 0;
					{
						IBlockState blockState2 = worldIn.getBlockState(pos.up());
						if (blockState2.getBlock() == Blocks.stone){
							multiplier++;
							worldIn.destroyBlock(pos.up(), false);
						}
					}
					{
						IBlockState blockState2 = worldIn.getBlockState(pos);
						if (blockState2.getBlock() == Blocks.stone){
							multiplier++;
							worldIn.destroyBlock(pos, false);
						}
					}
					{
						IBlockState blockState2 = worldIn.getBlockState(pos.down());
						if (blockState2.getBlock() == Blocks.stone){
							multiplier++;
							worldIn.destroyBlock(pos.down(), false);
						}
					}
					EnumFacing f = Utils.getDirectionFacing(playerIn, false);
					{
						BlockPos pos2 = pos.offset(f.rotateY());
						{
							IBlockState blockState2 = worldIn.getBlockState(pos2.up());
							if (blockState2.getBlock() == Blocks.stone){
								multiplier++;
								worldIn.destroyBlock(pos2.up(), false);
							}
						}
						{
							IBlockState blockState2 = worldIn.getBlockState(pos2);
							if (blockState2.getBlock() == Blocks.stone){
								multiplier++;
								worldIn.destroyBlock(pos2, false);
							}
						}
						{
							IBlockState blockState2 = worldIn.getBlockState(pos2.down());
							if (blockState2.getBlock() == Blocks.stone){
								multiplier++;
								worldIn.destroyBlock(pos2.down(), false);
							}
						}
					}
					{
						BlockPos pos2 = pos.offset(f.rotateYCCW());
						{
							IBlockState blockState2 = worldIn.getBlockState(pos2.up());
							if (blockState2.getBlock() == Blocks.stone){
								multiplier++;
								worldIn.destroyBlock(pos2.up(), false);
							}
						}
						{
							IBlockState blockState2 = worldIn.getBlockState(pos2);
							if (blockState2.getBlock() == Blocks.stone){
								multiplier++;
								worldIn.destroyBlock(pos2, false);
							}
						}
						{
							IBlockState blockState2 = worldIn.getBlockState(pos2.down());
							if (blockState2.getBlock() == Blocks.stone){
								multiplier++;
								worldIn.destroyBlock(pos2.down(), false);
							}
						}
					}
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/BubbleTrouble14/master
					int stone = calcOutput(multiplier, toolType.getPickaxeModifier(), 1);
					int flint = calcOutput(multiplier, toolType.getHatchetModifier(), 1);
					int metal = calcOutput(multiplier, toolType.getPickaxeModifier(), 0.1D);
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.stone, stone));//(int) (10 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPickaxeModifier())
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.flint, flint));//(int) (10 + itemRand.nextInt(100)/20.0*multiplier*toolType.getHatchetModifier())
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.metal, metal));//(int) (1 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPickaxeModifier())
				}else if(IRON_ORE_PREDICATE.apply(blockState)){
					this.destroyBlocks(worldIn, pos, player, stack, IRON_ORE_PREDICATE);
					System.out.println("How many iron ore blocks ? " + count);
					int stone = calcOutput(count, toolType.getPickaxeModifier(), 0.8D);
					int metal = calcOutput(count, toolType.getPickaxeModifier(), 1);
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.metal, metal));//(int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getPickaxeModifier())
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.stone, stone));//(int) (10 + itemRand.nextInt(100)/20.0*count*toolType.getHatchetModifier())

					//	System.out.println(" Wood: " + wood + " Thatch: " + thatch);
					count = 0;
<<<<<<< HEAD
=======
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.stone, (int) (10 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPrimaryModifier())));		
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.flint, (int) (10 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPrimaryModifier())));	
					entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.metal, (int) (1 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPrimaryModifier())));	
>>>>>>> refs/remotes/BubbleTrouble14/master
=======
				//	entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.stone, (int) (10 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPrimaryModifier())));		
				//	entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.flint, (int) (10 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPrimaryModifier())));	
				//	entityDropItem(worldIn, pos, blockIn, player, new ItemStack(ARKCraftItems.metal, (int) (1 + itemRand.nextInt(100)/20.0*multiplier*toolType.getPrimaryModifier())));	
>>>>>>> refs/remotes/BubbleTrouble14/master
				}
			}
		}else{
			damageTool(stack, playerIn);
		}
		return true;
	}

	public void destroyBlocks(World world, BlockPos pos, EntityPlayer player, ItemStack stack, Predicate<IBlockState> blockChecker) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = x - 1; i <= x + 1; i++) {
			for (int k = z - 1; k <= z + 1; k++) {
				for (int j = y - 1; j <= y + 1; j++) {
					IBlockState blockState = world.getBlockState(new BlockPos(i, j, k));
					if (blockChecker.apply(blockState))
					{
						world.destroyBlock(new BlockPos(i, j, k), false);
						++count;
						if(damageTool(stack, player)){
							this.destroyBlocks(world, new BlockPos(i, j, k), player, stack, blockChecker);
						}else{
							return;
						}
					}
				}
			}
		}
	}
	private static int calcOutput(int count, float toolModifier, double materialModifier){
		double ret = 0;
		for(int i = 0;i<count;i++){
			double r = (10 + itemRand.nextInt(100)) / 30D;
			r *= materialModifier;
			r *= toolModifier;
			ret += r;
		}
		return MathHelper.floor_double(ret);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, ToolLevel.VALUES.length - 1));
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		/*ClientProxy p = ((ClientProxy)ARKCraft.proxy);
		List<ItemStack> list = new ArrayList<ItemStack>();
		getSubItems(this, getCreativeTab(), list);
		for(int i = 0;i<list.size();i++){*/
		switch(ToolLevel.VALUES[stack.getMetadata() % ToolLevel.VALUES.length]){
		case PRIMITIVE:
			return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		case RAMSCHACKLE:
			return (EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		case APPRENTICE:
			return (EnumChatFormatting.BLUE + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		case JOURNEYMAN:
			return (EnumChatFormatting.DARK_PURPLE + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		case MASTERCRAFT:
			return (EnumChatFormatting.YELLOW + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		case ASCENDANT:
			return (EnumChatFormatting.RED + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
		default:
			break;
		}
		return super.getItemStackDisplayName(stack);
	}
<<<<<<< HEAD
<<<<<<< HEAD


=======
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		ClientProxy p = ((ClientProxy)ARKCraft.proxy);
		List<ItemStack> list = new ArrayList<ItemStack>();
		getSubItems(this, getCreativeTab(), list);
		for(int i = 0;i<list.size();i++){
			if(i == 1)
			{
				 return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
			}
			else if(i == 2)
			{
				return (EnumChatFormatting.GREEN + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
			}
			else if(i == 3)
			{
				return (EnumChatFormatting.BLUE + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();		
			}
			else if(i == 4)
			{
				return (EnumChatFormatting.DARK_PURPLE + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
			}
			else if(i == 5)
			{
				return (EnumChatFormatting.YELLOW + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
			}
			else if(i == 6)
			{
				return (EnumChatFormatting.RED + "" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
			}
		}
		return null;
	}

	
>>>>>>> refs/remotes/BubbleTrouble14/master
=======
	
>>>>>>> refs/remotes/BubbleTrouble14/master
	@SideOnly(Side.CLIENT)
	public void registerModels(){//TODO: Call this from the Client proxy for each tool item.
		ClientProxy p = ((ClientProxy)ARKCraft.proxy);
		List<ItemStack> list = new ArrayList<ItemStack>();
		getSubItems(this, getCreativeTab(), list);
		for(int i = 0;i<list.size();i++){
			p.registerItemTexture(this, i, "tool_" + getUnlocalizedName().substring(5) + "_" + ToolLevel.VALUES[i].name);
		}
	}
<<<<<<< HEAD
<<<<<<< HEAD

=======
	
>>>>>>> refs/remotes/BubbleTrouble14/master
=======

	
>>>>>>> refs/remotes/BubbleTrouble14/master
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		tooltip.add(I18n.format("arkcraft.tooltip.toolLevel", ToolLevel.VALUES[stack.getMetadata() % ToolLevel.VALUES.length].getTranslatedName()));
		if(advanced){
			int max = ToolLevel.VALUES[stack.getMetadata() % ToolLevel.VALUES.length].getDurrability(toolMaterial.getMaxUses());
			tooltip.add("Durability: " + max + "/" + (max - getToolDamage(stack)));
		}
	}
<<<<<<< HEAD
<<<<<<< HEAD

=======
	
>>>>>>> refs/remotes/BubbleTrouble14/master
=======
	
>>>>>>> refs/remotes/BubbleTrouble14/master
	public int getDurrability(ItemStack stack){
		return ToolLevel.VALUES[stack.getMetadata() % ToolLevel.VALUES.length].getDurrability(toolMaterial.getMaxUses());
	}
	public int getToolDamage(ItemStack stack){
		if(stack.hasTagCompound()){
			return stack.getTagCompound().getInteger(DAMAGE_NBT_NAME);
		}
		return 0;
	}
	public void setToolDamage(ItemStack stack, int newValue){
		if(!stack.hasTagCompound())stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger(DAMAGE_NBT_NAME, newValue);
	}
	public boolean damageTool(ItemStack toolStack, EntityLivingBase entityIn){
		int newValue = getToolDamage(toolStack) + 1;
		setToolDamage(toolStack, newValue);
		if(newValue >= getDurrability(toolStack)){
			entityIn.renderBrokenItemStack(toolStack);
			--toolStack.stackSize;

			if (entityIn instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer)entityIn;
				entityplayer.triggerAchievement(StatList.objectBreakStats[Item.getIdFromItem(toolStack.getItem())]);
				if (toolStack.stackSize < 1)
				{
					entityplayer.destroyCurrentEquippedItem();
				}
			}
			return false;
		}
		return true;
	}
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return getToolDamage(stack) > 0;
	}
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return ((double)getToolDamage(stack)) / getDurrability(stack);
	}
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "_" + ToolLevel.VALUES[stack.getMetadata() % ToolLevel.VALUES.length].name;
	}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		damageTool(stack, target);
		damageTool(stack, target);
		return true;
	}
	@Override
	public boolean isDamaged(ItemStack stack) {
		return false;
	}
}

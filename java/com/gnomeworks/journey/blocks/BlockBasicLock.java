package com.gnomeworks.journey.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockBasicLock extends BlockEndPortalFrame
{
	@SideOnly(Side.CLIENT)
    private IIcon iconEndPortalFrameTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconEndPortalFrameEye;
    @SideOnly(Side.CLIENT)
    private IIcon iconEndPortalFrameBottom;
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        this.iconEndPortalFrameTop = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.iconEndPortalFrameEye = p_149651_1_.registerIcon(this.getTextureName() + "_key");
        this.iconEndPortalFrameBottom = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
    }
	
	@Override
    public IIcon getIcon(int side, int meta)
    {
    	if(side == 0)
    		return iconEndPortalFrameBottom;
    	if(side == 1)
    		return iconEndPortalFrameTop;
    	else
    		return this.blockIcon;
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconEndPortalFrameEye()
    {
        return this.iconEndPortalFrameEye;
    }
	
	public boolean canProvidePower()
    {
		return true;
    }
}

/*
	@SideOnly(Side.CLIENT)
    private static IIcon iconBasicLockKeyhole;
    @SideOnly(Side.CLIENT)
    private static IIcon iconBasicLockKey;
    @SideOnly(Side.CLIENT)
    private static IIcon iconBasicLockBottom;
    
    private int renderID;
    
    private boolean isKeyInserted;
    
    public BlockBasicLock(int foo)
    {
    	this(Material.rock);
    	this.renderID = foo;
    	
    	this.isKeyInserted = false;
    	
    	this.setCreativeTab(CreativeTabs.tabMisc);
    	
    	this.setBlockUnbreakable();
    }
    
    protected BlockBasicLock(Material p_i45394_1_) 
	{
    	super(Material.rock);
	}
    
    public void setRenderType(int foo)
    {
    	this.renderID = foo;
    }
    
    @Override
	public int getRenderType()
    {
        return this.renderID;
    }
    
    @SideOnly(Side.CLIENT)
    public static IIcon getIconBasicLockKey()
    {
        return iconBasicLockKey;
    }
    
    @SideOnly(Side.CLIENT)
    public static IIcon getIconBasicLockBottom() 
	{
		return iconBasicLockBottom;
	}
    
    public static boolean isKeyInserted(int p_150020_0_)
    {
    	return (p_150020_0_ & 4) != 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        iconBasicLockKeyhole = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        iconBasicLockKey = p_149651_1_.registerIcon(this.getTextureName() + "_key");
        iconBasicLockBottom = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
    }
    
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = ((MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
        
        System.out.println("[DC] L value is: " + l);
        
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
    }
    
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        int l = p_149743_1_.getBlockMetadata(p_149743_2_, p_149743_3_, p_149743_4_);

        if (isKeyInserted(l))
        {
            this.setBlockBounds(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        }

        this.setBlockBoundsForItemRender();
    }
    
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
}

*/
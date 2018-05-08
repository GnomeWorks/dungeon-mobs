package com.gnomeworks.journey.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;

import com.gnomeworks.journey.ColorizerDungeon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBaseDungeonBlock extends Block
{
	@SideOnly(Side.CLIENT)
    private IIcon blockIcon;
    @SideOnly(Side.CLIENT)
    private IIcon overlay;
    @SideOnly(Side.CLIENT)
    private IIcon field_149993_M;
	
	public BlockBaseDungeonBlock(Material p_i45394_1_) 
	{
		super(p_i45394_1_);
	}
	
	public boolean isOpaqueCube() 
	{
        return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) 
	{
		return this.blockIcon;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    }
	
	@SideOnly(Side.CLIENT)
    public int getBlockColor() 
	{
            double d0 = 0.5D;
            double d1 = 1.0D;
            return ColorizerDungeon.getDungeonColor(d0, d1);
    }
	
	@SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) 
	{
		return this.getBlockColor();
    }
	
	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int xCoord, int yCoord, int zCoord) 
	{
		int l = 0;
		int i1 = 0;
		int j1 = 0;

		for (int k1 = -1; k1 <= 1; ++k1) 
		{
			for (int l1 = -1; l1 <= 1; ++l1) 
			{
				BiomeGenBase foo = world.getBiomeGenForCoords(xCoord + l1, zCoord + k1);
				
				int i2 = this.getBiomeDungeonColor(foo, xCoord + l1, yCoord, zCoord + k1);
				
				//int i2 = world.getBiomeGenForCoords(xCoord + l1, zCoord + k1).getBiomeGrassColor(xCoord + l1, yCoord, zCoord + k1);
				l += (i2 & 16711680) >> 16;
				i1 += (i2 & 65280) >> 8;
				j1 += i2 & 255;
			}
		}

		return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }
	
	@SideOnly(Side.CLIENT)
    public int getBiomeDungeonColor(BiomeGenBase biome, int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        double d0 = (double)MathHelper.clamp_float(biome.getFloatTemperature(p_150558_1_, p_150558_2_, p_150558_3_), 0.0F, 1.0F);
        double d1 = (double)MathHelper.clamp_float(biome.getFloatRainfall(), 0.0F, 1.0F);
        return ColorizerDungeon.getDungeonColor(d0, d1);
    }
}

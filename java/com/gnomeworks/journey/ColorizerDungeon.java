package com.gnomeworks.journey;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerGrass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorizerDungeon
{
    /** Color buffer for grass */
    private static int[] dungeonBuffer = new int[65536];
    private static final String __OBFID = "CL_00000138";

    private static final ResourceLocation field_130078_a = new ResourceLocation("journey:textures/colormap/dungeon1.png");
    
    public static void initDungeonBiomeColorizer()
    {
    	try
        {
    		IResourceManager foo = Minecraft.getMinecraft().getResourceManager();
    		
            ColorizerDungeon.setDungeonBiomeColorizer(TextureUtil.readImageData(foo, field_130078_a));
        }
        catch (IOException ioexception)
        {
            ;
        }
    }
    
    public static void setDungeonBiomeColorizer(int[] p_77479_0_)
    {
        dungeonBuffer = p_77479_0_;
    }

    /**
     * Gets grass color from temperature and humidity. Args: temperature, humidity
     */
    public static int getDungeonColor(double p_77480_0_, double p_77480_2_)
    {
        p_77480_2_ *= p_77480_0_;
        //int i = (int)((1.0D - p_77480_0_) * 125.0D);
        int i = (int)((1.0D - p_77480_0_) * 255.0D);
        int j = (int)((1.0D - p_77480_2_) * 255.0D);
        return dungeonBuffer[j << 8 | i];
    }
}
package com.gnomeworks.journey;

/*
 * JOURNEY
 * 
 * The world is a dangerous place, mothafucka.
 * 
 * (c) GnomeWorks 2015
 * Do not distribute without permission.
 */

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;

import com.gnomeworks.journey.blocks.BlockBaseDungeonBlock;
import com.gnomeworks.journey.blocks.BlockBasicLock;
import com.gnomeworks.journey.items.ItemBasicDoor;
import com.gnomeworks.journey.items.ItemBasicKey;
import com.gw.dm.DungeonMobsEventHandler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=Journey.MODID, name="Journey", version=Journey.VERSION)
public class Journey
{
	public static final String MODID = "journey";
    public static final String VERSION = "0.0.1";
    
    @Instance(MODID)
    public static Journey instance;
    
    @SidedProxy(clientSide="com.gnomeworks.journey.ClientProxy", serverSide="com.gnomeworks.journey.CommonProxy")
    public static CommonProxy proxy;
    
    /*
    @SidedProxy(clientSide="com.gw.dm.network.DungeonMobsClient", serverSide="com.gw.dm.network.DungeonMobsServer")
	public static ISidedProxy packetProxy;
    */
    
    public static JourneyEventHandler eventsHandler = new JourneyEventHandler();
    
    public static Block basicLock;
    public static int basicLockRenderID;
    
    public static Block basicDungeonBlock;
    
    public static int labyrinthRarity;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	//MinecraftForge.EVENT_BUS.register(new DungeonMobsEventSounds());
		
		ModMetadata modmeta = event.getModMetadata();
	    modmeta.authorList = Arrays.asList(new String[] { "GnomeWorks" });
	    modmeta.autogenerated = false;
	    modmeta.credits = "";
	    modmeta.description = 
	    		"Dungeons to explore.\n";
	    		//"\n" +
	    		//"Includes support for: ExtraBiomesXL, Biomes O' Plenty, Highlands, Mob Spawn Controls";
	    //modmeta.url = "http://www.minecraftforum.net/topic/231082-";
	    
	    //FMLCommonHandler.instance().bus().register(eventsHandler);
	    //MinecraftForge.EVENT_BUS.register(eventsHandler);
	    
	    basicLockRenderID = RenderingRegistry.getNextAvailableRenderId();
	    
	    labyrinthRarity = 5;
	    
	    //FMLCommonHandler.instance().bus().register(eventsHandler);
	    MinecraftForge.EVENT_BUS.register(eventsHandler);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	MinecraftForge.TERRAIN_GEN_BUS.register(new JourneyWorldGenEvent());
    	//proxy.registerRenders();
    	
    	// BASIC LOCK
    	basicLock = new BlockBasicLock().setBlockTextureName("journey:basiclock").setBlockName("basiclock");
    	GameRegistry.registerBlock(basicLock, "BasicLock");
    	
    	ItemBasicKey basicKey = new ItemBasicKey();
    	basicKey.setTextureName("journey:basic_key");
		GameRegistry.registerItem(basicKey, "basicKey");
    	
		ItemBasicDoor basicDoor = new ItemBasicDoor(Material.iron);
		GameRegistry.registerItem(basicDoor, "basicDoor");
		
		// BASIC DUNGEON BLOCK
		basicDungeonBlock = new BlockBaseDungeonBlock(Material.rock).setBlockTextureName("journey:base_dungeon_block").setBlockName("basicdungeonblock").setHardness(-1.0F);
		GameRegistry.registerBlock(basicDungeonBlock, "BasicDungeonBlock");
		
    	proxy.registerRenders();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	ColorizerDungeon.initDungeonBiomeColorizer();
    }
}
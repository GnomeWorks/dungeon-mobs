package com.gnomeworks.journey;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.terraingen.BiomeEvent.GetGrassColor;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import cpw.mods.fml.common.eventhandler.ListenerList;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class JourneyWorldGenEvent 
{
	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event) 
	{
		switch (event.world.provider.dimensionId) 
		{
			case 0:
				if (event.type == PopulateChunkEvent.Populate.EventType.DUNGEON)
				{
					int foo = event.world.rand.nextInt(Journey.labyrinthRarity);
					
					if(foo == 0 && !this.strongholdNearby(event.chunkProvider, event.world, event.rand, event.chunkX, event.chunkZ) && !this.villageNearby(event.chunkProvider, event.world, event.rand, event.chunkX, event.chunkZ))
						this.genTest(event.chunkProvider, event.world, event.rand, event.chunkX, event.chunkZ);
					
					break;
				} 
				else
					break;
		}
	}
	
	private boolean strongholdNearby(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ) 
	{
		int posX = chunkX << 4;
		int posZ = chunkZ << 4;
		
		ChunkPosition foo = chunkProvider.func_147416_a(world, "Stronghold", posX, 64, posZ);
		
		if(posX < (foo.chunkPosX + 128) && posX > (foo.chunkPosX - 128) && posZ < (foo.chunkPosZ + 128) && posZ > (foo.chunkPosZ - 128))
			return true;
		
		return false;
	}
	
	private boolean villageNearby(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ) 
	{
		int posX = chunkX << 4;
		int posZ = chunkZ << 4;
		
		ChunkPosition foo = chunkProvider.func_147416_a(world, "Village", posX, 64, posZ);
		
		if(foo == null)
			return false;
		
		if(posX < (foo.chunkPosX + 128) && posX > (foo.chunkPosX - 128) && posZ < (foo.chunkPosZ + 128) && posZ > (foo.chunkPosZ - 128))
			return true;
		
		return false;
	}

	private boolean genTest(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ)
	{
		int posX = (chunkX << 4) + rand.nextInt(16) - 8;
		int posZ = (chunkZ << 4) + rand.nextInt(16) - 8;
		
		//okay, some other shit... whatever
		
		int posY = 128;
		
		while(world.canBlockSeeTheSky(posX, posY, posZ))
		{
			posY--;
			
			if(posY == 0)
				return false;
		}
		
		posY++;
		
		if(world.getBlock(posX, posY - 1, posZ) == Blocks.leaves 
				|| world.getBlock(posX, posY - 1, posZ) == Blocks.leaves2
				|| world.getBlock(posX, posY - 1, posZ) == Blocks.log
				|| world.getBlock(posX, posY - 1, posZ) == Blocks.log2
				|| world.getBlock(posX, posY - 1, posZ) == Blocks.water)
			return false;
		
		/*
		 * Generate the base of the thing.
		 */
		for(int k = -3; k < 1; k++)
		{
			for(int i = -2; i < 3; i++)
			{
				for(int j = -2; j < 3; j++)
				{
					world.setBlock(posX + j, posY + k, posZ + i, Journey.basicDungeonBlock, 0, 3);
				}
			}
		}
		
		int carp = posY += 4;
		
		/*
		 * Generate the ceiling.
		 */
		for(int k = 0; k < 2; k++)
		{
			for(int i = (-2 + k); i < (3 - k); i++)
			{
				for(int j = (-2 + k); j < (3 - k); j++)
				{
					world.setBlock(posX + j, carp + k, posZ + i, Journey.basicDungeonBlock, 0, 3);
				}
			}
		}
		
		posY -= 4;
		
		posY++;
		
		/*
		 * Generate pillars.
		 */
		
		int posXX = -2;
		int posZZ = -2;
		
		for(int i = 0; i < 3; i ++)
		{
			for(int j = -1; j < 1; j++)
			{
				if(j == 0)
					posXX = 2;
				else
					posXX = -2;
				
				for(int k = -1; k < 1; k++)
				{
					if(k == 0)
						posZZ = 2;
					else
						posZZ = -2;
					
					world.setBlock(posX + posXX, posY + i, posZ + posZZ, Journey.basicDungeonBlock, 0, 3);
				}
			}
		}
		
		world.setBlock(posX, posY, posZ, Journey.basicDungeonBlock, 0, 3);
		
		return true;
	}
}

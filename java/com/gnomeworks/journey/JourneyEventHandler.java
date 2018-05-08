package com.gnomeworks.journey;

import net.minecraft.block.BlockPistonBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class JourneyEventHandler 
{
	//@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void doStuff(PlayerInteractEvent event)
	{
		if(event.action == Action.RIGHT_CLICK_BLOCK)
		{
			System.out.println("FUCK YOU!");
		}
	}
}

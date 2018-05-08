package com.gnomeworks.journey.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import com.gnomeworks.journey.Journey;
import com.gnomeworks.journey.blocks.BlockBasicLock;

public class ItemBasicKey extends Item
{
	public ItemBasicKey()
    {
        super();
        setHasSubtypes(false);
        maxStackSize = 16;
        setCreativeTab(CreativeTabs.tabMisc);
        
        this.setUnlocalizedName("basickey");
        
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		Block block = world.getBlock(x, y, z);
        int i1 = world.getBlockMetadata(x, y, z);

        if (player.canPlayerEdit(x, y, z, p_77648_7_, stack) && block == Journey.basicLock && !BlockBasicLock.isEnderEyeInserted(i1))
        {
            if (world.isRemote)
            {
                return true;
            }
            else
            {
            	world.setBlockMetadataWithNotify(x, y, z, i1 + 4, 2);
                world.func_147453_f(x, y, z, Journey.basicLock);
                //world.markBlockForUpdate(x, y, z);
                --stack.stackSize;
                int j1;

                for (j1 = 0; j1 < 16; ++j1)
                {
                    double d0 = (double)((float)x + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double)((float)y + 0.8125F);
                    double d2 = (double)((float)z + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;
                    world.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
                }

                j1 = i1 & 3;
                int j2 = 0;
                int k1 = 0;
                boolean flag1 = false;
                boolean flag = true;
                int k2 = Direction.rotateRight[j1];
                int l1;
                int i2;
                int l2;

                for (l1 = -2; l1 <= 2; ++l1)
                {
                    l2 = x + Direction.offsetX[k2] * l1;
                    i2 = z + Direction.offsetZ[k2] * l1;

                    if (world.getBlock(l2, y, i2) == Journey.basicLock)
                    {
                    	if(!BlockEndPortalFrame.isEnderEyeInserted(world.getBlockMetadata(l2, y, i2)))
                        {
                            flag = false;
                            break;
                        }

                        k1 = l1;

                        if (!flag1)
                        {
                            j2 = l1;
                            flag1 = true;
                        }
                    }
                }

                if (flag && k1 == j2 + 2)
                {
                    for (l1 = j2; l1 <= k1; ++l1)
                    {
                        l2 = x + Direction.offsetX[k2] * l1;
                        i2 = z + Direction.offsetZ[k2] * l1;
                        l2 += Direction.offsetX[j1] * 4;
                        i2 += Direction.offsetZ[j1] * 4;

                        if (world.getBlock(l2, y, i2) != Journey.basicLock || !!BlockBasicLock.isEnderEyeInserted(world.getBlockMetadata(l2, y, i2)))
                        {
                            flag = false;
                            break;
                        }
                    }

                    int i3;

                    for (l1 = j2 - 1; l1 <= k1 + 1; l1 += 4)
                    {
                        for (l2 = 1; l2 <= 3; ++l2)
                        {
                            i2 = x + Direction.offsetX[k2] * l1;
                            i3 = z + Direction.offsetZ[k2] * l1;
                            i2 += Direction.offsetX[j1] * l2;
                            i3 += Direction.offsetZ[j1] * l2;

                            if (world.getBlock(i2, y, i3) != Journey.basicLock || !BlockBasicLock.isEnderEyeInserted(world.getBlockMetadata(i2, y, i3)))
                            {
                                flag = false;
                                break;
                            }
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return false;
        }
		
		/*
        if (par3World.isRemote)
            return true;
        else
        {
            Block block = par3World.getBlock(par4, par5, par6);
            
            if(block instanceof BlockBasicLock)
            {
            	BlockBasicLock foo = (BlockBasicLock)block;
            	foo.insertKey();
            	
            	if (!par2EntityPlayer.capabilities.isCreativeMode)
                    --par1ItemStack.stackSize;
            }

            return true;
        }
        */
    }
}

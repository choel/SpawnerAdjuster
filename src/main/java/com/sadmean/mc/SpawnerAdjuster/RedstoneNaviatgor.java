package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.Material;
import org.bukkit.block.Block;

/*
 * This is not currently used in SpawnerAdjuster. 
 */
public class RedstoneNaviatgor {
	Block startBlock;
	public RedstoneNaviatgor(Block sourceBlock) {
		startBlock = sourceBlock;
	}
	
	public boolean powersMaterial(Material targetMaterial) {
		if(checkRedstone(startBlock, targetMaterial)) {
			return true;
		} else {
			return true;
		}
	}
	
	private boolean checkRedstone(Block block, Material target) {
		if(block.getRelative(1, 0, 0).getType() == Material.REDSTONE || block.getRelative(1, 0, 0).getType() == Material.REDSTONE_WIRE || block.getRelative(1, 0, 0).getType() == Material.DIODE || block.getRelative(1, 0, 0).getType() == Material.REDSTONE_TORCH_OFF || block.getRelative(1, 0, 0).getType() == Material.REDSTONE_TORCH_ON) {
			if(checkRedstone(block.getRelative(1, 0, 0), target)) {
				return true;
			}
		} else {
			if(block.getRelative(1, 0, 0).getType() == target) {
				return true;
			}
		}
		//0,0,-1
		
		
		return false;
	}
}

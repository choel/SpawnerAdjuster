package com.sadmean.mc.SpawnerAdjuster.Config;

import org.bukkit.metadata.FixedMetadataValue;

import com.sadmean.mc.SpawnerAdjuster.SpawnerAdjuster;

public class MetaData {

	//stored metadatas
	public static FixedMetadataValue NONE = new FixedMetadataValue(SpawnerAdjuster.getThisPlugin(), "none");
	public static FixedMetadataValue CHARGED = new FixedMetadataValue(SpawnerAdjuster.getThisPlugin(), "charged");
	public static FixedMetadataValue ZOMBIE = new FixedMetadataValue(SpawnerAdjuster.getThisPlugin(), "zombie");
	public static FixedMetadataValue WITHER = new FixedMetadataValue(SpawnerAdjuster.getThisPlugin(), "wither");
	
	public static void load() {
		
	}
	
}

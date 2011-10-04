package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class AdjusterPlayerListener extends PlayerListener {
	public static SpawnerAdjuster plugin; public AdjusterPlayerListener(SpawnerAdjuster instance) { 
		plugin = instance;
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() == null) return;
		if(event.getClickedBlock().getType() == Material.MOB_SPAWNER && SpawnerAdjuster.usePlayerListener) {
			if(SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.ChangeSpawnType")) {
				if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
					CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
					String name = spawner.getCreatureType().getName();
					setSpawnType(spawner, event.getPlayer());
					String newName = spawner.getCreatureType().getName();
					event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Spawner was: "+ ChatColor.GREEN + name + ChatColor.GRAY + " is now: " + ChatColor.GREEN + newName);
				}
			}
		}
	}
	
	public static void setSpawnType(CreatureSpawner spawner, Player player) {
		int i = 0;
		//if (!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && !SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && !SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie"))
		if (spawner.getCreatureType() == CreatureType.CAVE_SPIDER) {
			//spawner.setCreatureType(CreatureType.ENDERMAN);
			//return;
			i = 0;
		}
		if (spawner.getCreatureType() == CreatureType.ENDERMAN) {
			//spawner.setCreatureType(CreatureType.SILVERFISH);
			//return;
			i = 1;
		}
		if (spawner.getCreatureType() == CreatureType.SILVERFISH) {
			//spawner.setCreatureType(CreatureType.GIANT);
			//return;
			i = 2;
		}
		if (spawner.getCreatureType() == CreatureType.GIANT){
			//spawner.setCreatureType(CreatureType.SKELETON);
			//return;
			i = 3;
		}
		if (spawner.getCreatureType() == CreatureType.SKELETON) {
			//spawner.setCreatureType(CreatureType.ZOMBIE);
			//return;
			i = 4;
		}
		if (spawner.getCreatureType() == CreatureType.ZOMBIE) {
			//spawner.setCreatureType(CreatureType.CREEPER);
			//return;
			i = 5;
		}
		if (spawner.getCreatureType() == CreatureType.CREEPER) {
			//spawner.setCreatureType(CreatureType.SPIDER);
			//return;
			i = 6;
		}
		if (spawner.getCreatureType() == CreatureType.SPIDER) {
			//spawner.setCreatureType(CreatureType.PIG);
			//return;
			i = 7;
		}
		if (spawner.getCreatureType() == CreatureType.PIG) {
			//spawner.setCreatureType(CreatureType.COW); //for placed spawners
			//return;
			i = 8;
		}
		if (spawner.getCreatureType() == CreatureType.COW) {
			//spawner.setCreatureType(CreatureType.SHEEP);
			//return;
			i = 9;
		}
		if (spawner.getCreatureType() == CreatureType.SHEEP) {
			//spawner.setCreatureType(CreatureType.WOLF); //:D
			//return;
			i = 10;
		}
		if (spawner.getCreatureType() == CreatureType.WOLF) {
			//spawner.setCreatureType(CreatureType.CHICKEN); //:D
			//return;
			i = 11;
		}
		if (spawner.getCreatureType() == CreatureType.CHICKEN) {
			//spawner.setCreatureType(CreatureType.SLIME); //:D
			//return;
			i = 12;
		}
		if (spawner.getCreatureType() == CreatureType.SLIME) {
			//spawner.setCreatureType(CreatureType.GHAST); //:D
			//return;
			i = 13;
		}
		if (spawner.getCreatureType() == CreatureType.GHAST) {
			//spawner.setCreatureType(CreatureType.PIG_ZOMBIE); //:D
			//return;
			i = 14;
		}
		if (spawner.getCreatureType() == CreatureType.PIG_ZOMBIE) {
			//spawner.setCreatureType(CreatureType.CAVE_SPIDER); //:D
			//return;
			i = 15;
		}
		
		int initalvalue = i;
		//i--;
		int b = 0;
		while(i != initalvalue || b == 0) {
			if(i >= 16) i = 0;
			if(i == 0 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Enderman") && SpawnerAdjuster.allowEnderman) {
				spawner.setCreatureType(CreatureType.ENDERMAN);
				return;
			}
			if(i == 1 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Silverfish") && SpawnerAdjuster.allowSilverfish) {
				spawner.setCreatureType(CreatureType.SILVERFISH);
				return;
			}
			if(i == 2 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.allowGiant) {
				spawner.setCreatureType(CreatureType.GIANT);
				return;
			}
			if(i == 3 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Skeleton") && SpawnerAdjuster.allowSkeleton) {
				spawner.setCreatureType(CreatureType.SKELETON);
				return;
			}
			if(i == 4 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Zombie") && SpawnerAdjuster.allowZombie) {
				spawner.setCreatureType(CreatureType.ZOMBIE);
				return;
			}
			if(i == 5 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Creeper") && SpawnerAdjuster.allowCreeper) {
				spawner.setCreatureType(CreatureType.CREEPER);
				return;
			}
			if(i == 6 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Spider") && SpawnerAdjuster.allowSpider) {
				spawner.setCreatureType(CreatureType.SPIDER);
				return;
			}
			if(i == 7 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.allowPig) {
				spawner.setCreatureType(CreatureType.PIG);
				return;
			}
			if(i == 8 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.allowCow) {
				spawner.setCreatureType(CreatureType.COW);
				return;
			}
			if(i == 9 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.allowSheep) {
				spawner.setCreatureType(CreatureType.SHEEP);
				return;
			}
			if(i == 10 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.allowWolf) {
				spawner.setCreatureType(CreatureType.WOLF);
				return;
			}
			if(i == 11 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.allowChicken) {
				spawner.setCreatureType(CreatureType.CHICKEN);
				return;
			}
			if(i == 12 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.allowSlime) {
				spawner.setCreatureType(CreatureType.SLIME);
				return;
			}
			if(i == 13 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.allowGhast) {
				spawner.setCreatureType(CreatureType.GHAST);
				return;
			}
			if(i == 14 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.allowPigZombie) {
				spawner.setCreatureType(CreatureType.PIG_ZOMBIE);
				return;
			}
			if(i == 15 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.CaveSpider") && SpawnerAdjuster.allowCaveSpider) {
				spawner.setCreatureType(CreatureType.CAVE_SPIDER);
				return;
			}
			b++;
			i++;
			if(b > 3) {
				//infinite loop protection.
				return;
			}
		}
	}
}

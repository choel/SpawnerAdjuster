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
				if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
					CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
					String name = spawner.getCreatureType().getName();
					if(SpawnerAdjuster.opsChangeSpawnTypeOnly) {
						if(event.getPlayer().isOp()) {
							setSpawnType(spawner, event.getPlayer());
						}
					} else {
						setSpawnType(spawner, event.getPlayer());
					}
					String newName = spawner.getCreatureType().getName();
					event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Spawner was: "+ ChatColor.GREEN + name + ChatColor.GRAY + " is now: " + ChatColor.GREEN + newName);
					event.setCancelled(true); //maybe prevent block placement?
					spawner.setDelay(200);
				}
			}
		}
	}
	
	public static void setSpawnType(CreatureSpawner spawner, Player player) {
		int i = -1;
		
		if (spawner.getCreatureType() == CreatureType.CAVE_SPIDER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.CaveSpider") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 0;
		}
		if (spawner.getCreatureType() == CreatureType.SPIDER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Spider") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 1;
		}
		if (spawner.getCreatureType() == CreatureType.CREEPER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Creeper") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;			i = 1;
			i = 2;
		}
		if (spawner.getCreatureType() == CreatureType.SILVERFISH) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Silverfish") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 3;
		}
		if (spawner.getCreatureType() == CreatureType.SKELETON) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Skeleton") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 4;
		}
		if (spawner.getCreatureType() == CreatureType.ZOMBIE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Zombie") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 5;
		}
		if (spawner.getCreatureType() == CreatureType.SLIME) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 6;
		}
		if (spawner.getCreatureType() == CreatureType.ENDERMAN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostle.Enderman") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 7;
		}
		if (spawner.getCreatureType() == CreatureType.ENDER_DRAGON) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.EnderDragon") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 8;
		}
		if (spawner.getCreatureType() == CreatureType.MAGMA_CUBE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.MagmaCube") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 9;
		}
		if (spawner.getCreatureType() == CreatureType.BLAZE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Blaze") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 10;
		}
		if (spawner.getCreatureType() == CreatureType.GHAST) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 11;
		}
		if (spawner.getCreatureType() == CreatureType.PIG_ZOMBIE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 12;
		}
		if (spawner.getCreatureType() == CreatureType.PIG) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 13;
		}
		if (spawner.getCreatureType() == CreatureType.COW) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 14;
		}
		if (spawner.getCreatureType() == CreatureType.SHEEP) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 15;
		}
		if (spawner.getCreatureType() == CreatureType.WOLF) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 16;
		}
		if (spawner.getCreatureType() == CreatureType.CHICKEN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 17;
		}
		if (spawner.getCreatureType() == CreatureType.MUSHROOM_COW) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Mooshroom") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 18;
		}
		if (spawner.getCreatureType() == CreatureType.SNOWMAN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.SnowGolem") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 19;
		}
		if (spawner.getCreatureType() == CreatureType.VILLAGER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Villager") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 20;
		}
		if (spawner.getCreatureType() == CreatureType.GIANT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 21;
		}
		//if i is still -1, then we have an unknown mob type. We should not play with that spawner
		if(i == -1) {
			SpawnerAdjuster.log_It("warning", "unkown mob type error");
			return;
		}
		
		int initalvalue = i;
		//i--;
		int b = 0;
		while(i != initalvalue || b == 0) {
			if(i >= 22) i = 0;

			if(i == 0 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Spider") && SpawnerAdjuster.allowSpider) {
				spawner.setCreatureType(CreatureType.SPIDER);
				return;
			}
			if(i == 1 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Creeper") && SpawnerAdjuster.allowCreeper) {
				spawner.setCreatureType(CreatureType.CREEPER);
				return;
			}
			if(i == 2 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Silverfish") && SpawnerAdjuster.allowSilverfish) {
				spawner.setCreatureType(CreatureType.SILVERFISH);
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
			if(i == 5 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.allowSlime) {
				spawner.setCreatureType(CreatureType.SLIME);
				return;
			}
			if(i == 6 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Enderman") && SpawnerAdjuster.allowEnderman) {
				spawner.setCreatureType(CreatureType.ENDERMAN);
				return;
			}
			if(i == 7 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.EnderDragon") && SpawnerAdjuster.allowEnderDragon) {
				spawner.setCreatureType(CreatureType.ENDER_DRAGON);
				return;
			}
			if(i == 8 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.MagmaCube") && SpawnerAdjuster.allowMagmaCube) {
				spawner.setCreatureType(CreatureType.MAGMA_CUBE);
				return;
			}
			if(i == 9 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Blaze") && SpawnerAdjuster.allowBlaze) {
				spawner.setCreatureType(CreatureType.BLAZE);
				return;
			}
			if(i == 10 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.allowGhast) {
				spawner.setCreatureType(CreatureType.GHAST);
				return;
			}
			if(i == 11 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.allowPigZombie) {
				spawner.setCreatureType(CreatureType.PIG_ZOMBIE);
				return;
			}
			if(i == 12 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.allowPig) {
				spawner.setCreatureType(CreatureType.PIG);
				return;
			}
			if(i == 13 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.allowCow) {
				spawner.setCreatureType(CreatureType.COW);
				return;
			}
			if(i == 14 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.allowSheep) {
				spawner.setCreatureType(CreatureType.SHEEP);
				return;
			}
			if(i == 15 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.allowWolf) {
				spawner.setCreatureType(CreatureType.WOLF);
				return;
			}
			if(i == 16 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.allowChicken) {
				spawner.setCreatureType(CreatureType.CHICKEN);
				return;
			}
			if(i == 17 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Mooshroom") && SpawnerAdjuster.allowMooshroom) {
				spawner.setCreatureType(CreatureType.MUSHROOM_COW);
				return;
			}
			if(i == 18 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.SnowGolem") && SpawnerAdjuster.allowSnowGolem) {
				spawner.setCreatureType(CreatureType.SNOWMAN);
				return;
			}
			if(i == 19 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Villager") && SpawnerAdjuster.allowVillager) {
				spawner.setCreatureType(CreatureType.VILLAGER);
				return;
			}
			if(i == 20 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.allowGiant) {
				spawner.setCreatureType(CreatureType.GIANT);
				return;
			}
			if(i == 21 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.CaveSpider") && SpawnerAdjuster.allowCaveSpider) {
				spawner.setCreatureType(CreatureType.CAVE_SPIDER);
				return;
			}
			b++;
			i++;
			if(b > 30) {
				//infinite loop protection.
				return;
			}
		}
	}
}

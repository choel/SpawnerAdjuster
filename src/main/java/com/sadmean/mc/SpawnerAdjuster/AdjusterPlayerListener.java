package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.sadmean.mc.SpawnerAdjuster.Config.MetaData;

public class AdjusterPlayerListener implements Listener {
	public static SpawnerAdjuster plugin; public AdjusterPlayerListener(SpawnerAdjuster instance) { 
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() == null || !SpawnerAdjuster.usePlayerListener) return;
		if(event.getClickedBlock().getType() == Material.MOB_SPAWNER) {
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
				CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
				String name = spawner.getSpawnedType().getName();
				if(SpawnerAdjuster.opsChangeSpawnTypeOnly) {
					if(event.getPlayer().isOp()) {
						setSpawnType(spawner, event.getPlayer());
					}
				} else {
					if(SpawnerAdjuster.advanced_requireExtraPermission) {
						if(SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.ChangeSpawnType")) {
							setSpawnType(spawner, event.getPlayer());
						} else {
							//get fat, cat!
						}
					} else {
						setSpawnType(spawner, event.getPlayer());
					}
				}
				String newName = spawner.getSpawnedType().getName();
				if(SpawnerAdjuster.advanced_hideMessageIfSpawnerUnchanged && name.equalsIgnoreCase(newName)) {
					//messgae is hidden
				} else {
					//message is not hidden
					event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Spawner was: "+ ChatColor.GREEN + name + ChatColor.GRAY + ". Now set to: " + ChatColor.GREEN + newName);
					spawner.setDelay(200); //reset delay
					if(spawner.hasMetadata("sub_type")) spawner.removeMetadata("sub_type", SpawnerAdjuster.getThisPlugin()); //remove meta data
				}

			} else {
				if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
					//modify spawner settings, for MC1.3
					CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
					
					//if creeper, charge and uncharge
					if(spawner.getSpawnedType() == EntityType.CREEPER && SpawnerAdjuster.advanced_debugMode) {
						if(spawner.hasMetadata("sub_type")) {
							event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Charged Creeper spawning disabled");
							spawner.removeMetadata("sub_type", SpawnerAdjuster.getThisPlugin());
						} else {
							spawner.setMetadata("sub_type", MetaData.CHARGED);
							event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Charged Creeper spawning enabled");
						}
					}
					
					//if villager, change robe color
					if(spawner.getSpawnedType() == EntityType.VILLAGER) {
						//nothing yet
					}
					
					//if enderman, change holding block
					if(spawner.getSpawnedType() == EntityType.ENDERMAN) {
						//nothing yet
					}
					
					if(spawner.getSpawnedType() == EntityType.SKELETON && SpawnerAdjuster.advanced_debugMode) {
						if(spawner.hasMetadata("sub_type")) {
							if(spawner.getMetadata("sub_type").get(0).asString().equalsIgnoreCase("none")) {
								spawner.setMetadata("sub_type", MetaData.WITHER);
							} else {
								spawner.setMetadata("sub_type", MetaData.NONE);
							}
						} else {
							spawner.setMetadata("sub_type", MetaData.NONE);
						}
						event.getPlayer().sendMessage(SpawnerAdjuster.chatPrefix + "Skeleton Subtype set to" + ChatColor.RED + " " + spawner.getMetadata("sub_type").get(0).asString());
					}
				}
			}
		} 
	}
	
	public static void setSpawnType(CreatureSpawner spawner, Player player) {
		int i = -1;;
		
		if (spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.CaveSpider") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 0;
		}
		if (spawner.getSpawnedType() == EntityType.SPIDER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Spider") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 1;
		}
		if (spawner.getSpawnedType() == EntityType.CREEPER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Creeper") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;			i = 1;
			i = 2;
		}
		if (spawner.getSpawnedType() == EntityType.SILVERFISH) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Silverfish") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 3;
		}
		if (spawner.getSpawnedType() == EntityType.SKELETON) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Skeleton") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 4;
		}
		if (spawner.getSpawnedType() == EntityType.ZOMBIE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Zombie") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 5;
		}
		if (spawner.getSpawnedType() == EntityType.SLIME) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 6;
		}
		if (spawner.getSpawnedType() == EntityType.ENDERMAN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Enderman") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 7;
		}
		if (spawner.getSpawnedType() == EntityType.ENDER_DRAGON) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.EnderDragon") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 8;
		}
		if (spawner.getSpawnedType() == EntityType.MAGMA_CUBE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.MagmaCube") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 9;
		}
		if (spawner.getSpawnedType() == EntityType.BLAZE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Blaze") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 10;
		}
		if (spawner.getSpawnedType() == EntityType.GHAST) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 11;
		}
		if (spawner.getSpawnedType() == EntityType.PIG_ZOMBIE) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 12;
		}
		if (spawner.getSpawnedType() == EntityType.PIG) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 13;
		}
		if (spawner.getSpawnedType() == EntityType.COW) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 14;
		}
		if (spawner.getSpawnedType() == EntityType.SHEEP) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 15;
		}
		if (spawner.getSpawnedType() == EntityType.WOLF) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 16;
		}
		if (spawner.getSpawnedType() == EntityType.CHICKEN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 17;
		}
		if (spawner.getSpawnedType() == EntityType.MUSHROOM_COW) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Mooshroom") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 18;
		}
		if (spawner.getSpawnedType() == EntityType.SNOWMAN) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.SnowGolem") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 19;
		}
		if (spawner.getSpawnedType() == EntityType.VILLAGER) {
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Villager") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 20;
		}
		if (spawner.getSpawnedType() == EntityType.GIANT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 21;
		}
		if (spawner.getSpawnedType() == EntityType.OCELOT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Ocelot") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 22;
		}
		if (spawner.getSpawnedType() == EntityType.IRON_GOLEM){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.IronGolem") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 23;
		}
		if (spawner.getSpawnedType() == EntityType.SQUID){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Squid") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 24;
		}
		if (spawner.getSpawnedType() == EntityType.ARROW){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Arrow") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 25;
		}
		if (spawner.getSpawnedType() == EntityType.BOAT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Boat") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 26;
		}
		if (spawner.getSpawnedType() == EntityType.EGG){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Egg") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 27;
		}
		if (spawner.getSpawnedType() == EntityType.MINECART){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Minecart") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 28;
		}
		if (spawner.getSpawnedType() == EntityType.FIREBALL){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Fireball") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 29;
		}
		//1.4
		if (spawner.getSpawnedType() == EntityType.WITHER){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.Wither") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 30;
		}
		/* Wither Skeleton doesn't exist
		if (spawner.getSpawnedType() == EntityType.WITHERSKELETON){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.WitherSkeleton") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 31;
		}
		*/
		if (spawner.getSpawnedType() == EntityType.WITCH){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Witch") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 32;
		}
		/* Zombie Villager doesn't exist
		if (spawner.getSpawnedType() == EntityType.ZOMBIEVILLAGER){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.ZombieVillager") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 33;
		}
		*/
		if (spawner.getSpawnedType() == EntityType.BAT){
			if(!SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Bat") && SpawnerAdjuster.mustHaveValidPermissionsToAlterSpawner) return;
			i = 34;
		}
		
		//if i is still -1, then we have an unknown mob type. We should not play with that spawner
		if(i == -1) {
			SpawnerAdjuster.log_It("warning", "Unkown mob type error from spawner at " + spawner.getLocation());
			return;
		}
		
		int initalvalue = i;
		//i--;
		int b = 0;
		while(i != initalvalue || b == 0) {
			if(i >= 35) i = 0;

			if(i == 0 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Spider") && SpawnerAdjuster.allowSpider) {
				spawner.setSpawnedType(EntityType.SPIDER);
				return;
			}
			if(i == 1 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Creeper") && SpawnerAdjuster.allowCreeper) {
				spawner.setSpawnedType(EntityType.CREEPER);
				return;
			}
			if(i == 2 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Silverfish") && SpawnerAdjuster.allowSilverfish) {
				spawner.setSpawnedType(EntityType.SILVERFISH);
				return;
			}
			if(i == 3 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Skeleton") && SpawnerAdjuster.allowSkeleton) {
				spawner.setSpawnedType(EntityType.SKELETON);
				return;
			}
			if(i == 4 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Zombie") && SpawnerAdjuster.allowZombie) {
				spawner.setSpawnedType(EntityType.ZOMBIE);
				return;
			}
			if(i == 5 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Slime") && SpawnerAdjuster.allowSlime) {
				spawner.setSpawnedType(EntityType.SLIME);
				return;
			}
			if(i == 6 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Enderman") && SpawnerAdjuster.allowEnderman) {
				spawner.setSpawnedType(EntityType.ENDERMAN);
				return;
			}
			if(i == 7 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.EnderDragon") && SpawnerAdjuster.allowEnderDragon) {
				spawner.setSpawnedType(EntityType.ENDER_DRAGON);
				return;
			}
			if(i == 8 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.MagmaCube") && SpawnerAdjuster.allowMagmaCube) {
				spawner.setSpawnedType(EntityType.MAGMA_CUBE);
				return;
			}
			if(i == 9 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Blaze") && SpawnerAdjuster.allowBlaze) {
				spawner.setSpawnedType(EntityType.BLAZE);
				return;
			}
			if(i == 10 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.Ghast") && SpawnerAdjuster.allowGhast) {
				spawner.setSpawnedType(EntityType.GHAST);
				return;
			}
			if(i == 11 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.PigZombie") && SpawnerAdjuster.allowPigZombie) {
				spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
				return;
			}
			if(i == 12 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Pig") && SpawnerAdjuster.allowPig) {
				spawner.setSpawnedType(EntityType.PIG);
				return;
			}
			if(i == 13 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Cow") && SpawnerAdjuster.allowCow) {
				spawner.setSpawnedType(EntityType.COW);
				return;
			}
			if(i == 14 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Sheep") && SpawnerAdjuster.allowSheep) {
				spawner.setSpawnedType(EntityType.SHEEP);
				return;
			}
			if(i == 15 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Wolf") && SpawnerAdjuster.allowWolf) {
				spawner.setSpawnedType(EntityType.WOLF);
				return;
			}
			if(i == 16 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Chicken") && SpawnerAdjuster.allowChicken) {
				spawner.setSpawnedType(EntityType.CHICKEN);
				return;
			}
			if(i == 17 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Mooshroom") && SpawnerAdjuster.allowMooshroom) {
				spawner.setSpawnedType(EntityType.MUSHROOM_COW);
				return;
			}
			if(i == 18 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.SnowGolem") && SpawnerAdjuster.allowSnowGolem) {
				spawner.setSpawnedType(EntityType.SNOWMAN);
				return;
			}
			if(i == 19 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Villager") && SpawnerAdjuster.allowVillager) {
				spawner.setSpawnedType(EntityType.VILLAGER);
				return;
			}
			if(i == 20 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Unused.Giant") && SpawnerAdjuster.allowGiant) {
				spawner.setSpawnedType(EntityType.GIANT);
				return;
			}
			if(i == 21 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Ocelot") && SpawnerAdjuster.allowOcelot) {
				spawner.setSpawnedType(EntityType.OCELOT);
				return;
			}
			if(i == 22 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Utility.IronGolem") && SpawnerAdjuster.allowIronGolem) {
				spawner.setSpawnedType(EntityType.IRON_GOLEM);
				return;
			}
			if(i == 23 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Squid") && SpawnerAdjuster.allowSquid) {
				spawner.setSpawnedType(EntityType.SQUID);
				return;
			}
			if(i == 24 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Arrow") && SpawnerAdjuster.allowArrow) {
				spawner.setSpawnedType(EntityType.ARROW);
				return;
			}
			if(i == 25 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Boat") && SpawnerAdjuster.allowBoat) {
				spawner.setSpawnedType(EntityType.BOAT);
				return;
			}
			/* Eggs disabed due to console errors. Check back later
			if(i == 26 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Egg") && SpawnerAdjuster.allowEgg) {
				spawner.setSpawnedType(EntityType.EGG);
				return;
			}
			*/
			if(i == 27 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Minecart") && SpawnerAdjuster.allowMinecart) {
				spawner.setSpawnedType(EntityType.MINECART);
				return;
			}
			if(i == 28 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Entity.Fireball") && SpawnerAdjuster.allowFireball) {
				spawner.setSpawnedType(EntityType.FIREBALL);
				return;
			}
			if(i == 29 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Boss.Wither") && SpawnerAdjuster.allowWither) {
				spawner.setSpawnedType(EntityType.WITHER);
				return;
			}
			/* Wither Skeleton doesn't exist
			if(i == 30 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Nether.WitherSkeleton") && SpawnerAdjuster.allowWitherSkeleton) {
				spawner.setSpawnedType(EntityType.WITHERSKELETON);
				return;
			}
			*/
			if(i == 31 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.Witch") && SpawnerAdjuster.allowWitch) {
				spawner.setSpawnedType(EntityType.WITCH);
				return;
			}
			/* Zombie Villager Doesn't Exist
			if(i == 32 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.ZombieVillager") && SpawnerAdjuster.allowZombieVillager) {
				spawner.setSpawnedType(EntityType.ZOMBIEVILLAGER);
				return;
			}
			*/
			if(i == 33 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Neutral.Bat") && SpawnerAdjuster.allowBat) {
				spawner.setSpawnedType(EntityType.BAT);
				return;
			}
			if(i == 34 && SpawnerAdjuster.permCheck(player, "SpawnerAdjuster.SetMobs.Hostile.CaveSpider") && SpawnerAdjuster.allowCaveSpider) {
				spawner.setSpawnedType(EntityType.CAVE_SPIDER);
				return;
			}
			b++;
			i++;
			if(b > 40) { //This will probably need to go up in minecraft 1.5
				//infinite loop protection.
				return;
			}
		}
	}
}

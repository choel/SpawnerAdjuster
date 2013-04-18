package com.sadmean.mc.SpawnerAdjuster;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class AdjusterBlockListener implements Listener {
	public static SpawnerAdjuster plugin; public AdjusterBlockListener(SpawnerAdjuster instance) { 
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		if(!(SpawnerAdjuster.respondToRedstone && SpawnerAdjuster.useRedstoneListener)) return;
		if(event.getBlock().getRelative(1, 0, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(1, 0, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(-1, 0, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(-1, 0, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 0, 1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 0, 1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 0, -1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 0, -1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, -2, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, -2, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 2, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 2, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, 1, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, 1, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(1, -1, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(1, -1, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(-1, -1, 0).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(-1, -1, 0).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, -1, 1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, -1, 1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		if(event.getBlock().getRelative(0, -1, -1).getType() == Material.MOB_SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getRelative(0, -1, -1).getState();
			if(SpawnerAdjuster.redstoneForcesSpawn) {
				forceSpawn(spawner);
			} else {
				spawner.setDelay(1);
			}
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getType() == Material.MOB_SPAWNER && SpawnerAdjuster.useBlockListener) {
			if(event.getPlayer() != null) {
				if(!SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.BreakSpawners")) {
					event.setCancelled(true);
					return;
				}
			}
			if(SpawnerAdjuster.permCheck(event.getPlayer(), "SpawnerAdjuster.DropSpawners") && SpawnerAdjuster.allowDroppedSpawners) {
				if(!event.isCancelled()) {
					
					ItemStack spawnerstack = new ItemStack(event.getBlock().getType(), 1);
					ItemMeta im = spawnerstack.getItemMeta();
					CreatureSpawner Spawner = (CreatureSpawner) event.getBlock().getState();
					
					//get spawner type and set meta accordingly.
					if(Spawner.getSpawnedType() == EntityType.ARROW) {
						im.setDisplayName("Arrow Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.BAT) {
						im.setDisplayName("Bat Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.BLAZE) {
						im.setDisplayName("Blaze Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.BOAT) {
						im.setDisplayName("Boat Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.CAVE_SPIDER) {
						im.setDisplayName("Cave Spider Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.CHICKEN) {
						im.setDisplayName("Chicken Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.COMPLEX_PART) {
						im.setDisplayName("Complex Part Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.COW) {
						im.setDisplayName("Cow Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.CREEPER) {
						im.setDisplayName("Creeper Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.DROPPED_ITEM) {
						im.setDisplayName("Dropped Item Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.EGG) {
						im.setDisplayName("Egg Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ENDER_CRYSTAL) {
						im.setDisplayName("Ender Crystal Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ENDER_DRAGON) {
						im.setDisplayName("Ender Dragon Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ENDER_PEARL) {
						im.setDisplayName("Ender Pearl Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ENDER_SIGNAL) {
						im.setDisplayName("Ender Signal Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ENDERMAN) {
						im.setDisplayName("Enderman Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.EXPERIENCE_ORB) {
						im.setDisplayName("Experience Orb Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.FALLING_BLOCK) {
						im.setDisplayName("Falling Block Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.FIREBALL) {
						im.setDisplayName("Fireball Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.FIREWORK) {
						im.setDisplayName("Firework Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.FISHING_HOOK) {
						im.setDisplayName("Fishing Hook Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.GHAST) {
						im.setDisplayName("Ghast Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.GIANT) {
						im.setDisplayName("Giant Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.IRON_GOLEM) {
						im.setDisplayName("Iron Golem Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ITEM_FRAME) {
						im.setDisplayName("Item Frame Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.LIGHTNING) {
						im.setDisplayName("Lightning Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MAGMA_CUBE) {
						im.setDisplayName("Magma Cube Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MINECART) {
						im.setDisplayName("Minecart Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MINECART_CHEST) {
						im.setDisplayName("Minecart Chest Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MINECART_FURNACE) {
						im.setDisplayName("Minecart Furnace Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MINECART_HOPPER) {
						im.setDisplayName("Minecart Hopper Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MINECART_MOB_SPAWNER) {
						im.setDisplayName("Minecart Mob Spawner Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MINECART_TNT) {
						im.setDisplayName("Minecart TNT Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.MUSHROOM_COW) {
						im.setDisplayName("Mushroom Cow Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.OCELOT) {
						im.setDisplayName("Ocelot Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.PAINTING) {
						im.setDisplayName("Painting Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.PIG) {
						im.setDisplayName("Pig Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.PLAYER) {
						im.setDisplayName("Player Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.PRIMED_TNT) {
						im.setDisplayName("Primed TNT Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SHEEP) {
						im.setDisplayName("Sheep Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SILVERFISH) {
						im.setDisplayName("Silverfish Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SKELETON) {
						im.setDisplayName("Skeleton Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SLIME) {
						im.setDisplayName("Slime Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SMALL_FIREBALL) {
						im.setDisplayName("Small Fireball Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SNOWBALL) {
						im.setDisplayName("Snowball Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SNOWMAN) {
						im.setDisplayName("Snowman Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SPIDER) {
						im.setDisplayName("Spider Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SPLASH_POTION) {
						im.setDisplayName("Splash Potion Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.SQUID) {
						im.setDisplayName("Squid Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.THROWN_EXP_BOTTLE) {
						im.setDisplayName("Thrown EXP Bottle Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.UNKNOWN) {
						im.setDisplayName("Unknown Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.VILLAGER) {
						im.setDisplayName("Villager Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.WEATHER) {
						im.setDisplayName("Weather Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.WITCH) {
						im.setDisplayName("Witch Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.WITHER) {
						im.setDisplayName("Wither Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.WITHER_SKULL) {
						im.setDisplayName("Wither Skull Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.WOLF) {
						im.setDisplayName("Wolf Spawner");
					}
					if(Spawner.getSpawnedType() == EntityType.ZOMBIE) {
						im.setDisplayName("Zombie Spawner");
					}
					spawnerstack.setItemMeta(im);
					if(SpawnerAdjuster.advanced_needSilkTouchForSpawnerDrops) {
						Map<Enchantment, Integer> map = event.getPlayer().getItemInHand().getEnchantments();
						if(map.containsKey(Enchantment.SILK_TOUCH)) {
							if(map.get(Enchantment.SILK_TOUCH) > 0) {
								event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), spawnerstack);
							}
						}
					} else {
						event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), spawnerstack);
					}
					
				}	
			}
			if(!SpawnerAdjuster.spawnersCanDropExp) {
				event.setExpToDrop(0);
			}
		}
	}
	
	/*
	 * this wil not be used until 1.5 or 2.0 // Or never I guess wtf am I doing? //look I added more to this line
	 * Catches CreatureSpawnEvents and checks to see if redstone is nearby. 
	 * If redstone is near and it is unpowered, prevent the spawn.
	 * 
	 */
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getSpawnReason() == SpawnReason.SPAWNER) {
			if(SpawnerAdjuster.deactivedByRedstoneStatus) {
				//asdf
			}
		}
	}
	
	/*commenting these out so we don't waste cpu time
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		//admin redstone thing here (someday).
	}
	*/
	@EventHandler
	public void stopPigSpawn(CreatureSpawnEvent event) {
		if(event.getEntityType() == EntityType.PIG && event.getSpawnReason() == SpawnReason.SPAWNER && SpawnerAdjuster.advanced_stopPigSpawns) event.setCancelled(true);
	}
	
	private void forceSpawn(CreatureSpawner spawner) {

		int range = 1;
		boolean reset = false;
		for(int dx = -(range); dx <= range && !reset; dx++) {
			for(int dy = -(range); dy <= range && !reset; dy++) {
				for(int dz = -(range); dz <= range && !reset; dz++) {
					if(spawner.getBlock().getRelative(dx, dy, dz).getType() == Material.AIR) {
						try {
							Entity spawnedEnt = spawner.getWorld().spawnEntity(spawner.getBlock().getRelative(dx, dy, dz).getLocation(), spawner.getSpawnedType());
							List<Entity> entList = spawnedEnt.getNearbyEntities(SpawnerAdjuster.spawnerEntCheckRadius - 1, SpawnerAdjuster.spawnerEntCheckRadius - 1, SpawnerAdjuster.spawnerEntCheckRadius - 1);
							
							int numEntsofType = 0; //number of matching ents
						
							numEntsofType = entList.size();
							if(numEntsofType >= SpawnerAdjuster.maxNumberOfEntsNearSpawner && SpawnerAdjuster.useRadiusCheck) {
								spawnedEnt.remove();
							}
							reset = true;
							/* anti-munson implement here */
							if(!SpawnerAdjuster.canSpawn(spawner, spawnedEnt)) {
								spawnedEnt.remove();
							}
							/* end anti munson implement */	
							/* prevent pig spawns if defined as such */
							if(spawnedEnt.getType() == EntityType.PIG && SpawnerAdjuster.advanced_stopPigSpawns) {
								spawnedEnt.remove();
							}
						} catch(IllegalArgumentException e) {
							SpawnerAdjuster.log_It("severe", "IllegalArgumentException: Spawner at (" + spawner.getLocation().getX() + "," + spawner.getLocation().getY() + "," + spawner.getLocation().getZ() + ") set to " + spawner.getCreatureTypeName());
							SpawnerAdjuster.log_It("severe", "data: " + spawner.getRawData());
							SpawnerAdjuster.log_It("severe", "Please post this error to the SpawnerAdjuster page at BukkitDev");
							SpawnerAdjuster.log_It("severe", "The Spawner will now be deleted");
							spawner.setType(Material.AIR);
						}
					}
				}
			}
		}
		try{
			spawner.setDelay(600);
		} catch(Exception e) {
			SpawnerAdjuster.log_It("warning", "tried to reset spawner delay, but failed, probably because of an UNRELATED IllegalArgumentException");
		}
	}
	
	@EventHandler
	public void setSpawnTypeOnPlace(BlockPlaceEvent event) {
		if(!(event.getBlockPlaced().getType() == Material.MOB_SPAWNER)) return;
		if(event.getItemInHand().getItemMeta().getDisplayName() == null) return;
		if(!(event.getItemInHand().getType() == Material.MOB_SPAWNER)) return;
		
		String DisplayName = event.getItemInHand().getItemMeta().getDisplayName();
		CreatureSpawner Spawner = (CreatureSpawner) event.getBlockPlaced().getState();
		if(DisplayName.equalsIgnoreCase("Arrow Spawner")) {
			Spawner.setSpawnedType(EntityType.ARROW);
		}
		if(DisplayName.equalsIgnoreCase("Bat Spawner")) {
			Spawner.setSpawnedType(EntityType.BAT);
		}
		if(DisplayName.equalsIgnoreCase("Blaze Spawner")) {
			Spawner.setSpawnedType(EntityType.BLAZE);
		}
		if(DisplayName.equalsIgnoreCase("Cave Spider Spawner")) {
			Spawner.setSpawnedType(EntityType.CAVE_SPIDER);
		}
		if(DisplayName.equalsIgnoreCase("Chicken Spawner")) {
			Spawner.setSpawnedType(EntityType.CHICKEN);
		}
		if(DisplayName.equalsIgnoreCase("Cow Spawner")) {
			Spawner.setSpawnedType(EntityType.COW);
		}
		if(DisplayName.equalsIgnoreCase("Creeper Spawner")) {
			Spawner.setSpawnedType(EntityType.CREEPER);
		}
		if(DisplayName.equalsIgnoreCase("Ender Dragon Spawner")) {
			Spawner.setSpawnedType(EntityType.ENDER_DRAGON);
		}
		if(DisplayName.equalsIgnoreCase("Enderman Spawner")) {
			Spawner.setSpawnedType(EntityType.ENDERMAN);
		}
		if(DisplayName.equalsIgnoreCase("Ghast Spawner")) {
			Spawner.setSpawnedType(EntityType.GHAST);
		}
		if(DisplayName.equalsIgnoreCase("Giant Spawner")) {
			Spawner.setSpawnedType(EntityType.GIANT);
		}
		if(DisplayName.equalsIgnoreCase("Iron Golem Spawner")) {
			Spawner.setSpawnedType(EntityType.IRON_GOLEM);
		}
		if(DisplayName.equalsIgnoreCase("Magma Cube Spawner")) {
			Spawner.setSpawnedType(EntityType.MAGMA_CUBE);
		}
		if(DisplayName.equalsIgnoreCase("Mushroom Cow Spawner")) {
			Spawner.setSpawnedType(EntityType.MUSHROOM_COW);
		}
		if(DisplayName.equalsIgnoreCase("Ocelot Spawner")) {
			Spawner.setSpawnedType(EntityType.OCELOT);
		}
		if(DisplayName.equalsIgnoreCase("Pig Spawner")) {
			Spawner.setSpawnedType(EntityType.PIG);
		}
		if(DisplayName.equalsIgnoreCase("Pig Zombie Spawner")) {
			Spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
		}
		if(DisplayName.equalsIgnoreCase("Sheep Spawner")) {
			Spawner.setSpawnedType(EntityType.SHEEP);
		}
		if(DisplayName.equalsIgnoreCase("Silverfish Spawner")) {
			Spawner.setSpawnedType(EntityType.SILVERFISH);
		}
		if(DisplayName.equalsIgnoreCase("Skeleton Spawner")) {
			Spawner.setSpawnedType(EntityType.SKELETON);
		}
		if(DisplayName.equalsIgnoreCase("Slime Spawner")) {
			Spawner.setSpawnedType(EntityType.SLIME);
		}
		if(DisplayName.equalsIgnoreCase("Snowman Spawner")) {
			Spawner.setSpawnedType(EntityType.SNOWMAN);
		}
		if(DisplayName.equalsIgnoreCase("Spider Spawner")) {
			Spawner.setSpawnedType(EntityType.SPIDER);
		}
		if(DisplayName.equalsIgnoreCase("Squid Spawner")) {
			Spawner.setSpawnedType(EntityType.SQUID);
		}
		if(DisplayName.equalsIgnoreCase("Villager Spawner")) {
			Spawner.setSpawnedType(EntityType.VILLAGER);
		}
		if(DisplayName.equalsIgnoreCase("Witch Spawner")) {
			Spawner.setSpawnedType(EntityType.WITCH);
		}
		if(DisplayName.equalsIgnoreCase("Wither Spawner")) {
			Spawner.setSpawnedType(EntityType.WITHER);
		}
		if(DisplayName.equalsIgnoreCase("Wolf Spawner")) {
			Spawner.setSpawnedType(EntityType.WOLF);
		}
		if(DisplayName.equalsIgnoreCase("Zombie Spawner")) {
			Spawner.setSpawnedType(EntityType.ZOMBIE);
		}
	}
}

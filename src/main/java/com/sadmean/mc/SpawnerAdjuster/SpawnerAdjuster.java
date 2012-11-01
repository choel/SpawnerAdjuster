package com.sadmean.mc.SpawnerAdjuster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
//import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
//import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import com.sadmean.mc.SpawnerAdjuster.Config.Config;
import com.sadmean.mc.SpawnerAdjuster.Config.MetaData;
import com.sadmean.mc.SpawnerAdjuster.command.spawneradjusterreload;
import com.sadmean.mc.SpawnerAdjuster.command.spawneradjusterdebug;
import com.sadmean.mc.SpawnerAdjuster.command.spawneradjusterspawndata;
import com.sadmean.mc.SpawnerAdjuster.spawningEvents.CreeperListener;

public class SpawnerAdjuster extends JavaPlugin {

	//internal stuff
	static String mainDirectory = "plugins/SpawnerAdjuster"; //plugin directory
	private final AdjusterPlayerListener playerListener = new AdjusterPlayerListener(this); //the player listener.	
	private final AdjusterBlockListener BlockListener = new AdjusterBlockListener(this); //the block listener.	
	static public File configFile = new File(mainDirectory + File.separator + "config.yml"); //location of configfile.
	static public File metadataFile = new File(mainDirectory + File.separator + "metadata.yml");
	private static SpawnerAdjuster thisPlugin = null; //I don't know what this does. Necessary for fancy log
	public static Logger log = Logger.getLogger("Minecraft"); //logger object. can be written to directly with "log.info("herp derp")
	public static String chatPrefix = ChatColor.DARK_AQUA + "[SA] " + ChatColor.GRAY;
	private static PluginDescriptionFile thisYAML;
	private static String pluginName, pluginVersion, fullName;
	private final CreeperListener creeperListener = new CreeperListener();
	//SETTINGS -to be loaded from config later
	public static boolean ignorePermissions = true;
	public static boolean debugLogs = false;
	public static boolean SuperPerms = true; //no longer changeable in config
	public static boolean usePlayerListener = true; //no longer changeable in config
	public static boolean useRedstoneListener = true; //no longer changeable in config
	public static boolean useBlockListener = true; //no longer changeable in config
	//1.1
	public static boolean allowDroppedSpawners = true;
	public static boolean ignore_opsOnly = false;
	public static boolean ignore_OpsOnlyRedstone = false; //not yet used
	public static boolean ignore_OpsOnlyChangeSpawner = false; //not yet used
	public static boolean ignore_OpsOnlyDropSpanwers = false; //not yet used
	public static boolean opsChangeSpawnTypeOnly = false;
	public static boolean respondToRedstone = true;
	public static boolean redstoneForcesSpawn = true;
	//1.2
	public static boolean deactivedByRedstoneStatus = false; //not currently used actually
	public static boolean mustHaveValidPermissionsToAlterSpawner = false;
	//mob settings
	public static boolean allowChicken = true;
	public static boolean allowWolf = true;
	public static boolean allowSheep = true;
	public static boolean allowCow = true;
	public static boolean allowSquid = true;
	public static boolean allowEnderman = true;
	public static boolean allowCaveSpider = true;
	public static boolean allowSpider = true;
	public static boolean allowCreeper = true;
	public static boolean allowSkeleton = true;
	public static boolean allowZombie = true;
	public static boolean allowSilverfish = true;
	public static boolean allowSlime = true;
	public static boolean allowGhast = true;
	public static boolean allowPigZombie = true;
	public static boolean allowGiant = true;
	public static boolean allowPig = true;
	//Minecraft 1.0.0
	public static boolean allowBlaze = true;
	public static boolean allowEnderDragon = false;
	public static boolean allowMagmaCube = true;
	public static boolean allowMooshroom = true;
	public static boolean allowVillager = true;
	public static boolean allowSnowGolem = true;
	public static boolean allowOcelot = true;
	public static boolean allowIronGolem = true;
	//1.3
	public static ArrayList<Entity> creature_Store;
	public static ArrayList<CreatureSpawner> spawner_Store;
	public static ArrayList<Integer> entries;
	//force spawn settings
	public static int maxNumberOfEntsNearSpawner = 6;
	public static int spawnerEntCheckRadius = 6;
	public static int TotalSpawnedEnts = 50;
	//1.5
	public static boolean useRadiusCheck = true;
	private spawneradjusterreload reloadExecutor;
	private spawneradjusterdebug debugExecutor;
	//1.5.1
	public static boolean advanced_stopPigSpawns = false;
	//1.6.0
	public static boolean useVault = false;
	public static Permission perms = null;
	public static boolean advanced_requireExtraPermission = false;
	//1.7.0
	public static boolean advanced_hideMessageIfSpawnerUnchanged = false;
	//1.8.0
	public static boolean allowFireball = false;
	public static boolean allowMinecart = false;
	public static boolean allowEgg = false;
	public static boolean allowBoat = false;
	public static boolean allowArrow = false;
	public static boolean spawnersCanDropExp = true;
	//1.9.0
	public static boolean allowBat = false;
	public static boolean allowWitch = false;
	public static boolean allowZombieVillager = false;
	public static boolean allowWither = false;
	public static boolean allowWitherSkeleton = false;
	public static boolean allowMobMods = false;
	public static boolean advanced_debugMode = false;
	public static boolean advanced_needSilkTouchForSpawnerDrops = false;
	public static boolean advanced_useOldTrackingSystem = false;
	public static int entsPerSpawner = 10;
	private spawneradjusterspawndata dataExecutor; //for testing
	public static boolean advanced_storeMetadata = true;
	
    public static SpawnerAdjuster getThisPlugin() { //I do not know. Needed for fancy log
        return thisPlugin; 
    }

    private static void setThisPlugin(final SpawnerAdjuster thisPlugin) //also need for fancy log and other things
    {
        SpawnerAdjuster.thisPlugin = thisPlugin;
    }
	

    public void onLoad() //onLoad is called the instant this plugin is touched.
    {
        setThisPlugin(this); //not 100% sure
        
        //initialize our arrays
        creature_Store = new ArrayList<Entity>();
        spawner_Store = new ArrayList<CreatureSpawner>();
        entries = new ArrayList<Integer>();
    }
		
	public static boolean permCheck(Player player, String perm) {
		if(advanced_debugMode) return true;
		if(player.isOp()) {
			return true;
		}
		if (ignorePermissions) {
			return true;
		}
		
		if(SuperPerms) {
			if(player.hasPermission(perm)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;// should never hit this point
		}
		
		
	}
	
	/**
	 * @param message
	 */
	public static void log_It(String message) {
		//converts 1 string log_it to a 2 string log it. Fixes leftovers.
		String level = "undefined";
		log_It("warning", "this message's priority was not properly set");
		log_It(level, message);
	}
	
	/**
	 * @param level
	 * @param message
	 */
	public static void log_It(String level, String message) {
		//convert our level into an int for logging
		int level_int = 6;
		
		if(level == "finest") level_int = 0;
		if(level == "finer") level_int = 1;
		if(level == "fine") level_int = 2;
		if(level == "info") level_int = 3;
		if(level == "warning") level_int = 4;
		if(level == "severe") level_int = 5;
		if(level == "undefined") level_int = 6;
		
	
		switch (level_int) {
		case 0: if(debugLogs) log.finest(fullName + message); break; //for people who like logs in the hexabytes
		case 1: if(debugLogs) log.finer(fullName + message); break; //for people who like log file sizes in the petabytes
		case 2: if(debugLogs) log.fine(fullName + message); break; //for people who like log file sizes in the terabytes
		case 3: log.info(fullName + message); break; //for people who like log file sizes in the gigabytes
		case 4: log.warning(fullName + message); break; //for people who like log file sizes in the megabytes
		case 5: log.severe(fullName + message); break; //for people who like log file sizes in the kilobytes
		case 6: log.warning(fullName + message); break; //for me, 'cause I forgot to specify what level of logging
		default: log.warning(fullName + "warning defaulted, maybe a typo: " + message); //also for me, because I spelled the logging level wrong
			break;
		}
	}
	
	public void onEnable() {
		thisYAML = this.getDescription();
		pluginName = thisYAML.getName();
		pluginVersion = thisYAML.getVersion();
		fullName = "[" + pluginName + "][" + pluginVersion + "] ";
		PluginManager pm = this.getServer().getPluginManager(); //register this plugin
		if(usePlayerListener) pm.registerEvents(playerListener, this); //register our playerListener
		if(useBlockListener || useRedstoneListener) pm.registerEvents(BlockListener, this);
		if(advanced_debugMode) pm.registerEvents(creeperListener, this);
		new File(mainDirectory).mkdir();  //makes our directory if needed
		if(!configFile.exists()){ //if your config does not exist then ...
	         try {
	        	log_It("info", "No config defected. Attempting to create...");
	        	configFile.createNewFile(); //... we create it then ...
	         } catch (IOException ex) { 
	             ex.printStackTrace(); //not needed anymore probably
	         }
	 
		} 
		Config.load(); ///AHHH REALLY? WRONG FUCKING ORDER
		if(advanced_debugMode) {
			log_It("warning", "Debug mode is turned on.");
		}
		if(advanced_storeMetadata) {
			if(!metadataFile.exists()) {
				try {
					log_It("info", "No metadata file detected. Attempting to create...");
					metadataFile.createNewFile(); 
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			getThisPlugin().getServer().getScheduler().scheduleSyncDelayedTask(getThisPlugin(),new Runnable() {
				public void run() {
					MetaData.load();
				}
			}, 60L);
		}
		//set up command executors.
		reloadExecutor = new spawneradjusterreload(this);
		getCommand("spawneradjusterreload").setExecutor(reloadExecutor);
		debugExecutor = new spawneradjusterdebug(this);
		getCommand("spawneradjusterdebug").setExecutor(debugExecutor);
		dataExecutor = new spawneradjusterspawndata(this);
		getCommand("spawneradjusterspawndata").setExecutor(dataExecutor);
		
		//set up repeating task to clean monster spawner arrays
		int taskID = getThisPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(getThisPlugin(), new Runnable() {

		    public void run() {
		    	//clear non-existant creatures out of storage
		    	Iterator<Entity> iterator = creature_Store.iterator();
		    	while(iterator.hasNext()) {
		    		if(iterator.next().isDead()) iterator.remove();
		    	}
		    }
		}, 60L, 65L);
		if(taskID < 0) {
			log_It("warning", "Unable to set creature array cleaning task");
		} else {
			log_It("info", "Creature storage array cleaning task set with ID: " + Integer.toString(taskID));
		}
		
		//start vault lockon
		if(useVault) {
			if(getServer().getPluginManager().getPlugin("Vault") == null) {
				log_It("warning", "Vault not detected, but vault lockon requested. OHHHH SHIT!");
			}
			if(setupVaultPermissions()) log_It("info", "Vault lockon complete");
		}
		
		log_It("info", "Loading complete");
	}
	
    private boolean setupVaultPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

	
	public void onDisable() {
		//dump metadata
	}
	
	public static void addToSpawner(CreatureSpawner spawner) {
		if(!spawner_Store.contains(spawner)) {
			spawner_Store.add(spawner);
		
		}		
		int i = spawner_Store.indexOf(spawner); 
		entries.set(i, entries.get(i) + 1);
		
	}
	
	//this method does not really belong here, lets remove in 1.7
	public static boolean canSpawn(CreatureSpawner spawner, Entity ent) {
		if(advanced_useOldTrackingSystem) {
			creature_Store.add(ent);
			int point = 0;
			if(spawner_Store.contains(spawner)) {
				point = spawner_Store.indexOf(spawner);
				if(entries.get(point) >= entsPerSpawner || creature_Store.size() > TotalSpawnedEnts) {
					return false;
				}
			}
			return true;
		} else {
			creature_Store.add(ent);
			int spawnedEnts = spawner.getMetadata("TotalSpawnedEnts").get(0).asInt();
			if(spawnedEnts >= entsPerSpawner || creature_Store.size() > TotalSpawnedEnts) {
				return false;
			} else {
				spawner.setMetadata("spawnedEnts", new FixedMetadataValue(getThisPlugin(), spawnedEnts + 1));
				spawner.getMetadata("spawnedEnts").get(0).invalidate();
				return true;
			}
		}
	} 
}

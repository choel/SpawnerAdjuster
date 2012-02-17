package com.sadmean.mc.SpawnerAdjuster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import com.sadmean.mc.SpawnerAdjuster.Config.Config;

public class SpawnerAdjuster extends JavaPlugin {

	//internal stuff
	static String mainDirectory = "plugins/SpawnerAdjuster"; //plugin directory
	private final AdjusterPlayerListener playerListener = new AdjusterPlayerListener(this); //the player listener.	
	private final AdjusterBlockListener BlockListener = new AdjusterBlockListener(this); //the block listener.	
	static public File configFile = new File(mainDirectory + File.separator + "config.yml"); //location of configfile. 
	public static PermissionHandler permissionHandler; //permissions handler
    private static SpawnerAdjuster thisPlugin = null; //I don't know what this does. Necessary for fancy log
	public static Logger log = Logger.getLogger("Minecraft"); //logger object. can be written to directly with "log.info("herp derp")
    public static String chatPrefix = ChatColor.DARK_AQUA + "[SA] " + ChatColor.GRAY;
    private static PluginDescriptionFile thisYAML;
    private static String pluginName, pluginVersion, fullName;
	//SETTINGS -to be loaded from config later
	public static boolean ignorePermissions = true;
	public static boolean debugLogs = false;
	public static boolean SuperPerms = false;
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
	public static boolean deactivedByRedstoneStatus = false;
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
	public static boolean allowEnderDragon = true;
	public static boolean allowMagmaCube = true;
	public static boolean allowMooshroom = true;
	public static boolean allowVillager = true;
	public static boolean allowSnowGolem = true;
	//1.3
	public static ArrayList<Creature> creature_Store;
	public static ArrayList<CreatureSpawner> spawner_Store;
	public static ArrayList<Integer> entries;
	
	//force spawn settings
	public static int maxNumberOfEntsNearSpawner = 6;
	public static int spawnerEntCheckRadius = 6;
	public static int TotalSpawnedEnts = 50;
	
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
        creature_Store = new ArrayList();
        spawner_Store = new ArrayList();
        entries = new ArrayList();
    }
	 
	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
			if (this.permissionHandler == null) {
				if (permissionsPlugin != null) {
					this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
					log_It("info", "Legacy Permissions support will be going away in the future, please switch to a new permission system soon");
					log_It("info", "PermissionsEX, bPermissions and bukkitPermissions are all good ones. Visit bukkit.org!");
				} else {
					log_It("info", "Legacy Permission system not detected!");
					//ignorePermissions = true;
					if(!SuperPerms) {
						log_It("info", "SuperPermissions also not found. Your settings are probably incorrect.");
						//ignorePermissions = true;
					}
				}
			}
		}
	
	public static boolean permCheck(Player player, String perm) {
		if (ignorePermissions) {
			if(ignore_opsOnly) {
				if(player.isOp()) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		if(SuperPerms) {
			if(player.hasPermission(perm)) {
				return true;
			} else {
				return false;
			}
		} else {
			//legacy permissions support
			if(permissionHandler.has(player, perm)) {
				return true;
			} else {
				return false;
			}
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
		new File(mainDirectory).mkdir();  //makes our directory if needed
		if(!configFile.exists()){ //if your config does not exist then ...
	         try {
	        	log_It("info", "No config defected. Attempting to create...");
	        	configFile.createNewFile(); //... we create it then ...
	         } catch (IOException ex) { 
	             ex.printStackTrace(); //not needed anymore probably
	         }
	 
		} else { 
			log_It("info", "Config file exists. Loading...");
		}
		Config.load(); ///AHHH REALLY? WRONG FUCKING ORDER
		setupPermissions();

		
		//set up repeating task to clean monster spawner arrays
		int taskID = getThisPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(getThisPlugin(), new Runnable() {

		    public void run() {
		    	//clear non-existant creatures out of storage
		    	//int i = 0;
		    	Iterator<Creature> iterator = creature_Store.iterator();
		    	while(iterator.hasNext()) {
		    		if(iterator.next().isDead()) iterator.remove();
		    	}
		    	//decrement ALL entries
		    	/* disabed for now, may not actually need
		    	int point = 0;
		    	Iterator<Integer> itr = entries.iterator();
		    	while(itr.hasNext()) {
		    		if(itr.next() == 0) {
		    			itr.remove();
		    			spawner_Store.remove(point);
		    		} else {
		    			entries.set(point, itr.next() - 1);
		    		}
		    		point++;
		    	}
		        */
		    }
		}, 60L, 65L);
		if(taskID < 0) {
			log_It("warning", "Unable to set creature array cleaning task");
		} else {
			log_It("info", "Creature storage array cleaning task set with ID: " + Integer.toString(taskID));
		}
		log_It("info", "Loading complete");
	}
	
	public void onDisable() {
		
	}
	
	public static void addToSpawner(CreatureSpawner spawner) {
		if(!spawner_Store.contains(spawner)) {
			spawner_Store.add(spawner);
			entries.add(1);
		} else {
			int i =spawner_Store.indexOf(spawner); 
			int setter = entries.get(i);
			setter++;
			entries.set(i, setter);
		}
	}
	
	public static boolean canSpawn(CreatureSpawner spawner, Creature ent) {
		creature_Store.add(ent);
		int point = 0;
		if(spawner_Store.contains(spawner)) {
			point = spawner_Store.indexOf(spawner);
			if(entries.get(point) >= 10 || creature_Store.size() > TotalSpawnedEnts) {
				return false;
			}
		}
		return true;
	}

}

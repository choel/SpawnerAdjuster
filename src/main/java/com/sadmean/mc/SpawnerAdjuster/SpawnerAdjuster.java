package com.sadmean.mc.SpawnerAdjuster;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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
	//SETTINGS -to be loaded from config later
	public static boolean ignorePermissions = true;
	public static boolean debugLogs = false;
	public static boolean SuperPerms = false;
	public static boolean usePlayerListener = true; //no longer changable in config
	public static boolean useRedstoneListener = true; //no longer changable in config
	public static boolean useBlockListener = true; //no longer changable in config
	//1.1
	public static boolean allowDroppedSpawners = true;
	public static boolean ignore_opsOnly = false;
	public static boolean ignore_OpsOnlyRedstone = false; //not yet used
	public static boolean ignore_OpsOnlyChangeSpawner = false; //not yet used
	public static boolean ignore_OpsOnlyDropSpanwers = false; //not yet used
	public static boolean opsChangeSpawnTypeOnly = false;
	public static boolean respondToRedstone = true;
	public static boolean redstoneForcesSpawn = true;
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
    }
	 
	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
			if (this.permissionHandler == null) {
				if (permissionsPlugin != null) {
					this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
				} else {
					log_It("info", "Permission system not detected!");
					//ignorePermissions = true;
					if(!SuperPerms) {
						log_It("info", "SuperPermissions also not found. Ignore Permissions forced to true");
						ignorePermissions = true;
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
		PluginDescriptionFile thisYAML = getThisPlugin().getDescription();
		String pluginName = thisYAML.getName();
		String pluginVersion = thisYAML.getVersion();
		String fullName = "[" + pluginName + "][" + pluginVersion + "] ";
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
		PluginManager pm = this.getServer().getPluginManager(); //register this plugin
		log_It("info", "Enable Started");
		if(usePlayerListener) pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this); //register our playerListener
		if(useBlockListener) pm.registerEvent(Event.Type.BLOCK_BREAK, BlockListener, Event.Priority.Normal, this);
		if(useRedstoneListener) pm.registerEvent(Event.Type.REDSTONE_CHANGE, BlockListener, Event.Priority.Normal, this);
		new File(mainDirectory).mkdir();  //makes our directory if needed
		if(!configFile.exists()){ //if your config does not exist then ...
	         try {
	        	log_It("info", "No config defected. Attempting to create.");
	        	configFile.createNewFile(); //... we create it then ...
	         } catch (IOException ex) { 
	             ex.printStackTrace(); //not needed anymore probably
	         }
	 
		} else { 
			//it does exist?
		}
		setupPermissions();
		Config.load();
	}
	
	public void onDisable() {
		
	}
	

}

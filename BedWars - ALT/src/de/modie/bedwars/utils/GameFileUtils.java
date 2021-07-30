package de.modie.bedwars.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;

public class GameFileUtils {
	
	public static File folder = new File("plugins/BedWars");
	public static File file = new File("plugins/BedWars/data.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}
	public static void setUPFiles() {
		if(!(folder.exists()))
			folder.mkdir();
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		
		cfg.addDefault("Maps", new ArrayList<>());
		cfg.options().copyDefaults(true);
		saveFile();
			
	}
	
	public static void SetLoclocation(String name, Location loc) {
		cfg.set("Locations." + name +".X", loc.getBlockX() + 0.5);
		cfg.set("Locations." + name +".Y", loc.getBlockY());
		cfg.set("Locations." + name +".Z", loc.getBlockZ() + 0.5);
		cfg.set("Locations." + name +".Yaw", Math.round(loc.getYaw() / 45)  * 45);
		cfg.set("Locations." + name +".Pitch", Math.round(loc.getPitch() / 45)  * 45);
		cfg.set("Locations." + name +".world", loc.getWorld().getName());
		saveFile();
	}
	
	public static void SetLoclocation(String mapName, String name, Location loc) {
		cfg.set("Locations." + mapName + "." + name + ".X", loc.getBlockX() + 0.5);
		cfg.set("Locations." + mapName + "." + name + ".Y", loc.getBlockY());
		cfg.set("Locations." + mapName + "." + name + ".Z", loc.getBlockZ() + 0.5);
		cfg.set("Locations." + mapName + "." + name + ".Yaw", Math.round(loc.getYaw() / 45)  * 45);
		cfg.set("Locations." + mapName + "." + name +".Pitch", Math.round(loc.getPitch() / 45)  * 45);
		cfg.set("Locations." + mapName + "." + name + ".world", loc.getWorld().getName());
		saveFile();
	}
	
	public static void SetBedLocation(String mapName, String name, Location loc, String facing) {
		cfg.set("Locations." + mapName + "." + name + ".X", loc.getBlockX() + 0.5);
		cfg.set("Locations." + mapName + "." + name + ".Y", loc.getBlockY());
		cfg.set("Locations." + mapName + "." + name + ".Z", loc.getBlockZ() + 0.5);
		cfg.set("Locations." + mapName + "." + name + ".world", loc.getWorld().getName());
		cfg.set("Locations." + mapName + "." + name + ".Facing", facing);
		saveFile();
	}
	
	public static void SetBedLocation(String mapName, String name, Location loc) {
		cfg.set("Locations." + mapName + "." + name + ".X", loc.getBlockX() + 0.5);
		cfg.set("Locations." + mapName + "." + name + ".Y", loc.getBlockY());
		cfg.set("Locations." + mapName + "." + name + ".Z", loc.getBlockZ() + 0.5);
		cfg.set("Locations." + mapName + "." + name + ".world", loc.getWorld().getName());
		saveFile();
	}
	
	public static Location getBlockLocation(String mapName, String name) {
		String mainPath = "Location." + mapName + "." + name;
		return(new Location(Bukkit.getWorld(cfg.getString(mainPath + ".world")), cfg.getDouble(mainPath + ".X"), cfg.getDouble(mainPath + ".Y"), cfg.getDouble(mainPath + ".Z")));
		
	}
	
	public static Location getLocation(String name) {
		String mainPath = "Location." + name;
		Location loc = new Location(Bukkit.getWorld(cfg.getString(mainPath + ".world")), cfg.getDouble(mainPath + ".X"), cfg.getDouble(mainPath + ".Y"), cfg.getDouble(mainPath + ".Z"));
		loc.setYaw((float) cfg.getDouble(mainPath + ".Yaw"));
		loc.setPitch((float) cfg.getDouble(mainPath + ".Pitch"));
		return loc;
		
	}
	
	public static Location getLocation(String mapName, String name) {
		String mainPath = "Location." + mapName + "." + name;
		
		Location loc = new Location(Bukkit.getWorld(cfg.getString(mainPath + ".world")), cfg.getDouble(mainPath + ".X"), cfg.getDouble(mainPath + ".Y"), cfg.getDouble(mainPath + ".Z"));
		loc.setYaw((float) cfg.getDouble(mainPath + ".Yaw"));
		loc.setPitch((float) cfg.getDouble(mainPath + ".Pitch"));
		return loc;
		
	}
	
	public static BlockFace getBedFacing(String mapName, String name) {
		return BlockFace.valueOf(cfg.getString("Locations." + mapName + "." + name + ".Facing"));
		
	}
}

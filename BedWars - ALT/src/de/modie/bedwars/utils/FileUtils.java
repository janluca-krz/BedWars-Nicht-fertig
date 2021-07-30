package de.modie.bedwars.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileUtils {
	
	public static File folder = new File("plugin/BedWars");
	public static File file = new File("plugins/BedWars/config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
		
		Utils.prefix = getConfigString("Messages.Prefix");
		Utils.curretntMap = getConfigString("Settings.MapName").toUpperCase();
	}
	public static void setUPFiles() {
		if(folder.exists())
			folder.mkdir();
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
		
		cfg.addDefault("Message.prefix", "&8[&bBedWars] &7");
		cfg.addDefault("Settings.mapname", "NONE");
		cfg.addDefault("Settings.GameFormat", "8x1");
		cfg.options().copyDefaults(true);
		saveFile();
			
	}
	
	public static String getConfigString(String Path) {
		return cfg.getString(Path).replace("&", "§");
	}

}

package de.modie.bedwars.utils;

import org.bukkit.Bukkit;

public class Utils {
	
	public static String prefix;
	public static String curretntMap;

	public static void sendConsole(String msg) {
		Bukkit.getConsoleSender().sendMessage(prefix + msg);
		
	}

}

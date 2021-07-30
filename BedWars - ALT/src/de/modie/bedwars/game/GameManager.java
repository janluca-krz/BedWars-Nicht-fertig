package de.modie.bedwars.game;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class GameManager {
	
	public static ArrayList<BedWarsTeam> teams = new ArrayList<>();
	
	public static BedWarsTeam getTeam(Player p) {
		BedWarsTeam team = null;
		
		for(BedWarsTeam bedwarsTeam : teams) {
			if(bedwarsTeam.isTeammember(p))
				team = bedwarsTeam;
		}
		return team;
	}
	
	public static BedWarsTeam getTeamByName(String name) {
		BedWarsTeam team = null;
		
		for(BedWarsTeam bedwarsTeam : teams) {
			if(bedwarsTeam.getTeamName().equalsIgnoreCase(name))
				team = bedwarsTeam;
		}
		return team;
	}

}

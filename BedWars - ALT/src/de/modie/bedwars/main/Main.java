package de.modie.bedwars.main;

import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;

import de.modie.bedwars.commands.BedWarsCommand;
import de.modie.bedwars.game.BedWarsTeam;
import de.modie.bedwars.game.GameManager;
import de.modie.bedwars.utils.FileUtils;
import de.modie.bedwars.utils.GameFileUtils;
import de.modie.bedwars.utils.Utils;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		FileUtils.setUPFiles();
		GameFileUtils.setUPFiles();
		
		getCommand("bedwars").setExecutor(new BedWarsCommand());
		
		if(!(FileUtils.getConfigString("Settings.mapName").equalsIgnoreCase("NONE"))) {
			String gameFormat = FileUtils.getConfigString("Settings.GameFormat");
			
			if(gameFormat.equalsIgnoreCase("4x2")) {
				GameManager.teams.add(new BedWarsTeam("Blue", "§9Blue §7| §9", "§9", Color.AQUA, 1));
				GameManager.teams.add(new BedWarsTeam("Red", "§cRed §7| §c", "§c", Color.RED, 1));
				GameManager.teams.add(new BedWarsTeam("White", "White §7|", "", Color.WHITE, 1));
				GameManager.teams.add(new BedWarsTeam("Black", "§0Black §7| §0", "§0", Color.BLACK, 1));
				
				for(BedWarsTeam team : GameManager.teams) {
					team.setSpawnLocation(GameFileUtils.getLocation("Spawn" + team.getTeamName().toUpperCase()));
					team.setBedTop(GameFileUtils.getBlockLocation(Utils.curretntMap, "Bed" + team.getTeamName().toUpperCase()));
					team.setBedBottom(team.getBedTop().getBlock().getRelative(GameFileUtils.getBedFacing(Utils.curretntMap, "Bed" + team.getTeamName().toUpperCase())).getLocation());
					team.PlaceBed();
				}
			}
		}
		
		Utils.sendConsole("§aDas BedWars plugin funktioniert!");
	}

}

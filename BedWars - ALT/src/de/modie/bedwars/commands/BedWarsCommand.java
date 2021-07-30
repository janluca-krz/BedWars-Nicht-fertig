package de.modie.bedwars.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modie.bedwars.game.GameManager;
import de.modie.bedwars.utils.GameFileUtils;
import de.modie.bedwars.utils.Utils;

public class BedWarsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(p.hasPermission("easymc-bedwars.setup")) {
				if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("setlobby")) {
						GameFileUtils.SetLoclocation("lobby", p.getLocation());			
						p.sendMessage(Utils.prefix + "§7Die Position §eLobby §7wurde §aerfolgreich §7gesetzt");
					} else if(args.length >=2) {
						if(args[0].equalsIgnoreCase("createmap")) {
							List<String> maps = GameFileUtils.cfg.getStringList("Maps");				
							if(!(maps.contains(args[1].toUpperCase()))) {
								maps.add(args[1].toUpperCase());
								GameFileUtils.cfg.set("Maps", maps);
								GameFileUtils.saveFile();
								
								p.sendMessage(Utils.prefix + "§7Die Map §e" + args[1].toUpperCase() + "§7Wwurde §aerfolgreich §7erstellt");
							} else {
								p.sendMessage(Utils.prefix + "§cDie Map §e" + args[1].toUpperCase() + " §cexistiert bereits");
							}
						} else if(args[0].equalsIgnoreCase("deletemap")) {
						
							List<String> maps = GameFileUtils.cfg.getStringList("Maps");
							
							if(maps.contains(args[1].toUpperCase())) {
								maps.remove(args[1].toUpperCase());
								GameFileUtils.cfg.set("Maps", maps);
								
								String path = "Location." + args[1].toUpperCase();
								
								GameFileUtils.cfg.set(path, null);
								GameFileUtils.saveFile();
								
								p.sendMessage(Utils.prefix + "§7Die Map §e" + args[1].toUpperCase() + "§7Wwurde §aerfolgreich §cgelöscht");
							} else {
								p.sendMessage(Utils.prefix + "§cDie Map §e" + args[1].toUpperCase() + " §cexistiert nicht");
							} 
					} else if(args.length >= 3) {
						if(args[0].equalsIgnoreCase("setspawn")) {
							if(GameFileUtils.cfg.getStringList("Maps").contains(args[1].toUpperCase())) {
								if(GameManager.getTeamByName(args[2]) != null) {
									GameFileUtils.SetLoclocation(Utils.curretntMap,"Spawn" + args[2].toUpperCase(), p.getLocation());
									
									p.sendMessage(Utils.prefix + "§7Die Position §eSpawn-" + args[2].toUpperCase() + "§7wurde §aerfolgreich §7gesetzt");
								} else {
									p.sendMessage(Utils.prefix + "§cDas Team §e" + args[2].toUpperCase() + " §cexistiert nicht");
								}
							  
								} else {
									p.sendMessage(Utils.prefix + "§cDie Map §e" + args[1].toUpperCase() + " §cexistiert nicht");
								}
						} else if(args.length >= 3) {
							if(args[0].equalsIgnoreCase("setspawn")) {
								if(GameFileUtils.cfg.getStringList("Maps").contains(args[1].toUpperCase())) {
									if(GameManager.getTeamByName(args[2]) != null) {
										if(args[3].equalsIgnoreCase("NORTH") || args[3].equalsIgnoreCase("EAST") || args[3].equalsIgnoreCase("SOUTH") || args[3].equalsIgnoreCase("WEST")) {
											GameFileUtils.SetBedLocation(Utils.curretntMap,"Spawn" + args[2].toUpperCase(), p.getLocation(), args[3]);
												p.sendMessage(Utils.prefix + "§7Die Position §eBed-" + args[2].toUpperCase() + "§7wurde §aerfolgreich §7gesetzt");
											} else {
												p.sendMessage(Utils.prefix + "§7Das Facing §e " + args[3].toUpperCase() + " §7existiert nicht");
												p.sendMessage(Utils.prefix + "§7Alles Facings:§eNORTH, EAST. SOUTH, WEST");
											}
										} else {
											p.sendMessage(Utils.prefix + "§cDas Team §e" + args[2].toUpperCase() + " §cexistiert nicht");
										}
									} else {
										p.sendMessage(Utils.prefix + "§cDie Map §e" + args[1].toUpperCase() + " §cexistiert nicht");
									}
							} else {
								sendHelp(p);
							} 
						} else {
							sendHelp(p);
						} 
					} else {
						sendHelp(p);
					}			
				} else {
					sendHelp(p);
				}
			} else {
				sendHelp(p);
			}
		} else {
			p.sendMessage(Utils.prefix + "§c Dazu hast du leider keine Rechte :(");
		}
	} else {
		Utils.sendConsole("§cDu must ein Spieler sein um diesen Command ausführen zu können :(");
	}	
	return true;
}		
	private void sendHelp(Player p) {
		p.sendMessage(Utils.prefix + "§7/bedwars [Setlobby]");
		p.sendMessage(Utils.prefix + "§7/bedwars [CreateMap / deleteMap [Name] ]");
		p.sendMessage(Utils.prefix + "§7/bedwars [SetSpawn] [Map] [TeamName]");
		p.sendMessage(Utils.prefix + "§7/bedwars [SetBed] [Map] [TeamName] [Facing]");
	}
}


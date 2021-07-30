package de.modie.bedwars.game;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;

import de.modie.bedwars.utils.GameFileUtils;
import de.modie.bedwars.utils.Utils;

public class BedWarsTeam {
	
	private String teamName;
	private String prefix;
	private String color;
	private Color armorcolor;
	private int teamSize;
	private ArrayList<UUID> members;
	@SuppressWarnings("unused")
	private Location SpawnLocation;
	private Location bedTop, bedBottom;
	
	public BedWarsTeam(String teamName, String prefix, String color, Color armorcolor , int teamSize) {
		super();
		this.teamName = teamName;
		this.prefix = prefix;
		this.color = color;
		this.armorcolor = armorcolor;
		this.teamSize = teamSize;
		this.members = new ArrayList<>();
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public String prefix() {
		return prefix;
	}
	
	public String getColor() {
		return color;
	}
	
	public Color getArmorColor() {
		return armorcolor;
	}
	
	public int getTeamSize() {
		return teamSize;
	}
	
	public ArrayList<UUID> getMembers() {
		return members;
	}
	
	public boolean isTeammember(Player p) {
		return(members.contains(p.getUniqueId()));
	}
	
	public void setBedTop(Location bedTop) {
		this.bedTop = bedTop;
	}
	
	public Location getBedTop() {
		return bedTop;
	}
	
	public void setBedBottom(Location bedBottom) {
		this.bedBottom = bedBottom;
	}
	
	public Location getBedBottom() {
		return bedBottom;
	}
	
	public void PlaceBed() {
		BlockFace face = GameFileUtils.getBedFacing(Utils.curretntMap, "Bed" + teamName.toUpperCase());
		if(bedBottom != null) {
			Block b = bedBottom.getBlock();
			b.setType(Material.BED_BLOCK);
			
			BlockState state = b.getState();
			Bed bed = new Bed(Material.BED_BLOCK);
			bed.setHeadOfBed(false);
			bed.setFacingDirection(face);
			state.setData(bed);
			state.update(true);

		}
		if(bedTop != null) {
			Block b = bedBottom.getBlock();
			b.setType(Material.BED_BLOCK);
			
			BlockState state = b.getState();
			Bed bed = new Bed(Material.BED_BLOCK);
			bed.setHeadOfBed(true);
			bed.setFacingDirection(face);
			state.setData(bed);
			state.update(true);
		}
	}

	public void setSpawnLocation(Location spawnLocation) {
		this.SpawnLocation = spawnLocation;
	}
	

}

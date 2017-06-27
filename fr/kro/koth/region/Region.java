package fr.kro.koth.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;

public class Region {

	private static HashMap<String, Region> rgs = new HashMap<>();

	private String name;
	private String world;
	private int gX, gY, gZ;
	private int pX, pY, pZ;

	public Region(String name, String world, int x1, int y1, int z1, int x2, int y2, int z2) {
		this.name = name;
		this.world = world;

		if (x1 > x2) {
			gX = x1;
			pX = x2;
		} else {
			gX = x2;
			pX = x1;
		}
		if (y1 > y2) {
			gY = y1;
			pY = y2;
		} else {
			gY = y2;
			pY = y1;
		}
		if (z1 > z2) {
			gZ = z1;
			pZ = z2;
		} else {
			gZ = z2;
			pZ = z1;
		}

		rgs.put(name, this);
		new SaveRG(this).save();
	}

	public Region(String name, Location loc1, Location loc2) {
		this(name, loc1.getWorld().getName(), loc1.getBlockX(), loc1.getBlockY(), loc1.getBlockZ(), loc2.getBlockX(),
				loc2.getBlockY(), loc2.getBlockZ());
	}

	public static List<Region> getRegions() {
		List<Region> regions = new ArrayList<>();
		for (String r : rgs.keySet()) {
			regions.add(rgs.get(r));
		}
		return regions;
	}

	public static boolean isInRegion(Location loc) {
		int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
		for (Region reg : getRegions()) {
			if (loc.getWorld().getName().equals(reg.getWorld()) && (x <= reg.getGrandX() && x >= reg.getPetitX())
					&& (y <= reg.getGrandY() && y >= reg.getPetitY())
					&& (z <= reg.getGrandZ() && z >= reg.getPetitZ())) {
				return true;
			}
		}
		return false;
	}

	public static Region getRegion(String name) {
		for (Region reg : getRegions()) {
			if (reg.getName().equals(name))
				return reg;
		}
		return null;
	}

	public static boolean existRegion(String name) {
		return rgs.containsKey(name);
	}

	public static Region getRegion(Location loc) {
		int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
		for (Region reg : getRegions()) {
			if (loc.getWorld().getName().equals(reg.getWorld()) && (x <= reg.getGrandX() && x >= reg.getPetitX())
					&& (y <= reg.getGrandY() && y >= reg.getPetitY())
					&& (z <= reg.getGrandZ() && z >= reg.getPetitZ())) {
				return reg;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getWorld() {
		return world;
	}

	public int getGrandX() {
		return gX;
	}

	public int getGrandY() {
		return gY;
	}

	public int getGrandZ() {
		return gZ;
	}

	public int getPetitX() {
		return pX;
	}

	public int getPetitY() {
		return pY;
	}

	public int getPetitZ() {
		return pZ;
	}

	public void del() {
		rgs.remove(name);
		name = null;
		world = null;
		gX = 0;
		pX = 0;
		gY = 0;
		pY = 0;
		gZ = 0;
		pZ = 0;
	}

	public void rePlace(String world, int x1, int y1, int z1, int x2, int y2, int z2) {
		this.world = world;
		if (x1 > x2) {
			gX = x1;
			pX = x2;
		} else {
			gX = x2;
			pX = x1;
		}
		if (y1 > y2) {
			gY = y1;
			pY = y2;
		} else {
			gY = y2;
			pY = y1;
		}
		if (z1 > z2) {
			gZ = z1;
			pZ = z2;
		} else {
			gZ = z2;
			pZ = z1;
		}
		new SaveRG(this).save();
	}

	public void rePlace(Location loc1, Location loc2) {
		rePlace(loc1.getWorld().getName(), loc1.getBlockX(), loc1.getBlockY(), loc1.getBlockZ(), loc2.getBlockX(),
				loc2.getBlockY(), loc2.getBlockZ());
	}
}

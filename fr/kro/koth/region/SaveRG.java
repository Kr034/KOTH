package fr.kro.koth.region;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.kro.koth.Main;

public class SaveRG {

	private Region rg;
	private static File d = new File(Main.getInstance().getDataFolder() + "/arenas/");

	public SaveRG(Region rg) {
		this.rg = rg;
	}

	public void save() {
		File f = new File(d, rg.getName() + ".yml");
		if (!f.exists()) {
			try {
				f.createNewFile();
				File yml = new File(d, rg.getName() + ".yml");
				FileConfiguration conf = YamlConfiguration.loadConfiguration(yml);
				conf.set("name", rg.getName());
				conf.set("world", rg.getWorld());
				conf.set("g-x", rg.getGrandX());
				conf.set("g-y", rg.getGrandY());
				conf.set("g-z", rg.getGrandZ());
				conf.set("p-x", rg.getPetitX());
				conf.set("p-y", rg.getPetitY());
				conf.set("p-z", rg.getPetitZ());
				conf.save(yml);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File yml = new File(d, rg.getName() + ".yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(yml);
		conf.set("name", rg.getName());
		conf.set("world", rg.getWorld());
		conf.set("g-x", rg.getGrandX());
		conf.set("g-y", rg.getGrandY());
		conf.set("g-z", rg.getGrandZ());
		conf.set("p-x", rg.getPetitX());
		conf.set("p-y", rg.getPetitY());
		conf.set("p-z", rg.getPetitZ());
		try {
			conf.save(yml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadAllSaves() {
		File[] fs = d.listFiles();
		for (File f : fs) {
			FileConfiguration conf = YamlConfiguration.loadConfiguration(f);
			String name = conf.getString("name");
			String world = conf.getString("world");
			int gX = conf.getInt("g-x"), gY = conf.getInt("g-y"), gZ = conf.getInt("g-z"), pX = conf.getInt("p-x"),
					pY = conf.getInt("p-y"), pZ = conf.getInt("p-z");
			new Region(name, world, gX, gY, gZ, pX, pY, pZ);
		}
	}
}

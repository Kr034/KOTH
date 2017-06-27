package fr.kro.koth;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.kro.koth.cmds.CMDManager;
import fr.kro.koth.game.GameState;
import fr.kro.koth.region.SaveRG;

public class Main extends JavaPlugin {

	private static Main i;

	public void onEnable() {
		
		i = this;

		new CMDManager();

		File doc = new File(getDataFolder() + "/");
		if (!doc.exists())
			doc.mkdir();
		doc = new File(getDataFolder() + "/arenas/");
		if (!doc.exists())
			doc.mkdir();
		SaveRG.loadAllSaves();

		Bukkit.getServer().getPluginManager().registerEvents(new KOTHListener(), this);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

			@Override
			public void run() {
				String date = new SimpleDateFormat("uu - kk:mm:ss").format(new Date(System.currentTimeMillis()));
				if (date.equals("05 - 12:47:00")) {
					Bukkit.broadcastMessage("§6KOTH §3dans 15 minutes !§2 Pour plus d'infos : /f koth !");
				}
			}
		}, 20, 20);
	}

	public void onDisable() {

	}

	private static GameState current = GameState.NORMAL;

	public static void setGameState(GameState gs) {
		current = gs;

	}

	public static GameState getCurrentState() {
		return current;

	}

	public static void setNextGameState() {
		current = GameState.getGameState((byte) (current.getEtapeInGame() + 1));
	}

	public static JavaPlugin getInstance() {
		return i;
	}
}

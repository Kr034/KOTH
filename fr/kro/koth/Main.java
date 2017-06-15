package fr.kro.koth;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.kro.koth.cmds.CMDManager;
import fr.kro.koth.game.GameState;

public class Main extends JavaPlugin {

	public void onEnable() {

		new CMDManager();

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

			@Override
			public void run() {
				String date = new SimpleDateFormat("uu - kk:mm:ss").format(new Date(System.currentTimeMillis()));
				if (date.equals("03 - 15:00:00")) {

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
}

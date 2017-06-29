package fr.kro.koth.game;

import fr.kro.koth.Main;

public class KOTHManager {

	public static void startGame() {
		if (Main.getCurrentState() != GameState.GAME)
			;
		Main.setGameState(GameState.GAME);
		;
	}

	public static void stopGame() {
		if (Main.getCurrentState() == GameState.GAME)
			;
		Main.setGameState(GameState.FINISH);
	}
}
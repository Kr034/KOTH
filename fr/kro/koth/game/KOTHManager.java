package fr.kro.koth.game;

import fr.kro.koth.Main;


public class KOTHManager {
		  
	  public static void startGame() {
	  	if(Main.getCurrentState()!=GameState.NORMAL);
	    Main.setNextGameState();
	  }
	  
	  public static void stopGame() {
	  	
	}
}
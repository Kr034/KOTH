package fr.kro.koth.game;

public enum GameState {

	NORMAL(0), WAIT(1), GAME(2), FINISH(3);

	private byte e;

	GameState(int e) {
		this.e = (byte) e;
	}

	public static GameState getGameState(byte etape) {
		for (GameState gs : values()) {
			if (gs.e == etape)
				return gs;
		}

		return NORMAL;
	}

	public byte getEtapeInGame() {
		return e;
	}

}

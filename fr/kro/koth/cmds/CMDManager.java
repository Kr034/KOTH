package fr.kro.koth.cmds;

import be.Vilevar.PvPFac.Perms.*;

public class CMDManager {

	public CMDManager() {
		new Permission("cmd.koth", 0, new Command("cmd.koth", "koth", "<koth>", "Commandes de KOTH.", CMDKOTH.class));
	}
}

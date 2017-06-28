package fr.kro.koth.cmds;

import be.Vilevar.PvPFac.Perms.ACommand;
import be.Vilevar.PvPFac.Perms.Command;
import be.Vilevar.PvPFac.Perms.Permission;

public class CMDManager {

	public CMDManager() {
		new Permission("cmd.koth", 0,
				new Command("cmd.koth", new String[] { "koth", "k" }, "<koth>", "Commande de KOTH.", CMDKOTH.class));
		new ACommand("koth", new String[] { "koth", "k" }, "<koth> [region | run | stop] [add | del | list] [nom]", "Commande de KOTH ADMIN.", AKOTHCMD.class);

	}
}

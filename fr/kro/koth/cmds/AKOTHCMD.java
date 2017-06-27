package fr.kro.koth.cmds;

import be.Vilevar.PvPFac.Commands.Admins.CmdAdminsCommands;
import be.Vilevar.PvPFac.Perms.ACommand;
import be.Vilevar.PvPFac.Player.Admin;
import fr.kro.koth.Main;
import fr.kro.koth.game.GameState;
import fr.kro.koth.game.KOTHManager;
import fr.kro.koth.region.Region;

public class AKOTHCMD extends CmdAdminsCommands {

	public AKOTHCMD(Admin a, String[] args, ACommand acmd) {
		if (!a.hasPermission(acmd.getPermission())) {
			a.sendMessage(notPerm);
			return;
		}
		if (args.length == 0) {
			a.sendMessage("§a/" + label + " koth §c" + acmd.getUtilisation());
			return;
		}
		if (args.length >= 1) {
			// Run and stop commands
			{
				if (args[0].equalsIgnoreCase("run")) {
					if (args.length > 1)
						a.sendMessage(suppl_arg);
					return;
				} else if (args[0].equalsIgnoreCase("stop")) {
					if (args.length > 1)
						a.sendMessage(suppl_arg);
					if (Main.getCurrentState() != GameState.NORMAL)
						KOTHManager.stopGame();
					else
						a.sendMessage("§cAucune partie n'est en cours.");
					return;
				}
			}
		}
		if (args.length >= 1) {
			// Region Commands
			{
				if (args[0].equalsIgnoreCase("region") || args[0].equalsIgnoreCase("rg")) {
					if (args.length < 2)
						a.sendMessage("§a/" + label + " koth §c" + acmd.getUtilisation());
					if (args.length > 2)
						a.sendMessage(suppl_arg);
					if (args.length == 2) {
						if (args[1].equalsIgnoreCase("list"))
							a.sendMessage("§2Liste des Regions dans le monde :§e " + Region.getRegions());
						return;
					} else if (args.length == 3) {
						if (args[1].equalsIgnoreCase("add"))
							if (!args[2].equals(null))
								a.sendMessage("§2La region :§e " + args[2].toString() + "§2 à été crée.");
						if (args.length > 3)
							a.sendMessage(suppl_arg);
						return;
					}
				}
			}
		}
	}
}
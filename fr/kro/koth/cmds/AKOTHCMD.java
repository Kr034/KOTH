package fr.kro.koth.cmds;

import java.util.UUID;

import org.bukkit.Location;

import be.Vilevar.PvPFac.Commands.Admins.CmdAdminsCommands;
import be.Vilevar.PvPFac.Perms.ACommand;
import be.Vilevar.PvPFac.Player.Admin;
import fr.kro.koth.KOTHListener;
import fr.kro.koth.Main;
import fr.kro.koth.game.GameState;
import fr.kro.koth.game.KOTHManager;
import fr.kro.koth.region.Region;

public class AKOTHCMD extends CmdAdminsCommands {

	StringBuilder str = new StringBuilder("");

	public AKOTHCMD(Admin a, String[] args, ACommand acmd) {
		if (!a.hasPermission(acmd.getPermission())) {
			a.sendMessage(notPerm);
			return;
		}
		if (args.length == 0) {
			a.sendMessage("§a/" + label + " §c" + acmd.getUtilisation());
			return;
		}
		if (args.length >= 1) {
			// Run and stop commands
			{
				if (args[0].equalsIgnoreCase("run")) {
					KOTHManager.startGame();
					a.sendMessage("§2Vous avez forcé le démarage de la partie de la partie.");
					if (args.length > 1)
						a.sendMessage(suppl_arg);
					return;
				} else if (args[0].equalsIgnoreCase("stop")) {
					if (args.length > 1)
						a.sendMessage(suppl_arg);
					if (Main.getCurrentState() == GameState.GAME) {
						a.sendMessage("§2Vous avez forcé l'arrêt de la partie.");
						KOTHManager.stopGame();
						return;
					} else if (Main.getCurrentState() != GameState.GAME)
						a.sendMessage("§cAucune partie n'est en cours.");
					return;
				}
			}
		}
		if (args.length >= 1) {
			// Region commands
			{
				if (args[0].equalsIgnoreCase("region") || args[0].equalsIgnoreCase("rg")) {
					if (args.length == 1) {
						a.sendMessage("§a/" + label + " §c" + acmd.getUtilisation());
						return;
					}
					if (args[1].equalsIgnoreCase("list")) {
						if (args.length >= 3) {
							a.sendMessage(suppl_arg);
							return;
						}
						for (Region r : Region.getRegions()) {
							if (str.length() != 0) {
								str.append("§e, ");
							}
							str.append("§d" + r.getName());
						}
						a.sendMessage("§2Liste des Regions dans le monde :§e " + str.toString());
						return;
					}
					if (args[1].equalsIgnoreCase("add")) {
						if (args.length == 3) {
							if (!pt2(a.getUniqueId())) {
								a.sendMessage("§cLa region : §e" + args[2].toString()
										+ "§c ne peut être créer car aucun points n'a été sélectioner");
								return;
							}
							if (Region.existRegion(args[2])) {
								a.sendMessage("§cLa region :§e " + args[2].toString() + "§c existe déjà.");
								return;
							} else if (!Region.existRegion(args[2])) {
								new Region(args[2], ((Location[]) KOTHListener.location.get(a.getUniqueId()))[0],
										((Location[]) KOTHListener.location.get(a.getUniqueId()))[1]);
								a.sendMessage("§2La region :§e " + args[2].toString() + "§2 à été créer.");
								return;
							} else if (args.length > 3)
								a.sendMessage(suppl_arg);
							if (args.length <= 2)
								a.sendMessage("§a/" + label + " §c" + acmd.getUtilisation());
							return;
						}
					}
					if (args[1].equalsIgnoreCase("del")) {
						if (args.length == 3) {
							if (!Region.existRegion(args[2])) {
								a.sendMessage("§cLa region :§e " + args[2].toString() + "§c n'existe pas.");
								return;
							} else if (Region.existRegion(args[2])) {
								Region.getRegion(args[2]).del();
								a.sendMessage("§2La region :§e " + args[2].toString() + "§2 à été supprimer.");
								return;
							} else if (args.length > 3)
								a.sendMessage(suppl_arg);
							if (args[2] == null)
								a.sendMessage("§a/" + label + " §c" + acmd.getUtilisation());
							return;
						}
					}
				}
			}
		}
	}

	private static boolean pt2(UUID uuid) {
		return (KOTHListener.location.containsKey(uuid)) && (((Location[]) KOTHListener.location.get(uuid))[0] != null)
				&& (((Location[]) KOTHListener.location.get(uuid))[1] != null);
	}

}
package fr.kro.koth.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import be.Vilevar.PvPFac.Commands.MainCommand;
import be.Vilevar.PvPFac.Perms.Command;
import be.Vilevar.PvPFac.Player.FPlayer;

public class CMDKOTH extends MainCommand {

	public CMDKOTH(FPlayer p, String[] args, Command cmd) {
		if (!p.hasPermission(cmd.getPermission())) {
			p.sendMessage(notPerm);
			return;
		}
		Player player = p.getPlayer();
		Inventory inv = Bukkit.createInventory(null, 27, "§6KOTH §4Menu");

		player.openInventory(inv);

		ItemStack info = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta infoM = info.getItemMeta();
		infoM.setDisplayName("§2Information");
		info.setItemMeta(infoM);

		ItemStack regle = new ItemStack(Material.BOOK);
		ItemMeta regleM = regle.getItemMeta();
		regleM.setDisplayName("§2Regles du jeu!");
		regle.setItemMeta(regleM);

		inv.setItem(10, info);
		inv.setItem(16, regle);
	}
}
package fr.kro.koth;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KOTHListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		ItemStack current = e.getCurrentItem();

		if (current == null)
			return;
		if (inv.getName().equals("§6KOTH §4Menu")) {

			if (current.getType() == Material.DIAMOND_SWORD) {
				player.sendMessage("§3 Il se trouve plus loin vers la WarZone à 15h00 tous les Mercredi!");
				e.setCancelled(true);
				player.closeInventory();
			}

			if (current.getType() == Material.BOOK) {
				player.sendMessage("§3 pas mtn");
				e.setCancelled(true);
				player.closeInventory();
			}

		}
	}
}
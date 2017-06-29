package fr.kro.koth;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.kro.koth.game.GameState;
import fr.kro.koth.region.Region;

public class KOTHListener implements Listener {

	public static HashMap<UUID, Location[]> location = new HashMap<UUID, Location[]>();

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		ItemStack current = e.getCurrentItem();

		if (current == null)
			return;
		if (inv.getName().equals("§6KOTH §4Menu")) {
			if (current.getType() == Material.DIAMOND_SWORD) {
				player.sendMessage("§3 Il se trouve plus loin vers la WarZone a 15h00 tous les Mercredi!");
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

	@EventHandler
	public void onLocation(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (p.getGameMode() == GameMode.CREATIVE && e.hasItem() && p.hasPermission("koth.regions.add")
				&& e.getItem() != null && e.getItem().getType() == Material.SHEARS && e.hasBlock()) {
			e.setCancelled(true);

			Location loc = e.getClickedBlock().getLocation(), l2;
			Location[] ls = location.containsKey(p.getUniqueId()) ? location.get(p.getUniqueId())
					: new Location[] { null, null };
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				p.sendMessage("§2La seconde Location est à : " + loc.getBlockX() + ";" + loc.getBlockY() + ";"
						+ loc.getBlockZ());
				l2 = ls[0];
				ls = new Location[] { l2, loc };
				location.put(p.getUniqueId(), ls);
			} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
				p.sendMessage("§2La premiere Location est à : " + loc.getBlockX() + ";" + loc.getBlockY() + ";"
						+ loc.getBlockZ());
				l2 = ls[1];
				ls = new Location[] { loc, l2 };
				location.put(p.getUniqueId(), ls);
			}
		}
	}

	// Timer

	public int tache;
	public int compte = 20;

	@SuppressWarnings("deprecation")
	public void timer() {
		tache = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(Main.class),
				new Runnable() {

					@Override
					public void run() {
						if (compte <= 0) {
							Bukkit.broadcastMessage("§2Le timer est §cterminé §2!");
							Bukkit.getServer().getScheduler().cancelTask(tache);
						} else {
							Bukkit.broadcastMessage("§2Le timer est à " + ChatColor.RED + compte + " §2secondes !");

							compte--;
						}

					}
				}, 20L, 20L);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if (Main.getCurrentState() == GameState.GAME) {
			if (Region.getRegion(loc) == null) {
				return;
			}
			if (Region.getRegion(loc).getName().equalsIgnoreCase("Bleu")) {
				p.sendMessage("Bravo l'equipe Bleu a perdu");
			}
			if (Region.getRegion(loc).getName().equalsIgnoreCase("Rouge")) {
				p.sendMessage("Bravo l'equipe Rouge a perdu");
			}
		}
	}
}
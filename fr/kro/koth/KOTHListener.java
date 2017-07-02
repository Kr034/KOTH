package fr.kro.koth;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import be.Vilevar.PvPFac.Faction.Faction;
import be.Vilevar.PvPFac.Player.FPlayer;
import fr.kro.koth.game.GameState;
import fr.kro.koth.region.Region;

public class KOTHListener implements Listener {

	public static HashMap<UUID, Location[]> location = new HashMap<UUID, Location[]>();
	public HashMap<Faction, Integer> map = new HashMap<>();

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

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {

		ItemStack gg = new ItemStack(Material.DIAMOND, 16);

		Player p = e.getPlayer();
		Location loc = e.getBlock().getLocation();
		Block b = e.getBlock();

		int points = p.getLevel();
				
		if (Main.getCurrentState() == GameState.GAME) {
			if (Region.getRegion(loc) == null) {
				return;
			}
			if (Region.getRegion(loc).getName().equalsIgnoreCase("Totem")) {
				if (b.getType() == Material.OBSIDIAN) {
					p.setLevel(1);
					Bukkit.broadcastMessage("§3[§2KOTH§3] §5--> §6Le premier §4block§6 du Totem a été detruit, par §9"
							+ p.getName().toString() + " §6de la faction §d"
							+ FPlayer.getFPlayer(p).getFaction().getName().toString() + "§6!");
					map.put(FPlayer.getFPlayer(p).getFaction(), points);
				}
				if (b.getType() == Material.BEACON) {
					p.setLevel(2);
					Bukkit.broadcastMessage("§3[§2KOTH§3] §5--> §6Le deuxième §4block§6 du Totem a été detruit, par §9"
							+ p.getName().toString() + " §6de la faction §d"
							+ FPlayer.getFPlayer(p).getFaction().getName().toString()
							+ "§6, Il reste plus qu'un §4Block§6!");
					map.put(FPlayer.getFPlayer(p).getFaction(), points);
				}
				if (map.containsValue(2)) {
					Bukkit.broadcastMessage("§3[§2KOTH§3] §5--> §6Bravo à §9" + p.getName().toString()
							+ "§6 la faction §d" + FPlayer.getFPlayer(p).getFaction().getName().toString()
							+ " §6qui à détruit le totem et qui gagne le KOTH d'aujourd'hui !");
					Main.setNextGameState();
					Bukkit.broadcastMessage(
							"§3[§2KOTH§3] §5--> §6Partie terminée, prochaine partie dans une semaine !");
					map.clear();
					Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(Main.class),
							new Runnable() {

								@Override
								public void run() {
									FPlayer pf = FPlayer.getFPlayer(p);
									pf.getPlayer().getInventory().addItem(gg);
								}
							}, 150L);
				}
			}
		}
	}
}
package me.kippy.spleef;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {
	private Logger logger = getLogger();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.LEFT_CLICK_AIR) {
			Block b = p.getTargetBlock(null, 200);
			int Distance = getConfig().getInt("MaxDistance");
			if((Math.sqrt((Math.pow((b.getLocation().getX()) - (p.getLocation().getX()), 2) + (Math.pow((b.getLocation().getY()) - (p.getLocation().getY() + 1), 2)) + (Math.pow((b.getLocation().getZ()) - (p.getLocation().getZ()), 2))))) <= Distance) {
				if(getConfig().getBoolean("ItemDrop") == true) {
					b.breakNaturally();
				}else{
					b.setType(Material.AIR);
				}
			}
		}else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Block b1 = e.getClickedBlock();
			int Distance1 = getConfig().getInt("MaxDistance");
			if((Math.sqrt((Math.pow((b1.getLocation().getX()) - (p.getLocation().getX()), 2) + (Math.pow((b1.getLocation().getY()) - (p.getLocation().getY() + 1), 2)) + (Math.pow((b1.getLocation().getZ()) - (p.getLocation().getZ()), 2))))) <= Distance1) {
				if(getConfig().getBoolean("ItemDrop") == true) {
					b1.breakNaturally();
				}else{
					b1.setType(Material.AIR);
				}
			}
		}
	}
	
	@Override
	public void onDisable() {
		this.logger.info("Ranged Spleef has been disabled!");
	}
	
	@Override
	public void onEnable() {
		this.logger.info("Ranged Spleef has been enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		saveDefaultConfig();
		reloadConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if(cl.equalsIgnoreCase("itemdrop")) {
			if(getConfig().getBoolean("ItemDrop") == true) {
				getConfig().set("ItemDrop", false);
			}else{
				getConfig().set("ItemDrop", true);
			}
		}
		return false;
	}

}
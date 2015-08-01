package me.kippy.spleef;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
			b.setType(Material.AIR);
		}else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Block b = e.getClickedBlock();
			b.setType(Material.AIR);
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
	}

}

package com.buildersrefuge.utilities.listeners;

import com.buildersrefuge.utilities.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    public Main plugin;

    public CommandListener(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onWSCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().toLowerCase().startsWith("ws")) {
            if(plugin.getServer().getPluginManager().getPlugin("WorldSystem") == null) {
                event.setMessage(event.getMessage().replace("ws", "speed walk"));
            }
        }
    }
}

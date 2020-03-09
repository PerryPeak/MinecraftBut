package me.perry1900.perryminecraftbut;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Events implements Listener {
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        if (PerryMinecraftBut.instance.GameModifiers.get("alwayshungry") != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().setFoodLevel(0);
                }
            }.runTaskLater(PerryMinecraftBut.instance, 1);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (PerryMinecraftBut.instance.GameModifiers.get("alwayshungry") != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().setFoodLevel(0);
                }
            }.runTaskLater(PerryMinecraftBut.instance, 1);
        }
    }
}


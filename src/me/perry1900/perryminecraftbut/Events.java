package me.perry1900.perryminecraftbut;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Events implements Listener {
    private PerryMinecraftBut main;

    public Events(PerryMinecraftBut plugin) {
        main = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (main.getVars().getGameModifiers().get("losehealth").isTogged()) {
//            new BukkitRunnable() {
//                @Override
//                public void run() {
            e.getPlayer().setMaxHealth(main.getVars().getMaxhealth());
            e.getPlayer().setHealth(main.getVars().getMaxhealth());
//                }
//            }.runTaskLater(main, 1);
        }
        if (main.getVars().getGameModifiers().get("alwayshungry").isTogged()) {
//            new BukkitRunnable() {
//                @Override
//                public void run() {
            e.getPlayer().setFoodLevel(0);
//                }
//            }.runTaskLater(main, 1);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (main.getVars().getGameModifiers().get("losehealth").isTogged()) {
//            new BukkitRunnable() {
//                @Override
//                public void run() {
            e.getPlayer().setMaxHealth(20);
            e.getPlayer().setHealth(20);
//                }
//            }.runTaskLater(main, 1);
        }
        if (main.getVars().getGameModifiers().get("alwayshungry").isTogged()) {
//            new BukkitRunnable() {
//                @Override
//                public void run() {
            e.getPlayer().setFoodLevel(20);
//                }
//            }.runTaskLater(main, 1);
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        if (main.getVars().getGameModifiers().get("alwayshungry").isTogged()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().setFoodLevel(0);
                }
            }.runTaskLater(main, 1);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (main.getVars().getGameModifiers().get("alwayshungry").isTogged()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().setFoodLevel(0);
                }
            }.runTaskLater(main, 1);
        }
    }
}


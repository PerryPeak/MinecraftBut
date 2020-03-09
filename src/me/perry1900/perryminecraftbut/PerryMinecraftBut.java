package me.perry1900.perryminecraftbut;

import com.sun.java.swing.plaf.motif.resources.motif_de;
import net.minecraft.server.v1_8_R3.ExceptionInvalidSyntax;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class PerryMinecraftBut extends JavaPlugin {
    HashMap<String, Object> GameModifiers = new HashMap<String, Object>();
    public static PerryMinecraftBut instance;
    private int maxhealth;


    @Override
    public void onEnable() {
        getLogger().info("Enabling Perry's Minecraft But");
        saveDefaultConfig();
        instance = JavaPlugin.getPlugin(PerryMinecraftBut.class);
        getServer().getPluginCommand("MinecraftBut").setExecutor(new MinecraftButCommand());
        getServer().getPluginManager().registerEvents(new ModifierGUI(), this);
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Perry's Minecraft But");
        instance = null;
    }

    public void loseHealthToggle() {
        if (GameModifiers.get("losehealth") == null) {
            reduceHealth();
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.setMaxHealth(getConfig().getInt("LoseHealth.StartingHealth"));
                player.setHealth(getConfig().getInt("LoseHealth.StartingHealth"));
            }
            maxhealth = getConfig().getInt("LoseHealth.StartingHealth") - 1;
        } else {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.setMaxHealth(20);
                player.setHealth(20);
            }
            getServer().broadcastMessage(ChatColor.GREEN + "The lose health gamemode has been turned off! You will no longer lose health and your health is back to 20!");
            BukkitTask loseHealthTask = (BukkitTask) GameModifiers.get("losehealth");
            loseHealthTask.cancel();
            GameModifiers.remove("losehealth");

        }

    }

    public void alwaysHungryToggle() {
        if (GameModifiers.get("alwayshungry") == null) {
            GameModifiers.put("alwayshungry", true);
            if (GameModifiers.get("losehealth") == null) {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.setFoodLevel(0);
                }
                PerryMinecraftBut.instance.getServer().broadcastMessage(ChatColor.DARK_RED + "Everyone on the server is now constantly hungry");
            }
        } else {
            GameModifiers.remove("alwayshungry");
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.setFoodLevel(20);
            }
            PerryMinecraftBut.instance.getServer().broadcastMessage(ChatColor.GREEN+"Everyone is not longer hungry and hunger level is back to 20");
        }
    }

    private void reduceHealth() {
        GameModifiers.put("losehealth", new BukkitRunnable() {
            @Override
            public void run() {
                if (GameModifiers.get("losehealth") != null) {
                    if (maxhealth >= 1) {
                        getServer().broadcastMessage(ChatColor.DARK_RED + "The maximum amount of health has been reduced to " + maxhealth);
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.setMaxHealth(maxhealth);
                        }
                        maxhealth = maxhealth - 1;
                    } else {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.setMaxHealth(20);
                            player.setHealth(20);
                            player.kickPlayer("You ran out of time");
                        }
                        GameModifiers.remove("losehealth");
                        this.cancel();
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, getConfig().getInt("LoseHealth.Frequency") * 20, getConfig().getInt("LoseHealth.Frequency") * 20 - 1)); //the -1 is because most private server's tps is going to go down by a little bit and this isn't a plugin for big servers
    }

}

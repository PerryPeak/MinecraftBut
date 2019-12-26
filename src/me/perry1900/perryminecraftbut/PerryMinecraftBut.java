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
    //private boolean losehealth;
    //HashMap<String, Boolean> GameModifiers = new HashMap<String, Boolean>();
    HashMap<String, BukkitTask> GameModifiers = new HashMap<String, BukkitTask>();
    //static PerryMinecraftBut maininstance=this;
    public static PerryMinecraftBut instance;
    private int maxhealth;


    @Override
    public void onEnable() {
        getLogger().info("Enabling Perry's Minecraft But");
        instance = JavaPlugin.getPlugin(PerryMinecraftBut.class);
        getServer().getPluginCommand("MinecraftBut").setExecutor(new MinecraftButCommand());
        getServer().getPluginManager().registerEvents(new ModifierGUI(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Perry's Minecraft But");
        instance = null;
    }

    //    public boolean getGameModifier(String modifiername) throws GameModifierException {
//        Boolean gamemodifier = GameModifiers.get(modifiername);
//        if (gamemodifier != null) {
//            return gamemodifier;
//        } else throw new GameModifierException("Invalid game modifier!");
//    }
//
//    public int getMaxhealth() {
//        return maxhealth;
//    }
//
//    public void setMaxhealth(int newmaxhealth) {
//        maxhealth = newmaxhealth;
//    }
    public void loseHealthToggle() {
//        if(GameModifiers.get("losehealth")!=true) {
//            GameModifiers.put("losehealth", true);
        if (GameModifiers.get("losehealth") == null) {
            reduceHealth();
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.setMaxHealth(40);
                player.setHealth(40);
            }
            maxhealth = 39;
        } else {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.setMaxHealth(20);
                player.setHealth(20);
            }
            GameModifiers.get("losehealth").cancel();
            GameModifiers.remove("losehealth");

        }

    }

    public void reduceHealth() {
        GameModifiers.put("losehealth", new BukkitRunnable() {
            @Override
            public void run() {
                if (GameModifiers.get("losehealth") != null) {
//                    int amplifier=(maxhealth-20)/4;
//                    maxhealth=maxhealth-4;
                    if (maxhealth >= 1) {
                        getServer().broadcastMessage(ChatColor.DARK_RED + "The maximum amount of health has been reduced to " + maxhealth);
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
//                        double playerhealth=player.getHealth();
//                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
//                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,60*20,amplifier,false,false));
//                        player.setHealth(playerhealth);
                            player.setMaxHealth(maxhealth);
                        }
                        maxhealth = maxhealth - 1;
                    } else {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            player.setMaxHealth(20);
                            player.setHealth(20);
                            player.kickPlayer("You ran out of time");
                        }
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 1 * 60 * 20, 1 * 60 * 20));
    }

//    changed because it wouldn't have worked with multiple modes if I kept it

//    public void everyMinute() {
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                if (GameModifiers.get("losehealth")) {
//                    getServer().broadcastMessage(ChatColor.DARK_RED + "The maximum amount of health has been reduced to " + maxhealth);
////                    int amplifier=(maxhealth-20)/4;
////                    maxhealth=maxhealth-4;
//                    if(maxhealth>1) {
//                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
////                        double playerhealth=player.getHealth();
////                        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
////                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,60*20,amplifier,false,false));
////                        player.setHealth(playerhealth);
//                            player.setMaxHealth(maxhealth);
//                        }
//                        maxhealth = maxhealth - 1;
//                    }
//                    else {
//                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
//                            player.kickPlayer("You ran out of time, nerd");
//                        }
//                    }
//                }
//                else {
//                    this.cancel();
//                }
//            }
//        }.runTaskTimer(this, 1 * 60 * 20, 1 * 60 * 20);
//    }

//    public HashMap<String, Boolean> getGameModifiers() {
//        return GameModifiers;
//    }
}

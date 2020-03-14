package me.perry1900.perryminecraftbut.gamemodifiers;

import me.perry1900.perryminecraftbut.PerryMinecraftBut;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class LoseHealth extends GameModifier {
    BukkitTask bukkitTask;
    PerryMinecraftBut main;

    public LoseHealth(PerryMinecraftBut mainInstance) {
        main = mainInstance;
        Map<String, GameModifier> gameModifiers = main.getVars().getGameModifiers();
        gameModifiers.put("losehealth", this);
        main.getVars().setGameModifiers(gameModifiers);
    }

    //    @Override
//    public void toggle() {
//        toggled = !toggled;
//        if (main.GameModifiers.get("losehealth") == null) {
//            reduceHealth();
    //            `for (Player player : getServer().getOnlinePlayers()) {
    //                player.setMaxHealth(main.getConfig().getInt("LoseHealth.StartingHealth"));
    //                player.setHealth(main.getConfig().getInt("LoseHealth.StartingHealth"));
    //            }`
//            main.setMaxhealth(main.getConfig().getInt("LoseHealth.StartingHealth") - 1);
//        } else {
//            for (Player player : getServer().getOnlinePlayers()) {
//                player.setMaxHealth(20);
//                player.setHealth(player.getMaxHealth());
//            }
//            getServer().broadcastMessage(ChatColor.GREEN + "The lose health gamemode has been turned off! You will no longer lose health and your health is back to 20!");
//            BukkitTask loseHealthTask = (BukkitTask) main.GameModifiers.get("losehealth");
//            loseHealthTask.cancel();
//            main.GameModifiers.remove("losehealth");
//
//        }
//    }
    @Override
    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            for (Player player : getServer().getOnlinePlayers()) {
                player.setMaxHealth(main.getConfig().getInt("LoseHealth.StartingHealth"));
            }
            main.getVars().setMaxhealth(main.getConfig().getInt("LoseHealth.StartingHealth") - 1);
            createBukkitTask();
            if (main.getConfig().getInt("LoseHealth.StartingHealth") > 20)
                getServer().broadcastMessage(ChatColor.DARK_RED + "The max amount of health has been increased but beware: it will decrease by 1 hp every " + main.getConfig().getInt("LoseHealth.Frequency") + " seconds!");
            else if (main.getConfig().getInt("LoseHealth.StartingHealth") < 20)
                getServer().broadcastMessage(ChatColor.DARK_RED + "The max amount of health has been decreased and will decrease by 1 hp every " + main.getConfig().getInt("LoseHealth.Frequency") + " seconds!");
            else
                getServer().broadcastMessage(ChatColor.DARK_RED + "The max amount of health will now decrease by 1 every " + main.getConfig().getInt("LoseHealth.Frequency") + " seconds!");
        } else {
            bukkitTask.cancel();
            for (Player player : getServer().getOnlinePlayers()) {
                player.setMaxHealth(20);
                player.setHealth(20);
            }
            getServer().broadcastMessage(ChatColor.GREEN + "The max amount of health is back to normal and will no longer decrease every " + main.getConfig().getInt("LoseHealth.Frequency") + " seconds!");
        }
    }

    private void createBukkitTask() {
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                int maxhealth = main.getVars().getMaxhealth();
                if (maxhealth >= 1) {
                    getServer().broadcastMessage(ChatColor.DARK_RED + "The maximum amount of health has been reduced to " + maxhealth);
                    for (Player player : getServer().getOnlinePlayers()) {
                        player.setMaxHealth(maxhealth);
                    }
                    maxhealth = maxhealth - 1;
                    main.getVars().setMaxhealth(maxhealth);
                } else {
                    for (Player player : getServer().getOnlinePlayers()) {
                        player.setMaxHealth(20);
                        player.setHealth(20);
                        player.kickPlayer("You ran out of time");
                    }

                }
            }

        }.runTaskTimer(main, main.getConfig().getInt("LoseHealth.Frequency") * 20, main.getConfig().getInt("LoseHealth.Frequency") * 20 - 1); //the -1 is because most private server's tps is going to go down by a little bit and this isn't a plugin for big servers
    }
}

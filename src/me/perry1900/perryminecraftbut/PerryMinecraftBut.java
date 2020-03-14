package me.perry1900.perryminecraftbut;

import me.perry1900.perryminecraftbut.gamemodifiers.AlwaysHungry;
import me.perry1900.perryminecraftbut.gamemodifiers.LoseHealth;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PerryMinecraftBut extends JavaPlugin {
    private Vars vars;

    @Override
    public void onEnable() {
        vars = new Vars(this);
        saveDefaultConfig();
        getServer().getPluginCommand("MinecraftBut").setExecutor(new MinecraftButCommand(this));
        getServer().getPluginManager().registerEvents(vars.getModifierGUI(), this);
        getServer().getPluginManager().registerEvents(new Events(this), this);
        createGameModifierObjects();
    }
    @Override
    public void onDisable() {
        if (vars.getGameModifiers().get("losehealth").isTogged()) {
            for (Player player : getServer().getOnlinePlayers()) {
                player.setMaxHealth(20);
                player.setHealth(20);
            }
        }
        if (vars.getGameModifiers().get("alwayshungry").isTogged()) {
            for (Player player : getServer().getOnlinePlayers()) {
                player.setFoodLevel(20);
            }
        }
    }

    public void createGameModifierObjects() {
        new LoseHealth(this);
        new AlwaysHungry(vars);
    }

    public Vars getVars() {
        return vars;
    }
}

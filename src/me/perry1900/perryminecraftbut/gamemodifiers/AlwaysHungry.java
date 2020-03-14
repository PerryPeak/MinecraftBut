package me.perry1900.perryminecraftbut.gamemodifiers;

import me.perry1900.perryminecraftbut.Vars;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class AlwaysHungry extends GameModifier {
    public AlwaysHungry(Vars vars) {
        Map<String, GameModifier> gameModifiers = vars.getGameModifiers();
        gameModifiers.put("alwayshungry", this);
        vars.setGameModifiers(gameModifiers);
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            for (Player player : getServer().getOnlinePlayers()) {
                player.setFoodLevel(0);
            }
            getServer().broadcastMessage(ChatColor.DARK_RED + "Everyone on the server is now constantly hungry");
        } else {
            for (Player player : getServer().getOnlinePlayers()) {
                player.setFoodLevel(20);
            }
            getServer().broadcastMessage(ChatColor.GREEN + "Everyone is no longer hungry");
        }
    }
}

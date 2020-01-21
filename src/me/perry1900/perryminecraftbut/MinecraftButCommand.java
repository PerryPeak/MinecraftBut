package me.perry1900.perryminecraftbut;

import net.minecraft.server.v1_8_R3.ServerGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinecraftButCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ModifierGUI modifiergui = new ModifierGUI();
                modifiergui.openInventory(player);
                return true;
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You must be a player to open the Minecraft But GUI!");
            }
        }
        if (args[0].equals("losehealth")) {
            PerryMinecraftBut.instance.getServer().broadcastMessage(ChatColor.DARK_RED + "The max amount of health has been increased but beware: it will now go down by 1 every "+PerryMinecraftBut.instance.getConfig().getInt("LoseHealth.Frequency")+" seconds!");
            PerryMinecraftBut.instance.loseHealthToggle();
            return true;
        }
        else if (args[0].equals("alwayshungery")) {
            PerryMinecraftBut.instance.alwaysHungryToggle();
            return true;
        }
        return false;
    }
}

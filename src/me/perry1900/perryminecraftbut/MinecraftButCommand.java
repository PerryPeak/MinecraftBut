package me.perry1900.perryminecraftbut;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinecraftButCommand implements CommandExecutor {
    private PerryMinecraftBut main;

    public MinecraftButCommand(PerryMinecraftBut plugin) {
        main = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                main.getVars().getModifierGUI().openInventory(player);
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You must be a player to open the Minecraft But GUI!");
            }
            return true;
        } else if (args.length == 1) {
            if ("losehealth".equalsIgnoreCase(args[0])) {
                main.getVars().getGameModifiers().get("losehealth").toggle();
                return true;
            } else if ("alwayshungry".equalsIgnoreCase(args[0])) {
                main.getVars().getGameModifiers().get("alwayshungry").toggle();
                return true;
            }
            System.out.println(args[0]);
        }
        return false;
    }


}

package me.perry1900.perryminecraftbut;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ModifierGUI implements InventoryHolder, Listener {
    private final Inventory modifiergui;
    private PerryMinecraftBut main;

    public ModifierGUI(PerryMinecraftBut plugin) {
        main = plugin;
        modifiergui = Bukkit.createInventory(null, 54, "Minecraft But...");
        initializeItems();
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return modifiergui;
    }

    private void initializeItems() {
        modifiergui.addItem(createGuiItem(Material.REDSTONE, "Lose Health", ChatColor.GREEN + "Everyone on the server starts with " + main.getConfig().getInt("LoseHealth.StartingHealth") + " hp", ChatColor.AQUA + "but the max health decreases every " + main.getConfig().getInt("LoseHealth.Frequency") + " seconds"));
        modifiergui.addItem(createGuiItem(Material.ROTTEN_FLESH, "Always Hungry", ChatColor.GREEN + "Makes everyone on the server constantly hungry"));

    }


    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        List<String> metalore = Arrays.asList(lore);
        //metalore.addAll(Arrays.asList(lore));
        meta.setLore(metalore);
        item.setItemMeta(meta);
        return item;
    }

    public void openInventory(Player player) {
        player.openInventory(modifiergui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() != null) {
            return;
        }
        if (e.getClick() == ClickType.NUMBER_KEY) {
            e.setCancelled(true);
        }
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("Lose Health")) {
            main.getVars().getGameModifiers().get("losehealth").toggle();
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("Always Hungry")) {
            main.getVars().getGameModifiers().get("alwayshungry").toggle();
        }
    }

}

package me.perry1900.perryminecraftbut;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.Bukkit;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ModifierGUI implements InventoryHolder, Listener {
    private final Inventory modifiergui;

    public ModifierGUI() {
        modifiergui = Bukkit.createInventory(null, 54, "Minecraft But...");
        initializeItems();
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return modifiergui;
    }

    public void initializeItems() {
        modifiergui.addItem(createGuiItem(Material.REDSTONE, "Lose Health", "§aEveryone on the server starts with "+ PerryMinecraftBut.instance.getConfig().getInt("LoseHealth.StartingHealth")+ " hp", "§bbut the max health decreases every "+PerryMinecraftBut.instance.getConfig().getInt("LoseHealth.Frequency")+" seconds"));
    }

    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metalore = new ArrayList<String>();

        metalore.addAll(Arrays.asList(lore));
        meta.setLore(metalore);
        item.setItemMeta(meta);
        return item;
    }

    public void openInventory(Player player) {
        player.openInventory(modifiergui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
//        System.out.println("test1");
        if (e.getInventory().getHolder() != null) {
            System.out.println("not MinecraftBut GUI");
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
//            System.out.println("test number");
            e.setCancelled(true);
        }
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
//            System.out.println("air/null");
            return;
        }

        if (e.getRawSlot() == 0) {
//            System.out.println("test2");
            player.chat("/minecraftbut losehealth");
        }
    }

}

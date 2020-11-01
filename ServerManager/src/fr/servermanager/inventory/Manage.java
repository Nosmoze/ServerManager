package fr.servermanager.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Manage {

    public static void openMenuMessage(int size, String name, Player p){
        Inventory inv = Bukkit.createInventory(null, size, name);
        for(MessageInv item : MessageInv.values()){
            inv.setItem(item.getSlot(), item.getItem());
        }
        p.openInventory(inv);
    }

    public static void openMenuServer(int size, String name, Player p){
        Inventory inv = Bukkit.createInventory(null, size, name);
        for(ServerInv item : ServerInv.values()){
            inv.setItem(item.getSlot(), item.getItem());
        }
        p.openInventory(inv);
    }
}

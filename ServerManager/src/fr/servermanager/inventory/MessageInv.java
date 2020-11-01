package fr.servermanager.inventory;

import fr.servermanager.config.Languages;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public enum MessageInv {

    WELCOME(11, new ItemStack(Material.LECTERN), Languages.getList("Manage.Welcome.Lore"), Languages.getString("Manage.Welcome.Name")),
    STOP(3, new ItemStack(Material.BARRIER), Languages.getList("Manage.StopServer.Lore"), Languages.getString("Manage.StopServer.Name")),
    WHITELIST(21, new ItemStack(Material.HOPPER), Languages.getList("Manage.WhiteList.Lore"), Languages.getString("Manage.WhiteList.Name")),
    MOTD(13, new ItemStack(Material.OAK_SIGN), Languages.getList("Manage.Motd.Lore"), Languages.getString("Manage.Motd.Name")),
    QUIT(5, new ItemStack(Material.CLOCK), Languages.getList("Manage.Quit.Lore"), Languages.getString("Manage.Quit.Name")),
    JOIN(23, new ItemStack(Material.COMPASS), Languages.getList("Manage.Join.Lore"), Languages.getString("Manage.Join.Name")),
    SLOT(15, new ItemStack(Material.NETHER_STAR), Languages.getList("Manage.Hover.Lore"), Languages.getString("Manage.Hover.Name")),
    DECO1(0, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), null, null),
    DECO2(8, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), null, null),
    DECO3(9, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), null, null),
    DECO4(17, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), null, null),
    DECO5(18, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), null, null),
    DECO6(26, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), null, null);

    private int slot;
    private ItemStack it;
    private List<String> lore;
    private String name;

    MessageInv(int slot, ItemStack it, List<String> lore, String name) {
        this.setSlot(slot);
        this.setItemStack(it);
        this.setLore(lore);
        this.setName(name);
    }

    public ItemStack getItem(){
        ItemStack is = it;
        ItemMeta im = it.getItemMeta();
        im.setLore(lore);
        im.setDisplayName(name);
        is.setItemMeta(im);
        return is;

    }

    public void setSlot(int slot){
        this.slot = slot;
    }

    public void setItemStack(ItemStack it){
        this.it = it;
    }

    public void setLore(List<String> lore){
        this.lore = lore;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getSlot(){
        return this.slot;
    }

    public ItemStack getIt(){
        return this.it;
    }

    public List<String> getLore(){
        return this.lore;
    }

    public String getName(){
        return this.name;
    }

}

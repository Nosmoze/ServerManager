package fr.servermanager.inventory;

import fr.servermanager.config.Languages;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public enum ServerInv {

    JOIN(3, new ItemStack(Material.COMPASS), Languages.getList("Manage.Server.Join.Lore"), ServerInv.getColorName("Manage.Server.Join.Status", "Manage.Server.Join.Name")),
    QUIT(21, new ItemStack(Material.CLOCK), Languages.getList("Manage.Server.Quit.Lore"), ServerInv.getColorName("Manage.Server.Quit.Status", "Manage.Server.Quit.Name")),
    WELCOME(11, new ItemStack(Material.LECTERN), Languages.getList("Manage.Server.Welcome.Lore"), ServerInv.getColorName("Manage.Server.Welcome.Status", "Manage.Server.Welcome.Name")),
    TNT(5, new ItemStack(Material.TNT), Languages.getList("Manage.Server.TNT.Lore"), ServerInv.getColorName("Manage.Server.TNT.Status", "Manage.Server.TNT.Name")),
    SQL(13, new ItemStack(Material.FLOWER_BANNER_PATTERN), Languages.getList("Manage.Server.SQL.Lore"), ServerInv.getColorName("Manage.Server.SQL.Status", "Manage.Server.SQL.Name")),
    PACK(15, new ItemStack(Material.ITEM_FRAME), Languages.getList("Manage.Server.Pack.Lore"), ServerInv.getColorName("Manage.Server.Pack.Status", "Manage.Server.Pack.Name")),
    LANG(23, new ItemStack(Material.BOOK), Languages.getList("Manage.Server.Language.Lore"), Languages.getString("Manage.Server.Language.Name")),
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

    ServerInv(int slot, ItemStack it, List<String> lore, String name) {
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

    private static String getColorName(String Boolean, String path){
        if(Languages.getBoolean(Boolean)){
            return "§a" + Languages.getString(path);
        }else {
            return "§4" + Languages.getString(path);
        }
    }

}

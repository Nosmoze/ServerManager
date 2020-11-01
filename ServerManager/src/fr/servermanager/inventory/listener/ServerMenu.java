package fr.servermanager.inventory.listener;

import fr.servermanager.config.FilesManager;
import fr.servermanager.config.Languages;
import fr.servermanager.inventory.ServerInv;
import fr.servermanager.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ServerMenu implements Listener {

    private final Languages lang;
    private final FilesManager fm;
    private final Messages msg;
    public ServerMenu(Languages lang, FilesManager fm, Messages msg){
        this.msg = msg;
        this.lang =lang;
        this.fm = fm;
    }

    @EventHandler
    public void onInteract(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        if(e.getView().getTitle().equalsIgnoreCase("Manage Server")){
            if(it == null) return;
            e.setCancelled(true);
            if(it.equals(ServerInv.JOIN.getItem())){
                if(p.hasPermission("servermanager.joinserver")) {
                    if (e.getClick().isRightClick()) {
                        if (isBoolean("Manage.Server.Join.Status")) {
                            setJoin(false);
                            ServerInv.JOIN.setName("§4" + Languages.getString("Manage.Server.Join.Name"));
                            p.closeInventory();
                        } else {
                            setJoin(true);
                            ServerInv.JOIN.setName("§a" + Languages.getString("Manage.Server.Join.Name"));
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
            if(it.equals(ServerInv.QUIT.getItem())){
                if(p.hasPermission("servermanager.quitserver")) {
                    if (e.getClick().isRightClick()) {
                        if (isBoolean("Manage.Server.Quit.Status")) {
                            setQuit(false);
                            ServerInv.QUIT.setName("§4" + Languages.getString("Manage.Server.Quit.Name"));
                            p.closeInventory();
                        } else {
                            setQuit(true);
                            ServerInv.QUIT.setName("§a" + Languages.getString("Manage.Server.Quit.Name"));
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
            if(it.equals(ServerInv.WELCOME.getItem())){
                if(p.hasPermission("servermanager.welcomeserver")) {
                    if (e.getClick().isRightClick()) {
                        if (isBoolean("Manage.Server.Welcome.Status")) {
                            setWelcome(false);
                            ServerInv.WELCOME.setName("§4" + Languages.getString("Manage.Server.Welcome.Name"));
                            p.closeInventory();
                        } else {
                            setWelcome(true);
                            ServerInv.WELCOME.setName("§a" + Languages.getString("Manage.Server.Welcome.Name"));
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
            if(it.equals(ServerInv.TNT.getItem())){
                if(p.hasPermission("servermanager.tntserver")){
                    if(e.getClick().isRightClick()){
                        if(isBoolean("Manage.Server.TNT.Status")){
                            setTNT(false);
                            ServerInv.TNT.setName("§4" + Languages.getString("Manage.Server.TNT.Name"));
                            p.closeInventory();
                        }else{
                            setTNT(true);
                            ServerInv.TNT.setName("§a" + Languages.getString("Manage.Server.TNT.Name"));
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
            if(it.equals(ServerInv.SQL.getItem())){
                if(p.hasPermission("servermanager.sqlserver")) {
                    if (e.getClick().isRightClick()) {
                        if (fm.getConfig("config.yml").getBoolean("SqlConnection")) {
                            setSQL(false);
                            ServerInv.SQL.setName("§4" + Languages.getString("Manage.Server.SQL.Name"));
                            p.closeInventory();
                        } else {
                            setSQL(true);
                            ServerInv.SQL.setName("§a" + Languages.getString("Manage.Server.SQL.Name"));
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
            if(it.equals(ServerInv.PACK.getItem())){
                if(p.hasPermission("servermanager.packserver")) {
                    if (e.getClick().isRightClick()) {
                        if (isBoolean("Manage.Server.Pack.Status")) {
                            setPack(false);
                            ServerInv.PACK.setName("§4" + Languages.getString("Manage.Server.Pack.Name"));
                            p.closeInventory();
                        } else {
                            setPack(true);
                            ServerInv.PACK.setName("§a" + Languages.getString("Manage.Server.Pack.Name"));
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
            if(it.equals(ServerInv.LANG.getItem())){
                if(p.hasPermission("servermanager.languageserver")) {
                    if (e.getClick().isRightClick()) {
                        if (fm.getConfig("config.yml").getString("Language").equalsIgnoreCase("Fr")) {
                            fm.getConfig("config.yml").set("Language", "En");
                            fm.saveConfig("config.yml");
                            p.closeInventory();
                        } else {
                            fm.getConfig("config.yml").set("Language", "Fr");
                            fm.saveConfig("config.yml");
                            p.closeInventory();
                        }
                    }
                }else{
                    p.closeInventory();
                    p.sendMessage(msg.ErrorPerm);
                }
            }
        }
    }

    public static boolean isBoolean(String path) {
        return Languages.getBoolean(path);
    }

    private void setJoin(boolean join) {
        lang.setBoolean("Manage.Server.Join.Status", join);
    }

    private void setQuit(boolean quit){
        lang.setBoolean("Manage.Server.Quit.Status", quit);
    }

    private void setWelcome(boolean welcome){
        lang.setBoolean("Manage.Server.Welcome.Status", welcome);
    }

    private void setTNT(boolean tnt){
        lang.setBoolean("Manage.Server.TNT.Status", tnt);
    }

    private void setSQL(boolean sql){
        fm.getConfig("config.yml").set("SqlConnection", sql);
        fm.saveConfig("config.yml");
    }

    private void setPack(boolean pack){
        lang.setBoolean("Manage.Server.Pack.Status", pack);
    }

}

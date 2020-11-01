package fr.servermanager.inventory.listener;

import fr.servermanager.inventory.MessageInv;
import fr.servermanager.utils.ManagerMessage;
import fr.servermanager.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class MessagesMenu implements Listener {

    public static boolean setJoin = false;
    public static boolean setQuit = false;
    public static boolean setWelcome = false;
    public static boolean setMotd = false;
    public static boolean setWhiteList = false;
    public static boolean setStopServer = false;
    public static boolean setHover = false;

    public ManagerMessage managerMessage;
    public Messages msg;
    public MessagesMenu(ManagerMessage managerMessage, Messages msg){
        this.managerMessage = managerMessage;
        this.msg = msg;
    }

    public static boolean isSetJoin(){
        return setJoin;
    }
    public static boolean isSetQuit(){
        return setQuit;
    }
    public static boolean isSetWelcome(){
        return setWelcome;
    }
    public static boolean isSetMotd(){
        return setMotd;
    }
    public static boolean isSetWhiteList(){
        return setWhiteList;
    }
    public static boolean isSetStopServer(){
        return setStopServer;
    }
    public static boolean isSetHover(){
        return setHover;
    }
    public static void setFalseSetJoin(){
        setJoin = false;
    }
    public static void setFalseSetWhiteList(){
        setWhiteList = false;
    }
    public static void setFalseSetMotd(){
        setMotd = false;
    }
    public static void setFalseSetStopServer(){
        setStopServer = false;
    }
    public static void setFalseSetHover(){
        setHover = false;
    }
    public static void setFalseSetQuit(){
        setQuit = false;
    }
    public static void setFalseSetWelcome(){
        setWelcome = false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        //player will change welcome message
        if (isSetWelcome()) {
            managerMessage.setWelcome_msg(e.getMessage());
            setWelcome = false;
            e.setCancelled(true);
        }
        //player will change restart message
        if (isSetStopServer()) {
            managerMessage.setStop_msg(e.getMessage());
            setJoin = false;
            e.setCancelled(true);
        }
        //player will change WhiteList message
        if (isSetWhiteList()) {
            managerMessage.setWhiteList_msg(e.getMessage());
            setWhiteList = false;
            e.setCancelled(true);
        }
        //player will MOTD
        if (isSetMotd()) {
            managerMessage.setMotd_msg(e.getMessage());
            setWhiteList = false;
            e.setCancelled(true);
        }
        //player will change disconnect message
        if (isSetQuit()) {
            managerMessage.setQuit_msg(e.getMessage());
            setQuit = false;
            e.setCancelled(true);
        }
        //player will change connect message
        if (isSetJoin()) {
            managerMessage.setJoin_msg(e.getMessage());
            setJoin = false;
            e.setCancelled(true);
        }
        //player will change HoverText slot message
        if (isSetHover()) {
            managerMessage.setHover_msg(e.getMessage());
            setHover = false;
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();

        //verifier le menu
        if(e.getView().getTitle().equalsIgnoreCase("Manage Messages")){
            if(it == null) return;
            e.setCancelled(true);
            if(!isSetHover() && !isSetQuit() && !isSetMotd() && !isSetWhiteList() && !isSetStopServer() && !isSetWelcome() && !isSetJoin()) {
                //if item is Welcome
                if (it.equals(MessageInv.WELCOME.getItem())) {
                    if(p.hasPermission("servermanager.welcomemessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getWelcome_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setWelcome = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
                //if item is Stop
                if (it.equals(MessageInv.STOP.getItem())) {
                    if(p.hasPermission("servermanager.stopmessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getStop_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setStopServer = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
                //if item is WhiteList
                if (it.equals(MessageInv.WHITELIST.getItem())) {
                    if(p.hasPermission("servermanager.whitelistmessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getWhiteList_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setWhiteList = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
                //if item is Motd
                if (it.equals(MessageInv.MOTD.getItem())) {
                    if(p.hasPermission("servermanager.motdmessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getMotd_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setMotd = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
                //if item is Quit
                if (it.equals(MessageInv.QUIT.getItem())) {
                    if(p.hasPermission("servermanager.quitmessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getQuit_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setQuit = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
                //if item is Join
                if (it.equals(MessageInv.JOIN.getItem())) {
                    if(p.hasPermission("servermanager.joinmessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getJoin_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setJoin = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
                //if item is Slot
                if (it.equals(MessageInv.SLOT.getItem())) {
                    if(p.hasPermission("servermanager.slotmessage")) {
                        if (e.getClick().isRightClick()) {
                            p.closeInventory();
                            p.sendMessage(managerMessage.getHover_msg());
                        }
                        if (e.getClick().isLeftClick()) {
                            p.closeInventory();
                            p.sendMessage(msg.Instructions);
                            setHover = true;
                        }
                    }else{
                        p.closeInventory();
                        p.sendMessage(msg.ErrorPerm);
                    }
                }
            }else{
                p.closeInventory();
                p.sendMessage(msg.ErrorInstructionProgress);
            }
        }
    }
}

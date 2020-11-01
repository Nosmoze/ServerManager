package fr.servermanager.inventory.listener;

import fr.servermanager.config.Languages;
import fr.servermanager.utils.ManagerMessage;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.Arrays;

public class Listeners implements Listener {

    public ManagerMessage managerMessage;
    public Listeners(ManagerMessage managerMessage){
        this.managerMessage = managerMessage;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!e.getPlayer().hasPlayedBefore()){
            if(ServerMenu.isBoolean("Manage.Server.Welcome.Status")) {
                e.setJoinMessage(managerMessage.getWelcome_msg().replaceAll("%Player%", e.getPlayer().getDisplayName()));
            }
        }else{
            if(ServerMenu.isBoolean("Manage.Server.Join.Status")) {
                e.setJoinMessage(managerMessage.getJoin_msg().replaceAll("%Player%", e.getPlayer().getDisplayName()));
            }
            if(ServerMenu.isBoolean("Manage.Server.Pack.Status")){
                e.getPlayer().setResourcePack(Languages.getString("Manage.Server.Pack.Value"));
            }
        }
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e){
        e.setMotd(formatMotd(managerMessage.getMotd_msg()));

    }

    private String formatMotd(String motd){
        return ChatColor.translateAlternateColorCodes('&', motd).replace("/n", "\n");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(ServerMenu.isBoolean("Manage.Server.Quit.Status")) {
            e.setQuitMessage(managerMessage.getQuit_msg().replaceAll("%Player%", e.getPlayer().getDisplayName()));
        }
    }

    @EventHandler
    public void onExplode(EntityDamageByEntityEvent e){
        if(ServerMenu.isBoolean("Manage.Server.TNT.Status")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        if(ServerMenu.isBoolean("Manage.Server.TNT.Status")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onTab(TabCompleteEvent e){
        if(e.getBuffer().trim().equalsIgnoreCase("/manager")){
            e.getCompletions().addAll(Arrays.asList(new String[] {"messages", "server"}));
        }
    }
}

package fr.servermanager.utils;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import fr.servermanager.config.Languages;
import fr.servermanager.main.ServerManager;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class HoverTextClass {

    private HoverTextClass(){
        throw new IllegalStateException("Utility class");
    }

    public static void activateHoverText(WrappedServerPing ping, ServerManager main){
        List<WrappedGameProfile> players = new ArrayList<WrappedGameProfile>();
        for(String string : getHoverMsg()){
            players.add(new WrappedGameProfile(UUID.randomUUID(), string));
        }
        ping.setPlayers(players);
    }

    private static List<String> getHoverMsg(){
        String Hover = Languages.getString("Manage.Hover.Value").replaceAll("&", "§").replaceAll("%slot%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));
        return Arrays.asList(Hover.split("/n"));
    }
}

package fr.servermanager.main;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import fr.servermanager.utils.HoverTextClass;
import org.bukkit.plugin.Plugin;

public class ProtocolLibImplement {

    private static ServerManager main;

    public ProtocolLibImplement(ServerManager plugin) {
        setPlugin(plugin);
    }

    private static void setPlugin(ServerManager plugin) {
        main = plugin;
    }

    public void initializeProtocolLib(){

        ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new PacketAdapter(PacketAdapter.params((Plugin)main, new PacketType[] { PacketType.Status.Server.SERVER_INFO }).optionAsync()){
            public void onPacketSending(PacketEvent e){
                WrappedServerPing ping = (WrappedServerPing) e.getPacket().getServerPings().read(0);
                HoverTextClass.activateHoverText(ping, ProtocolLibImplement.main);
            }
        });
    }
}

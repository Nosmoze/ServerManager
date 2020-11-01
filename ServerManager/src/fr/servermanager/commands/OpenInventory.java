package fr.servermanager.commands;

import fr.servermanager.inventory.Manage;
import fr.servermanager.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenInventory implements CommandExecutor {

    public Messages messages;
    public OpenInventory(Messages messages){
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("manager")){
                if(args.length == 0){
                    p.sendMessage("§7This server uses ServerManager version§8 " + Bukkit.getServer().getPluginManager().getPlugin("ServerManager").getDescription().getVersion());
                    p.sendMessage("§7This plugin was created by §8Nosmoze");
                    return true;
                }if(args.length == 1){
                    //display message menu
                    if(args[0].equalsIgnoreCase("messages")){
                        if(!p.hasPermission("servermanager.messages")) {
                            p.sendMessage(messages.ErrorPerm);
                            return false;
                        }else {
                            Manage.openMenuMessage(27, "Manage Messages", p);
                            return true;
                        }
                        //display server menu
                    }if(args[0].equalsIgnoreCase("server")){
                        if(!p.hasPermission("servermanager.server")) {
                            p.sendMessage(messages.ErrorPerm);
                            return false;
                        }else {
                            Manage.openMenuServer(27, "Manage Server", p);
                            return true;
                        }
                    }else{
                        p.sendMessage(messages.ErrorGlobal);
                        return false;
                    }
                }else{
                    p.sendMessage(messages.ErrorGlobal);
                    return false;
                }
            }
        }else{
            Bukkit.getServer().getConsoleSender().sendMessage(messages.ErrorConsole);
            return false;
        }
        return false;
    }
}

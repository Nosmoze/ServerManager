package fr.servermanager.commands;

import fr.servermanager.inventory.listener.MessagesMenu;
import fr.servermanager.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cancel implements CommandExecutor {

    public Messages messages;
    public Cancel(Messages messages){
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("Cancel")){
                if(p.hasPermission("servermanager.messages")){
                    if(MessagesMenu.isSetJoin()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetJoin();
                        return true;
                    }
                    if(MessagesMenu.isSetWelcome()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetWelcome();
                        return true;
                    }
                    if(MessagesMenu.isSetStopServer()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetStopServer();
                        return true;
                    }
                    if(MessagesMenu.isSetWhiteList()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetWhiteList();
                        return true;
                    }
                    if(MessagesMenu.isSetMotd()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetMotd();
                        return true;
                    }
                    if(MessagesMenu.isSetQuit()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetQuit();
                        return true;
                    }
                    if(MessagesMenu.isSetHover()){
                        p.sendMessage(messages.CancelInstructions);
                        MessagesMenu.setFalseSetHover();
                        return true;
                    }
                    p.sendMessage(messages.ErrorInstructions);
                    return false;
                }else{
                    p.sendMessage(messages.ErrorPerm);
                }
            }
        }else{
            sender.sendMessage(messages.ErrorConsole);
            return false;
        }
        return false;
    }
}

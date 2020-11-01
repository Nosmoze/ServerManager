package fr.servermanager.utils;

import fr.servermanager.config.Languages;
import org.bukkit.ChatColor;

public class Messages {

    public String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "ServerManager" + ChatColor.DARK_GRAY + "] ";

    public String prefixConsole = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "ServerManager" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY;

    public String ErrorGlobal = Languages.getString("Messages.ErrorGlobal");

    public String ErrorConsole = prefixConsole + Languages.getString("Messages.ErrorConsole");

    public String ErrorPerm = Languages.getString("Messages.ErrorPerm");

    public String Instructions = prefix + Languages.getString("Messages.Instructions");

    public String CancelInstructions = prefix + Languages.getString("Messages.CancelInstructions");

    public String ErrorInstructions = Languages.getString("Messages.ErrorInstructions");

    public String ErrorInstructionProgress = Languages.getString("Messages.ErrorInstructionProgress");

}

package fr.servermanager.main;

import fr.servermanager.commands.Cancel;
import fr.servermanager.commands.OpenInventory;
import fr.servermanager.config.FilesManager;
import fr.servermanager.config.Languages;
import fr.servermanager.inventory.listener.Listeners;
import fr.servermanager.inventory.listener.MessagesMenu;
import fr.servermanager.inventory.listener.ServerMenu;
import fr.servermanager.sql.DbAccess;
import fr.servermanager.sql.DbConnection;
import fr.servermanager.utils.ManagerMessage;
import fr.servermanager.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerManager extends JavaPlugin {

    public FilesManager Fm;
    public DbAccess dbAccess;
    public Languages languages;
    public ManagerMessage ManagerMsg;
    public DbConnection dbConnection;
    public Messages msg;
    public MessagesMenu messagesMenu;
    public ProtocolLibImplement protocolLibImplement;
    public ServerMenu serverMenu;

    @Override
    public void onEnable() {
        this.Fm = new FilesManager(this);
        this.dbAccess = new DbAccess(Fm);
        this.languages = new Languages(this);
        this.serverMenu = new ServerMenu(languages, Fm, msg);
        this.dbConnection = new DbConnection("jdbc:mysql://", dbAccess.getHost(), dbAccess.getDatabase(), dbAccess.getUser(), dbAccess.getPass());
        this.ManagerMsg = new ManagerMessage(dbAccess, dbConnection, languages, this);
        this.msg = new Messages();
        this.messagesMenu = new MessagesMenu(ManagerMsg, msg);


        if(dbAccess.AuthorizeConnection()) {
            InitializeTableMessages();
        }
        ManagerMsg.InitializeMessages();

        if(Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib") == null){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "ServerManager requires ProtocolLib");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
        this.protocolLibImplement = new ProtocolLibImplement(this);
        protocolLibImplement.initializeProtocolLib();

        getCommand("manager").setExecutor(new OpenInventory(msg));
        getCommand("cancel").setExecutor(new Cancel(msg));

        getServer().getPluginManager().registerEvents(new MessagesMenu(ManagerMsg, msg), this);
        getServer().getPluginManager().registerEvents(new Listeners(ManagerMsg), this);
        getServer().getPluginManager().registerEvents(new ServerMenu(languages, Fm, msg), this);

        getServer().getConsoleSender().sendMessage(msg.prefixConsole + " This plugin was developed by" + ChatColor.DARK_GRAY + ChatColor.BOLD + ChatColor.ITALIC + " Nosmoze");

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(msg.prefixConsole + " The plugin is disabled");
    }

    private void InitializeTableMessages(){
        dbConnection.Connected();
        try {
            //CreateTable is not exists
            PreparedStatement step1 = dbConnection.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS messages(Stop_msg VARCHAR(255), Quit_msg VARCHAR(255), Welcome_msg VARCHAR(255), Motd_msg VARCHAR(255), Hover_msg VARCHAR(255), WhiteList_msg VARCHAR(255), Join_msg VARCHAR(255)) ENGINE=INNODB DEFAULT CHARSET=utf8");
            step1.execute();

            //Check if table is empty
            PreparedStatement step2 = dbConnection.getConnection().prepareStatement("SELECT Stop_msg, Quit_msg, Welcome_msg, Motd_msg, Hover_msg, WhiteList_msg, Join_msg FROM messages");

            ResultSet rs = step2.executeQuery();
            int number = 0;
            while (rs.next()){
                number++;
            }

            //If table is empty, initialize defaults messages
            if(number == 0){
                PreparedStatement step3 = dbConnection.getConnection().prepareStatement("INSERT INTO messages(Stop_msg, Quit_msg, Welcome_msg, Motd_msg, Hover_msg, WhiteList_msg, Join_msg) VALUES (?,?,?,?,?,?,?)");
                step3.setString(1, languages.getString("Manage.StopServer.Value"));
                step3.setString(2, languages.getString("Manage.Quit.Value"));
                step3.setString(3, languages.getString("Manage.Welcome.Value"));
                step3.setString(4, languages.getString("Manage.Motd.Value"));
                step3.setString(5, languages.getString("Manage.Hover.Value"));
                step3.setString(6, languages.getString("Manage.WhiteList.Value"));
                step3.setString(7, languages.getString("Manage.Join.Value"));
                step3.execute();
                step3.close();
            }

            step1.close();
            step2.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

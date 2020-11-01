package fr.servermanager.utils;

import fr.servermanager.config.Languages;
import fr.servermanager.main.ServerManager;
import fr.servermanager.sql.DbAccess;
import fr.servermanager.sql.DbConnection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerMessage {

    private static String Welcome_msg, Stop_msg, Quit_msg, Motd_msg, Hover_msg, WhiteList_msg, Join_msg;
    public DbAccess dbAccess;
    public DbConnection dbConnection;
    public Languages lang;
    public ServerManager plugin;

    public ManagerMessage(DbAccess dbAccess, DbConnection dbConnection, Languages lang, ServerManager plugin){
        this.dbAccess = dbAccess;
        this.dbConnection = dbConnection;
        this.lang = lang;
        this.plugin = plugin;
    }

    public String getWelcome_msg() {
        return Welcome_msg;
    }

    public String getStop_msg() {
        return Stop_msg;
    }

    public String getQuit_msg() {
        return Quit_msg;
    }

    public String getMotd_msg() {
        return Motd_msg;
    }

    public String getHover_msg() {
        return Hover_msg;
    }

    public String getWhiteList_msg() {
        return WhiteList_msg;
    }

    public String getJoin_msg() {
        return Join_msg;
    }

    public void InitializeMessages(){
        if(dbAccess.AuthorizeConnection()){
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("SELECT Stop_msg, Quit_msg, Welcome_msg, Motd_msg, Hover_msg, WhiteList_msg, Join_msg FROM messages");
                ResultSet rs = q.executeQuery();

                while (rs.next()){
                    Welcome_msg = rs.getString("Welcome_msg");
                    Stop_msg = rs.getString("Stop_msg");
                    Quit_msg = rs.getString("Quit_msg");
                    Motd_msg = rs.getString("Motd_msg");
                    Hover_msg = rs.getString("Hover_msg");
                    WhiteList_msg = rs.getString("WhiteList_msg");
                    Join_msg = rs.getString("Join_msg");
                    initializeStopMessage();
                    initializeWhiteListMessage();
                }
                q.close();
                rs.close();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.printStackTrace();
            }
        }else{
            Welcome_msg = Languages.getString("Manage.Welcome.Value");
            Stop_msg = Languages.getString("Manage.StopServer.Value");
            Quit_msg = Languages.getString("Manage.Quit.Value");
            Motd_msg = Languages.getString("Manage.Motd.Value");
            Hover_msg = Languages.getString("Manage.Hover.Value");
            WhiteList_msg = Languages.getString("Manage.WhiteList.Value");
            Join_msg = Languages.getString("Manage.Join.Value");
            initializeStopMessage();
            initializeWhiteListMessage();


        }
    }

    public void setWelcome_msg(String newWelcome){
        if(dbAccess.AuthorizeConnection()){
            Welcome_msg = newWelcome;
            lang.setString("Manage.Welcome.Value", newWelcome);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET Welcome_msg=?");
                q.setString(1, newWelcome);
                q.executeUpdate();
                dbConnection.Close();
            } catch (SQLException e) {
                dbConnection.Close();
                e.getCause();
            }
        }else{
            Welcome_msg = newWelcome;
            lang.setString("Manage.Welcome.Value", newWelcome);
        }
    }

    private void initializeStopMessage(){
        File spigot = new File("spigot.yml");

        YamlConfiguration deConfigSpigot = YamlConfiguration.loadConfiguration(spigot);
        deConfigSpigot.set("messages.restart", Stop_msg);
        try {
            deConfigSpigot.save(spigot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStop_msg(String newStop){
        File spigot = new File("spigot.yml");

        YamlConfiguration deConfigSpigot = YamlConfiguration.loadConfiguration(spigot);
        deConfigSpigot.set("messages.restart", newStop);
        try {
            deConfigSpigot.save(spigot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dbAccess.AuthorizeConnection()){
            Stop_msg = newStop;
            lang.setString("Manage.StopServer.Value", newStop);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET Stop_msg=?");
                q.setString(1, newStop);
                q.executeUpdate();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.getCause();
            }
        }else{
            Stop_msg = newStop;
            lang.setString("Manage.StopServer.Value", newStop);
        }
    }

    private void initializeWhiteListMessage(){
        File spigot = new File("spigot.yml");

        YamlConfiguration deConfigSpigot = YamlConfiguration.loadConfiguration(spigot);
        deConfigSpigot.set("messages.whitelist", WhiteList_msg);
        try {
            deConfigSpigot.save(spigot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWhiteList_msg(String newWhiteList){
        File spigot = new File("spigot.yml");

        YamlConfiguration deConfigSpigot = YamlConfiguration.loadConfiguration(spigot);
        deConfigSpigot.set("messages.whitelist", newWhiteList);
        try {
            deConfigSpigot.save(spigot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(dbAccess.AuthorizeConnection()){
            WhiteList_msg = newWhiteList;
            lang.setString("Manage.WhiteList.Value", newWhiteList);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET WhiteList_msg=?");
                q.setString(1, newWhiteList);
                q.executeUpdate();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.getCause();
            }
        }else{
            WhiteList_msg = newWhiteList;
            lang.setString("Manage.WhiteList.Value", newWhiteList);
        }
    }

    public void setMotd_msg(String newMotd){
        if(dbAccess.AuthorizeConnection()){
            Motd_msg = newMotd;
            lang.setString("Manage.Motd.Value", newMotd);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET Motd_msg=?");
                q.setString(1, newMotd);
                q.executeUpdate();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.getCause();
            }
        }else{
            Motd_msg = newMotd;
            lang.setString("Manage.Motd.Value", newMotd);
        }
    }

    public void setQuit_msg(String newQuit){
        if(dbAccess.AuthorizeConnection()){
            Quit_msg = newQuit;
            lang.setString("Manage.Quit.Value", newQuit);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET Quit_msg=?");
                q.setString(1, newQuit);
                q.executeUpdate();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.getCause();
            }
        }else{
            Quit_msg = newQuit;
            lang.setString("Manage.Quit.Value", newQuit);
        }
    }

    public void setJoin_msg(String newJoin){
        if(dbAccess.AuthorizeConnection()){
            Join_msg = newJoin;
            lang.setString("Manage.Join.Value", newJoin);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET Join_msg=?");
                q.setString(1, newJoin);
                q.executeUpdate();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.getCause();
            }
        }else{
            Join_msg = newJoin;
            lang.setString("Manage.Join.Value", newJoin);
        }
    }

    public void setHover_msg(String newHover){
        if(dbAccess.AuthorizeConnection()){
            Hover_msg = newHover;
            lang.setString("Manage.Hover.Value", newHover);
            dbConnection.Connected();
            try {
                PreparedStatement q = dbConnection.getConnection().prepareStatement("UPDATE messages SET Hover_msg=?");
                q.setString(1, newHover);
                q.executeUpdate();
                dbConnection.Close();
            }catch (SQLException e){
                dbConnection.Close();
                e.getCause();
            }
        }else{
            Hover_msg = newHover;
            lang.setString("Manage.Hover.Value", newHover);
        }
    }
}

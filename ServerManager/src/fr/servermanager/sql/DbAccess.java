package fr.servermanager.sql;

import fr.servermanager.config.FilesManager;

public class DbAccess {
    private FilesManager data;

    public DbAccess(FilesManager data){
        this.data = data;
    }

    public String getUser(){
        return data.getConfig("config.yml").getString("User");
    }

    public String getPass(){
       return data.getConfig("config.yml").getString("Password");
    }

    public String getHost(){
        return data.getConfig("config.yml").getString("Host");
    }

    public String getDatabase(){
        return data.getConfig("config.yml").getString("Database");
    }

    public Boolean AuthorizeConnection(){
        return data.getConfig("config.yml").getBoolean("SqlConnection");
    }
}

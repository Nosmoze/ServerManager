package fr.servermanager.config;

import fr.servermanager.main.ServerManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FilesManager {

    private ServerManager plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public FilesManager(ServerManager plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig(String filename){
        if(this.configFile == null){
            this.configFile = new File(this.plugin.getDataFolder(), filename);
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource(filename);
        if(defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(String filename){
        if(this.dataConfig == null){
            reloadConfig(filename);
        }
        return this.dataConfig;
    }

    public void saveConfig(String filename){
        if(this.dataConfig == null || this.configFile == null){
            return;
        }

        try {
            this.getConfig(filename).save(this.configFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig(){
        if(this.configFile == null){
            this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        }
        if(!this.configFile.exists()){
            this.plugin.saveResource("config.yml", false);
        }
    }
}

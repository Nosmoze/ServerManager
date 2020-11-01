package fr.servermanager.config;

import fr.servermanager.main.ServerManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Languages {

    private static ServerManager plugin;

    public Languages(ServerManager plugin){
        this.plugin = plugin;
        CreateConfigLanguage();
    }

    private void CreateConfigLanguage(){
        final File folder = new File(plugin.getDataFolder(), "Languages");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        CreateFileLanguages();
    }

    private void CreateFileLanguages(){
        final File Fr = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-FR.yml");
        if(!Fr.exists()){
            try {
                Fr.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final File En = new File(plugin.getDataFolder() + File.separator + "Languages","Lang-EN.yml");
        if(!En.exists()){
            try {
                En.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            saveDefaultConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveDefaultConfig() throws IOException {
        final File Fr = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-FR.yml");
        final FileConfiguration FR = YamlConfiguration.loadConfiguration(Fr);
        final File En = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-EN.yml");
        final FileConfiguration EN = YamlConfiguration.loadConfiguration(En);


        Reader LangFR = null;
        Reader LangEN = null;
        try {
            LangFR = new InputStreamReader(plugin.getResource("Lang-FR.yml"), "UTF-8");
            LangEN = new InputStreamReader(plugin.getResource("Lang-EN.yml"), "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        if(LangFR != null){
            YamlConfiguration deConfigFR = YamlConfiguration.loadConfiguration(LangFR);
            FR.setDefaults(deConfigFR);
            FR.options().copyDefaults(true);
            FR.save(Fr);
        }
        if(LangEN != null){
            YamlConfiguration deConfigEN = YamlConfiguration.loadConfiguration(LangEN);
            EN.setDefaults(deConfigEN);
            EN.options().copyDefaults(true);
            EN.save(En);
        }
    }

    private static FileConfiguration getLangFr(){
        final File Fr = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-FR.yml");
        return YamlConfiguration.loadConfiguration(Fr);
    }

    private static FileConfiguration getLangEn(){
        final File En = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-EN.yml");
        return YamlConfiguration.loadConfiguration(En);
    }

    private static FileConfiguration getLangUse(){
        final String lang = plugin.Fm.getConfig("config.yml").getString("Language");
        if(lang.equalsIgnoreCase("Fr")){
            return getLangFr();
        }
        return getLangEn();
    }

    public static String getString(String path){
        try {
            return getLangUse().getString(path).replaceAll("&", "§");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean getBoolean(String path){
        return getLangUse().getBoolean(path);
    }

    public static List getList(String path){
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < Languages.getLangUse().getStringList(path).size(); i++){
            list.add(getLangUse().getStringList(path).get(i).replaceAll("&", "§"));
        }
        return list;
    }

    public void setString(String path, String value) {
        final File file;
        final FileConfiguration langUse = getLangUse();

        final String lang = plugin.Fm.getConfig("config.yml").getString("Language");
        if(lang.equalsIgnoreCase("Fr")){
            file = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-FR.yml");
        }else{
            file = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-EN.yml");
        }

        langUse.set(path, value);
        try {
            langUse.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setBoolean(String path, boolean value) {
        final File file;
        final FileConfiguration langUse = getLangUse();

        final String lang = plugin.Fm.getConfig("config.yml").getString("Language");
        if(lang.equalsIgnoreCase("Fr")){
            file = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-FR.yml");
        }else{
            file = new File(plugin.getDataFolder() + File.separator + "Languages", "Lang-EN.yml");
        }

        langUse.set(path, value);
        try {
            langUse.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

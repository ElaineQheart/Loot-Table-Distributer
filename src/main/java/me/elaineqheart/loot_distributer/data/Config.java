package me.elaineqheart.loot_distributer.data;

import com.google.common.base.Charsets;
import me.elaineqheart.loot_distributer.Loot_distributer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {

    private File file;
    private FileConfiguration customFile;

    public void setup(String fileName, boolean copyDefaults){
        file = new File(Loot_distributer.getPlugin().getDataFolder(),  fileName + ".yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //uwu
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);

        if(!copyDefaults) {
            save();
            return;
        }
        final InputStream defConfigStream = Loot_distributer.getPlugin().getResource(fileName + ".yml");
        if (defConfigStream == null) return;
        customFile.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
        customFile.options().copyDefaults(true);
        save();
    }

    public FileConfiguration get(){
        return customFile;
    }

    public void save(){
        try {
            customFile.save(file);
        }catch (IOException e){
            Loot_distributer.getPlugin().getLogger().severe("Couldn't save displays.yml file");
        }
    }

    public void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}

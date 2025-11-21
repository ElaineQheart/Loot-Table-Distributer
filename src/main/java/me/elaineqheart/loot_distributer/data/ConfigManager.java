package me.elaineqheart.loot_distributer.data;

public class ConfigManager {

    public static Config tables = new Config();

    public static void setup() {
        tables.setup("tablelist", true);
    }

    public static void reload() {
        tables.reload();
    }

}

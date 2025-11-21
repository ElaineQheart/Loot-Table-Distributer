package me.elaineqheart.loot_distributer.data;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.loot.LootTable;

import java.util.List;
import java.util.Random;

public class Tables {

    private static final Random randomGenerator = new Random();
    public static List<String> chestLoot;
    public static List<String> barrelLoot;

    public static LootTable randomChest() {
        return Bukkit.getLootTable(NamespacedKey.minecraft(Tables.chestLoot.get(randomGenerator.nextInt(Tables.chestLoot.size()))));
    }
    public static LootTable randomBarrel() {
        return Bukkit.getLootTable(NamespacedKey.minecraft(Tables.barrelLoot.get(randomGenerator.nextInt(Tables.barrelLoot.size()))));
    }

    static {
        loadData();
    }

    public static void loadData() {
        FileConfiguration c = ConfigManager.tables.get();
        chestLoot = c.getStringList("chest");
        barrelLoot= c.getStringList("barrel");
    }

}

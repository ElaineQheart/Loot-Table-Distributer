package me.elaineqheart.loot_distributer;

import me.elaineqheart.loot_distributer.commands.LootCommand;
import me.elaineqheart.loot_distributer.data.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Loot_distributer extends JavaPlugin {

    private static Loot_distributer instance;
    public static Loot_distributer getPlugin(){return instance;}

    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.setup();
        Objects.requireNonNull(getCommand("loot_distributer")).setExecutor(new LootCommand());
        Objects.requireNonNull(getCommand("loot_distributer")).setTabCompleter(new LootCommand());
    }

}

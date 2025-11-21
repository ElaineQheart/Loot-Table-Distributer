package me.elaineqheart.loot_distributer.commands;

import me.elaineqheart.loot_distributer.data.ConfigManager;
import me.elaineqheart.loot_distributer.data.Settings;
import me.elaineqheart.loot_distributer.loop.Distributer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LootCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player)) return true;

        Player p = ((Player) commandSender).getPlayer();
        assert p != null;
        if(strings.length == 0) return false;
        switch (strings[0]) {
            case "reload_file":
                ConfigManager.reload();
                p.sendMessage("Successfully reloaded!");
                break;
            case "range":
                if(strings.length != 2) {
                    p.sendMessage("Usage: /" + s + " range <int>");
                } else {
                    int range = 0;
                    try {
                        range = Integer.parseInt(strings[1]);
                        if(range < 1) throw new RuntimeException();
                    } catch (Exception e) {
                        p.sendMessage(strings[1] + " is not a valid number!");
                    }
                    Settings.setRange(p, range);
                    p.sendMessage("Set range to " + range);
                }
                break;
            case "start":
                Distributer.start(p);
                break;
            case "stop":
                Distributer.stop(p);
                break;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> params = new ArrayList<>();
        List<String> assetParams = new ArrayList<>();

        if(strings.length == 1) {
            assetParams.addAll(List.of("reload_file", "range", "start", "stop"));
        }

        for (String p : assetParams) {
            if (p.indexOf(strings[0]) == 0){
                params.add(p);
            }
        }
        return params;
    }
}

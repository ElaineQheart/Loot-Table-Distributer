package me.elaineqheart.loot_distributer.data;

import me.elaineqheart.loot_distributer.Loot_distributer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Settings {

    public static void setRange(Player p, int amount) {
        p.getPersistentDataContainer().set(new NamespacedKey(Loot_distributer.getPlugin(), "range"), PersistentDataType.INTEGER, amount);
    }

    public static int getRange(Player p) {
        if(!p.getPersistentDataContainer().has(new NamespacedKey(Loot_distributer.getPlugin(), "range"), PersistentDataType.INTEGER)) return 20;
        return p.getPersistentDataContainer().get(new NamespacedKey(Loot_distributer.getPlugin(), "range"), PersistentDataType.INTEGER);
    }

}

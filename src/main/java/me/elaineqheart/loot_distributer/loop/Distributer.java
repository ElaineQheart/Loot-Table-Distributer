package me.elaineqheart.loot_distributer.loop;

import me.elaineqheart.loot_distributer.Loot_distributer;
import me.elaineqheart.loot_distributer.data.Settings;
import me.elaineqheart.loot_distributer.data.Tables;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class Distributer implements Runnable{

    private static final HashMap<Player, UUID> taskIDs = new HashMap<>();

    private final Set<Location> locations = new HashSet<>();
    private static final HashMap<Player, Set<Entity>> marker = new HashMap<>();
    private final Player p;

    public Distributer(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        Block block = p.getTargetBlockExact(Settings.getRange(p), FluidCollisionMode.NEVER);
        if(block == null) return;
        boolean summonInteraction = false;
        if(block.getState() instanceof Chest blockState) {
            Inventory inv = blockState.getSnapshotInventory();
            inv.clear();
            blockState.setLootTable(Tables.randomChest(), new Random().nextLong());
            blockState.update();
            summonInteraction = true;
        } else if(block.getState() instanceof Barrel blockState) {
            Inventory inv = blockState.getSnapshotInventory();
            inv.clear();
            blockState.setLootTable(Tables.randomBarrel(), new Random().nextLong());
            blockState.update();
            summonInteraction = true;
        }
        Location loc = block.getLocation();
        if(summonInteraction && (locations.isEmpty() || !locations.contains(loc))) {
            locations.add(loc);
            Entity entity = loc.getWorld().spawnEntity(loc.clone().add(0.5,0,0.5), EntityType.INTERACTION);
            entity.setGlowing(true);
            marker.computeIfAbsent(p, k -> new HashSet<>());
            marker.get(p).add(entity);
        }
    }

    public static void start(Player p) {
        stop(p);
        UUID id = UUID.randomUUID();
        taskIDs.put(p, id);
        Distributer distributer = new Distributer(p);
        TaskManager.addTaskID(id, Bukkit.getScheduler().runTaskTimer(Loot_distributer.getPlugin(), distributer, 0, 1).getTaskId());
    }

    public static void stop(Player p){
        TaskManager.cancelTask(taskIDs.remove(p));
        if(marker.get(p) == null) return;
        marker.get(p).forEach(Entity::remove);
    }
}

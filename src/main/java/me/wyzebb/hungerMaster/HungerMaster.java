package me.wyzebb.hungerMaster;

import me.wyzebb.hungerMaster.commands.FoodCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class HungerMaster extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("food").setExecutor(new FoodCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package theacecollective.huggies;

import theacecollective.huggies.commandexecutors.HugCommandExecutor;

import org.bukkit.plugin.java.JavaPlugin;

public final class Huggies extends JavaPlugin {

    @Override
    public void onEnable() {
        // Initialize the config
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        getCommand("hug").setExecutor(new HugCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

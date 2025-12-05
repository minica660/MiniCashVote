package MiniCash.miniCashVote;

import MiniCash.miniCashVote.commands.Maincommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniCashVote extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("vote").setExecutor(new Maincommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

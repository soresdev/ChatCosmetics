package me.sores.chatcosmetics;

import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by sores on 4/14/2021.
 */
public class ChatCosmetics extends JavaPlugin {

    private static ChatCosmetics instance;

    @Override
    public void onEnable() {
        instance = this;

        new Init(this);
    }

    @Override
    public void onDisable() {
        Init.getInstance().unload();
        instance = null;
    }

    public static ChatCosmetics getInstance() {
        return instance;
    }
}

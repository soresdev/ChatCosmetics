package me.sores.chatcosmetics;

import me.sores.chatcosmetics.commands.Command_ccreload;
import me.sores.chatcosmetics.commands.Command_chatcolor;
import me.sores.chatcosmetics.commands.Command_prefixes;
import me.sores.chatcosmetics.config.Config;
import me.sores.chatcosmetics.profile.Profile;
import me.sores.chatcosmetics.profile.ProfileHandler;
import me.sores.chatcosmetics.profile.ProfileListener;
import me.sores.chatcosmetics.util.SparkHook;
import me.sores.spark.util.database.MongoBase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

/**
 * Created by sores on 4/14/2021.
 */
public class ChatCosmetics extends JavaPlugin {

    private static ChatCosmetics instance;
    private MongoBase mongoBase;

    private ProfileHandler profileHandler;

    @Override
    public void onEnable() {
        instance = this;
        SparkHook.hookSpark(this);

        initInstances();

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        profileHandler.getProfiles().values().forEach(Profile::saveData);
        instance = null;
    }

    public void registerCommands(){
        new Command_chatcolor(this);
        new Command_prefixes(this);
        new Command_ccreload(this);
    }

    public void registerListeners(){
        Collections.singletonList(new ProfileListener()).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void initInstances(){
        new Config();
        mongoBase = new MongoBase(Config.DATABASE_HOST, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD, Config.DATABASE_NAME, Config.DATABASE_COLLECTION);

        profileHandler = new ProfileHandler();
        profileHandler.init();
    }

    public MongoBase getMongoBase() {
        return mongoBase;
    }

    public static ChatCosmetics getInstance() {
        return instance;
    }
}

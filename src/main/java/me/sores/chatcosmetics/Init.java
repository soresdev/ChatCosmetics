package me.sores.chatcosmetics;

import me.sores.chatcosmetics.commands.Command_ccreload;
import me.sores.chatcosmetics.commands.Command_chatcolor;
import me.sores.chatcosmetics.commands.Command_prefixes;
import me.sores.chatcosmetics.config.Config;
import me.sores.chatcosmetics.profile.Profile;
import me.sores.chatcosmetics.profile.ProfileHandler;
import me.sores.chatcosmetics.profile.ProfileListener;
import me.sores.chatcosmetics.util.ImpulseHook;
import me.sores.impulse.util.StringUtil;
import me.sores.impulse.util.abstr.AbstractInit;
import me.sores.impulse.util.cmdfrmwrk.BaseCommand;
import me.sores.impulse.util.database.MongoBase;
import org.bukkit.plugin.Plugin;

/**
 * Created by sores on 4/16/2021.
 */
public class Init extends AbstractInit {

    private static Init instance;
    private MongoBase mongoBase;

    private ProfileHandler profileHandler;

    public Init(Plugin plugin) {
        super(plugin);
        instance = this;

        ImpulseHook.hook(ChatCosmetics.getInstance());

        initInstances();
        registerCommands();
        registerEvents();
    }

    @Override
    public void initInstances() {
        new Config();

        try{
            mongoBase = new MongoBase(Config.DATABASE_HOST, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD, Config.DATABASE_NAME, Config.DATABASE_COLLECTION);
        }catch (Exception ex){
            StringUtil.log("&c[ChatCosmetics] Could not connect to MongoDB Database, make sure it is setup.");
        }

        profileHandler = new ProfileHandler();
        profileHandler.init();
    }

    @Override
    public void registerEvents() {
        registerListener(new ProfileListener());
    }

    @Override
    public void registerCommands() {
        registerCommand("ccreload", new Command_ccreload());
        registerCommand("chatcolor", new Command_chatcolor());
        registerCommand("prefixes", new Command_prefixes());
    }

    @Override
    public void unload() {
        profileHandler.getProfiles().values().forEach(Profile::saveData);
        instance = null;
    }

    public void registerCommand(String title, BaseCommand command) {
        registerCommand(getCommandRegistrar(), title, command);
    }

    public MongoBase getMongoBase() {
        return mongoBase;
    }

    public static Init getInstance() {
        return instance;
    }
}

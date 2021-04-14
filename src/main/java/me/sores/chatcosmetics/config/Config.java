package me.sores.chatcosmetics.config;

import com.google.common.collect.Lists;
import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.chatcosmetics.util.prefixes.ChatPrefix;
import me.sores.spark.util.ItemData;
import me.sores.spark.util.configuration.ConfigFile;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by sores on 4/14/2021.
 */
public class Config {

    public static String DATABASE_HOST, DATABASE_USERNAME, DATABASE_PASSWORD, DATABASE_NAME, DATABASE_COLLECTION;

    private static List<ChatPrefix.Prefix> prefixes = Lists.newArrayList();

    public Config() {
        ConfigFile config = new ConfigFile("config.yml", ChatCosmetics.getInstance());

        DATABASE_HOST = config.getString("database.host");
        DATABASE_USERNAME = config.getString("database.username");
        DATABASE_PASSWORD = config.getString("database.password");
        DATABASE_NAME = config.getString("database.name");
        DATABASE_COLLECTION = config.getString("database.collection");

        if(config.contains("prefixes")){
            ItemData data = new ItemData(Material.NAME_TAG);

            for(String index : config.getConfigurationSection("prefixes").getKeys(false)){
                String path = "prefixes." + index + ".";

                addPrefix(new ChatPrefix.Prefix(index, StringEscapeUtils.unescapeJava(config.getString(path + "display")),
                        StringEscapeUtils.unescapeJava(config.getString(path + "prefix")), data));
            }
        }
    }

    public static void reload(){
        destroy();
        new Config();
    }

    private static void destroy(){
        prefixes.clear();
    }

    private void addPrefix(ChatPrefix.Prefix prefix){
        prefixes.add(prefix);
    }

    public static List<ChatPrefix.Prefix> getPrefixes() {
        return prefixes;
    }
}

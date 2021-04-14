package me.sores.chatcosmetics.util;

import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.spark.util.StringUtil;

/**
 * Created by sores on 4/14/2021.
 */
public class SparkHook {

    public static void hookSpark(ChatCosmetics chatCosmetics){
        if(isSparkHooked()){
            chatCosmetics.getServer().getConsoleSender().sendMessage(StringUtil.color("&a[ChatCosmetics] Spark depend found."));
        }else{
            chatCosmetics.getServer().getConsoleSender().sendMessage(StringUtil.color("&c[ChatCosmetics] Disabling..."));
            chatCosmetics.getServer().getConsoleSender().sendMessage(StringUtil.color("&c[ChatCosmetics] ChatCosmetics cannot be enabled while missing Spark depend."));
            chatCosmetics.getServer().getPluginManager().disablePlugin(chatCosmetics);
        }
    }

    private static boolean isSparkHooked(){
        return ChatCosmetics.getInstance().getServer().getPluginManager().getPlugin("Spark") != null;
    }

}

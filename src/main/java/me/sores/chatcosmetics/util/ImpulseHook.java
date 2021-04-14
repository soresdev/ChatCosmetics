package me.sores.chatcosmetics.util;

import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.impulse.util.StringUtil;

/**
 * Created by sores on 4/14/2021.
 */
public class ImpulseHook {

    public static void hook(ChatCosmetics chatCosmetics){
        if(isImpulseHooked()){
            chatCosmetics.getServer().getConsoleSender().sendMessage(StringUtil.color("&a[ChatCosmetics] Impulse depend found."));
        }else{
            chatCosmetics.getServer().getConsoleSender().sendMessage(StringUtil.color("&c[ChatCosmetics] Disabling..."));
            chatCosmetics.getServer().getConsoleSender().sendMessage(StringUtil.color("&c[ChatCosmetics] ChatCosmetics cannot be enabled while missing Impulse depend."));
            chatCosmetics.getServer().getPluginManager().disablePlugin(chatCosmetics);
        }
    }

    private static boolean isImpulseHooked(){
        return ChatCosmetics.getInstance().getServer().getPluginManager().getPlugin("Impulse") != null;
    }

}

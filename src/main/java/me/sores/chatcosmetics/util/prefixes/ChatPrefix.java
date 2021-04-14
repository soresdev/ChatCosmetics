package me.sores.chatcosmetics.util.prefixes;

import me.sores.chatcosmetics.config.Config;
import me.sores.chatcosmetics.profile.Profile;
import me.sores.impulse.util.ItemData;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class ChatPrefix {

    public static class Prefix {

        public Prefix(String index, String display, String color, ItemData data) {
            this.index = index;
            this.display = display;
            this.prefix = color;
            this.data = data;
        }

        private String index, display, prefix;
        private ItemData data;

        public void apply(Profile profile){
            profile.setSelectedPrefix(this);
        }

        public boolean has(Player player){
            return player.isOp() || player.hasPermission("chatcosmetics.prefixes." + index.toLowerCase());
        }

        public String getIndex() {
            return index;
        }

        public String getDisplay() {
            return display;
        }

        public String getPrefix() {
            return prefix;
        }

        public ItemData getData() {
            return data;
        }
    }

    public static Prefix valueOf(String name){
        for(Prefix prefix : Config.getPrefixes()){
            if(prefix.getIndex().equalsIgnoreCase(name)) return prefix;
        }

        return null;
    }

}

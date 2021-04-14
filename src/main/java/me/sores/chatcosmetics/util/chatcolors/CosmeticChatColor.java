package me.sores.chatcosmetics.util.chatcolors;

import me.sores.chatcosmetics.profile.Profile;
import me.sores.spark.util.ItemData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public enum CosmeticChatColor {

    RESET("Reset", ChatColor.RESET, new ItemData(Material.WOOL, (short) 0)) {
        @Override
        public boolean has(Player player) {
            return true;
        }

        @Override
        public void apply(Profile profile) {
            profile.setSelectedChatColor(null);
        }
    },

    GREEN("Green", ChatColor.GREEN, new ItemData(Material.WOOL, (short) 5)),
    BLUE("Blue", ChatColor.AQUA, new ItemData(Material.WOOL, (short) 3)),
    YELLOW("Yellow", ChatColor.YELLOW, new ItemData(Material.WOOL, (short) 4)),
    PINK("Pink", ChatColor.LIGHT_PURPLE, new ItemData(Material.WOOL, (short) 6)),
    RED("Red", ChatColor.RED, new ItemData(Material.WOOL, (short) 14)),
    DARK_GRAY("Dark Gray", ChatColor.DARK_GRAY, new ItemData(Material.WOOL, (short) 7)),
    DARK_GREEN("Dark Green", ChatColor.DARK_GREEN, new ItemData(Material.WOOL, (short) 13));

    CosmeticChatColor(String display, ChatColor color, ItemData data){
        this.display = display;
        this.color = color.toString();
        this.data = data;
    }

    CosmeticChatColor(String display, String color, ItemData data) {
        this.display = display;
        this.color = color;
        this.data = data;
    }

    private String display, color;
    private ItemData data;

    public void apply(Profile profile){
        profile.setSelectedChatColor(this);
    }

    public boolean has(Player player){
        return player.isOp() || player.hasPermission("chatcosmetics.chatcolor." + toString().toLowerCase());
    }

    public String getDisplay() {
        return display;
    }

    public String getColor() {
        return color;
    }

    public ItemData getData() {
        return data;
    }
}


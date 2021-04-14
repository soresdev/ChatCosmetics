package me.sores.chatcosmetics.util.prefixes.menu;

import me.sores.chatcosmetics.profile.Profile;
import me.sores.chatcosmetics.util.prefixes.ChatPrefixColor;
import me.sores.spark.util.ItemBuilder;
import me.sores.spark.util.MessageUtil;
import me.sores.spark.util.menu.Button;
import me.sores.spark.util.menu.Menu;
import me.sores.spark.util.menu.buttons.BackButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sores on 4/14/2021.
 */
public class PrefixColorMenu extends Menu {

    private Player player;
    private Profile profile;

    public PrefixColorMenu(Player player, Profile profile) {
        this.player = player;
        this.profile = profile;

        setAutoUpdate(true);
        setUpdateAfterClick(true);
    }

    @Override
    public String getTitle(Player player) {
        return "&aSelect your prefix color";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int index = 0;

        for(ChatPrefixColor color : ChatPrefixColor.values()){
            buttons.put(index, new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(color.getData().getMaterial()).setData(color.getData().getData()).setName(color.getColor() + color.getDisplay()).build();
                }

                @Override
                public void clicked(Player player, ClickType clickType) {
                    setColor(color);
                    MessageUtil.message(player, "&7You have selected the " + color.getColor() + profile.getSelectedPrefix().getDisplay() + " &7prefix.");
                    player.closeInventory();
                }
            });

            index++;
        }

        buttons.put(26, new BackButton(new PrefixMenu(player, profile)));

        return buttons;
    }

    private void setColor(ChatPrefixColor color){
        profile.setSelectedPrefixColor(color);
    }
}

package me.sores.chatcosmetics.commands;

import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.chatcosmetics.profile.ProfileHandler;
import me.sores.chatcosmetics.util.chatcolors.menu.ColorMenu;
import me.sores.spark.util.MessageUtil;
import me.sores.spark.util.command.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class Command_chatcolor implements ICommand {

    private ChatCosmetics chatCosmetics;

    public Command_chatcolor(ChatCosmetics chatCosmetics) {
        this.chatCosmetics = chatCosmetics;

        register();
    }

    @Override
    public void execute(CommandSender sender, String... args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(!hasPermisson(player, "chatcosmetics.chatcolor")){
                sendPermissionMessage(player);
                return;
            }

            new ColorMenu(player, ProfileHandler.getInstance().getFrom(player.getUniqueId())).openMenu(player);
        }
    }

    @Override
    public void register() {
        chatCosmetics.getCommand("chatcolor").setExecutor(this);
    }

    @Override
    public void sendPermissionMessage(Player player) {
        MessageUtil.noPermission(player);
    }

    @Override
    public boolean hasPermisson(Player player, String s) {
        return player.hasPermission(s);
    }
}

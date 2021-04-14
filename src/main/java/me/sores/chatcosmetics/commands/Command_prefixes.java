package me.sores.chatcosmetics.commands;

import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.chatcosmetics.profile.ProfileHandler;
import me.sores.chatcosmetics.util.prefixes.menu.PrefixMenu;
import me.sores.spark.util.MessageUtil;
import me.sores.spark.util.command.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class Command_prefixes implements ICommand {

    private ChatCosmetics chatCosmetics;

    public Command_prefixes(ChatCosmetics chatCosmetics) {
        this.chatCosmetics = chatCosmetics;

        register();
    }

    @Override
    public void execute(CommandSender sender, String... args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(!hasPermisson(player, "chatcosmetics.prefixes")){
                sendPermissionMessage(player);
                return;
            }

            new PrefixMenu(player, ProfileHandler.getInstance().getFrom(player.getUniqueId())).openMenu(player);
        }
    }

    @Override
    public void register() {
        chatCosmetics.getCommand("prefixes").setExecutor(this);
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

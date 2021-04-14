package me.sores.chatcosmetics.commands;

import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.chatcosmetics.config.Config;
import me.sores.impulse.util.MessageUtil;
import me.sores.impulse.util.command.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class Command_ccreload implements ICommand {

    private ChatCosmetics chatCosmetics;

    public Command_ccreload(ChatCosmetics chatCosmetics) {
        this.chatCosmetics = chatCosmetics;

        register();
    }

    @Override
    public void execute(CommandSender sender, String... args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(!hasPermisson(player, "chatcosmetics.reload")){
                sendPermissionMessage(player);
                return;
            }

            Config.reload();
            MessageUtil.message(player, "&aYou have successfully reloaded ChatCosmetics' config file.");
        }
    }

    @Override
    public void register() {
        chatCosmetics.getCommand("ccreload").setExecutor(this);
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

package me.sores.chatcosmetics.commands;

import me.sores.chatcosmetics.config.Config;
import me.sores.impulse.util.MessageUtil;
import me.sores.impulse.util.cmdfrmwrk.BaseCommand;
import me.sores.impulse.util.cmdfrmwrk.CommandUsageBy;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class Command_ccreload extends BaseCommand {

    public Command_ccreload(){
        super("ccreload", "chatcosmetics.reload", CommandUsageBy.PLAYER, new String[] { "chatcosmeticsreload" });
        setUsage("/<command>");
        setArgRange(0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Config.reload();
        MessageUtil.message(player, "&aYou have successfully reloaded ChatCosmetics' config file.");
    }

}

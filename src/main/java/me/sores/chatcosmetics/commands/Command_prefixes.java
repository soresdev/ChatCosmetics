package me.sores.chatcosmetics.commands;

import me.sores.chatcosmetics.profile.ProfileHandler;
import me.sores.chatcosmetics.util.prefixes.menu.PrefixMenu;
import me.sores.impulse.util.cmdfrmwrk.BaseCommand;
import me.sores.impulse.util.cmdfrmwrk.CommandUsageBy;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class Command_prefixes extends BaseCommand {

    public Command_prefixes(){
        super("prefix", "chatcosmetics.prefixes", CommandUsageBy.PLAYER, new String[] { "tag", "tags", "prefix", "pref" });
        setUsage("/<command>");
        setArgRange(0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        new PrefixMenu(player, ProfileHandler.getInstance().getFrom(player.getUniqueId())).openMenu(player);
    }

}

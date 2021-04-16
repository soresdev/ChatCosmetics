package me.sores.chatcosmetics.commands;

import me.sores.chatcosmetics.profile.ProfileHandler;
import me.sores.chatcosmetics.util.chatcolors.menu.ColorMenu;
import me.sores.impulse.util.cmdfrmwrk.BaseCommand;
import me.sores.impulse.util.cmdfrmwrk.CommandUsageBy;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by sores on 4/14/2021.
 */
public class Command_chatcolor extends BaseCommand {

    public Command_chatcolor(){
        super("chatcolor", "chatcosmetics.chatcolor", CommandUsageBy.PLAYER, new String[] { "colors", "chatcolors" });
        setUsage("/<command>");
        setArgRange(0, 0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        new ColorMenu(player, ProfileHandler.getInstance().getFrom(player.getUniqueId())).openMenu(player);
    }

}

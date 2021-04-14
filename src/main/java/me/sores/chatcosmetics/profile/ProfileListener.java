package me.sores.chatcosmetics.profile;

import me.sores.impulse.util.StringUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by sores on 4/14/2021.
 */
public class ProfileListener implements Listener {

    @EventHandler
    public void onAsyncPlayerLogin(AsyncPlayerPreLoginEvent event){
        if(ProfileHandler.getInstance().getFrom(event.getUniqueId()) == null){
            ProfileHandler.getInstance().getProfiles().put(event.getUniqueId(), new Profile(event.getUniqueId()));
        }

        Profile profile = ProfileHandler.getInstance().getFrom(event.getUniqueId());

        ProfileHandler.getInstance().load(profile);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Profile profile = ProfileHandler.getInstance().getFrom(player.getUniqueId());

        ProfileHandler.getInstance().save(profile);
        ProfileHandler.getInstance().getProfiles().remove(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Profile profile = ProfileHandler.getInstance().getFrom(event.getPlayer().getUniqueId());

        if(profile.getSelectedPrefix() != null){
            if(profile.getSelectedPrefixColor() != null){
                event.setFormat(StringUtil.color(profile.getSelectedPrefixColor().getColor() +
                        profile.getSelectedPrefix().getPrefix().replace("_", " ")) + " " + event.getFormat());
            }else{
                event.setFormat(profile.getSelectedPrefix().getPrefix().replace("_", " ") + " " + event.getFormat());
            }
        }
        if(profile.getSelectedChatColor() != null) event.setMessage(StringUtil.color(profile.getSelectedChatColor().getColor().replace("_", " ") + event.getMessage()));
    }

}

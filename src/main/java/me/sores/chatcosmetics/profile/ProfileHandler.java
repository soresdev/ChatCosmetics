package me.sores.chatcosmetics.profile;

import com.google.common.collect.Maps;
import me.sores.chatcosmetics.ChatCosmetics;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;

/**
 * Created by sores on 4/14/2021.
 */
public class ProfileHandler {

    private static ProfileHandler instance;
    private Map<UUID, Profile> profiles;

    public ProfileHandler() {
        instance = this;
        profiles = Maps.newHashMap();
    }

    public void init(){
        if(Bukkit.getOnlinePlayers().size() != 0){
            Bukkit.getOnlinePlayers().forEach(player -> {
                if(getFrom(player.getUniqueId()) == null){
                    profiles.put(player.getUniqueId(), new Profile(player.getUniqueId()));
                }

                load(getFrom(player.getUniqueId()));
            });
        }
    }

    public void load(Profile profile){
        Bukkit.getScheduler().runTaskAsynchronously(ChatCosmetics.getInstance(), profile::loadData);
    }

    public void save(Profile profile){
        Bukkit.getScheduler().runTaskAsynchronously(ChatCosmetics.getInstance(), profile::saveData);
    }

    public Profile getFrom(UUID uuid){
        for(Profile profile : profiles.values()){
            if(profile.getID().equals(uuid)){
                return profile;
            }
        }

        return null;
    }

    public Map<UUID, Profile> getProfiles() {
        return profiles;
    }

    public static ProfileHandler getInstance() {
        return instance;
    }
}

package me.sores.chatcosmetics.profile;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import me.sores.chatcosmetics.ChatCosmetics;
import me.sores.chatcosmetics.util.chatcolors.CosmeticChatColor;
import me.sores.chatcosmetics.util.prefixes.ChatPrefix;
import me.sores.chatcosmetics.util.prefixes.ChatPrefixColor;
import me.sores.impulse.util.profile.PlayerProfile;
import org.bson.Document;

import java.util.UUID;

/**
 * Created by sores on 4/14/2021.
 */
public class Profile extends PlayerProfile {

    private ChatPrefix.Prefix selectedPrefix;
    private ChatPrefixColor selectedPrefixColor;
    private CosmeticChatColor selectedChatColor;

    public Profile(UUID uuid) {
        super(uuid);

        selectedPrefix = null;
        selectedPrefixColor = null;
        selectedChatColor = CosmeticChatColor.RESET;

    }

    @Override
    public void saveData() {
        try{
            MongoCollection<Document> collection = ChatCosmetics.getInstance().getMongoBase().getCollection();
            Document fetched = fetchCurrentObject();

            if(fetched != null){
                collection.replaceOne(fetched, createDocument());
            }else{
                collection.insertOne(createDocument());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        Document fetched = fetchCurrentObject();

        if(fetched != null){
            try{

                if(fetched.containsKey("prefix")){
                    try{
                        selectedPrefix = ChatPrefix.valueOf(fetched.getString("prefix"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                if(fetched.containsKey("prefix_color")){
                    try{
                        selectedPrefixColor = ChatPrefixColor.valueOf(fetched.getString("prefix_color"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                if(fetched.containsKey("chatcolor")){
                    try{
                        selectedChatColor = CosmeticChatColor.valueOf(fetched.getString("chatcolor"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            saveData();
        }
    }

    @Override
    public Document createDocument() {
        Document document = new Document("_id", getID().toString());

        document.put("name", getName());

        if(selectedPrefix != null) document.put("prefix", selectedPrefix.getIndex());
        if(selectedPrefixColor != null) document.put("prefix_color", selectedPrefixColor.toString());
        if(selectedChatColor != null) document.put("chatcolor", selectedChatColor.toString());

        return document;
    }

    @Override
    public Document fetchCurrentObject() {
        FindIterable<Document> cursor = ChatCosmetics.getInstance().getMongoBase().getCollection().find(new Document("_id", getID().toString()));

        return cursor.first();
    }

    public ChatPrefix.Prefix getSelectedPrefix() {
        return selectedPrefix;
    }

    public void setSelectedPrefix(ChatPrefix.Prefix selectedPrefix) {
        this.selectedPrefix = selectedPrefix;
    }

    public ChatPrefixColor getSelectedPrefixColor() {
        return selectedPrefixColor;
    }

    public void setSelectedPrefixColor(ChatPrefixColor selectedPrefixColor) {
        this.selectedPrefixColor = selectedPrefixColor;
    }

    public CosmeticChatColor getSelectedChatColor() {
        return selectedChatColor;
    }

    public void setSelectedChatColor(CosmeticChatColor selectedChatColor) {
        this.selectedChatColor = selectedChatColor;
    }
}

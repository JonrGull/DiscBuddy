package discbuddy;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ManageDataBase extends ListenerAdapter {

    private static final String DB_NAME = "users";

    ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://jonrgull:q8bcrH7K9Lkxet49@cluster0.s9fqw.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase(DB_NAME);

    public void onMessageReceived(MessageReceivedEvent event) {
        ManageDataBase db = new ManageDataBase();

        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!users")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage(db.database.getCollection(DB_NAME).find().first().toString()).queue();

        }

    }

}
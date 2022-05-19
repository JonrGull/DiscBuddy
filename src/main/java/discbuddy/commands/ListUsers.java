package discbuddy.commands;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ListUsers extends ListenerAdapter {
    Dotenv dotenv = Dotenv.load();
    String DB_URL = dotenv.get("DB_URL");

    private static final String DB_NAME = "users";

    ConnectionString connectionString = new ConnectionString(DB_URL);
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase db = mongoClient.getDatabase(DB_NAME);
    MongoCollection<Document> items = db.getCollection("users");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if (msg.getContentRaw().equals("!list")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Users in the database:").queue();
            for (Document doc : items.find()) {
                channel.sendMessage("There's " + doc.getString("user")).queue();
            }
        }

    }

}

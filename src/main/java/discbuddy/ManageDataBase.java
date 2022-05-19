package discbuddy;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

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
    MongoDatabase db = mongoClient.getDatabase(DB_NAME);
    MongoCollection<Document> items = db.getCollection("users");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String userId = event.getAuthor().getId();
        Document user = new Document("userId", userId);

        if (msg.getContentRaw().equals("!register")) {
            MessageChannel channel = event.getChannel();

            if (items.find(user).first() == null) {
                items.insertOne(user);
                channel.sendMessage("You have been registered!").queue();
            } else {
                channel.sendMessage("Hang on there, you are already registered!").queue();
            }

        }

        if (msg.getContentRaw().equals("!unregister")) {
            MessageChannel channel = event.getChannel();

            if (items.find(user).first() != null) {
                items.deleteOne(user);
                channel.sendMessage("You have been unregistered!").queue();
            } else {
                channel.sendMessage("Who are you? Did you register yet?").queue();
            }

        }

        if (msg.getContentRaw().equals("!check")) {
            MessageChannel channel = event.getChannel();

            if (items.find(user).first() != null) {
                channel.sendMessage("Wooo! You are registered!").queue();
            } else {
                channel.sendMessage("You are not registered yet!").queue();
            }

        }

    }

}
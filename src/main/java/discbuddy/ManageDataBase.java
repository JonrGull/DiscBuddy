package discbuddy;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ManageDataBase {

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

    public static void main(String[] args) {
        ManageDataBase db = new ManageDataBase();

        // count documents in users db
        System.out.println(db.database.getCollection("users").countDocuments());
    }

}

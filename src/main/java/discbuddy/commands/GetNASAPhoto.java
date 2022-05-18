package discbuddy.commands;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import discbuddy.commands.utils.Photo;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GetNASAPhoto extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Dotenv dotenv = Dotenv.load();
        String NASA_KEY = dotenv.get("NASA_KEY");
        String PHOTO_API_URL = "https://api.nasa.gov/planetary/apod?api_key=" + NASA_KEY + "&count=1";

        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!space")) {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .timeout(Duration.ofMinutes(2))
                    .header("accept", "application_json")
                    .uri(URI.create(PHOTO_API_URL))
                    .build();

            HttpResponse<String> response;
            try {
                MessageChannel channel = event.getChannel();

                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                ObjectMapper mapper = new ObjectMapper();
                List<Photo> posts = mapper.readValue(response.body(), new TypeReference<List<Photo>>() {});

                channel.sendMessage("Here's a sweet photo of space!: " + posts.get(0).getHdurl()).queue();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

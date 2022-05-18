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

import discbuddy.commands.utils.Cat;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Cats extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Dotenv dotenv = Dotenv.load();
        String CAT_KEY = dotenv.get("CAT_KEY");
        String CAT_API_URL = "https://api.thecatapi.com/v1/images/search?mime_types=gif";

        String[] LIST_OF_CAT_WORDS = { "cat", "cats", "neko", "kitty", "kitty-cat", "kittycat", "ねこ", "猫", "sad",
                "feline" };
        String[] message = event.getMessage().getContentRaw().split(" ");

        String foundCatWord;

        for (String word : message) {
            for (String catWord : LIST_OF_CAT_WORDS) {
                if (word.equalsIgnoreCase(catWord)) {

                    foundCatWord = word;

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .GET()
                            .timeout(Duration.ofMinutes(2))
                            .header("accept", "application_json")
                            .header("x-api-key", CAT_KEY)
                            .uri(URI.create(CAT_API_URL))
                            .build();

                    HttpResponse<String> response;
                    try {
                        MessageChannel channel = event.getChannel();

                        response = client.send(request, HttpResponse.BodyHandlers.ofString());

                        ObjectMapper mapper = new ObjectMapper();
                        List<Cat> cat = mapper.readValue(response.body(), new TypeReference<List<Cat>>() {
                        });

                        channel.sendMessage(
                                "Did someone say " + foundCatWord + "?! " + cat.get(0).getUrl()).queue();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}

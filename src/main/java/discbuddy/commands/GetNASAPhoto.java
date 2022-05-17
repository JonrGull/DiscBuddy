package discbuddy.commands;

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

        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!space")) {

            String[] nasaArray = new String[1];
            nasaArray[0] = "https://api.nasa.gov/planetary/apod?api_key=" + NASA_KEY + "&count=1";

            MessageChannel channel = event.getChannel();
            channel.sendMessage(
                    "Here is your NASA photo of the day: ").queue();

        }
    }

}

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

        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!space")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage(
                    "https://api.nasa.gov/planetary/apod?api_key=" + dotenv.get("NASA_KEY")).queue();

        }
    }

}

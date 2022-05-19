package discbuddy;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class KickUser extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String user = event.getAuthor().getAsMention();

        if (msg.getContentRaw().equals("This bot sucks")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("No, " + user + "! This bot is awesome!").queue();
            event.getGuild().kick(event.getMember()).queue();
            channel.sendMessage(user + " has been kicked from the server!").queue();
        }
    }

}

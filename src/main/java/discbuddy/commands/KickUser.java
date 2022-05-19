package discbuddy.commands;

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

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            channel.sendMessage("**Shining my boot**").queue();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            channel.sendMessage("3").queue();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            channel.sendMessage("2").queue();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            channel.sendMessage("1").queue();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            event.getGuild().kick(event.getMember()).queue();
            channel.sendMessage(user + " has been kicked from the server!").queue();
        }
    }

}

package discbuddy.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GetHelp extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!help")) {
            MessageChannel channel = event.getChannel();

            channel.sendMessage("No shame in asking for help! Here is what I can do for you: \n"
                    + "`!register` - registers you to the database \n"
                    + "`!unregister` - unregisters you from the database \n"
                    + "`!check` - checks if you are in the database \n"
                    + "`!list` - lists all users in the database \n"
                    + "`!time` - returns the current time \n"
                    + "`!nasa` - returns a picture of the day from NASA \n"
                    + "`!help` - returns this message")
                    .queue();

        }
    }
}

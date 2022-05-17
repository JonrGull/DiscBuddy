package discbuddy.commands;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GetTime extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!time")) {
            MessageChannel channel = event.getChannel();
            DateTime dt = new DateTime();
            LocalDate date = LocalDate.now();

            String currentTime = "The time is: " + (dt.toString("HH:mm:ss"));
            String currentDate = ("\n" + " Today's date is: " + date.toString("MM/dd/yyyy"));

            if (dt.getHourOfDay() < 12) {
                channel.sendMessage("Good morning!☀️ "  + currentTime + currentDate)
                        .queue();
            } else if (dt.getHourOfDay() < 18) {
                channel.sendMessage("Good afternoon! ⛱️ " + currentTime + currentDate).queue();
            } else {
                channel.sendMessage("Good evening! 🌕 " + currentTime).queue();
            }
        }
    }
}
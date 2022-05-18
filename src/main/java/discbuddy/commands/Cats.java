package discbuddy.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Cats {
    public void onGuildMessageReceived(MessageReceivedEvent event) {

        String[] LIST_OF_CAT_WORDS = { "cat", "cats", "neko", "kitty", "kitty-cat", "kittycat", "ねこ", "猫", "sad" };
        String[] message = event.getMessage().getContentRaw().split(" ");
        for (int i = 0; i < message.length; i++) {

            for (int b = 0; b < LIST_OF_CAT_WORDS.length; b++) {
                if (message[i].equalsIgnoreCase(LIST_OF_CAT_WORDS[b])) {
                    event.getMessage().delete().queue();
                }
            }
        }

    }
}

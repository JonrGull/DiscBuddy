package discbuddy;

import javax.security.auth.login.LoginException;

import discbuddy.commands.Cats;
import discbuddy.commands.GetHelp;
import discbuddy.commands.GetNASAPhoto;
import discbuddy.commands.GetTime;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Main {

    public static void main(String[] args) throws LoginException, InterruptedException {

        Dotenv dotenv = Dotenv.load();

        JDA bot = JDABuilder.createDefault(dotenv.get("DISCBUDDY_KEY"))
                .setActivity(Activity.playing("Virtual Studio Code"))
                .build()
                .awaitReady();

        bot.getTextChannelsByName("general", true).get(0).sendMessage(
                "My name is **DiscBuddy**! Thank you for adding me. Type **!help** for my list of commands!")
                .queue();

        bot.addEventListener(new GetHelp());
        bot.addEventListener(new GetTime());
        bot.addEventListener(new GetNASAPhoto());
        bot.addEventListener(new Cats());
        bot.addEventListener(new KickUser());
        bot.addEventListener(new ManageDataBase());

    }
}
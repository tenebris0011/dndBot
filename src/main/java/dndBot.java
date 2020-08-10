import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.examples.command.ShutdownCommand;
import commands.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

public class dndBot {
    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException {
        // Config.txt consists of bot token and ownerID.
        List<String> list = Files.readAllLines(Paths.get("config.txt"));

        // Grabs 1st line from config and stores it in token variable.
        String token = list.get(0);
        String ownerID = list.get(1);

        // Define eventwaiter.
        EventWaiter waiter = new EventWaiter();

        // Define a command client.
        CommandClientBuilder client = new CommandClientBuilder();

        // Default is !help
        client.useDefaultGame();

        // Set ownerID
        client.setOwnerId(ownerID);

        // Command prefix
        client.setPrefix("!");

        client.addCommands(

                // Command to roll dice.
                new diceCommand(),

                // Command to flip coin.
                new flipCommand(),

                // Command to get spell info.
                new spellCommand(),

                // Command to get monster info.
                new monsterCommand(),

                // Command to shutdown server.
                new ShutdownCommand());

    new JDABuilder(AccountType.BOT)
            .setToken(token)
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.playing("loading..."))

            .addEventListeners(waiter, client.build())
            .build();
    }
}

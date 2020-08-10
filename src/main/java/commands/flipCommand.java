package commands;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;

public class flipCommand extends Command{
   //public Random random;
    public flipCommand()
    {
            //this.random = new Random();
            this.name = "flip";
            this.help = "This command flips a coin.";
    }
    protected void execute(CommandEvent event) {
        Random random = new Random();
        List<String> coin = Arrays.asList("heads", "tails");
        String flip = coin.get(random.nextInt(coin.size()));
        event.replySuccess("You clipped a coin with the result of: " + flip +".");
    }
}

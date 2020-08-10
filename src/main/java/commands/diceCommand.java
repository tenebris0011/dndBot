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

public class diceCommand extends Command{
    public Random random;
    public diceCommand()
    {
        this.random = new Random();
        this.name = "dice";
        this.help = "This command requires 2 arguments to roll the dice. Your first input is the dice selection, the second is the modifier. If no modifier simply put in 0. Example !dice 20 3";
        this.arguments = "<item> <item>";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()) {
            event.replyWarning("I need choices!");
        }
        else if(!event.getArgs().isEmpty())
        {
            //Stores args in array list.
            List<String> items = Arrays.asList(event.getArgs().split("\\s"));
            int choice = Integer.parseInt(items.get(0));
            int modifier = Integer.parseInt(items.get(1));
            int total;
            //Switch case for dice.
            switch (choice) {
                case 4:
                    total = random.nextInt(4) + 1 + modifier;
                    event.replySuccess("You chose to roll a d4 with the result of " + Integer.toString(total) + ". Your a modifier was " + modifier +".");
                    break;
                case 6:
                    total = random.nextInt(6) + 1 + modifier;
                    event.replySuccess("You chose to roll a d6 with the result of " + Integer.toString(total) + ". Your a modifier was " + modifier +".");
                    break;
                case 8:
                    total = random.nextInt(8) + 1 + modifier;
                    event.replySuccess("You chose to roll a d8 with the result of " + Integer.toString(total) + ". Your a modifier was " + modifier +".");
                    break;
                case 10:
                    total = random.nextInt(10) + 1 + modifier;
                    event.replySuccess("You chose to roll a d10 with the result of " + Integer.toString(total) + ". Your a modifier was " + modifier +".");
                    break;
                case 12:
                    total = random.nextInt(12) + 1 + modifier;
                    event.replySuccess("You chose to roll a d12 with the result of " + Integer.toString(total) + ". Your a modifier was " + modifier +".");
                    break;
                case 20:
                    total = random.nextInt(20) + 1 + modifier;
                    event.replySuccess("You chose to roll a d20 with the result of " + Integer.toString(total) + ". Your a modifier was " + modifier +".");
                    break;
            }
        }
    }
}

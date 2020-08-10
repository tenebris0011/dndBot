package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class spellCommand extends Command {
    //public Random random;
    public spellCommand()
    {
        this.name = "spell";
        this.help = "Command to get info on spells. This command may take a second to populate all responses.";

    }
    //Execute bot command
    protected void execute(CommandEvent event) {
        //parses url into array as list.
        List<String> items = Arrays.asList(event.getArgs().split("\\s"));
        int i;
        int total = 0;
        String search = "";
        //stores each item in search string
        for (i = 0; i < items.size();)
        {
            search += items.get(i);
            i++;
            if (i != items.size())
            {
                search += "-";
            }
        }
        //takes search string and inputs into json url for json to parse.
        String formattedSearch = search.toLowerCase().replace("\'","");
        try {
            String url = "https://api.open5e.com/spells/"+formattedSearch+"/?format=json";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
            // outputs results into discord channel.
            JSONObject myresponse = new JSONObject(response.toString());
            event.replySuccess("Name: "+myresponse.getString("name"));
            event.replySuccess("Description: "+myresponse.getString("desc"));
            event.replySuccess("Higher Level: "+myresponse.getString("higher_level"));
            event.replySuccess("Range: "+myresponse.getString("range"));
            event.replySuccess("Duration: "+myresponse.getString("duration"));
            event.replySuccess("Casting Time: "+myresponse.getString("casting_time"));
        }catch (IOException e)
        {
            e.getMessage();
        }
    }
}

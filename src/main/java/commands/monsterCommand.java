package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class monsterCommand  extends Command {
    {
        this.name = "monster";
        this.help = "Command to get info on monsters. This command may take a second to populate all responses.";

    }
    //Execute bot command
    protected void execute(CommandEvent event) {
        List<String> items = Arrays.asList(event.getArgs().split("\\s"));
        int i;
        int total = 0;
        String search = "";
        for (i = 0; i < items.size();)
        {
            search += items.get(i);
            i++;
            if (i != items.size())
            {
                search += "-";
            }
        }
        String formattedSearch = search.toLowerCase().replace("\'","");
        // parses json output.
        try {
            String url = "https://api.open5e.com/monsters/" + formattedSearch + "/?format=json";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject myresponse = new JSONObject(response.toString());
            // Gets legendary_actions array from json response.

            //actions
            //special_abilities
            event.replySuccess("Name: " + myresponse.getString("name"));
            event.replySuccess("Size: " + myresponse.getString("size"));
            event.replySuccess("Armor Class: " + myresponse.getInt("armor_class"));
            event.replySuccess("Hit Points: " + myresponse.getInt("hit_points"));
            event.replySuccess("Hit Dice: " + myresponse.getString("hit_dice"));
            // gets arrays from list and outputs them
            List<String> list = Arrays.asList("actions", "legendary_actions", "special_abilities");
            JSONArray actionsArray = myresponse.getJSONArray("actions");
            JSONArray legArray = myresponse.getJSONArray("legendary_actions");
            JSONArray specArray = myresponse.getJSONArray("special_abilities");

            event.replySuccess("__**Actions**__");
            for (i = 0; i < actionsArray.length();i++)
            {
                String result = "";
                result = actionsArray.get(i).toString();
                event.replySuccess(result.replace("\"","").replace("Hit:","\n*Hit:*").replace("Attack:","\n*Attack:*").replace("damage_dice:","\n*Damage Dice:* ").replace("attack_bonus:","*Attack Bonus:* ").replace("name:","\n**Name:** ").replace("desc:","\n*Desc:* ").replace(",","").replace("{","").replace("}",""));
                if (i == actionsArray.length())
                    event.replySuccess("\n");
            }

            event.replySuccess("__**Legendary Actions**__");
            for (i = 0; i < legArray.length();i++)
            {
                String result = "";
                result = legArray.get(i).toString();
                event.replySuccess(result.replace("\"","").replace("name:","**Name:** ").replace("desc:","\n*Desc:* ").replace(",","").replace("{","").replace("}",""));
                if (i == legArray.length())
                    event.replySuccess("\n");
            }

            event.replySuccess("__**Special Actions**__");
            for (i = 0; i < specArray.length();i++)
            {
                String result = "";
                result = specArray.get(i).toString();
                event.replySuccess(result.replace("\"","").replace("name:","**Name:** ").replace("desc:","\n*Desc:* ").replace(",","").replace("{","").replace("}",""));
                if (i == specArray.length())
                    event.replySuccess("\n");
            }
            //event.replySuccess("Hit Dice: " + );

            //see if there is a better way to modify the arrays.
        } catch (IOException e) {
            e.getMessage();
        }
    }
}

package group8_java.school_business_tour_management.dao;

import group8_java.school_business_tour_management.models.Account;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private static String filePath = "src/group8_java/school_business_tour_management/files/Account.json";

    public static List<Account> readFromFile() throws Exception {
        List<Account> data = new ArrayList<>();

        // Check if the file exists and is not empty
        if (Files.exists(Paths.get(filePath)) && Files.size(Paths.get(filePath)) > 0) {
            Reader reader = new FileReader(filePath);
            // Parse the JSON file
            JSONArray accountArray = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);

            // Iterate over the JSON array
            for (Object accObject : accountArray) {
                JSONObject accJson = (JSONObject) accObject;

                // Extract data from the JSON object
                int id = Integer.parseInt(accJson.get("id").toString());
                String username = (String) accJson.get("username");
                String password = (String) accJson.get("password");
                String role = (String) accJson.get("role");

                // Create Account object and add it to the list
                Account acc = new Account(id, username, password, role);
                data.add(acc);
            }
        }

        return data;
    }

    public static void writeToFile(List<Account> accounts) throws Exception {
        JSONArray accountArray = new JSONArray();

        // Convert each Account object to JSON and add it to the array
        for (Account acc : accounts) {
            JSONObject accJson = new JSONObject();
            accJson.put("id", acc.getId());
            accJson.put("username", acc.getUsername());
            accJson.put("password", acc.getPassword());
            accJson.put("role", acc.getRole());

            accountArray.add(accJson);
        }

        // Write the JSON array to the file
        try ( FileWriter file = new FileWriter(filePath)) {
            file.write(accountArray.toJSONString());
        }
    }
}

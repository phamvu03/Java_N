package group8_java.school_bussiness_tour_management.dao;

import group8_java.school_bussiness_tour_management.models.Company;
import group8_java.school_bussiness_tour_management.models.Tour;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private static String filePath = "src/group8_java/school_bussiness_tour_management/files/Company.json";

    public static List<Company> readFromFile() throws Exception {
        List<Company> data = new ArrayList<>();

        // Check if the file exists and is not empty
        Path path = Paths.get(filePath);
        if (Files.exists(path) && Files.size(path) > 0) {
            try ( Reader reader = new FileReader(filePath)) {
                // Parse the JSON file
                JSONArray companyArray = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);

                // Iterate over the JSON array
                for (Object companyObject : companyArray) {
                    JSONObject companyJson = (JSONObject) companyObject;

                    // Extract data from the JSON object
                    int id = Integer.parseInt(companyJson.get("id").toString());
                    String code = (String) companyJson.get("code");
                    String name = (String) companyJson.get("name");
                    String description = (String) companyJson.get("description");
                    String email = (String) companyJson.get("email");
                    String phoneNumber = (String) companyJson.get("phoneNumber");
                    String address = (String) companyJson.get("address");

                    // Create Company object and add it to the list
                    Company company = new Company(id, code, name, description, email, phoneNumber, address);

                    // Extract tours if present in JSON
                    JSONArray toursArray = (JSONArray) companyJson.get("tours");
                    if (toursArray != null) {
                        List<Tour> tours = new ArrayList<>();
                        for (Object tourObject : toursArray) {
                            JSONObject tourJson = (JSONObject) tourObject;
                            Tour tour = new Tour();
                            tour.setId(Integer.parseInt(tourJson.get("id").toString()));
                            tour.setCode((String) tourJson.get("code"));
                            tour.setName((String) tourJson.get("name"));
                            tour.setDescription((String) tourJson.get("description"));
                            tour.setStartDate((String) tourJson.get("startDate"));
                            tour.setAvailables(Integer.parseInt(tourJson.get("availables").toString()));
                            tour.setCompanyId(Integer.parseInt(tourJson.get("companyId").toString()));
                            tour.setTeacherId(Integer.parseInt(tourJson.get("teacherId").toString()));
                            tour.setPresentator((String) tourJson.get("presentator"));

                            // Add the tour to the list
                            tours.add(tour);
                        }
                        // Set the tours for the company
                        company.setTours(tours);
                    }

                    data.add(company);
                }
            } catch (Exception e) {
                throw new IOException("Error reading Company data from file", e);
            }
        }

        return data;
    }

    public static void writeToFile(List<Company> companies) throws Exception {
        JSONArray companyArray = new JSONArray();

        // Convert each Company object to JSON and add it to the array
        for (Company company : companies) {
            JSONObject companyJson = new JSONObject();
            companyJson.put("id", company.getId());
            companyJson.put("code", company.getCode());
            companyJson.put("name", company.getName());
            companyJson.put("description", company.getDescription());
            companyJson.put("email", company.getEmail());
            companyJson.put("phoneNumber", company.getPhoneNumber());
            companyJson.put("address", company.getAddress());

            // Convert List<Tour> to JSON array
            List<Tour> tours = company.getTours();
            if (tours != null && !tours.isEmpty()) {
                JSONArray toursArray = new JSONArray();
                for (Tour tour : tours) {
                    JSONObject tourJson = new JSONObject();
                    tourJson.put("id", tour.getId());
                    tourJson.put("code", tour.getCode());
                    tourJson.put("name", tour.getName());
                    tourJson.put("description", tour.getDescription());
                    tourJson.put("startDate", tour.getStartDate());
                    tourJson.put("availables", tour.getAvailables());
                    tourJson.put("companyId", tour.getCompanyId());
                    tourJson.put("teacherId", tour.getTeacherId());
                    tourJson.put("presentator", tour.getPresentator());

                    toursArray.add(tourJson);
                }
                companyJson.put("tours", toursArray);
            }

            companyArray.add(companyJson);
        }

        // Write the JSON array to the file
        try ( FileWriter file = new FileWriter(filePath)) {
            file.write(companyArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package group8_java.school_bussiness_tour_management.dao;

import group8_java.school_bussiness_tour_management.models.Teacher;
import group8_java.school_bussiness_tour_management.models.Tour;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author NGUYEN TRANG
 */
public class TeacherDAO {

    private static final String filePath = "src/group8_java/school_bussiness_tour_management/files/Teacher.json";

    public static List<Teacher> readFromFile() throws Exception {
        List<Teacher> data = new ArrayList<>();

        Path path = Paths.get(filePath);
        if (Files.exists(path) && Files.size(path) > 0) {
            try (Reader reader = new FileReader(filePath)) {
                JSONArray teacherArray = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);
                for (Object teacherObject : teacherArray) {
                    JSONObject teacherJson = (JSONObject) teacherObject;
                    int id = Integer.parseInt(teacherJson.get("id").toString());
                    String imagePath = (String)teacherJson.get("imagePath");
                    String code = (String) teacherJson.get("code");
                    String firstname = (String) teacherJson.get("firstname");
                    String lastname = (String) teacherJson.get("lastname");
                    String email = (String) teacherJson.get("email");
                    String address = (String) teacherJson.get("address");
                    String phone = (String) teacherJson.get("phoneNumber");
                    String birthDate = (String) teacherJson.get("birthDate");
                    int accountId = Integer.parseInt(teacherJson.get("accountId").toString());
                    Teacher tea = new Teacher(id,imagePath, code, firstname, lastname, address, phone, email, birthDate,accountId);
                    JSONArray toursArray = (JSONArray) teacherJson.get("tours");
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
                            tours.add(tour);
                        }
                        tea.setTours(tours);
                    }

                    data.add(tea);
                }
            } catch (Exception e) {
                throw new IOException("Error reading Company data from file", e);
            }
        }

        return data;
    }

    public static void writeToFile(List<Teacher> teachers) throws Exception {
       
        JSONArray teacherArray = new JSONArray();
        for (Teacher teacher : teachers) {
            JSONObject teacherJson = new JSONObject();
            teacherJson.put("id", teacher.getId());
            teacherJson.put("imagePath", teacher.getImagePath());
            teacherJson.put("code", teacher.getCode());
            teacherJson.put("firstname", teacher.getFirstName());
            teacherJson.put("lastname", teacher.getLastName());
            teacherJson.put("email", teacher.getEmail());
            teacherJson.put("phoneNumber", teacher.getPhoneNumber());
            teacherJson.put("address", teacher.getAddress());
            teacherJson.put("birthDate", teacher.getBirthDate());
            teacherJson.put("accountId", teacher.getAccountId());
            List<Tour> tours = teacher.getTours();
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
                teacherJson.put("tours", toursArray);
            }

            teacherArray.add(teacherJson);
        }

        try ( FileWriter file = new FileWriter(filePath)) {
            file.write(teacherArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}

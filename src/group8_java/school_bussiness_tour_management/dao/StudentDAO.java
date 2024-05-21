package group8_java.school_bussiness_tour_management.dao;

import group8_java.school_bussiness_tour_management.models.Student;
import group8_java.school_bussiness_tour_management.models.StudentTour;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String filePath = "src/group8_java/school_bussiness_tour_management/files/Student.json";

    public static List<Student> readFromFile() throws IOException {
        List<Student> data = new ArrayList<>();

        Path path = Paths.get(filePath);
        if (Files.exists(path) && Files.size(path) > 0) {
            try {
                JSONParser parser = new JSONParser();
                JSONArray studentArray = (JSONArray) parser.parse(Files.newBufferedReader(path));

                for (Object studentObject : studentArray) {
                    JSONObject studentJson = (JSONObject) studentObject;

                    int id = getInteger(studentJson, "id");
                    String imagePath = getString(studentJson, "imagePath");
                    String code = getString(studentJson, "code");
                    String username = getString(studentJson, "username");
                    String password = getString(studentJson, "password");
                    String role = getString(studentJson, "role");
                    String firstName = getString(studentJson, "firstName");
                    String lastName = getString(studentJson, "lastName");
                    String address = getString(studentJson, "address");
                    String phoneNumber = getString(studentJson, "phoneNumber");
                    String email = getString(studentJson, "email");
                    String birthDate = getString(studentJson, "birthDate");
                    int classId = getInteger(studentJson, "classId");
                    int accountId = getInteger(studentJson, "accountId");

                    Student student = new Student(id, imagePath, code, firstName, lastName, address, phoneNumber, email, birthDate, classId);
                    student.setAccountId(accountId);
                    
                    JSONArray studentToursArray = (JSONArray) studentJson.get("studentTours");
                    if (studentToursArray != null) {
                        List<StudentTour> studentTours = new ArrayList<>();
                        for (Object studentTourObject : studentToursArray) {
                            JSONObject studentTourJson = (JSONObject) studentTourObject;

                            StudentTour studentTour = new StudentTour();
                            studentTour.setStudentId(getInteger(studentTourJson, "studentId"));
                            studentTour.setTourId(getInteger(studentTourJson, "tourId"));
                            studentTour.setRate(getInteger(studentTourJson, "rate"));

                            studentTours.add(studentTour);
                        }
                        student.setStudentTours(studentTours);
                    }

                    data.add(student);
                }
            } catch (Exception e) {
                throw new IOException("Error reading Student data from file", e);
            }
        }
        return data;
    }

    public static void writeToFile(List<Student> students) throws IOException {
        JSONArray studentArray = new JSONArray();

        for (Student student : students) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("id", student.getId());
            studentJson.put("imagePath", student.getImagePath());
            studentJson.put("code", student.getCode());
            studentJson.put("firstName", student.getFirstName());
            studentJson.put("lastName", student.getLastName());
            studentJson.put("address", student.getAddress());
            studentJson.put("phoneNumber", student.getPhoneNumber());
            studentJson.put("email", student.getEmail());
            studentJson.put("birthDate", student.getBirthDate());
            studentJson.put("classId", student.getClassId());
            studentJson.put("accountId", student.getAccountId());

            List<StudentTour> studentTours = student.getStudentTours();
            if (studentTours != null && !studentTours.isEmpty()) {
                JSONArray studentToursArray = new JSONArray();
                for (StudentTour studentTour : studentTours) {
                    JSONObject studentTourJson = new JSONObject();
                    studentTourJson.put("studentId", studentTour.getStudentId());
                    studentTourJson.put("tourId", studentTour.getTourId());
                    studentTourJson.put("rate", studentTour.getRate());

                    studentToursArray.add(studentTourJson);
                }
                studentJson.put("studentTours", studentToursArray);
            } else {
                studentJson.put("studentTours", new JSONArray());
            }
            studentArray.add(studentJson);
        }

        try {
            Files.write(Paths.get(filePath), studentArray.toJSONString().getBytes());
        } catch (IOException e) {
            throw new IOException("Error writing Student data to file", e);
        }
    }

    private static String getString(JSONObject jsonObject, String key) {
        return (jsonObject.get(key) != null) ? jsonObject.get(key).toString() : "";
    }

    private static int getInteger(JSONObject jsonObject, String key) {
        return (jsonObject.get(key) != null) ? Integer.parseInt(jsonObject.get(key).toString()) : 0;
    }
}

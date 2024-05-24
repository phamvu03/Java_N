package group8_java.school_business_tour_management.dao;

import group8_java.school_business_tour_management.models.Classroom;
import group8_java.school_business_tour_management.models.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//reset dao
public class ClassroomDAO {

    private static String filePath = "src/group8_java/school_business_tour_management/files/Classroom.json";

    public static List<Classroom> readFromFile() throws Exception {
        List<Classroom> data = new ArrayList<>();

        // Check if the file exists and is not empty
        if (Files.exists(Paths.get(filePath)) && Files.size(Paths.get(filePath)) > 0) {
            try (Reader reader = new FileReader(filePath)) {
                // Parse the JSON file
                JSONArray classroomArray = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);
                // Iterate over the JSON array
                for (Object classroomObject : classroomArray) {
                    JSONObject classroomJson = (JSONObject) classroomObject;

                    // Extract data from the JSON object
                    int id = Integer.parseInt(classroomJson.get("id").toString());
                    String code = (String) classroomJson.get("code");
                    String name = (String) classroomJson.get("name");

                    // Create Account object and add it to the list
                    Classroom classroom = new Classroom(id, code, name);
                    JSONArray studentClassroomsArray = (JSONArray) classroomJson.get("students");
                    if (studentClassroomsArray != null) {
                        List<Student> students = new ArrayList<>();
                        for (Object studentObject : studentClassroomsArray) {
                            JSONObject studentJson = (JSONObject) studentObject;
                            Student student = new Student();
                            student.setId(Integer.parseInt(studentJson.get("id").toString()));
                            student.setFirstName((String) studentJson.get("firstName"));
                            student.setLastName((String) studentJson.get("lastName"));
                            students.add(student);
                            classroom.setStudents(students);
                        }
                    }
                    data.add(classroom);
                }
            } catch (Exception e) {
                throw new IOException("Error reading Company data from file", e);
            }
        }

        return data;
    }

    public static void writeToFile(List<Classroom> classrooms) throws Exception {
        JSONArray classroomArray = new JSONArray();
        for (Classroom item : classrooms) {
            JSONObject itemJson = new JSONObject();
            itemJson.put("id", item.getId());
            itemJson.put("code", item.getCode());
            itemJson.put("name", item.getName());

            List<Student> students = item.getStudents();
            if (students != null && !students.isEmpty()) {
                JSONArray studentsArray = new JSONArray();
                for (Student student : students) {
                    JSONObject studentJson = new JSONObject();
                    studentJson.put("id", student.getId());
                    studentJson.put("firstName", student.getFirstName());
                    studentJson.put("lastName", student.getLastName());

                    studentsArray.add(studentJson);
                }
                itemJson.put("students", studentsArray);
            }
            classroomArray.add(itemJson);
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(classroomArray.toJSONString());
        }
    }

}

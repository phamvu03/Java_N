package group8_java.school_bussiness_tour_management.dao;

import group8_java.school_bussiness_tour_management.models.StudentTour;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentTourDAO {

    private static final String filePath = "src/group8_java/school_bussiness_tour_management/files/StudentTour.json";

    public static List<StudentTour> readFromFile() throws IOException {
        List<StudentTour> data = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (Files.exists(path) && Files.size(path) > 0) {
            try (Reader reader = new FileReader(filePath)) {
                JSONArray studentTourArray = (JSONArray) new JSONParser().parse(reader);

                for (Object studentTourObject : studentTourArray) {
                    JSONObject studentTourJson = (JSONObject) studentTourObject;

                    int studentId = Integer.parseInt(studentTourJson.get("studentId").toString());
                    int tourId = Integer.parseInt(studentTourJson.get("tourId").toString());
                    int rate = Integer.parseInt(studentTourJson.get("rate").toString());

                    StudentTour studentTour = new StudentTour(studentId, tourId, rate);
                    data.add(studentTour);
                }
            } catch (Exception e) {
                throw new IOException("Error reading StudentTour data from file", e);
            }
        }
        return data;
    }

    public static void writeToFile(List<StudentTour> studentTours) throws IOException {
        JSONArray studentTourArray = new JSONArray();

        for (StudentTour studentTour : studentTours) {
            JSONObject studentTourJson = new JSONObject();
            studentTourJson.put("studentId", studentTour.getStudentId());
            studentTourJson.put("tourId", studentTour.getTourId());
            studentTourJson.put("rate", studentTour.getRate());

            studentTourArray.add(studentTourJson);
        }

        try (Writer writer = new FileWriter(filePath)) {
            writer.write(studentTourArray.toJSONString());
        } catch (IOException e) {
            throw new IOException("Error writing StudentTour data to file", e);
        }
    }
}

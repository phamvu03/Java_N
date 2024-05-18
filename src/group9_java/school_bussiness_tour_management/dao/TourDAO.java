/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group7_java.school_bussiness_tour_management.dao;

import group7_java.school_bussiness_tour_management.models.Student;
import group7_java.school_bussiness_tour_management.models.Tour;
import group7_java.school_bussiness_tour_management.models.StudentTour;
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

/**
 *
 * @author gialo
 */
public class TourDAO {

    private static String filePath = "src/group9_java/school_bussiness_tour_management/files/Tour.json";

    public static List<Tour> readFromFile() throws Exception {
        List<Tour> data = new ArrayList<>();

        Path path = Paths.get(filePath);
        if (Files.exists(path) && Files.size(path) > 0) {
            try (Reader reader = new FileReader(filePath)) {
                JSONArray tourArray = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);

                for (Object tourObject : tourArray) {
                    JSONObject tourJson = (JSONObject) tourObject;

                    int id = Integer.parseInt(tourJson.get("id").toString());
                    String code = tourJson.get("code").toString();
                    String name = tourJson.get("name").toString();
                    String description = tourJson.get("description").toString();
                    String startDate = tourJson.get("startDate").toString();
                    int availables = Integer.parseInt(tourJson.get("availables").toString());
                    int companyId = Integer.parseInt(tourJson.get("companyId").toString());
                    int teacherId = Integer.parseInt(tourJson.get("teacherId").toString());
                    String presentator = tourJson.get("presentator").toString();

                    Tour tour = new Tour(id, code, name, description, startDate, availables, companyId, teacherId,
                            presentator);

                    JSONArray studentToursArray = (JSONArray) tourJson.get("studentTours");
                    if (studentToursArray != null) {
                        List<StudentTour> studentTours = new ArrayList<>();
                        for (Object studentTourObject : studentToursArray) {
                            JSONObject studentTourJson = (JSONObject) studentTourObject;
                            StudentTour studentTour = new StudentTour();
                            studentTour.setStudentId(Integer.parseInt(studentTourJson.get("studentId").toString()));
                            studentTour.setTourId(Integer.parseInt(studentTourJson.get("tourId").toString()));
                            studentTour.setRate(Integer.parseInt(studentTourJson.get("rate").toString()));

                            studentTours.add(studentTour);
                        }
                        tour.setStudentTours(studentTours);
                    }
                    data.add(tour);
                }
            } catch (Exception e) {
                throw new IOException("Error reading Company data from file", e);
            }
        }
        return data;
    }

    public static void writeToFile(List<Tour> tours) throws Exception {
        JSONArray tourArray = new JSONArray();

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

            List<StudentTour> studentTours = tour.getStudentTours();
            if (tour.getId() == 5) {
                System.out.println("studenttours before store in json: " + studentTours);
            }
            if (studentTours != null && !studentTours.isEmpty()) {
                JSONArray studentToursArray = new JSONArray();
                for (StudentTour studentTour : studentTours) {
                    JSONObject studentTourJson = new JSONObject();
                    studentTourJson.put("studentId", studentTour.getStudentId());
                    studentTourJson.put("tourId", studentTour.getTourId());
                    studentTourJson.put("rate", studentTour.getRate());
                    studentToursArray.add(studentTourJson);
                }
                tourJson.put("studentTours", studentToursArray);
            }
            tourArray.add(tourJson);
        }

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(tourArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

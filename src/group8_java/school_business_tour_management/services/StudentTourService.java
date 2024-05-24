/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group8_java.school_business_tour_management.services;

import group8_java.school_business_tour_management.common.MessageDialog;
import group8_java.school_business_tour_management.dao.StudentDAO;
import group8_java.school_business_tour_management.dao.StudentTourDAO;
import group8_java.school_business_tour_management.dao.TourDAO;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.models.StudentTour;
import group8_java.school_business_tour_management.models.Tour;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gialo
 */
public class StudentTourService {

    public static List<Tour> getToursForStudent(int studentId) throws Exception {
        List<Tour> toursForStudent = new ArrayList<>();
        List<StudentTour> studentTours = StudentTourDAO.readFromFile();
        for (StudentTour studentTour : studentTours) {
            if (studentTour.getStudentId() == studentId) {
                int tourId = studentTour.getTourId();
                Tour tour = TourService.getTourById(tourId);
                if (tour != null) {
                    toursForStudent.add(tour);
                }
            }
        }
        return toursForStudent;
    }

    public static List<StudentTour> getStudentTour(int studentId) throws Exception {
        List<StudentTour> studentTour = new ArrayList<>();
        List<StudentTour> student = StudentTourDAO.readFromFile();
        for (StudentTour stu : student) {
            if (stu.getStudentId() == studentId) {
                studentTour.add(stu);
            }
        }
        return studentTour;
    }

    public static void deleteStudentTour(int studentId, int tourId) throws Exception {
        StudentTour needDelete = new StudentTour(studentId, tourId, 0);

        List<StudentTour> studentTourData = StudentTourDAO.readFromFile();
        StudentTour willDelete = null;
        for (StudentTour current : studentTourData) {
            if (current.getStudentId() == needDelete.getStudentId() && current.getTourId() == needDelete.getTourId()) {
                willDelete = current;
                break;
            }
        }
        if (willDelete != null) {
            studentTourData.remove(willDelete);
            StudentTourDAO.writeToFile(studentTourData);
        }

        willDelete = null;
        List<Student> studentData = StudentDAO.readFromFile();
        for (Student student : studentData) {
            if (student.getId() == needDelete.getStudentId()) {
                if (student.getStudentTours() == null) {
                    student.setStudentTours(new ArrayList<>());
                }
                for (StudentTour current : student.getStudentTours()) {
                    if (current.getStudentId() == needDelete.getStudentId() && current.getTourId() == needDelete.getTourId()) {
                        willDelete = current;
                        break;
                    }
                }
                if (willDelete != null) {
                    student.getStudentTours().remove(willDelete);
                    break;
                }
            }
            if (willDelete != null) {
                break;
            }
        }
        StudentDAO.writeToFile(studentData);

        willDelete = null;
        List<Tour> tourData = TourDAO.readFromFile();
        for (Tour tour : tourData) {
            if (tour.getId() == needDelete.getTourId()) {
                if (tour.getStudentTours() == null) {
                    tour.setStudentTours(new ArrayList<>());
                }
                for (StudentTour current : tour.getStudentTours()) {
                    if (current.getStudentId() == needDelete.getStudentId() && current.getTourId() == needDelete.getTourId()) {
                        willDelete = current;
                        break;
                    }
                }
                if (willDelete != null) {
                    tour.getStudentTours().remove(willDelete);
                    break;
                }
            }
            if (willDelete != null) {
                break;
            }
        }
        TourDAO.writeToFile(tourData);

    }

    public static void updateStudentTour(int studentId, int tourId, int rate) throws Exception {
        List<StudentTour> studentTourData = StudentTourDAO.readFromFile();
        for (int i = 0; i < studentTourData.size(); i++) {
            if (studentTourData.get(i).getStudentId() == studentId && studentTourData.get(i).getTourId() == tourId) {
                studentTourData.get(i).setRate(rate);
                break;
            }
        }
        StudentTourDAO.writeToFile(studentTourData);

        List<Tour> tourData = new TourDAO().readFromFile();
        for (int i = 0; i < tourData.size(); i++) {
            if (tourData.get(i).getId() == tourId) {
                for (int j = 0; j < tourData.get(i).getStudentTours().size(); j++) {
                    if (tourData.get(i).getStudentTours().get(j).getStudentId() == studentId) {
                        tourData.get(i).getStudentTours().get(j).setRate(rate);
                        break;
                    }
                }
            }
        }
        TourDAO.writeToFile(tourData);

        List<Student> studentData = StudentDAO.readFromFile();
        for (int i = 0; i < studentData.size(); i++) {
            if (studentData.get(i).getId() == studentId) {
                for (int j = 0; j < studentData.get(i).getStudentTours().size(); j++) {
                    if (studentData.get(i).getStudentTours().get(j).getTourId() == tourId) {
                        studentData.get(i).getStudentTours().get(j).setRate(rate);
                        break;
                    }
                }
            }
        }
        StudentDAO.writeToFile(studentData);
    }

    public static StudentTour getByStudentIdAndTourId(int studentId, int tourId) throws Exception {
        List<StudentTour> studentTourData = new ArrayList<>();
        for (int i = 0; i < studentTourData.size(); i++) {
            if (studentTourData.get(i).getStudentId() == studentId && studentTourData.get(i).getTourId() == tourId) {
                return studentTourData.get(i);
            }
        }
        return null;
    }

    public static boolean isExistRegisteredTour(int studentId, int tourId) throws Exception {
        List<StudentTour> studentTour_data = StudentTourDAO.readFromFile();
        for (StudentTour studentTour : studentTour_data) {
            if (studentTour.getStudentId() == studentId && studentTour.getTourId() == tourId) {
                return false;
            }
        }
        return true;
    }
    
    public static StudentTour getStudentTourByStudenIdAndTourId(int studentId, int tourId)throws Exception{
        List<StudentTour> studentTours = getStudentTour(studentId);
        for(StudentTour studentTour : studentTours){
            if(studentTour.getStudentId() == studentId && studentTour.getTourId() == tourId){
                return studentTour;
            }
        }
        return null;
    }
}

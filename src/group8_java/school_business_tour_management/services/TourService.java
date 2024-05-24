/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group8_java.school_business_tour_management.services;

import group8_java.school_business_tour_management.common.MessageDialog;
import group8_java.school_business_tour_management.dao.CompanyDAO;
import group8_java.school_business_tour_management.dao.StudentDAO;
import group8_java.school_business_tour_management.dao.TeacherDAO;
import group8_java.school_business_tour_management.dao.TourDAO;
import group8_java.school_business_tour_management.models.Company;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.models.Teacher;
import group8_java.school_business_tour_management.models.Tour;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gialo
 */
public class TourService {
  public static Tour getByTourCode(String code) throws Exception {
        List<Tour> tours = TourDAO.readFromFile();
        for (Tour tour : tours) {
            if (tour.getCode().equals(code)) {
                return tour;
            }
        }
        return null;
    }
    public static Tour getByIdFromList(int tourId, List<Tour> data) {
        for (Tour tour : data) {
            if (tour.getId() == tourId) {
                return tour;
            }
        }
        return null;
    }

    public static Tour getById(int tourId) throws Exception {
        List<Tour> data = TourDAO.readFromFile();
        for (Tour tour : data) {
            if (tour.getId() == tourId) {
                return tour;
            }
        }
        return null;
    }

    public static boolean isExistedStudentCode(String student_code) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        for (Student stu : data) {
            if (stu.getCode().trim().equals(student_code.trim())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistedTourCode(String tourCode) throws Exception {
        List<Tour> data = TourDAO.readFromFile();
        for (Tour tour : data) {
            if (tour.getCode().trim().equals(tourCode.trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<Student> getAllStudents() throws Exception {
        return StudentDAO.readFromFile();
    }

    public static List<Tour> getAllTours() throws Exception {
        return TourDAO.readFromFile();
    }

    public static List<Tour> getAllToursByCompanyId(int companyId) throws Exception {
        List<Tour> data = TourDAO.readFromFile();
        List<Tour> tourByComId = new ArrayList<>();
        for (Tour tour : data) {
            if (tour.getCompanyId() == companyId) {
                tourByComId.add(tour);
            }
        }
        return tourByComId;
    }

    public static int getLastTourId() throws Exception {
        List<Tour> data = TourDAO.readFromFile();
        if (data != null) {
            if (data.size() == 0) {
                return 0;
            }
            return data.get(data.size() - 1).getId();
        }
        return -1;
    }

    public static Tour getTourByIndex(int index) throws Exception {
        return TourDAO.readFromFile().get(index);
    }

    public static Tour getTourById(int index) throws Exception {
        List<Tour> data = TourDAO.readFromFile();
        if (data != null) {
            for (Tour item : data) {
                if (item.getId() == index) {
                    return item;
                }
            }
        }
        return null;
    }

    public static void createNewTour(String code, String name, String description, String startDate, int availables, int companyId, int teacherId, String presentator) throws Exception {
        int lastId = getLastTourId();
        lastId++;
        Tour tour = new Tour(lastId, code, name, description, startDate, availables, companyId, teacherId, presentator);
        List<Tour> tour_data = TourDAO.readFromFile();
        List<Company> company_data = CompanyDAO.readFromFile();
        List<Teacher> teacher_data = TeacherDAO.readFromFile();
        for (Company comp : company_data) {
            if (comp.getId() == companyId) {
                if (comp.getTours() == null) {
                    comp.setTours(new ArrayList<>()); // Khởi tạo danh sách nếu là null
                }
                comp.getTours().add(tour);
            }
        }
        for (Teacher tea : teacher_data) {
            if (tea.getId() == teacherId) {
                if (tea.getTours() == null) {
                    tea.setTours(new ArrayList<>()); // Khởi tạo danh sách nếu là null
                }
                tea.getTours().add(tour);
            }
        }
        tour_data.add(tour);
        TourDAO.writeToFile(tour_data);
        CompanyDAO.writeToFile(company_data);
        TeacherDAO.writeToFile(teacher_data);
    }

    public static void updateTour(Tour uTour) throws Exception {
        List<Tour> tour_data = TourDAO.readFromFile();
        List<Company> company_data = CompanyDAO.readFromFile();
        List<Teacher> teacher_data = TeacherDAO.readFromFile();
        
        for(Tour tour : tour_data) {
            if(tour.getId() == uTour.getId()) {
                if(tour.getCompanyId() == uTour.getCompanyId()) {
                    for (Company comp : company_data) {
                        if (comp.getTours() == null) {
                            comp.setTours(new ArrayList<>()); // Khởi tạo danh sách nếu là null
                        }
                        for (Tour comp_tour : comp.getTours()) {
                            if (comp_tour.getId() == uTour.getId()) {
                                comp_tour.setCode(uTour.getCode());
                                comp_tour.setName(uTour.getName());
                                comp_tour.setDescription(uTour.getDescription());
                                comp_tour.setStartDate(uTour.getStartDate());
                                comp_tour.setAvailables(uTour.getAvailables());
                                comp_tour.setCompanyId(uTour.getCompanyId());
                                comp_tour.setTeacherId(uTour.getTeacherId());
                                comp_tour.setPresentator(uTour.getPresentator());
                            }
                        }
                    }
                }else{
                    for(Company comp : company_data) {
                        if (comp.getTours() == null) {
                            comp.setTours(new ArrayList<>()); // Khởi tạo danh sách nếu là null
                        }
                        if(comp.getId() == uTour.getCompanyId()) {
                            comp.getTours().add(uTour);
                        }
                        if(comp.getId() == tour.getCompanyId()) {
                            comp.getTours().remove(tour);
                        }
                    }
                }
            }
        }

        for (Tour tour : tour_data) {
            if (tour.getId() == uTour.getId()) {
                tour.setCode(uTour.getCode());
                tour.setName(uTour.getName());
                tour.setDescription(uTour.getDescription());
                tour.setStartDate(uTour.getStartDate());
                tour.setAvailables(uTour.getAvailables());
                tour.setCompanyId(uTour.getCompanyId());
                tour.setTeacherId(uTour.getTeacherId());
                tour.setPresentator(uTour.getPresentator());
            }
        }
        
        
        boolean tourUpdated = false;

        for (Teacher tea : teacher_data) {
            if (tea.getTours() == null) {
                tea.setTours(new ArrayList<>()); // Khởi tạo danh sách nếu là null
            }

            if (tea.getId() == uTour.getTeacherId()) {
                for (Tour tea_tour : tea.getTours()) {
                    if (tea_tour.getId() == uTour.getId()) {
                        tea_tour.setCode(uTour.getCode());
                        tea_tour.setName(uTour.getName());
                        tea_tour.setDescription(uTour.getDescription());
                        tea_tour.setStartDate(uTour.getStartDate());
                        tea_tour.setAvailables(uTour.getAvailables());
                        tea_tour.setCompanyId(uTour.getCompanyId());
                        tea_tour.setTeacherId(uTour.getTeacherId());
                        tea_tour.setPresentator(uTour.getPresentator());
                        tourUpdated = true;
                        break; // Kết thúc vòng lặp nếu đã cập nhật tour
                    }
                }

                if (!tourUpdated) {
                    tea.getTours().add(uTour);
                }
            } else {
                Iterator<Tour> iterator = tea.getTours().iterator();
                if(iterator != null)
                {
                    while (iterator.hasNext()) {
                        Tour tea_tour = iterator.next();
                        if (tea_tour.getId() == uTour.getId()) {
                            iterator.remove();
                            tourUpdated = true;
                            break; // Kết thúc vòng lặp nếu đã xóa tour
                        }
                    }
                }
            }
        }
        TourDAO.writeToFile(tour_data);
        CompanyDAO.writeToFile(company_data);
        TeacherDAO.writeToFile(teacher_data);
    }

    public static void deleteTour(int tourId) throws Exception {
        List<Tour> tour_data = TourDAO.readFromFile();
        List<Company> company_data = CompanyDAO.readFromFile();
        List<Teacher> teacher_data = TeacherDAO.readFromFile();
        Tour delTour = null;
        for (Tour tour : tour_data) {
            if (tour.getId() == tourId) {
                delTour = tour;
                break;
            }
        }
        if (delTour != null) {
            tour_data.remove(delTour);
            for (Company comp : company_data) {
                if (comp.getId() == delTour.getCompanyId()) {
                    comp.getTours().remove(delTour);
                    break;
                }
            }
           for (Teacher tea : teacher_data) {
            if (tea.getId() == delTour.getTeacherId()) {
                Iterator<Tour> teacherTourIterator = tea.getTours().iterator();
                while (teacherTourIterator.hasNext()) {
                    Tour item = teacherTourIterator.next();
                    if (item.getId() == delTour.getId()) {
                        teacherTourIterator.remove();
                        break; // Kết thúc vòng lặp sau khi xóa
                    }
                }
                break; // Kết thúc vòng lặp ngoài cùng
            }
        }
            CompanyDAO.writeToFile(company_data);
            TourDAO.writeToFile(tour_data);
            TeacherDAO.writeToFile(teacher_data);
        }
    }

     public static List<Tour> getComingTourByStudentId(int studentId) throws Exception {
        List<Tour> comingTours = new ArrayList<>();
        List<Tour> tour_data = TourDAO.readFromFile();
        List<Tour> registeredTours = StudentTourService.getToursForStudent(studentId);

        // Định dạng của chuỗi ngày trong JSON
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();

        for (Tour tour : tour_data) {
            String date = tour.getStartDate();
            LocalDate startDate = LocalDate.parse(date, formatter);
            for (Tour registeredTour : registeredTours) {
                if (tour.getId() != registeredTour.getId() && startDate.isAfter(currentDate)) {
                    comingTours.add(tour);
                }

            }
        }

        return comingTours;
    }
     
}

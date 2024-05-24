package group8_java.school_business_tour_management.services;

import static group8_java.school_business_tour_management.common.Validator.formatName;
import group8_java.school_business_tour_management.dao.AccountDAO;
import group8_java.school_business_tour_management.dao.CompanyDAO;
import group8_java.school_business_tour_management.dao.TeacherDAO;
import group8_java.school_business_tour_management.dao.TourDAO;
import group8_java.school_business_tour_management.models.Account;
import group8_java.school_business_tour_management.models.Company;
import group8_java.school_business_tour_management.models.StudentTour;
import group8_java.school_business_tour_management.models.Teacher;
import group8_java.school_business_tour_management.models.Tour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherService {

    public static String LastName(String name) {
        String[] splitName = formatName(name).split(" ");
        String lastName = "";
        for (int i = 0; i < splitName.length - 1; i++) {
            lastName += (splitName[i] + " ");
        }
        return lastName.trim();
    }

    public static String FirstName(String name) {
        String[] splitName = formatName(name).split(" ");
        return splitName[splitName.length - 1].trim();
    }

    public static List<Teacher> getAllTeachers() throws Exception {
        return TeacherDAO.readFromFile();
    }

    public static Teacher getTeacherById(int index) throws Exception {
        List<Teacher> data = TeacherDAO.readFromFile();
        if (data != null) {
            for (Teacher item : data) {
                if (item.getId() == index) {
                    return item;
                }
            }
        }
        return null;
    }

    public static boolean isCheckCodeTeacher(String newCode) throws Exception {
        List<Teacher> teachers = TeacherDAO.readFromFile();
        for (Teacher next : teachers) {
            if (next.getCode().equalsIgnoreCase(newCode)) {
                return true;
            }
        }
        return false;
    }

    public static int getLastTeacherId() throws Exception {
        List<Teacher> data = TeacherDAO.readFromFile();
        if (data != null) {
            if (data.size() == 0) {
                return 0;
            }
            return data.get(data.size() - 1).getId();
        }
        return -1;
    }

    public static int getLastAccountId() throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        if (data != null) {
            if (data.size() == 0) {
                return 0;
            }
            return data.get(data.size() - 1).getId();
        }
        return -1;
    }

    public static void createNewTeacher(String imagePath, String code, String name, String address, String phoneNumber, String email, String birthDate) throws Exception {
        if (!isCheckCodeTeacher(code)) {
            int lastId = getLastTeacherId();
            int id = ++lastId;
            int lastAccountId = getLastAccountId();
            int accountId = ++lastAccountId;
            List<Teacher> data = TeacherDAO.readFromFile();
            Teacher tea = new Teacher(id, imagePath, code, FirstName(name), LastName(name), address, phoneNumber, email, birthDate, accountId);
            data.add(tea);
            Account acc = new Account(accountId, code, code, "Tài khoản giáo viên");
            List<Account> dataAccount = AccountDAO.readFromFile();
            dataAccount.add(acc);
            AccountDAO.writeToFile(dataAccount);
            TeacherDAO.writeToFile(data);
        }
    }

    public static Teacher getTeacherByIndex(int index) throws Exception {
        return TeacherDAO.readFromFile().get(index);
    }

    public static void updateTeacher(Teacher teacher) throws Exception {
        List<Teacher> data = TeacherDAO.readFromFile();

        for (Teacher tea : data) {
            if (tea.getId() == teacher.getId()) {
                tea.setCode(teacher.getCode());
                tea.setFirstName(teacher.getFirstName());
                tea.setLastName(teacher.getLastName());
                tea.setPhoneNumber(teacher.getPhoneNumber());
                tea.setBirthDate(teacher.getBirthDate());
                tea.setEmail(teacher.getEmail());
                tea.setAddress(teacher.getAddress());
                tea.setImagePath(teacher.getImagePath());
                tea.setAccountId(teacher.getAccountId());
                break;
            }
        }

        TeacherDAO.writeToFile(data);
    }

    public static void deleteTeacher(int teacherId) throws Exception {
        List<Teacher> data = TeacherDAO.readFromFile();
        List<Tour> data_tours = TourDAO.readFromFile();
        List<Account> data_accounts = AccountDAO.readFromFile();
        Teacher delTea = null;
        for (Teacher tea : data) {
            if (tea.getId() == teacherId) {
                delTea = tea;
                break;
            }
        }
        if (delTea != null) {
            for (Tour item : data_tours) {
                if (item.getTeacherId() == teacherId) {
                    item.setTeacherId(teacherId - 100);
                }
                break;
            }
            Iterator<Account> iterator = data_accounts.iterator();
            while (iterator.hasNext()) {
                Account itemAccount = iterator.next();
                if (itemAccount.getId() == delTea.getAccountId()) {
                    iterator.remove();
                }
            }
            data.remove(delTea);
            TeacherDAO.writeToFile(data);
            TourDAO.writeToFile(data_tours);
            AccountDAO.writeToFile(data_accounts);

        }
    }

    public static List listToursOfTeacher(int teacherId) throws Exception {
        List<Tour> data = TourDAO.readFromFile();
        List<Tour> teacherTours = new ArrayList<>();

        for (Tour tourItem : data) {
            if (tourItem.getTeacherId() == teacherId) {
                teacherTours.add(tourItem);
            }
        }

        if (!teacherTours.isEmpty()) {
            return teacherTours;
        }
        return null;
    }

    public static String getNameCompanyFromIdCompany(int companyID) throws Exception {
        String name = "";
        List<Company> data = CompanyDAO.readFromFile();
        for (Company com : data) {
            if (com.getId() == companyID) {
                name = com.getName();
                break;
            }
        }
        return name;
    }

    public static int getNumberOfStudents(List<StudentTour> students) throws IOException {
        if (students != null) {
            if (!students.isEmpty()) {
                return students.size();
            }
        }
        return 0;
    }

    public static String getTeacherNameById(int teacherId) throws Exception {
        List<Teacher> teachers = TeacherDAO.readFromFile();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                return teacher.getLastName() + " " + teacher.getFirstName();
            }
        }
        return null;
    }

    public static Teacher getTeacherByAccountId(int accountId) throws Exception {
        List<Teacher> data = TeacherDAO.readFromFile();
        for (Teacher teacher : data) {
            if (teacher.getAccountId() == accountId) {
                return teacher;
            }
        }
        return null;
    }
    
}

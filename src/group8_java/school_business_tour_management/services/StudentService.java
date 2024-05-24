package group8_java.school_business_tour_management.services;

import group8_java.school_business_tour_management.dao.StudentDAO;
import group8_java.school_business_tour_management.dao.StudentTourDAO;
import group8_java.school_business_tour_management.models.Account;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.models.StudentTour;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public static boolean isExistedStudentCode(String student_code) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        for (Student stu : data) {
            if (stu.getCode().trim().equals(student_code.trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<Student> getAllStudents() throws Exception {
        return StudentDAO.readFromFile();
    }

    public static int getLastStudentId() throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        if (data != null) {
            if (data.size() == 0) {
                return 0;
            }
            return data.get(data.size() - 1).getId();
        }
        return -1;
    }

    public static Student getStudentByIndex(int index) throws Exception {
        return StudentDAO.readFromFile().get(index);
    }

    public static List<Student> getStudentByClassId(int classId) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        List<Student> trueData = new ArrayList<Student>();
        if (data != null) {
            for (Student stu : data) {
                // sua lai kieu du lieu cua student classId thanh string
                if (stu.getClassId() == classId) {
                    trueData.add(stu);
                }
            }
        }
        return trueData;
    }

    public static void createNewStudent(String code, String imagePath, String firstName, String lastName, String address, String phoneNumber, String email, String birthDate, int classId) throws Exception {
//        int lastId = getLastStudentId();
//        lastId++;
//        Student student = new Student(lastId, imagePath, code, firstName, lastName, address, phoneNumber, email, birthDate, classId);
//        List<Student> data = StudentDAO.readFromFile();
//        Account studentAccount = AccountService.createNewAccount(code, code, "Tài khoản sinh viên");
//        student.setAccountId(studentAccount.getId());
//        data.add(student);
//        StudentDAO.writeToFile(data);
    }

    public static Student createStudentAttachToAccount(int accountId) throws Exception {
        int lastId = getLastStudentId();
        lastId++;
        Student student = new Student(lastId);
        student.setFirstName("SV chưa nhập");
        List<Student> data = StudentDAO.readFromFile();
        student.setAccountId(accountId);
        data.add(student);
        StudentDAO.writeToFile(data);
        return student;
    }

    public static void updateStudent(Student student) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        for (Student stu : data) {
            if (stu.getCode().equals(student.getCode())) {
                stu.setCode(student.getCode());
                stu.setFirstName(student.getFirstName());
                stu.setLastName(student.getLastName());
                stu.setAddress(student.getAddress());
                stu.setPhoneNumber(student.getPhoneNumber());
                stu.setEmail(student.getEmail());
                stu.setBirthDate(student.getBirthDate());
                stu.setImagePath(student.getImagePath());
                stu.setClassId(student.getClassId());

                break;
            }
        }
        StudentDAO.writeToFile(data);
    }

    public static void deleteStudent(int studentId) throws Exception {
//        List<Student> data = StudentDAO.readFromFile();
//        Student delStu = null;
//        for (Student stu : data) {
//            if (stu.getId() == studentId) {
//                delStu = stu;
//                break;
//            }
//        }
//        if (delStu != null) {
//            AccountService.deleteAccount(delStu.getAccountId());
//            data.remove(delStu);
//            StudentDAO.writeToFile(data);
//        }
    }

    public static Student getById(int studentId) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        for (Student student : data) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Student getByCode(String code) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        for (Student student : data) {
            if (student.getCode().equals(code.trim())) {
                return student;
            }
        }
        return null;
    }

    public static Student getByIdFromList(int studentId, List<Student> data) {
        for (Student student : data) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Student getStudentByAccountId(int accountId) throws Exception {
        List<Student> data = StudentDAO.readFromFile();
        for (Student student : data) {
            if (student.getAccountId() == accountId) {
                return student;
            }
        }
        return null;
    }

    public static List<Student> getListStudentInTourByTourId(int tourId) throws Exception {
        List<StudentTour> studentTour_data = StudentTourDAO.readFromFile();
        List<Student> students = new ArrayList<>();

        for (StudentTour studentTour : studentTour_data) {
            if (studentTour.getTourId() == tourId) {
                Student student = StudentService.getById(studentTour.getStudentId());
                students.add(student);
            }
        }
        return students;
    }

}

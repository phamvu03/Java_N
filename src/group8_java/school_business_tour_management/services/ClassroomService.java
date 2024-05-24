package group8_java.school_business_tour_management.services;

import group8_java.school_business_tour_management.dao.ClassroomDAO;
import group8_java.school_business_tour_management.models.Classroom;
import java.util.List;

public class ClassroomService {

    public static Classroom getById(int classId) throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        for (Classroom item : data) {
            if (item.getId() == classId) {
                return item;
            }
        }
        return null;
    }

    public static boolean isExisted(Classroom classroom) throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        for (Classroom item : data) {
            if (item.getCode().equals(classroom.getCode()) && item.getName().equalsIgnoreCase(classroom.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistedCode(String code) throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        for (Classroom item : data) {
            if (item.getCode().trim().equalsIgnoreCase(code.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isExistedName(String name) throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        for (Classroom item : data) {
            if (item.getName().trim().equals(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<Classroom> getAllClassroom() throws Exception {
        return ClassroomDAO.readFromFile();
    }

    public static int getLastClassroomId() throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        if (data != null) {
            if (data.size() == 0) {
                return 0;
            }
            return data.get(data.size() - 1).getId();
        }
        return -1;
    }

    public static Classroom getClassroomByIndex(int index) throws Exception {
        return ClassroomDAO.readFromFile().get(index);
    }

    public static void createNewClassroom(String code, String name) throws Exception {
        int lastId = getLastClassroomId();
        lastId++;
        Classroom classroom = new Classroom(lastId, code, name);
        List<Classroom> data = ClassroomDAO.readFromFile();
        data.add(classroom);
        ClassroomDAO.writeToFile(data);
    }

    public static void updateClassroom(Classroom classroom) throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        for (Classroom item : data) {
            if (item.getId() == classroom.getId()) {
                item.setCode(classroom.getCode());
                item.setName(classroom.getName());
                break;
            }
        }
        ClassroomDAO.writeToFile(data);
    }

    public static void deleteClassroom(int classroomId) throws Exception {
        List<Classroom> data = ClassroomDAO.readFromFile();
        Classroom deleteClassroom = null;
        for (Classroom item : data) {
            if (item.getId() == classroomId) {
                deleteClassroom = item;
                break;
            }
        }
        if (deleteClassroom != null) {
            data.remove(deleteClassroom);
            ClassroomDAO.writeToFile(data);
        }
    }
}

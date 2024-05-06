/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9_java.school_bussiness_tour_management.dao;

import group9_java.school_bussiness_tour_management.models.Class;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ClassDAO {
    private static String filePath = "src/group7_java/school_bussiness_tour_management/files/Class.txt";
    
    public static List<Class> readFromFile() throws Exception {
        List<Class> data = new ArrayList<Class>();
        BufferedReader read = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = read.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String code = parts[1];
            String name = parts[2];
            Class clas = new Class(id, code, name);
            data.add(clas);
        }
        return data;
    }
    
    public static void writeToFile(List<Class> classes) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Class clas : classes) {
            bw.write(clas.toStringFile());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
}

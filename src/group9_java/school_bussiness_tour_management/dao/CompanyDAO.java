/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9_java.school_bussiness_tour_management.dao;


import group9_java.school_bussiness_tour_management.models.Company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAM
 */
public class CompanyDAO {
    private static String filePath = "src/group9_java/school_bussiness_tour_management/files/Company.txt";

    public static List<Company> readFromFile() throws Exception{
        List<Company> data = new ArrayList<Company>();
        BufferedReader read = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = read.readLine())!=null){
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String code = parts[1];
            String name = parts[2];
            String description = parts[3];
            String email = parts[4];
            String phone = parts[5];
            String address = parts[6];
            Company com = new Company(id, code,name,description,email,phone,address);
            data.add(com);
        }
        return data;
    }
    
    public static void writeToFile(List<Company> companies) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Company comp : companies) {
            bw.write(comp.toStringFile());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
}

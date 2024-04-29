/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9_java.school_bussiness_tour_management.dao;

import group9_java.school_bussiness_tour_management.models.Account;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class AccountDAO {

    private static String filePath = "src/group7_java/school_bussiness_tour_management/files/Account.txt";

    public static List<Account> readFromFile() throws Exception {
        List<Account> data = new ArrayList<Account>();
        BufferedReader read = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = read.readLine()) != null) {
            String[] infoParts = line.split(",");
            int id = Integer.parseInt(infoParts[0]);
            String username = infoParts[1];
            String password = infoParts[2];
            Account acc = new Account(id, username, password);
            data.add(acc);
        }
        return data;
    }

    public static void writeToFile(List<Account> accounts) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Account sv : accounts) {
            bw.write(sv.toString());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
}

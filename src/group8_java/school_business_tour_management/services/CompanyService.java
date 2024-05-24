/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group8_java.school_business_tour_management.services;

import group8_java.school_business_tour_management.dao.CompanyDAO;
import group8_java.school_business_tour_management.models.Company;
import java.util.List;

/**
 *
 * @author LAM
 */
public class CompanyService {

    public static Company getById(int companyId) throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        for (Company comp : data) {
            if (comp.getId() == companyId) {
                return comp;
            }
        }
        return null;
    }

    public static boolean isExisted(Company company) throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        for (Company com : data) {
            if (com.getCode().equals(company.getCode()) && com.getName().equals(company.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistedComName(String companyname) throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        for (Company com : data) {
            if (com.getName().trim().equals(companyname.trim())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistedComCode(String companycode) throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        for (Company com : data) {
            if (com.getCode().trim().equals(companycode.trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<Company> getAllCompanies() throws Exception {
        return CompanyDAO.readFromFile();
    }

    public static int getLastCompanyId() throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        if (data != null) {
            if (data.size() == 0) {
                return 0;
            }
            return data.get(data.size() - 1).getId();
        }
        return -1;
    }

    public static Company getCompanyByIndex(int index) throws Exception {
        return CompanyDAO.readFromFile().get(index);
    }

    public static void createNewCompany(String code, String name, String description, String email, String phone, String address) throws Exception {
        int lastId = getLastCompanyId();
        lastId++;
        Company com = new Company(lastId, code, name, description, email, phone, address);
        List<Company> data = CompanyDAO.readFromFile();
        data.add(com);
        CompanyDAO.writeToFile(data);
    }

    public static void updateCompany(Company company) throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        for (Company com : data) {
            if (com.getId() == company.getId()) {
                com.setCode(company.getCode());
                com.setName(company.getName());
                com.setDescription(company.getDescription());
                com.setEmail(company.getEmail());
                com.setPhoneNumber(company.getPhoneNumber());
                com.setAddress(company.getAddress());
                break;
            }
        }
        CompanyDAO.writeToFile(data);
    }

    public static void deleteCompany(int companyId) throws Exception {
        List<Company> data = CompanyDAO.readFromFile();
        Company delCom = null;
        for (Company com : data) {
            if (com.getId() == companyId) {
                delCom = com;
                break;
            }
        }
        if (delCom != null) {
            data.remove(delCom);
            CompanyDAO.writeToFile(data);
        }
    }
    
    public static String getCompanyNameById(int companyId)throws Exception{
        List<Company> companies = CompanyDAO.readFromFile();
        for(Company comp : companies){
            if(comp.getId() == companyId){
                return comp.getName();
            }
        }
        return null;
    }

}

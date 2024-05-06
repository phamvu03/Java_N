/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9_java.school_bussiness_tour_management.services;

import group9_java.school_bussiness_tour_management.dao.AccountDAO;
import group9_java.school_bussiness_tour_management.models.Account;
import java.util.List;

/**
 *
 * @author PC
 */
public class AccountService {

    public static boolean isExisted(Account account) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        for (Account acc : data) {
            if (acc.getUsername().equals(account.getUsername()) && acc.getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistedUsername(String username) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        for (Account acc : data) {
            if (acc.getUsername().trim().equals(username.trim())) {
                return true;
            }
        }
        return false;
    }

    public static List<Account> getAllAccounts() throws Exception {
        return AccountDAO.readFromFile();
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

    public static Account getAccountByIndex(int index) throws Exception {
        return AccountDAO.readFromFile().get(index);
    }

    public static void createNewAccount(String username, String password) throws Exception {
        int lastId = getLastAccountId();
        lastId++;
        Account acc = new Account(lastId, username, password);
        List<Account> data = AccountDAO.readFromFile();
        data.add(acc);
        AccountDAO.writeToFile(data);
    }

    public static void updateAccount(Account account) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        for (Account acc : data) {
            if (acc.getId() == account.getId()) {
                acc.setPassword(account.getPassword());
                acc.setUsername(account.getUsername());
                break;
            }
        }
        AccountDAO.writeToFile(data);
    }

    public static void deleteAccount(int accountId) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        Account deleteAccount = null;
        for (Account acc : data) {
            if (acc.getId() == accountId) {
                deleteAccount = acc;
                break;
            }
        }
        if (deleteAccount != null) {
            data.remove(deleteAccount);
            AccountDAO.writeToFile(data);
        }
    }
}

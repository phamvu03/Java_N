package group8_java.school_business_tour_management.services;

import group8_java.school_business_tour_management.dao.AccountDAO;
import group8_java.school_business_tour_management.dao.StudentDAO;
import group8_java.school_business_tour_management.models.Account;
import group8_java.school_business_tour_management.models.Student;
import java.util.List;

public class AccountService {

    public static Account currentLoginUser;

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

    public static Account getAccountByUsername(String username) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        for (Account acc : data) {
            if (acc.getUsername().equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
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

    public static Account createNewAccount(String username, String password, String role) throws Exception {
        int lastId = getLastAccountId();
        lastId++;
        Account acc = new Account(lastId, username, password, role);
        List<Account> data = AccountDAO.readFromFile();
        data.add(acc);
        AccountDAO.writeToFile(data);
        return acc;
    }

    public static Account updateAccount(Account account) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        for (Account acc : data) {
            if (acc.getId() == account.getId()) {
                acc.setPassword(account.getPassword());
                acc.setUsername(account.getUsername());
                acc.setRole(account.getRole());
                break;
            }
        }
        AccountDAO.writeToFile(data);
        return account;
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

    public static Account getById(int accountId) throws Exception {
        List<Account> data = AccountDAO.readFromFile();
        for (Account acc : data) {
            if (acc.getId() == accountId) {
                return acc;
            }
        }
        return null;
    }

    public static Account getAccountByStudentId(int studentId) throws Exception {
        List<Student> studentData = StudentDAO.readFromFile();
        int accountId = -1;
        for (Student student : studentData) {
            if (student.getId() == studentId) {
                accountId = student.getAccountId();
                break;
            }
        }
        if (accountId != -1) {
            return getById(accountId);
        }
        return null;
    }
}

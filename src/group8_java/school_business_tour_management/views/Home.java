/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8_java.school_business_tour_management.views;

import group8_java.school_business_tour_management.common.MessageDialog;
import group8_java.school_business_tour_management.common.TransmittedDataShowData;
import group8_java.school_business_tour_management.dao.CompanyDAO;
import group8_java.school_business_tour_management.dao.StudentDAO;
import group8_java.school_business_tour_management.dao.TeacherDAO;
import group8_java.school_business_tour_management.dao.TourDAO;
import group8_java.school_business_tour_management.models.Account;
import group8_java.school_business_tour_management.models.Company;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.models.StudentTour;
import group8_java.school_business_tour_management.models.Teacher;
import group8_java.school_business_tour_management.models.Tour;
import group8_java.school_business_tour_management.services.AccountService;
import group8_java.school_business_tour_management.services.ClassroomService;
import group8_java.school_business_tour_management.services.CompanyService;
import group8_java.school_business_tour_management.services.StudentService;
import group8_java.school_business_tour_management.services.TeacherService;
import group8_java.school_business_tour_management.services.TourService;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author VV
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Account currentLoginUser = AccountService.currentLoginUser;
        if (!currentLoginUser.getRole().equals("Toàn quyền hệ thống")) {
            manageAccountButton.setVisible(false);
        }
        loadTextButton();
        initializeTableOfTours();
    }

    private DefaultTableModel tableModel;

    private void loadTableDataOfTours() {
        try {
            List<Tour> tour_data = TourService.getAllTours();
            List<Company> company_data = CompanyService.getAllCompanies();
            List<Teacher> teacher_data = TeacherService.getAllTeachers();
            tableModel.setRowCount(0);
            if (tour_data != null) {
                for (Tour tour : tour_data) {
                    String dateString = tour.getStartDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        LocalDate inputDate = LocalDate.parse(dateString, formatter);
                        LocalDate currentDate = LocalDate.now();
                        if (currentDate.equals(inputDate)) {
                            String companyName = "";
                            String teacherName = "";
                            for (Company comp : company_data) {
                                if (comp.getId() == tour.getCompanyId()) {
                                    companyName = comp.getName();
                                }
                            }
                            for (Teacher tea : teacher_data) {
                                if (tea.getId() == tour.getTeacherId()) {
                                    teacherName = tea.getLastName() + " " + tea.getFirstName();
                                }
                            }
                            tableModel.addRow(new Object[]{tour.getCode(), tour.getName(), tour.getDescription(),
                                tour.getAvailables(),
                                tour.getPresentator(), companyName, teacherName});
                        }
                    } catch (Exception ex) {
                        MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Tải dữ liệu cho bảng có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }

    private void initializeTableOfTours() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã chuyến", "Tên chuyến", "Mô tả",
            "Số lượng", "Người đại diện công ty", "Công ty", "Giáo viên"});
        tourNowTable.setModel(tableModel);

        loadTableDataOfTours();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        manageTeacherButton = new javax.swing.JButton();
        manageStudentButton = new javax.swing.JButton();
        manageCompanyButton = new javax.swing.JButton();
        manageClassButton = new javax.swing.JButton();
        manageAccountButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        manageTourButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tourDataButton = new javax.swing.JButton();
        companyDataButton = new javax.swing.JButton();
        studentDataButton = new javax.swing.JButton();
        teacherDataButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        todayTourTable = new javax.swing.JScrollPane();
        tourNowTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang chủ admin");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        manageTeacherButton.setText("Quản lí thông tin giáo viên");
        manageTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTeacherButtonActionPerformed(evt);
            }
        });

        manageStudentButton.setText("Quản lí thông tin sinh viên");
        manageStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStudentButtonActionPerformed(evt);
            }
        });

        manageCompanyButton.setText("Quản lí thông tin doanh nghiệp");
        manageCompanyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCompanyButtonActionPerformed(evt);
            }
        });

        manageClassButton.setText("Quản lí thông tin lớp học");
        manageClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageClassButtonActionPerformed(evt);
            }
        });

        manageAccountButton.setText("Quản lí tài khoản hệ thống");
        manageAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAccountButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU");

        logoutButton.setBackground(new java.awt.Color(253, 0, 0));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("Đăng xuất");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        manageTourButton.setText("Quản lí chuyến tham quan doanh nghiệp");
        manageTourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTourButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(manageTourButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageClassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageTeacherButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageStudentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageCompanyButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageTourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(manageStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageTeacherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(manageAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {manageAccountButton, manageClassButton});

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DỮ LIỆU HIỆN CÓ TRONG HỆ THỐNG");

        tourDataButton.setBackground(new java.awt.Color(0, 102, 0));
        tourDataButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tourDataButton.setForeground(new java.awt.Color(255, 255, 255));
        tourDataButton.setText("xxx chuyến tham quan được tổ chức");
        tourDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tourDataButtonActionPerformed(evt);
            }
        });

        companyDataButton.setBackground(new java.awt.Color(0, 102, 0));
        companyDataButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        companyDataButton.setForeground(new java.awt.Color(255, 255, 255));
        companyDataButton.setText("xxx doanh nghiệp được liên kết với nhà trường");
        companyDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyDataButtonActionPerformed(evt);
            }
        });

        studentDataButton.setBackground(new java.awt.Color(0, 102, 0));
        studentDataButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        studentDataButton.setForeground(new java.awt.Color(255, 255, 255));
        studentDataButton.setText("xxx sinh viên được quản lí");
        studentDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDataButtonActionPerformed(evt);
            }
        });

        teacherDataButton.setBackground(new java.awt.Color(0, 102, 0));
        teacherDataButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        teacherDataButton.setForeground(new java.awt.Color(255, 255, 255));
        teacherDataButton.setText("xxx giáo viên đại diện doanh nghiệp");
        teacherDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherDataButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CÁC CHUYẾN THAM QUAN ĐƯỢC TỔ CHỨ TRONG NGÀY");

        tourNowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        todayTourTable.setViewportView(tourNowTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(studentDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tourDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(teacherDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .addComponent(companyDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(todayTourTable, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tourDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacherDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todayTourTable, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageClassButtonActionPerformed
        dispose();
        ManageClassroom manageClassroomScreen = new ManageClassroom();
        manageClassroomScreen.setLocationRelativeTo(null);
        manageClassroomScreen.setVisible(true);
    }//GEN-LAST:event_manageClassButtonActionPerformed

    private void tourDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tourDataButtonActionPerformed
        try {
            dispose();
            TransmittedDataShowData data = new TransmittedDataShowData("tours", "home");
            ShowData screen = new ShowData(data);
            if (screen != null) {
                screen.setLocationRelativeTo(null);
                screen.setVisible(true);
            }

        } catch (Exception e) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + e, "Lỗi");
        }
    }//GEN-LAST:event_tourDataButtonActionPerformed

    private void companyDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyDataButtonActionPerformed
        try {
            dispose();
            TransmittedDataShowData data = new TransmittedDataShowData("companys", "home");
            ShowData screen = new ShowData(data);
            if (screen != null) {
                screen.setLocationRelativeTo(null);
                screen.setVisible(true);
            }
        } catch (Exception e) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + e, "Lỗi");
        }
    }//GEN-LAST:event_companyDataButtonActionPerformed

    private void studentDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDataButtonActionPerformed
        try {
            dispose();
            TransmittedDataShowData data = new TransmittedDataShowData("students", "home");
            ShowData screen = new ShowData(data);
            if (screen != null) {
                screen.setLocationRelativeTo(null);
                screen.setVisible(true);
            }
        } catch (Exception e) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + e, "Lỗi");
        }
    }//GEN-LAST:event_studentDataButtonActionPerformed

    private void teacherDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherDataButtonActionPerformed
        try {
            dispose();
            TransmittedDataShowData data = new TransmittedDataShowData("teachers", "home");
            ShowData screen = new ShowData(data);
            if (screen != null) {
                screen.setLocationRelativeTo(null);
                screen.setVisible(true);
            }
        } catch (Exception e) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + e, "Lỗi");
        }
    }//GEN-LAST:event_teacherDataButtonActionPerformed

    private String getCellValue(Tour tour, int columnIndex) throws Exception {
        switch (columnIndex) {
            case 0:
                return tour.getCode();
            case 1:
                return tour.getName();
            case 2:
                return tour.getDescription();
            case 3:
                return String.valueOf(tour.getAvailables());
            case 4:
                return tour.getPresentator();
            case 5:
                return CompanyService.getById(tour.getCompanyId()).getName();
            case 6:
                Teacher teacher = TeacherService.getTeacherById(tour.getTeacherId());
                if (teacher == null) {
                    return "";
                }
                String fullName = teacher.getLastName() + " " + teacher.getFirstName();
                return fullName;
            default:
                return "";
        }
    }

    private void manageAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manageAccountButtonActionPerformed
        dispose();
        ManageAccount manageAccountScreen = new ManageAccount();
        manageAccountScreen.setLocationRelativeTo(null);
        manageAccountScreen.setVisible(true);
    }// GEN-LAST:event_manageAccountButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutButtonActionPerformed
        int key = MessageDialog.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất khỏi hệ thống?", "Xác nhận");
        if (key == 0) {
            dispose();
            Login loginScreen = new Login();
            loginScreen.setLocationRelativeTo(null);
            loginScreen.setVisible(true);
        }
    }// GEN-LAST:event_logoutButtonActionPerformed

    private void manageCompanyButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manageCompanyButtonActionPerformed
        dispose();
        ManageCompany manageCompanyScreen = new ManageCompany();
        manageCompanyScreen.setLocationRelativeTo(null);
        manageCompanyScreen.setVisible(true);
    }// GEN-LAST:event_manageCompanyButtonActionPerformed

    private void manageTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manageTeacherButtonActionPerformed
        dispose();
        ManageTeacher manageTeacherScreen = new ManageTeacher();
        manageTeacherScreen.setLocationRelativeTo(null);
        manageTeacherScreen.setVisible(true);
    }// GEN-LAST:event_manageTeacherButtonActionPerformed

    private void manageStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manageStudentButtonActionPerformed
        dispose();
        ManageStudent manageStudentScreen = new ManageStudent();
        manageStudentScreen.setLocationRelativeTo(null);
        manageStudentScreen.setVisible(true);
    }// GEN-LAST:event_manageStudentButtonActionPerformed

    private void manageTourButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manageTourButtonActionPerformed
        dispose();
        ManageTour manageTourScreen = new ManageTour();
        manageTourScreen.setLocationRelativeTo(null);
        manageTourScreen.setVisible(true);
    }// GEN-LAST:event_manageTourButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Home view = new Home();
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        });
    }

    public void loadTextButton() {
        try {
            List<Student> students = StudentDAO.readFromFile();
            List<Tour> tours = TourDAO.readFromFile();
            List<Teacher> teachers = TeacherDAO.readFromFile();
            List<Company> companys = CompanyDAO.readFromFile();

            if (students == null) {
                studentDataButton.setText("0 sinh viên được quản lí");
            } else {
                studentDataButton.setText(students.size() + " sinh viên được quản lí");
            }

            if (tours == null) {
                tourDataButton.setText("0 chuyến tham quan được tổ chức");
            } else {
                tourDataButton.setText(tours.size() + " chuyến tham quan được tổ chức");
            }

            if (teachers == null) {
                teacherDataButton.setText("0 giáo viên đại diện doanh nghiệp");
            } else {
                teacherDataButton.setText(teachers.size() + " giáo viên đại diện doanh nghiệp");
            }

            if (companys == null) {
                companyDataButton.setText("0 doanh nghiệp được liên kết với nhà trường");
            } else {
                companyDataButton.setText(companys.size() + " doanh nghiệp được liên kết với nhà trường");
            }

        } catch (Exception ex) {
            MessageDialog.showErrorDialog(jPanel1, "Lỗi, chi tiết: " + ex.getMessage(), "Lỗi");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton companyDataButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton manageAccountButton;
    private javax.swing.JButton manageClassButton;
    private javax.swing.JButton manageCompanyButton;
    private javax.swing.JButton manageStudentButton;
    private javax.swing.JButton manageTeacherButton;
    private javax.swing.JButton manageTourButton;
    private javax.swing.JButton studentDataButton;
    private javax.swing.JButton teacherDataButton;
    private javax.swing.JScrollPane todayTourTable;
    private javax.swing.JButton tourDataButton;
    private javax.swing.JTable tourNowTable;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package group8_java.school_business_tour_management.views;

//import com.itextpdf.text.Rectangle;
import group8_java.school_business_tour_management.common.MessageDialog;
import group8_java.school_business_tour_management.common.TransmittedDataShowData;
import group8_java.school_business_tour_management.dao.StudentDAO;
import group8_java.school_business_tour_management.dao.StudentTourDAO;
import group8_java.school_business_tour_management.dao.TeacherDAO;
import group8_java.school_business_tour_management.dao.TourDAO;
import group8_java.school_business_tour_management.models.Company;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.models.StudentTour;
import group8_java.school_business_tour_management.models.Teacher;
import group8_java.school_business_tour_management.models.Tour;
import group8_java.school_business_tour_management.services.ClassroomService;
import group8_java.school_business_tour_management.services.CompanyService;
import group8_java.school_business_tour_management.services.StudentService;
import group8_java.school_business_tour_management.services.StudentTourService;
import group8_java.school_business_tour_management.services.TeacherService;
import group8_java.school_business_tour_management.services.TourService;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinh
 */
public class StudentAndTeacherHome extends javax.swing.JFrame {

    /**
     * Creates new form StudentAndTeacherHome
     */
    private Student loggedInStudent;
    private Teacher loggedInTeacher;
    private String reated = "comingTours";

    /**
     * Creates new form Home
     *
     * @param loggedInStudent
     */
    public StudentAndTeacherHome() {
        initComponents();
    }

    public StudentAndTeacherHome(Object obj) {
        initComponents();
        if (obj instanceof Student) {
            this.loggedInStudent = (Student) obj;
            String title = loggedInStudent.getLastName() + " " + loggedInStudent.getFirstName();
            if (title.contains("SV chưa nhập")) {
                welcomeLabel.setText("Xin chào");
            } else {
                welcomeLabel.setText("Xin chào " + title);
            }
        } else if (obj instanceof Teacher) {
            this.loggedInTeacher = (Teacher) obj;
            welcomeLabel.setText("Xin chào " + loggedInTeacher.getLastName() + " " + loggedInTeacher.getFirstName());
        } else {
            throw new IllegalArgumentException("Không hỗ trợ loại đối tượng này.");
        }
        if (loggedInStudent == null && loggedInTeacher == null) {
            MessageDialog.showErrorDialog(this, "Phải đăng nhập với tài khoản sinh viên để sử dụng GUI này", "Lỗi");
            return;
        }
        ratedTour.setVisible(false);
        initializeTableOfTours();
        evaluateButton.setVisible(false);
        if (loggedInTeacher != null) {
            evaluateButton.setVisible(true);
        }
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
                        if (currentDate.compareTo(inputDate) < 0) {
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
                            tableModel.addRow(new Object[] { tour.getCode(), tour.getName(), tour.getDescription(),
                                    tour.getAvailables(), tour.getStartDate(),
                                    tour.getPresentator(), companyName, teacherName });
                        }
                    } catch (Exception ex) {
                        MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Tải dữ liệu cho bảng có lỗi! Chi tiết: " + ex.getMessage(),
                    "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }

    private void initializeTableOfTours() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] { "Mã chuyến", "Tên chuyến", "Mô tả",
                "Số lượng", "Ngày  diễn ra", "Người đại diện công ty", "Công ty", "Giáo viên" });
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        registeredTourButton = new javax.swing.JButton();
        updateProfile = new javax.swing.JButton();
        showTourInDay = new javax.swing.JButton();
        showUpcomingTours = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        evaluateButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        searchButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tourNowTable = new javax.swing.JTable();
        showStudentsOfTour = new javax.swing.JButton();
        addTour = new javax.swing.JButton();
        ratedTour = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Xin chào");

        registeredTourButton.setText("Các chuyến tham quan đã đăng kí");
        registeredTourButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registeredTourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registeredTourButtonActionPerformed(evt);
            }
        });

        updateProfile.setText("Tài khoản cá nhân");
        updateProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileActionPerformed(evt);
            }
        });

        showTourInDay.setText("Chuyến tham quan hôm nay của bạn");
        showTourInDay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        showTourInDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTourInDayActionPerformed(evt);
            }
        });

        showUpcomingTours.setText("Các chuyến tham quan sắp diễn ra");
        showUpcomingTours.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        showUpcomingTours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showUpcomingToursActionPerformed(evt);
            }
        });

        logoutButton.setBackground(new java.awt.Color(253, 0, 0));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("Đăng xuất");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        evaluateButton.setText("Đánh giá sinh viên của chuyến tham quan");
        evaluateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        evaluateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(evaluateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 259,
                                                Short.MAX_VALUE)
                                        .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(showUpcomingTours, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(registeredTourButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updateProfile, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(showTourInDay, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(welcomeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(registeredTourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(showTourInDay, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(showUpcomingTours, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(evaluateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CÁC CHUYẾN THAM QUAN DOANH NGHIỆP SẮP DIỄN RA");

        searchInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInputKeyReleased(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(51, 102, 255));
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Tìm kiếm");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        tourNowTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane1.setViewportView(tourNowTable);

        showStudentsOfTour.setBackground(new java.awt.Color(0, 153, 0));
        showStudentsOfTour.setForeground(new java.awt.Color(255, 255, 255));
        showStudentsOfTour.setText("Xem danh sách sinh viên tham gia chuyến tham quan");
        showStudentsOfTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStudentsOfTourActionPerformed(evt);
            }
        });

        addTour.setBackground(new java.awt.Color(0, 153, 0));
        addTour.setForeground(new java.awt.Color(255, 255, 255));
        addTour.setText("Đăng ký tham gia chuyến tham quan");
        addTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTourActionPerformed(evt);
            }
        });

        ratedTour.setBackground(new java.awt.Color(0, 153, 0));
        ratedTour.setForeground(new java.awt.Color(255, 255, 255));
        ratedTour.setText("Các chuyến tham quan đã được đánh giá");
        ratedTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ratedTourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ratedTour)
                                .addGap(18, 18, 18)
                                .addComponent(addTour)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(showStudentsOfTour))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(searchInput, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(291, 291, 291)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(showStudentsOfTour, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addTour, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ratedTour, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registeredTourButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_registeredTourButtonActionPerformed
        try {
            dispose();
            ShowData screen = null;
            if (loggedInTeacher != null) {
                TransmittedDataShowData data = new TransmittedDataShowData("toursOfTeacher",
                        "studentAndTeacherHome", loggedInTeacher.getId(), false);
                screen = new ShowData(data);
            } else if (loggedInStudent != null) {
                TransmittedDataShowData data = new TransmittedDataShowData("toursOfStudents",
                        "studentAndTeacherHome", loggedInStudent.getId(), true);
                screen = new ShowData(data);
            }
            screen.setLocationRelativeTo(null);
            screen.setVisible(true);
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(jPanel1, "Có lỗi, chi tiết: " + ex.getMessage(), "Lỗi");
        }
    }// GEN-LAST:event_registeredTourButtonActionPerformed

    private void updateProfileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updateProfileActionPerformed
        try {
            // if (loggedInStudent != null) {
            // dispose();
            // PersonalAccountInformation personalAccountInformationScreen = new
            // PersonalAccountInformation(loggedInStudent);
            // personalAccountInformationScreen.setLocationRelativeTo(null);
            // personalAccountInformationScreen.setVisible(true);
            // } else if (loggedInTeacher != null) {
            // dispose();
            // TeacherProfile screen = new TeacherProfile(loggedInTeacher);
            // screen.setLocationRelativeTo(null);
            // screen.setVisible(true);
            // }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(jPanel1, "Có lỗi, chi tiết: " + ex.getMessage(), "Lỗi");
        }
    }// GEN-LAST:event_updateProfileActionPerformed

    private void showTourInDayActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_showTourInDayActionPerformed
        try {
            jLabel3.setText("CHUYẾN THAM QUAN HÔM NAY CỦA BẠN");
            addTour.setVisible(false);
            ratedTour.setVisible(false);
            searchInput.setVisible(false);
            searchButton.setVisible(false);
            tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(new String[] { "Mã chuyến", "Tên chuyến", "Mô tả",
                    "Số lượng", "Ngày diễn ra", "Người đại diện công ty", "Công ty", "Giáo viên" });
            tourNowTable.setModel(tableModel);
            tableModel.setRowCount(0);
            if (loggedInStudent != null) {
                List<Tour> tour_data = StudentTourService.getToursForStudent(loggedInStudent.getId());
                List<Company> company_data = CompanyService.getAllCompanies();
                List<Teacher> teacher_data = TeacherService.getAllTeachers();

                if (tour_data != null) {
                    for (Tour tour : tour_data) {
                        String dateString = tour.getStartDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        try {
                            LocalDate inputDate = LocalDate.parse(dateString, formatter);
                            LocalDate currentDate = LocalDate.now();
                            if (currentDate.compareTo(inputDate) == 0) {
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
                                tableModel.addRow(new Object[] { tour.getCode(), tour.getName(), tour.getDescription(),
                                        tour.getAvailables(), tour.getStartDate(),
                                        tour.getPresentator(), companyName, teacherName });
                            }
                        } catch (Exception ex) {
                            MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(),
                                    "Có lỗi xảy ra");
                            ex.printStackTrace();
                        }
                    }
                }
            } else if (loggedInTeacher != null) {
                List<Tour> tour_data = loggedInTeacher.getTours();
                List<Company> company_data = CompanyService.getAllCompanies();
                List<Teacher> teacher_data = TeacherService.getAllTeachers();

                if (tour_data != null) {
                    for (Tour tour : tour_data) {
                        String dateString = tour.getStartDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        try {
                            LocalDate inputDate = LocalDate.parse(dateString, formatter);
                            LocalDate currentDate = LocalDate.now();
                            if (currentDate.compareTo(inputDate) == 0) {
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
                                tableModel.addRow(new Object[] { tour.getCode(), tour.getName(), tour.getDescription(),
                                        tour.getAvailables(), tour.getStartDate(),
                                        tour.getPresentator(), companyName, teacherName });
                            }
                        } catch (Exception ex) {
                            MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(),
                                    "Có lỗi xảy ra");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }// GEN-LAST:event_showTourInDayActionPerformed

    private void showUpcomingToursActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_showUpcomingToursActionPerformed
        jLabel3.setText("CÁC CHUYẾN THAM QUAN DOANH NGHIỆP SẮP DIỄN RA");
        addTour.setVisible(true);
        ratedTour.setVisible(false);
        searchInput.setVisible(true);
        searchButton.setVisible(true);
        initializeTableOfTours();
        reated = "comingTours";
    }// GEN-LAST:event_showUpcomingToursActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutButtonActionPerformed
        int key = MessageDialog.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất khỏi hệ thống?", "Xác nhận");
        // if (key == 0) {
        // dispose();
        // Login loginScreen = new Login();
        // loginScreen.setLocationRelativeTo(null);
        // loginScreen.setVisible(true);
        // }
    }// GEN-LAST:event_logoutButtonActionPerformed

    private void searchInputKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_searchInputKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Handle Enter key press
            searchByKey();
        }
    }// GEN-LAST:event_searchInputKeyReleased

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchButtonActionPerformed
        searchByKey();
    }// GEN-LAST:event_searchButtonActionPerformed

    private void searchByKey() {
        try {
            String keyword = searchInput.getText().trim();
            int count = 0;
            if (keyword.trim().equals("")) {
                if (reated.equalsIgnoreCase("comingTours")) {
                    initializeTableOfTours();
                } else if (reated.equalsIgnoreCase("tookPlaceTours")) {
                    loadTableOfTookPlaceTours(false);
                }
                return;
            }
            List<Tour> tour_data = TourService.getAllTours();
            List<Company> company_data = CompanyService.getAllCompanies();
            List<Teacher> teacher_data = TeacherService.getAllTeachers();
            tableModel.setRowCount(0);
            if (tour_data != null) {
                for (Tour tour : tour_data) {
                    try {

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
                        if (keyword.equalsIgnoreCase(tour.getCode()) || keyword.equalsIgnoreCase(tour.getName())) {
                            tableModel.addRow(new Object[] { tour.getCode(), tour.getName(), tour.getDescription(),
                                    tour.getAvailables(),
                                    tour.getPresentator(), companyName, teacherName });
                        }

                    } catch (Exception ex) {
                        MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
                        ex.printStackTrace();
                    }
                }
            }
            tableModel.fireTableDataChanged();
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Tải dữ liệu cho bảng có lỗi! Chi tiết: " + ex.getMessage(),
                    "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }

    private void showStudentsOfTourActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_showStudentsOfTourActionPerformed

        try {
            int index = tourNowTable.getSelectedRow();
            if (index == -1) {
                MessageDialog.showInfoDialog(this, "Vui chọn chuyến tham quan mà bạn muốn xem danh sách sinh viên",
                        "Thông báo");
                return;
            }
            List<Tour> data_tour = TourDAO.readFromFile();
            String tourCode = (String) tourNowTable.getValueAt(index, 0);
            int tourID = -1;
            for (Tour item : data_tour) {
                if (item.getCode().equalsIgnoreCase(tourCode)) {
                    tourID = item.getId();
                    break;
                }
            }
            dispose();
            TransmittedDataShowData data_show = null;
            if (loggedInTeacher != null) {
                data_show = new TransmittedDataShowData("studentTours",
                        "studentAndTeacherHome", tourID, loggedInTeacher.getId(), false);
                if (reated.equalsIgnoreCase("tookPlaceTours")) {
                    data_show = new TransmittedDataShowData("studentTookPlaceTours",
                            "studentAndTeacherHome", tourID, loggedInTeacher.getId(), false);
                }
            } else if (loggedInStudent != null) {
                data_show = new TransmittedDataShowData("studentTours",
                        "studentAndTeacherHome", tourID, loggedInStudent.getId(), true);
            }
            ShowData screen = new ShowData(data_show);
            screen.setLocationRelativeTo(null);
            screen.setVisible(true);
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(jPanel1,
                    "Có lỗi ở màn StudentAndTeacherHome cụ thể là nút showStudentsOfTourActionPerformed, chi tiết: "
                            + ex.getMessage(),
                    "Lỗi");
        }
    }// GEN-LAST:event_showStudentsOfTourActionPerformed

    private void addTourActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addTourActionPerformed
        try {
            if (reated.equalsIgnoreCase("tookPlaceTours")) {
                loadTableOfTookPlaceTours(false);
            } else {
                int index = tourNowTable.getSelectedRow();
                if (index == -1) {
                    MessageDialog.showInfoDialog(this, "Vui chọn chuyến tham quan mà bạn muốn thêm", "Thông báo");
                    return;
                }
                String tourCode = (String) tourNowTable.getValueAt(index, 0);
                int id = -1;
                List<Tour> data_tours = TourDAO.readFromFile();
                Iterator<Tour> iterator = data_tours.iterator();
                while (iterator.hasNext()) {
                    Tour tour = iterator.next();
                    if (tour.getCode().equalsIgnoreCase(tourCode)) {
                        id = tour.getId();
                        break;
                    }
                }
                Tour selectTour = TourService.getTourById(id);
                if (loggedInStudent != null) {
                    List<StudentTour> data_student_tours = StudentTourDAO.readFromFile();
                    List<StudentTour> data_students_of_this_tour = selectTour.getStudentTours();
                    List<Tour> tours = StudentTourService.getToursForStudent(loggedInStudent.getId());
                    List<Student> data_students = StudentService.getAllStudents();
                    if (data_students_of_this_tour != null && data_student_tours != null && tours != null) {
                        for (StudentTour item : data_students_of_this_tour) {
                            if (item.getStudentId() == loggedInStudent.getId()) {
                                MessageDialog.showInfoDialog(tourNowTable, "Bạn đã đăng ký chuyến tham quan này rồi",
                                        "Thông báo");
                                return;
                            }
                        }
                        for (Tour item : tours) {
                            if (item.getStartDate().compareTo(selectTour.getStartDate()) == 0) {
                                MessageDialog.showInfoDialog(tourNowTable,
                                        "Bạn đã có một chuyến tham quan vào ngày " + item.getStartDate() + " rồi.",
                                        "Thông báo");
                                return;
                            }
                            if (item.getId() == selectTour.getId()
                                    && item.getAvailables() == selectTour.getStudentTours().size()) {
                                MessageDialog.showInfoDialog(tourNowTable,
                                        "Hiện tại số lượng người tham quan đã đầy, mong bạn tham gia chuyến tham quan khác",
                                        "Thông báo");
                                return;
                            }
                        }
                    }
                    StudentTour newTour = new StudentTour(loggedInStudent.getId(), selectTour.getId(), 0);
                    data_student_tours.add(newTour);
                    if (data_tours != null) {
                        for (Tour item : data_tours) {
                            if (item.getId() == id) {
                                if (item.getStudentTours() == null) {
                                    item.setStudentTours(new ArrayList<>());
                                }
                                item.getStudentTours().add(newTour);

                                break;
                            }
                        }
                    }
                    if (data_students != null) {
                        for (Student item : data_students) {
                            if (item.getId() == loggedInStudent.getId()) {
                                if (item.getStudentTours() == null) {
                                    item.setStudentTours(new ArrayList<>());
                                }
                                item.getStudentTours().add(newTour);

                                break;
                            }
                        }
                    }
                    TourDAO.writeToFile(data_tours);
                    StudentTourDAO.writeToFile(data_student_tours);
                    StudentDAO.writeToFile(data_students);
                    MessageDialog.showInfoDialog(tourNowTable, "Đăng ký tham quan thành công", "Thông báo");
                } else if (loggedInTeacher != null) {
                    List<Tour> data_tours_teacher = loggedInTeacher.getTours();
                    List<Teacher> data_teachers = TeacherDAO.readFromFile();
                    if (data_tours_teacher != null) {
                        for (Tour item : data_tours_teacher) {
                            if (item.getId() == selectTour.getId()) {
                                MessageDialog.showInfoDialog(tourNowTable, "Bạn đã đăng ký chuyến tham quan này rồi",
                                        "Thông báo");
                                return;
                            }
                            if (item.getStartDate().compareTo(selectTour.getStartDate()) == 0) {
                                MessageDialog.showInfoDialog(tourNowTable,
                                        "Bạn đã có một chuyến tham quan vào ngày " + item.getStartDate() + " rồi.",
                                        "Thông báo");
                                return;
                            }
                            if (item.getId() == selectTour.getId()
                                    && item.getAvailables() == selectTour.getStudentTours().size()) {
                                MessageDialog.showInfoDialog(tourNowTable,
                                        "Hiện tại số lượng người tham quan đã đầy, mong bạn tham gia chuyến tham quan khác",
                                        "Thông báo");
                                return;
                            }
                        }
                        for (Tour item : data_tours) {
                            if (item.getId() == id) {
                                item.setTeacherId(loggedInTeacher.getId());
                                break;
                            }
                        }
                        for (Teacher item : data_teachers) {
                            if (item.getId() == loggedInTeacher.getId()) {
                                item.getTours().add(selectTour);
                            }
                        }
                    }
                    TeacherDAO.writeToFile(data_teachers);
                    TourDAO.writeToFile(data_tours);
                    initializeTableOfTours();
                    MessageDialog.showInfoDialog(tourNowTable, "Đăng ký tham quan thành công", "Thông báo");
                }
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(tourNowTable,
                    "Có lỗi ở màn StudentAndTeacherHome cụ thể là nút showStudentsOfTourActionPerformed, chi tiết: "
                            + ex.getMessage(),
                    "Lỗi");
        }
    }// GEN-LAST:event_addTourActionPerformed

    private void evaluateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_evaluateButtonActionPerformed
        jLabel3.setText("DANH SÁCH CÁC CHUYẾN THAM QUAN CẦN ĐƯỢC ĐÁNH GIÁ");
        addTour.setText("Các chuyến tham quan chưa đánh giá");
        ratedTour.setVisible(true);
        reated = "tookPlaceTours";
        loadTableOfTookPlaceTours(false);

    }// GEN-LAST:event_evaluateButtonActionPerformed

    private void ratedTourActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ratedTourActionPerformed
        loadTableOfTookPlaceTours(true);
    }// GEN-LAST:event_ratedTourActionPerformed

    private void loadTableOfTookPlaceTours(boolean isRatedTour) {
        try {
            tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(new String[] { "Mã chuyến", "Tên chuyến", "Mô tả",
                    "Số lượng", "Ngày  diễn ra", "Người đại diện công ty", "Công ty" });
            tourNowTable.setModel(tableModel);
            List<Tour> tour_data = loggedInTeacher.getTours();
            List<Company> company_data = CompanyService.getAllCompanies();
            List<Tour> tour_not_rated = new ArrayList<>();
            List<Tour> tour_rated = new ArrayList<>();
            if (tour_data != null) {
                for (Tour tour : tour_data) {
                    String dateString = tour.getStartDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        LocalDate inputDate = LocalDate.parse(dateString, formatter);
                        LocalDate currentDate = LocalDate.now();
                        int count = 0;
                        if (currentDate.compareTo(inputDate) > 0) {
                            Tour fillTour = TourService.getTourById(tour.getId());
                            List<StudentTour> data_students_tour = fillTour.getStudentTours();
                            if (data_students_tour != null) {
                                for (StudentTour item : data_students_tour) {
                                    if (item.getRate() > 0) {
                                        count++;
                                    }
                                }
                                if (count == data_students_tour.size()) {
                                    tour_rated.add(tour);
                                } else {
                                    tour_not_rated.add(tour);
                                }
                            }

                        }
                    } catch (Exception ex) {
                        MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
                        ex.printStackTrace();
                    }
                }
                List<Tour> selectedTours = isRatedTour ? tour_rated : tour_not_rated;
                for (Tour tour : selectedTours) {
                    try {
                        String companyName = company_data.stream()
                                .filter(comp -> comp.getId() == tour.getCompanyId())
                                .findFirst()
                                .map(Company::getName)
                                .orElse("");
                        tableModel.addRow(new Object[] { tour.getCode(), tour.getName(), tour.getDescription(),
                                tour.getAvailables(), tour.getStartDate(),
                                tour.getPresentator(), companyName });
                    } catch (Exception ex) {
                        MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(StudentAndTeacherHome.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentAndTeacherHome.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentAndTeacherHome.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentAndTeacherHome.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentAndTeacherHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTour;
    private javax.swing.JButton evaluateButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton ratedTour;
    private javax.swing.JButton registeredTourButton;
    private javax.swing.JToggleButton searchButton;
    private javax.swing.JTextField searchInput;
    private javax.swing.JButton showStudentsOfTour;
    private javax.swing.JButton showTourInDay;
    private javax.swing.JButton showUpcomingTours;
    private javax.swing.JTable tourNowTable;
    private javax.swing.JButton updateProfile;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}

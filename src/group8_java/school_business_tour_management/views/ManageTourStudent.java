/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package group8_java.school_business_tour_management.views;

//import com.itextpdf.text.log.Logger;
import group8_java.school_business_tour_management.common.MessageDialog;
import group8_java.school_business_tour_management.models.Classroom;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.models.StudentTour;
import group8_java.school_business_tour_management.models.Tour;
import group8_java.school_business_tour_management.services.ClassroomService;
import group8_java.school_business_tour_management.services.CompanyService;
import group8_java.school_business_tour_management.services.StudentService;
import group8_java.school_business_tour_management.services.StudentTourService;
import group8_java.school_business_tour_management.services.TourService;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.System.Logger.Level;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author VV
 */
public class ManageTourStudent extends javax.swing.JFrame {

    /**
     * Creates new form ManageStudent
     */
    public Classroom classroomm = new Classroom();

    public ManageTourStudent() {
        initComponents();
        setLocationRelativeTo(null);
        initializeTable();
    }

    private Tour selectedTour;

    public ManageTourStudent(Tour tour) {
        try {
            this.selectedTour = tour;
            initComponents();
            setLocationRelativeTo(null);
            initializeTable();
            String tourTitle = tour.getName() + " (Mã: " + tour.getCode() + ", công ty: " + CompanyService.getById(tour.getCompanyId()).getName() + ") - Ngày: " + tour.getStartDate();
            tourNameTitle.setText(tourTitle);
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Có lỗi xảy ra! Chi tiết: " + ex.getMessage(), "lỗi");
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_back = new javax.swing.JButton();
        screenTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        tourNameTitle = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        rateStudentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Xem danh sách sinh viên tham gia của một chuyến tham quan");

        btn_back.setText("Quay lại");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        screenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        screenTitle.setText("Danh sách sinh viên tham gia chuyến tham quan");

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentTable);

        jLabel9.setText("Tìm kiếm sinh viên:");

        searchInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchInputKeyPressed(evt);
            }
        });

        searchButton.setText("Tìm kiếm");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        addButton.setText("Thêm sinh viên vào chuyến đi");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Xóa sinh viên trong danh sách");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        tourNameTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tourNameTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tourNameTitle.setText("Tên công ty - Ngày diễn ra");

        resetButton.setText("Nhập lại");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        rateStudentButton.setText("Đánh giá sinh viên");
        rateStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateStudentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchInput)
                            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rateStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_back)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(screenTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tourNameTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(screenTitle)
                .addGap(2, 2, 2)
                .addComponent(tourNameTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rateStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        dispose();
        ManageTour screen = new ManageTour();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void studentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentTableMouseClicked
        try {
            int index = studentTable.getSelectedRow();
            if (index == -1) {
//                MessageDialog.showInfoDialog(this, "Vui chọn chọn sinh viên", "Thông báo");
                return;
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + ex.getMessage() + "\n" + ex.toString() + "\n", "Phát hiện lỗi");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_studentTableMouseClicked

    private void handleSearchByKeyword() {
        try {
            String keyword = searchInput.getText().trim();
            if (keyword.trim().equals("")) {
                MessageDialog.showInfoDialog(this, "Chưa có từ khóa tìm kiếm", "Thông báo");
                return;
            }
            List<StudentTour> data = selectedTour.getStudentTours();
            tableModel.setRowCount(0);
            if (data != null) {
                for (StudentTour stuTour : data) {
                    Student stu = StudentService.getById(stuTour.getStudentId());
                    if (stu.getAddress().contains(keyword) || stu.getCode().contains(keyword) || stu.getFirstName().contains(keyword) || stu.getLastName().contains(keyword) || stu.getPhoneNumber().contains(keyword)) {
                        tableModel.addRow(
                                new Object[]{
                                    stu.getCode(),
                                    stu.getLastName(),
                                    stu.getFirstName(),
                                    ClassroomService.getById(stu.getClassId()).getName(),
                                    stu.getBirthDate(),
                                    stu.getPhoneNumber(),
                                    stu.getEmail(),
                                    stu.getAddress()
                                });
                    }
                }
            }
            tableModel.fireTableDataChanged();
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Tải dữ liệu cho bảng có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        handleSearchByKeyword();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        reinitialize();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void reinitialize() {
        searchInput.setText("");
        loadTableData();
    }

    private void searchInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // Handle Enter key press
            handleSearchByKeyword();
        }
    }//GEN-LAST:event_searchInputKeyPressed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        AddStudentToTour screen = new AddStudentToTour(selectedTour);
        screen.setLocationRelativeTo(null);
        dispose();
        screen.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            int index = studentTable.getSelectedRow();
            if (index == -1) {
                MessageDialog.showInfoDialog(this, "Vui chọn chọn sinh viên để thực hiện chức năng này", "Thông báo");
                return;
            }
            Student student = StudentService.getByCode(studentTable.getValueAt(index, 0).toString());

            int keyPress = MessageDialog.showConfirmDialog(this, "Bạn có chắc muốn xóa sinh viên này khỏi danh sách tham gia chuyến tham quan?", "Xác nhận");
            if (keyPress == 0) {
                System.out.println("checking student and tour id: " + student.getId() + ", " + selectedTour.getId());
                StudentTourService.deleteStudentTour(student.getId(), selectedTour.getId());
                this.selectedTour = TourService.getById(selectedTour.getId());
                reinitialize();
            }
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + ex.getMessage() + "\n" + ex.toString() + "\n", "Phát hiện lỗi");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void rateStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateStudentButtonActionPerformed
        try {
            int index = studentTable.getSelectedRow();
            if (index == -1) {
                MessageDialog.showInfoDialog(this, "Vui chọn chọn sinh viên để thực hiện chức năng này", "Thông báo");
                return;
            }
            Student student = StudentService.getByCode(studentTable.getValueAt(index, 0).toString());

            dispose();
            RateStudentResult rateScreen = new RateStudentResult(selectedTour, student);
            rateScreen.setVisible(true);
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Có lỗi, chi tiết: " + ex.getMessage() + "\n" + ex.toString() + "\n", "Phát hiện lỗi");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_rateStudentButtonActionPerformed

    private String getCellValue(Student stu, StudentTour stuTour, int columnIndex) throws Exception{
        switch (columnIndex) {
            case 0:
                return stu.getCode();
            case 1:
                return stu.getLastName();
            case 2:
                return stu.getFirstName();
            case 3:
                return stu.getBirthDate();
            case 4:
                return ClassroomService.getById(stu.getClassId()).getName();
            case 5:
                return stu.getPhoneNumber();
            case 6:
                return stu.getEmail();
            case 7:
                return stu.getAddress();
            case 8:
                return String.valueOf(stuTour.getRate());
            case 9:
                return stuTour.getResult();
            default:
                return "";
        }
    }

    
    private DefaultTableModel tableModel;

    private void loadTableData() {
        try {
            List<StudentTour> data = selectedTour.getStudentTours();
            tableModel.setRowCount(0);
            if (data != null) {
                for (StudentTour stuTour : data) {
                    Student stu = StudentService.getById(stuTour.getStudentId());
                    tableModel.addRow(
                            new Object[]{
                                stu.getCode(),
                                stu.getLastName(),
                                stu.getFirstName(),
                                stu.getBirthDate(),
                                ClassroomService.getById(stu.getClassId()).getName(),
                                stu.getPhoneNumber(),
                                stu.getEmail(),
                                stu.getAddress(),
                                stuTour.getRate(),
                                stuTour.getResult()
                            });
                }
            }
            tableModel.fireTableDataChanged();
        } catch (Exception ex) {
            MessageDialog.showErrorDialog(this, "Tải dữ liệu cho bảng có lỗi! Chi tiết: " + ex.getMessage(), "Có lỗi xảy ra");
            ex.printStackTrace();
        }
    }

    private void initializeTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã sinh viên", "Họ", "Tên", "Ngày sinh", "Lớp", "SĐT", "Email", "Địa chỉ", "Điểm đánh giá", "Xếp loại"});
        studentTable.setModel(tableModel);
        loadTableData();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageTourStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageTourStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageTourStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageTourStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageTourStudent().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton rateStudentButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel screenTitle;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchInput;
    private javax.swing.JTable studentTable;
    private javax.swing.JLabel tourNameTitle;
    // End of variables declaration//GEN-END:variables
}

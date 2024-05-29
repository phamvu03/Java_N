/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package group8_java.school_business_tour_management.views;

import group8_java.school_business_tour_management.common.MessageDialog;
import group8_java.school_business_tour_management.models.Account;
import group8_java.school_business_tour_management.models.Student;
import group8_java.school_business_tour_management.services.AccountService;
import group8_java.school_business_tour_management.services.StudentService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gialo
 */
public class ChangePassword extends javax.swing.JFrame {

    private static Student loggedInStudent;

    public ChangePassword(Student loggedInStudent) {
        initComponents();
        ChangePassword.loggedInStudent = loggedInStudent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Jl = new javax.swing.JLabel();
        oldPasswordField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        reEnterPasswordField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        newPasswordField = new javax.swing.JTextField();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thay đổi mật khẩu");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        Jl.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Jl.setText("Mật khẩu cũ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Mật khẩu mới");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("Nhập lại mật khẩu");

        jButton4.setBackground(new java.awt.Color(51, 102, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Lưu thay đổi");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        CancelButton.setBackground(new java.awt.Color(255, 0, 102));
        CancelButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CancelButton.setForeground(new java.awt.Color(255, 255, 255));
        CancelButton.setText("Hủy");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jButton4,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 179,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(39, 39, 39)
                                                                .addComponent(CancelButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 179,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                .addComponent(reEnterPasswordField,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 418,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(newPasswordField,
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(Jl,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(oldPasswordField)))
                                                .addGap(0, 18, Short.MAX_VALUE)))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(Jl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oldPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3)
                                .addGap(7, 7, 7)
                                .addComponent(reEnterPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        try {
            Account account = AccountService.getById(loggedInStudent.getAccountId());
            String oldPassword = AccountService.getById(loggedInStudent.getAccountId()).getPassword();
            String oldPasswordField = this.oldPasswordField.getText().trim();
            String newPassword = this.newPasswordField.getText().trim();
            String reNewPassword = this.reEnterPasswordField.getText().trim();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || reNewPassword.isEmpty()) {
                MessageDialog.showErrorDialog(this, "Nhập đủ dữ liệu", "Thông báo");
                return;
            }

            if (!oldPassword.equals(oldPasswordField)) {
                MessageDialog.showErrorDialog(this, "Mật khẩu không đúng", "Thông báo");
                return;
            }

            if (!newPassword.equals(reNewPassword)) {
                MessageDialog.showErrorDialog(this, "Nhập lại mật khẩu không đúng", "Thông báo");
                return;
            }

            account.setPassword(newPassword);
            AccountService.updateAccount(account);
            MessageDialog.showInfoDialog(this, "Đổi mật khẩu thành công", "Thông báo");
            dispose();
            PersonalAccountInformation personalAccountInformationScreen = new PersonalAccountInformation(
                    loggedInStudent);
            personalAccountInformationScreen.setLocationRelativeTo(null);
            personalAccountInformationScreen.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_jButton4ActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CancelButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy cập nhật", "Thông báo",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            dispose();
            try {
                PersonalAccountInformation personalAccountInformationScreen = new PersonalAccountInformation(
                        loggedInStudent);
                personalAccountInformationScreen.setLocationRelativeTo(null);
                personalAccountInformationScreen.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(UpdateStudentProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }// GEN-LAST:event_CancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword(loggedInStudent).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel Jl;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField newPasswordField;
    private javax.swing.JTextField oldPasswordField;
    private javax.swing.JTextField reEnterPasswordField;
    // End of variables declaration//GEN-END:variables
}

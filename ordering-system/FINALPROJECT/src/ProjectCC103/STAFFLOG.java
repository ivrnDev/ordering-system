
package ProjectCC103;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class STAFFLOG extends javax.swing.JFrame {


    public STAFFLOG() {
        initComponents();
    }

    public void Connect() {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            String user = userInput.getText();
            String pwd = new String (passwordInput.getPassword());
            
            PreparedStatement pst = con.prepareStatement("SELECT * FROM employees WHERE `Username` = '"+user+"' AND `Password` = '"+pwd+"'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                new POS().setVisible(true);
                this.setVisible(false);
              

            }else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(STAFFLOG.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        
        
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(218, 176, 6));
        jLabel1.setText("Password:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(330, 210, 100, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(218, 176, 6));
        jLabel2.setText("Username:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(330, 130, 100, 22);

        userInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(userInput);
        userInput.setBounds(330, 160, 240, 30);

        passwordInput.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(passwordInput);
        passwordInput.setBounds(330, 250, 240, 30);

        btnLogin.setBackground(new java.awt.Color(221, 247, 227));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(480, 310, 90, 40);

        btnBack.setBackground(new java.awt.Color(221, 247, 227));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(330, 310, 90, 40);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(218, 176, 6));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("STAFF LOGIN");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(350, 40, 210, 38);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(410, 90, 90, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/login.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 600, 450);

        setSize(new java.awt.Dimension(617, 457));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Connect();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
       new HOME().setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

  
    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new STAFFLOG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables
}

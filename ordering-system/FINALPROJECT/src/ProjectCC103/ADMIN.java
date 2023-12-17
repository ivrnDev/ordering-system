package ProjectCC103;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ADMIN extends javax.swing.JFrame {

    public ADMIN() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);

        salesTable();
        showTable();
        employeeTable();
        totalSales();
        totalEarnings();
        todaySales();
        monthSales();
        yearSales();
        dailyTable();
        monthlyTable();
        annualTable();
        dailyAve();
        monthlyAve();
        annualAve();

    }

    public void showTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM pos");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tableOverview.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }

        } catch (SQLException ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     

    public void salesTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM salestable");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) salesTable.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }

        } catch (SQLException ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void totalEarnings() {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String sql = "SELECT SUM(Price) FROM pos";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                double salesAmount = rs.getDouble(1);

                DecimalFormat df = new DecimalFormat("#,##0.00");
                String pesoSign = "₱ ";

                earnings.setText(pesoSign + df.format(salesAmount));
            }
        } catch (Exception e) {

        }
    }

    public void totalSales() {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String sql = "SELECT SUM(Quantity) FROM pos";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                double salesAmount = rs.getDouble(1);

                DecimalFormat df = new DecimalFormat("#,##0");

                sales.setText(df.format(salesAmount));
            }
        } catch (Exception e) {

        }

    }

    public void fill() {
        DefaultTableModel model = (DefaultTableModel) tableOverview.getModel();
        String search = searchBar.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        tableOverview.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));

    }

    public void employeeTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

        } catch (SQLException ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void todaySales() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String today = "SELECT IFNULL(SUM(Quantity), 0),  IFNULL(SUM(Price), 0) FROM pos WHERE date = CURDATE()";
            PreparedStatement pst = con.prepareStatement(today);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                int salesQuantity = rs.getInt(1);
                double salesAmount = rs.getDouble(2);

                DecimalFormat df = new DecimalFormat("#,##0.00");
                String pesoSign = "₱ ";

                todaySales.setText(Integer.toString(salesQuantity));
                todayEarnings.setText(pesoSign + df.format(salesAmount));
            }
        } catch (Exception e) {

        }
    }

    public void monthSales() {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            String sql = "SELECT SUM(Quantity), SUM(Price) FROM pos WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(DATE) = YEAR(CURRENT_DATE())";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                int salesQuantity = rs.getInt(1);
                double salesAmount = rs.getDouble(2);

                DecimalFormat df = new DecimalFormat("#,##0.00");
                String pesoSign = "₱ ";

                monthlySales.setText(Integer.toString(salesQuantity));
                monthlyEarnings.setText(pesoSign + df.format(salesAmount));

            }

        } catch (SQLException e) {
        }

    }

    public void yearSales() {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            String sql = "SELECT SUM(Quantity), SUM(Price) FROM pos WHERE YEAR(DATE) = YEAR(CURRENT_DATE())";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                int salesQuantity = rs.getInt(1);
                double salesAmount = rs.getDouble(2);

                DecimalFormat df = new DecimalFormat("#,##0.00");
                String pesoSign = "₱ ";

                annualSales.setText(Integer.toString(salesQuantity));
                annualEarnings.setText(pesoSign + df.format(salesAmount));

            }

        } catch (SQLException e) {
        }

    }

    public void dailyTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String dailysql = "SELECT DATE(date), SUM(Quantity), SUM(Price) FROM pos GROUP BY DATE(date)";
            PreparedStatement pst = con.prepareStatement(dailysql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                DefaultTableModel model = (DefaultTableModel) daily.getModel();

                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)});

            }
        } catch (Exception e) {

        }
    }

    public void monthlyTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String monthlysql = "SELECT Year(date), Month(date), SUM(Quantity), SUM(Price) FROM pos GROUP BY Year(date), Month(date)";
            PreparedStatement pst = con.prepareStatement(monthlysql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                DefaultTableModel model = (DefaultTableModel) month.getModel();

                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});

            }
        } catch (Exception e) {

        }

    }

    public void annualTable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String annualsql = "SELECT Year(date), SUM(Quantity), SUM(Price) FROM pos GROUP BY Year(date)";
            PreparedStatement pst = con.prepareStatement(annualsql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                DefaultTableModel model = (DefaultTableModel) year.getModel();

                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)});

            }
        } catch (Exception e) {

        }

    }

    public void dailyAve() {
        double sales = 0.0;
        double price = 0.0;
        int rowCount = daily.getRowCount();
        double averageSales = 0.0;
        double averagePrice = 0.0;

        if (rowCount == 0) {

            averageSales = 0.0;
            averagePrice = 0.0;

        } else {

            for (int row = 0; row < rowCount; row++) {
                sales += Double.parseDouble(daily.getValueAt(row, 1).toString());
                price += Double.parseDouble(daily.getValueAt(row, 2).toString());
            }
            averageSales = sales / rowCount;
            averagePrice = price / rowCount;
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df1 = new DecimalFormat("#,###.##");
        String pesoSign = "₱ ";
        dailyAveSales.setText(df1.format(averageSales));
        dailyAveEarnings.setText(pesoSign + df.format(averagePrice));

    }

    public void monthlyAve() {
        double sales = 0.0;
        double price = 0.0;
        int rowCount = month.getRowCount();
        double averageSales = 0.0;
        double averagePrice = 0.0;

        if (rowCount == 0) {

            averageSales = 0.0;
            averagePrice = 0.0;

        } else {

            for (int row = 0; row < rowCount; row++) {
                sales += Double.parseDouble(month.getValueAt(row, 2).toString());
                price += Double.parseDouble(month.getValueAt(row, 3).toString());
            }
            averageSales = sales / rowCount;
            averagePrice = price / rowCount;
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df1 = new DecimalFormat("#,###.##");
        String pesoSign = "₱ ";
        monthlyAveSales.setText(df1.format(averageSales));
        monthlyAveEarnings.setText(pesoSign + df.format(averagePrice));

    }

    public void annualAve() {
        double sales = 0.0;
        double price = 0.0;
        int rowCount = year.getRowCount();
        double averageSales = 0.0;
        double averagePrice = 0.0;

        if (rowCount == 0) {

            averageSales = 0.0;
            averagePrice = 0.0;

        } else {

            for (int row = 0; row < rowCount; row++) {
                sales += Double.parseDouble(year.getValueAt(row, 1).toString());
                price += Double.parseDouble(year.getValueAt(row, 2).toString());
            }
            averageSales = sales / rowCount;
            averagePrice = price / rowCount;
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat df1 = new DecimalFormat("#,###.##");
        String pesoSign = "₱ ";
        annualAveSales.setText(df1.format(averageSales));
        annualAveEarnings.setText(pesoSign + df.format(averagePrice));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        SIDEBAR = new javax.swing.JPanel();
        DASHBOARDPANE = new javax.swing.JPanel();
        DAShboard = new javax.swing.JLabel();
        REPORTpane = new javax.swing.JPanel();
        REPORT = new javax.swing.JLabel();
        ACCOUNT = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        EMPLOYEEpane = new javax.swing.JPanel();
        EMPLOYEE = new javax.swing.JLabel();
        LOGOUTpane = new javax.swing.JPanel();
        LOGOUT = new javax.swing.JLabel();
        ANALYTICpane1 = new javax.swing.JPanel();
        ANALYTICS1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        OverviewTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        todaySales = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        monthlySales = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        monthlyEarnings = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        annualSales = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        annualEarnings = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        todayEarnings = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        sales = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        earnings = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Analyticstab = new javax.swing.JPanel();
        fs1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        daily = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        month = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        annualAveSales = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        monthlyAveSales = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        monthlyAveEarnings = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        annualAveEarnings = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        year = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        dailyAveSales = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        dailyAveEarnings = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        orderTab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOverview = new javax.swing.JTable();
        searchBar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        EmployeesTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        employeeS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        AccountTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        adminUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        adminPass = new javax.swing.JPasswordField();
        txtLastname = new javax.swing.JTextField();
        txtFirstname = new javax.swing.JTextField();
        txtMiddlename = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(7, 10, 82));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(218, 176, 6));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(218, 176, 6));
        jLabel3.setText("WELCOME TO CZYRAH'S PIZZA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 760, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1370, 100);

        SIDEBAR.setBackground(new java.awt.Color(210, 19, 18));
        SIDEBAR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DASHBOARDPANE.setBackground(new java.awt.Color(221, 247, 227));

        DAShboard.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DAShboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DAShboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/dashboard-layout.png"))); // NOI18N
        DAShboard.setText("  Dashboard");
        DAShboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DAShboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DAShboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DASHBOARDPANELayout = new javax.swing.GroupLayout(DASHBOARDPANE);
        DASHBOARDPANE.setLayout(DASHBOARDPANELayout);
        DASHBOARDPANELayout.setHorizontalGroup(
            DASHBOARDPANELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DASHBOARDPANELayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(DAShboard, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        DASHBOARDPANELayout.setVerticalGroup(
            DASHBOARDPANELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DAShboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SIDEBAR.add(DASHBOARDPANE, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 50));

        REPORTpane.setBackground(new java.awt.Color(221, 247, 227));

        REPORT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        REPORT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        REPORT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orders.png"))); // NOI18N
        REPORT.setText("  Orders");
        REPORT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        REPORT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                REPORTMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout REPORTpaneLayout = new javax.swing.GroupLayout(REPORTpane);
        REPORTpane.setLayout(REPORTpaneLayout);
        REPORTpaneLayout.setHorizontalGroup(
            REPORTpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(REPORTpaneLayout.createSequentialGroup()
                .addComponent(REPORT, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );
        REPORTpaneLayout.setVerticalGroup(
            REPORTpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(REPORT, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SIDEBAR.add(REPORTpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 220, 50));

        ACCOUNT.setBackground(new java.awt.Color(221, 247, 227));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/user-male-circle.png"))); // NOI18N
        jLabel6.setText("  Account");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ACCOUNTLayout = new javax.swing.GroupLayout(ACCOUNT);
        ACCOUNT.setLayout(ACCOUNTLayout);
        ACCOUNTLayout.setHorizontalGroup(
            ACCOUNTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACCOUNTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        ACCOUNTLayout.setVerticalGroup(
            ACCOUNTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SIDEBAR.add(ACCOUNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 220, 50));

        EMPLOYEEpane.setBackground(new java.awt.Color(221, 247, 227));

        EMPLOYEE.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EMPLOYEE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EMPLOYEE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/conference-background-selected.png"))); // NOI18N
        EMPLOYEE.setText("  Employees");
        EMPLOYEE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EMPLOYEE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EMPLOYEEMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout EMPLOYEEpaneLayout = new javax.swing.GroupLayout(EMPLOYEEpane);
        EMPLOYEEpane.setLayout(EMPLOYEEpaneLayout);
        EMPLOYEEpaneLayout.setHorizontalGroup(
            EMPLOYEEpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EMPLOYEEpaneLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(EMPLOYEE, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        EMPLOYEEpaneLayout.setVerticalGroup(
            EMPLOYEEpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EMPLOYEE, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SIDEBAR.add(EMPLOYEEpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 220, 50));

        LOGOUTpane.setBackground(new java.awt.Color(221, 247, 227));

        LOGOUT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LOGOUT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LOGOUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/logout-rounded.png"))); // NOI18N
        LOGOUT.setText("  Logout");
        LOGOUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LOGOUT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LOGOUTMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout LOGOUTpaneLayout = new javax.swing.GroupLayout(LOGOUTpane);
        LOGOUTpane.setLayout(LOGOUTpaneLayout);
        LOGOUTpaneLayout.setHorizontalGroup(
            LOGOUTpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LOGOUTpaneLayout.createSequentialGroup()
                .addComponent(LOGOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        LOGOUTpaneLayout.setVerticalGroup(
            LOGOUTpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LOGOUT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SIDEBAR.add(LOGOUTpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 220, 50));

        ANALYTICpane1.setBackground(new java.awt.Color(221, 247, 227));

        ANALYTICS1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ANALYTICS1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ANALYTICS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/graph-report.png"))); // NOI18N
        ANALYTICS1.setText("  Analytics");
        ANALYTICS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ANALYTICS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ANALYTICS1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ANALYTICpane1Layout = new javax.swing.GroupLayout(ANALYTICpane1);
        ANALYTICpane1.setLayout(ANALYTICpane1Layout);
        ANALYTICpane1Layout.setHorizontalGroup(
            ANALYTICpane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANALYTICpane1Layout.createSequentialGroup()
                .addComponent(ANALYTICS1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
        ANALYTICpane1Layout.setVerticalGroup(
            ANALYTICpane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ANALYTICS1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SIDEBAR.add(ANALYTICpane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 220, 50));

        getContentPane().add(SIDEBAR);
        SIDEBAR.setBounds(0, 100, 260, 610);

        OverviewTab.setBackground(new java.awt.Color(228, 228, 228));
        OverviewTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salesTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Item", "Total Sales", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salesTable.setAutoscrolls(false);
        salesTable.setGridColor(new java.awt.Color(0, 0, 0));
        salesTable.setRowHeight(20);
        salesTable.setSelectionBackground(new java.awt.Color(102, 255, 204));
        salesTable.getTableHeader().setResizingAllowed(false);
        salesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(salesTable);
        if (salesTable.getColumnModel().getColumnCount() > 0) {
            salesTable.getColumnModel().getColumn(0).setResizable(false);
            salesTable.getColumnModel().getColumn(1).setResizable(false);
            salesTable.getColumnModel().getColumn(2).setResizable(false);
            salesTable.getColumnModel().getColumn(3).setResizable(false);
        }

        OverviewTab.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 730, 130));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Today Sales");

        todaySales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        todaySales.setText("0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(todaySales)
                    .addComponent(jLabel16))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addGap(35, 35, 35)
                .addComponent(todaySales)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        OverviewTab.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 230, 160));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Sales this Month");

        monthlySales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        monthlySales.setText("0");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel19))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(monthlySales)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addGap(35, 35, 35)
                .addComponent(monthlySales)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        OverviewTab.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 230, 160));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Earnings this Month");

        monthlyEarnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        monthlyEarnings.setText("0.00");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel20))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(monthlyEarnings)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel20)
                .addGap(34, 34, 34)
                .addComponent(monthlyEarnings)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        OverviewTab.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, 230, 160));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Sales this Year");

        annualSales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        annualSales.setText("0");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel21))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(annualSales)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addGap(37, 37, 37)
                .addComponent(annualSales)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        OverviewTab.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 230, 160));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Earnings this Year");

        annualEarnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        annualEarnings.setText("0.00");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel22))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(annualEarnings)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel22)
                .addGap(34, 34, 34)
                .addComponent(annualEarnings)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        OverviewTab.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 230, 160));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Today Earnings");

        todayEarnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        todayEarnings.setText("0.00");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel18))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(todayEarnings)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel18)
                .addGap(36, 36, 36)
                .addComponent(todayEarnings)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        OverviewTab.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 230, 160));

        jPanel2.setBackground(new java.awt.Color(218, 176, 6));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 255, 204), 5, true));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Total Sales");

        sales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        sales.setText("0");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Total Earnings");

        earnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        earnings.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(earnings, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(sales))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel11)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(sales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(31, 31, 31)
                .addComponent(earnings)
                .addGap(32, 32, 32))
        );

        OverviewTab.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 290, 290));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Dashboard");
        OverviewTab.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orderingSystem.png"))); // NOI18N
        OverviewTab.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 620));

        jTabbedPane1.addTab("", OverviewTab);

        Analyticstab.setBackground(new java.awt.Color(228, 228, 228));
        Analyticstab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fs1.setBackground(new java.awt.Color(0, 102, 153));
        fs1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Analyticstab.add(fs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        daily.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        daily.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Total Sales", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        daily.setGridColor(new java.awt.Color(0, 0, 0));
        daily.setRowSelectionAllowed(false);
        daily.setSelectionBackground(new java.awt.Color(102, 255, 204));
        daily.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(daily);
        if (daily.getColumnModel().getColumnCount() > 0) {
            daily.getColumnModel().getColumn(0).setResizable(false);
            daily.getColumnModel().getColumn(1).setResizable(false);
            daily.getColumnModel().getColumn(2).setResizable(false);
        }

        Analyticstab.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 330, 210));

        month.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        month.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Month", "Total Sales", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        month.setGridColor(new java.awt.Color(0, 0, 0));
        month.setRowSelectionAllowed(false);
        month.setSelectionBackground(new java.awt.Color(102, 255, 204));
        month.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(month);
        if (month.getColumnModel().getColumnCount() > 0) {
            month.getColumnModel().getColumn(0).setResizable(false);
            month.getColumnModel().getColumn(1).setResizable(false);
            month.getColumnModel().getColumn(2).setResizable(false);
            month.getColumnModel().getColumn(3).setResizable(false);
        }

        Analyticstab.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, 370, 210));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        annualAveSales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        annualAveSales.setText("0");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Annual Average Sales");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel23))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(annualAveSales)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(annualAveSales)
                .addGap(34, 34, 34))
        );

        Analyticstab.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, 310, 130));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        monthlyAveSales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        monthlyAveSales.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Monthly Average Sales");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(monthlyAveSales)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(33, 33, 33)
                .addComponent(monthlyAveSales)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        Analyticstab.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 310, 130));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        monthlyAveEarnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        monthlyAveEarnings.setText("0.00");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Monthly Average Earnings");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel17))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(monthlyAveEarnings)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(28, 28, 28)
                .addComponent(monthlyAveEarnings)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        Analyticstab.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 310, 130));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        annualAveEarnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        annualAveEarnings.setText("0.00");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Annual Average Earnings");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel24))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(annualAveEarnings)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(30, 30, 30)
                .addComponent(annualAveEarnings)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        Analyticstab.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 310, 130));

        year.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        year.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Total Sales", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        year.setGridColor(new java.awt.Color(0, 0, 0));
        year.setRowSelectionAllowed(false);
        year.setSelectionBackground(new java.awt.Color(102, 255, 204));
        year.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(year);
        if (year.getColumnModel().getColumnCount() > 0) {
            year.getColumnModel().getColumn(0).setResizable(false);
            year.getColumnModel().getColumn(1).setResizable(false);
            year.getColumnModel().getColumn(2).setResizable(false);
        }

        Analyticstab.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 330, 210));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        dailyAveSales.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        dailyAveSales.setText("0");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Daily Average Sales");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel30))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(dailyAveSales)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGap(35, 35, 35)
                .addComponent(dailyAveSales)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        Analyticstab.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 310, 130));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        dailyAveEarnings.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        dailyAveEarnings.setText("0.00");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("Daily Average Earnings");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel31))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(dailyAveEarnings)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(28, 28, 28)
                .addComponent(dailyAveEarnings)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        Analyticstab.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 310, 130));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Analytics");
        Analyticstab.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orderingSystem.png"))); // NOI18N
        Analyticstab.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 620));

        jTabbedPane1.addTab("", Analyticstab);

        orderTab.setBackground(new java.awt.Color(228, 228, 228));
        orderTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableOverview.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tableOverview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item ID", "Item", "Quantity", "Price", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOverview.setGridColor(new java.awt.Color(0, 0, 0));
        tableOverview.setSelectionBackground(new java.awt.Color(102, 255, 204));
        tableOverview.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableOverview);
        if (tableOverview.getColumnModel().getColumnCount() > 0) {
            tableOverview.getColumnModel().getColumn(0).setResizable(false);
            tableOverview.getColumnModel().getColumn(1).setResizable(false);
            tableOverview.getColumnModel().getColumn(2).setResizable(false);
            tableOverview.getColumnModel().getColumn(3).setResizable(false);
            tableOverview.getColumnModel().getColumn(4).setResizable(false);
        }

        orderTab.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 830, 430));

        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });
        orderTab.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 280, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Search");
        orderTab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orderingSystem.png"))); // NOI18N
        orderTab.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 620));

        jTabbedPane1.addTab("", orderTab);

        EmployeesTab.setBackground(new java.awt.Color(228, 228, 228));
        EmployeesTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        employeeTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Last Name", "First Name", "Middle Name", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeTable.setGridColor(new java.awt.Color(0, 0, 0));
        employeeTable.setSelectionBackground(new java.awt.Color(102, 255, 204));
        employeeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(employeeTable);
        if (employeeTable.getColumnModel().getColumnCount() > 0) {
            employeeTable.getColumnModel().getColumn(0).setResizable(false);
            employeeTable.getColumnModel().getColumn(1).setResizable(false);
            employeeTable.getColumnModel().getColumn(2).setResizable(false);
            employeeTable.getColumnModel().getColumn(3).setResizable(false);
            employeeTable.getColumnModel().getColumn(4).setResizable(false);
            employeeTable.getColumnModel().getColumn(5).setResizable(false);
        }

        EmployeesTab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 740, -1));

        employeeS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeeS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeSActionPerformed(evt);
            }
        });
        employeeS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeSKeyReleased(evt);
            }
        });
        EmployeesTab.add(employeeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 290, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" Search");
        EmployeesTab.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orderingSystem.png"))); // NOI18N
        EmployeesTab.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 620));

        jTabbedPane1.addTab("", EmployeesTab);

        AccountTab.setBackground(new java.awt.Color(228, 228, 228));
        AccountTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADMIN ACCOUNT");
        AccountTab.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(221, 247, 227));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        AccountTab.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, -1, 30));

        btnAdd.setBackground(new java.awt.Color(221, 247, 227));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        AccountTab.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 100, 40));

        adminUser.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(adminUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 250, 30));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, 250, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Username");
        AccountTab.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Password");
        AccountTab.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, -1, -1));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 250, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Middle Name");
        AccountTab.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Last Name");
        AccountTab.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("First Name");
        AccountTab.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Username");
        AccountTab.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Password");
        AccountTab.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        adminPass.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(adminPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, 260, 30));

        txtLastname.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(txtLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 250, 30));

        txtFirstname.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(txtFirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 250, 30));

        txtMiddlename.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AccountTab.add(txtMiddlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 250, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ADD EMPLOYEEES");
        AccountTab.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orderingSystem.png"))); // NOI18N
        AccountTab.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 620));

        jTabbedPane1.addTab("", AccountTab);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(230, 60, 1140, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void DAShboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DAShboardMouseClicked
        jTabbedPane1.setSelectedIndex(0);


    }//GEN-LAST:event_DAShboardMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void EMPLOYEEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EMPLOYEEMouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_EMPLOYEEMouseClicked

    private void REPORTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_REPORTMouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_REPORTMouseClicked

    private void ANALYTICS1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ANALYTICS1MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_ANALYTICS1MouseClicked

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed

    }//GEN-LAST:event_searchBarActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        fill();
    }//GEN-LAST:event_searchBarKeyReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");
            PreparedStatement pst = con.prepareStatement("UPDATE admin SET `Username`= ?,`Password`= ?");
            String user = adminUser.getText();
            String pwd = new String(adminPass.getPassword());
            pst.setString(1, user);
            pst.setString(2, pwd);
            pst.executeUpdate();

            adminUser.setText("");
            adminPass.setText("");

            JOptionPane.showMessageDialog(this, "Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            String Lname = txtLastname.getText();
            String Fname = txtFirstname.getText();
            String Mname = txtMiddlename.getText();
            String Username = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());

            String sql = "INSERT INTO `employees`(`Last Name`, `First Name`, `Middle Name`, `Username`, `Password`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, Lname);
            statement.setString(2, Fname);
            statement.setString(3, Mname);
            statement.setString(4, Username);
            statement.setString(5, pass);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Successfully Registered");
            txtLastname.setText("");
            txtFirstname.setText("");
            txtMiddlename.setText("");
            txtUsername.setText("");
            txtPassword.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(ADMIN.class.getName()).log(Level.SEVERE, null, ex);
        }

        employeeTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void employeeSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeSActionPerformed

    private void employeeSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeSKeyReleased
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        String search = employeeS.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        employeeTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_employeeSKeyReleased

    private void LOGOUTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LOGOUTMouseClicked
        JFrame frame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Do you really want to logout?", "LOGOUT",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            new HOME().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_LOGOUTMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ACCOUNT;
    private javax.swing.JLabel ANALYTICS1;
    private javax.swing.JPanel ANALYTICpane1;
    private javax.swing.JPanel AccountTab;
    private javax.swing.JPanel Analyticstab;
    private javax.swing.JPanel DASHBOARDPANE;
    private javax.swing.JLabel DAShboard;
    private javax.swing.JLabel EMPLOYEE;
    private javax.swing.JPanel EMPLOYEEpane;
    private javax.swing.JPanel EmployeesTab;
    private javax.swing.JLabel LOGOUT;
    private javax.swing.JPanel LOGOUTpane;
    private javax.swing.JPanel OverviewTab;
    private javax.swing.JLabel REPORT;
    private javax.swing.JPanel REPORTpane;
    private javax.swing.JPanel SIDEBAR;
    private javax.swing.JPasswordField adminPass;
    private javax.swing.JTextField adminUser;
    private javax.swing.JLabel annualAveEarnings;
    private javax.swing.JLabel annualAveSales;
    private javax.swing.JLabel annualEarnings;
    private javax.swing.JLabel annualSales;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTable daily;
    private javax.swing.JLabel dailyAveEarnings;
    private javax.swing.JLabel dailyAveSales;
    private javax.swing.JLabel earnings;
    private javax.swing.JTextField employeeS;
    private javax.swing.JTable employeeTable;
    private javax.swing.JPanel fs1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable month;
    private javax.swing.JLabel monthlyAveEarnings;
    private javax.swing.JLabel monthlyAveSales;
    private javax.swing.JLabel monthlyEarnings;
    private javax.swing.JLabel monthlySales;
    private javax.swing.JPanel orderTab;
    private javax.swing.JLabel sales;
    private javax.swing.JTable salesTable;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable tableOverview;
    private javax.swing.JLabel todayEarnings;
    private javax.swing.JLabel todaySales;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtMiddlename;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTable year;
    // End of variables declaration//GEN-END:variables

}

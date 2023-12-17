package ProjectCC103;

import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class POS extends javax.swing.JFrame {

    public POS() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        scaleImages();
       
    }
public void scaleImages(){
    ImageIcon icon = new ImageIcon("C:\\Users\\QCU\\Documents\\NetBeansProjects\\FINALPROJECT\\src\\Picture\\FhamCheese.png");
    Image img = icon.getImage();
    Image imgScale = img.getScaledInstance(jLabel6.getWidth(), jLabel6.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(imgScale);    
    jLabel6.setIcon(scaledIcon);
 
    ImageIcon icon1 = new ImageIcon("C:\\Users\\QCU\\Documents\\NetBeansProjects\\FINALPROJECT\\src\\Picture\\Fvegeterian.png");  
    Image img1 = icon1.getImage();
    Image imgScale1 = img1.getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon1 = new ImageIcon(imgScale1);    
    jLabel9.setIcon(scaledIcon1);
    
    ImageIcon icon2 = new ImageIcon("C:\\Users\\QCU\\Documents\\NetBeansProjects\\FINALPROJECT\\src\\Picture\\Fpepperoni.png");
    Image img2 = icon2.getImage();
    Image imgScale2 = img2.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon2 = new ImageIcon(imgScale2);    
    jLabel8.setIcon(scaledIcon2);
    
    ImageIcon icon3 = new ImageIcon("C:\\Users\\QCU\\Documents\\NetBeansProjects\\FINALPROJECT\\src\\Picture\\Fhawaiian.png");
    Image img3 = icon3.getImage();
    Image imgScale3 = img3.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon3 = new ImageIcon(imgScale3);    
    jLabel10.setIcon(scaledIcon3);
    
    ImageIcon icon4 = new ImageIcon("C:\\Users\\QCU\\Documents\\NetBeansProjects\\FINALPROJECT\\src\\Picture\\Fbacon.png");
    Image img4 = icon4.getImage();
    Image imgScale4 = img4.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon4 = new ImageIcon(imgScale4);    
    jLabel4.setIcon(scaledIcon4);
    
    }



    @SuppressWarnings("unchecked")

    public void Connect() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();

            for (int i = 0; i < model.getRowCount(); i++) {
                String ID = model.getValueAt(i, 0).toString();
                String Item = model.getValueAt(i, 1).toString();
                String Qty = model.getValueAt(i, 2).toString();
                String Price = model.getValueAt(i, 3).toString();

                String sql = "INSERT INTO pos (`Product ID`, Item, Quantity, Price, Date, Time) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(sql);
                LocalDateTime currentDateTime = LocalDateTime.now();
                statement.setString(1, ID);
                statement.setString(2, Item);
                statement.setString(3, Qty);
                statement.setString(4, Price);
                statement.setObject(5, currentDateTime.toLocalDate());
                statement.setObject(6, currentDateTime.toLocalTime());
                statement.executeUpdate();

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "THE DATABASE HAS NOT YET CONNECTED", "WARNING", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void ItemCost() {
        double total = 0.0;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            double price = (double) receiptTable.getValueAt(row, 3);

            total = total + price;
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String pesoSign = "₱ ";
        displaySubtotal.setText(pesoSign + df.format(total));

    }

    public void tax() {
        double tax = 0.0;
        double vat = 6;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            double price = (double) receiptTable.getValueAt(row, 3);

            double perc = vat / 100;
            double result = price * perc;

            tax = tax + result;
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String pesoSign = "₱ ";

        displayVat.setText(pesoSign + df.format(tax));

    }

    public void total() {

        double totalPrice = 0.0;
        double vat = 6;

         if(receiptTable.getRowCount() != 0) {
        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            double price = (double) receiptTable.getValueAt(row, 3);

            double perc = vat / 100;
            totalPrice += price;

            double totalTax = price * perc;

            double result = totalPrice + totalTax;

            DecimalFormat df = new DecimalFormat("#,##0.00");
            String pesoSign = "₱ ";

            displayTotal.setText(pesoSign + df.format(result));
        
        }
    
        }else {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            String pesoSign = "₱ ";
            displayTotal.setText(pesoSign + df.format(0.00));
        
         }
    }

    public void bestSeller() {

        DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc103", "root", "");

            for (int i = 0; i < model.getRowCount(); i++) {

                String TotalSale = model.getValueAt(i, 2).toString();
                String TotalAmount = model.getValueAt(i, 3).toString();
                String ID = model.getValueAt(i, 0).toString();

                String sql = "UPDATE salestable SET `Total Sales` = `Total Sales` + ?, `Total Amount` = `Total Amount` + ? WHERE `Product ID` = ?";
                PreparedStatement statement = con.prepareStatement(sql);

                statement.setString(1, TotalSale);
                statement.setString(2, TotalAmount);
                statement.setString(3, ID);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);

        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        receiptTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayReceipt = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        displaySubtotal = new javax.swing.JLabel();
        displayTotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        displayVat = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cashInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        displayChange = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        bntPrint = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnPay = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn9 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 730));
        getContentPane().setLayout(null);

        receiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        receiptTable.setGridColor(new java.awt.Color(0, 0, 0));
        receiptTable.setSelectionBackground(new java.awt.Color(153, 255, 153));
        receiptTable.setShowHorizontalLines(true);
        receiptTable.getTableHeader().setResizingAllowed(false);
        receiptTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(receiptTable);
        if (receiptTable.getColumnModel().getColumnCount() > 0) {
            receiptTable.getColumnModel().getColumn(0).setResizable(false);
            receiptTable.getColumnModel().getColumn(1).setResizable(false);
            receiptTable.getColumnModel().getColumn(2).setResizable(false);
            receiptTable.getColumnModel().getColumn(3).setResizable(false);
        }
        receiptTable.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(380, 20, 310, 460);

        jPanel1.setBackground(new java.awt.Color(223, 46, 56));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 150, 150));

        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 150));

        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 150, 150));

        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 150));

        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 150, 150));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 20, 350, 460);

        displayReceipt.setEditable(false);
        displayReceipt.setBackground(new java.awt.Color(255, 255, 255));
        displayReceipt.setColumns(20);
        displayReceipt.setRows(5);
        jScrollPane2.setViewportView(displayReceipt);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(700, 20, 300, 460);

        jPanel2.setBackground(new java.awt.Color(223, 46, 56));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(25, 24, 24));
        jLabel1.setText("TOTAL:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(25, 24, 24));
        jLabel5.setText("SUBTOTAL:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        displaySubtotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        displaySubtotal.setForeground(new java.awt.Color(25, 24, 24));
        displaySubtotal.setText("0.00");
        jPanel2.add(displaySubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        displayTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        displayTotal.setForeground(new java.awt.Color(25, 24, 24));
        displayTotal.setText("0.00");
        jPanel2.add(displayTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 24, 24));
        jLabel7.setText("VAT:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        displayVat.setBackground(new java.awt.Color(255, 255, 255));
        displayVat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        displayVat.setForeground(new java.awt.Color(25, 24, 24));
        displayVat.setText("0.00");
        jPanel2.add(displayVat, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 520, 310, 170);

        jPanel3.setBackground(new java.awt.Color(223, 46, 56));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 24, 24));
        jLabel2.setText("CASH");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        cashInput.setBackground(new java.awt.Color(221, 247, 227));
        cashInput.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(cashInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 210, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(25, 24, 24));
        jLabel3.setText("CHANGE:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        displayChange.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        displayChange.setForeground(new java.awt.Color(25, 24, 24));
        displayChange.setText("0.00");
        jPanel3.add(displayChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(370, 520, 350, 170);

        jPanel4.setBackground(new java.awt.Color(223, 46, 56));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bntPrint.setBackground(new java.awt.Color(221, 247, 227));
        bntPrint.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        bntPrint.setText("PRINT");
        bntPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintActionPerformed(evt);
            }
        });
        jPanel4.add(bntPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 140, 70));

        btnRemove.setBackground(new java.awt.Color(221, 247, 227));
        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnRemove.setText("REMOVE");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        jPanel4.add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 140, 70));

        btnPay.setBackground(new java.awt.Color(221, 247, 227));
        btnPay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnPay.setText("PAY");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });
        jPanel4.add(btnPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 70));

        getContentPane().add(jPanel4);
        jPanel4.setBounds(770, 520, 350, 170);

        jPanel5.setBackground(new java.awt.Color(223, 46, 56));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn9.setBackground(new java.awt.Color(221, 247, 227));
        btn9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel5.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 90, 90));

        btn8.setBackground(new java.awt.Color(221, 247, 227));
        btn8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel5.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 90, 90));

        btn4.setBackground(new java.awt.Color(221, 247, 227));
        btn4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel5.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 90));

        btn5.setBackground(new java.awt.Color(221, 247, 227));
        btn5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel5.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 90, 90));

        btn6.setBackground(new java.awt.Color(221, 247, 227));
        btn6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel5.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 90, 90));

        btn1.setBackground(new java.awt.Color(221, 247, 227));
        btn1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel5.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, 90));

        btn2.setBackground(new java.awt.Color(221, 247, 227));
        btn2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel5.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 90, 90));

        btn3.setBackground(new java.awt.Color(221, 247, 227));
        btn3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel5.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 90, 90));

        btnDot.setBackground(new java.awt.Color(221, 247, 227));
        btnDot.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btnDot.setText(".");
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        jPanel5.add(btnDot, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 90, 90));

        btn0.setBackground(new java.awt.Color(221, 247, 227));
        btn0.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        jPanel5.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 90, 90));

        btnDelete.setBackground(new java.awt.Color(221, 247, 227));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btnDelete.setText("C");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 90, 90));

        btn7.setBackground(new java.awt.Color(221, 247, 227));
        btn7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel5.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 90));

        getContentPane().add(jPanel5);
        jPanel5.setBounds(1010, 20, 330, 460);

        btnExit.setBackground(new java.awt.Color(221, 247, 227));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(1170, 610, 140, 70);

        btnReset.setBackground(new java.awt.Color(221, 247, 227));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset);
        btnReset.setBounds(1170, 520, 140, 70);

        jLabel11.setForeground(new java.awt.Color(25, 24, 24));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/orderingSystem.png"))); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 0, 1360, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
        model.setRowCount(0);
        displayChange.setText("0.00");
        cashInput.setText("");
        displaySubtotal.setText("0.00");
        displayReceipt.setText("");
        displayTotal.setText("0.00");
        displayVat.setText("0.00");

    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        JFrame frame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Point of Sale",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            new HOME().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void bntPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintActionPerformed
        try {
            displayReceipt.print();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_bntPrintActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        DefaultTableModel dt = (DefaultTableModel) receiptTable.getModel();
        int rw = receiptTable.getSelectedRow();
        dt.removeRow(rw);
        ItemCost();
        total();
        tax();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed

        double total = 0.0;
        double cash = 0.0;

        try {
            cash = Double.parseDouble(cashInput.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalPrice = 0.0;
        double vat = 6;

        String totalGet = displayTotal.getText().toString();
        String totalval = totalGet.replace("₱ ", "").replace(",", "");
        double totalValue = Double.parseDouble(totalval);
        double change = cash - totalValue;

        DecimalFormat df = new DecimalFormat("#,###.00");
        String pesoSign = "₱ ";
        double inpCash = Double.parseDouble(cashInput.getText().toString());
        String cashInp = pesoSign + df.format(inpCash);

        
        if (change < 0) {

            JOptionPane.showMessageDialog(this, "Insufficient amount. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Connect();
            bestSeller();
            displayChange.setText(pesoSign + df.format(change));
            try {
                DefaultTableModel DF = (DefaultTableModel) receiptTable.getModel();

                String formatString = "%-20s %-10s %-10s%n";
                String header = String.format(formatString, "  Item", "Qty", "   Price");
                String separator = "------------------------------------------\n";

                StringBuilder receiptBuilder = new StringBuilder();
                receiptBuilder.append("\t       Czyrah's Pizza \n")
                        .append("   673 Quirino Highway, San Bartolome, \n")
                        .append("\t Novaliches, Quezon City, \n")
                        .append("\t       +63 928806347 \n")
                        .append(separator)
                        .append(header)
                        .append(separator);

                int rowCount = DF.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String itemName = DF.getValueAt(i, 1).toString();
                    String itemQty = DF.getValueAt(i, 2).toString();
                    String itemPrice = DF.getValueAt(i, 3).toString();
                    String line = String.format(formatString, itemName, itemQty, itemPrice);
                   
                    receiptBuilder.append(line);




                }

                receiptBuilder.append(separator)
                        .append("  Total : " + displayTotal.getText() + "\n")
                        .append("  Paid :  " + cashInp + "\n")
                        .append("  Change : " + displayChange.getText() + "\n")
                        .append(separator)
                        .append("     Thanks For Buying Our Products!" + "\n")
                        .append(separator)
                        .append("\t     By Group 6 corp." + "\n");

                displayReceipt.setFont(new Font("Monospaced", Font.PLAIN, 12));
                displayReceipt.setText(receiptBuilder.toString());
                JOptionPane.showMessageDialog(this, "Order Success!");
                
                  } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);

            }

        }

    }//GEN-LAST:event_btnPayActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        String button9 = btn9.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button9;

        if (cashDisplay == "") {
            cashInput.setText(button9);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        String button8 = btn8.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button8;

        if (cashDisplay == "") {
            cashInput.setText(button8);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        String button4 = btn4.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button4;

        if (cashDisplay == "") {
            cashInput.setText(button4);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        String button5 = btn5.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button5;

        if (cashDisplay == "") {
            cashInput.setText(button5);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        String button6 = btn6.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button6;

        if (cashDisplay == "") {
            cashInput.setText(button6);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        String button1 = btn1.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button1;

        if (cashDisplay == "") {
            cashInput.setText(button1);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        String button2 = btn2.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button2;

        if (cashDisplay == "") {
            cashInput.setText(button2);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        String button3 = btn3.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button3;

        if (cashDisplay == "") {
            cashInput.setText(button3);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        String btndot = btnDot.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + btndot;

        if (!cashInput.getText().contains(".")) {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btnDotActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        String button0 = btn0.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button0;

        if (cashDisplay == "") {
            cashInput.setText(button0);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        cashInput.setText("");
        displayChange.setText("0.00");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        String button7 = btn7.getText();
        String cashDisplay = cashInput.getText();
        String addCashDisplay = cashDisplay + button7;

        if (cashDisplay == "") {
            cashInput.setText(button7);
        } else {
            cashInput.setText(addCashDisplay);
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        double itemPrice = 75;
        boolean itemAlreadyAdded = false;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("HAM & CHEESE")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity + 1, row, 2);
                itemAlreadyAdded = true;
                break;
            }
        }
        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("HAM & CHEESE")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity * itemPrice, row, 3);
                itemAlreadyAdded = true;
                break;
            }
        }
        if (!itemAlreadyAdded) {
            DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
            model.addRow(new Object[]{1, "HAM & CHEESE", 1, itemPrice});
        }
        ItemCost();
        tax();
        total();
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        double itemPrice = 85;
        boolean itemAlreadyAdded = false;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("PEPPERONI")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity + 1, row, 2);
                itemAlreadyAdded = true;
                break;
            }
        }
        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("PEPPERONI")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity * itemPrice, row, 3);
                itemAlreadyAdded = true;
                break;
            }
        }

        if (!itemAlreadyAdded) {
            DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
            model.addRow(new Object[]{2, "PEPPERONI", 1, itemPrice});
        }

        ItemCost();
        tax();
        total();
        
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        double itemPrice = 75;
        boolean itemAlreadyAdded = false;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("VEGETARIAN")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity + 1, row, 2);
                itemAlreadyAdded = true;
                break;
            }
        }
        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("VEGETARIAN")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity * itemPrice, row, 3);
                itemAlreadyAdded = true;
                break;
            }
        }

        if (!itemAlreadyAdded) {
            DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
            model.addRow(new Object[]{3, "VEGETARIAN", 1, itemPrice});
        }

        ItemCost();
        tax();
        total();
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        double itemPrice = 85;
        boolean itemAlreadyAdded = false;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("HAWAIIAN")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity + 1, row, 2);
                itemAlreadyAdded = true;
                break;
            }
        }
        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("HAWAIIAN")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity * itemPrice, row, 3);
                itemAlreadyAdded = true;
                break;
            }
        }

        if (!itemAlreadyAdded) {
            DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
            model.addRow(new Object[]{4, "HAWAIIAN", 1, itemPrice});
        }

        ItemCost();
        tax();
        total();
        
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        double itemPrice = 95;
        boolean itemAlreadyAdded = false;

        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("BACON")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity + 1, row, 2);
                itemAlreadyAdded = true;
                break;
            }
        }
        for (int row = 0; row < receiptTable.getRowCount(); row++) {
            if (receiptTable.getValueAt(row, 1).equals("BACON")) {
                int currentQuantity = (int) receiptTable.getValueAt(row, 2);
                receiptTable.setValueAt(currentQuantity * itemPrice, row, 3);
                itemAlreadyAdded = true;
                break;
            }
        }

        if (!itemAlreadyAdded) {
            DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
            model.addRow(new Object[]{5, "BACON", 1, itemPrice});
        }

        ItemCost();
        tax();
        total();
        
    }//GEN-LAST:event_jLabel4MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntPrint;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextField cashInput;
    private javax.swing.JLabel displayChange;
    private javax.swing.JTextArea displayReceipt;
    private javax.swing.JLabel displaySubtotal;
    private javax.swing.JLabel displayTotal;
    private javax.swing.JLabel displayVat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable receiptTable;
    // End of variables declaration//GEN-END:variables
}

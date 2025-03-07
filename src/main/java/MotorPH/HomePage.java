package MotorPH;

import com.opencsv.exceptions.CsvValidationException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomePage extends javax.swing.JFrame {

    private Employee currentUser;

    private JButton addUpdateDeleteBtn;
    private JButton viewAllSalaryBtn;
    private JButton viewLeaveBtn;
    private JButton viewEmployeeDetailsBtn;
    private JButton viewSalaryBtn;
    private JButton requestLeaveBtn;
    private JButton viewPersonalDetailsBtn;

    private JPanel buttonPanel;
    private JPanel centerPanel;

    public HomePage(Employee user) throws IOException, CsvValidationException {
        this.currentUser = user;
        initComponents();
        initializeUI();
        configureButtons();
        showDate();
        this.setLocationRelativeTo(null);
    }

    public void showDate() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        date.setText(s.format(d));
    }

    public JButton getAddUpdateDeleteBtn() {
        return addUpdateDeleteBtn;
    }

    public JButton getViewAllSalaryBtn() {
        return viewAllSalaryBtn;
    }

    public JButton getViewLeaveBtn() {
        return viewLeaveBtn;
    }

    public JButton getViewEmployeeDetailsBtn() {
        return viewEmployeeDetailsBtn;
    }

    public JButton getViewSalaryBtn() {
        return viewSalaryBtn;
    }

    public JButton getRequestLeaveBtn() {
        return requestLeaveBtn;
    }

    public JButton getViewPersonalDetailsBtn() {
        return viewPersonalDetailsBtn;
    }

    public Label getLabel1() {
        return label1;
    }

    public Employee getCurrentUser() {
        return currentUser;
    }

    private void initializeUI() {
        // Initialize buttons
        addUpdateDeleteBtn = new JButton("Manage Employees"); //Admin
        viewAllSalaryBtn = new JButton("View All Salaries"); //Admin, Manager
        viewLeaveBtn = new JButton("View Leave Requests"); // Admin, Manager
        viewEmployeeDetailsBtn = new JButton("View Employee Details"); //Manager
        viewPersonalDetailsBtn = new JButton("View Personal Details"); //Regular Employee
        viewSalaryBtn = new JButton("View Salary"); //Regular Employee
        requestLeaveBtn = new JButton("Request Leave"); //Regular Employee

        //Button action listeners
        
        //Admin
        addUpdateDeleteBtn.addActionListener(e -> {
            if (currentUser instanceof Admin admin) {
                admin.viewEmployeeDetails();
                this.dispose();
            }
        });
        //Admin & Manager
        viewAllSalaryBtn.addActionListener(e -> {
            if (currentUser instanceof Admin admin) {
                admin.viewSalary();
            } else if (currentUser instanceof Manager manager) {
                ViewSalary viewSalary = new ViewSalary(this, manager.getEmployeeNo());
                viewSalary.setCurrentUser(currentUser);
                manager.viewSalary();
            }
            this.dispose();
        });
        //Admin & Manager
        viewLeaveBtn.addActionListener(e -> {
            if (currentUser instanceof Admin admin) {
                admin.processLeaveRequest();
            } else if (currentUser instanceof Manager manager) {
                manager.processLeaveRequest();
            }
            this.dispose();
        });
        //Manager
        viewEmployeeDetailsBtn.addActionListener(e -> {
            if (currentUser instanceof Manager manager) {
                manager.viewEmployeeDetails();
                this.dispose();
            }
        });
        //Regular Employee
        requestLeaveBtn.addActionListener(e -> {
            if (currentUser instanceof RegularEmployee regular) {
                try {
                    ViewRequest requestPage = new ViewRequest(this);
                    regular.processLeaveRequest();
                    this.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Regular Employee
        viewPersonalDetailsBtn.addActionListener(e -> {
            if (currentUser instanceof RegularEmployee regular) {
                regular.viewEmployeeDetails();
                this.dispose();
            }
        });
        //Regular Employee
        viewSalaryBtn.addActionListener(e -> {
            if (currentUser instanceof RegularEmployee regular) {
                //ViewSalary viewSalary = new ViewSalary(this, regular.getEmployeeNo());
                //viewSalary.setCurrentUser(currentUser);
                regular.viewSalary();
                this.dispose();
            }
        });

        // Set button size
        Dimension buttonSize = new Dimension(200, 50);
        addUpdateDeleteBtn.setPreferredSize(buttonSize);
        viewAllSalaryBtn.setPreferredSize(buttonSize);
        viewLeaveBtn.setPreferredSize(buttonSize);
        viewEmployeeDetailsBtn.setPreferredSize(buttonSize);
        viewPersonalDetailsBtn.setPreferredSize(buttonSize);
        viewSalaryBtn.setPreferredSize(buttonSize);
        requestLeaveBtn.setPreferredSize(buttonSize);

        // Panel with GridLayout (1 column, multiple rows)
        buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        // Wrapper panel for centering
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 150));
        centerPanel.add(buttonPanel);

        // Set frame layout
        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureButtons() {
        buttonPanel.removeAll();
        currentUser.accessPermissions(this); //Let Employee subclass decide buttons
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    /*private void openFrame(JFrame frame) {
        frame.setVisible(true);
        dispose(); // Close the current frame
    }*/
    public void addButton(JButton button) {
        buttonPanel.add(button);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        date = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MotorPH System");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(700, 540));
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 100));
        jPanel1.setLayout(null);

        label1.setBackground(new java.awt.Color(242, 242, 242));
        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setName(""); // NOI18N
        label1.setText("Employee ID: " + currentUser.getEmployeeNo());
        label1.setVisible(false);
        jPanel1.add(label1);
        label1.setBounds(20, 70, 170, 20);

        date.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        date.setText("jLabel1");
        jPanel1.add(date);
        date.setBounds(590, 70, 100, 16);

        logoutButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutButton)
                .addContainerGap(405, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        LogInPage login = new LogInPage();
        this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private javax.swing.JButton logoutButton;
    // End of variables declaration//GEN-END:variables
}

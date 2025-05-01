/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Osama
 */
public class AdminView extends javax.swing.JFrame {

    /**
     * Creates new form AdminView
     */
    private javax.swing.border.TitledBorder panelBorder;
    private javax.swing.JLabel selectedLabel;
    private final java.awt.Color HOVER_COLOR = new java.awt.Color(204, 229, 255);
    private final java.awt.Color SELECTED_COLOR = new java.awt.Color(153, 204, 255);
    private final java.awt.Color DEFAULT_COLOR = new java.awt.Color(230, 230, 230);

    private ArrayList<Student> students;
    private ArrayList<Admin> admins;
    private ArrayList<Teacher> teachers;
    private ArrayList<Module> modules;
    private ArrayList<Assessment> assessments;
    private ArrayList<Payment> payments;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Session> sessions;
    private ArrayList<Enrollment> enrollments;

    public AdminView() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        panelBorder = (javax.swing.border.TitledBorder) jPanel3.getBorder();

        loadAllData();

        setupSidebarEffects();

        setSelectedSidebarItem(jLabel1);

        updateMainPanel("Students");

        setupButtonListeners();
    }

    /**
     * Load all data from files using FileDataStore
     */
    private void loadAllData() {
        try {

            students = FileDataStore.loadStudents();
            admins = FileDataStore.loadAdmins();
            teachers = FileDataStore.loadTeachers();
            modules = FileDataStore.loadModules();
            classrooms = FileDataStore.loadClassrooms();
            sessions = FileDataStore.loadSessions(modules, classrooms);

            enrollments = FileDataStore.loadEnrollments(students, modules);

            payments = FileDataStore.loadPayments(students);

            assessments = new ArrayList<>();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + e.getMessage(),
                    "Data Loading Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Set up action listeners for action buttons
     */
    private void setupButtonListeners() {

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleAddAction(selectedLabel.getText());
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleUpdateAction(selectedLabel.getText());
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleDeleteAction(selectedLabel.getText());
            }
        });

    }

    /**
     * Handle add action based on the selected category
     *
     * @param category The current selected category
     */
    private void handleAddAction(String category) {
        switch (category) {
            case "Students":
                addStudent();
                break;
            case "Teachers":
                addTeacher();
                break;
            case "Modules":
                addModule();
                break;
            case "Assessments":
                addAssessment();
                break;
            case "Payments":
                addPayment();
                break;
            case "Admins":
                addAdmin();
                break;
            case "Sessions":
                addSession();
                break;
            case "Classrooms":
                addClassroom();
                break;
            case "Enrollments":
                addEnrollment();
                break;
            default:
                break;
        }
    }

    /**
     * Handle update action based on the selected category
     *
     * @param category The current selected category
     */
    private void handleUpdateAction(String category) {

        int selectedRow = tblCourses.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a row to update",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        switch (category) {
            case "Students":
                updateStudent(selectedRow);
                break;
            case "Teachers":
                updateTeacher(selectedRow);
                break;
            case "Modules":
                updateModule(selectedRow);
                break;
            case "Assessments":
                updateAssessment(selectedRow);
                break;
            case "Payments":
                updatePayment(selectedRow);
                break;
            case "Admins":
                updateAdmin(selectedRow);
                break;
            case "Sessions":
                updateSession(selectedRow);
                break;
            case "Classrooms":
                updateClassroom(selectedRow);
                break;
            case "Enrollments":
                updateEnrollment(selectedRow);
                break;
            default:
                break;
        }
    }

    /**
     * Handle delete action based on the selected category
     *
     * @param category The current selected category
     */
    private void handleDeleteAction(String category) {

        int selectedRow = tblCourses.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a row to delete",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this " + category.substring(0, category.length() - 1) + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        switch (category) {
            case "Students":
                deleteStudent(selectedRow);
                break;
            case "Teachers":
                deleteTeacher(selectedRow);
                break;
            case "Modules":
                deleteModule(selectedRow);
                break;
            case "Assessments":
                deleteAssessment(selectedRow);
                break;
            case "Payments":
                deletePayment(selectedRow);
                break;
            case "Admins":
                deleteAdmin(selectedRow);
                break;
            case "Sessions":
                deleteSession(selectedRow);
                break;
            case "Classrooms":
                deleteClassroom(selectedRow);
                break;
            case "Enrollments":
                deleteEnrollment(selectedRow);
                break;
            default:
                break;
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
 
         jPanel1 = new javax.swing.JPanel();
         lblWelcome = new javax.swing.JLabel();
         btnLogout = new javax.swing.JButton();
         jPanel2 = new javax.swing.JPanel();
         jPanel5 = new javax.swing.JPanel();
         jLabel1 = new javax.swing.JLabel();
         jLabel6 = new javax.swing.JLabel();
         jLabel2 = new javax.swing.JLabel();
         jLabel3 = new javax.swing.JLabel();
         jLabel4 = new javax.swing.JLabel();
         jLabel5 = new javax.swing.JLabel();
         jLabel7 = new javax.swing.JLabel();
         jLabel8 = new javax.swing.JLabel();
         jLabel9 = new javax.swing.JLabel();
         jPanel3 = new javax.swing.JPanel();
         jScrollPane1 = new javax.swing.JScrollPane();
         tblCourses = new javax.swing.JTable();
         jPanel4 = new javax.swing.JPanel();
         jButton1 = new javax.swing.JButton();
         jButton2 = new javax.swing.JButton();
         jButton3 = new javax.swing.JButton();
         jButton4 = new javax.swing.JButton();
 
         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 
         jPanel1.setBackground(new java.awt.Color(51, 153, 255));
 
         lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
         lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
         lblWelcome.setText("Admin Dashboard");
 
         btnLogout.setBackground(new java.awt.Color(204, 204, 204));
         btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
         btnLogout.setText("Logout");
         btnLogout.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 btnLogoutActionPerformed(evt);
             }
         });
 
         javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
         jPanel1.setLayout(jPanel1Layout);
         jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel1Layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(lblWelcome)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 425, Short.MAX_VALUE)
                 .addComponent(btnLogout)
                 .addGap(23, 23, 23))
         );
         jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                 .addContainerGap(29, Short.MAX_VALUE)
                 .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(lblWelcome)
                     .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(33, 33, 33))
         );
 
         jPanel5.setBackground(new java.awt.Color(230, 230, 230));
 
         jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel1.setText("Students");
 
         jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel6.setText("Admins");
 
         jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel2.setText("Teachers");
 
         jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel3.setText("Modules");
 
         jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel4.setText("Assessments");
 
         jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel5.setText("Payments");
         
         jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel7.setText("Sessions");
         
         jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel8.setText("Classrooms");
         
         jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
         jLabel9.setText("Enrollments");
 
         javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
         jPanel5.setLayout(jPanel5Layout);
         jPanel5Layout.setHorizontalGroup(
             jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel3)
                     .addComponent(jLabel2)
                     .addComponent(jLabel6)
                     .addComponent(jLabel1)
                     .addComponent(jLabel4)
                     .addComponent(jLabel5)
                     .addComponent(jLabel7)
                     .addComponent(jLabel8)
                     .addComponent(jLabel9))
                 .addGap(50, 50, 50))
         );
         jPanel5Layout.setVerticalGroup(
             jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel5Layout.createSequentialGroup()
                 .addGap(17, 17, 17)
                 .addComponent(jLabel1)
                 .addGap(21, 21, 21)
                 .addComponent(jLabel6)
                 .addGap(18, 18, 18)
                 .addComponent(jLabel2)
                 .addGap(25, 25, 25)
                 .addComponent(jLabel3)
                 .addGap(25, 25, 25)
                 .addComponent(jLabel4)
                 .addGap(25, 25, 25)
                 .addComponent(jLabel5)
                 .addGap(25, 25, 25)
                 .addComponent(jLabel7)
                 .addGap(25, 25, 25)
                 .addComponent(jLabel8)
                 .addGap(25, 25, 25)
                 .addComponent(jLabel9)
                 .addGap(82, 82, 82))
         );
 
         javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
         jPanel2.setLayout(jPanel2Layout);
         jPanel2Layout.setHorizontalGroup(
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel2Layout.createSequentialGroup()
                 .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(0, 0, Short.MAX_VALUE))
         );
         jPanel2Layout.setVerticalGroup(
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         );
 
         jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Students", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
 
         tblCourses.setBackground(new java.awt.Color(242, 242, 242));
         tblCourses.setModel(new javax.swing.table.DefaultTableModel(
             new Object [][] {
 
             },
             new String [] {
                 "Student ID", "Name", "Email", "Year", "Annual Fee"
             }
         ) {
             boolean[] canEdit = new boolean [] {
                 false, false, false, false, false
             };
 
             public boolean isCellEditable(int rowIndex, int columnIndex) {
                 return canEdit [columnIndex];
             }
         });
         tblCourses.getTableHeader().setReorderingAllowed(false);
         jScrollPane1.setViewportView(tblCourses);
 
         javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
         jPanel3.setLayout(jPanel3Layout);
         jPanel3Layout.setHorizontalGroup(
             jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jScrollPane1)
                 .addContainerGap())
         );
         jPanel3Layout.setVerticalGroup(
             jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                 .addContainerGap())
         );
 
         jButton1.setBackground(new java.awt.Color(51, 153, 255));
         jButton1.setText("Add Student");
 
         jButton2.setBackground(new java.awt.Color(51, 153, 255));
         jButton2.setText("Update Student");
         jButton2.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButton2ActionPerformed(evt);
             }
         });
 
         jButton3.setBackground(new java.awt.Color(51, 153, 255));
         jButton3.setText("Delete Student");
         jButton3.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButton3ActionPerformed(evt);
             }
         });
         
         jButton4.setBackground(new java.awt.Color(51, 153, 255));
         jButton4.setText("Assign Module");
         jButton4.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 assignModuleToTeacherActionPerformed(evt);
             }
         });
 
         javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
         jPanel4.setLayout(jPanel4Layout);
         jPanel4Layout.setHorizontalGroup(
             jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(jPanel4Layout.createSequentialGroup()
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addComponent(jButton1)
                 .addGap(18, 18, 18)
                 .addComponent(jButton2)
                 .addGap(18, 18, 18)
                 .addComponent(jButton3)
                 .addGap(18, 18, 18)
                 .addComponent(jButton4)
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         jPanel4Layout.setVerticalGroup(
             jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                 .addContainerGap(17, Short.MAX_VALUE)
                 .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                 .addComponent(jButton1)
                 .addComponent(jButton2)
                 .addComponent(jButton3)
                 .addComponent(jButton4))
             .addContainerGap()
             )
         );
 
         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
             .addGroup(layout.createSequentialGroup()
                 .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addContainerGap())
                     .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
         );
 
         pack();
     }// </editor-fold>//GEN-END:initComponents

     private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
         Login loginForm = new Login();
         loginForm.setVisible(true);
         this.dispose();
     }//GEN-LAST:event_btnLogoutActionPerformed

     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         // TODO add your handling code here:
     }//GEN-LAST:event_jButton2ActionPerformed

     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         // TODO add your handling code here:
     }//GEN-LAST:event_jButton3ActionPerformed

     private void setupSidebarEffects() {

         javax.swing.JLabel[] labels = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9};

         for (javax.swing.JLabel label : labels) {

             label.setOpaque(true);

             label.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15));

             label.addMouseListener(new java.awt.event.MouseAdapter() {
                 public void mouseEntered(java.awt.event.MouseEvent evt) {
                     if (label != selectedLabel) {
                         label.setBackground(HOVER_COLOR);
                         label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                     }
                 }

                 public void mouseExited(java.awt.event.MouseEvent evt) {
                     if (label != selectedLabel) {
                         label.setBackground(DEFAULT_COLOR);
                     }
                 }

                 public void mouseClicked(java.awt.event.MouseEvent evt) {
                     setSelectedSidebarItem(label);
                     updateMainPanel(label.getText());
                 }
             });
         }

         for (javax.swing.JLabel label : labels) {
             label.setBackground(DEFAULT_COLOR);
         }
     }

     private void setSelectedSidebarItem(javax.swing.JLabel label) {
         if (selectedLabel != null) {
             selectedLabel.setBackground(DEFAULT_COLOR);
             selectedLabel.setForeground(java.awt.Color.BLACK);
         }
         selectedLabel = label;
         selectedLabel.setBackground(SELECTED_COLOR);
         selectedLabel.setForeground(new java.awt.Color(0, 51, 153));

         panelBorder.setTitle(label.getText());
         jPanel3.repaint();

         updateButtonLabels(label.getText());

         if (selectedLabel.getText().equals("Teachers")) {
             jButton4.setVisible(true);
         } else {
             jButton4.setVisible(false);
         }

         updateMainPanel(selectedLabel.getText());
     }

     private void updateButtonLabels(String category) {

         switch (category) {
             case "Students":
                 jButton1.setText("Add Student");
                 jButton2.setText("Update Student");
                 jButton3.setText("Delete Student");
                 break;
             case "Teachers":
                 jButton1.setText("Add Teacher");
                 jButton2.setText("Update Teacher");
                 jButton3.setText("Delete Teacher");
                 break;
             case "Modules":
                 jButton1.setText("Add Module");
                 jButton2.setText("Update Module");
                 jButton3.setText("Delete Module");
                 break;
             case "Assessments":
                 jButton1.setText("Add Assessment");
                 jButton2.setText("Update Assessment");
                 jButton3.setText("Delete Assessment");
                 break;
             case "Payments":
                 jButton1.setText("Add Payment");
                 jButton2.setText("Update Payment");
                 jButton3.setText("Delete Payment");
                 break;
             case "Admins":
                 jButton1.setText("Add Admin");
                 jButton2.setText("Update Admin");
                 jButton3.setText("Delete Admin");
                 break;
             case "Sessions":
                 jButton1.setText("Add Session");
                 jButton2.setText("Update Session");
                 jButton3.setText("Delete Session");
                 break;
             case "Classrooms":
                 jButton1.setText("Add Classroom");
                 jButton2.setText("Update Classroom");
                 jButton3.setText("Delete Classroom");
                 break;
             case "Enrollments":
                 jButton1.setText("Add Enrollment");
                 jButton2.setText("Update Enrollment");
                 jButton3.setText("Delete Enrollment");
                 break;
             default:
                 break;
         }
     }

     private void updateMainPanel(String category) {

         javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblCourses.getModel();
         model.setRowCount(0);

         switch (category) {
             case "Students":
                 model.setColumnIdentifiers(new String[]{"Student ID", "Name", "Email", "Year", "Annual Fee"});

                 if (students != null) {
                     for (Student student : students) {
                         model.addRow(new Object[]{
                             student.getStudentID(),
                             student.getName(),
                             student.getEmail(),
                             student.getYear(),
                             student.getAnnualFee()
                         });
                     }
                 }

                 break;
             case "Teachers":
                 model.setColumnIdentifiers(new String[]{"Teacher ID", "Name", "Email", "Status", "Password"});

                 if (teachers != null) {
                     for (Teacher teacher : teachers) {
                         model.addRow(new Object[]{
                             teacher.getStaffId(),
                             teacher.getName(),
                             teacher.getEmail(),
                             teacher.getStatus(),
                             teacher.getPassword()
                         });
                     }
                 }

                 break;
             case "Modules":
                 model.setColumnIdentifiers(new String[]{"Module ID", "Module Name", "Capacity", "Year"});

                 if (modules != null) {
                     for (Module module : modules) {
                         model.addRow(new Object[]{
                             module.getModuleID(),
                             module.getModuleName(),
                             module.getMaxCapacity(),
                             module.getModuleYear()
                         });
                     }
                 }

                 break;
             case "Assessments":
                 model.setColumnIdentifiers(new String[]{"Assessment ID", "Module", "Title", "Duration", "Date"});

                 if (assessments != null) {
                     for (Assessment assessment : assessments) {

                         String formattedDate = "";
                         if (assessment.getDate() != null) {
                             java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
                             formattedDate = sdf.format(assessment.getDate());
                         }

                         model.addRow(new Object[]{
                             assessment.getAssessmentID(),
                             assessment.getModule().getModuleName(),
                             assessment.getTitle(),
                             assessment.getDuration(),
                             formattedDate
                         });
                     }
                 }
                 break;
             case "Payments":
                 model.setColumnIdentifiers(new String[]{"Payment ID", "Student", "Amount", "Description", "Date"});

                 if (payments != null) {
                     for (Payment payment : payments) {
                         model.addRow(new Object[]{
                             payment.getPaymentID(),
                             payment.getPayeeId().getName(),
                             payment.getAmount(),
                             payment.getDescription(),
                             payment.getDate()
                         });
                     }
                 }
                 break;
             case "Admins":
                 model.setColumnIdentifiers(new String[]{"Admin ID", "Name", "Email", "Status", "Password"});

                 if (admins != null) {
                     for (Admin admin : admins) {
                         model.addRow(new Object[]{
                             admin.getStaffId(),
                             admin.getName(),
                             admin.getEmail(),
                             admin.getStatus(),
                             admin.getPassword()
                         });
                     }
                 }
                 break;
             case "Sessions":
                 model.setColumnIdentifiers(new String[]{"Session ID", "Module", "Session Name", "Start Time", "End Time", "Classroom", "Status"});

                 if (sessions != null) {
                     for (Session session : sessions) {
                         model.addRow(new Object[]{
                             session.getSessionID(),
                             session.getModule().getModuleName(),
                             session.getSessionName(),
                             session.getStartTime(),
                             session.getEndTime(),
                             session.getClassroom().getRoomName(),
                             session.getStatus()
                         });
                     }
                 }
                 break;
             case "Classrooms":
                 model.setColumnIdentifiers(new String[]{"Classroom ID", "Room Name", "Capacity", "Resources"});

                 if (classrooms != null) {
                     for (Classroom classroom : classrooms) {
                         model.addRow(new Object[]{
                             classroom.getClassroomID(),
                             classroom.getRoomName(),
                             classroom.getCapacity(),
                             classroom.getResources()
                         });
                     }
                 }
                 break;
             case "Enrollments":
                 model.setColumnIdentifiers(new String[]{"Enrollment ID", "Student", "Module", "Grade", "Status"});

                 if (enrollments != null) {
                     for (Enrollment e : enrollments) {
                         model.addRow(new Object[]{
                             e.getEnrollmentID(),
                             e.getStudent().getName() + " (ID: " + e.getStudent().getStudentID() + ")",
                             e.getModule().getModuleName() + " (Year: " + e.getModule().getModuleYear() + ")",
                             e.getGrade(),
                             e.getEnrollmentStatus()
                         });
                     }
                 }
                 break;
             default:
                 break;
         }

         jPanel3.repaint();
         jPanel4.revalidate();
         jPanel4.repaint();
     }

     @Override
     public void setVisible(boolean b) {
         super.setVisible(b);
         setupSidebarListeners();
     }

     private void setupSidebarListeners() {

         jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel1);
                 updateMainPanel("Students");
             }
         });

         jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel2);
                 updateMainPanel("Teachers");
             }
         });

         jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel3);
                 updateMainPanel("Modules");
             }
         });

         jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel4);
                 updateMainPanel("Assessments");
             }
         });

         jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel5);
                 updateMainPanel("Payments");
             }
         });

         jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel6);
                 updateMainPanel("Admins");
             }
         });

         jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel7);
                 updateMainPanel("Sessions");
             }
         });

         jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel8);
                 updateMainPanel("Classrooms");
             }
         });

         jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 setSelectedSidebarItem(jLabel9);
                 updateMainPanel("Enrollments");
             }
         });
     }

     /**
      * Add a new student
      */
     private void addStudent() {

         String name = JOptionPane.showInputDialog(this, "Enter student name:");
         if (name == null || name.trim().isEmpty()) {
             return;
         }

         String email = JOptionPane.showInputDialog(this, "Enter student email:");
         if (email == null || email.trim().isEmpty()) {
             return;
         }

         String password = JOptionPane.showInputDialog(this, "Enter student password:");
         if (password == null || password.trim().isEmpty()) {
             return;
         }

         String yearStr = JOptionPane.showInputDialog(this, "Enter student year (1-4):");
         if (yearStr == null || yearStr.trim().isEmpty()) {
             return;
         }

         String feeStr = JOptionPane.showInputDialog(this, "Enter annual fee:");
         if (feeStr == null || feeStr.trim().isEmpty()) {
             return;
         }

         try {
             int year = Integer.parseInt(yearStr);
             float fee = Float.parseFloat(feeStr);

             Student student = new Student(name, email, fee, year, password);

             students.add(student);

             FileDataStore.saveStudents(students);

             updateMainPanel("Students");

             JOptionPane.showMessageDialog(this,
                     "Student added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding student: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing student
      *
      * @param selectedRow The selected row in the table
      */
     private void updateStudent(int selectedRow) {
         try {

             int studentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Student studentToUpdate = null;
             for (Student s : students) {
                 if (s.getStudentID() == studentId) {
                     studentToUpdate = s;
                     break;
                 }
             }

             if (studentToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Student not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String name = JOptionPane.showInputDialog(this,
                     "Enter student name:",
                     studentToUpdate.getName());
             if (name == null) {
                 return;
             }

             String email = JOptionPane.showInputDialog(this,
                     "Enter student email:",
                     studentToUpdate.getEmail());
             if (email == null) {
                 return;
             }

             String password = JOptionPane.showInputDialog(this,
                     "Enter student password:",
                     studentToUpdate.getPassword());
             if (password == null) {
                 return;
             }

             String yearStr = JOptionPane.showInputDialog(this,
                     "Enter student year (1-4):",
                     studentToUpdate.getYear());
             if (yearStr == null) {
                 return;
             }

             String feeStr = JOptionPane.showInputDialog(this,
                     "Enter annual fee:",
                     studentToUpdate.getAnnualFee());
             if (feeStr == null) {
                 return;
             }

             int year = Integer.parseInt(yearStr);
             float fee = Float.parseFloat(feeStr);

             studentToUpdate.setName(name);
             studentToUpdate.setEmail(email);
             studentToUpdate.setPassword(password);
             studentToUpdate.setYear(year);
             studentToUpdate.setAnnualFee(fee);

             FileDataStore.saveStudents(students);

             updateMainPanel("Students");

             JOptionPane.showMessageDialog(this,
                     "Student updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating student: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete a student
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteStudent(int selectedRow) {
         try {

             int studentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Student studentToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < students.size(); i++) {
                 if (students.get(i).getStudentID() == studentId) {
                     studentToDelete = students.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (studentToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Student not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             boolean isEnrolled = false;
             for (Enrollment enrollment : enrollments) {
                 if (enrollment.getStudent().getStudentID() == studentId) {
                     isEnrolled = true;
                     break;
                 }
             }

             if (isEnrolled) {
                 int confirm = JOptionPane.showConfirmDialog(this,
                         "This student is enrolled in modules. Delete anyway?",
                         "Warning",
                         JOptionPane.YES_NO_OPTION);

                 if (confirm != JOptionPane.YES_OPTION) {
                     return;
                 }

                 ArrayList<Enrollment> enrollmentsToRemove = new ArrayList<>();
                 for (Enrollment enrollment : enrollments) {
                     if (enrollment.getStudent().getStudentID() == studentId) {
                         enrollmentsToRemove.add(enrollment);
                     }
                 }

                 enrollments.removeAll(enrollmentsToRemove);
                 FileDataStore.saveEnrollments(enrollments);
             }

             boolean hasPayments = false;
             for (Payment payment : payments) {
                 if (payment.getPayeeId().getStudentID() == studentId) {
                     hasPayments = true;
                     break;
                 }
             }

             if (hasPayments) {
                 int confirm = JOptionPane.showConfirmDialog(this,
                         "This student has payment records. Delete anyway?",
                         "Warning",
                         JOptionPane.YES_NO_OPTION);

                 if (confirm != JOptionPane.YES_OPTION) {
                     return;
                 }

                 ArrayList<Payment> paymentsToRemove = new ArrayList<>();
                 for (Payment payment : payments) {
                     if (payment.getPayeeId().getStudentID() == studentId) {
                         paymentsToRemove.add(payment);
                     }
                 }

                 payments.removeAll(paymentsToRemove);
                 FileDataStore.savePayments(payments);
             }

             students.remove(indexToRemove);

             FileDataStore.saveStudents(students);

             updateMainPanel("Students");

             JOptionPane.showMessageDialog(this,
                     "Student deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting student: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new teacher
      */
     private void addTeacher() {

         String name = JOptionPane.showInputDialog(this, "Enter teacher name:");
         if (name == null || name.trim().isEmpty()) {
             return;
         }

         String email = JOptionPane.showInputDialog(this, "Enter teacher email:");
         if (email == null || email.trim().isEmpty()) {
             return;
         }

         String password = JOptionPane.showInputDialog(this, "Enter teacher password:");
         if (password == null || password.trim().isEmpty()) {
             return;
         }

         String[] statusOptions = {"ACTIVE", "INACTIVE", "ON_LEAVE"};
         String statusStr = (String) JOptionPane.showInputDialog(this,
                 "Select status:",
                 "Teacher Status",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 statusOptions,
                 statusOptions[0]);
         if (statusStr == null) {
             return;
         }

         try {
             StaffStatus status = StaffStatus.valueOf(statusStr);

             Teacher teacher = new Teacher(name, email, "teacher", status, password);

             teachers.add(teacher);

             FileDataStore.saveTeachers(teachers);

             updateMainPanel("Teachers");

             JOptionPane.showMessageDialog(this,
                     "Teacher added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding teacher: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing teacher
      *
      * @param selectedRow The selected row in the table
      */
     private void updateTeacher(int selectedRow) {
         try {

             int teacherId = (int) tblCourses.getValueAt(selectedRow, 0);
             Teacher teacherToUpdate = null;
             for (Teacher t : teachers) {
                 if (t.getStaffId() == teacherId) {
                     teacherToUpdate = t;
                     break;
                 }
             }

             if (teacherToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Teacher not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String name = JOptionPane.showInputDialog(this,
                     "Enter teacher name:",
                     teacherToUpdate.getName());
             if (name == null) {
                 return;
             }

             String email = JOptionPane.showInputDialog(this,
                     "Enter teacher email:",
                     teacherToUpdate.getEmail());
             if (email == null) {
                 return;
             }

             String password = JOptionPane.showInputDialog(this,
                     "Enter teacher password:",
                     teacherToUpdate.getPassword());
             if (password == null) {
                 return;
             }

             String[] statusOptions = {"ACTIVE", "INACTIVE", "ON_LEAVE"};
             String statusStr = (String) JOptionPane.showInputDialog(this,
                     "Select status:",
                     "Teacher Status",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     statusOptions,
                     teacherToUpdate.getStatus().toString());
             if (statusStr == null) {
                 return;
             }

             StaffStatus status = StaffStatus.valueOf(statusStr);

             teacherToUpdate.setName(name);
             teacherToUpdate.setEmail(email);
             teacherToUpdate.setPassword(password);
             teacherToUpdate.setStatus(status);

             FileDataStore.saveTeachers(teachers);

             updateMainPanel("Teachers");

             JOptionPane.showMessageDialog(this,
                     "Teacher updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating teacher: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete a teacher
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteTeacher(int selectedRow) {
         try {

             int teacherId = (int) tblCourses.getValueAt(selectedRow, 0);
             Teacher teacherToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < teachers.size(); i++) {
                 if (teachers.get(i).getStaffId() == teacherId) {
                     teacherToDelete = teachers.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (teacherToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Teacher not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             boolean hasSessions = false;
             for (Session session : sessions) {

             }

             if (hasSessions) {
                 int confirm = JOptionPane.showConfirmDialog(this,
                         "This teacher has associated sessions. Delete anyway?",
                         "Warning",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.WARNING_MESSAGE);

                 if (confirm != JOptionPane.YES_OPTION) {
                     return;
                 }

             }

             teachers.remove(indexToRemove);

             FileDataStore.saveTeachers(teachers);

             updateMainPanel("Teachers");

             JOptionPane.showMessageDialog(this,
                     "Teacher deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting teacher: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new module
      */
     private void addModule() {

         String moduleName = JOptionPane.showInputDialog(this, "Enter module name:");
         if (moduleName == null || moduleName.trim().isEmpty()) {
             return;
         }

         String capacityStr = JOptionPane.showInputDialog(this, "Enter maximum capacity:");
         if (capacityStr == null || capacityStr.trim().isEmpty()) {
             return;
         }

         String yearStr = JOptionPane.showInputDialog(this, "Enter module year (1-4):");
         if (yearStr == null || yearStr.trim().isEmpty()) {
             return;
         }

         try {
             int capacity = Integer.parseInt(capacityStr);
             int year = Integer.parseInt(yearStr);

             Module module = new Module(moduleName, capacity, new ArrayList<>(), year);

             modules.add(module);

             FileDataStore.saveModules(modules);

             updateMainPanel("Modules");

             JOptionPane.showMessageDialog(this,
                     "Module added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding module: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing module
      *
      * @param selectedRow The selected row in the table
      */
     private void updateModule(int selectedRow) {
         try {

             int moduleId = (int) tblCourses.getValueAt(selectedRow, 0);
             Module moduleToUpdate = null;
             for (Module m : modules) {
                 if (m.getModuleID() == moduleId) {
                     moduleToUpdate = m;
                     break;
                 }
             }

             if (moduleToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Module not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String moduleName = JOptionPane.showInputDialog(this,
                     "Enter module name:",
                     moduleToUpdate.getModuleName());
             if (moduleName == null) {
                 return;
             }

             String capacityStr = JOptionPane.showInputDialog(this,
                     "Enter maximum capacity:",
                     moduleToUpdate.getMaxCapacity());
             if (capacityStr == null) {
                 return;
             }

             String yearStr = JOptionPane.showInputDialog(this,
                     "Enter module year (1-4):",
                     moduleToUpdate.getModuleYear());
             if (yearStr == null) {
                 return;
             }

             int capacity = Integer.parseInt(capacityStr);
             int year = Integer.parseInt(yearStr);

             moduleToUpdate.setModuleName(moduleName);
             moduleToUpdate.setMaxCapacity(capacity);
             moduleToUpdate.setModuleYear(year);

             FileDataStore.saveModules(modules);

             updateMainPanel("Modules");

             JOptionPane.showMessageDialog(this,
                     "Module updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating module: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete a module
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteModule(int selectedRow) {
         try {

             int moduleId = (int) tblCourses.getValueAt(selectedRow, 0);
             Module moduleToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < modules.size(); i++) {
                 if (modules.get(i).getModuleID() == moduleId) {
                     moduleToDelete = modules.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (moduleToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Module not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             boolean hasEnrollments = false;
             for (Enrollment enrollment : enrollments) {
                 if (enrollment.getModule().getModuleID() == moduleId) {
                     hasEnrollments = true;
                     break;
                 }
             }

             if (hasEnrollments) {
                 int confirm = JOptionPane.showConfirmDialog(this,
                         "Students are enrolled in this module. Delete anyway?",
                         "Warning",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.WARNING_MESSAGE);

                 if (confirm != JOptionPane.YES_OPTION) {
                     return;
                 }

                 ArrayList<Enrollment> enrollmentsToRemove = new ArrayList<>();
                 for (Enrollment enrollment : enrollments) {
                     if (enrollment.getModule().getModuleID() == moduleId) {
                         enrollmentsToRemove.add(enrollment);
                     }
                 }

                 enrollments.removeAll(enrollmentsToRemove);
                 FileDataStore.saveEnrollments(enrollments);
             }

             boolean hasSessions = false;
             for (Session session : sessions) {
                 if (session.getModule().getModuleID() == moduleId) {
                     hasSessions = true;
                     break;
                 }
             }

             if (hasSessions) {
                 int confirm = JOptionPane.showConfirmDialog(this,
                         "This module has associated sessions. Delete anyway?",
                         "Warning",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.WARNING_MESSAGE);

                 if (confirm != JOptionPane.YES_OPTION) {
                     return;
                 }

                 ArrayList<Session> sessionsToRemove = new ArrayList<>();
                 for (Session session : sessions) {
                     if (session.getModule().getModuleID() == moduleId) {
                         sessionsToRemove.add(session);
                     }
                 }

                 sessions.removeAll(sessionsToRemove);
                 FileDataStore.saveSessions(sessions);
             }

             modules.remove(indexToRemove);

             FileDataStore.saveModules(modules);

             updateMainPanel("Modules");

             JOptionPane.showMessageDialog(this,
                     "Module deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting module: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new admin
      */
     private void addAdmin() {

         String name = JOptionPane.showInputDialog(this, "Enter admin name:");
         if (name == null || name.trim().isEmpty()) {
             return;
         }

         String email = JOptionPane.showInputDialog(this, "Enter admin email:");
         if (email == null || email.trim().isEmpty()) {
             return;
         }

         String password = JOptionPane.showInputDialog(this, "Enter admin password:");
         if (password == null || password.trim().isEmpty()) {
             return;
         }

         String[] statusOptions = {"ACTIVE", "INACTIVE", "ON_LEAVE"};
         String statusStr = (String) JOptionPane.showInputDialog(this,
                 "Select status:",
                 "Admin Status",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 statusOptions,
                 statusOptions[0]);
         if (statusStr == null) {
             return;
         }

         try {
             StaffStatus status = StaffStatus.valueOf(statusStr);

             Admin admin = new Admin(name, email, "admin", status, password);

             admins.add(admin);

             FileDataStore.saveAdmins(admins);

             updateMainPanel("Admins");

             JOptionPane.showMessageDialog(this,
                     "Admin added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding admin: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing admin
      *
      * @param selectedRow The selected row in the table
      */
     private void updateAdmin(int selectedRow) {
         try {

             int adminId = (int) tblCourses.getValueAt(selectedRow, 0);
             Admin adminToUpdate = null;
             for (Admin a : admins) {
                 if (a.getStaffId() == adminId) {
                     adminToUpdate = a;
                     break;
                 }
             }

             if (adminToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Admin not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String name = JOptionPane.showInputDialog(this,
                     "Enter admin name:",
                     adminToUpdate.getName());
             if (name == null) {
                 return;
             }

             String email = JOptionPane.showInputDialog(this,
                     "Enter admin email:",
                     adminToUpdate.getEmail());
             if (email == null) {
                 return;
             }

             String password = JOptionPane.showInputDialog(this,
                     "Enter admin password:",
                     adminToUpdate.getPassword());
             if (password == null) {
                 return;
             }

             String[] statusOptions = {"ACTIVE", "INACTIVE", "ON_LEAVE"};
             String statusStr = (String) JOptionPane.showInputDialog(this,
                     "Select status:",
                     "Admin Status",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     statusOptions,
                     adminToUpdate.getStatus().toString());
             if (statusStr == null) {
                 return;
             }

             StaffStatus status = StaffStatus.valueOf(statusStr);

             adminToUpdate.setName(name);
             adminToUpdate.setEmail(email);
             adminToUpdate.setPassword(password);
             adminToUpdate.setStatus(status);

             FileDataStore.saveAdmins(admins);

             updateMainPanel("Admins");

             JOptionPane.showMessageDialog(this,
                     "Admin updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating admin: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete an admin
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteAdmin(int selectedRow) {
         try {

             int adminId = (int) tblCourses.getValueAt(selectedRow, 0);
             Admin adminToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < admins.size(); i++) {
                 if (admins.get(i).getStaffId() == adminId) {
                     adminToDelete = admins.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (adminToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Admin not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             if (admins.size() <= 1) {
                 JOptionPane.showMessageDialog(this,
                         "Cannot delete the only admin in the system.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             admins.remove(indexToRemove);

             FileDataStore.saveAdmins(admins);

             updateMainPanel("Admins");

             JOptionPane.showMessageDialog(this,
                     "Admin deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting admin: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new session
      */
     private void addSession() {
         if (modules.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "No modules available. Please create a module first.",
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             return;
         }

         if (classrooms.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "No classrooms available. Please add a classroom first.",
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             return;
         }

         String[] moduleNames = new String[modules.size()];
         for (int i = 0; i < modules.size(); i++) {
             moduleNames[i] = modules.get(i).getModuleName();
         }

         String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                 "Select module:",
                 "Module Selection",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 moduleNames,
                 moduleNames[0]);
         if (selectedModuleName == null) {
             return;
         }

         Module selectedModule = null;
         for (Module module : modules) {
             if (module.getModuleName().equals(selectedModuleName)) {
                 selectedModule = module;
                 break;
             }
         }

         if (selectedModule == null) {
             return;
         }

         String[] classroomNames = new String[classrooms.size()];
         for (int i = 0; i < classrooms.size(); i++) {
             classroomNames[i] = classrooms.get(i).getRoomName() + " (Capacity: " + classrooms.get(i).getCapacity() + ")";
         }

         String selectedClassroomName = (String) JOptionPane.showInputDialog(this,
                 "Select classroom:",
                 "Classroom Selection",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 classroomNames,
                 classroomNames[0]);
         if (selectedClassroomName == null) {
             return;
         }

         String roomName = selectedClassroomName.substring(0, selectedClassroomName.indexOf(" ("));

         Classroom selectedClassroom = null;
         for (Classroom classroom : classrooms) {
             if (classroom.getRoomName().equals(roomName)) {
                 selectedClassroom = classroom;
                 break;
             }
         }

         if (selectedClassroom == null) {
             return;
         }

         String sessionName = JOptionPane.showInputDialog(this, "Enter session name:");
         if (sessionName == null || sessionName.trim().isEmpty()) {
             return;
         }

         String startTime = JOptionPane.showInputDialog(this, "Enter start time (e.g., 09:00 AM):");
         if (startTime == null || startTime.trim().isEmpty()) {
             return;
         }

         String endTime = JOptionPane.showInputDialog(this, "Enter end time (e.g., 11:00 AM):");
         if (endTime == null || endTime.trim().isEmpty()) {
             return;
         }

         String[] statusOptions = {"Scheduled", "In Progress", "Completed", "Cancelled"};
         String status = (String) JOptionPane.showInputDialog(this,
                 "Select session status:",
                 "Session Status",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 statusOptions,
                 statusOptions[0]);
         if (status == null) {
             return;
         }

         try {

             ArrayList<Student> attendees = new ArrayList<>();

             Session session = new Session(selectedModule, sessionName, startTime, endTime, selectedClassroom, attendees, status);

             sessions.add(session);

             FileDataStore.saveSessions(sessions);

             updateMainPanel("Sessions");

             JOptionPane.showMessageDialog(this,
                     "Session added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding session: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing session
      *
      * @param selectedRow The selected row in the table
      */
     private void updateSession(int selectedRow) {
         try {

             int sessionId = (int) tblCourses.getValueAt(selectedRow, 0);
             Session sessionToUpdate = null;
             for (Session s : sessions) {
                 if (s.getSessionID() == sessionId) {
                     sessionToUpdate = s;
                     break;
                 }
             }

             if (sessionToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Session not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String[] moduleNames = new String[modules.size()];
             for (int i = 0; i < modules.size(); i++) {
                 moduleNames[i] = modules.get(i).getModuleName();
             }

             String currentModuleName = sessionToUpdate.getModule().getModuleName();

             String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                     "Select module:",
                     "Module Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     moduleNames,
                     currentModuleName);
             if (selectedModuleName == null) {
                 return;
             }

             Module selectedModule = null;
             for (Module module : modules) {
                 if (module.getModuleName().equals(selectedModuleName)) {
                     selectedModule = module;
                     break;
                 }
             }

             if (selectedModule == null) {
                 return;
             }

             String[] classroomNames = new String[classrooms.size()];
             for (int i = 0; i < classrooms.size(); i++) {
                 classroomNames[i] = classrooms.get(i).getRoomName() + " (Capacity: " + classrooms.get(i).getCapacity() + ")";
             }

             String currentClassroomName = sessionToUpdate.getClassroom().getRoomName()
                     + " (Capacity: " + sessionToUpdate.getClassroom().getCapacity() + ")";

             String selectedClassroomName = (String) JOptionPane.showInputDialog(this,
                     "Select classroom:",
                     "Classroom Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     classroomNames,
                     currentClassroomName);
             if (selectedClassroomName == null) {
                 return;
             }

             String roomName = selectedClassroomName.substring(0, selectedClassroomName.indexOf(" ("));

             Classroom selectedClassroom = null;
             for (Classroom classroom : classrooms) {
                 if (classroom.getRoomName().equals(roomName)) {
                     selectedClassroom = classroom;
                     break;
                 }
             }

             if (selectedClassroom == null) {
                 return;
             }

             String sessionName = JOptionPane.showInputDialog(this,
                     "Enter session name:",
                     sessionToUpdate.getSessionName());
             if (sessionName == null) {
                 return;
             }

             String startTime = JOptionPane.showInputDialog(this,
                     "Enter start time (e.g., 09:00 AM):",
                     sessionToUpdate.getStartTime());
             if (startTime == null) {
                 return;
             }

             String endTime = JOptionPane.showInputDialog(this,
                     "Enter end time (e.g., 11:00 AM):",
                     sessionToUpdate.getEndTime());
             if (endTime == null) {
                 return;
             }

             String[] statusOptions = {"Scheduled", "In Progress", "Completed", "Cancelled"};
             String status = (String) JOptionPane.showInputDialog(this,
                     "Select session status:",
                     "Session Status",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     statusOptions,
                     sessionToUpdate.getStatus());
             if (status == null) {
                 return;
             }

             sessionToUpdate.setModule(selectedModule);
             sessionToUpdate.setSessionName(sessionName);
             sessionToUpdate.setStartTime(startTime);
             sessionToUpdate.setEndTime(endTime);
             sessionToUpdate.setClassroom(selectedClassroom);
             sessionToUpdate.setStatus(status);

             FileDataStore.saveSessions(sessions);

             updateMainPanel("Sessions");

             JOptionPane.showMessageDialog(this,
                     "Session updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating session: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete a session
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteSession(int selectedRow) {
         try {

             int sessionId = (int) tblCourses.getValueAt(selectedRow, 0);
             Session sessionToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < sessions.size(); i++) {
                 if (sessions.get(i).getSessionID() == sessionId) {
                     sessionToDelete = sessions.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (sessionToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Session not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             sessions.remove(indexToRemove);

             FileDataStore.saveSessions(sessions);

             updateMainPanel("Sessions");

             JOptionPane.showMessageDialog(this,
                     "Session deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting session: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new assessment
      */
     private void addAssessment() {
         if (modules.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "No modules available. Please create a module first.",
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             return;
         }

         String[] moduleNames = new String[modules.size()];
         for (int i = 0; i < modules.size(); i++) {
             moduleNames[i] = modules.get(i).getModuleName();
         }

         String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                 "Select module:",
                 "Module Selection",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 moduleNames,
                 moduleNames[0]);
         if (selectedModuleName == null) {
             return;
         }

         Module selectedModule = null;
         for (Module module : modules) {
             if (module.getModuleName().equals(selectedModuleName)) {
                 selectedModule = module;
                 break;
             }
         }

         if (selectedModule == null) {
             return;
         }

         String title = JOptionPane.showInputDialog(this, "Enter assessment title:");
         if (title == null || title.trim().isEmpty()) {
             return;
         }

         String duration = JOptionPane.showInputDialog(this, "Enter duration (e.g., 2 hours):");
         if (duration == null || duration.trim().isEmpty()) {
             return;
         }

         String dateStr = JOptionPane.showInputDialog(this, "Enter date (MM/DD/YYYY):");
         if (dateStr == null || dateStr.trim().isEmpty()) {
             return;
         }

         try {

             java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
             java.util.Date date = sdf.parse(dateStr);

             Assessment assessment = new Assessment(0, date, duration, selectedModule, title);

             selectedModule.getAssessments().add(assessment);

             assessments.add(assessment);

             FileDataStore.saveModules(modules);

             updateMainPanel("Assessments");

             JOptionPane.showMessageDialog(this,
                     "Assessment added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (java.text.ParseException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid date format. Please use MM/DD/YYYY format.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding assessment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing assessment
      *
      * @param selectedRow The selected row in the table
      */
     private void updateAssessment(int selectedRow) {
         try {

             int assessmentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Assessment assessmentToUpdate = null;
             for (Assessment a : assessments) {
                 if (a.getAssessmentID() == assessmentId) {
                     assessmentToUpdate = a;
                     break;
                 }
             }

             if (assessmentToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Assessment not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String[] moduleNames = new String[modules.size()];
             for (int i = 0; i < modules.size(); i++) {
                 moduleNames[i] = modules.get(i).getModuleName();
             }

             String currentModuleName = assessmentToUpdate.getModule().getModuleName();

             String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                     "Select module:",
                     "Module Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     moduleNames,
                     currentModuleName);
             if (selectedModuleName == null) {
                 return;
             }

             Module selectedModule = null;
             for (Module module : modules) {
                 if (module.getModuleName().equals(selectedModuleName)) {
                     selectedModule = module;
                     break;
                 }
             }

             if (selectedModule == null) {
                 return;
             }

             String title = JOptionPane.showInputDialog(this,
                     "Enter assessment title:",
                     assessmentToUpdate.getTitle());
             if (title == null) {
                 return;
             }

             String duration = JOptionPane.showInputDialog(this,
                     "Enter duration (e.g., 2 hours):",
                     assessmentToUpdate.getDuration());
             if (duration == null) {
                 return;
             }

             java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
             String dateStr = JOptionPane.showInputDialog(this,
                     "Enter date (MM/DD/YYYY):",
                     sdf.format(assessmentToUpdate.getDate()));
             if (dateStr == null) {
                 return;
             }

             try {

                 java.util.Date date = sdf.parse(dateStr);

                 if (assessmentToUpdate.getModule() != selectedModule) {
                     assessmentToUpdate.getModule().getAssessments().remove(assessmentToUpdate);
                     selectedModule.getAssessments().add(assessmentToUpdate);
                     assessmentToUpdate.setModule(selectedModule);
                 }

                 assessmentToUpdate.setTitle(title);
                 assessmentToUpdate.setDuration(duration);
                 assessmentToUpdate.setDate(date);

                 FileDataStore.saveModules(modules);

                 updateMainPanel("Assessments");

                 JOptionPane.showMessageDialog(this,
                         "Assessment updated successfully!",
                         "Success",
                         JOptionPane.INFORMATION_MESSAGE);

             } catch (java.text.ParseException e) {
                 JOptionPane.showMessageDialog(this,
                         "Invalid date format. Please use MM/DD/YYYY format.",
                         "Input Error",
                         JOptionPane.ERROR_MESSAGE);
             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating assessment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete an assessment
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteAssessment(int selectedRow) {
         try {

             int assessmentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Assessment assessmentToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < assessments.size(); i++) {
                 if (assessments.get(i).getAssessmentID() == assessmentId) {
                     assessmentToDelete = assessments.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (assessmentToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Assessment not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             Module module = assessmentToDelete.getModule();
             module.getAssessments().remove(assessmentToDelete);

             assessments.remove(indexToRemove);

             FileDataStore.saveModules(modules);

             updateMainPanel("Assessments");

             JOptionPane.showMessageDialog(this,
                     "Assessment deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting assessment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new payment
      */
     private void addPayment() {
         if (students.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "No students available. Please create a student first.",
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             return;
         }

         String[] studentNames = new String[students.size()];
         for (int i = 0; i < students.size(); i++) {
             studentNames[i] = students.get(i).getName() + " (ID: " + students.get(i).getStudentID() + ")";
         }

         String selectedStudentName = (String) JOptionPane.showInputDialog(this,
                 "Select student:",
                 "Student Selection",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 studentNames,
                 studentNames[0]);
         if (selectedStudentName == null) {
             return;
         }

         int selectedStudentId = Integer.parseInt(selectedStudentName.substring(
                 selectedStudentName.lastIndexOf("ID: ") + 4,
                 selectedStudentName.lastIndexOf(")")
         ));

         Student selectedStudent = null;
         for (Student student : students) {
             if (student.getStudentID() == selectedStudentId) {
                 selectedStudent = student;
                 break;
             }
         }

         if (selectedStudent == null) {
             return;
         }

         String amountStr = JOptionPane.showInputDialog(this, "Enter payment amount:");
         if (amountStr == null || amountStr.trim().isEmpty()) {
             return;
         }

         String description = JOptionPane.showInputDialog(this, "Enter payment description:");
         if (description == null || description.trim().isEmpty()) {
             return;
         }

         String date = JOptionPane.showInputDialog(this, "Enter payment date (YYYY-MM-DD):");
         if (date == null || date.trim().isEmpty()) {
             return;
         }

         try {
             float amount = Float.parseFloat(amountStr);

             Payment payment = new Payment(amount, selectedStudent, description, date);

             payments.add(payment);

             FileDataStore.savePayments(payments);

             updateMainPanel("Payments");

             JOptionPane.showMessageDialog(this,
                     "Payment added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding payment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing payment
      *
      * @param selectedRow The selected row in the table
      */
     private void updatePayment(int selectedRow) {
         try {

             int paymentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Payment paymentToUpdate = null;
             for (Payment p : payments) {
                 if (p.getPaymentID() == paymentId) {
                     paymentToUpdate = p;
                     break;
                 }
             }

             if (paymentToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Payment not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String[] studentNames = new String[students.size()];
             for (int i = 0; i < students.size(); i++) {
                 studentNames[i] = students.get(i).getName() + " (ID: " + students.get(i).getStudentID() + ")";
             }

             String currentStudentName = paymentToUpdate.getPayeeId().getName()
                     + " (ID: " + paymentToUpdate.getPayeeId().getStudentID() + ")";

             String selectedStudentName = (String) JOptionPane.showInputDialog(this,
                     "Select student:",
                     "Student Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     studentNames,
                     currentStudentName);
             if (selectedStudentName == null) {
                 return;
             }

             int selectedStudentId = Integer.parseInt(selectedStudentName.substring(
                     selectedStudentName.lastIndexOf("ID: ") + 4,
                     selectedStudentName.lastIndexOf(")")
             ));

             Student selectedStudent = null;
             for (Student student : students) {
                 if (student.getStudentID() == selectedStudentId) {
                     selectedStudent = student;
                     break;
                 }
             }

             if (selectedStudent == null) {
                 return;
             }

             String amountStr = JOptionPane.showInputDialog(this,
                     "Enter payment amount:",
                     paymentToUpdate.getAmount());
             if (amountStr == null) {
                 return;
             }

             String description = JOptionPane.showInputDialog(this,
                     "Enter payment description:",
                     paymentToUpdate.getDescription());
             if (description == null) {
                 return;
             }

             String date = JOptionPane.showInputDialog(this,
                     "Enter payment date (YYYY-MM-DD):",
                     paymentToUpdate.getDate());
             if (date == null) {
                 return;
             }

             float amount = Float.parseFloat(amountStr);

             paymentToUpdate.setAmount(amount);
             paymentToUpdate.setPayeeId(selectedStudent);
             paymentToUpdate.setDescription(description);
             paymentToUpdate.setDate(date);

             FileDataStore.savePayments(payments);

             updateMainPanel("Payments");

             JOptionPane.showMessageDialog(this,
                     "Payment updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating payment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete a payment
      *
      * @param selectedRow The selected row in the table
      */
     private void deletePayment(int selectedRow) {
         try {

             int paymentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Payment paymentToDelete = null;
             int indexToRemove = -1;

             for (int i = 0; i < payments.size(); i++) {
                 if (payments.get(i).getPaymentID() == paymentId) {
                     paymentToDelete = payments.get(i);
                     indexToRemove = i;
                     break;
                 }
             }

             if (paymentToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Payment not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             payments.remove(indexToRemove);

             FileDataStore.savePayments(payments);

             updateMainPanel("Payments");

             JOptionPane.showMessageDialog(this,
                     "Payment deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting payment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     private void addClassroom() {

         String roomName = JOptionPane.showInputDialog(this, "Enter classroom name:");
         if (roomName == null || roomName.trim().isEmpty()) {
             return;
         }

         String capacityStr = JOptionPane.showInputDialog(this, "Enter capacity:");
         if (capacityStr == null || capacityStr.trim().isEmpty()) {
             return;
         }

         String resources = JOptionPane.showInputDialog(this, "Enter available resources (optional):");

         try {
             int capacity = Integer.parseInt(capacityStr);

             Classroom classroom = new Classroom(roomName, capacity);
             if (resources != null && !resources.trim().isEmpty()) {
                 classroom.setResources(resources);
             }

             classroom.addClassroom(classrooms);

             updateMainPanel("Classrooms");

             JOptionPane.showMessageDialog(this,
                     "Classroom added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format for capacity. Please enter a valid number.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding classroom: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing classroom
      *
      * @param selectedRow The selected row in the table
      */
     private void updateClassroom(int selectedRow) {
         try {

             int classroomId = (int) tblCourses.getValueAt(selectedRow, 0);
             Classroom classroomToUpdate = null;
             for (Classroom c : classrooms) {
                 if (c.getClassroomId() == classroomId) {
                     classroomToUpdate = c;
                     break;
                 }
             }

             if (classroomToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Classroom not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String roomName = JOptionPane.showInputDialog(this,
                     "Enter classroom name:",
                     classroomToUpdate.getRoomName());
             if (roomName == null) {
                 return;
             }

             String capacityStr = JOptionPane.showInputDialog(this,
                     "Enter capacity:",
                     classroomToUpdate.getCapacity());
             if (capacityStr == null) {
                 return;
             }

             String resources = JOptionPane.showInputDialog(this,
                     "Enter available resources (optional):",
                     classroomToUpdate.getResources());

             int capacity = Integer.parseInt(capacityStr);

             classroomToUpdate.setRoomName(roomName);
             classroomToUpdate.setCapacity(capacity);
             if (resources != null) {
                 classroomToUpdate.setResources(resources);
             }

             classroomToUpdate.updateClassroom(classrooms);

             updateMainPanel("Classrooms");

             JOptionPane.showMessageDialog(this,
                     "Classroom updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format for capacity. Please enter a valid number.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating classroom: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete a classroom
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteClassroom(int selectedRow) {
         try {

             int classroomId = (int) tblCourses.getValueAt(selectedRow, 0);
             Classroom classroomToDelete = null;

             for (Classroom c : classrooms) {
                 if (c.getClassroomId() == classroomId) {
                     classroomToDelete = c;
                     break;
                 }
             }

             if (classroomToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Classroom not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             boolean hasScheduledSessions = false;
             for (Session session : sessions) {
                 if (session.getClassroom().getClassroomId() == classroomId) {
                     hasScheduledSessions = true;
                     break;
                 }
             }

             if (hasScheduledSessions) {
                 int confirm = JOptionPane.showConfirmDialog(this,
                         "This classroom has scheduled sessions. Delete anyway?",
                         "Warning",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.WARNING_MESSAGE);

                 if (confirm != JOptionPane.YES_OPTION) {
                     return;
                 }

                 ArrayList<Session> sessionsToRemove = new ArrayList<>();
                 for (Session session : sessions) {
                     if (session.getClassroom().getClassroomId() == classroomId) {
                         sessionsToRemove.add(session);
                     }
                 }

                 sessions.removeAll(sessionsToRemove);
                 FileDataStore.saveSessions(sessions);
             }

             classroomToDelete.deleteClassroom(classrooms);

             updateMainPanel("Classrooms");

             JOptionPane.showMessageDialog(this,
                     "Classroom deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting classroom: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Add a new enrollment
      */
     private void addEnrollment() {
         if (students.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "No students available. Please create a student first.",
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             return;
         }

         if (modules.isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "No modules available. Please create a module first.",
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             return;
         }

         String[] studentNames = new String[students.size()];
         for (int i = 0; i < students.size(); i++) {
             studentNames[i] = students.get(i).getName() + " (ID: " + students.get(i).getStudentID() + ")";
         }

         String selectedStudentName = (String) JOptionPane.showInputDialog(this,
                 "Select student:",
                 "Student Selection",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 studentNames,
                 studentNames[0]);
         if (selectedStudentName == null) {
             return;
         }

         int selectedStudentId = Integer.parseInt(selectedStudentName.substring(
                 selectedStudentName.lastIndexOf("ID: ") + 4,
                 selectedStudentName.lastIndexOf(")")
         ));

         Student selectedStudent = null;
         for (Student student : students) {
             if (student.getStudentID() == selectedStudentId) {
                 selectedStudent = student;
                 break;
             }
         }

         if (selectedStudent == null) {
             return;
         }

         String[] moduleNames = new String[modules.size()];
         for (int i = 0; i < modules.size(); i++) {
             moduleNames[i] = modules.get(i).getModuleName() + " (Year: " + modules.get(i).getModuleYear() + ")";
         }

         String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                 "Select module:",
                 "Module Selection",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 moduleNames,
                 moduleNames[0]);
         if (selectedModuleName == null) {
             return;
         }

         String moduleName = selectedModuleName.substring(0, selectedModuleName.indexOf(" (Year:"));

         Module selectedModule = null;
         for (Module module : modules) {
             if (module.getModuleName().equals(moduleName)) {
                 selectedModule = module;
                 break;
             }
         }

         if (selectedModule == null) {
             return;
         }

         if (selectedStudent.getYear() != selectedModule.getModuleYear()) {
             int confirm = JOptionPane.showConfirmDialog(this,
                     "Student year (" + selectedStudent.getYear() + ") does not match module year ("
                     + selectedModule.getModuleYear() + "). Enroll anyway?",
                     "Year Mismatch",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.WARNING_MESSAGE);

             if (confirm != JOptionPane.YES_OPTION) {
                 return;
             }
         }

         int enrolledCount = 0;
         for (Enrollment e : enrollments) {
             if (e.getModule().getModuleID() == selectedModule.getModuleID()
                     && e.getEnrollmentStatus() == Status.ACTIVE) {
                 enrolledCount++;
             }
         }

         if (enrolledCount >= selectedModule.getMaxCapacity()) {
             int confirm = JOptionPane.showConfirmDialog(this,
                     "This module is at maximum capacity. Enroll anyway?",
                     "Module Full",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.WARNING_MESSAGE);

             if (confirm != JOptionPane.YES_OPTION) {
                 return;
             }
         }

         for (Enrollment e : enrollments) {
             if (e.getStudent().getStudentID() == selectedStudent.getStudentID()
                     && e.getModule().getModuleID() == selectedModule.getModuleID()
                     && e.getEnrollmentStatus() == Status.ACTIVE) {

                 JOptionPane.showMessageDialog(this,
                         "This student is already enrolled in this module.",
                         "Duplicate Enrollment",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }
         }

         try {

             Enrollment newEnrollment = new Enrollment(selectedStudent, selectedModule, Status.ACTIVE);

             newEnrollment.addEnrollment(enrollments);

             updateMainPanel("Enrollments");

             JOptionPane.showMessageDialog(this,
                     "Enrollment added successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error adding enrollment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Update an existing enrollment
      *
      * @param selectedRow The selected row in the table
      */
     private void updateEnrollment(int selectedRow) {
         try {

             int enrollmentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Enrollment enrollmentToUpdate = null;

             for (Enrollment e : enrollments) {
                 if (e.getEnrollmentID() == enrollmentId) {
                     enrollmentToUpdate = e;
                     break;
                 }
             }

             if (enrollmentToUpdate == null) {
                 JOptionPane.showMessageDialog(this,
                         "Enrollment not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             String[] studentNames = new String[students.size()];
             for (int i = 0; i < students.size(); i++) {
                 studentNames[i] = students.get(i).getName() + " (ID: " + students.get(i).getStudentID() + ")";
             }

             String currentStudentName = enrollmentToUpdate.getStudent().getName()
                     + " (ID: " + enrollmentToUpdate.getStudent().getStudentID() + ")";

             String selectedStudentName = (String) JOptionPane.showInputDialog(this,
                     "Select student:",
                     "Student Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     studentNames,
                     currentStudentName);
             if (selectedStudentName == null) {
                 return;
             }

             int selectedStudentId = Integer.parseInt(selectedStudentName.substring(
                     selectedStudentName.lastIndexOf("ID: ") + 4,
                     selectedStudentName.lastIndexOf(")")
             ));

             Student selectedStudent = null;
             for (Student student : students) {
                 if (student.getStudentID() == selectedStudentId) {
                     selectedStudent = student;
                     break;
                 }
             }

             if (selectedStudent == null) {
                 return;
             }

             String[] moduleNames = new String[modules.size()];
             for (int i = 0; i < modules.size(); i++) {
                 moduleNames[i] = modules.get(i).getModuleName() + " (Year: " + modules.get(i).getModuleYear() + ")";
             }

             String currentModuleName = enrollmentToUpdate.getModule().getModuleName()
                     + " (Year: " + enrollmentToUpdate.getModule().getModuleYear() + ")";

             String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                     "Select module:",
                     "Module Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     moduleNames,
                     currentModuleName);
             if (selectedModuleName == null) {
                 return;
             }

             String moduleName = selectedModuleName.substring(0, selectedModuleName.indexOf(" (Year:"));

             Module selectedModule = null;
             for (Module module : modules) {
                 if (module.getModuleName().equals(moduleName)) {
                     selectedModule = module;
                     break;
                 }
             }

             if (selectedModule == null) {
                 return;
             }

             String gradeStr = JOptionPane.showInputDialog(this,
                     "Enter grade (0-100):",
                     enrollmentToUpdate.getGrade());
             if (gradeStr == null) {
                 return;
             }

             String[] statusOptions = {"ACTIVE", "CANCELLED"};
             String currentStatus = enrollmentToUpdate.getEnrollmentStatus().toString();

             String statusStr = (String) JOptionPane.showInputDialog(this,
                     "Select enrollment status:",
                     "Status Selection",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     statusOptions,
                     currentStatus);
             if (statusStr == null) {
                 return;
             }

             float grade = Float.parseFloat(gradeStr);
             Status status = Status.valueOf(statusStr);

             if ((selectedStudent.getStudentID() != enrollmentToUpdate.getStudent().getStudentID()
                     || selectedModule.getModuleID() != enrollmentToUpdate.getModule().getModuleID())
                     && status == Status.ACTIVE) {

                 for (Enrollment e : enrollments) {
                     if (e.getEnrollmentID() != enrollmentToUpdate.getEnrollmentID()
                             && e.getStudent().getStudentID() == selectedStudent.getStudentID()
                             && e.getModule().getModuleID() == selectedModule.getModuleID()
                             && e.getEnrollmentStatus() == Status.ACTIVE) {

                         JOptionPane.showMessageDialog(this,
                                 "This student is already enrolled in this module.",
                                 "Duplicate Enrollment",
                                 JOptionPane.ERROR_MESSAGE);
                         return;
                     }
                 }
             }

             enrollmentToUpdate.setStudent(selectedStudent);
             enrollmentToUpdate.setModule(selectedModule);
             enrollmentToUpdate.setGrade(grade);

             if (status == Status.ACTIVE) {
                 enrollmentToUpdate.activate();
             } else {
                 enrollmentToUpdate.cancel();
             }

             enrollmentToUpdate.updateEnrollment(enrollments);

             updateMainPanel("Enrollments");

             JOptionPane.showMessageDialog(this,
                     "Enrollment updated successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this,
                     "Invalid number format. Please enter valid numbers.",
                     "Input Error",
                     JOptionPane.ERROR_MESSAGE);
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error updating enrollment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     /**
      * Delete an enrollment
      *
      * @param selectedRow The selected row in the table
      */
     private void deleteEnrollment(int selectedRow) {
         try {

             int enrollmentId = (int) tblCourses.getValueAt(selectedRow, 0);
             Enrollment enrollmentToDelete = null;

             for (Enrollment e : enrollments) {
                 if (e.getEnrollmentID() == enrollmentId) {
                     enrollmentToDelete = e;
                     break;
                 }
             }

             if (enrollmentToDelete == null) {
                 JOptionPane.showMessageDialog(this,
                         "Enrollment not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             int confirm = JOptionPane.showConfirmDialog(this,
                     "Are you sure you want to delete this enrollment?",
                     "Confirm Delete",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.WARNING_MESSAGE);

             if (confirm != JOptionPane.YES_OPTION) {
                 return;
             }

             enrollmentToDelete.removeEnrollment(enrollments);

             updateMainPanel("Enrollments");

             JOptionPane.showMessageDialog(this,
                     "Enrollment deleted successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error deleting enrollment: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
     }

     private void assignModuleToTeacherActionPerformed(java.awt.event.ActionEvent evt) {
         try {

             int selectedRow = tblCourses.getSelectedRow();
             if (selectedRow == -1) {
                 JOptionPane.showMessageDialog(this,
                         "Please select a teacher to assign a module",
                         "No Selection",
                         JOptionPane.WARNING_MESSAGE);
                 return;
             }

             int teacherId = (int) tblCourses.getValueAt(selectedRow, 0);
             Teacher selectedTeacher = null;
             for (Teacher teacher : teachers) {
                 if (teacher.getStaffId() == teacherId) {
                     selectedTeacher = teacher;
                     break;
                 }
             }

             if (selectedTeacher == null) {
                 JOptionPane.showMessageDialog(this,
                         "Teacher not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             if (modules == null || modules.isEmpty()) {
                 JOptionPane.showMessageDialog(this,
                         "No modules available. Please add modules first.",
                         "No Modules",
                         JOptionPane.WARNING_MESSAGE);
                 return;
             }

             String[] moduleNames = modules.stream().map(Module::getModuleName).toArray(String[]::new);
             String selectedModuleName = (String) JOptionPane.showInputDialog(this,
                     "Select a module to assign:",
                     "Assign Module",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     moduleNames,
                     moduleNames.length > 0 ? moduleNames[0] : null);

             if (selectedModuleName == null) {
                 return;
             }

             Module selectedModule = null;
             for (Module module : modules) {
                 if (module.getModuleName().equals(selectedModuleName)) {
                     selectedModule = module;
                     break;
                 }
             }

             if (selectedModule == null) {
                 JOptionPane.showMessageDialog(this,
                         "Module not found.",
                         "Error",
                         JOptionPane.ERROR_MESSAGE);
                 return;
             }

             selectedTeacher.assignModule(selectedModule);

             FileDataStore.saveTeachers(teachers);

             JOptionPane.showMessageDialog(this,
                     "Module assigned successfully!",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE);

             updateMainPanel("Teachers");

         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                     "Error assigning module: " + e.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
         }
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
             java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
             java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
             java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         //</editor-fold>

         /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
                 new AdminView().setVisible(true);
             }
         });
     }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton btnLogout;
     private javax.swing.JButton jButton1;
     private javax.swing.JButton jButton2;
     private javax.swing.JButton jButton3;
     private javax.swing.JButton jButton4;
     private javax.swing.JLabel jLabel1;
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
     private javax.swing.JLabel lblWelcome;
     private javax.swing.JTable tblCourses;
     // End of variables declaration//GEN-END:variables
}

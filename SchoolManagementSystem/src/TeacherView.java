import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Omayr
 */
public class TeacherView extends javax.swing.JFrame {
    private Teacher currentTeacher;
    private ArrayList<Session> sessions;
    private ArrayList<Student> students;
    private Module selectedModule;
    private Session selectedSession;

    /**
     * Creates new form TeacherView
     */
    public TeacherView() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Creates new form TeacherView with specified teacher data
     * @param teacher The teacher whose data will be displayed
     */
    public TeacherView(Teacher teacher) {
        initComponents();
        this.currentTeacher = teacher;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.sessions = FileDataStore.loadSessions();
        this.students = FileDataStore.loadStudents();
        displayTeacherInfo();
        loadAssignedModules();
        
        // Set up table selection listener for courses
        tblCourses.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblCourses.getSelectedRow() != -1) {
                int moduleId = (int) tblCourses.getValueAt(tblCourses.getSelectedRow(), 0);
                loadModuleSessions(moduleId);
            }
        });
        
        // Set up table selection listener for sessions
        tblSessions.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblSessions.getSelectedRow() != -1) {
                int sessionId = (int) tblSessions.getValueAt(tblSessions.getSelectedRow(), 0);
                loadSessionAttendance(sessionId);
            }
        });
    }
    
    /**
     * Display the teacher's information in the appropriate labels
     */
    private void displayTeacherInfo() {
        if (currentTeacher != null) {
            lblStudentID.setText(String.valueOf(currentTeacher.getStaffId()));
            lblName.setText(currentTeacher.getName());
            lblEmail.setText(currentTeacher.getEmail());
            lblWelcome.setText("Welcome, " + currentTeacher.getName());
        }
    }
    
    /**
     * Load the teacher's assigned modules and display them in the table
     */
    private void loadAssignedModules() {
        if (currentTeacher != null) {
            DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
            // Clear existing rows
            model.setRowCount(0);
            
            // Load modules from the teacher
            ArrayList<Module> assignedModules = currentTeacher.getAssignedModules();
            
            // If the teacher doesn't have any modules assigned yet, try to load modules from the system
            if (assignedModules.isEmpty()) {
                // This is a placeholder for module assignment logic
                // In a real system, you might want to query for modules assigned to this teacher
                ArrayList<Module> allModules = FileDataStore.loadModules();
                
                // For demonstration, we'll assign some modules to the teacher if they exist
                for (Module module : allModules) {
                    // Add rows to the table
                    model.addRow(new Object[]{
                        module.getModuleID(),
                        module.getModuleName(),
                        module.getMaxCapacity()
                    });
                }
            } else {
                // Add assigned modules to the table
                for (Module module : assignedModules) {
                    model.addRow(new Object[]{
                        module.getModuleID(),
                        module.getModuleName(),
                        module.getMaxCapacity()
                    });
                }
            }
        }
    }

    /**
     * Load sessions for a selected module
     * @param moduleId The ID of the selected module
     */
    private void loadModuleSessions(int moduleId) {
        // Find the selected module
        ArrayList<Module> modules = FileDataStore.loadModules();
        for (Module module : modules) {
            if (module.getModuleID() == moduleId) {
                selectedModule = module;
                break;
            }
        }
        
        if (selectedModule == null) {
            return;
        }
        
        // Load sessions for this module
        DefaultTableModel model = (DefaultTableModel) tblSessions.getModel();
        model.setRowCount(0);
        
        for (Session session : sessions) {
            if (session.getModule() != null && session.getModule().getModuleID() == moduleId) {
                model.addRow(new Object[]{
                    session.getSessionID(),
                    session.getSessionName(),
                    session.getStartTime(),
                    session.getEndTime(),
                    session.getStatus()
                });
            }
        }
    }
    
    /**
     * Load attendance records for a selected session
     * @param sessionId The ID of the selected session
     */
    private void loadSessionAttendance(int sessionId) {
        // Find the selected session
        for (Session session : sessions) {
            if (session.getSessionID() == sessionId) {
                selectedSession = session;
                break;
            }
        }
        
        if (selectedSession == null) {
            return;
        }
        
        // Load attendance for this session
        DefaultTableModel model = (DefaultTableModel) tblAttendance.getModel();
        model.setRowCount(0);
        
        ArrayList<Student> attendees = selectedSession.getAttendees();
        if (attendees != null && !attendees.isEmpty()) {
            for (Student student : attendees) {
                model.addRow(new Object[]{
                    student.getStudentID(),
                    student.getName(),
                    student.getEmail(),
                    "Present"
                });
            }
        }
    }
    
    /**
     * Record attendance for a student in the selected session
     */
    private void recordAttendance() {
        if (selectedSession == null) {
            JOptionPane.showMessageDialog(this, "Please select a session first.",
                    "No Session Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show a dialog to select a student
        ArrayList<Student> moduleStudents = getStudentsForModule(selectedModule);
        if (moduleStudents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students enrolled in this module.",
                    "No Students", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Create a list of student names for display in the dialog
        String[] studentNames = new String[moduleStudents.size()];
        for (int i = 0; i < moduleStudents.size(); i++) {
            Student student = moduleStudents.get(i);
            studentNames[i] = student.getName() + " (ID: " + student.getStudentID() + ")";
        }
        
        // Show the student selection dialog
        String selectedStudentName = (String) JOptionPane.showInputDialog(this,
                "Select a student to record attendance:", "Record Attendance",
                JOptionPane.QUESTION_MESSAGE, null, studentNames, studentNames[0]);
        
        if (selectedStudentName == null) {
            return; // User canceled
        }
        
        // Extract the student ID from the selection
        int selectedStudentId = Integer.parseInt(selectedStudentName.substring(
                selectedStudentName.lastIndexOf("ID: ") + 4, selectedStudentName.lastIndexOf(")")));
        
        // Find the selected student
        Student selectedStudent = null;
        for (Student student : moduleStudents) {
            if (student.getStudentID() == selectedStudentId) {
                selectedStudent = student;
                break;
            }
        }
        
        if (selectedStudent == null) {
            return;
        }
        
        // Check if the student is already in the attendance list
        ArrayList<Student> attendees = selectedSession.getAttendees();
        boolean alreadyPresent = false;
        
        for (Student attendee : attendees) {
            if (attendee.getStudentID() == selectedStudent.getStudentID()) {
                alreadyPresent = true;
                break;
            }
        }
        
        if (alreadyPresent) {
            JOptionPane.showMessageDialog(this, "This student is already marked as present.",
                    "Already Present", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Add the student to the attendance list
            attendees.add(selectedStudent);
            selectedSession.setAttendees(attendees);
            
            // Update the session in the list
            for (int i = 0; i < sessions.size(); i++) {
                if (sessions.get(i).getSessionID() == selectedSession.getSessionID()) {
                    sessions.set(i, selectedSession);
                    break;
                }
            }
            
            // Save the updated sessions
            FileDataStore.saveSessions(sessions);
            
            // Refresh the attendance table
            loadSessionAttendance(selectedSession.getSessionID());
            
            JOptionPane.showMessageDialog(this, "Attendance recorded successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Get students enrolled in a specific module
     * @param module The module to get students for
     * @return List of students enrolled in the module
     */
    private ArrayList<Student> getStudentsForModule(Module module) {
        ArrayList<Student> moduleStudents = new ArrayList<>();
        ArrayList<Enrollment> enrollments = FileDataStore.loadEnrollments();
        
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getModule().getModuleID() == module.getModuleID()) {
                moduleStudents.add(enrollment.getStudent());
            }
        }
        
        return moduleStudents;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblStudentID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSessions = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAttendance = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnRecordAttendance = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setText("Teacher Dashboard");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teacher Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Teacher ID:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Email:");

        lblStudentID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStudentID.setText("0");

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblName.setText("Teacher Name");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("teacher@example.com");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblStudentID))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lblEmail)))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStudentID))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblName))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblEmail))
                .addGap(41, 41, 41))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enrolled Courses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course ID", "Course Name", "Credits"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCourses);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Your Courses:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Module Sessions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Session ID", "Session Name", "Start Time", "End Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSessions);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Sessions:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Session Attendance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Email", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAttendance);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Attendance:");

        btnRecordAttendance.setBackground(new java.awt.Color(153, 204, 255));
        btnRecordAttendance.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnRecordAttendance.setText("Record Attendance");
        btnRecordAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordAttendanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRecordAttendance)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecordAttendance)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        StudentLogin loginForm = new StudentLogin();
        loginForm.setVisible(true);
        this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRecordAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordAttendanceActionPerformed
        recordAttendance();
    }//GEN-LAST:event_btnRecordAttendanceActionPerformed

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
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRecordAttendance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JTable tblAttendance;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblSessions;
    // End of variables declaration//GEN-END:variables
}

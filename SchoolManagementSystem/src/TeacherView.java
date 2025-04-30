import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private ArrayList<Session> teacherSessions;
    private Session selectedSession;
    private ArrayList<Student> allStudents;
    private Map<Integer, Boolean> attendanceMap; // Maps student ID to attendance status

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
        displayTeacherInfo();
        loadAssignedModules();
        
        // Initialize attendance tracking
        this.teacherSessions = new ArrayList<>();
        this.attendanceMap = new HashMap<>();
        this.allStudents = FileDataStore.loadStudents();
        loadTeacherSessions();
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
     * Load all sessions for modules assigned to this teacher
     */
    private void loadTeacherSessions() {
        if (currentTeacher == null) return;
        
        // Get all sessions from file storage
        ArrayList<Session> allSessions = FileDataStore.loadSessions();
        teacherSessions.clear();
        
        // Get all modules assigned to the teacher
        ArrayList<Module> assignedModules = currentTeacher.getAssignedModules();
        
        // Filter sessions that belong to the teacher's modules
        for (Session session : allSessions) {
            Module sessionModule = session.getModule();
            if (sessionModule != null) {
                for (Module teacherModule : assignedModules) {
                    if (teacherModule.getModuleID() == sessionModule.getModuleID()) {
                        teacherSessions.add(session);
                        break;
                    }
                }
            }
        }
        
        // Update the sessions table
        updateSessionsTable();
    }
    
    /**
     * Update the sessions table with current session data
     */
    private void updateSessionsTable() {
        DefaultTableModel model = (DefaultTableModel) tblSessions.getModel();
        model.setRowCount(0); // Clear existing rows
        
        for (Session session : teacherSessions) {
            model.addRow(new Object[]{
                session.getSessionID(),
                session.getSessionName(),
                session.getModule().getModuleName(),
                session.getStartTime(),
                session.getEndTime(),
                session.getClassroom() != null ? session.getClassroom().getRoomName() : "TBA"
            });
        }
    }
    
    /**
     * Load students for attendance for a selected session
     */
    private void loadStudentsForAttendance() {
        if (selectedSession == null) return;
        
        DefaultTableModel model = (DefaultTableModel) tblAttendance.getModel();
        model.setRowCount(0); // Clear existing rows
        
        // Initialize attendance map for this session
        attendanceMap.clear();
        
        // Get current attendees for this session
        ArrayList<Student> currentAttendees = selectedSession.getAttendees();
        
        // Get all enrollments to find students who should be in this session
        ArrayList<Enrollment> enrollments = FileDataStore.loadEnrollments();
        ArrayList<Student> enrolledStudents = new ArrayList<>();
        
        // Find all students enrolled in this module
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getModule().getModuleID() == selectedSession.getModule().getModuleID()) {
                enrolledStudents.add(enrollment.getStudent());
            }
        }
        
        // Add enrolled students to the table
        for (Student student : enrolledStudents) {
            boolean isPresent = false;
            
            // Check if student is already marked as present in this session
            for (Student attendee : currentAttendees) {
                if (attendee.getStudentID() == student.getStudentID()) {
                    isPresent = true;
                    break;
                }
            }
            
            // Add to attendance map
            attendanceMap.put(student.getStudentID(), isPresent);
            
            // Add to table
            model.addRow(new Object[]{
                student.getStudentID(),
                student.getName(),
                student.getEmail(),
                isPresent
            });
        }
    }
    
    /**
     * Save attendance records for the selected session
     */
    private void saveAttendance() {
        if (selectedSession == null) return;
        
        ArrayList<Student> updatedAttendees = new ArrayList<>();
        
        // For each student in the attendance map
        for (Map.Entry<Integer, Boolean> entry : attendanceMap.entrySet()) {
            int studentId = entry.getKey();
            boolean isPresent = entry.getValue();
            
            if (isPresent) {
                // Find the student in allStudents and add to attendees
                for (Student student : allStudents) {
                    if (student.getStudentID() == studentId) {
                        updatedAttendees.add(student);
                        break;
                    }
                }
            }
        }
        
        // Update the session with new attendees
        selectedSession.setAttendees(updatedAttendees);
        
        // Update session in the list
        ArrayList<Session> allSessions = FileDataStore.loadSessions();
        for (int i = 0; i < allSessions.size(); i++) {
            if (allSessions.get(i).getSessionID() == selectedSession.getSessionID()) {
                allSessions.set(i, selectedSession);
                break;
            }
        }
        
        // Save back to file
        FileDataStore.saveSessions(allSessions);
        
        // Refresh the teacher sessions
        loadTeacherSessions();
        
        JOptionPane.showMessageDialog(this, 
            "Attendance saved successfully for " + selectedSession.getSessionName(), 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Handle the selection change in the sessions table
     */
    private void sessionTableSelectionChanged() {
        int selectedRow = tblSessions.getSelectedRow();
        if (selectedRow >= 0) {
            int sessionId = (int) tblSessions.getValueAt(selectedRow, 0);
            
            // Find the session in teacherSessions
            for (Session session : teacherSessions) {
                if (session.getSessionID() == sessionId) {
                    selectedSession = session;
                    break;
                }
            }
            
            // Load students for this session
            loadStudentsForAttendance();
        }
    }
    
    /**
     * Toggle attendance status for a student
     * @param studentId The ID of the student
     */
    private void toggleAttendance(int studentId) {
        if (attendanceMap.containsKey(studentId)) {
            // Toggle the attendance status
            boolean currentStatus = attendanceMap.get(studentId);
            attendanceMap.put(studentId, !currentStatus);
            
            // Refresh the attendance table
            loadStudentsForAttendance();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAttendance = new javax.swing.JTable();
        btnSaveAttendance = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();

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
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sessions and Attendance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Session ID", "Session Name", "Module", "Start Time", "End Time", "Classroom"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSessions.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sessionTableSelectionChanged();
            }
        });
        jScrollPane2.setViewportView(tblSessions);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Sessions:");

        tblAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Email", "Present"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAttendance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblAttendance.rowAtPoint(evt.getPoint());
                int col = tblAttendance.columnAtPoint(evt.getPoint());
                if (col == 3) {
                    int studentId = (int) tblAttendance.getValueAt(row, 0);
                    toggleAttendance(studentId);
                }
            }
        });
        jScrollPane3.setViewportView(tblAttendance);

        btnSaveAttendance.setText("Save Attendance");
        btnSaveAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAttendance();
            }
        });

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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveAttendance)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveAttendance)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Courses", jPanel3);
        jTabbedPane1.addTab("Sessions", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        StudentLogin loginForm = new StudentLogin();
        loginForm.setVisible(true);
        this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

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
    private javax.swing.JButton btnSaveAttendance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JTable tblAttendance;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblSessions;
    // End of variables declaration//GEN-END:variables
}

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
 * @author Osama
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
        
        initAttendanceBtn();
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
            DefaultTableModel model = (DefaultTableModel) tblCourses1.getModel();
            
            model.setRowCount(0);
            
            
            ArrayList<Module> assignedModules = currentTeacher.getAssignedModules();
            
            
            if (assignedModules.isEmpty()) {
                
                ArrayList<Module> allModules = FileDataStore.loadModules();
                
                
                for (Module module : allModules) {
                    currentTeacher.assignModule(module);
                }
                
                
                assignedModules = currentTeacher.getAssignedModules();
                
                
                ArrayList<Teacher> teachers = FileDataStore.loadTeachers();
                for (int i = 0; i < teachers.size(); i++) {
                    if (teachers.get(i).getStaffId() == currentTeacher.getStaffId()) {
                        teachers.set(i, currentTeacher);
                        break;
                    }
                }
                FileDataStore.saveTeachers(teachers);
            }
            
            
            for (Module module : assignedModules) {
                model.addRow(new Object[]{
                    module.getModuleID(),
                    module.getModuleName(),
                    module.getMaxCapacity()
                });
            }
        }
    }

    /**
     * Initialize the Add Attendance button action
     */
    private void initAttendanceBtn() {
        addAttendanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAttendanceBtnActionPerformed(evt);
            }
        });
    }

    /**
     * Handle the Add Attendance button click
     */
    private void addAttendanceBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedSession == null) {
            JOptionPane.showMessageDialog(this, 
                "Please select a session first.", 
                "No Session Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int selectedRow = tblCourses.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student first.", 
                "No Student Selected", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int studentId = (int) tblCourses.getValueAt(selectedRow, 0);
        String studentName = (String) tblCourses.getValueAt(selectedRow, 1);
        
        
        Student selectedStudent = null;
        for (Student s : students) {
            if (s.getStudentID() == studentId) {
                selectedStudent = s;
                break;
            }
        }
        
        if (selectedStudent == null) {
            JOptionPane.showMessageDialog(this, 
                "Student not found in the system.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        String[] options = {"Present", "Absent"};
        int choice = JOptionPane.showOptionDialog(this,
                "Mark " + studentName + " as:",
                "Attendance",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        boolean isPresent = (choice == 0); // Present = 0, Absent = 1
        
        
        ArrayList<Attendance> attendances = FileDataStore.loadAttendance();
        Attendance attendance = new Attendance(selectedStudent, selectedSession, isPresent);
        attendance.saveAttendance(attendances);
        
        
        if (isPresent) {
            ArrayList<Student> attendees = selectedSession.getAttendees();
            if (!containsStudent(attendees, selectedStudent)) {
                attendees.add(selectedStudent);
                selectedSession.setAttendees(attendees);
                selectedSession.updateSession(sessions);
            }
        }
        
        JOptionPane.showMessageDialog(this, 
            studentName + " marked as " + (isPresent ? "present" : "absent") + ".", 
            "Attendance Recorded", 
            JOptionPane.INFORMATION_MESSAGE);
        
        
        loadSessionAttendance();
    }

    /**
     * Check if a student is in the attendees list
     */
    private boolean containsStudent(ArrayList<Student> students, Student student) {
        for (Student s : students) {
            if (s.getStudentID() == student.getStudentID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Load the attendance data for the selected session
     */
    private void loadSessionAttendance() {
        if (selectedSession == null) {
            return;
        }
        
        
        Module sessionModule = selectedSession.getModule();
        
        
        DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
        model.setRowCount(0);
        
        
        if (model.getColumnCount() < 3) {
            model.setColumnIdentifiers(new String[]{"Student ID", "Student Name", "Status"});
        }
        
        
        ArrayList<Enrollment> enrollments = FileDataStore.loadEnrollments();
        ArrayList<Student> moduleStudents = new ArrayList<>();
        
        
        System.out.println("Selected module ID: " + sessionModule.getModuleID() + ", Name: " + sessionModule.getModuleName());
        
        
        this.students = FileDataStore.loadStudents();
        
        
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found - creating sample enrollments");
            
            for (Student student : students) {
                Enrollment enrollment = new Enrollment(student, sessionModule, Status.ACTIVE);
                moduleStudents.add(student);
                
                
                enrollments.add(enrollment);
            }
            
            FileDataStore.saveEnrollments(enrollments);
        } else {
            
            System.out.println("Processing " + enrollments.size() + " enrollments");
            for (Enrollment e : enrollments) {
                try {
                    
                    if (e.getModule() != null && e.getStudent() != null && 
                        e.getModule().getModuleID() == sessionModule.getModuleID() && 
                        e.getEnrollmentStatus() == Status.ACTIVE) {
                        moduleStudents.add(e.getStudent());
                        System.out.println("Added student: " + e.getStudent().getName() + " to module students");
                    }
                } catch (Exception ex) {
                    System.out.println("Error processing enrollment: " + ex.getMessage());
                }
            }
        }
        
        
        if (moduleStudents.isEmpty()) {
            System.out.println("No enrolled students found - adding all students for demonstration");
            
            moduleStudents.addAll(students);
            
            
            for (Student student : students) {
                Enrollment enrollment = new Enrollment(student, sessionModule, Status.ACTIVE);
                enrollments.add(enrollment);
            }
            
            FileDataStore.saveEnrollments(enrollments);
        }
        
        
        ArrayList<Attendance> attendanceRecords = FileDataStore.loadAttendance();
        
        
        ArrayList<Student> sessionAttendees = selectedSession.getAttendees();
        if (sessionAttendees == null) {
            sessionAttendees = new ArrayList<>();
        }
        
        
        for (Student student : moduleStudents) {
            String attendanceStatus = "Not Marked";
            
            
            for (Attendance record : attendanceRecords) {
                if (record.getSession().getSessionID() == selectedSession.getSessionID() && 
                    record.getStudent().getStudentID() == student.getStudentID()) {
                    attendanceStatus = record.isPresent() ? "Present" : "Absent";
                    break;
                }
            }
            
            
            if (attendanceStatus.equals("Not Marked")) {
                for (Student attendee : sessionAttendees) {
                    if (attendee.getStudentID() == student.getStudentID()) {
                        attendanceStatus = "Present";
                        break;
                    }
                }
            }
            
            
            model.addRow(new Object[]{
                student.getStudentID(),
                student.getName(),
                attendanceStatus
            });
        }
        
        
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, 
                "No students found enrolled in this module.", 
                "No Students", 
                JOptionPane.INFORMATION_MESSAGE);
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
        tblCourses1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        addAttendanceBtn = new javax.swing.JButton();
        selectSessionBtn = new javax.swing.JButton();

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
                .addGap(18, 300, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblName))
                .addGap(18, 300, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblEmail))
                .addGap(41, 41, 41))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCourses);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Session Attendance");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assigned Modules", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblCourses1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Module Name", "Credits"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCourses1);
        if (tblCourses1.getColumnModel().getColumnCount() > 0) {
            tblCourses1.getColumnModel().getColumn(2).setHeaderValue("Credits");
        }

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Your Modules:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
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

        addAttendanceBtn.setBackground(new java.awt.Color(51, 153, 255));
        addAttendanceBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAttendanceBtn.setText("Add Attendance");

        selectSessionBtn.setBackground(new java.awt.Color(51, 153, 255));
        selectSessionBtn.setForeground(new java.awt.Color(255, 255, 255));
        selectSessionBtn.setText("Select Session");
        selectSessionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSessionBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(selectSessionBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addAttendanceBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectSessionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addAttendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Login loginForm = new Login();
        loginForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void selectSessionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSessionBtnActionPerformed
        if (currentTeacher == null || sessions == null) {
            JOptionPane.showMessageDialog(this, 
                "No sessions available.", 
                "No Sessions", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        this.sessions = FileDataStore.loadSessions();
        
        if (sessions.isEmpty()) {
            
            
            this.sessions = FileDataStore.loadSessions();
            
            if (sessions.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No sessions available.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        ArrayList<Session> teacherSessions = new ArrayList<>();
        ArrayList<Module> assignedModules = currentTeacher.getAssignedModules();
        
        for (Session session : sessions) {
            for (Module module : assignedModules) {
                if (session.getModule().getModuleID() == module.getModuleID()) {
                    teacherSessions.add(session);
                    break;
                }
            }
        }
        
        if (teacherSessions.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "You don't have any sessions scheduled for your modules.", 
                "No Sessions", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        String[] sessionNames = new String[teacherSessions.size()];
        for (int i = 0; i < teacherSessions.size(); i++) {
            Session s = teacherSessions.get(i);
            sessionNames[i] = s.getSessionName() + " (" + s.getModule().getModuleName() + 
                              ", " + s.getStartTime() + " - " + s.getEndTime() + ")";
        }
        
        
        String selectedSessionName = (String) JOptionPane.showInputDialog(this,
                "Select session:",
                "Session Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sessionNames,
                sessionNames.length > 0 ? sessionNames[0] : null);
        
        if (selectedSessionName == null) return;
        
        
        selectedSession = null; 
        for (int i = 0; i < teacherSessions.size(); i++) {
            if (selectedSessionName.equals(sessionNames[i])) {
                selectedSession = teacherSessions.get(i);
                break;
            }
        }
        
        if (selectedSession != null) {
            
            jLabel6.setText("Session Attendance: " + selectedSession.getSessionName());
            
            loadSessionAttendance();
        }
    }//GEN-LAST:event_selectSessionBtnActionPerformed

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
    private javax.swing.JButton addAttendanceBtn;
    private javax.swing.JButton btnLogout;
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
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JButton selectSessionBtn;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTable tblCourses1;
    // End of variables declaration//GEN-END:variables
}

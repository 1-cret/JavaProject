import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.lang.reflect.Array;

public class SchoolMangementSystem {

    public void addStudent(ArrayList<Student> students) {
        System.out.println("Enter student name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter student email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.println("Enter student annual fee: ");
        Scanner sc = new Scanner(System.in);
        float annualFee = sc.nextFloat();
        System.out.println("Enter student year (1-12): ");
        Scanner sc1 = new Scanner(System.in);
        int year = sc1.nextInt();
        System.out.println("Enter student name: ");
        String password = new Scanner(System.in).nextLine();
        Student newStudent = new Student(name, email, annualFee, year, password);
        newStudent.createAccount(students, newStudent);
        System.out.println("Student added successfully!");
    }

    public void updateStudent(ArrayList<Student> students) {
        System.out.println("Enter student ID: ");
        Scanner sc = new Scanner(System.in);
        int studentID = sc.nextInt();
        Student updatedStudent = null;
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                updatedStudent = s;
                break;
            }
        }
        if (updatedStudent == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.println("Enter student name: ");
        String name = new Scanner(System.in).nextLine();
        updatedStudent.setName(name);
        System.out.println("Enter student email: ");
        String email = new Scanner(System.in).nextLine();
        updatedStudent.setEmail(email);
        System.out.println("Enter student annual fee: ");
        float annualFee = Float.parseFloat(System.console().readLine());
        updatedStudent.setAnnualFee(annualFee);
        updatedStudent.manageAccount(students);
        System.out.println("Student updated successfully!");
    }

    public void deleteStudent(ArrayList<Student> students) {
        System.out.println("Enter student ID: ");
        Scanner sc = new Scanner(System.in);
        int studentID = sc.nextInt();
        Student deletedStudent = null;
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                deletedStudent = s;
                break;
            }
        }
        if (deletedStudent == null) {
            System.out.println("Student not found!");
            return;
        }
        deletedStudent.removeAccount(students);
        System.out.println("Student deleted successfully!");
    }

    public void viewStudentCourses(ArrayList<Enrollment> enrollments) {
        System.out.println("Enter student ID: ");
        Scanner sc = new Scanner(System.in);
        int studentID = sc.nextInt();
        for (Enrollment e : enrollments) {
            if (e.getStudent().getStudentID() == studentID) {
                System.out.println("Student ID: " + e.getStudent().getStudentID());
                System.out.println("Module ID: " + e.getModule().getModuleID());
                System.out.println("Enrollment status: " + e.getEnrollmentStatus());
            }
        }
    }

    public void listAllStudents(ArrayList<Student> students) {
        for (Student s : students) {
            System.out.println("Student ID: " + s.getStudentID());
            System.out.println("Student name: " + s.getName());
            System.out.println("Student email: " + s.getEmail());
            System.out.println("Student annual fee: " + s.getAnnualFee());
        }
    }

    public void addModule(ArrayList<Module> modules) {
        System.out.println("Enter module name: ");
        String moduleName = new Scanner(System.in).nextLine();
        System.out.println("Enter module max capacity: ");
        Scanner sc = new Scanner(System.in);
        int maxCapacity = sc.nextInt();
        System.out.println("Enter module year (1-12): ");
        Scanner sc1 = new Scanner(System.in);
        int moduleYear = sc1.nextInt();
        Module newModule = new Module(moduleName, maxCapacity, null, moduleYear);
        newModule.addModule(modules);
    }

    public void updateModule(ArrayList<Module> modules) {
        System.out.println("Enter module ID: ");
        Scanner sc = new Scanner(System.in);
        int moduleID = sc.nextInt();
        Module updatedModule = null;
        for (Module m : modules) {
            if (m.getModuleID() == moduleID) {
                updatedModule = m;
                break;
            }
        }
        if (updatedModule == null) {
            System.out.println("Module not found!");
            return;
        }
        System.out.println("Enter module name: ");
        String moduleName = new Scanner(System.in).nextLine();
        updatedModule.setModuleName(moduleName);
        System.out.println("Enter module max capacity: ");
        Scanner sc1 = new Scanner(System.in);
        int maxCapacity = sc1.nextInt();
        updatedModule.setMaxCapacity(maxCapacity);
        updatedModule.updateModule(modules, updatedModule);
        System.out.println("Module updated successfully!");
    }

    public void deleteModule(ArrayList<Module> modules) {
        System.out.println("Enter module ID: ");
        Scanner sc1 = new Scanner(System.in);
        int moduleID = sc1.nextInt();
        Module deletedModule = null;
        for (Module m : modules) {
            if (m.getModuleID() == moduleID) {
                deletedModule = m;
                break;
            }
        }
        if (deletedModule == null) {
            System.out.println("Module not found!");
            return;
        }
        deletedModule.removeModule(modules, deletedModule);
        System.out.println("Module deleted successfully!");
    }

    public void listAllModules(ArrayList<Module> modules) {
        for (Module m : modules) {
            System.out.println("Module ID: " + m.getModuleID());
            System.out.println("Module name: " + m.getModuleName());
            System.out.println("Module max capacity: " + m.getMaxCapacity());
        }
    }

    public void addClassroom(ArrayList<Classroom> classrooms) {
        System.out.println("Enter classroom name: ");
        String roomName = new Scanner(System.in).nextLine();
        System.out.println("Enter classroom capacity: ");
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        Classroom newClassroom = new Classroom(roomName, capacity);
        newClassroom.addClassroom(classrooms);
        System.out.println("Classroom added successfully!");
    }

    public void updateClassroom(ArrayList<Classroom> classrooms) {
        System.out.println("Enter classroom ID: ");
        Scanner sc = new Scanner(System.in);
        int classroomID = sc.nextInt();
        Classroom updatedClassroom = null;
        for (Classroom c : classrooms) {
            if (c.getClassroomId() == classroomID) {
                updatedClassroom = c;
                break;
            }
        }
        if (updatedClassroom == null) {
            System.out.println("Classroom not found!");
            return;
        }
        System.out.println("Enter classroom name: ");
        String roomName = new Scanner(System.in).nextLine();
        updatedClassroom.setRoomName(roomName);
        System.out.println("Enter classroom capacity: ");
        Scanner s1c = new Scanner(System.in);
        int capacity = s1c.nextInt();
        updatedClassroom.setCapacity(capacity);
        updatedClassroom.updateClassroom(classrooms);
        System.out.println("Classroom updated successfully!");
    }

    public void deleteClassroom(ArrayList<Classroom> classrooms) {
        System.out.println("Enter classroom ID: ");
        Scanner sc = new Scanner(System.in);
        int classroomID = sc.nextInt();
        Classroom deletedClassroom = null;
        for (Classroom c : classrooms) {
            if (c.getClassroomId() == classroomID) {
                deletedClassroom = c;
                break;
            }
        }
        if (deletedClassroom == null) {
            System.out.println("Classroom not found!");
            return;
        }
        deletedClassroom.deleteClassroom(classrooms);
        System.out.println("Classroom deleted successfully!");
    }

    public void listAllClassrooms(ArrayList<Classroom> classrooms) {
        for (Classroom c : classrooms) {
            System.out.println("Classroom ID: " + c.getClassroomId());
            System.out.println("Classroom name: " + c.getRoomName());
            System.out.println("Classroom capacity: " + c.getCapacity());
        }
    }

    public void addEnrollment(ArrayList<Enrollment> enrollments, ArrayList<Student> students,
            ArrayList<Module> modules) {
        System.out.println("Enter student ID: ");
        Scanner sc = new Scanner(System.in);
        int studentID = sc.nextInt();
        System.out.println("Enter module ID: ");
        Scanner sc1 = new Scanner(System.in);
        int moduleID = sc1.nextInt();

        Student student = null;
        Module module = null;
        // Set student and module data from the arraylist
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        for (Module m : modules) {
            if (m.getModuleID() == moduleID) {
                module = m;
            }
        }

        if (module == null) {
            System.out.println("Module not found!");
            return;
        }

        Status status = Status.ACTIVE;
        Enrollment newEnrollment = new Enrollment(student, module, status);
        newEnrollment.addEnrollment(enrollments);
        System.out.println("Enrollment added successfully!");
    }

    public void updateEnrollment(ArrayList<Enrollment> enrollments, ArrayList<Student> students,
            ArrayList<Module> modules) {
        System.out.println("Enter enrollment ID: ");
        Scanner sc = new Scanner(System.in);
        int enrollmentID = sc.nextInt();
        Enrollment updatedEnrollment = null;
        for (Enrollment e : enrollments) {
            if (e.getEnrollmentID() == enrollmentID) {
                updatedEnrollment = e;
                break;
            }
        }
        if (updatedEnrollment == null) {
            System.out.println("Enrollment not found!");
            return;
        }
        System.out.println("Enter student ID: ");
        Scanner sc1 = new Scanner(System.in);
        int studentID = sc1.nextInt();
        System.out.println("Enter module ID: ");
        Scanner sc2 = new Scanner(System.in);
        int moduleID = sc2.nextInt();
        System.out.println("Enter grade: ");
        float grade = Float.parseFloat(System.console().readLine());
        Student student = null;
        Module module = null;
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        for (Module m : modules) {
            if (m.getModuleID() == moduleID) {
                module = m;
                break;
            }
        }
        if (module == null) {
            System.out.println("Module not found!");
            return;
        }
        updatedEnrollment.setStudent(student);
        updatedEnrollment.setModule(module);
        updatedEnrollment.setGrade(grade);
        updatedEnrollment.updateEnrollment(enrollments);
        System.out.println("Enrollment updated successfully!");
    }

    public void deleteEnrollment(ArrayList<Enrollment> enrollments) {
        System.out.println("Enter enrollment ID: ");
        Scanner sc = new Scanner(System.in);
        int enrollmentID = sc.nextInt();
        Enrollment deletedEnrollment = null;
        for (Enrollment e : enrollments) {
            if (e.getEnrollmentID() == enrollmentID) {
                deletedEnrollment = e;
                break;
            }
        }
        if (deletedEnrollment == null) {
            System.out.println("Enrollment not found!");
            return;
        }
        deletedEnrollment.removeEnrollment(enrollments);
        System.out.println("Enrollment deleted successfully!");
    }

    public void listAllEnrollments(ArrayList<Enrollment> enrollments) {
        for (Enrollment e : enrollments) {
            System.out.println("Enrollment ID: " + e.getEnrollmentID());
            System.out.println("Student ID: " + e.getStudent().getStudentID());
            System.out.println("Module ID: " + e.getModule().getModuleID());
            System.out.println("Grade: " + e.getGrade());
            System.out.println("Status: " + e.getEnrollmentStatus());
        }
    }

    /*
     * public Session(Module module, String sessionName, Date startTime, String
     * endTime,
     * Classroom classroom, ArrayList<Student> attendees, String status)
     */
    public void addSession(ArrayList<Session> sessions, ArrayList<Module> modules, ArrayList<Classroom> classrooms) {
        // Get module and classroom by ID
        System.out.println("Enter module ID: ");
        int moduleID = Integer.parseInt(System.console().readLine());
        System.out.println("Enter classroom ID: ");
        int classroomID = Integer.parseInt(System.console().readLine());
        // Get module and classroom by ID
        Module module = null;
        Classroom classroom = null;
        for (Module m : modules) {
            if (m.getModuleID() == moduleID) {
                module = m;
                break;
            }
        }
        if (module == null) {
            System.out.println("Module not found!");
            return;
        }
        for (Classroom c : classrooms) {
            if (c.getClassroomId() == classroomID) {
                classroom = c;
                break;
            }
        }
        if (classroom == null) {
            System.out.println("Classroom not found!");
            return;
        }
        System.out.println("Enter session name: ");
        String sessionName = new Scanner(System.in).nextLine();
        System.out.println("Enter start time: ");
        String startTime = new Scanner(System.in).nextLine();
        System.out.println("Enter end time: ");
        String endTime = new Scanner(System.in).nextLine();
        System.out.println("Enter session status: ");
        String status = new Scanner(System.in).nextLine();
        Session newSession = new Session(module, sessionName, startTime, endTime, classroom, null, status);
        newSession.addSession(sessions);
        System.out.println("Session added successfully!");
    }

    public void updateSession(ArrayList<Session> sessions, ArrayList<Module> modules, ArrayList<Classroom> classrooms) {
        System.out.println("Enter session ID: ");
        int sessionID = Integer.parseInt(System.console().readLine());
        Session updatedSession = null;
        for (Session s : sessions) {
            if (s.getSessionID() == sessionID) {
                updatedSession = s;
                break;
            }
        }
        if (updatedSession == null) {
            System.out.println("Session not found!");
            return;
        }
        System.out.println("Enter module ID: ");
        int moduleID = Integer.parseInt(System.console().readLine());
        System.out.println("Enter classroom ID: ");
        int classroomID = Integer.parseInt(System.console().readLine());
        Module module = null;
        Classroom classroom = null;
        for (Module m : modules) {
            if (m.getModuleID() == moduleID) {
                module = m;
                break;
            }
        }
        if (module == null) {
            System.out.println("Module not found!");
            return;
        }
        for (Classroom c : classrooms) {
            if (c.getClassroomId() == classroomID) {
                classroom = c;
                break;
            }
        }
        if (classroom == null) {
            System.out.println("Classroom not found!");
            return;
        }

        System.out.println("Enter session name: ");
        String sessionName = new Scanner(System.in).nextLine();
        System.out.println("Enter start time: ");
        String startTime = new Scanner(System.in).nextLine();
        System.out.println("Enter end time: ");
        String endTime = new Scanner(System.in).nextLine();
        System.out.println("Enter session status: ");
        String status = new Scanner(System.in).nextLine();
        updatedSession.setSessionName(sessionName);
        updatedSession.setStartTime(startTime);
        updatedSession.setEndTime(endTime);
        updatedSession.setStatus(status);
        updatedSession.setModule(module);
        updatedSession.setClassroom(classroom);
        updatedSession.updateSession(sessions);
        System.out.println("Session updated successfully!");
    }

    public void deleteSession(ArrayList<Session> sessions) {
        System.out.println("Enter session ID: ");
        int sessionID = Integer.parseInt(System.console().readLine());
        Session deletedSession = null;
        for (Session s : sessions) {
            if (s.getSessionID() == sessionID) {
                deletedSession = s;
                break;
            }
        }
        if (deletedSession == null) {
            System.out.println("Session not found!");
            return;
        }
        deletedSession.removeSession(sessions);
        System.out.println("Session deleted successfully!");
    }

    public void listAllSessions(ArrayList<Session> sessions) {
        for (Session s : sessions) {
            System.out.println("Session ID: " + s.getSessionID());
            System.out.println("Module ID: " + s.getModule().getModuleID());
            System.out.println("Session name: " + s.getSessionName());
            System.out.println("Start time: " + s.getStartTime());
            System.out.println("End time: " + s.getEndTime());
            System.out.println("Classroom ID: " + s.getClassroom().getClassroomId());
            System.out.println("Status: " + s.getStatus());
        }
    }

    public void addPayment(ArrayList<Payment> payments, ArrayList<Student> students) {
        System.out.println("Enter student ID: ");
        int studentID = Integer.parseInt(System.console().readLine());
        System.out.println("Enter amount: ");
        float amount = Float.parseFloat(System.console().readLine());
        System.out.println("Enter description: ");
        String description = new Scanner(System.in).nextLine();
        System.out.println("Enter date: ");
        String date = new Scanner(System.in).nextLine();
        Student student = null;
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        Payment newPayment = new Payment(amount, student, description, date);
        newPayment.addPayment(payments);
        System.out.println("Payment added successfully!");
        // float amount, Student student, String description, String date
    }

    public void updatePayment(ArrayList<Payment> payments) {
        Payment updatedPayment = null;
        System.out.println("Enter payment ID: ");
        int paymentID = Integer.parseInt(System.console().readLine());
        for (Payment p : payments) {
            if (p.getPaymentID() == paymentID) {
                updatedPayment = p;
                break;
            }
        }
        if (updatedPayment == null) {
            System.out.println("Payment not found!");
            return;
        }
        System.out.println("Enter amount: ");
        float amount = Float.parseFloat(System.console().readLine());
        System.out.println("Enter description: ");
        String description = new Scanner(System.in).nextLine();
        System.out.println("Enter date: ");
        String date = new Scanner(System.in).nextLine();
        updatedPayment.setAmount(amount);
        updatedPayment.setDescription(description);
        updatedPayment.setDate(date);
        updatedPayment.updatePayment(payments);
        System.out.println("Payment updated successfully!");
    }

    public void deletePayment(ArrayList<Payment> payments) {
        System.out.println("Enter payment ID: ");
        int paymentID = Integer.parseInt(System.console().readLine());
        Payment deletedPayment = null;
        for (Payment p : payments) {
            if (p.getPaymentID() == paymentID) {
                deletedPayment = p;
                break;
            }
        }
        if (deletedPayment == null) {
            System.out.println("Payment not found!");
            return;
        }
        deletedPayment.removePayment(payments);
        System.out.println("Payment deleted successfully!");
    }

    public void listAllPayments(ArrayList<Payment> payments) {
        for (Payment p : payments) {
            System.out.println("Payment ID: " + p.getPaymentID());
            System.out.println("Amount: " + p.getAmount());
            System.out.println("Student ID: " + p.getPayeeId().getStudentID());
            System.out.println("Description: " + p.getDescription());
            System.out.println("Date: " + p.getDate());
        }
    }

    public void listAllTeachers(ArrayList<Teacher> teachers) {
        for (Teacher t : teachers) {
            System.out.println("Teacher ID: " + t.getStaffId());
            System.out.println("Teacher name: " + t.getName());
            System.out.println("Teacher email: " + t.getEmail());
        }
    }

    public void addTeacher(ArrayList<Teacher> teachers) {
        System.out.println("Enter teacher name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter teacher email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.println("Enter role: ");
        String role = new Scanner(System.in).nextLine();
        StaffStatus status = StaffStatus.ACTIVE;
        System.out.println("Enter student name: ");
        String password = new Scanner(System.in).nextLine();
        Teacher newTeacher = new Teacher(name, email, role, status, password);
        teachers.add(newTeacher);
        System.out.println("Teacher added successfully!");
    }

    public void updateTeacher(ArrayList<Teacher> teachers) {
        System.out.println("Enter teacher ID: ");
        int teacherID = Integer.parseInt(System.console().readLine());
        // Check if teacher exists
        Teacher updatedTeacher = null;
        for (Teacher t : teachers) {
            if (t.getStaffId() == teacherID) {
                updatedTeacher = t;
                break;
            }
        }
        if (updatedTeacher == null) {
            System.out.println("Teacher not found!");
            return;
        }
        System.out.println("Enter teacher name: ");
        String name = new Scanner(System.in).nextLine();
        updatedTeacher.setName(name);
        System.out.println("Enter teacher email: ");
        String email = new Scanner(System.in).nextLine();
        updatedTeacher.setEmail(email);
        updatedTeacher.updateTeacher(teachers);
        System.out.println("Teacher updated successfully!");
    }

    public void deleteTeacher(ArrayList<Teacher> teachers) {
        System.out.println("Enter teacher ID: ");
        int teacherID = Integer.parseInt(System.console().readLine());
        // Check if teacher exists
        Teacher deletedTeacher = null;
        for (Teacher t : teachers) {
            if (t.getStaffId() == teacherID) {
                deletedTeacher = t;
                break;
            }
        }
        if (deletedTeacher == null) {
            System.out.println("Teacher not found!");
            return;
        }

        teachers.remove(deletedTeacher);
        System.out.println("Teacher deleted successfully!");
    }

    public class studentFunction {

        // This will run for studentflow (loggedin) student
        public void enrollToModule(ArrayList<Module> modules, ArrayList<Enrollment> enrollments, Student student) {
            System.out.println("Enter module ID: ");
            int moduleID = Integer.parseInt(System.console().readLine());
            Module module = null;
            for (Module m : modules) {
                if (m.getModuleID() == moduleID) {
                    module = m;
                    break;
                }
            }
            if (module == null) {
                System.out.println("Module not found!");
                return;
            }
            Status status = Status.ACTIVE;
            // Check year   
            if (student.getYear() != module.getModuleYear()) {
                System.out.println("You are not eligible to enroll in this module.");
                return;
            }
            // Check if full
            if (module.getMaxCapacity() <= enrollments.size()) {
                System.out.println("Module is full. Cannot enroll.");
                return;
            }
            // Check if already enrolled
            for (Enrollment e : enrollments) {
                if (e.getStudent().getStudentID() == student.getStudentID() && e.getModule().getModuleID() == module.getModuleID()) {
                    System.out.println("You are already enrolled in this module.");
                    return;
                }
            }

            Enrollment newEnrollment = new Enrollment(student, module, status);
            enrollments.add(newEnrollment);
            System.out.println("Enrolled successfully!");
        }

        public void viewEnrolledCourses(ArrayList<Enrollment> enrollments, Student student) {
            System.out.println("Enrolled courses for student ID: " + student.getStudentID());
            for (Enrollment e : enrollments) {
                if (e.getStudent().getStudentID() == student.getStudentID()) {
                    System.out.println("Module ID: " + e.getModule().getModuleID());
                    System.out.println("Enrollment status: " + e.getEnrollmentStatus());
                }
            }
        }

        public void viewPayments(ArrayList<Payment> payments, Student student) {
            System.out.println("Payments for student ID: " + student.getStudentID());
            for (Payment p : payments) {
                if (p.getPayeeId().getStudentID() == student.getStudentID()) {
                    System.out.println("Payment ID: " + p.getPaymentID());
                    System.out.println("Amount: " + p.getAmount());
                    System.out.println("Description: " + p.getDescription());
                    System.out.println("Date: " + p.getDate());
                }
            }
        }
    }

    public class TeacherFunction {

        public void registerAttendee(ArrayList<Session> sessions, ArrayList<Student> students) {
            System.out.println("Enter session ID: ");
            int sessionID = Integer.parseInt(System.console().readLine());
            System.out.println("Enter student ID: ");
            int studentID = Integer.parseInt(System.console().readLine());
            Session session = null;
            Student student = null;
            for (Session s : sessions) {
                if (s.getSessionID() == sessionID) {
                    session = s;
                    break;
                }
            }
            if (session == null) {
                System.out.println("Session not found!");
                return;
            }
            for (Student s : students) {
                if (s.getStudentID() == studentID) {
                    student = s;
                    break;
                }
            }
            if (student == null) {
                System.out.println("Student not found!");
                return;
            }
            ArrayList<Student> attendees = session.getAttendees();
            attendees.add(student);
            session.setAttendees(attendees);
            System.out.println("Student registered for session successfully!");
        }

        // This will run for teacherflow (loggedin) teacher
        // Add session
        public void addSession(ArrayList<Session> sessions, ArrayList<Module> modules, ArrayList<Classroom> classrooms) {
            // Get module and classroom by ID
            System.out.println("Enter module ID: ");
            int moduleID = Integer.parseInt(System.console().readLine());
            System.out.println("Enter classroom ID: ");
            int classroomID = Integer.parseInt(System.console().readLine());
            // Get module and classroom by ID
            Module module = null;
            Classroom classroom = null;
            for (Module m : modules) {
                if (m.getModuleID() == moduleID) {
                    module = m;
                    break;
                }
            }
            if (module == null) {
                System.out.println("Module not found!");
                return;
            }
            for (Classroom c : classrooms) {
                if (c.getClassroomId() == classroomID) {
                    classroom = c;
                    break;
                }
            }
            if (classroom == null) {
                System.out.println("Classroom not found!");
                return;
            }
            System.out.println("Enter session name: ");
            String sessionName = new Scanner(System.in).nextLine();
            System.out.println("Enter start time: ");
            String startTime = new Scanner(System.in).nextLine();
            System.out.println("Enter end time: ");
            String endTime = new Scanner(System.in).nextLine();
            System.out.println("Enter session status: ");
            String status = new Scanner(System.in).nextLine();
            Session newSession = new Session(module, sessionName, startTime, endTime, classroom, null, status);
            newSession.addSession(sessions);
        }

        // List students
        public void listStudents(ArrayList<Student> students) {
            System.out.println("Students enrolled in the module: ");
            for (Student s : students) {
                System.out.println("Student ID: " + s.getStudentID());
                System.out.println("Student name: " + s.getName());
                System.out.println("Student email: " + s.getEmail());
                System.out.println("Student annual fee: " + s.getAnnualFee());
            }
        }

    }

    public static void studentFlow(ArrayList<Student> students, ArrayList<Enrollment> enrollments,
            ArrayList<Session> sessions, ArrayList<Payment> payments, ArrayList<Module> modules, String[] args) throws Exception {
        System.out.println("Please login to continue.");
        int studentID = 0;
        System.out.println("Enter your student ID: ");
        studentID = new Scanner(System.in).nextInt();
        // Check if student exists
        Student student = null;
        for (Student s : students) {
            if (s.getStudentID() == studentID) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student not found!");
            main(args);
        }

        int option = 0;
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. View enrolled-in courses");
            System.out.println("2. Enroll in a course");
            System.out.println("3. View payments");
            System.out.println("4. Logout");
            option = Integer.parseInt(System.console().readLine());
            studentFunction studentFunction = new SchoolMangementSystem().new studentFunction();
            switch (option) {
                case 1:
                    studentFunction.viewEnrolledCourses(enrollments, student);
                    break;
                case 2:
                    studentFunction.enrollToModule(modules, enrollments, student);
                    break;
                case 3:
                    studentFunction.viewPayments(payments, student);
                case 4:
                    System.out.println("Logging out...");
                    main(null);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void staffFlow(ArrayList<Student> students, ArrayList<Enrollment> enrollments,
            ArrayList<Session> sessions, ArrayList<Payment> payments, ArrayList<Module> modules,
            ArrayList<Classroom> classrooms, String[] args, ArrayList<Teacher> staff) throws Exception {
        boolean loggedIn = false;
        if (!loggedIn) {
            System.out.println("Please login to continue.");
            int staffID = 0;
            System.out.println("Enter your staff ID: ");
            Scanner sc = new Scanner(System.in);
            staffID = sc.nextInt();
            Admin admin = new Admin(staffID);
            loggedIn = admin.login();
            if (loggedIn) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed. Please try again.");
                staffFlow(students, enrollments, sessions, payments, modules, classrooms, args, staff);
            }
        }
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Manage students");
            System.out.println("2. Manage modules");
            System.out.println("3. Manage classrooms");
            System.out.println("4. Manage enrollments");
            System.out.println("5. Manage sessions");
            System.out.println("6. Manage payments");
            System.out.println("7. Manage teachers");
            System.out.println("8. Exit");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            SchoolMangementSystem app = new SchoolMangementSystem();
            switch (option) {
                case 1 -> {
                    System.out.println("1. Add student");
                    System.out.println("2. Update student");
                    System.out.println("3. Delete student");
                    System.out.println("4. View student courses");
                    System.out.println("5. List all students");
                    System.out.println("6. Back");
                    Scanner sc1 = new Scanner(System.in);
                    int studentOption = sc1.nextInt();
                    switch (studentOption) {
                        case 1 ->
                            app.addStudent(students);
                        case 2 ->
                            app.updateStudent(students);
                        case 3 ->
                            app.deleteStudent(students);
                        case 4 ->
                            app.viewStudentCourses(enrollments);
                        case 5 ->
                            app.listAllStudents(students);
                        case 6 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("1. Add module");
                    System.out.println("2. Update module");
                    System.out.println("3. Delete module");
                    System.out.println("4. List all modules");
                    System.out.println("5. Back");
                    Scanner sc1 = new Scanner(System.in);
                    int moduleOption = sc1.nextInt();
                    switch (moduleOption) {
                        case 1 ->
                            app.addModule(modules);
                        case 2 ->
                            app.updateModule(modules);
                        case 3 ->
                            app.deleteModule(modules);
                        case 4 ->
                            app.listAllModules(modules);
                        case 5 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }

                case 3 -> {
                    System.out.println("1. Add classroom");
                    System.out.println("2. Update classroom");
                    System.out.println("3. Delete classroom");
                    System.out.println("4. List all classrooms");
                    System.out.println("5. Back");
                    Scanner sc2 = new Scanner(System.in);
                    int classroomOption = sc2.nextInt();
                    switch (classroomOption) {
                        case 1 ->
                            app.addClassroom(classrooms);
                        case 2 ->
                            app.updateClassroom(classrooms);
                        case 3 ->
                            app.deleteClassroom(classrooms);
                        case 4 ->
                            app.listAllClassrooms(classrooms);
                        case 5 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }

                case 4 -> {
                    System.out.println("1. Add enrollment");
                    System.out.println("2. Update enrollment");
                    System.out.println("3. Delete enrollment");
                    System.out.println("4. List all enrollments");
                    System.out.println("5. Back");
                    Scanner sc1 = new Scanner(System.in);
                    int enrollmentOption = sc1.nextInt();
                    switch (enrollmentOption) {
                        case 1 ->
                            app.addEnrollment(enrollments, students, modules);
                        case 2 ->
                            app.updateEnrollment(enrollments, students, modules);
                        case 3 ->
                            app.deleteEnrollment(enrollments);
                        case 4 ->
                            app.listAllEnrollments(enrollments);
                        case 5 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }

                case 5 -> {
                    System.out.println("1. Add session");
                    System.out.println("2. Update session");
                    System.out.println("3. Delete session");
                    System.out.println("4. List all sessions");
                    System.out.println("5. Back");
                    Scanner sc3 = new Scanner(System.in);
                    int sessionOption = sc3.nextInt();
                    switch (sessionOption) {
                        case 1 ->
                            app.addSession(sessions, modules, classrooms);
                        case 2 ->
                            app.updateSession(sessions, modules, classrooms);
                        case 3 ->
                            app.deleteSession(sessions);
                        case 4 ->
                            app.listAllSessions(sessions);

                        case 5 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }

                case 6 -> {
                    System.out.println("1. Add payment");
                    System.out.println("2. Update payment");
                    System.out.println("3. Delete payment");
                    System.out.println("4. List all payments");
                    System.out.println("5. Back");
                    Scanner sc1 = new Scanner(System.in);
                    int paymentOption = sc1.nextInt();
                    switch (paymentOption) {
                        case 1 ->
                            app.addPayment(payments, students);
                        case 2 ->
                            app.updatePayment(payments);
                        case 3 ->
                            app.deletePayment(payments);
                        case 4 ->
                            app.listAllPayments(payments);
                        case 5 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }

                case 7 -> {
                    System.out.println("1. Add teacher");
                    System.out.println("2. Update teacher");
                    System.out.println("3. Delete teacher");
                    System.out.println("4. List all teachers");
                    System.out.println("5. Back");
                    Scanner sc1 = new Scanner(System.in);
                    int teacherOption = sc1.nextInt();
                    switch (teacherOption) {
                        case 1 ->
                            app.addTeacher(staff);
                        case 2 ->
                            app.updateTeacher(staff);
                        case 3 ->
                            app.deleteTeacher(staff);
                        case 4 ->
                            app.listAllTeachers(staff);
                        case 5 ->
                            main(args);
                        default -> {
                            System.out.println("Invalid option. Please try again.");
                            main(args);
                        }
                    }
                }

                case 8 -> {
                    System.out.println("Exiting...");
                    main(args);
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    main(args);
                }
            }
        }

    }

    public static void teacherFlow(ArrayList<Student> students, ArrayList<Enrollment> enrollments,
            ArrayList<Session> sessions, ArrayList<Payment> payments, ArrayList<Module> modules,
            ArrayList<Classroom> classrooms, String[] args, ArrayList<Teacher> staff) throws Exception {
        boolean loggedIn = false;
        if (!loggedIn) {
            System.out.println("Please login to continue.");
            int teacherID = 0;
            System.out.println("Enter your teacher ID: ");
            Scanner sc = new Scanner(System.in);
            teacherID = sc.nextInt();
            Teacher teacher = null;
            for (Teacher t : staff) {
                if (t.getStaffId() == teacherID) {
                    teacher = t;
                    break;
                }
            }
            if (teacher == null) {
                System.out.println("Teacher not found!");
                main(args);
            }
            if (loggedIn) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed. Please try again.");
                teacherFlow(students, enrollments, sessions, payments, modules, classrooms, args, staff);
            }
        }
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. List students");
            System.out.println("2. Add session");
            System.out.println("3. Register attendee");
            System.out.println("4. Logout");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            TeacherFunction teacherFunction = new SchoolMangementSystem().new TeacherFunction();
            switch (option) {
                case 1:
                    teacherFunction.listStudents(students);
                    break;
                case 2:
                    teacherFunction.addSession(sessions, modules, classrooms);
                    break;
                case 3:
                    teacherFunction.registerAttendee(sessions, students);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    main(args);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    main(args);
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // Load data from files
        ArrayList<Student> students = FileDataStore.loadStudents();
        ArrayList<Module> modules = FileDataStore.loadModules();
        ArrayList<Classroom> classrooms = FileDataStore.loadClassrooms();
        ArrayList<Teacher> teachers = FileDataStore.loadTeachers();
        ArrayList<Session> sessions = FileDataStore.loadSessions(modules, classrooms);
        ArrayList<Enrollment> enrollments = FileDataStore.loadEnrollments(students, modules);
        ArrayList<Payment> payments = FileDataStore.loadPayments(students);

        System.out.println("Welcome to School Management System!");
        System.out.println("1. Student.");
        System.out.println("2. Admin.");
        System.out.println("3. Teacher.");
        Scanner sc = new Scanner(System.in);
        int userType = sc.nextInt();

        if (userType == 1) {
            // Get data from files
            // Load data from files
            studentFlow(students, enrollments, sessions, payments, modules, args);
            // Save changes to files
            FileDataStore.saveStudents(students);
            FileDataStore.saveEnrollments(enrollments);
            FileDataStore.savePayments(payments);
        } else if (userType == 2) {
            staffFlow(students, enrollments, sessions, payments, modules, classrooms, args, teachers);
            // Save all changes to files
            FileDataStore.saveStudents(students);
            FileDataStore.saveModules(modules);
            FileDataStore.saveClassrooms(classrooms);
            FileDataStore.saveTeachers(teachers);
            FileDataStore.saveSessions(sessions);
            FileDataStore.saveEnrollments(enrollments);
            FileDataStore.savePayments(payments);
        } else if (userType == 3) {
            teacherFlow(students, enrollments, sessions, payments, modules, classrooms, args, teachers);
            // Save changes to files
            FileDataStore.saveSessions(sessions);
            FileDataStore.saveTeachers(teachers);
        } else {
            System.out.println("Invalid option. Please try again.");
            main(args);
        }
    }
}

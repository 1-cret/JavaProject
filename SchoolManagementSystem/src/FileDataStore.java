import java.io.*;
import java.util.ArrayList;

public class FileDataStore {
    private static final String DATA_DIRECTORY = "data/";
    private static final String STUDENTS_FILE = DATA_DIRECTORY + "students.txt";
    private static final String MODULES_FILE = DATA_DIRECTORY + "modules.txt";
    private static final String CLASSROOMS_FILE = DATA_DIRECTORY + "classrooms.txt";
    private static final String ENROLLMENTS_FILE = DATA_DIRECTORY + "enrollments.txt";
    private static final String SESSIONS_FILE = DATA_DIRECTORY + "sessions.txt";
    private static final String PAYMENTS_FILE = DATA_DIRECTORY + "payments.txt";
    private static final String TEACHERS_FILE = DATA_DIRECTORY + "teachers.txt";
    private static final String ADMINS_FILE = DATA_DIRECTORY + "admins.txt";

    // Initialize the data directory if it doesn't exist
    static {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // Student operations
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File file = new File(STUDENTS_FILE);
            if (!file.exists()) {
                // Create the file if it doesn't exist
                file.createNewFile();
                
                System.out.println("No student data found.");
                return students;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Loading student data: " + line);
                String[] data = line.split(",");
                if (data.length >= 5) {
                    int studentID = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    float annualFee = Float.parseFloat(data[4]);
                    int year = Integer.parseInt(data[5]);
                    
                    Student student = new Student(name, email, annualFee, year, password);
                    // Set the studentID directly to maintain consistency
                    students.add(student);
                }
            }
            System.out.println("Student data loaded successfully.");
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public static void saveStudents(ArrayList<Student> students) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(STUDENTS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Student student : students) {
                String line = student.getStudentID() + "," + 
                              student.getName() + "," + 
                              student.getEmail() + "," + 
                                student.getPassword() + "," +
                              student.getAnnualFee() + "," + 
                              student.getYear();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    // Admin operations
    public static ArrayList<Admin> loadAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            File file = new File(ADMINS_FILE);
            if (!file.exists()) {
                return admins;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    int staffId = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    StaffStatus status = StaffStatus.valueOf(data[4]);
                    String role = "admin";
                    Admin admin = new Admin(name, email, role, status, password);
                    admins.add(admin);
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading admins: " + e.getMessage());
        }
        return admins;
    }
    public static void saveAdmins(ArrayList<Admin> admins) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(ADMINS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Admin admin : admins) {
                String line = admin.getStaffId() + "," + 
                              admin.getName() + "," + 
                              admin.getEmail() + "," + 
                                admin.getPassword() + "," +
                              admin.getStatus();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving admins: " + e.getMessage());
        }
    }

    // Module operations
    public static ArrayList<Module> loadModules() {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            File file = new File(MODULES_FILE);
            if (!file.exists()) {
                return modules;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    int moduleID = Integer.parseInt(data[0]);
                    String moduleName = data[1];
                    int maxCapacity = Integer.parseInt(data[2]);
                    int moduleYear = Integer.parseInt(data[3]);
                    
                    // Load assessments (if any) from another file
                    ArrayList<Assessment> assessments = new ArrayList<>();
                    
                    Module module = new Module(moduleName, maxCapacity, assessments, moduleYear);
                    modules.add(module);
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading modules: " + e.getMessage());
        }
        return modules;
    }

    public static void saveModules(ArrayList<Module> modules) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(MODULES_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Module module : modules) {
                String line = module.getModuleID() + "," + 
                              module.getModuleName() + "," + 
                              module.getMaxCapacity() + "," + 
                              module.getModuleYear();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving modules: " + e.getMessage());
        }
    }

    // Classroom operations
    public static ArrayList<Classroom> loadClassrooms() {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        try {
            File file = new File(CLASSROOMS_FILE);
            if (!file.exists()) {
                return classrooms;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    int classroomId = Integer.parseInt(data[0]);
                    String roomName = data[1];
                    int capacity = Integer.parseInt(data[2]);
                    
                    Classroom classroom = new Classroom(roomName, capacity);
                    classrooms.add(classroom);
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading classrooms: " + e.getMessage());
        }
        return classrooms;
    }

    public static void saveClassrooms(ArrayList<Classroom> classrooms) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(CLASSROOMS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Classroom classroom : classrooms) {
                String line = classroom.getClassroomId() + "," + 
                              classroom.getRoomName() + "," + 
                              classroom.getCapacity();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving classrooms: " + e.getMessage());
        }
    }

    // Teacher operations
    public static ArrayList<Teacher> loadTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            File file = new File(TEACHERS_FILE);
            if (!file.exists()) {
                return teachers;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    int staffId = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    StaffStatus status = StaffStatus.valueOf(data[4]);
                    String role = "teacher";
                    Teacher teacher = new Teacher(name, email, role, status, password);
                    teachers.add(teacher);
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return teachers;
    }

    public static void saveTeachers(ArrayList<Teacher> teachers) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(TEACHERS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Teacher teacher : teachers) {
                String line = teacher.getStaffId() + "," + 
                              teacher.getName() + "," + 
                              teacher.getEmail() + "," + 

                                teacher.getPassword() + "," +
                              teacher.getStatus();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving teachers: " + e.getMessage());
        }
    }

    // Session operations
    public static ArrayList<Session> loadSessions(ArrayList<Module> modules, ArrayList<Classroom> classrooms) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            File file = new File(SESSIONS_FILE);
            if (!file.exists()) {
                return sessions;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 7) {
                    int sessionID = Integer.parseInt(data[0]);
                    int moduleID = Integer.parseInt(data[1]);
                    String sessionName = data[2];
                    String startTime = data[3];
                    String endTime = data[4];
                    int classroomID = Integer.parseInt(data[5]);
                    String status = data[6];
                    
                    // Find module and classroom
                    Module module = null;
                    Classroom classroom = null;
                    
                    for (Module m : modules) {
                        if (m.getModuleID() == moduleID) {
                            module = m;
                            break;
                        }
                    }
                    
                    for (Classroom c : classrooms) {
                        if (c.getClassroomId() == classroomID) {
                            classroom = c;
                            break;
                        }
                    }
                    
                    if (module != null && classroom != null) {
                        Session session = new Session(module, sessionName, startTime, endTime, classroom, new ArrayList<>(), status);
                        sessions.add(session);
                    }
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading sessions: " + e.getMessage());
        }
        return sessions;
    }

    public static void saveSessions(ArrayList<Session> sessions) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(SESSIONS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Session session : sessions) {
                String line = session.getSessionID() + "," + 
                              session.getModule().getModuleID() + "," + 
                              session.getSessionName() + "," + 
                              session.getStartTime() + "," + 
                              session.getEndTime() + "," + 
                              session.getClassroom().getClassroomId() + "," + 
                              session.getStatus();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving sessions: " + e.getMessage());
        }
    }

    // Enrollment operations
    public static ArrayList<Enrollment> loadEnrollments(ArrayList<Student> students, ArrayList<Module> modules) {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        try {
            File file = new File(ENROLLMENTS_FILE);
            if (!file.exists()) {
                return enrollments;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    int enrollmentID = Integer.parseInt(data[0]);
                    int studentID = Integer.parseInt(data[1]);
                    int moduleID = Integer.parseInt(data[2]);
                    Status status = Status.valueOf(data[3]);
                    float grade = Float.parseFloat(data[4]);
                    
                    // Find student and module
                    Student student = null;
                    Module module = null;
                    
                    for (Student s : students) {
                        if (s.getStudentID() == studentID) {
                            student = s;
                            break;
                        }
                    }
                    
                    for (Module m : modules) {
                        if (m.getModuleID() == moduleID) {
                            module = m;
                            break;
                        }
                    }
                    
                    if (student != null && module != null) {
                        Enrollment enrollment = new Enrollment(student, module, status);
                        enrollment.setGrade(grade);
                        enrollments.add(enrollment);
                    }
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading enrollments: " + e.getMessage());
        }
        return enrollments;
    }

    public static void saveEnrollments(ArrayList<Enrollment> enrollments) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(ENROLLMENTS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Enrollment enrollment : enrollments) {
                String line = enrollment.getEnrollmentID() + "," + 
                              enrollment.getStudent().getStudentID() + "," + 
                              enrollment.getModule().getModuleID() + "," + 
                              enrollment.getEnrollmentStatus() + "," +
                              enrollment.getGrade();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving enrollments: " + e.getMessage());
        }
    }

    // Payment operations
    public static ArrayList<Payment> loadPayments(ArrayList<Student> students) {
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            File file = new File(PAYMENTS_FILE);
            if (!file.exists()) {
                return payments;
            }

            DataInputStream input = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    int paymentID = Integer.parseInt(data[0]);
                    float amount = Float.parseFloat(data[1]);
                    int studentID = Integer.parseInt(data[2]);
                    String description = data[3];
                    String date = data[4];
                    
                    // Find student
                    Student student = null;
                    for (Student s : students) {
                        if (s.getStudentID() == studentID) {
                            student = s;
                            break;
                        }
                    }
                    
                    if (student != null) {
                        Payment payment = new Payment(amount, student, description, date);
                        payments.add(payment);
                    }
                }
            }
            reader.close();
            input.close();
            
        } catch (IOException e) {
            System.err.println("Error loading payments: " + e.getMessage());
        }
        return payments;
    }

    public static void savePayments(ArrayList<Payment> payments) {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(PAYMENTS_FILE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            
            for (Payment payment : payments) {
                String line = payment.getPaymentID() + "," + 
                              payment.getAmount() + "," + 
                              payment.getPayeeId().getStudentID() + "," + 
                              payment.getDescription() + "," + 
                              payment.getDate();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            output.close();
            
        } catch (IOException e) {
            System.err.println("Error saving payments: " + e.getMessage());
        }
    }
}
import java.io.*;
import java.util.ArrayList;

public class FileDataStore {

    private static final String DATA_DIRECTORY = "src/data/";
    private static final String STUDENTS_FILE = DATA_DIRECTORY + "students.txt";
    private static final String MODULES_FILE = DATA_DIRECTORY + "modules.txt";
    private static final String CLASSROOMS_FILE = DATA_DIRECTORY + "classrooms.txt";
    private static final String ENROLLMENTS_FILE = DATA_DIRECTORY + "enrollments.txt";
    private static final String SESSIONS_FILE = DATA_DIRECTORY + "sessions.txt";
    private static final String PAYMENTS_FILE = DATA_DIRECTORY + "payments.txt";
    private static final String TEACHERS_FILE = DATA_DIRECTORY + "teachers.txt";
    private static final String ADMINS_FILE = DATA_DIRECTORY + "admins.txt";
    private static final String ATTENDANCE_FILE = DATA_DIRECTORY + "attendance.txt";

    static {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File file = new File(STUDENTS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("No student data found.");
                return students;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            students = (ArrayList<Student>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Student data loaded successfully.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public static void saveStudents(ArrayList<Student> students) {
        try {
            FileOutputStream fos = new FileOutputStream(STUDENTS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    public static ArrayList<Admin> loadAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            File file = new File(ADMINS_FILE);
            if (!file.exists()) {
                return admins;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            admins = (ArrayList<Admin>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading admins: " + e.getMessage());
        }
        return admins;
    }

    public static void saveAdmins(ArrayList<Admin> admins) {
        try {
            FileOutputStream fos = new FileOutputStream(ADMINS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(admins);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving admins: " + e.getMessage());
        }
    }

    public static ArrayList<Module> loadModules() {
        ArrayList<Module> modules = new ArrayList<>();
        try {
            File file = new File(MODULES_FILE);
            if (!file.exists()) {
                return modules;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            modules = (ArrayList<Module>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading modules: " + e.getMessage());
        }
        return modules;
    }

    public static void saveModules(ArrayList<Module> modules) {
        try {
            FileOutputStream fos = new FileOutputStream(MODULES_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(modules);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving modules: " + e.getMessage());
        }
    }

    public static ArrayList<Classroom> loadClassrooms() {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        try {
            File file = new File(CLASSROOMS_FILE);
            if (!file.exists()) {
                return classrooms;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            classrooms = (ArrayList<Classroom>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading classrooms: " + e.getMessage());
        }
        return classrooms;
    }

    public static void saveClassrooms(ArrayList<Classroom> classrooms) {
        try {
            FileOutputStream fos = new FileOutputStream(CLASSROOMS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(classrooms);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving classrooms: " + e.getMessage());
        }
    }

    public static ArrayList<Teacher> loadTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            File file = new File(TEACHERS_FILE);
            if (!file.exists()) {
                return teachers;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            teachers = (ArrayList<Teacher>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return teachers;
    }

    public static void saveTeachers(ArrayList<Teacher> teachers) {
        try {
            FileOutputStream fos = new FileOutputStream(TEACHERS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(teachers);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving teachers: " + e.getMessage());
        }
    }

    public static ArrayList<Session> loadSessions() {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            File file = new File(SESSIONS_FILE);
            if (!file.exists()) {
                return sessions;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sessions = (ArrayList<Session>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Sessions loaded successfully.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading sessions: " + e.getMessage());
        }
        return sessions;
    }

    public static ArrayList<Session> loadSessions(ArrayList<Module> modules, ArrayList<Classroom> classrooms) {
        return loadSessions();
    }

    public static void saveSessions(ArrayList<Session> sessions) {
        try {
            FileOutputStream fos = new FileOutputStream(SESSIONS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sessions);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving sessions: " + e.getMessage());
        }
    }

    public static ArrayList<Enrollment> loadEnrollments(ArrayList<Student> students, ArrayList<Module> modules) {
        return loadEnrollments();
    }

    
    public static ArrayList<Enrollment> loadEnrollments() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        try {
            File file = new File(ENROLLMENTS_FILE);
            if (!file.exists()) {
                return enrollments;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            enrollments = (ArrayList<Enrollment>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading enrollments: " + e.getMessage());
        }
        return enrollments;
    }

    public static void saveEnrollments(ArrayList<Enrollment> enrollments) {
        try {
            FileOutputStream fos = new FileOutputStream(ENROLLMENTS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(enrollments);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving enrollments: " + e.getMessage());
        }
    }

    public static ArrayList<Payment> loadPayments(ArrayList<Student> students) {
        return loadPayments();
    }
    
    public static ArrayList<Payment> loadPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            File file = new File(PAYMENTS_FILE);
            if (!file.exists()) {
                return payments;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            payments = (ArrayList<Payment>) ois.readObject();
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading payments: " + e.getMessage());
        }
        return payments;
    }

    public static void savePayments(ArrayList<Payment> payments) {
        try {
            FileOutputStream fos = new FileOutputStream(PAYMENTS_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(payments);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving payments: " + e.getMessage());
        }
    }
}

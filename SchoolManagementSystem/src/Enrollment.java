import java.util.ArrayList;

public class Enrollment implements IEnrollment {
    private static int enrollmentCounter = 0;
    private int enrollmentID;
    private Student student;
    private Module module;
    private Status enrollmentStatus;
    private float grade;

    public Enrollment(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public Enrollment(Student student, Module module, Status enrollmentStatus){
        this.student = student;
        this.module = module;
        this.enrollmentStatus = enrollmentStatus;
        grade = 0.0f;
        this.enrollmentID = enrollmentCounter++;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public float getGrade() {
        return grade;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }


    public Status getEnrollmentStatus() {
        return enrollmentStatus;
    }


    public void generateReports(ArrayList<Enrollment> enrollments, Module module) {
        int enrolledStudents = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getModule().equals(module)) {
                enrolledStudents++;
            }
        }
        System.out.println("Module: " + module.getModuleName());
        System.out.println("Total Enrolled Students: " + enrolledStudents);
        System.out.println("Max Capacity: " + module.getMaxCapacity());
        System.out.println("Status: " + (enrolledStudents >= module.getMaxCapacity() ? "Full" : "Available"));
    }

    
    public void addEnrollment(ArrayList<Enrollment> enrollments) {
        enrollments.add(this);
        // Persist the data to file
        FileDataStore.saveEnrollments(enrollments);
    }

    
    public void removeEnrollment(ArrayList<Enrollment> enrollments) {
        enrollments.remove(this);
        // Persist the data to file
        FileDataStore.saveEnrollments(enrollments);
    }

    
    public void updateEnrollment(ArrayList<Enrollment> enrollments) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getEnrollmentID() == this.enrollmentID) {
                enrollments.set(i, this);
                break;
            }
        }
        // Persist the data to file
        FileDataStore.saveEnrollments(enrollments);
    }

    @Override
    public void activate() {
        this.enrollmentStatus = Status.ACTIVE;
        System.out.println("Enrollment activated.");
    }

    @Override
    public void cancel() {
        enrollmentStatus = Status.CANCELLED;
        System.out.println("Enrollment cancelled.");
    }
}

enum Status {
    ACTIVE, CANCELLED;
}

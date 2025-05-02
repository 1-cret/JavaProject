//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.text.SimpleDateFormat;
//
//public class Attendance implements Serializable {
//    private static final long serialVersionUID = 1L;
//    private int attendanceId;
//    private Student student;
//    private Session session;
//    private String date;
//    private boolean present;
//    private static int attendanceCounter = 0;
//    
//    public Attendance(Student student, Session session, boolean present) {
//        this.student = student;
//        this.session = session;
//        this.present = present;
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        this.date = formatter.format(new Date());
//        this.attendanceId = attendanceCounter++;
//    }
//    
//    public int getAttendanceId() {
//        return attendanceId;
//    }
//    
//    public Student getStudent() {
//        return student;
//    }
//    
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//    
//    public Session getSession() {
//        return session;
//    }
//    
//    public void setSession(Session session) {
//        this.session = session;
//    }
//    
//    public String getDate() {
//        return date;
//    }
//    
//    public void setDate(String date) {
//        this.date = date;
//    }
//    
//    public boolean isPresent() {
//        return present;
//    }
//    
//    public void setPresent(boolean present) {
//        this.present = present;
//    }
//    
//    public void saveAttendance(ArrayList<Attendance> attendances) {
//        attendances.add(this);
//        FileDataStore.saveAttendance(attendances);
//    }
//    
//    public void updateAttendance(ArrayList<Attendance> attendances) {
//        for (int i = 0; i < attendances.size(); i++) {
//            if (attendances.get(i).getAttendanceId() == this.attendanceId) {
//                attendances.set(i, this);
//                break;
//            }
//        }
//        FileDataStore.saveAttendance(attendances);
//    }
//}
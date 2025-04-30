import java.util.*;

public class Session {
    private static int sessionCounter = 0;
    private int sessionID;
    private Module module;
    private String sessionName;
    private String startTime;
    private String endTime;
    private Classroom classroom;
    private ArrayList<Student> attendees;
    private String status;

    public Session(int sessionID) {
        this.sessionID = sessionID;
        this.attendees = new ArrayList<>();
    }

    public Session(Module module, String sessionName, String startTime, String endTime, Classroom classroom, ArrayList<Student> attendees, String status) {
        this.module = module;
        this.sessionName = sessionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.attendees = new ArrayList<>(attendees);
        this.status = status;
        this.sessionID = sessionCounter++;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setAttendees(ArrayList<Student> attendees) {
        this.attendees = new ArrayList<>(attendees);
    }

    public ArrayList<Student> getAttendees() {
        return new ArrayList<>(attendees);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void addSession(ArrayList<Session> session){
        session.add(this);
        // Persist the data to file
        FileDataStore.saveSessions(session);
    }
    
    public void removeSession(ArrayList<Session> session){
        session.remove(this);
        // Persist the data to file
        FileDataStore.saveSessions(session);
    }
    
    public void updateSession(ArrayList<Session> session){
        for (int i = 0; i < session.size(); i++) {
            if (session.get(i).getSessionID() == this.sessionID) {
                session.set(i, this);
                break;
            }
        }
        // Persist the data to file
        FileDataStore.saveSessions(session);
    }
}

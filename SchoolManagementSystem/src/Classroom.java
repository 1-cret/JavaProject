import java.util.ArrayList;

public class Classroom {
    private static int classroomCounter = 0;
    private int classroomId;
    private String roomName;
    private int capacity;
    private String resources;
    
    public Classroom(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.classroomId = classroomCounter++;
        this.resources = "";
        // This constructor will be used to create a new classroom that's not in the database yet
    }
    
    public Classroom(int classroomId) {
        this.classroomId = classroomId;
        // This constructor will be used to create a classroom that's already in the database
    }

    public int getClassroomId() {
        return classroomId;
    }
    
    // Added for backwards compatibility with existing code
    public int getClassroomID() {
        return classroomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public void setResources(String resources) {
        this.resources = resources;
    }
    
    public String getResources() {
        return resources;
    }
    
    public void updateClassroom(ArrayList<Classroom> classrooms) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassroomId() == this.getClassroomId()) {
                classrooms.set(classrooms.indexOf(classroom), this);
                break;
            }
        }
        // Persist the data to file
        FileDataStore.saveClassrooms(classrooms);
    }
    
    public void deleteClassroom(ArrayList<Classroom> classrooms) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassroomId() == this.getClassroomId()) {
                classrooms.remove(classroom);
                break;
            }
        }
        // Persist the data to file
        FileDataStore.saveClassrooms(classrooms);
    }
    
    public void addClassroom(ArrayList<Classroom> classrooms) {
        classrooms.add(this);
        // Persist the data to file
        FileDataStore.saveClassrooms(classrooms);
    }
}

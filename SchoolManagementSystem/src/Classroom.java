
import java.io.Serializable;
import java.util.ArrayList;

public class Classroom implements Serializable {

    private static int ClassroomCounter = 0;
    private static final long serialVersionUID = 1L;
    private int classroomId;
    private String roomName;
    private int capacity;
    private String resources;

    public Classroom(int classroomId) {
        this.classroomId = classroomId;
    }

    public Classroom(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.resources = "";
        this.classroomId = ClassroomCounter++;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

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
        FileDataStore.saveClassrooms(classrooms);
    }

    public void deleteClassroom(ArrayList<Classroom> classrooms) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassroomId() == this.getClassroomId()) {
                classrooms.remove(classroom);
                break;
            }
        }
        FileDataStore.saveClassrooms(classrooms);
    }

    public void addClassroom(ArrayList<Classroom> classrooms) {
        classrooms.add(this);
        FileDataStore.saveClassrooms(classrooms);
    }
}

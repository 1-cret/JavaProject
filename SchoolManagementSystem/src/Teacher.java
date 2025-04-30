import java.util.ArrayList;

public class Teacher extends Staff {
    private ArrayList<Module> assignedModules;
    
    public Teacher(int staffId) {
        super(staffId);
        assignedModules = new ArrayList<>();
    }
    
    public Teacher(String name, String email, String role, StaffStatus status, String password) {
        super(name, email, role, status, password);
        assignedModules = new ArrayList<>();
    }   
    
    public void setAssignedModules(ArrayList<Module> assignedModules) {
        this.assignedModules = assignedModules;
    }

    public ArrayList<Module> getAssignedModules() {
        return assignedModules;
    }

    public void assignModule(Module module) {
        assignedModules.add(module);
        System.out.println("Module " + module.getModuleID() + " assigned to teacher.");
    }

    public void removeModuleAssignment(int moduleID) {
        for (Module module : assignedModules) {
            if (module.getModuleID() == moduleID) {
                assignedModules.remove(module);
                break;
            }
        }
        System.out.println("Module " + moduleID + " removed from teacher's assignments.");
    }
    
    public void updateTeacher(ArrayList<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            if (teacher.getStaffId() == this.getStaffId()) {
                teacher.setName(this.getName());
                teacher.setEmail(this.getEmail());
                teacher.setRole(this.getRole());
                teacher.setStatus(this.getStatus());
                break;
            }
        }
        // Persist the data to file
        FileDataStore.saveTeachers(teachers);
    }

    public boolean login() {
        return true; // Placeholder for login logic
    }
}
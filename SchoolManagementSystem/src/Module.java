import java.util.*;

public class Module {
    private static int moduleCounter = 0;
    private int moduleID;
    private String moduleName;
    private int maxCapacity;
    private ArrayList<Assessment> assessment;
    private int moduleYear;
    
    public Module(int moduleID) {
        this.moduleID = moduleID;
    }

    
    public Module(String moduleName, int maxCapacity, ArrayList<Assessment> assessment, int moduleYear) {
        this.moduleName = moduleName;
        this.maxCapacity = maxCapacity;
        this.assessment = assessment;
        this.maxCapacity = maxCapacity;
        this.moduleYear = moduleYear;
        this.moduleID = moduleCounter++;
    }

    public int getModuleID() {
        return moduleID;
    }
    public void setModuleYear(int moduleYear) {
        this.moduleYear = moduleYear;
    }
    public int getModuleYear() {
        return moduleYear;
    }
    

   
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setAssessment(ArrayList<Assessment> assessment) {
        this.assessment = assessment;
    }
    public ArrayList<Assessment> getAssessments() {
        return assessment;
    }

    public void addModule(ArrayList<Module> module) {
        module.add(this);
        // Persist the data to file
        FileDataStore.saveModules(module);
    }
 
    
    public void removeModule(ArrayList<Module> module, Module removedModule) {
        module.remove(removedModule);
        // Persist the data to file
        FileDataStore.saveModules(module);
    }

    
    public void updateModule(ArrayList<Module> module, Module editedModule) {
        for (Module m : module) {
            if (m.getModuleID() == editedModule.getModuleID()) {
                m.setModuleName(editedModule.getModuleName());
                m.setMaxCapacity(editedModule.getMaxCapacity());
            }
        }
        // Persist the data to file
        FileDataStore.saveModules(module);
    }
}

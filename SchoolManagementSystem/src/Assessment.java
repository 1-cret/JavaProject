import java.util.Date;

public class Assessment {
    private static int assessmentCounter = 0;
    private int assessmentID;
    private Date date;
    private String duration;
    private String title;
    private Module module;

    
    public Assessment(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    
    public Assessment(int assessmentID, Date date, String duration, Module module, String title) {
        this.assessmentID = assessmentID;
        this.date = date;
        this.duration = duration;
        this.module = module;
        this.title = title;
        this.assessmentID = assessmentCounter++;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    
    public void setModule(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

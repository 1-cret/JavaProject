import java.io.Serializable;

public class Staff implements Serializable {
    private static int staffCounter = 0;
    private static final long serialVersionUID = 1L;
    private int staffId;
    private String name;
    private String email;
    private String role;
    private StaffStatus status;
    private String password;

    public Staff(int staffId) {
        this.staffId = staffId;
    }

    public Staff(String name, String email, String role, StaffStatus status, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.staffId = staffCounter++;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }
}

enum StaffStatus {
    ACTIVE, INACTIVE, SUSPENDED, TERMINATED;
}

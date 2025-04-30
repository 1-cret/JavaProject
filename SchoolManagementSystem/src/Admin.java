
import java.util.ArrayList;

public class Admin extends Staff {

    public Admin(int staffId) {
        super(staffId);
    } 
    public Admin(String name, String email, String role, StaffStatus status, String password) {
        super(name, email, role, status, password);
    }

    void manageAccount(ArrayList<Admin> Admin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

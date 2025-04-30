import java.util.*;
public class Payment {
    private static int paymentCounter = 0;
    private int paymentID;
    private float amount;
    private Student student;
    private String description;
    private String date;

    public Payment(int paymentID) {
        this.paymentID = paymentID;
    }

    public Payment(float amount, Student student, String description, String date) {
        this.amount = amount;
        this.student = student;
        this.description = description;
        this.date = date;
        this.paymentID = paymentCounter++; 
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setPayeeId(Student student) {
        this.student = student;
    }

    public Student getPayeeId() {
        return student;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void addPayment(ArrayList<Payment> payment) {
        payment.add(this);
        // Persist the data to file
        FileDataStore.savePayments(payment);
    }
    
    public void removePayment(ArrayList<Payment> payment) {
        payment.remove(this);
        // Persist the data to file
        FileDataStore.savePayments(payment);
    }
    
    public void updatePayment(ArrayList<Payment> payment) {
        for(int i = 0; i < payment.size(); i++) {
            if(payment.get(i).getPaymentID() == this.paymentID) {
                payment.set(i, this);
                break;
            }
        }
        // Persist the data to file
        FileDataStore.savePayments(payment);
    }

    public String generateReceipt() {
        return "Receipt for Payment ID: " + paymentID + "\nAmount: " + amount + "\nDate: " + date;
    }
}

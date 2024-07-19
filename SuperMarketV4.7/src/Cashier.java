
import java.time.LocalDateTime;
class Cashier extends Employee {
    private int userId;
    private String securityQuestion;
    private String password;
    private LocalDateTime date;
    private int idUserType;
    private Security security = new Security();
    private Employee employee;

    public Cashier(String name, String phoneNumber, String email, EmployeeType employeeType, int userId, String securityQuestion, String password, LocalDateTime date, int idUserType, Security security, Employee employee) {
        super(name, phoneNumber, email, employeeType);
        this.userId = userId;
        this.securityQuestion = securityQuestion;
        this.password = password;
        this.date = date;
        this.idUserType = idUserType;
        this.security = security;
        this.employee = employee;
    }


}

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
public class Payments  {
// to track the last payment
    private static int payrollExecutions =0;
    final int salary = 2600;
    private LocalDateTime lastPaymentDate;
    private double paymentAmount;
    public double getPaymentAmount() {
        DecimalFormat format = new DecimalFormat("#.##");
        String formatedNumber = format.format(paymentAmount);
        return Double.parseDouble(formatedNumber);
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Payments(LocalDateTime date) {
        this.lastPaymentDate= LocalDateTime.now();
    }
    public void displayPaymentMenu(WareHouse warehouse,Item item, Inventory inventory ) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean exitPaymentMenu = false;
        do {
            System.out.println("===============================================");
            System.out.println("                  SUPERMARKET                  ");
            System.out.println("===============================================");
            System.out.println("Please select an option from the menu below:   ");
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║                   USER MENU                  ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║ 1. Check last payment date and time.         ║");
            System.out.println("║ 2. Make a payment.                           ║");
            System.out.println("║ 3. Return to Main Menu.                      ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkLastPaymentDate();
                    break;
                case 2:
                    makePayment(warehouse.listEmployees);
                    break;
                case 3:
                    Main main = new Main();
                    main.displayMenu(warehouse,item, inventory);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
            System.out.println();
        } while (!exitPaymentMenu);
        scanner.close();
    }

    // Método para realizar un nuevo pago (solo permitido para los gerentes)
    public  void makePayment( List<Employee> listEmployees) {
        try
        {
            int payrollProcess = pendingPayroll();
            if (payrollProcess==0 && payrollExecutions==0)
                payrollProcess=1;
            System.out.println("===============================================");
            System.out.println("                  SUPERMARKET                  ");
            System.out.println("===============================================");
            System.out.println("Payroll in process...   ");
            System.out.println();
            int payroll = payrollProcess * salary;
            for (Employee employee:listEmployees)
                 {
                     System.out.println(employee.getName() + ", Salary: " + salary +  ", Payroll process pending: " + payrollProcess + ", Total payroll: " + payroll  );
                     // we update the paymentamount
                     this.paymentAmount = this.paymentAmount +  payroll;
                 }
            this.lastPaymentDate = LocalDateTime.now();
            payrollExecutions++;
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to execute payroll process");
        }
    }

    // Método para verificar la última fecha de pago
    public void checkLastPaymentDate() {
       if (payrollExecutions > 0) {
           SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
           String fechaFormateada = formato.format(lastPaymentDate);
           System.out.println("The last payment date was: " + lastPaymentDate);
        } else {
            System.out.println("No payments have been made yet.");
        }
    }
    private int pendingPayroll()
    {
        int pendingpayroll =0;
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            int hrLast= lastPaymentDate.getHour() * 60;
            int minLast = hrLast + lastPaymentDate.getMinute();
            int hrCurrent = currentTime.getHour() * 60;
            int minCurrent = hrCurrent + currentTime.getMinute();
            pendingpayroll = minCurrent - minLast;

        }
        catch (Exception ex)
        {
            pendingpayroll=0;
        }
        return pendingpayroll;
    }
}
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Employee {
    public IBuilder getIbuilder() {
        return ibuilder;
    }

    public void setIbuilder(IBuilder ibuilder) {
        this.ibuilder = ibuilder;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    IBuilder ibuilder;

    private String name;
    private String phoneNumber;
    private String email;
    private boolean canBuy = false;
    private boolean canSell = false;
    private boolean canPay = false ;
    private EmployeeType employeeType;
    private Scanner scanner = new Scanner(System.in);

    public Employee(){}
    public Employee(String name, String phoneNumber, String email, EmployeeType employeeType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employeeType = employeeType;

        // Establecer capacidades según el tipo de empleado
        if (employeeType == EmployeeType.MANAGER) {
            canBuy = true;
            canSell = true;
            canPay = true;
        } else if (employeeType == EmployeeType.CASHIER) {
            canSell = true;
        }
    }

    public void createItem(String sku,String name,double sellPrice, double buyPrice, int onStock, int soldItems, int purchasedItems, List<Item> listItems)
    {
        this.ibuilder.buildItemType();
        this.ibuilder.buildSKU(sku);
        this.ibuilder.buildName(name);
        this.ibuilder.buildSellPrice(sellPrice);
        this.ibuilder.buildBuyPrice(buyPrice);
        this.ibuilder.buildItemsOnStock(onStock);
        this.ibuilder.buildSoldItems(soldItems);
        this.ibuilder.buildPurchasedItems(purchasedItems);
        //We add the item to the list
        listItems.add(ibuilder.getItem());

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCanBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public boolean isCanSell() {
        return canSell;
    }

    public void setCanSell(boolean canSell) {
        this.canSell = canSell;
    }

    public boolean isCanPay() {
        return canPay;
    }

    public void setCanPay(boolean canPay) {
        this.canPay = canPay;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }


    public enum EmployeeType {
        MANAGER,
        CASHIER
    }


    public void displayMenuEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================");
        System.out.println("                  SUPERMARKET                  ");
        System.out.println("===============================================");
        System.out.println("Please select an option from the menu below:   ");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                   USER MENU                  ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Create a new Employee                     ║");
        System.out.println("║ 2. Display Employees            .            ║");
        System.out.println("║ 3. Return to the main menu.                  ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        int submenuEmployee = Integer.parseInt(scanner.nextLine());

        System.out.println();
        System.out.println();

        switch (submenuEmployee) {
            case 1:
                createNewEmployee();
                break;

            case 2:
                break;

            case 3:
                break;

            default:
                System.out.println("ERROR: Invalid option. Please choose a valid option from the menu.");
                break;
        }

        scanner.close(); // Close the Scanner when you're done using it

        System.out.println();
        System.out.println();
    }

    private void createNewEmployee() {
        // Implement logic for creating a new employee
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee name:");
        String name = scanner.nextLine();
        System.out.println("Enter employee phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter employee email:");
        String email = scanner.nextLine();

        System.out.println("Select employee type (1 for MANAGER, 2 for CASHIER):");
        int employeeTypeChoice = Integer.parseInt(scanner.nextLine());
            Employee.EmployeeType employeeType =
                    (employeeTypeChoice == 1) ? Employee.EmployeeType.MANAGER : Employee.EmployeeType.CASHIER;
        Employee newEmployee = new Employee(name, phoneNumber, email, employeeType);
        System.out.println("New employee created: " + newEmployee.getName());
    }


    public boolean canBuy() {
        return canBuy;
    }

    public boolean canSell() {
        return canSell;
    }

    public boolean canPay() {
        return canPay;
    }

}

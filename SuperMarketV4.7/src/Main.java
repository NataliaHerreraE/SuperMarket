import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        //Configure the admin user
        Employee admin = new Employee("admin","4384384384","team@lasallecollege.com", Employee.EmployeeType.MANAGER);
        // We create the Inventory object to handle the items list
        Inventory inventory = new Inventory();
        Item item = new Item();
        Payments payments = new Payments(LocalDateTime.now());
        WareHouse wareHouse = new WareHouse(inventory,item,admin,payments);
        wareHouse.prepareInventory(admin);
        Main main = new Main();
        // Menu for the action
        main.displayMenu(wareHouse,item,inventory);
        }

    //nuevo parametro inventory
        public void displayMenu(WareHouse wareHouse,Item item,Inventory inventory) {
            Scanner scanner = new Scanner(System.in);
            int submenuEmployee =0;
            boolean displaymenu=true;
            do {
                System.out.println("===============================================");
                System.out.println("                  SUPERMARKET                  ");
                System.out.println("===============================================");
                System.out.println("Please select an option from the menu below:   ");
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║                   USER MENU                  ║");
                System.out.println("╠══════════════════════════════════════════════╣");
                System.out.println("║ 1. Display all items inventory               ║");
                System.out.println("║ 2. Current time.                             ║");
                System.out.println("║ 3. Sell Inventory.                           ║");
                System.out.println("║ 4. Buy Inventory.                            ║");
                System.out.println("║ 5. Number of Items bought.                   ║");
                System.out.println("║ 6. Number of Items sold.                     ║");
                System.out.println("║ 7. Number of bought items by Item type.      ║");
                System.out.println("║ 8. Number of sold items by Item type.        ║");
                System.out.println("║ 9. Execute payroll.                          ║");
                System.out.println("║ 10.Cost all items purchased.                 ║");
                System.out.println("║ 11.Cost of amount of pay employees.          ║");
                System.out.println("║ 12.Total expenses (Item purchased + payroll).║");
                System.out.println("║ 13.Total sales (Total amount sold items).    ║");
                System.out.println("║ 14.The total profit.                         ║");
                System.out.println("║ 15.Exit                                      ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                System.out.println();
                System.out.println("Please select an option from the menu below:   ");
                System.out.println("...");

                System.out.println("Option: ");
                try {
                    submenuEmployee = scanner.nextInt();
                    System.out.println();
                    System.out.println();
                    Item.ItemType typeItem= null;
                    String sku="";
                    int quantity=0;
                    switch (submenuEmployee) {
                        case 1:
                            inventory.DisplayAllItems();
                            break;
                        case 2:
                            displayCurrentTime();
                            break;
                        case 3:
                            typeItem = askItemType(wareHouse.inventory);
                            sku= askItemToSell(wareHouse.inventory,typeItem,true);
                            quantity=askQuantity();
                            wareHouse.Sales(sku,quantity);
                            break;
                        case 4:
                            typeItem = askItemType(wareHouse.inventory);
                            sku=askItemToPurchase(wareHouse.inventory,typeItem,true);
                            quantity= askQuantity();
                            wareHouse.purchaseItem(sku,quantity);
                            break;
                        case 5:
                            System.out.println("Purchased Items "+item.getPurchasedItems());
                            break;
                        case 6:
                            System.out.println("Sold Items: "+item.getSoldItems());
                            break;
                        case 7:
                            System.out.println("  Purchased");
                            for (ItemByCategory itemCategory:wareHouse.PurchasedByCategory)
                            {
                                System.out.println(" Category: "+itemCategory.category + " Quantity: "+itemCategory.quantity);
                            }
                            break;
                        case 8:
                            System.out.println("  Sold");
                            for (ItemByCategory itemCategory:wareHouse.SoldByCategory)
                            {
                                System.out.println(" Category: "+itemCategory.category + " Quantity: "+itemCategory.quantity);
                            }
                            break;
                        case 9:
                            wareHouse.payments.displayPaymentMenu(wareHouse,item, inventory);
                            break;
                        case 10:
                            System.out.println("Cost all items purchased: "+wareHouse.getCostPurchasedItem()+"$");
                            break;
                        case 11:
                            System.out.println("Amount of payroll employees: " + wareHouse.payments.getPaymentAmount() +"$");
                            break;
                        case 12:
                            System.out.println("Total expenses " + wareHouse.TotalExpenses() +"$");
                            break;
                        case 13:
                            System.out.println("Total sales: "+wareHouse.getCostSalesItem()+"$");
                            break;
                        case 14:
                            System.out.println("Profit: "+wareHouse.Profit()+"$");
                            break;
                        case 15:
                            System.out.println("Exiting...");
                            displaymenu=false;
                            System.exit(0);
                            break;
                        default:
                            System.out.println("ERROR: Invalid option. Please choose a valid option from the menu.");
                            break;

                    }
                    System.out.println();
                    System.out.println("Press enter to continue");
                    scanner.nextInt();

                }
                catch (NumberFormatException e) {
                    System.out.println("ERROR: Please enter a valid number.");
                }

                System.out.println();
                System.out.println();

            }while (submenuEmployee != 15);
            // scanner.close(); // Close the Scanner when you're done using it


        }

    private void displayCurrentTime() {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Current Time: " + formattedTime);
    }


    public String askItemToSell(Inventory inventory, Item.ItemType itemtype, boolean sell){
        String sku;
        inventory.DisplayItemsbyType(itemtype, sell);
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce the name or sku that you want to sell");
        sku= scanner.nextLine();
        return sku;
    }

    public Item.ItemType askItemType(Inventory inventory){
        Item.ItemType  type;
        inventory.DisplayItemTypes();
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce the id desired item type:");
        String text= scanner.nextLine();
        return inventory.GetItemType(Integer.parseInt(text));
    }
    //inventory.DisplayItemTypes();
    //System.out.println("Select the ITem type to Buy:");


    public String askItemToPurchase(Inventory inventory, Item.ItemType itemtype, boolean sell){
        String sku;
        inventory.DisplayItemsbyType(itemtype, sell);
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce the name or sku that you want to buy");

        sku= scanner.nextLine();



        return sku;
    }

    public int askQuantity(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce the quantity");
        int quantity;
        quantity = Integer.parseInt(scanner.nextLine());

        return  quantity;
    }


}
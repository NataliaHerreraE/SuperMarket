import java.text.DecimalFormat;
import java.util.*;

public class WareHouse {
    List<Employee> listEmployees = new ArrayList<>();
    Item item;
    Payments payments;
    public double getCostSalesItem() {
        DecimalFormat format = new DecimalFormat("#.##");
        String formatedNumber = format.format(costSalesItem);
        return Double.parseDouble(formatedNumber);
    }
    public void setCostSalesItem(double costSalesItem) {
        this.costSalesItem += costSalesItem;
    }
    public double getCostPurchasedItem() {
        DecimalFormat format = new DecimalFormat("#.##");
        String formatedNumber = format.format(costPurchasedItem);
        return Double.parseDouble(formatedNumber);
    }
    public void setCostPurchasedItem(double costPurchasedItem) {
        this.costPurchasedItem += costPurchasedItem;
    }
    private  double costSalesItem,costPurchasedItem;
    Inventory inventory;
    public List<ItemByCategory> SoldByCategory = new ArrayList<>();
    public List<ItemByCategory> PurchasedByCategory = new ArrayList<>();
    private double budget=100000;
    //nuevo
    public double getBudget() {
        DecimalFormat format = new DecimalFormat("#.##");
        String formatedNumber = format.format(budget);
        return Double.parseDouble(formatedNumber);
    }
    //nuevo
    public void setBudget(double budget) {
       if (budget>0){
           this.budget = budget;
       }else{
           System.out.println("the budget can not be negative");
       }
    }
    public double TotalExpenses()
    {
        DecimalFormat format = new DecimalFormat("#.##");
        String formatedNumber = format.format(this.costPurchasedItem + payments.getPaymentAmount());
        return Double.parseDouble(formatedNumber);
    }
    public double Profit()
    {
        DecimalFormat format = new DecimalFormat("#.##");
        String formatedNumber = format.format(this.costSalesItem - TotalExpenses());
        return Double.parseDouble(formatedNumber);
    }

    public WareHouse(Inventory inventory,Item item,Employee employee, Payments payments) {
        this.inventory = inventory;
        this.item=item;
        this.listEmployees.add(employee);
        this.listEmployees.add(new Employee("Alma", "4387737595", "alma@gmail.com", Employee.EmployeeType.CASHIER));
        this.listEmployees.add(new Employee("Francisca", "4387737596", "francisca@gmail.com", Employee.EmployeeType.CASHIER));
        this.payments = payments;
    }
    //here we registered all the sales by their category
    public boolean checkMoney(double money) {
        if (money < getBudget()) {
            System.out.println("Sorry, you don't have enough money");
            return false;
        } else {
            return true;
        }
    }
    public void purchaseItem(String sku, int quantity) {
        double purchase=0;
        String category="";
        Boolean find=false;
        Boolean categoryFound=false;
        int lastQuantity=0;
        for (Item item:inventory.listItems
             ) {

          if (item.getSku().equals(sku) || item.getName().equals(sku) ){

              purchase= item.getBuyPrice()*quantity;
              find=true;
              category=item.getItemType().toString();
              item.setPurchasedItems(item.getPurchasedItems() + quantity); ;
              break;
          }
        }
        if (find){
            System.out.println("Current Budget "+getBudget());
            budget-=purchase;
            System.out.println("Final Budget "+getBudget());
            inventory.addStock(sku, quantity);
            setCostPurchasedItem(purchase);
            item.setPurchasedItems(quantity+ item.getPurchasedItems());
        }else{
            System.out.println("item not found");
        }

        //add the new quantity to the previous item purchased


        List<ItemByCategory> modifications = new ArrayList<>();

        for (ItemByCategory itemC : PurchasedByCategory) {
            if (itemC.category.equals(category)) {
                lastQuantity = itemC.getQuantity();
                itemC.setQuantity(lastQuantity + quantity);
                categoryFound = true;
                break; //
            }
        }

        if (!categoryFound) {

            PurchasedByCategory.add(new ItemByCategory(category, quantity));
        }
        PurchasedByCategory.addAll(modifications);
    }

    public void Sales(String sku, int quantity) {
        double sale = 0;
        int lastQuantity = 0;
        Boolean find = false;
        Boolean categoryFound = false;
        String category = "";
        for (Item item : inventory.listItems) {

            if (item.getSku().equals(sku) || item.getName().equals(sku)) {
                sale = item.getSellPrice() * quantity;
                find = true;
                category = item.getItemType().toString();
                item.setSoldItems(item.getSoldItems() + quantity) ;
                break;
            }
        }
        if (find) {
            System.out.println("Current Budget " + getBudget());
            budget += sale;
            System.out.println("Final Budget " + getBudget());
            inventory.removeStock(sku, quantity);
            setCostSalesItem(sale);
            item.setSoldItems(quantity + item.getSoldItems());
        } else {
            System.out.println("item not found");
        }
        //add the new quantity to the previous item sold
        List<ItemByCategory> modifications = new ArrayList<>();

        for (ItemByCategory itemC : SoldByCategory) {
            if (itemC.category.equals(category)) {
                lastQuantity = itemC.getQuantity();
                itemC.setQuantity(lastQuantity + quantity);
                categoryFound = true;
                break; //
            }
        }

        if (!categoryFound) {

            SoldByCategory.add(new ItemByCategory(category, quantity));
        }
        SoldByCategory.addAll(modifications);
    }
    public double getBuyPrice(String sku) {
        for (Item item : inventory.listItems) {
            if (item.getSku().equals(sku)) {
                return item.getBuyPrice();
            }
        }
        return 0;
    }
    public double getSellPrice(String sku) {
        for (Item item : inventory.listItems
        ) {
            if (item.getSku().equals(sku)) {
                return item.getSellPrice();
            }
        }
        return 0;
    }
    public void prepareInventory(Employee employee)
    {
        //Example calling the IBuilder with Chicken product
        Chicken chicken =new Chicken();
        employee.setIbuilder(chicken);
        employee.createItem("1","Chicken breasts per kilogram",14.61,11.68,100,0,100, inventory.listItems);
        chicken =new Chicken();
        employee.setIbuilder(chicken);
        employee.createItem("2","Chicken drumsticks per kilogram",6.65,5.32,100,0,100, inventory.listItems);
        chicken =new Chicken();
        employee.setIbuilder(chicken);
        employee.createItem("3","Chicken thigh per kilogram",12.47,9.97,100,0,100, inventory.listItems);
        chicken =new Chicken();
        employee.setIbuilder(chicken);employee.createItem("4","Whole chicken per kilogram",6.99,5.59,100,0,100, inventory.listItems);
        //Example calling the IBuilder with dairy product
        Dairy dairy =new Dairy();
        employee.setIbuilder(dairy);
        employee.createItem("5","Block cheese 500 grams",6.84,5.47,100,0,100, inventory.listItems);
        dairy =new Dairy();
        employee.setIbuilder(dairy);employee.createItem("6","Butter 454 grams",6.27,5.01,100,0,100, inventory.listItems);
        dairy =new Dairy();
        employee.setIbuilder(dairy);employee.createItem("7","Cream 1 litre",4.65,3.72,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("8","Margarine 907 grams",7.61,6.08,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("9","Milk 1 litre",3,2.4,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("10","Milk 2 litres",5.14,4.11,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("11","Milk 4 litres",6.39,5.11,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("12","Nut milk 1.89 litres",4.17,3.33,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("13","Soy milk 1.89 litres",4.31,3.44,100,0,100, inventory.listItems);
        dairy =new Dairy();employee.setIbuilder(dairy);employee.createItem("14","Yogurt 500 grams",3.33,2.66,100,0,100, inventory.listItems);

        //Example calling the IBuilder with fruit product
        Fruit fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("15","Apples per kilogram",6.04,4.83,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("16","Avocado unit",2.02,1.61,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("17","Bananas per kilogram",1.69,1.35,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("18","Cantaloupe unit",3.21,2.56,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("19","Grapes per kilogram",6.63,5.3,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("20","Lemons unit",0.9,0.72,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("21","Limes unit",0.76,0.6,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("22","Oranges 1.36 kilograms3 ",6.35,5.08,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("23","Oranges per kilogram",4.8,3.84,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("24","Pears per kilogram",5.58,4.46,100,0,100, inventory.listItems);
        fruit =new Fruit();employee.setIbuilder(fruit);employee.createItem("25","Strawberries 454 grams",3.72,2.97,100,0,100, inventory.listItems);

        //Example calling the IBuilder with Meat product
        Meat meat =new Meat();employee.setIbuilder(meat);employee.createItem("26","Beef rib cuts per kilogram",26.96,21.56,100,0,100, inventory.listItems);
        meat =new Meat();employee.setIbuilder(meat);employee.createItem("27","Beef stewing cuts per kilogram",19.48,15.58,100,0,100, inventory.listItems);
        meat =new Meat();employee.setIbuilder(meat);employee.createItem("28","Beef striploin cuts per kilogram",32.11,25.68,100,0,100, inventory.listItems);
        meat =new Meat();employee.setIbuilder(meat);employee.createItem("29","Beef top sirloin cuts per kilogram",21.71,17.36,100,0,100, inventory.listItems);
        meat =new Meat();employee.setIbuilder(meat);employee.createItem("30","Ground beef per kilogram",11.19,8.95,100,0,100, inventory.listItems);

        //Example calling the IBuilder with pork product
        Pork pork =new Pork();employee.setIbuilder(pork);employee.createItem("31","Bacon 500 grams",6.62,5.29,100,0,100, inventory.listItems);
        pork =new Pork();employee.setIbuilder(pork);employee.createItem("32","Pork loin cuts per kilogram",8.93,7.14,100,0,100, inventory.listItems);
        pork =new Pork();employee.setIbuilder(pork);employee.createItem("33","Pork rib cuts per kilogram",8.86,7.08,100,0,100, inventory.listItems);
        pork =new Pork();employee.setIbuilder(pork);employee.createItem("34","Pork shoulder cuts per kilogram",7.35,5.88,100,0,100, inventory.listItems);
        pork =new Pork();employee.setIbuilder(pork);employee.createItem("35","Wieners 400 grams",4.04,3.23,100,0,100, inventory.listItems);

        //Example calling the IBuilder with seafood product
        Seafood seafood =new Seafood();employee.setIbuilder(seafood);employee.createItem("36","Canned salmon 213 grams",5.1,4.08,100,0,100, inventory.listItems);
        seafood =new Seafood();employee.setIbuilder(seafood);employee.createItem("37","Canned tuna 170 grams",1.72,1.37,100,0,100, inventory.listItems);
        seafood =new Seafood();employee.setIbuilder(seafood);employee.createItem("38","Salmon per kilogram",26.07,20.85,100,0,100, inventory.listItems);
        seafood =new Seafood();employee.setIbuilder(seafood);employee.createItem("39","Shrimp 300 grams",7.89,6.31,100,0,100, inventory.listItems);

        //Example calling the IBuilder with vegetales product
        Vegetables vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("40","Broccoli unit",2.56,2.04,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("41","Cabbage per kilogram",2.9,2.32,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("42","Carrots 1.36 kilograms",4.24,3.39,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("43","Celery unit",3.7,2.96,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("44","Cucumber unit",1.45,1.16,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("45","Iceberg lettuce unit",2.48,1.98,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("46","Mushrooms 227 grams",2.46,1.96,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("47","Onions 1.36 kilograms",4.71,3.76,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("48","Onions per kilogram",5.42,4.33,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("49","Potatoes 4.54 kilograms",5.75,4.6,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("50","Potatoes per kilogram",5.29,4.23,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("51","Romaine lettuce unit",2.3,1.84,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("52","Sweet potatoes per kilogram",4.59,3.67,100,0,100, inventory.listItems);
        vegetables =new Vegetables();employee.setIbuilder(vegetables);employee.createItem("53","Tomatoes per kilogram",4.66,3.72,100,0,100, inventory.listItems);
    }

}



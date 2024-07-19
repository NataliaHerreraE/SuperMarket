import java.util.ArrayList;
import java.util.List;
public class Inventory {
    List<Item> listItems = new ArrayList<>();
    List<Item.ItemType> itemTypes = new Item().listOfItemTypes();
    public Inventory(){}
    public void DisplayItemTypes()
    {
        System.out.println("===============================================");
        System.out.println("                  SUPERMARKET                  ");
        System.out.println("===============================================");
        System.out.println("Please select an option from the menu below:   ");
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                   ITEM TYPES                 ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        for (Item.ItemType itemType: itemTypes) {
            System.out.println("╠ " + (itemTypes.indexOf(itemType) + 1) + ". "+ itemType.name());

        }
    }
    public Item.ItemType GetItemType(int id)
    {
        return itemTypes.get(id-1);
    }
    public void costOfAllItemsPurchased()
    {
        double costAllItems=0;
        for (Item item: listItems)
        {
            costAllItems+=item.getPurchasedItems();
        }
    }
    public void DisplayItemsbyType(Item.ItemType itemtype, boolean sell)
    {
        System.out.println("==============================================================================================");
        System.out.println("                  SUPERMARKET                                    SUPERMARKET                  ");
        System.out.println("==============================================================================================");
        for (Item item: listItems) {
            if (item.getItemType() == itemtype) {
                //System.out.println("Item type: " + item.getItemType() + ", SKU: " + item.getSku() + ", Name: " + item.getName() + ", Sell price: " + item.getSellPrice() + ", Buy price: " + item.getBuyPrice() + ", On Stock: " + item.getOnStock() + ", Sold Items: " + item.getSoldItems() + ", Purchased Items: " + item.getPurchasedItems());
                if (sell)
                    System.out.println("Item type: " + item.getItemType() + ", SKU: " + item.getSku() + ", Name: " + item.getName() +  ", Sell price: " + item.getSellPrice() + ", On Stock: " + item.getOnStock());
                else
                    System.out.println("Item type: " + item.getItemType() + ", SKU: " + item.getSku() + ", Name: " + item.getName() +  ", buy price: " + item.getBuyPrice());
            }
            //warehouse.createItem("Dairy","SKU","Name",10,20,30,40,50, inventory.listItems);
        }
    }
    public void DisplayAllItems()
    {
        System.out.println("=============================================================================================================================================");
        System.out.println("                  SUPERMARKET                                    SUPERMARKET                                    SUPERMARKET                  ");
        System.out.println("=============================================================================================================================================");
        for (Item item: listItems) {
                System.out.println("Item type: " + item.getItemType() + ", SKU: " + item.getSku() + ", Name: " + item.getName() + ", Sell price: " + item.getSellPrice() + ", Buy price: " + item.getBuyPrice() + ", On Stock: " + item.getOnStock() + ", Sold Items: " + item.getSoldItems() + ", Purchased Items: " + item.getPurchasedItems());
        }
    }
    public void addStock(String sku,int quantity){
        for (Item item:listItems
             ) {
            if (item.getSku().equals(sku)){
                int currentStock = item.getOnStock();
                System.out.println("Current "+currentStock);
                item.setOnStock(currentStock+quantity);
                System.out.println("Final Stock "+item.getOnStock());
                break;
            }
        }
    }
    public void removeStock(String sku,int quantity){
        for (Item item:listItems) {
            if (item.getSku().equals(sku)){
                int currentStock = item.getOnStock();
                System.out.println("Current "+currentStock);
                item.setOnStock(currentStock-quantity);
                System.out.println("Final Stock "+item.getOnStock());
            }
        }
    }
}
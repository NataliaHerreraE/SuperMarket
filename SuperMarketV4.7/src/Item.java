import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Item {

    // Item SKU for search Items in the list
    private String sku;

    //Item name
    private String name;

    //Sale price
    private double sellPrice;

    //Buy price
    private double buyPrice;

    //Qty Items on Stock
    private int onStock;
    //Qty sell Items
    private int soldItems;

    //Qty purchased Items
    private int purchasedItems;

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    //Item type to facilitate how to search the items
    private Item.ItemType itemType;
    public ItemType getItemType() {
        return itemType;
    }

    public void setTypeItem(ItemType itemType) {
        this.itemType = itemType;
    }




    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getOnStock() {
        return onStock;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
    }

    public int getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(int soldItems) {
        this.soldItems = soldItems;
    }

    public int getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(int purchasedItems) {
        this.purchasedItems = purchasedItems;
    }


    //Constructor
    public Item(){
    }

    public enum ItemType
    {
        Chicken,
        Dairy,
        Fruit,
        Meat,
        Pork,
        Seafood,
        Vegetables

    }

    public List<ItemType> listOfItemTypes()
    {
        return new ArrayList<>(Arrays.asList(ItemType.values()));

    }


}

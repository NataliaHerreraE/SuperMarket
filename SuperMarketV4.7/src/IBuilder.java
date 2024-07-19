public interface IBuilder {
    //Property Item Type
    void buildItemType();

    // Property Item SKU
    void buildSKU(String sku);

    //Property Item name
    void buildName(String name);

    //Property Sale price

    void buildSellPrice(double salePrice);

    // Property get Buy price
    void buildBuyPrice(double buyPrice);

    // Property Qty Items on Stock
    void buildItemsOnStock(int onStock);

    // Property Qty sell Items
    void buildSoldItems(int soldItems);

    //Property Qty purchased Items
    void buildPurchasedItems(int purchasedItems);

    Item getItem();
}

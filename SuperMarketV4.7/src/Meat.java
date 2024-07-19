public class Meat  implements IBuilder{
    Item item;

    public Meat()
    {
        this.item=new Item();
    }
    @Override
    public void buildItemType() {
        this.item.setItemType(Item.ItemType.Meat);
    }

    @Override
    public void buildSKU(String sku) {
        this.item.setSku(sku);
    }

    @Override
    public void buildName(String name) {
        this.item.setName(name);
    }

    @Override
    public void buildSellPrice(double salePrice) {
        this.item.setSellPrice(salePrice);
    }

    @Override
    public void buildBuyPrice(double buyPrice) {
        this.item.setBuyPrice(buyPrice);
    }

    @Override
    public void buildItemsOnStock(int onStock) {
        this.item.setOnStock(onStock)   ;
    }

    @Override
    public void buildSoldItems(int soldItems) {
        this.item.setSoldItems(soldItems);
    }

    @Override
    public void buildPurchasedItems(int purchasedItems) {
        this.item.setPurchasedItems(purchasedItems);
    }

    @Override
    public Item getItem() {
        return this.item;
    }
}


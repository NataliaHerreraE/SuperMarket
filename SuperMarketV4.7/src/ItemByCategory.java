public class ItemByCategory {
    String category;
    int quantity;


    public ItemByCategory(String category,int quantity){

        this.category=category;
        this.quantity=quantity;


    }

    public void setQuantity(int quantity){
        this.quantity=quantity;


    }

    public int getQuantity(){

        return quantity;
    }
}

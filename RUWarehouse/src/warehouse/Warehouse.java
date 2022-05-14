package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    private void setnull(){
        for(int i=0; i<sectors.length; i++){
            sectors[i].set(0, null);
        }
    }
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
        setnull();
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        Product prod = new Product(id, name, stock, day, demand);
        Sector cart = sectors[id%10];
        cart.add(prod);
        setnull();
    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        int latecart = sectors[id%10].getSize();
        sectors[id%10].swim(latecart);
        setnull();
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
        
       Sector thissec=sectors[id%10];
       if(thissec.getSize()==5){
       thissec.swap(1, thissec.getSize());
       thissec.deleteLast();
       thissec.sink(1);
        }
        setnull();
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        Sector thissec = sectors[id%10];
        int secsize = thissec.getSize();
        for(int i = 1; i<secsize+1; i++){
            if(thissec.get(i).getId()==id){
                thissec.get(i).updateStock(amount);
            }
        }
        fixHeap(id);
        setnull();
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        Sector thissec = sectors[id%10];
        int secsize = thissec.getSize();
        for(int i = 1; i<=secsize; i++){
            if(thissec.get(i)!=null && thissec.get(i).getId()==id){
                thissec.swap(i,secsize);
                thissec.deleteLast();
                thissec.sink(i);
            }
        }
        setnull();

    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        Sector thissec = sectors[id%10];
        int secsize = thissec.getSize();
        for(int i = 1; i<=secsize; i++){
            if(thissec.get(i).getId()==id&&amount<=thissec.get(i).getStock()){
                thissec.get(i).setLastPurchaseDay(day);
                thissec.get(i).setStock(thissec.get(i).getStock()-amount);
                thissec.get(i).setDemand(thissec.get(i).getDemand()+amount);
            }
        }
        thissec.sink(id%10);
        fixHeap(id);
        setnull();

    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        if(findbestsec(id)==id%10){
            evictIfNeeded(id);
            addProduct(id, name, stock, day, demand);
        }else{
            addProduct(findbestsec(id), name, stock, day, demand);
        }
        fixHeap(findbestsec(id));
        setnull();
    }

    private int findbestsec(int id){
        int j=0;
        for(int i=id%10; j<=10; i++){
            if(sectors[i].getSize()<5){
               
                return i;
            }
            if(i==9){
                i=-1;
            }
            j++;
            
        }
        return id%10;
    }
    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}

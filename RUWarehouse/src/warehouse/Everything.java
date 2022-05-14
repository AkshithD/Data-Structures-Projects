package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile("everything.in");
        StdOut.setFile("everything.out");
        Warehouse w = new Warehouse();
        int numoflines = StdIn.readInt();
        for(int i =0; i<numoflines; i++){
        String str = StdIn.readString();
        if(str.equals("add")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            w.addProduct(id, name, stock, day, demand);
        }else if(str.equals("purchase")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            int amount = StdIn.readInt();
            w.purchaseProduct(id, day, amount);
        }else if(str.equals("restock")){
            int id = StdIn.readInt();
            int amount = StdIn.readInt();
            w.restockProduct(id, amount);
        }else if(str.equals("delete")){
            w.deleteProduct(StdIn.readInt());
        }
    }
    StdOut.print(w);
        
    }
}

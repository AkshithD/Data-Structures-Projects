package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile("restock.in");
        StdOut.setFile("restock.out");
        Warehouse w = new Warehouse();
        int numofquiries = StdIn.readInt();
        for(int i =0; i<numofquiries; i++){
            if(StdIn.readString().equals("add")){
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int stock = StdIn.readInt();
                int demand = StdIn.readInt();
                w.addProduct(id, name, stock, day, demand);
            }else{
                int id = StdIn.readInt();
                int amount = StdIn.readInt();
                w.restockProduct(id, amount);
            }
        }
        StdOut.print(w);
    }
}

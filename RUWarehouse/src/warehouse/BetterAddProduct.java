package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile("betteraddproduct.in");
        StdOut.setFile("betteraddproduct.out");
        Warehouse w = new Warehouse();
        int numofsec= StdIn.readInt();
        for(int i=0; i<numofsec;i++){
        int day = StdIn.readInt();
        int id = StdIn.readInt();
        String name = StdIn.readString();
        int stock = StdIn.readInt();
        int demand = StdIn.readInt();
        w.betterAddProduct(id, name, stock, day, demand);
        }
       StdOut.print(w);
        
    }
}

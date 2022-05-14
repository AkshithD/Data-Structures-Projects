package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile("deleteproduct.in");
        StdOut.setFile("deleteproduct.out");
        Warehouse box = new Warehouse();
        int i = 0;
        int x = StdIn.readInt();
        while (i < x) {
            String whatToDo = StdIn.readString();
            if (whatToDo.equals("add")) {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                String name = StdIn.readString();
                int stock = StdIn.readInt();
                int demand = StdIn.readInt();
                box.addProduct(id, name, stock, day, demand);
            }
            else {
                box.deleteProduct(StdIn.readInt());
            }
                
            i++;
        }
        StdOut.println(box.toString());
    }
}

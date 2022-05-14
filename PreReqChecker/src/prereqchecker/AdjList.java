package prereqchecker;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        
	    StdIn.setFile(args[0]);
        int classes = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < classes ; i++){
            ArrayList<String> arraylist = new ArrayList<String>();
            arraylist.add(StdIn.readLine());
            adjlist.add(arraylist);
        }

        int changes = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < changes; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }

        StdOut.setFile(args[1]);
        for(int i = 0; i < adjlist.size(); i++){
            ArrayList<String> temp = adjlist.get(i);
            for(int j = 0; j < temp.size(); j++){
                StdOut.print(temp.get(j) + " ");
            }
            StdOut.println();
        }
        
    }

    private static int find(String course, ArrayList<ArrayList<String>> adjlist){
        for(int i = 0; i < adjlist.size(); i++){
            if(adjlist.get(i).get(0).equals(course)){
                return i;
            }
        }
        return -1;
    }
}
package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
        StdIn.setFile(args[0]);
        int classes = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < classes ; i++){
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(StdIn.readLine());
            adjlist.add(arrayList);
        }
        int n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }
        Set<String> set = new HashSet<>();
        StdIn.setFile(args[1]);
        n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            addpre(StdIn.readLine(), set, adjlist);
        }
        StdOut.setFile(args[2]);
        for(int i = 0; i < adjlist.size(); i++){
            if(!set.contains(adjlist.get(i).get(0))){
                int j = 1;
                boolean temp = true;
                while(j < adjlist.get(i).size()){
                    if(!set.contains(adjlist.get(i).get(j))){
                        temp = false;
                    }
                    j++;
                }
                if(temp){
                    StdOut.println(adjlist.get(i).get(0));
                }
            }
        }
	    
    }

    private static void addpre(String course, Set<String> classes, ArrayList<ArrayList<String>> adjlist){
          if(adjlist.get(find(course,adjlist)).size() == 1){
              if(!classes.contains(course)){
                classes.add(course);
              }
          }
          else{
            if(!classes.contains(course)){
                classes.add(course);
              }
              for(int i = 1;i < adjlist.get(find(course,adjlist)).size(); i++){
                addpre(adjlist.get(find(course,adjlist)).get(i), classes, adjlist);
              }
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
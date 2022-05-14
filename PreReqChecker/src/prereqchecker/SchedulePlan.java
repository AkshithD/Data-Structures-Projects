package prereqchecker;

import java.util.*;

public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	    StdIn.setFile(args[0]);
        int n = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < n ; i++){
            ArrayList<String> arraylist = new ArrayList<String>();
            arraylist.add(StdIn.readLine());
            adjlist.add(arraylist);
        }
        n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }

        StdIn.setFile(args[1]);
        String target  = StdIn.readLine();
        n = Integer.parseInt(StdIn.readLine());
        Set<String> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            addpre(StdIn.readLine(), set, adjlist);
        }
        Set<String> needToBeTaken = new HashSet<>();
        addpre(target, needToBeTaken, adjlist);

        ArrayList<ArrayList<String>> classes = new ArrayList<ArrayList<String>>();
        while(!eligible(set,adjlist).contains(target)){
            Boolean add = false;
            ArrayList<String> sem = new ArrayList<String>();
            for (String i : needToBeTaken) {
                for (String f : eligible(set,adjlist)) {
                    if(f.equals(i)){
                        sem.add(f);
                        add = true;
                    }
                }
            }
            if(add){
                classes.add(sem);
                for(int i = 0; i < sem.size(); i++){
                    set.add(sem.get(i));
                }
            }
        }

        StdOut.setFile(args[2]);
        StdOut.println(classes.size());
        for(int i  = 0; i < classes.size(); i++){
            for(int f = 0; f < classes.get(i).size(); f++){
                StdOut.print(classes.get(i).get(f) + " ");
            }
            StdOut.println();
        }
    }
    
    private static Set<String> eligible(Set<String> ClassesTaken, ArrayList<ArrayList<String>> adjlist){
        Set<String> Eligible = new HashSet<>();
        for(int i = 0; i < adjlist.size(); i++){
            if(!ClassesTaken.contains(adjlist.get(i).get(0))){
                int j = 1;
                boolean temp = true;
                while(j < adjlist.get(i).size()){
                    if(!ClassesTaken.contains(adjlist.get(i).get(j))){
                        temp = false;
                    }
                    j++;
                }
                if(temp){
                    Eligible.add(adjlist.get(i).get(0));
                }
            }
        }
        return Eligible;
    }

    private static void addpre(String course, Set<String> ClassesTaken, ArrayList<ArrayList<String>> adjlist){
        if(adjlist.get(find(course,adjlist)).size() == 1){
            if(!ClassesTaken.contains(course)){
              ClassesTaken.add(course);
            }
        }
        else{
          if(!ClassesTaken.contains(course)){
              ClassesTaken.add(course);
            }
            for(int i = 1;i < adjlist.get(find(course,adjlist)).size(); i++){
              addpre(adjlist.get(find(course,adjlist)).get(i), ClassesTaken, adjlist);
            }
        }
  }

  private static int find(String course, ArrayList<ArrayList<String>> adj){
      for(int i = 0; i < adj.size(); i++){
          if(adj.get(i).get(0).equals(course)){
              return i;
          }
      }
      return -1;
  }
}
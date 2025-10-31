import java.util.ArrayList;

import javax.swing.JLabel;

public class Route {// this class will be helpful for  for if the user wants many routs
    ArrayList<Station>path;
    ArrayList<String>lines;
    ArrayList <Integer> zonesUsed;
    double price;
    double totTime;
    int numChanges;

    public ArrayList<Station> getPath(){
        return path;
    }

    public void zonesUsedCal(){
        for(Station s:path){
            int zone = s.getZone();
            if(zonesUsed.contains(zone)==false){
                zonesUsed.add(zone);
            }
        }
    }

    public double getPrice(){
        zonesUsedCal();
        if(zonesUsed.size()==1){price = 1.4;}//only one
        if(zonesUsed.size()==4){price = 4.6;}//all 4
        if(zonesUsed.size() == 3){
            if(zonesUsed.contains(1)){price = 3.8;}
            else{price = 3.2;}
        }
        if(zonesUsed.size()==2){
            if(zonesUsed.contains(1)){price =2.8;}
            else{price = 2.4;}
        }
        return price;
    }

    public ArrayList<String> getLines(){
        return lines;
    }

    public Route(ArrayList<Station> paths,ArrayList<String>line, double totalTime,int changes){
        path=paths;
        lines = line;
        totTime =  totalTime;
        numChanges = changes;
        zonesUsed = new ArrayList<>();

    }
    public double getTime(){
        return totTime;
    }

    public int getChanges(){
        return numChanges;
    }

    // will be redundant when i get the visulisation of the orute to work but that not done yet at all 
    public void printRoute(String routeType) {// i will need to change this method when i want to try star task as want to make it like the underground tube out display
        System.out.println();
        if (path == null || path.size() == 0) {//either it borken or a station could be closed
            System.out.println("No route found.");
            return;
        }
        // new label for displaying all of the stations
            
    
       // if (lines == null || lines.size() == 0) {//Task 2 
        //    System.out.println("No line data available ");
        //    for (Station s : path) {
        //        System.out.println(s.getName());
         //   }
       //     System.out.println("\nOverall Journey Time (mins) = " + totTime);
       //     System.out.println("Number of Changes = " + numChanges);
       //     return;
       // }
    //
        if(routeType == "Fastest Route"){
        System.out.println("*** Quckest Time Route ***");}//will need to change this for when do least changes
        else{System.out.println("*** Least Changes Route ***");}
    
        System.out.println(path.get(0).getName() + " on " + lines.get(0) + " line");// print out the starting station and what line it is on
        String lastLine = lines.get(0);
    
        // Loop over lines 
        for (int i = 1; i < path.size(); i++) {
            String currentLine = lines.get(i - 1);  // shift index to match transitions
            if (!currentLine.equals(lastLine)) {//to show the change don't need to worry about add in time as that is done in the solve class
                System.out.println("***  Change Line to " + currentLine + " line ***");//make this structure look better
            }
            System.out.println(path.get(i).getName() + " on " + currentLine + " line");// says each station on the path
            //System.out.println(path.get(i).isOpen()); // this should be closed but keeps printing that it is open 
            lastLine = currentLine;
        }
    
        System.out.println("Overall Journey Time (mins) = " + totTime);// this sometimes doen't do the right thing FIX //fixed it there was a differnace between the index of the lines so didnt add on the last station
        System.out.println("Number of Changes = " + numChanges);
    }
    
    
    
    
    
    
    
    
    
    

}

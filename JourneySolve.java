import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
//this
public class JourneySolve {//probable rename this c
    MetroNetwork metro;
    ArrayList<Station> allStations;
   

    public  JourneySolve(MetroNetwork n){
        metro = n;// pass in the network of the trains will need to also add walking when the time comes 
        allStations = metro.getStations();
    }

    public Route djikstrasFastest(String startStationName, String endStationName){
        // should give all the stations with all of their connections sorted
        Station start = metro.nameToStation(startStationName);
        Station end = metro.nameToStation(endStationName);// these will call method in metro that will retirn Station with matching string name
        
        //start algarithum - task for next session find out how to make a value infinate 
        // will make array of everything and hae al of their indexs match up for i can itterate
        double[] time = new double[allStations.size()];// can be station as not adding anymore stations as reading is done in metor class
        boolean[] visited = new boolean[allStations.size()];
        Station[] previous = new Station[allStations.size()];
        String[] lines = new String[allStations.size()];

        //make all of the nodes have distance of infinaty make visited = flase ect
        for(int i =0; i < allStations.size();i++){
            time[i] = Double.POSITIVE_INFINITY;
            visited[i] = false;
            previous[i] = null;//as nothing has happend yet
            lines[i]=null;
        }
       int startIndex = allStations.indexOf(start);
       time[startIndex]=0;

        // start the algarithum now that set up is done start the algarithum
        boolean done = false; 
        while(done==false){
            double fastestTime = Double.POSITIVE_INFINITY;
            int index = -1;

            for(int i =0; i<allStations.size();i++){
                if(!visited[i]&& time[i]<fastestTime)// will update as i find out the fastst time every time it itterates (while)
                {// so not visited and it is a faster time then go to that statoin
                fastestTime = time[i];
                index = i;
                }
            }

                if(index == -1){done = true;break;}// means that you have the shortest time

                Station current= allStations.get(index); // this will get the station which is closest to the start station
                visited[index] = true;// will go through all of the conections 
                if(current == end){done = true;break;}// you have found the hsortest path to the station

                // now need to go through the connections in current and work out the times
                // rember me you need to account for 2 min if you change lines as that is important
                ArrayList<Connection> connections = current.getConnections();// will give all the conections from current 
                for(Connection c: connections){
                    Station conectionStation = c.getStationTo();// where the connection is going to
                    if(conectionStation.isOpen() == false){continue;}// STAR TASK COME BACK TO
                    int toStationIndex =  allStations.indexOf(conectionStation); // will get the station of the startion oyu are going to 
                    if(visited[toStationIndex]){continue;}// means that it is a station that has already been searched and had all connectoins searched basically has done the reverse operatin of what trying to do 
                    double conectionTime = c.getTime();
                    String conectionLine =  c.getLine();
                    
                    String curLine =  lines[index];
                if(curLine !=null && !curLine.equals(conectionLine)){//checks that th line that you are on matches the line you are going to take to this new station
                   if(!curLine.equals("Walk")){conectionTime+=2;}// dont add the delay if they are walking 
                     // account for the change 
                }
                double curTime  =time[index]+ conectionTime; // will get the time from the root to this nod at this moment of the journy

                // then need to check that if this is a faster way to get to the station change the time
                if(curTime < time[toStationIndex] ){
                    time[toStationIndex]= curTime;
                    previous[toStationIndex] = current;
                    lines[toStationIndex] = conectionLine;
                    visited[toStationIndex] = false; // added as i think it isnt looking at mutliple routes throuhj a stations force it 
                }
            }
    }
    //system.out.println("not found route");
    return buildRoute (allStations, previous, lines, time,start,end);// could possibly make this its own class but see if works first
    // havent made build route yet
    
}


// Computes a route between two station names that tries to minimize the number of line changes.
// First tries a tram-only route. If that fails, allows walking as a last resort.
public Route djikstrasLeastChange(String startStationName, String endStationName) {
    // Convert station names to actual Station objects
    Station start = metro.nameToStation(startStationName);
    Station end = metro.nameToStation(endStationName);

    // First attempt: find a route without walking (trams only)
    Route route = leastChangePath(start, end, false);

    // If a tram-only route exists, return it
    if (route != null) {
        return route;
    }

    // If tram-only fails, allow walking between stations (fallback strategy)
    return leastChangePath(start, end, true);
}





private Route leastChangePath(Station start, Station end, boolean allowWalking) {
   

    
    ArrayList<Step> steps = new ArrayList<>();//have all the steps to get to the solution
    
    ArrayList<String> visited = new ArrayList<>();//track stations viited

    
    steps.add(new Step(start, null, 0, 0.0, null));// start station and start og the route so that id why there is null and 0
    Step finalStep = null;

    //keep going until we find the route
    while (!steps.isEmpty()) {
        // Manually find the best step with the least changes 
        int bestDex = 0;
        for (int i = 1; i < steps.size(); i++) {
            Step curStep = steps.get(i);
            Step bestStep = steps.get(bestDex);
            if (curStep.numChanges < bestStep.numChanges ||(curStep.numChanges == bestStep.numChanges && curStep.totalTime < bestStep.totalTime)) {
                bestDex = i;//a route with less changes has been found or same chanegs but qucker time
            }
        }

        // Remove and process the best step
        Step currentStep = steps.remove(bestDex);
        String StationLine = currentStep.station.getName() + "|" + currentStep.lineUsed;// need as you can have multiple lines go through each station

        // Skip if we already visited this station with the same line
        if (visited.contains(StationLine)) continue;
        visited.add(StationLine);//add if it hasnt visited so wont ocme back

        // Check if we've reached the end station
        if (currentStep.station == end) {// idk if i may have to change this cuz it could stop before find right route
            finalStep = currentStep;
            break;
        }

        
        for (Connection c : currentStep.station.getConnections()) {//go through all of the connections in a station
            // If walking is not allowed, skip "Walk" connections
            if (allowWalking==false && c.getLine().equals("Walk")) continue;//skip if not ment to be a non walk route ( first attempt)

            Station nextStation = c.getStationTo();
            
            if (nextStation.isOpen()== false) continue;//skip closed stations

            String nextLine = c.getLine();
            double curTime = currentStep.totalTime + c.getTime();
            int changes = currentStep.numChanges;

            // Check if this connection changes a line 
            if (currentStep.lineUsed != null && !currentStep.lineUsed.equals(nextLine)) {//see if the line changes aka new lie is different form the current line
                if (!currentStep.lineUsed.equals("Walk") && !nextLine.equals("Walk")) {
                    curTime += 2; // Add time penalty for changing lines
                }
                changes++;
            }

            
            steps.add(new Step(nextStation, nextLine, changes, curTime, currentStep));// the just worked out step
        }
    }

   
    if (finalStep == null) return null;// can't find a path could be if you need to use walking and it is the non walking route

    // Trace back the path from the destination to the start
    ArrayList<Station> routeStations = new ArrayList<>();
    ArrayList<String> linesOnRoute = new ArrayList<>();
    Step trace = finalStep;
    String previousLine = null;
    int totalChanges = 0;

    // need to go in reverse order to from end to start and add the stations used and lines used to the array lists to then put into the route method
    while (trace != null) {
        routeStations.add(0, trace.station); // Add station to start of list
        if (trace.lineUsed != null) linesOnRoute.add(0, trace.lineUsed);

        // Count line changes
        if (previousLine != null && trace.lineUsed != null && !trace.lineUsed.equals(previousLine)) {
            totalChanges++;
        }
        if (trace.lineUsed != null) previousLine = trace.lineUsed;

        trace = trace.previousStep;
    }

    // Return the complete route with stations, lines, total time, and changes
    return new Route(routeStations, linesOnRoute, finalStep.totalTime, totalChanges);
}









public Route buildRoute(ArrayList<Station> allStations,Station[] previous,String[] linesused,double[] time,Station start,Station end){
    ArrayList<Station> path = new ArrayList<>();
    ArrayList<String> lines = new ArrayList<>();
    int finishIndex = allStations.indexOf(end);
    double totTime = time[finishIndex];// idk if there is a better way cuz i work out the fastest path from the start node to any other given node but only need one but if it work don't touch it
    int changes = 0;// i need to go through and count how mnay times the line changes
    Station current = end;
    String lastLine = null;// so u know when to stop
 
 
    while(current!=null){
        path.add(0,current);
        int curIndex =  allStations.indexOf(current);
        String line = linesused[curIndex];
 
 
        if(line != null){
            lines.add(0,line);
            if(lastLine!= null && !lastLine.equals(line)){// means the next line is differnt and it it not the end of journy
                changes++;
            }
            lastLine = line;
        }
        current = previous[curIndex];
    }
    return new Route(path,lines,totTime,changes);
 } 
}

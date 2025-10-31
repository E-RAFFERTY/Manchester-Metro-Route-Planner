import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


public class MetroNetwork {
    ArrayList<Station> stationsList;// to keep track of what stations exist this will be helpful checking if a station is real
    ArrayList<String> linesList;

    
    // maybe get the date and time here and cloud close the stations if the current time is past closing data     
    public ArrayList<Connection> getConnection (Station station){// returns all possible connections from a given stations
        return station.getConnections();
    }
    public ArrayList<Station> getStations(){// has a list of every named station returns it
        return stationsList;
    }

    public Boolean doesStationExist(String station){
        for(Station i:stationsList){
            if(i.getName().equals(station)){// you can't compare as == has to be like this
                return true;
            }

        }
        return false;

    }
    public  MetroNetwork(){
        stationsList = new ArrayList<Station>();
        linesList = new ArrayList<String>();
        boolean firstLine = true;// so i can skip the collum names in document
        String metroFile = "Metrolink_times_linecolour.csv";
        String Walktimes = "walktimes.csv"; // will nees this for the futue when do extra task 
        String zones = "zones.csv"; 

        List<List<String>> metroLinesData = new ArrayList<>();// makes a 2D array of all the lines then of the connnection 
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(metroFile))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                if(firstLine ==true){firstLine = false; continue;}// skip collums
                // Split the line by comma and convert to a List
                String[] values = line.split(",");
                List<String> lineData = Arrays.asList(values);
                metroLinesData.add(lineData);// adds to the 2D array

            }// now everything has been added to 

                // Add the line data to Station
            
            for (int i = 0; i < metroLinesData.size(); i++) {// from to line time is the correct order
                List<String> row = metroLinesData.get(i);
                String StationFrom = row.get(0).trim();// trim removes white spaces
                String StationTo = row.get(1).trim();
                String lineCol = row.get(2);// you cant do row[index] for an array list
                double time = Double.valueOf(row.get(3));
                Station from;
                Station to;
                if(doesLineExist(lineCol) ==false){
                    linesList.add(lineCol);
                }
                if(doesStationExist(StationFrom)== false){// if this is the first time the station is mentioned
                    from = new Station(StationFrom);
                    stationsList.add(from);//ik this will make a copy but the purpose of this is soly for the name nothing more
                }// will be usful as it will give a list of all of the sattions and there names 
                else{
                    from = nameToStation(StationFrom);
                }
                if(doesStationExist(StationTo)== false){
                    to = new Station(StationTo);
                    stationsList.add(to);
                    
                }
                else{
                    to = nameToStation(StationTo);
                }
                Connection forward =  new Connection(from,to,lineCol,time);
                Connection backward =  new Connection(to,from,lineCol,time);// to gte it both ways as same weight in opposit direaction and this is an undirected tree
                from.addConection(forward);
                to.addConection(backward);
                updateStaionsList(from);// will replace so allways the most upto date station conections are their means i don't have duplicates
                updateStaionsList(to);


                //System.out.println(time);// just to test that this prints --- it does 
                
            }

        }
    
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());// this will prevent the application from crashing
        }
        //now reading in the walking times

        // assumption made that the walking tistationmes matrix will always be given in alphebetical order
        firstLine =true;
        String[] alphabeticalStations = new String[stationsList.size()];// put the stations into alphabetical order as that is how they are in the matrix 
        int count =0;
        for(Station s:stationsList){
            alphabeticalStations[count] = s.getName();
            count++;
        }
        Arrays.sort(alphabeticalStations); 
        try (BufferedReader br = new BufferedReader(new FileReader(Walktimes))) {
            String line= br.readLine();
            line= br.readLine();//should be the 2nd line
           // while ((line = br.readLine()) != null) {
                
                //if(firstLine ==true){firstLine = false; continue;}// skip collums
                // Split the line by comma and convert to a List
                String[] values = line.split(",");
                List<String> walkingData = Arrays.asList(values);// will give all the times it will take to get from a station to the rest of the stations

                String stationStr = walkingData.get(0).trim();// will give you the name of the station
                Station station  = nameToStation(stationStr);// to add the connection 
                for(int i = 1;i<walkingData.size();i++){// to go through the rest of the times on the same line
                    //the index of the station walking to should be offset by +1 due to the station title in spread sheet
                   
                    double walkTime = Double.valueOf(walkingData.get(i).trim());// get the station in the collum(left)
                    Station stationWalkTo = nameToStation(alphabeticalStations[i-1]);
                    //System.out.println(stationStr+" to "+stationWalkTo.getName());// this works now
                    //System.out.println(walkTime);
                    //System.out.println();
                    Connection walk = new Connection(station, stationWalkTo, "Walk", walkTime);// wil have to hange the walk time as to not add 2 mins
                    station.addConection(walk);
                    //issue i am updatung the copy
                    updateStaionsList(station);
                    
                    
                //}

               

            }// now everything has been added to 
            


        }
        catch(IOException e){
            System.err.println("Error " + e.getMessage());// this will prevent the application from crashing
        }
        // add ing the zones 
        try (BufferedReader br = new BufferedReader(new FileReader(zones))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(firstLine ==true){firstLine = false; continue;}// skip collums
                // Split the line by comma and convert to a List
                String[] values = line.split(",");
                List<String> lineData = Arrays.asList(values);
                Station station  =  nameToStation(lineData.get(0));
                int zone  =  Integer.parseInt(lineData.get(1));
                station.setZone(zone);
                updateStaionsList(station);// should set the zone in the station and update it in list of stations in the array
                

            }
        }

        catch (IOException e) {
            System.err.println("Error " + e.getMessage());// this will prevent the application from crashing
        }

        
    }

    public Boolean doesLineExist(String line){
        for(String s:linesList){
            if(s.equals(line)){return true;}
        }
        return false;
    }
    public ArrayList<String> getLines(){
        return linesList;
    }

    public Station nameToStation(String name){
        for (Station s:stationsList ){
            if(s.getName().equals(name)){return s;}
        }
        Station p = new Station("error");
        return p;// this wont happen though
    }
   
    public void updateStaionsList(Station s){// explaintion above 
        int count =0;
        for(Station i:stationsList){
            if(i.getName() == s.getName()){
                stationsList.set(count,s);
                
                return;
            }
            count++;
        }
    }
    public void closeStation(String name){
        Station station = nameToStation(name);
        
        station.changeStatus(false);
        //System.out.println("here wow "+station.isOpen());
        updateStaionsList(station);
        
        //System.out.println("here wow 5 "+nameToStation(name).isOpen());
    
    }
    public void openStation(String name){
        Station station = nameToStation(name);
        station.changeStatus(true);
    
    }
    public Boolean addDelay(String from, String to, double delay, String line){// almoast forgot to specify 
        Station stationFrom = nameToStation(from);
        Boolean valid = false;
        //Station stationTo = nameToStation(from);
        ArrayList<Connection> conections = stationFrom.getConnections();
        for(Connection c:conections){
            Station n = c.getStationTo();
            if(n.getName().equals(to) && c.getLine().equals(line)){// mean that this is the right connection to add the delay to 
                c.addDelay(delay);
                valid = true;
               // System.out.println("yoooo peeps");
                //stationFrom.updateConnections(conections);
            }
        }

        for (Station s:stationsList ){
            if(s.getName().equals(from)){ 
                s = stationFrom;
            }
        }
        //System.out.println(valid);
        return valid; // for message box
        
        


    }
   
   
}

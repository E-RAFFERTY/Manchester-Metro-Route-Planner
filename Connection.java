public class Connection {
    Station stationTo;
    Station stationFrom;
    double time;
    String line;

    public Connection ( Station from, Station to, String metroLine,double timeTaken){//initilizes and sets all of the values to the parameters
        stationFrom = from;
        stationTo  =to;
        time = timeTaken;
        line = metroLine;
    }
   
    // methods to return private attributes
    public Station getStationFrom(){
        return stationFrom;
    } 
    public Station getStationTo(){
        return stationTo;
    } 
    public String getLine(){
        return line;
    }
    public double getTime(){
        return time;
    }   
    public void addDelay(double delay){//for if their is a delay of the trains
        time +=delay;
    } 
}

import java.util.ArrayList; 
public class Station {

    String name;
    ArrayList<Connection> conections;// dont use normay arry size dynamic
    Boolean open;
    int zone;

    public Station(String stationName){
        name = stationName;
        open = true;
        conections = new ArrayList<Connection>();

    }
    public boolean isOpen(){
        return open;
    }

    public String getName(){//return station name as string
        return name;
    }

    public ArrayList<Connection> getConnections(){
        return conections;
    }

    public void addConection(Connection newConection){//add to the list
        conections.add(newConection);
    }

    public void changeStatus(boolean status){// to account for open and closed stations    
        open = status;
    }

    public void updateConnections(ArrayList<Connection> conection){
        conections = conection;
    }
    //write a method to overide the connections when there has been a delay

    public int getZone(){
        return zone;
    }

    public void setZone(int num){
        zone = num;
    }
    
    
}

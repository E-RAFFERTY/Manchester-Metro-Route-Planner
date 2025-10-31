import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graphy extends JComponent {

    Route route;
    int x;
    int y;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        x = 900;
        y = 100;
        ArrayList<Station> paths =  route.getPath();
        ArrayList<String>lines  = route.getLines();
        
       
            g.setColor(getColour(lines.get(0)));
            g.drawLine(x, y, x, y + 100);
            g.setColor(Color.BLACK);
            g.drawString(paths.get(0).getName(), x+20, y-10);
            y += 100;
            String curPath = lines.get(0);// keep track of changes
            
            //if(curPath.equals("Walk")){pathWayDraw+="|\n| "+curPath+"\n| \n";}
            //else{pathWayDraw+="|\n| "+curPath+" Line\n| \n";}
            super.paintComponent(g);

            for(int i = 0;i<lines.size();i++){
                if(!curPath.equals(lines.get(i))){
                    curPath = lines.get(i);
                    //pathWayDraw+="\n";
                    //pathWayDraw+=(paths.get(i)).getName();
                    g.setColor(getColour(lines.get(i)));
                    g.drawLine(x, y, x, y + 100);
                    g.setColor(Color.BLACK);
                    g.drawString(paths.get(i).getName(), x+20, y-10);
                    y += 100;
                    
                }
               
            }
            g.setColor(Color.BLACK);
            g.drawString(paths.get(paths.size()-1).getName(), x+20, y-10);
           
           
        
    }

    public void setRoute(Route solution) {
        route = solution;
        repaint(); // Safe place to trigger a repaint
    }

    public Color getColour(String col) {
        switch (col.toLowerCase()) {
            case "yellow":
                return Color.YELLOW;
            case "purple":
                return new Color(153, 51, 255);
            case "green":
                return Color.GREEN;
            case "lightblue":
                return new Color(102, 178, 255);
            case "pink":
                return Color.PINK;
            case "darkblue":
                return Color.BLUE;
            case "red":
                return Color.RED;
            default:
                return Color.LIGHT_GRAY; // Default for "walking"
        }
    }
}

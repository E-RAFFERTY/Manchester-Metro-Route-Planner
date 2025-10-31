import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Driver {
    static Image img;// didnt like it with out the static efore VS code did this
    static MetroNetwork n;
        public static void main(String[] args) throws IOException {//error with out the throw exception

             n = new MetroNetwork();
            ArrayList<Station> stationsArray = n.getStations();
           
            //n.closeStation("Village"); THIS DOES WORK
            //n.addDelay("Eccles", "Ladywell", 20, "lightblue");
            
    
            JFrame window = new JFrame("Manchester Metro");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            //window.setSize(684,600);
            window.setSize(780,600);
            JPanel panel = new JPanel();
            panel.setLayout(null);//does it so it doesnt auto set it to layout
            //panel.setBackground(Color.LIGHT_GRAY);
            //window.add(panel);

            //trying to add the icon 
            ImageIcon img = new ImageIcon(Driver.class.getResource("icon.jpg"));  
            window.setIconImage(img.getImage());
            //Graph nk = new Graph();
            
            
           
            
            

            // ADDING A LABLE TO THE SCREEN
            JLabel StartMessageLable = new JLabel("Select Starting Station");
            StartMessageLable.setForeground(Color.DARK_GRAY);
            StartMessageLable.setFont(new Font("Arial",Font.BOLD,16));
            StartMessageLable.setBounds(220,90,300,100);
            panel.add(StartMessageLable);
            //new label
            JLabel endMessageLable = new JLabel("Select End Station");
            endMessageLable.setForeground(Color.DARK_GRAY);
            endMessageLable.setFont(new Font("Arial",Font.BOLD,16));
            endMessageLable.setBounds(220,170,300,100);
            panel.add(endMessageLable);
            //new lable
            JLabel SyleMessageLable = new JLabel("Select Style");
            SyleMessageLable.setForeground(Color.DARK_GRAY);
            SyleMessageLable.setFont(new Font("Arial",Font.BOLD,16));
            SyleMessageLable.setBounds(220,250,300,100);
            panel.add(SyleMessageLable);
            // new lable
            JLabel timeMessageLable = new JLabel("Total Time:");
            timeMessageLable.setForeground(Color.DARK_GRAY);
            timeMessageLable.setFont(new Font("Arial",Font.BOLD,16));
            timeMessageLable.setBounds(220,500,300,100);
            panel.add(timeMessageLable);
            //new lable
            JLabel changesMessageLable = new JLabel("Total Changes:");
            changesMessageLable.setForeground(Color.DARK_GRAY);
            changesMessageLable.setFont(new Font("Arial",Font.BOLD,16));
            changesMessageLable.setBounds(420,500,300,100);
            panel.add(changesMessageLable);
            //new lable trial of printing out the route
            JLabel routeMessageLable = new JLabel("Route");
            routeMessageLable.setForeground(Color.DARK_GRAY);
            routeMessageLable.setFont(new Font("Arial",Font.BOLD,16));
            routeMessageLable.setBounds(40,-80,500,500);
            //panel.add(routeMessageLable);

            JTextArea areaBoxRoute = new JTextArea("Route:");
            areaBoxRoute.setForeground(Color.DARK_GRAY);
            areaBoxRoute.setFont(new Font("Arial",Font.BOLD,9));
            areaBoxRoute.setBounds(30,140,160,300);
            areaBoxRoute.setForeground(Color.DARK_GRAY);
            panel.add(areaBoxRoute);
            

            // delay lable 
            JLabel AddDelay = new JLabel("Add Delay");
            AddDelay.setForeground(Color.DARK_GRAY);
            AddDelay.setFont(new Font("Arial",Font.BOLD,16));
            AddDelay.setBounds(220,330,300,100);
            panel.add(AddDelay);
            // delay combo
            JLabel closeStation = new JLabel("Close/Open Station");
            closeStation.setForeground(Color.DARK_GRAY);
            closeStation.setFont(new Font("Arial",Font.BOLD,16));
            closeStation.setBounds(220,410,300,100);
            
            panel.add(closeStation);
            // add text box to panel

            JTextField delayBox = new JTextField(3);
            delayBox.setForeground(Color.DARK_GRAY);
            delayBox.setFont(new Font("Arial",Font.BOLD,16));
            delayBox.setBounds(640,390,65,41);
            panel.add(delayBox);

            // add Delay button 
            JButton delayButton = new JButton("Add");
            delayButton.setBounds(715,390,61,40);
            panel.add(delayButton);
            // drawing 

            //reset button
            JButton resetButton  = new JButton("reset");
            resetButton.setBounds(670,470,80,40);
            panel.add(resetButton);
           
            

            
            
            


            //ADDING AN IMAGE TO THE SCREEN
            ImageIcon myPicture = new ImageIcon("metroBan5.png");// ive added a black border round the logo as i went crazy trying to scale the image may fix later
            JLabel pictureLable = new JLabel(myPicture);
            //pictureLable.setBounds(0,0,myPicture.getIconWidth(),myPicture.getIconHeight());
            pictureLable.setBounds(0,-60,780,230);// places at the top like a banner found through trial and error
            panel.add(pictureLable);

            ImageIcon myPictureBee = new ImageIcon("bee.png");// ive added a black border round the logo as i went crazy trying to scale the image may fix later
            JLabel pictureLableBee = new JLabel(myPictureBee);
            //pictureLable.setBounds(0,0,myPicture.getIconWidth(),myPicture.getIconHeight());
            pictureLableBee.setBounds(450,130,250,250);// places at the top like a banner found through trial and error
            panel.add(pictureLableBee);



            //ADDING BUTTON TO SCREEN
            JButton startButton = new JButton("calculate");
            startButton.setBounds(40,480,150,40);
            panel.add(startButton);

            JButton openStationButton = new JButton("Open");
            openStationButton.setBounds(380,470,80,40);
            openStationButton.setBackground(new Color(191, 245, 201));
            panel.add(openStationButton);
            
            JButton closeStationButton = new JButton("Close");
            closeStationButton.setBounds(470,470,80,40);
            closeStationButton.setBackground(new Color(237, 176, 173));
            panel.add(closeStationButton);
           
            //ADDING A COMBO BOX (DROP DOWN FOR ALL OF THE STATIONS)
            String[] options = new String[stationsArray.size()];
            int count =0;
            for(Station s:stationsArray){
                options[count] = s.getName();
                count++;
            }
            Arrays.sort(options); // was a pain searching unsorted stations list 
            JComboBox<String> comboBoxStart = new JComboBox<>(options);
            comboBoxStart.setBounds(220,150,150,40);
            panel.add(comboBoxStart);

            JComboBox<String> comboBoxEnd = new JComboBox<>(options);
            comboBoxEnd.setBounds(220,230,150,40);
            panel.add(comboBoxEnd);

            JComboBox<String> comboBoxDelayStart = new JComboBox<>(options);
            comboBoxDelayStart.setBounds(220,390,150,40);
            panel.add(comboBoxDelayStart);

            JComboBox<String> comboBoxDelayEnd = new JComboBox<>(options);
            comboBoxDelayEnd.setBounds(380,390,150,40);
            panel.add(comboBoxDelayEnd);

            JComboBox<String> comboBoxClose = new JComboBox<>(options);
            comboBoxClose.setBounds(220,470,150,40);
            panel.add(comboBoxClose);

            String[] styleRoute =  new String[]{"Fastest Route","Least Changes"};
            JComboBox<String> comboBoxSyle = new JComboBox<>(styleRoute);
            comboBoxSyle.setBounds(220,310,150,40);
            panel.add(comboBoxSyle);

             // label for price
             JLabel priceLable = new JLabel("Price: £");
             priceLable.setForeground(Color.DARK_GRAY);
             priceLable.setFont(new Font("Arial",Font.BOLD,16));
             priceLable.setBounds(600,500,300,100);
             panel.add(priceLable);

            ArrayList<String> linesList  =n.getLines();// could try using rhe 2D array u alread made
            String[] linList = new String[linesList.size()];// feel like there must be an easier way 
            for(int i=0; i<linesList.size();i++){
                linList[i] = linesList.get(i);
            }
            
            JComboBox<String> comboBoxline = new JComboBox<>(linList);
            comboBoxline.setBounds(540,390,90,40);
            panel.add(comboBoxline);
            /// button action listiners here
            /// open station
            openStationButton.addActionListener(e ->{
                String station = (String)comboBoxClose.getSelectedItem();// changed this from getting info from the button to the combo box 
                n.openStation(station);
            });
            //close station
            closeStationButton.addActionListener(e ->{
                String station = (String)comboBoxClose.getSelectedItem();// this finally works 
                System.out.println(station);
                //n.updateStaionsList(n.nameToStation(station));
                
                n.closeStation(station);// this is not working
                //Station s = n.nameToStation(station);
                //System.out.println("here too "+s.isOpen());//this does not give the right answer
            });
            // add a delay
            
            resetButton.addActionListener(e ->{
                n = new MetroNetwork();
            });
            delayButton.addActionListener(e ->{
            
                // read in all the information from the GUI
            String str = delayBox.getText();
            String delayStationStart = (String)comboBoxDelayStart.getSelectedItem();
            String delayStationEnd = (String)comboBoxDelayEnd.getSelectedItem();
            double timeDelayed = Double.parseDouble(str);
            String line = (String)comboBoxline.getSelectedItem();
            
            boolean valid = n.addDelay(delayStationStart,delayStationEnd,timeDelayed,line);//will permently change unless add reset button or reload
            
            if(valid == false){
                JOptionPane.showMessageDialog(null, "not a valid connection");
            }
            
            System.out.println(timeDelayed);
            });

            //ADDING ACTION LISTIONER so that when the button is clicked it will do the code in brackets i need to make it set station values and start the sort
            //code crashes if start and stop are the same so check for that as an issue
            // will work out the journey that has been selected
            startButton.addActionListener(e -> {
                String startName = (String)comboBoxStart.getSelectedItem();
                String endName =  (String)comboBoxEnd.getSelectedItem();

                if(startName == endName){}
                else if((String)comboBoxSyle.getSelectedItem()== "Fastest Route"){
                    JourneySolve journey = new JourneySolve(n);
                    Route solution = journey.djikstrasFastest(startName, endName);// finds the shortest path
                    solution.printRoute("Fastest Route");
                 
                    double timeTaken = solution.getTime();
                    int changesTaken = solution.getChanges();
                    changesMessageLable.setText("Total changes: "+String.valueOf(changesTaken)); 
                    timeMessageLable.setText("Total time: "+String.valueOf(timeTaken)+ " mins"); // multi line label <html>Total<br> time: </html>
                    // draw display here
                    ArrayList<Station> paths =  solution.getPath();
                    ArrayList<String>lines  = solution.getLines();
                    String pathWayDraw = "Route:\n\n"+paths.get(0).getName()+"\n"; // adding start path
                    String curPath = lines.get(0);// keep track of changes
                    // pathWayDraw+="|\n| "+curPath+" Line\n| \n";
                    if(curPath.equals("Walk")){pathWayDraw+="|\n| "+curPath+"\n| \n";}
                    else{pathWayDraw+="|\n| "+curPath+" Line\n| \n";}
                    for(int i = 0;i<lines.size();i++){
                        if(!curPath.equals(lines.get(i))){
                            curPath = lines.get(i);
                            //pathWayDraw+="\n";
                            pathWayDraw+=(paths.get(i)).getName();
                            if(curPath.equals("Walk")){pathWayDraw+="\n|\n| "+curPath+"\n| \n";}
                            else{pathWayDraw+="\n|\n| "+curPath+" Line\n| \n";}
                            
                        }
                    }
                    //pathWayDraw+="<br>";
                    areaBoxRoute.setForeground(Color.DARK_GRAY);
                    //pathWayDraw+="\n";
                    pathWayDraw+=(paths.get(lines.size())).getName();
                    // pathWayDraw+="<html>";// end path
                    routeMessageLable.setText(pathWayDraw);
                    //delayBoxRoute.setText(pathWayDraw);
                    areaBoxRoute.setText(pathWayDraw);
                    System.out.println();
                    System.out.println(pathWayDraw);

                    System.out.println(solution.getPrice());
                    priceLable.setText("Price: £"+Double.toString(solution.getPrice())+"0");

                    // drawing the graph idk if to do all of the stations or just the solution
                    Graphy graph = new Graphy();
                    graph.setBounds(0, 0, 2000, 700);
                    panel.add(graph);
                    graph.setRoute(solution);

                    panel.repaint();
                    
                }

                else if((String)comboBoxSyle.getSelectedItem()== "Least Changes"){
                    JourneySolve journey = new JourneySolve(n);
                    Route solution = journey.djikstrasLeastChange(startName, endName);// finds the least changes path
                    solution.printRoute("Least Changes");
                    double timeTaken = solution.getTime();
                    int changesTaken = solution.getChanges();
                    changesMessageLable.setText("Total changes: "+String.valueOf(changesTaken)); 
                    timeMessageLable.setText("Total time: "+String.valueOf(timeTaken)+" mins"); // multi line label <html>Total<br> time: </html>

                     timeTaken = solution.getTime();
                     changesTaken = solution.getChanges();
                    changesMessageLable.setText("Total changes: "+String.valueOf(changesTaken)); 
                    timeMessageLable.setText("Total time: "+String.valueOf(timeTaken)); // multi line label <html>Total<br> time: </html>
                    // draw display here
                    ArrayList<Station> paths =  solution.getPath();
                    ArrayList<String>lines  = solution.getLines();
                    String pathWayDraw = "Route:\n\n"+paths.get(0).getName()+"\n"; // adding start path
                    String curPath = lines.get(0);// keep track of changes
                    // pathWayDraw+="|\n| "+curPath+" Line\n| \n";ss
                    if(curPath.equals("Walk")){pathWayDraw+="|\n| "+curPath+"\n| \n";}
                    else{pathWayDraw+="|\n| "+curPath+" Line\n| \n";}
                    for(int i = 0;i<lines.size();i++){
                        if(!curPath.equals(lines.get(i))){
                            curPath = lines.get(i);
                            //pathWayDraw+="\n";
                            pathWayDraw+=(paths.get(i)).getName();
                            if(curPath.equals("Walk")){pathWayDraw+="\n|\n| "+curPath+"\n| \n";}
                            else{pathWayDraw+="\n|\n| "+curPath+" Line\n| \n";}
                            
                        }
                    }
                    //pathWayDraw+="<br>";
                    areaBoxRoute.setForeground(Color.DARK_GRAY);
                    //pathWayDraw+="\n";
                    pathWayDraw+=(paths.get(lines.size())).getName();
                    // pathWayDraw+="<html>";// end path
                    routeMessageLable.setText(pathWayDraw);
                    //delayBoxRoute.setText(pathWayDraw);
                    areaBoxRoute.setText(pathWayDraw);

                    Graphy graph = new Graphy();
                    graph.setBounds(0, 0, 2000, 700);
                    panel.add(graph);
                    graph.setRoute(solution);

                    panel.repaint();
                
                }

                


                
               });

            //ImageIcon icon = new ImageIcon("metroIcon.jpg");
            //window.setIconImage(icon.getImage());



            window.add(panel);
            window.setVisible(true);


      //GridLayout layout = new GridLayout(3,4);
        //panel.setLayout(layout);
       // window.setContentPane(panel);
       
        //ImageIcon myPicture = new ImageIcon("MetroLogo.svg");
        //JLabel picLabel = new JLabel(myPicture);
        //panel.add(picLabel);
          
        

        
        // this is just me chekcking that it does what i want it to do it does can delet next time
        for(Station i:stationsArray){
            //System.out.println(i.getName()); // I DONT NEEd TO PRINT THIS OUT  anymore
            String str = "";
            for(Connection c:i.getConnections()){
                String h = c.getStationFrom().getName();
                str+=("  "+h);
                h= c.getStationTo().getName();str+=("  "+h);
                h= c.getLine();
                str+=("  "+h);
                
                

            }
        }
// asking the user to input a start and stopping stations will imporve as i get more advance with my code
// while loop so wont stop until a valid station has been entered
//        System.out.println("Enter a statring station");
//        Scanner scanner = new Scanner(System.in);
//        String from = scanner.nextLine().trim();
//        while(n.doesStationExist(from) ==  false){
//            System.out.println("not a valid station");
//            System.out.println("Enter a statring station");
//             from = scanner.nextLine();
//        }
//
 //       System.out.println("Enter a ending station");
//         scanner = new Scanner(System.in);
//         String to = scanner.nextLine().trim();
 //       while(n.doesStationExist(to) ==  false){
//            System.out.println("not a valid station");
//            System.out.println("Enter a ending station");
//            to = scanner.nextLine();
//        }
//        scanner.close();
//        
//    }
   
    }
// doesnt work trying to draw lines
    
}

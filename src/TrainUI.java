import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

public class TrainUI extends JFrame {
    /*
     * Define all the main JFrame objects to be used in this UI.
     */
    private JPanel controlButtonsFrame;
    private JButton particularDistanceButton;
    private JButton showAllRoutesButton;
    private JButton shortestButton;
    private JLabel userText;
    private JComboBox sourceMenu;
    private JComboBox destinationMenu;
    private JTextField weightDisplay;
    private JMenuBar menuBar;

    /*
     * Define the JMenuItems
     */
    private JMenuItem resetItem;
    private JMenuItem exitItem;
    private LinkedList<JMenuItem> nodeMenuList;

    public TrainUI() {
        super("Kiwiland Train Routes");
        setLayout(new GridLayout(5,1));
        ActionHandler handler = new ActionHandler();

        /*
         * Create three buttons to control the function of the UI
         */
        controlButtonsFrame = new JPanel();
        controlButtonsFrame.setLayout(new GridLayout(1,3));
        particularDistanceButton = new JButton("ROUTE DISTANCE");
        particularDistanceButton.setToolTipText("Find the distance between two adjacent stations.");
        particularDistanceButton.addActionListener(handler);
        showAllRoutesButton = new JButton("SHOW ALL ROUTES");
        showAllRoutesButton.setToolTipText("Show all possible routes between two stations.");
        showAllRoutesButton.addActionListener(handler);
        shortestButton = new JButton("SHORTEST ROUTE");
        shortestButton.setToolTipText("Find the shortest route between two stations.");
        shortestButton.addActionListener(handler);
        controlButtonsFrame.add(particularDistanceButton);
        controlButtonsFrame.add(showAllRoutesButton);
        controlButtonsFrame.add(shortestButton);


        /*
         * Create an array of Strings to represent the routes in this Graph.
         */
        String[] routes = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};

        /*
         * DEFINE THE GRAPH FOR THIS UI
         */
        Graph trainGraph = new Graph();
        try {
            for (String s : routes) {
                trainGraph.addRoute(s);
            }
        } catch (InvalidRouteInputException irie) {
            JOptionPane.showMessageDialog(null,irie.getMessage());
        }


        userText = new JLabel("Please use the buttons to select a function for this application.");
        userText.setHorizontalAlignment(JLabel.CENTER);

        /*
         * Set up the File menu
         */
        JMenu fileMenu = new JMenu("File");
        resetItem = new JMenuItem("Reset");
        resetItem.setToolTipText("Reset both source and destination.");
        resetItem.addActionListener(handler);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener((event) -> System.exit(0));
        fileMenu.add(resetItem);
        fileMenu.add(exitItem);

        /*
         * Set up the menu for Source
         */
        JMenuItem[] sourceMenuItems = new JMenuItem[(trainGraph.getNodes().size())];
        String[] sourceList = new String[sourceMenuItems.length + 1];
        sourceList[0] = "Please select a source station!";
        for(int i = 0 ; i < trainGraph.getNodes().size() ; i++) {
            sourceMenuItems[i] = new JMenuItem(trainGraph.getNodes().get(i).getStation());
            sourceMenuItems[i].addActionListener(handler);
            sourceList[i] = trainGraph.getNodes().get(i).getStation();
        }
        sourceMenu = new JComboBox(sourceList);

        /*
         * Set up the menu for Destination
         */
        String[] destinationList = new String[destinationMenuItems.length + 1];
        destinationList[0] = "Please select a destination station!";
        for(int i = 0 ; i < trainGraph.getNodes().size() ; i++) {
            destinationMenuItems[i] = new JMenuItem(trainGraph.getNodes().get(i).getStation());
            destinationMenuItems[i].addActionListener(handler);
            destinationList[i] = trainGraph.getNodes().get(i).getStation();
        }




        add(userText);
        add(controlButtonsFrame);
    }

    /**
     * Action handler that handles all actions performed by the UI.
     */
    private class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            /*
             * CONTROL BUTTONS
             */
            if(event.getSource() == particularDistanceButton) {
                userText.setText("Use the menus to find the distance between two stations.");
                add(sourceMenu);
                add(destinationMenu);
            }
        }
    }
}

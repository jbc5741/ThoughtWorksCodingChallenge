import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.LinkedList;

public class TrainUI implements ItemListener {

    private static final String SPECIFIC_ROUTE = "SPECIFIC ROUTE";
    private static final String POSSIBLE_ROUTES = "POSSIBLE ROUTES";
    private static final String SHORTEST_ROUTE = "SHORTEST ROUTE";

    private JPanel trainCard;

    private String[] routeArray = { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
    private Graph trainGraph = new Graph();

    private JPanel specificRoutePanel;
    private JButton addStop;
    private LinkedList<JComboBox> specificRouteStops = new LinkedList<JComboBox>();
    private JTextField specificRouteWeight;
    private JButton calculateSpecificRoute;

    private JPanel possibleRoutePanel;
    private JComboBox possibleRouteSource;
    private JComboBox possibleRouteDestination;
    private JTextField maximumWeight;
    private JButton findPossibleRoutes;
    private JTextArea routeListArea;


    public void addComponent(Container pane) {

        try{
            for (String s : routeArray) {
                trainGraph.addRoute(s);
            }
        } catch (InvalidRouteInputException irie) {
            JOptionPane.showMessageDialog(null, irie.getMessage());
        }
        String[] stationList = new String[(trainGraph.getNodes().size())];
        for(int i = 0 ; i < stationList.length ; i++ ) {
            stationList[i] = trainGraph.getNodes().get(i).getStation();
        }

        /*
         * SET UP COMBO BOX TO SWITCH BETWEEN PROGRAM FUNCTIONALITY
         */
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {SPECIFIC_ROUTE, POSSIBLE_ROUTES, SHORTEST_ROUTE};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        ActionHandler handler = new ActionHandler();

        /*
         * CREATE THE CARDS FOR EACH PROGRAM FUNCTION
         */
        specificRoutePanel = new JPanel();
        JLabel specificRouteSourceLabel = new JLabel("From:");
        specificRoutePanel.add(specificRouteSourceLabel);
        specificRouteStops.add(new JComboBox(stationList));
        specificRoutePanel.add(specificRouteStops.get(0));
        addStop = new JButton("Add a stop");
        specificRoutePanel.add(addStop);
        addStop.addActionListener(handler);
        calculateSpecificRoute = new JButton("Calculate Route");
        specificRoutePanel.add(calculateSpecificRoute);
        calculateSpecificRoute.addActionListener(handler);
        specificRouteWeight = new JTextField();
        specificRouteWeight.setEditable(false);


        possibleRoutePanel = new JPanel();
        JLabel possibleRouteSourceLabel = new JLabel("From: ");
        possibleRoutePanel.add(possibleRouteSourceLabel);
        possibleRouteSource = new JComboBox(stationList);
        possibleRoutePanel.add(possibleRouteSource);
        JLabel possibleRouteDestinationLabel = new JLabel("To: ");
        possibleRoutePanel.add(possibleRouteDestinationLabel);
        possibleRouteDestination = new JComboBox(stationList);
        possibleRouteDestination.addItemListener(this);
        possibleRoutePanel.add(possibleRouteDestination);
        JLabel maximumWeightLabel = new JLabel("Max Distance: ");
        possibleRoutePanel.add(maximumWeightLabel);
        maximumWeight = new JTextField("",15);
        possibleRoutePanel.add(maximumWeight);
        findPossibleRoutes = new JButton("Find Possible Routes");
        findPossibleRoutes.addActionListener(handler);
        possibleRoutePanel.add(findPossibleRoutes);
        routeListArea = new JTextArea(5,15);
        possibleRoutePanel.add(routeListArea);


        JPanel shortestRoutePanel = new JPanel();
        JLabel shortestRouteSourceLabel = new JLabel("From:");
        shortestRoutePanel.add(shortestRouteSourceLabel);
        JComboBox shortestRouteComboBox1 = new JComboBox(stationList);
        shortestRoutePanel.add(shortestRouteComboBox1);

        trainCard = new JPanel(new CardLayout());
        trainCard.add(specificRoutePanel, SPECIFIC_ROUTE);
        trainCard.add(possibleRoutePanel, POSSIBLE_ROUTES);
        trainCard.add(shortestRoutePanel, SHORTEST_ROUTE);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(trainCard, BorderLayout.CENTER);

        LinkedList<String> sss = trainGraph.findPossibleRoutes(trainGraph.getNodes().get(0),trainGraph.getNodes().get(0),30);
        for (String s : sss) {
            System.out.println(s);
        }

    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl1 = (CardLayout)(trainCard.getLayout());
        cl1.show(trainCard, (String)evt.getItem());
    }

    /*
     * CREATE THE ACTION LISTENER FOR VARIOUS BUTTONS
     */
    class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == addStop) {

                specificRoutePanel.remove(addStop);
                specificRoutePanel.remove(calculateSpecificRoute);
                specificRoutePanel.remove(specificRouteWeight);

                System.out.println("Here");
                String selectedString = specificRouteStops.getLast().getSelectedItem().toString();
                System.out.println("There");
                Node selectedNode = new Node(selectedString);
                int routeIndex = trainGraph.getNodes().indexOf(selectedNode);
                Node thisNode = trainGraph.getNodes().get(routeIndex);
                for (Node n : thisNode.getAdjacentList()) {
                    System.out.println("" + n);
                }
                String[] destinationList = new String[(thisNode.getAdjacentList().size())];
                for (int i = 0 ; i < destinationList.length ; i++) {
                    System.out.println(thisNode.getStation() + i + "");
                    destinationList[i] = thisNode.getAdjacentList().get(i).getStation();
                    System.out.println("" + thisNode.getAdjacentList().get(i));
                }
                specificRouteStops.add(new JComboBox(destinationList));
                specificRoutePanel.add(specificRouteStops.getLast());

                specificRoutePanel.add(addStop);
                specificRoutePanel.add(calculateSpecificRoute);
                specificRoutePanel.add(specificRouteWeight);

                specificRoutePanel.revalidate();
            }


            else if(event.getSource() == calculateSpecificRoute) {
                int calculatedWeight = 0;

                Edge[] edgesToAdd = new Edge[(specificRouteStops.size() - 1)];
                for (int i = 0 ; i < edgesToAdd.length ; i++) {
                    Node nodeA = new Node(specificRouteStops.get(i).getSelectedItem().toString());
                    Node nodeB = new Node(specificRouteStops.get(i+1).getSelectedItem().toString());

                    try {
                        calculatedWeight += trainGraph.getEdge(nodeA,nodeB);
                    } catch (NodeNotContainedException nnce) {
                        JOptionPane.showMessageDialog(null, nnce.getMessage());
                    }
                }

                specificRouteWeight.setText("The total length for this route is: " + calculatedWeight);
                specificRoutePanel.revalidate();
            }

            else if (event.getSource() == findPossibleRoutes) {
                String tempSourceString = possibleRouteSource.getSelectedItem().toString();
                String tempDestinationString = possibleRouteDestination.getSelectedItem().toString();

                Node tempSource = new Node(tempSourceString);
                Node tempDest = new Node(tempDestinationString);

                int tempSourceIndex = trainGraph.getNodes().indexOf(tempSource);
                int tempDestIndex = trainGraph.getNodes().indexOf(tempDest);

                LinkedList<String> routeList = trainGraph.findPossibleRoutes(trainGraph.getNodes().get(tempSourceIndex),
                                                                             trainGraph.getNodes().get(tempDestIndex),
                                                                             Integer.parseInt(maximumWeight.getText()));

                String returnString = "";
                for (String s : routeList) {
                    returnString += s;
                }
                routeListArea.setText(returnString);
                possibleRoutePanel.revalidate();
            }
        }
    }

    private static void createAndShowUI() {
        JFrame trainFrame = new JFrame("TW Train Routes");
        trainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TrainUI ui = new TrainUI();
        ui.addComponent(trainFrame.getContentPane());

        trainFrame.pack();
        trainFrame.setSize(500,200);
        trainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}


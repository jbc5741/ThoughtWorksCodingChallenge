import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class TrainUI extends JFrame {
    /*
     * Define all the main JFrame objects to be used in this UI.
     */
    private JLabel userText;
    private JMenu sourceMenu;
    private JMenu destinationMenu;
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
        setLayout(new GridLayout(4,1));
        ActionHandler handler = new ActionHandler();

        /*
         * DEFINE THE NODES AND EDGES FOR THIS UI
         */
        Node nodeA = new Node("A",new LinkedList<Node>());
        Node nodeB = new Node("B",new LinkedList<Node>());
        Node nodeC = new Node("C",new LinkedList<Node>());
        Node nodeD = new Node("D",new LinkedList<Node>());
        Node nodeE = new Node("E",new LinkedList<Node>());

        Edge route1 = new Edge(nodeA,nodeB);
        Edge route1 = new Edge(nodeA,nodeB);
        Edge route1 = new Edge(nodeA,nodeB);

        userText = new JLabel("Please use the menus to select your source and destination.");
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
        sourceMenu = new JMenu("Source");
        for(Node n : nodeArray) {

        }

    }
}

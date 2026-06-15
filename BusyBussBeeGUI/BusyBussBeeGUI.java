
/**
* BusyBussBee Task Manager - GUI Version
*
* Demonstrates Java's LinkedList and ListIterator using a Swing desktop UI.
* The window is divided into 3 zones using BorderLayout.
* 
*  +---------------------------+
*  |          NORTH            |  <- Title label
*  +---------------------------+
*  |                           |
*  |          CENTER           |  <- Task list display (scrollable)
*  |                           |
*  +---------------------------+
*  |          SOUTH            |  <- Text input + buttons
*  +---------------------------+
*                       
*/

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BusyBussBeeGUI {

    // -------------------------------------------------------------------------
    // Fields — accessible throughout the entire class
    // -------------------------------------------------------------------------

    private LinkedList<String> tasks = new LinkedList<>(); // our data structure

    private JFrame frame; // the main application window
    private JTextArea taskDisplay; // multi-line area that shows the task list
    private JTextField taskInput; // single-line box where the user types a task

    // -------------------------------------------------------------------------
    // Constructor — called by main(); kicks off the UI build
    // -------------------------------------------------------------------------

    public BusyBussBeeGUI() {
        buildWindow();
    }

    // -------------------------------------------------------------------------
    // buildWindow() — constructs every UI component and wires them together.
    // Everything lives inside this method so Java can see the variables.
    // -------------------------------------------------------------------------

    private void buildWindow() {

        // --- Main window (JFrame) ---
        frame = new JFrame("BusyBussBee Task Manager");
        ImageIcon appIcon = new ImageIcon("BusyBussBeeGUIIcon.png"); // your image filename here
        frame.setIconImage(appIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app on X
        frame.setSize(500, 450);
        frame.setLayout(new BorderLayout(10, 10)); // 3-zone layout

        // --- north: Title label ---
        JLabel title = new JLabel("BusyBussBee Task Manager", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // padding
        frame.add(title, BorderLayout.NORTH);

        // --- center: Scrollable task list ---
        // JTextArea is a multi-line read-only display (editable = false)
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        taskDisplay.setText("Your task list will appear here.");

        // Wrap in a JScrollPane so a scrollbar appears when the list grows long
        JScrollPane scrollPane = new JScrollPane(taskDisplay);
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- south: Input field + buttons ---
        // bottomPanel stacks the text field above the button row
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Text field where the user types a new task
        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 14));
        taskInput.setToolTipText("Type a task here, then click a button below.");
        bottomPanel.add(taskInput, BorderLayout.CENTER);

        // Button row — FlowLayout lines them up horizontally
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton addLastBtn = new JButton("Add Task"); // adds to end
        JButton addFirstBtn = new JButton("Add Urgent Task"); // adds to front
        JButton viewBtn = new JButton("Refresh List"); // re-renders list
        JButton clearInputBtn = new JButton("Clear Input"); // empties text field

        buttonPanel.add(addLastBtn);
        buttonPanel.add(addFirstBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(clearInputBtn);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // --- ActionListeners — what happens when each button is clicked ---

        // "Add Task" → appends to the end of the LinkedList (lowest priority)
        addLastBtn.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.addLast(task); // LinkedList method: insert at tail
                taskInput.setText(""); // clear the input box after adding
                refreshDisplay(); // redraw the list
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task first.");
            }
        });

        // "Add Urgent Task" → inserts at the front of the LinkedList (highest priority)
        addFirstBtn.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.addFirst(task); // LinkedList method: insert at head
                taskInput.setText("");
                refreshDisplay();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter an urgent task first.");
            }
        });

        // "Refresh List" → re-renders the current task list in the display area
        viewBtn.addActionListener(e -> refreshDisplay());

        // "Clear Input" → empties the text field without adding anything
        clearInputBtn.addActionListener(e -> taskInput.setText(""));

        // Fun touch - pressing Enter in the text field triggers "Add Task"
        // automatically
        taskInput.addActionListener(e -> addLastBtn.doClick());

        // --- Final step: centre on screen and make visible ---
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // -------------------------------------------------------------------------
    // refreshDisplay() — iterates the LinkedList and prints each task.
    // Uses the same ListIterator pattern as the original console version.
    // -------------------------------------------------------------------------

    private void refreshDisplay() {
        if (tasks.isEmpty()) {
            taskDisplay.setText("(Your list is empty — no tasks yet.)");
            return;
        }

        StringBuilder sb = new StringBuilder("--- Today's To-Do List ---\n\n");

        // ListIterator walks the LinkedList front-to-back, same as the console version
        ListIterator<String> iterator = tasks.listIterator();
        int count = 1;
        while (iterator.hasNext()) {
            sb.append(count).append(".  [ ]  ").append(iterator.next()).append("\n");
            count++;
        }

        taskDisplay.setText(sb.toString());
    }

    // -------------------------------------------------------------------------
    // main() — entry point.
    // invokeLater() ensures the UI is built on the Swing Event Dispatch Thread,
    // which is the thread-safe way to start any Swing application.
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BusyBussBeeGUI::new);
    }
}
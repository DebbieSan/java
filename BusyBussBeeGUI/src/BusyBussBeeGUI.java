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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    // Colour palette — butter yellow theme
    // -------------------------------------------------------------------------

    private static final Color YELLOW_LIGHT  = new Color(0xFFF8DC); // cornsilk — background
    private static final Color YELLOW_MID    = new Color(0xF5E642); // butter yellow — buttons
    private static final Color YELLOW_HOVER  = new Color(0xE6D100); // deeper gold — button press
    private static final Color BROWN_TEXT    = new Color(0x4A3800); // dark amber — all text
    private static final Color YELLOW_BORDER = new Color(0xC8A400); // warm gold — borders/accents

    // -------------------------------------------------------------------------
    // Fields — accessible throughout the entire class
    // -------------------------------------------------------------------------

    private LinkedList<String>  tasks     = new LinkedList<>(); // task names
    private LinkedList<Boolean> completed = new LinkedList<>(); // completion state per task

    // File where tasks are saved between sessions
    private static final String SAVE_FILE = System.getProperty("user.home") + File.separator + "busybussbee_tasks.txt";

    private JFrame    frame;
    private JTextArea taskDisplay;
    private JTextField taskInput;

    // -------------------------------------------------------------------------
    // Constructor — called by main(); kicks off the UI build
    // -------------------------------------------------------------------------

    public BusyBussBeeGUI() {
        loadTasks(); // restore any previously saved tasks
        buildWindow();
    }

    // -------------------------------------------------------------------------
    // styledButton() — creates a consistently themed button
    // -------------------------------------------------------------------------

    private JButton styledButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(YELLOW_MID);
        btn.setForeground(BROWN_TEXT);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(YELLOW_BORDER, 1),
            BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e)  { btn.setBackground(YELLOW_HOVER); }
            public void mouseReleased(java.awt.event.MouseEvent e) { btn.setBackground(YELLOW_MID);   }
            public void mouseEntered(java.awt.event.MouseEvent e)  { btn.setBackground(YELLOW_HOVER); }
            public void mouseExited(java.awt.event.MouseEvent e)   { btn.setBackground(YELLOW_MID);   }
        });
        return btn;
    }

    // -------------------------------------------------------------------------
    // buildWindow() — constructs every UI component and wires them together.
    // -------------------------------------------------------------------------

    private void buildWindow() {

        // --- Main window (JFrame) ---
        frame = new JFrame("BusyBussBee Task Manager");
        frame.getContentPane().setBackground(YELLOW_LIGHT);

        // Load icon only if the file actually exists, to avoid a crash
        File iconFile = new File("BusyBussBeeGUIIcon.png");
        if (iconFile.exists()) {
            ImageIcon appIcon = new ImageIcon("BusyBussBeeGUIIcon.png");
            frame.setIconImage(appIcon.getImage());
        }

        // Save tasks to disk whenever the user closes the window
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                saveTasks();
                System.exit(0);
            }
        });

        frame.setSize(520, 480);
        frame.setLayout(new BorderLayout(10, 10));

        // --- NORTH: Title label ---
        JLabel title = new JLabel("  BusyBussBee Task Manager", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(BROWN_TEXT);
        title.setOpaque(true);
        title.setBackground(YELLOW_MID);
        title.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, YELLOW_BORDER),
            BorderFactory.createEmptyBorder(12, 0, 12, 0)
        ));
        frame.add(title, BorderLayout.NORTH);

        // --- CENTER: Scrollable task list ---
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        taskDisplay.setBackground(YELLOW_LIGHT);
        taskDisplay.setForeground(BROWN_TEXT);
        taskDisplay.setCaretColor(BROWN_TEXT);
        taskDisplay.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        taskDisplay.setText("Your task list will appear here.");

        JScrollPane scrollPane = new JScrollPane(taskDisplay);
        scrollPane.setBorder(BorderFactory.createLineBorder(YELLOW_BORDER, 1));
        scrollPane.getViewport().setBackground(YELLOW_LIGHT);
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- SOUTH: Input field + buttons ---
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBackground(YELLOW_LIGHT);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 14));
        taskInput.setBackground(Color.WHITE);
        taskInput.setForeground(BROWN_TEXT);
        taskInput.setCaretColor(BROWN_TEXT);
        taskInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(YELLOW_BORDER, 1),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        taskInput.setToolTipText("Type a task here, then click a button below.");
        bottomPanel.add(taskInput, BorderLayout.CENTER);

        // Row 1: adding tasks
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        addPanel.setBackground(YELLOW_LIGHT);
        JButton addLastBtn  = styledButton("Add Task");
        JButton addFirstBtn = styledButton("Add Urgent Task");
        addPanel.add(addLastBtn);
        addPanel.add(addFirstBtn);

        // Row 2: managing tasks
        JPanel managePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        managePanel.setBackground(YELLOW_LIGHT);
        JButton completeBtn = styledButton("Mark Complete");
        JButton deleteBtn   = styledButton("Delete Task");
        JButton cleanupBtn  = styledButton("Clean Up Done Items");
        managePanel.add(completeBtn);
        managePanel.add(deleteBtn);
        managePanel.add(cleanupBtn);

        JPanel buttonStack = new JPanel(new BorderLayout());
        buttonStack.setBackground(YELLOW_LIGHT);
        buttonStack.add(addPanel,    BorderLayout.NORTH);
        buttonStack.add(managePanel, BorderLayout.SOUTH);

        bottomPanel.add(buttonStack, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // --- ActionListeners ---

        // Add Task → append to end of list
        addLastBtn.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.addLast(task);
                completed.addLast(false);
                taskInput.setText("");
                refreshDisplay();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task first.");
            }
        });

        // Add Urgent Task → insert at front of list
        addFirstBtn.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                tasks.addFirst(task);
                completed.addFirst(false);
                taskInput.setText("");
                refreshDisplay();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter an urgent task first.");
            }
        });

        // Mark Complete → ask for task number, toggle its done state
        completeBtn.addActionListener(e -> {
            if (tasks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No tasks to mark.");
                return;
            }
            String input = JOptionPane.showInputDialog(frame,
                    "Enter the task number to mark complete/incomplete:");
            if (input == null) return;
            try {
                int index = Integer.parseInt(input.trim()) - 1;
                if (index < 0 || index >= tasks.size()) {
                    JOptionPane.showMessageDialog(frame, "Invalid task number.");
                    return;
                }
                completed.set(index, !completed.get(index));
                refreshDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        // Delete Task → ask for task number, remove from both lists
        deleteBtn.addActionListener(e -> {
            if (tasks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No tasks to delete.");
                return;
            }
            String input = JOptionPane.showInputDialog(frame,
                    "Enter the task number to delete:");
            if (input == null) return;
            try {
                int index = Integer.parseInt(input.trim()) - 1;
                if (index < 0 || index >= tasks.size()) {
                    JOptionPane.showMessageDialog(frame, "Invalid task number.");
                    return;
                }
                String removed = tasks.get(index);
                tasks.remove(index);
                completed.remove(index);
                refreshDisplay();
                JOptionPane.showMessageDialog(frame, "Deleted: \"" + removed + "\"");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        // Clean Up Done Items → remove all completed tasks from both lists
        cleanupBtn.addActionListener(e -> {
            int before = tasks.size();
            ListIterator<String>  taskIter = tasks.listIterator();
            ListIterator<Boolean> doneIter = completed.listIterator();
            while (taskIter.hasNext()) {
                taskIter.next();
                if (doneIter.next()) {
                    taskIter.remove();
                    doneIter.remove();
                }
            }
            int removed = before - tasks.size();
            if (removed == 0) {
                JOptionPane.showMessageDialog(frame, "No completed tasks to remove.");
            } else {
                refreshDisplay();
                JOptionPane.showMessageDialog(frame, "Removed " + removed + " completed task(s).");
            }
        });

        // Pressing Enter in the text field triggers Add Task
        taskInput.addActionListener(e -> addLastBtn.doClick());

        // --- Finalize ---
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        refreshDisplay(); // show any tasks loaded from disk
    }

    // -------------------------------------------------------------------------
    // refreshDisplay() — iterates both LinkedLists and renders each task.
    // -------------------------------------------------------------------------

    private void refreshDisplay() {
        if (tasks.isEmpty()) {
            taskDisplay.setText("Your list is empty. Let's start a new list!");
            return;
        }

        StringBuilder sb = new StringBuilder("--- Today's To-Do List ---\n\n");

        ListIterator<String>  taskIter = tasks.listIterator();
        ListIterator<Boolean> doneIter = completed.listIterator();
        int count = 1;

        while (taskIter.hasNext()) {
            String  task = taskIter.next();
            boolean done = doneIter.next();
            String  box  = done ? "[x]" : "[ ]";
            String  line = done ? task + "  ✓" : task;
            sb.append(count).append(".  ").append(box).append("  ").append(line).append("\n");
            count++;
        }

        sb.append("\n(Tasks saved automatically on close.)");
        taskDisplay.setText(sb.toString());
    }

    // -------------------------------------------------------------------------
    // saveTasks() — writes tasks + completion state to a plain-text file.
    // Format per line:  DONE|task text   or   TODO|task text
    // -------------------------------------------------------------------------

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            ListIterator<String>  taskIter = tasks.listIterator();
            ListIterator<Boolean> doneIter = completed.listIterator();
            while (taskIter.hasNext()) {
                String  task = taskIter.next();
                boolean done = doneIter.next();
                writer.write((done ? "DONE" : "TODO") + "|" + task);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Could not save tasks: " + ex.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // loadTasks() — reads the save file back into the two LinkedLists.
    // -------------------------------------------------------------------------

    private void loadTasks() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DONE|")) {
                    tasks.addLast(line.substring(5));
                    completed.addLast(true);
                } else if (line.startsWith("TODO|")) {
                    tasks.addLast(line.substring(5));
                    completed.addLast(false);
                }
            }
        } catch (IOException ex) {
            System.err.println("Could not load tasks: " + ex.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // main() — entry point.
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BusyBussBeeGUI::new);
    }
}
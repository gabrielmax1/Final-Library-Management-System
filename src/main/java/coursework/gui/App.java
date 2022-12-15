package coursework.gui;
// This package is a collection of classes used for creating graphical user interface (GUI) components for Java programs.


import javax.swing.*;
// This is the critical Class in the View which will instantiate
public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();

        // Setting the tile of the GUI
        frame.setTitle("Library Management System");
        frame.setContentPane(new MainPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add the close function
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

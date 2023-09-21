package main;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class Animation {
    JFrame frame;

    public Animation() {
        frame = new JFrame("Orbit 3Body");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TestPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}

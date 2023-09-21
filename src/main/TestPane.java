package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class TestPane extends JPanel {
    JPanel stats = new JPanel();

    Motion m = new Motion();

    public TestPane() {
        Timer timer = new Timer(40, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                m.update();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        stats.setBounds(0,800,1000,100);
        JTextArea js = new JTextArea("m1 = " + m.m1 +"kg"
                + " | m2 = "+ m.m2 +"kg"
                + " | m3 = "+ m.m3 +"kg"
                + " | G = " + m.G  + "N⋅m²/kg²" );

        stats.add(js);
        super.paintComponent(g);
        super.setBackground(Color.BLACK);
        super.add(stats);
        Graphics2D g2d = (Graphics2D) g.create();


        g2d.scale(0.05 , 0.05);
        Image img = null;
        Image img2 = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/planet2.png")));
            img2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/planet.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawImage(img2.getScaledInstance(3000,3000,1), (int)m.getP3_start()[0],(int)m.getP3_start()[1], null);

        g2d.drawImage(img, (int)m.getP1_start()[0], (int)m.getP1_start()[1], null);
        g2d.drawImage(img, (int)m.getP2_start()[0], (int)m.getP2_start()[1], null);
        g2d.setPaint(Color.red);
        g2d.setFont(new Font("Default", 2,350));
        g2d.drawString("velocities " + " v1 ="+ m.getV1_start()[0] + " m/s \n"
                +"v2 = "+ m.getV2_start()[0] + " m/s \n"
                +"v3 = "+ m.getV3_start()[0] + " m/s",200,300);

        g2d.drawOval((int) m.getBarycenterCoordinates()[0], (int) m.getBarycenterCoordinates()[1],500,500);
        g2d.dispose();
    }
}

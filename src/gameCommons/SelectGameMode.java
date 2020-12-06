package gameCommons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SelectGameMode extends JFrame {

    Font arcadeFont;

    public SelectGameMode()
    {
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
             ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("ARCADECLASSIC.TTF")));
            System.out.println("La police a été chargée !");
        } catch (IOException |FontFormatException e) {
            //Handle exception
            System.out.println("La police n'a pas été chargée !");
        }

        JFrame frame = new JFrame("Frogger");

        JLabel label = new JLabel("FROGGER", JLabel.CENTER);
        label.setFont(arcadeFont);
        label.setSize(200,100);

        frame.add(label);
        frame.setLayout(new GridLayout(8, 1,100,10));

        JButton p1 = new JButton("Partie 1");
        p1.setBackground(new Color(59, 89, 182));
        p1.setForeground(Color.WHITE);
        p1.setFocusPainted(false);

        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mode partie 1 !");
                frame.dispose();
                LauchGame g = new LauchGame(true,false,false,false);
            }
        });

        JButton p2 = new JButton("Partie 2");
        p2.setBackground(new Color(59, 89, 182));
        p2.setForeground(Color.WHITE);
        p2.setFocusPainted(false);

        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mode partie 2 !");
                frame.dispose();
                LauchGame g = new LauchGame(false,true,false,false);
            }
        });

        JButton p3 = new JButton("Partie 3");
        p3.setBackground(new Color(59, 89, 182));
        p3.setForeground(Color.WHITE);
        p3.setFocusPainted(false);

        p3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mode partie 3 !");
                frame.dispose();
                LauchGame g = new LauchGame(false,false,true,false);
            }
        });

        JButton p4 = new JButton("Partie 4");
        p4.setBackground(new Color(59, 89, 182));
        p4.setForeground(Color.WHITE);
        p4.setFocusPainted(false);

        p4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mode partie 4 !");
                frame.dispose();
                LauchGame g = new LauchGame(false,false,false,true);
            }
        });


        frame.add(p1);
        frame.add(p2);
        frame.add(p3);
        frame.add(p4);

        JLabel label2 = new JLabel("Année 2020-2021", JLabel.CENTER);
        //label2.setFont(arcadeFont);
        //label2.setSize(200,100);
        frame.add(label2);

        JLabel label3 = new JLabel("Théo Manea - Fréderic Almeida De Oliveira", JLabel.CENTER);
        //label3.setFont(arcadeFont);
        //label3.setSize(200,100);
        frame.add(label3);

        JLabel label4 = new JLabel("Paris-Sud-University", JLabel.CENTER);
        //label4.setFont(arcadeFont);
        //label4.setSize(200,100);
        frame.add(label4);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

}

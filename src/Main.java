package src;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import java.util.Scanner;

public class Main {

  //static JFrame f; 
  
  //static JButton b, b1, b2; 
  
  //static JLabel l; 

    public static void main(String[] args) {
        JFrame f= new JFrame("Frame Example");
        
        JPanel panel = new JPanel();
        
        panel.setBounds(40,80,200,200);
        panel.setBackground(Color.gray);
        
        JButton b1 = new JButton("Button 1");
        
        b1.setBounds(50,100,80,30);
        b1.setBackground(Color.yellow);
        
        JButton b2 = new JButton("Button 2");
        
        b2.setBounds(100,100,80,30);
        b2.setBackground(Color.green);
        
        panel.add(b1); 
        panel.add(b2);
        
        f.add(panel);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
        Scanner sc = new Scanner(System.in);
        String game = sc.nextLine();
        
        GameFactory gc = new GameFactory();        
        
        gc.getGame(game);
        sc.close();
    }
}

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Menkrep;

import Menkrep.Model.Reference.Reference;
//
import java.io.IOException;
import java.net.URISyntaxException;


import javax.swing.*;
import java.awt.*;

public class App{

    private JPanel mainPanel;
    private JButton button1;

    public String getGreeting(){
        return "Hello world";
    }

    public static void main(String[] args){
        try {
            Reference kartuReference = new Reference();
            System.out.println(kartuReference.getKarakter());
        } catch (IOException e) {
            System.out.println("Error: directory not found");
        }
        System.out.println("Hello world");

        JFrame frame = new JFrame("Menkrep");
        frame.setContentPane(new JPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
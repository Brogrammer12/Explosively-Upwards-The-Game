package main;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.setTitle("Explosively Upwards");
        window.setLocationRelativeTo(null);
        everythingManager window2=new everythingManager();
        window.add(window2);
        window.pack();
        window2.startGameThread();
    }
}

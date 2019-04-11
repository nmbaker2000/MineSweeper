import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mine Sweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MSPanel ms = new MSPanel(10,10, .2);
        frame.getContentPane().add(ms);
        frame.pack();
        frame.setVisible(true);
    }
}

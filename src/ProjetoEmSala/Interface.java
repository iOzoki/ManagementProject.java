package ProjetoEmSala;
import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame {

    public Interface() {
        super("Interface");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Menu");



        JButton buttonOne = new JButton("One");
        JButton buttonTwo = new JButton("Two");
        JButton buttonThree = new JButton("Three");
        JButton buttonFour = new JButton("Four");
        JButton buttonFive = new JButton("Five");
        JButton buttonSix = new JButton("Six");
        JButton buttonSeven = new JButton("Seven");
        JButton buttonEight = new JButton("Eight");
        buttonOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonThree.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFour.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFive.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSix.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSeven.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonEight.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(buttonOne);
        add(buttonTwo);
        add(buttonThree);
        add(buttonFour);
        add(buttonFive);
        add(buttonSix);
        add(buttonSeven);
        add(buttonEight);


        this.setVisible(true);
    }





}

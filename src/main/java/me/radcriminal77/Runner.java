package me.radcriminal77;

import javax.swing.*;
import java.awt.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    private static JLabel label;

    public static void run(String input) {

        /*
        if visual mode is true, make a frame to display output

        TODO: if debug mode is true, this frame will also have a visual representation of the tape with a ^ showing
        TODO: which spot we are on and a button to run each character step by step
        */

        if (Main.visualMode) {
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();

            frame.setTitle("Output");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setSize(new Dimension(400, 400));
            frame.setResizable(false);
            frame.add(panel);

            panel.setLayout(null);

            label = new JLabel();
            label.setBounds(0, 0, 400, 200);
            label.setVerticalAlignment(JLabel.TOP);
            label.setHorizontalAlignment(JLabel.LEFT);
            panel.add(label);

            frame.setVisible(true);
        }

        boolean[] tape = new boolean[64];
        Arrays.fill(tape, false);
        byte cur = 0;

        CharacterIterator it = new StringCharacterIterator(input);

        while (it.current() != CharacterIterator.DONE) {
            switch (it.current()) {
                case '<' -> cur--;
                case '>' -> cur++;
                case '!' -> tape[cur] = !(tape[cur]);
                case '@' -> tape[cur] = getInput();
                case '#' -> display(tape[cur]);
            }

            // move on to next char
            it.next();
        }

    }

    private static void display(Boolean b) {
        if (Main.visualMode) {
            label.setText(label.getText() + b);
        } else {
            System.out.print(b);
        }
    }

    private static boolean getInput() {
        if (Main.visualMode) {
            // TODO: make frame that cannot be closed and will have a button for true and false
            // TODO: close after one of the buttons is pressed
            while (true) {
                try {
                    System.out.print("Enter true or false: ");
                    Scanner scanner = new Scanner(System.in);
                    return scanner.nextBoolean();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input");
                }
            }
        } else {
            while (true) {
                try {
                    System.out.print("Enter true or false: ");
                    Scanner scanner = new Scanner(System.in);
                    return scanner.nextBoolean();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input");
                }
            }
        }
        //return false;
    }
}

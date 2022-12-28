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

        final Display<Boolean> booleanDisplay = new Display<>(label);
        final Display<String> stringDisplay = new Display<>(label);

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
                case '#' -> booleanDisplay.display(tape[cur]);
                case 'r' -> cur = 0;
                case 'c' -> cDisplay(tape, cur);
                default -> stringDisplay.display("Invalid Token: " + it.current());
            }

            // move on to next char
            it.next();
        }

    }

    private static void cDisplay(boolean[] tape, byte cur) {
        int bin = 0;

        // turn an array like {0, 1, 1, 0, 0, 0, 1, 1} to -> (int) 01100011
        int toPow = (int) Math.pow(10, 7);
        for(int i = 0; i < 8; i++) {
            bin += boolToInt(tape[cur + i]) * toPow;
            toPow = toPow / 10;
        }

        char character = (char) binaryToDecimal(bin);
        new Display<Character>(label).display(character);
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

    private static int boolToInt(boolean b) {
        if(b) return 1; else return 0;
    }

    private static int binaryToDecimal(int binaryNumber) {
        // declaration of variables
        int decimalRep = 0, temp, remainder, base = 1;
        temp = binaryNumber;

        // applying the conversion algorithm
        while(temp > 0) {
            // dividing the binary number and storing the rightmost digit in the remainder variable
            remainder = temp % 10;
            decimalRep = decimalRep + (remainder * base);
            temp /= 10;
            base *= 2;
        }
        return decimalRep;
    }
}

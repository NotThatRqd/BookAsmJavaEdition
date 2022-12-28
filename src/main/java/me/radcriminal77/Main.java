package me.radcriminal77;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    public static boolean visualMode = true;

    public static void main(String[] args) {

        // set visual mode here somehow

        if (visualMode) {
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();

            frame.setTitle("Book Asm: Java Edition");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(400, 400));
            frame.setResizable(false);
            frame.add(panel);

            panel.setLayout(null);

            JLabel label = new JLabel("Book Asm");
            label.setBounds(10, 20, 80, 25);
            panel.add(label);

            JTextField inputField = new JTextField(20);
            inputField.setBounds(100, 20, 165, 25);
            panel.add(inputField);

            JButton button = new JButton("Run");
            button.setBounds(10, 60, 70, 25);
            button.setFocusable(false);
            button.addActionListener(event -> {
                Runner.run(inputField.getText());
            });
            panel.add(button);

            frame.setVisible(true);
        } else {

            System.out.println("Enter Book Asm");
            System.out.println("~q to quit");

            Scanner scanner = new Scanner(System.in);  // Create a Scanner object

            while (true) {

                System.out.print("- ");

                String input = scanner.nextLine();  // Read user input

                // quit if input is ~q
                if (input.equals("~q")) {
                    break;
                }

                Runner.run(input);

                System.out.println();

            }
        }

    }

}
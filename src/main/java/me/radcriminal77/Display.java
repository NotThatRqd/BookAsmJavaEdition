package me.radcriminal77;

import javax.swing.*;

public class Display <T> {
    private final JLabel label;

    public Display(JLabel label) {
        this.label = label;
    }

    public void display(T toDisplay) {
        if (Main.visualMode) {
            label.setText(label.getText() + toDisplay.toString());
        } else {
            System.out.print(toDisplay.toString());
        }
    }
}

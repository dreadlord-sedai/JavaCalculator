import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class Calculator implements ActionListener {



    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;

    Font myFont = new Font("Serif", Font.BOLD, 30);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {


        // Set FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // Create a panel
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        // Create number buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        // Set button properties
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;


        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        // Create number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this);
        }

        // Set button positions
        delButton.setBounds(50, 430, 140, 50);
        clrButton.setBounds(210, 430, 140, 50);

        // Create a panel for the buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons to the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Calculator calc = new Calculator();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Handle button clicks
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        // Handle Decimal button
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }

        // Handle Operator buttons
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        // Handle Equals button
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> {
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                }
            }
            textField.setText(String.valueOf(result));
            num1 = result; // Store result for further calculations
        }

        // Handle Clear button
        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        // Handle Delete button
        if (e.getSource() == delButton) {
            String currentText = textField.getText();
            if (!currentText.isEmpty()) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }

        }
    }
}

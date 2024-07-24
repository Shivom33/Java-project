import java.awt.*;
import java.awt.event.*;
public class Calculator extends Frame implements ActionListener {
    TextField display;
    Button[] numberButtons;
    Button addButton, subButton, mulButton, divButton, equalsButton, clearButton;
    Panel panel;
    String currentOperator;
    double currentValue, tempValue;
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setLayout(new BorderLayout());
        setVisible(true);
        display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);
        panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        add(panel, BorderLayout.CENTER);
        numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
            numberButtons[i].addActionListener(this);
            panel.add(numberButtons[i]);
        }
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        equalsButton = new Button("=");
        clearButton = new Button("C");
        addButton.setFont(new Font("Arial", Font.BOLD, 24));
        subButton.setFont(new Font("Arial", Font.BOLD, 24));
        mulButton.setFont(new Font("Arial", Font.BOLD, 24));
        divButton.setFont(new Font("Arial", Font.BOLD, 24));
        equalsButton.setFont(new Font("Arial", Font.BOLD, 24));
        clearButton.setFont(new Font("Arial", Font.BOLD, 24));
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(equalsButton);
        panel.add(clearButton);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                display.setText(display.getText() + command);
            } else if (command.equals("C")) {
                display.setText("");
                currentValue = 0;
                tempValue = 0;
                currentOperator = "";
            } else if (command.equals("=")) {
                tempValue = Double.parseDouble(display.getText());
                calculate();
                display.setText(String.valueOf(currentValue));
                currentOperator = "";
            } else {
                if (!display.getText().isEmpty()) {
                    currentValue = Double.parseDouble(display.getText());
                    currentOperator = command;
                    display.setText("");
                }
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
        } catch (ArithmeticException ex) {
            display.setText("Error");
        }
    }
    private void calculate() {
        switch (currentOperator) {
            case "+":
                currentValue += tempValue;
                break;
            case "-":
                currentValue -= tempValue;
                break;
            case "*":
                currentValue *= tempValue;
                break;
            case "/":
                if (tempValue == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                currentValue /= tempValue;
                break;
        }
    }
    public static void main(String[] args) {
        new Calculator();
    }
}
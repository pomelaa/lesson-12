import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Текстове поле для відображення введених та результату
    JTextField textField;

    // Змінні для зберігання операндів та операції
    double num1, num2, result;
    int operator;

    // Конструктор класу
    public Calculator() {
        // Створення вікна
        setTitle("Калькулятор");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Додавання текстового поля
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        // Створення кнопок та їх додавання
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Виведення вікна
        setVisible(true);
    }

    // Обробник подій для кнопок
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            textField.setText(textField.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(textField.getText());
            operator = getOperatorCode(command);
            textField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            result = performOperation(num1, num2, operator);
            textField.setText(String.valueOf(result));
        } else if (command.equals(".")) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
        }
    }

    // Отримання коду операції
    private int getOperatorCode(String operator) {
        switch (operator) {
            case "+":
                return 1;
            case "-":
                return 2;
            case "*":
                return 3;
            case "/":
                return 4;
            default:
                return 0;
        }
    }

    // Виконання операції
    private double performOperation(double num1, double num2, int operator) {
        switch (operator) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Ділення на нуль неможливе", "Помилка", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        // Запуск програми
        new Calculator();
    }
}

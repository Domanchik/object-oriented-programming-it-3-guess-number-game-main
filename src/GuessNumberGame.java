import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GuessNumberGame extends JFrame {

    private int secretNumber;
    private int attempts;
    private final int MAX_ATTEMPTS = 10;

    private JLabel infoLabel;
    private JLabel attemptsLabel;
    private JTextField inputField;
    private JButton checkButton;
    private JButton newGameButton;

    public GuessNumberGame() {

        setTitle("Угадай число");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Компоненты
        infoLabel = new JLabel("Угадайте число от 1 до 100");
        infoLabel.setForeground(Color.BLUE);

        attemptsLabel = new JLabel("Попыток: 0 / " + MAX_ATTEMPTS);

        inputField = new JTextField(10);

        checkButton = new JButton("Проверить");

        newGameButton = new JButton("Новая игра");

        // Добавление компонентов
        add(infoLabel);
        add(inputField);
        add(checkButton);
        add(newGameButton);
        add(attemptsLabel);

        // Начало игры
        startNewGame();

        // Кнопка проверки
        checkButton.addActionListener(e -> checkNumber());

        // Кнопка новой игры
        newGameButton.addActionListener(e -> startNewGame());

        setVisible(true);
    }

    private void startNewGame() {

        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;

        attempts = 0;

        infoLabel.setText("Угадайте число от 1 до 100");
        infoLabel.setForeground(Color.BLUE);

        attemptsLabel.setText("Попыток: 0 / " + MAX_ATTEMPTS);

        inputField.setText("");

        checkButton.setEnabled(true);
    }

    private void checkNumber() {

        try {

            int userNumber = Integer.parseInt(inputField.getText());

            attempts++;

            if (userNumber < secretNumber) {

                infoLabel.setText("Загаданное число БОЛЬШЕ");
                infoLabel.setForeground(Color.ORANGE);

            } else if (userNumber > secretNumber) {

                infoLabel.setText("Загаданное число МЕНЬШЕ");
                infoLabel.setForeground(Color.RED);

            } else {

                infoLabel.setText("Ты угадал!");
                infoLabel.setForeground(Color.GREEN);

                JOptionPane.showMessageDialog(
                        this,
                        "Поздравляю! Ты угадал число за " + attempts + " попыток!"
                );

                checkButton.setEnabled(false);
            }

            attemptsLabel.setText("Попыток: " + attempts + " / " + MAX_ATTEMPTS);

            // Ограничение попыток
            if (attempts >= MAX_ATTEMPTS && userNumber != secretNumber) {

                JOptionPane.showMessageDialog(
                        this,
                        "Попытки закончились!\nЗагаданное число было: " + secretNumber
                );

                checkButton.setEnabled(false);

                infoLabel.setText("Игра окончена");
                infoLabel.setForeground(Color.BLACK);
            }

        } catch (NumberFormatException ex) {

            infoLabel.setText("Введите число!");
            infoLabel.setForeground(Color.MAGENTA);
        }

        inputField.setText("");
    }

    public static void main(String[] args) {

        new GuessNumberGame();
    }
}
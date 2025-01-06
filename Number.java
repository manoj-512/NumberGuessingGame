import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Number extends JFrame implements ActionListener {
     int MAX_ATTEMPTS = 10;
     int rno;
     int attemptCount;
     JTextField guessField;
     JButton guessButton;
     JLabel feedbackLabel;
     JLabel attemptsLabel;
     JLabel label2;

    public Number() {
        setTitle("Number Guessing Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        Random rand = new Random();
        rno = rand.nextInt(100);
        attemptCount = 0;

        JLabel titleLabel = new JLabel("Number Guessing Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("poppins", Font.BOLD, 24));
        add(titleLabel);

        label2 = new JLabel("Guess the number between 1 to 100", SwingConstants.CENTER);
        add(label2);
        label2.setFont(new Font("poppins",Font.BOLD, 18));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel guessLabel = new JLabel("Enter your guess: ");
        guessLabel.setFont(new Font("arial", Font.BOLD, 16));

        guessField = new JTextField(12);
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        guessButton = new JButton("Check");
        guessButton.setPreferredSize(new Dimension(100, 35)); 
        guessButton.addActionListener(this);
        JButton restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(100, 35));
        restartButton.addActionListener(e -> resetGame());
        
        buttonPanel.add(guessButton);
        buttonPanel.add(restartButton);
        add(buttonPanel);
        
        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts remaining: " + (MAX_ATTEMPTS - attemptCount), SwingConstants.CENTER);
        add(attemptsLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attemptCount++;

            if (guess < rno) {
                feedbackLabel.setText("Numer is Lower --");
            } else if (guess > rno) {
                feedbackLabel.setText("Number is Higher --");
            } else {
                feedbackLabel.setText("Congratulations! You've guessed the number in " + attemptCount + "th attempt.");
                guessButton.setEnabled(false);
            }

            if (attemptCount >= MAX_ATTEMPTS && guess != rno) {
                feedbackLabel.setText("Out of attempts! The number was " + rno + ". Try again by restarting.");
                guessButton.setEnabled(false);
            }

            attemptsLabel.setText("Attempts remaining: " + (MAX_ATTEMPTS - attemptCount));
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }
    private void resetGame() {
    Random rand = new Random();
    rno = rand.nextInt(100); 
    attemptCount = 0; 
    feedbackLabel.setText(""); 
    guessField.setText(""); 
    attemptsLabel.setText("Attempts remaining: " + MAX_ATTEMPTS); 
    guessButton.setEnabled(true); 
    }

    public static void main(String[] args) {
        new Number();
    }
}

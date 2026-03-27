
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CountdownTimer extends JFrame {
    private JLabel timeLabel;
    private JTextField secondsField;
    private JButton startButton;
    private Timer timer;
    private int secondsLeft;
    
    public CountdownTimer() {
        setTitle("Countdown Timer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Time display
        timeLabel = new JLabel("00:00", JLabel.CENTER);
        timeLabel.setFont(new Font("Digital", Font.BOLD, 48));
        add(timeLabel, BorderLayout.CENTER);
        
        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Seconds: "));
        secondsField = new JTextField(5);
        startButton = new JButton("Start");
        
        controlPanel.add(secondsField);
        controlPanel.add(startButton);
        add(controlPanel, BorderLayout.SOUTH);
        
        startButton.addActionListener(e -> startCountdown());
    }
    
    private void startCountdown() {
        try {
            secondsLeft = Integer.parseInt(secondsField.getText());
            if(secondsLeft <= 0) return;
            
            startButton.setEnabled(false);
            updateDisplay();
            
            timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    secondsLeft--;
                    updateDisplay();
                    
                    if(secondsLeft <= 0) {
                        timer.stop();
                        startButton.setEnabled(true);
                        timeLabel.setText("00:00");
                        JOptionPane.showMessageDialog(null, "⏰ TIME'S UP! ⏰");
                    }
                }
            });
            timer.start();
            
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a number!");
        }
    }
    
    private void updateDisplay() {
        int mins = secondsLeft / 60;
        int secs = secondsLeft % 60;
        timeLabel.setText(String.format("%02d:%02d", mins, secs));
    }
    
    public static void main(String[] args) {
        new CountdownTimer().setVisible(true);
    }
}
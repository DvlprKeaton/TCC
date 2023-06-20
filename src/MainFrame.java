import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainFrame extends JFrame {

    private JTextField textField1, textField2;
    private JButton calculateButton, clearButton;
    private JLabel resultLabel, baseLabel;

    public MainFrame() {
        setTitle("Additional Thickness Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField1 = new JTextField();
        textField2 = new JTextField();

        // Set preferred size for the text fields
        textField1.setPreferredSize(new Dimension(200, 30));
        textField2.setPreferredSize(new Dimension(200, 30));

         // Set placeholder text for the inputs
         setPlaceholder(textField1, "Enter base price");
         setPlaceholder(textField2, "Enter additional thickness");

        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");

        // Set preferred size for the buttons
        calculateButton.setPreferredSize(new Dimension(100, 30));
        clearButton.setPreferredSize(new Dimension(100, 30));

        baseLabel = new JLabel("Base Price:");
        resultLabel = new JLabel("Additional Price:");

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(baseLabel);
        inputPanel.add(textField1);
        inputPanel.add(resultLabel);
        inputPanel.add(textField2);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        JPanel outputPanel = new JPanel(new GridLayout(3, 1));
        outputPanel.add(baseLabel);
        outputPanel.add(resultLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        setPreferredSize(new Dimension(400, 200));

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    private void calculateResult() {
        String bPrice = textField1.getText();
        String thick = textField2.getText();

        if (bPrice.isEmpty() || thick.isEmpty()) {
            
            baseLabel.setText("");
            resultLabel.setText("Please enter valid numbers");
            return;
        }
    
        if (!isNumeric(bPrice) || !isNumeric(thick)) {
            
            baseLabel.setText("");
            resultLabel.setText("Please enter valid numbers");
            return;
        }

        double basePrice = Double.parseDouble(bPrice);
        double additionalThickness = Double.parseDouble(thick);
        double additionalPercentage = 0.15;
        double additionalCharge = 0;

        double thicknessIncrement = Math.floor((additionalThickness) / 0.5);
        additionalCharge = thicknessIncrement * additionalPercentage * basePrice;

        resultLabel.setText(String.format("Additional Charge: $%.2f", additionalCharge));
        baseLabel.setText(String.format("Base Price: $%.2f", basePrice));
    }

    private void clearFields(){
        resultLabel.setText("Additional Price:");
        baseLabel.setText("Base Price:");
         // Set placeholder text for the inputs
         setPlaceholder(textField1, "Enter base price");
         setPlaceholder(textField2, "Enter additional thickness");
    }
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}








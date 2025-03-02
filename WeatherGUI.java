
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.swing.*;

public class WeatherGUI extends JFrame {
    public WeatherGUI(){
        super("Weather App");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int WIDTH = 500;
        int HEIGHT = 600;
        setSize(WIDTH, HEIGHT);

        setLocationRelativeTo(null);

        setLayout(null);

        setResizable(false);

        addGUI();
        
    }

    private void addGUI(){

        JTextField question = new JTextField("Where do you live?");
        question.setBounds(75, 0, 251, 50);
        question.setFont(new Font("Dialog", Font.BOLD, 20));
        question.setEditable(false);
        question.setBackground(Color.GRAY);
        add(question);
        JTextField search = new JTextField();


        search.setBounds(50, 50, 351, 45);

        search.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(search);
        JButton button = new JButton();
        button.setText("Search");
        button.setFont(new Font("Dialog", Font.ITALIC, 15));
        button.setBounds(250, 250, 100, 40);
        add(button);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the city name from the text field
                String city = search.getText();
                String newcity = "";
                try {
                    newcity = URLEncoder.encode(city, "UTF-8");
                    newcity.replace("+", " ");
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        
                // Check if the city field is not empty
                if (!city.isEmpty()) {
                    // Fetch the temperature from the backend
                    String temperature = BackendforWeather.main(newcity);
                    
                    // Display the fetched temperature in a dialog box
                    JOptionPane.showMessageDialog(null, temperature);
                } else {
                    // If no city was entered, prompt the user to enter a city
                    JOptionPane.showMessageDialog(null, "Please enter a city.");
                }
            }
        });

        JButton infoButton = new JButton();
        infoButton.setText("i");
        infoButton.setBounds(0, 400, 30, 30);
        add(infoButton);

        infoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(null, "The Product Manager Accelerator Program is designed to support PM professionals through every stage of their career. \nFrom students looking for entry-level jobs to Directors looking to take on a leadership role, our program has helped over hundreds of students fulfill their career aspirations. \n Our Product Manager Accelerator community are ambitious and committed.\n Through our program they have learnt, honed and developed new PM and leadership skills, giving them a strong foundation for their future endeavours.");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoButton.setBackground(UIManager.getColor("control"));
            }
        });
        
        JTextArea name = new JTextArea();
        name.setText("by Vishrut Vanga");
        name.setBounds(350, 550, 120, 40);
        name.setBackground(Color.BLUE);
        add(name);
    }
}
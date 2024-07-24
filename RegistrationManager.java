import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
public class RegistrationManager extends Frame implements ActionListener {
    TextField nameField, emailField;
    CheckboxGroup genderGroup;
    Checkbox male, female;
    Choice countryChoice;
    List hobbyList;
    Button submitButton, displayButton, exportButton;
    TextArea displayArea;
    ArrayList<String> registrationData;
    public RegistrationManager() {
        setTitle("Registration Manager");
        setSize(500, 600);
        setLayout(new FlowLayout());
        setVisible(true);
        Label nameLabel = new Label("Name:");
        nameField = new TextField(20);
        Label emailLabel = new Label("Email:");
        emailField = new TextField(20);
        Label genderLabel = new Label("Gender:");
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        Label countryLabel = new Label("Country:");
        countryChoice = new Choice();
        countryChoice.add("USA");
        countryChoice.add("UK");
        countryChoice.add("India");
        countryChoice.add("Canada");
        Label hobbyLabel = new Label("Hobbies:");
        hobbyList = new List(4, true);
        hobbyList.add("Reading");
        hobbyList.add("Traveling");
        hobbyList.add("Cooking");
        hobbyList.add("Gaming");
        submitButton = new Button("Submit");
        displayButton = new Button("Display");
        exportButton = new Button("Export");
        displayArea = new TextArea(10, 40);
        displayArea.setEditable(false);
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(genderLabel);
        add(male);
        add(female);
        add(countryLabel);
        add(countryChoice);
        add(hobbyLabel);
        add(hobbyList);
        add(submitButton);
        add(displayButton);
        add(exportButton);
        add(displayArea);
        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        exportButton.addActionListener(this);
        registrationData = new ArrayList<>();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String gender = genderGroup.getSelectedCheckbox().getLabel();
            String country = countryChoice.getSelectedItem();
            String[] hobbies = hobbyList.getSelectedItems();

            StringBuilder hobbyString = new StringBuilder();
            for (String hobby : hobbies) {
                hobbyString.append(hobby).append(", ");
            }
            if (hobbyString.length() > 0) {
                hobbyString.setLength(hobbyString.length() - 2); // Remove trailing comma and space
            }

            String registrationInfo = String.format("Name: %s\nEmail: %s\nGender: %s\nCountry: %s\nHobbies: %s\n",
                    name, email, gender, country, hobbyString.toString());

            registrationData.add(registrationInfo);
            displayArea.setText("Registration Successful!");

        } else if (e.getSource() == displayButton) {
            displayArea.setText(""); // Clear the display area
            for (String data : registrationData) {
                displayArea.append(data + "\n----------------------\n");
            }

        } else if (e.getSource() == exportButton) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("registrations.txt"))) {
                for (String data : registrationData) {
                    writer.println(data);
                    writer.println("----------------------");
                }
                displayArea.setText("Data exported to registrations.txt");
            } catch (IOException ex) {
                displayArea.setText("Error exporting data");
            }
        }
    }
    public static void main(String[] args) {
        new RegistrationManager();
    }
}
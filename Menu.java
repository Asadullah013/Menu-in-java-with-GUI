import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    HashMap<String, String> tickets = new HashMap<>();
    private static final String TICKET_FILE = "tickets.txt";

    public Main() {
        // Load tickets from file
        loadTickets();

        // Setting up the main frame
        setTitle("AIR EXPRESS");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Creating a header label
        JLabel headerLabel = new JLabel("AIR EXPRESS", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(new Color(0, 102, 204));

        // Creating a panel for the main menu with GridBagLayout
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createTitledBorder("Main Menu"));
        menuPanel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Adding buttons to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton bookingButton = createStyledButton("Booking");
        menuPanel.add(bookingButton, gbc);

        gbc.gridy++;
        JButton helpButton = createStyledButton("Help");
        menuPanel.add(helpButton, gbc);

        gbc.gridy++;
        JButton viewTicketsButton = createStyledButton("View Tickets");
        menuPanel.add(viewTicketsButton, gbc);

        gbc.gridy++;
        JButton exitButton = createStyledButton("Exit");
        menuPanel.add(exitButton, gbc);

        // Adding action listeners to buttons
        bookingButton.addActionListener(e -> bookingOption());
        helpButton.addActionListener(e -> displayHelp());
        viewTicketsButton.addActionListener(e -> viewTickets());
        exitButton.addActionListener(e -> {
            saveTickets();
            System.exit(0);
        });

        // Adding components to the main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // Adding main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }

    private void bookingOption() {
        // Creating a new frame for booking options
        JFrame bookingFrame = new JFrame("Booking");
        bookingFrame.setSize(400, 300);
        bookingFrame.setLocationRelativeTo(null);

        // Creating a panel for booking options
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Select a Destination"));

        // Adding buttons for each route
        JButton quettaButton = createStyledButton("Quetta");
        JButton islamabadButton = createStyledButton("Islamabad");
        JButton lahoreButton = createStyledButton("Lahore");
        JButton peshawarButton = createStyledButton("Peshawar");
        JButton muzaffarabadButton = createStyledButton("Muzaffarabad");
        JButton returnButton = createStyledButton("Return to Menu");

        // Adding action listeners to buttons
        quettaButton.addActionListener(e -> bookTicket("Quetta"));
        islamabadButton.addActionListener(e -> bookTicket("Islamabad"));
        lahoreButton.addActionListener(e -> bookTicket("Lahore"));
        peshawarButton.addActionListener(e -> bookTicket("Peshawar"));
        muzaffarabadButton.addActionListener(e -> bookTicket("Muzaffarabad"));
        returnButton.addActionListener(e -> bookingFrame.dispose());

        // Adding buttons to the panel
        panel.add(quettaButton);
        panel.add(islamabadButton);
        panel.add(lahoreButton);
        panel.add(peshawarButton);
        panel.add(muzaffarabadButton);
        panel.add(returnButton);

        // Adding panel to the frame
        bookingFrame.add(panel);
        bookingFrame.setVisible(true);
    }

    private void bookTicket(String city) {
        // Creating a new frame for ticket booking details
        JFrame detailsFrame = new JFrame("Booking Details");
        detailsFrame.setSize(400, 300);
        detailsFrame.setLocationRelativeTo(null);

        // Creating a panel for ticket options
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Available Options"));

        String[] routeDetails = getRouteDetails(city);

        // Adding labels for each time option
        for (int i = 0; i < routeDetails.length; i++) {
            int timeOption = i + 1;
            JButton optionButton = createStyledButton(routeDetails[i]);
            optionButton.addActionListener(e -> {
                tickets.put(city, routeDetails[timeOption - 1]);
                JOptionPane.showMessageDialog(detailsFrame, "Ticket booked for: " + routeDetails[timeOption - 1]);
                saveTickets();
                detailsFrame.dispose();
            });
            panel.add(optionButton);
        }

        // Adding panel to the frame
        detailsFrame.add(panel);
        detailsFrame.setVisible(true);
    }

    private String[] getRouteDetails(String city) {
        switch (city) {
            case "Quetta":
                return new String[]{
                        "\nTime = 9:00 AM \nPrice = 3000 \nDate = 02-03-2024.",
                        "\nTime = 2:00 PM \nPrice = 2500 \nDate = 02-03-2024.",
                        "Time = 11:00 PM \nPrice = 3000 \nDate = 02-03-2024."
                };
            case "Islamabad":
                return new String[]{
                        "\nTime = 9:00 AM \nPrice = 3000 \nDate = 02-03-2024.",
                        "\nTime = 2:00 PM \nPrice = 2500 \nDate = 02-03-2024.",
                        "Time = 11:00 PM \nPrice = 3000 \nDate = 02-03-2024."
                };
            case "Lahore":
                return new String[]{
                        "\nTime = 9:00 AM \nPrice = 3000 \nDate = 02-03-2024.",
                        "\nTime = 2:00 PM \nPrice = 2500 \nDate = 02-03-2024.",
                        "Time = 11:00 PM \nPrice = 3000 \nDate = 02-03-2024."
                };
            case "Peshawar":
                return new String[]{
                        "\nTime = 9:00 AM \nPrice = 3000 \nDate = 02-03-2024.",
                        "\nTime = 2:00 PM \nPrice = 2500 \nDate = 02-03-2024.",
                        "Time = 11:00 PM \nPrice = 3000 \nDate = 02-03-2024."
                };
            case "Muzaffarabad":
                return new String[]{
                        "\nTime = 9:00 AM \nPrice = 3000 \nDate = 02-03-2024.",
                        "\nTime = 2:00 PM \nPrice = 2500 \nDate = 02-03-2024.",
                        "Time = 11:00 PM \nPrice = 3000 \nDate = 02-03-2024."
                };
            default:
                return new String[0];
        }
    }

    private void displayHelp() {
        JOptionPane.showMessageDialog(this, "For Any Help Contact +923312636787\nFor Report Mail airexpress@email.com");
    }

    private void viewTickets() {
        if (tickets.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tickets booked yet.");
        } else {
            StringBuilder ticketDetails = new StringBuilder("Your booked tickets:\n");
            for (String route : tickets.keySet()) {
                ticketDetails.append("Route: ").append(route).append(", ").append(tickets.get(route)).append("\n");
            }
            JOptionPane.showMessageDialog(this, ticketDetails.toString());
        }
    }

    private void saveTickets() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TICKET_FILE))) {
            for (Map.Entry<String, String> entry : tickets.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving tickets: " + e.getMessage());
        }
    }

    private void loadTickets() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TICKET_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    tickets.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // File might not exist or other IO error, handle accordingly
            System.err.println("Error loading tickets: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

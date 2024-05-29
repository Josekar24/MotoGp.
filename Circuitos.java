package MotoGp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Circuitos extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/MotoGp";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private JButton volverMenuButton;

    public Circuitos() {
        setTitle("Circuitos de MotoGP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 4, 8, 10));
        panel.setBackground(Color.decode("#fafcff"));
        panel.setPreferredSize(new Dimension(1000, 600)); // Ajustar tamaño del panel

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String query = "SELECT * FROM Circuitos";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String pais = resultSet.getString("pais");
                    double longitud = resultSet.getDouble("longitud");
                    panel.add(createCircuitPanel(nombre, pais, longitud));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Configurar y agregar el botón Volver al Menú
        configurarBotonVolverMenu();

        // Añadir el botón al panel principal
        add(new JScrollPane(panel), BorderLayout.CENTER); // Agregar un panel de desplazamiento para hacer que el panel principal sea desplazable

        // Añadir el botón al fondo de la ventana
        add(volverMenuButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createCircuitPanel(String nombre, String pais, double longitud) {
        JPanel circuitPanel = new JPanel(new BorderLayout());
        circuitPanel.setBackground(Color.white);
        circuitPanel.setBorder(BorderFactory.createLineBorder(Color.red, 5)); // Añadir borde azul

        JLabel nameLabel = new JLabel(nombre);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        circuitPanel.add(nameLabel, BorderLayout.NORTH);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setText("País: " + pais + "\nLongitud: " + longitud + " km");
        detailsArea.setEditable(false);
        detailsArea.setOpaque(false);
        circuitPanel.add(detailsArea, BorderLayout.CENTER);

        return circuitPanel;
    }

    private void configurarBotonVolverMenu() {
        volverMenuButton = new JButton("Volver al Menú");
        volverMenuButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Ajustar el tamaño de la fuente
        volverMenuButton.setBackground(Color.WHITE);
        volverMenuButton.setForeground(Color.BLACK);
        volverMenuButton.setPreferredSize(new Dimension(60, 40)); // Ajustar tamaño del botón

        volverMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Circuitos::new);
    }


    }


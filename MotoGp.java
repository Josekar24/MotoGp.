package MotoGp;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class MotoGp extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/MotoGp";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private JButton volverMenuButton;
    

    public MotoGp() {
        setTitle("Equipos de MotoGP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 3, 20, 20)); // Cambiar a 4 filas y 3 columnas
        panel.setBackground(Color.decode("#fafcff"));
        panel.setPreferredSize(new Dimension(1800, 1000)); // Ajustar tamaño del panel principal

        // Agregar paneles de equipo con nombre y foto
        panel.add(createTeamPanel("Aprilia Racing", "src/Fotosgp/aprilia.png", "src/Fotosgp/aleixespargaro-min.png","src/Fotosgp/maverickvinales-min.png", Color.BLACK));
        panel.add(createTeamPanel("Ducati Lenovo Team", "src/Fotosgp/DUCATI-LENOVO.png", "src/Fotosgp/eneabastianini-min.png","src/Fotosgp/francescobagnaia-min.png" , Color.RED));
        panel.add(createTeamPanel("Gresini Racing MotoGP", "src/Fotosgp/gresini.png", "src/Fotosgp/alexmarquez-min.png","src/Fotosgp/MarcMarquez-min.png", Color.CYAN));
        panel.add(createTeamPanel("LCR Honda", "src/Fotosgp/logolcrhonda.png", "src/Fotosgp/rider-bio_johannzarco-min.png","src/Fotosgp/takaakinakagami-min.png", Color.DARK_GRAY));
        panel.add(createTeamPanel("Monster Energy Yamaha MotoGP", "src/Fotosgp/logo-monster-energy-yamaha.png", "src/Fotosgp/fabioquartararo-min.png","src/Fotosgp/alexrins-min.png", Color.BLUE));
        panel.add(createTeamPanel("Pertamina Enduro VR46 Racing Team", "src/Fotosgp/Pertamina_Enduro_VR46_Racing_Team.png", "src/Fotosgp/fabiodigiannantonio-min.png","src/Fotosgp/marcobezzecchi-min.png", Color.YELLOW));
        panel.add(createTeamPanel("Pramac Racing", "src/Fotosgp/Logo-prima-pramac.png", "src/Fotosgp/FrancoMorbidelli-min.png","src/Fotosgp/JorgeMartin--min.png", Color.MAGENTA));
        panel.add(createTeamPanel("Red Bull GASGAS Tech3", "src/Fotosgp/logogasgas.png", "src/Fotosgp/Pedroacosta-min.png","src/Fotosgp/augustofernandez-min.png", Color.RED));
        panel.add(createTeamPanel("Red Bull KTM Factory Racing", "src/Fotosgp/logo redbullktm.png", "src/Fotosgp/bradbinder-min.png","src/Fotosgp/jackmiller-min.png", Color.LIGHT_GRAY));
        panel.add(createTeamPanel("Repsol Honda Team", "src/Fotosgp/logo honda.png", "src/Fotosgp/lucamarini-min.png","src/Fotosgp/joanmir-min.png", Color.ORANGE));
        panel.add(createTeamPanel("Trackhouse Racing", "src/Fotosgp/logo-trackhouse.png", "src/Fotosgp/raulfernandez-min.png","src/Fotosgp/migueloliveira-min.png", Color.GREEN));

        // Configurar y agregar el botón Volver al Menú
        configurarBotonVolverMenu();
        panel.add(volverMenuButton);

        add(new JScrollPane(panel)); // Agregar un panel de desplazamiento para hacer que el panel principal sea desplazable
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
                new Menu(); // Abre una nueva instancia del menú principal
            }
        });
    }

    private JPanel createTeamPanel(String teamName, String imagePath1, String imagePath2, String image, Color borderColor) {
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.setBackground(Color.white);
        teamPanel.setBorder(BorderFactory.createLineBorder(borderColor, 5));

        JPanel imagesPanel = new JPanel(new GridLayout(1, 3)); 
        imagesPanel.setBackground(Color.white);

        if (imagePath1 != null && imagePath2 != null && image != null) {
            ImageIcon icon1 = new ImageIcon(imagePath1);
            ImageIcon icon2 = new ImageIcon(imagePath2);
            ImageIcon icon3 = new ImageIcon(image);
            JLabel imageLabel1 = new JLabel(new ImageIcon(icon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // Aumentar tamaño de las imágenes
            JLabel imageLabel2 = new JLabel(new ImageIcon(icon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // Aumentar tamaño de las imágenes
            JLabel imageLabel3 = new JLabel(new ImageIcon(icon3.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))); // Aumentar tamaño de las imágenes
            imagesPanel.add(imageLabel1);
            imagesPanel.add(imageLabel2);
            imagesPanel.add(imageLabel3);
        }

        teamPanel.add(imagesPanel, BorderLayout.WEST);

        JLabel nameLabel = new JLabel(teamName);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Aumentar tamaño de la fuente del nombre del equipo
        teamPanel.add(nameLabel, BorderLayout.CENTER);

        JButton button = new JButton("Ver Equipos");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir un nuevo JPanel con los detalles del equipo
                JFrame detailsFrame = new JFrame(teamName + "");
                JPanel detailsPanel = new JPanel(new BorderLayout());

                try {
                    Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                    String query = "SELECT * FROM Pilotos WHERE id_equipo = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, getTeamId(teamName));
                    ResultSet resultSet = statement.executeQuery();

                    // Panel para mostrar los detalles de los pilotos
                    JPanel pilotsPanel = new JPanel(new GridLayout(0, 1));
                    while (resultSet.next()) {
                        String nombrePiloto = resultSet.getString("nombre");
                        String pais = resultSet.getString("pais");
                        int edad = resultSet.getInt("edad");
                        JLabel label = new JLabel(nombrePiloto + " - País: " + pais + ", Edad: " + edad);
                        pilotsPanel.add(label);
                    }
                    detailsPanel.add(new JScrollPane(pilotsPanel), BorderLayout.CENTER);

                    connection.close();
                } catch (SQLException ex) {
                    System.err.println("Error al ejecutar la consulta SQL: " + ex.getMessage());
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }

                detailsFrame.add(detailsPanel);
                detailsFrame.setSize(500, 300); // Tamaño personalizado
                detailsFrame.setLocationRelativeTo(null);
                detailsFrame.getContentPane().setBackground(borderColor); // Establecer el color de fondo
                detailsFrame.setVisible(true);
            }
        });

        teamPanel.add(button, BorderLayout.SOUTH);

        // Ajustar el tamaño preferido del panel de equipo
        teamPanel.setPreferredSize(new Dimension(600, 200)); // Ajusta según tus preferencias

        return teamPanel;
    }

    private int getTeamId(String teamName) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int teamId = -1;
        try {
            connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            String query = "SELECT id_equipo FROM Equipos WHERE LOWER(nombre) = LOWER(?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, teamName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                teamId = resultSet.getInt("id_equipo");
            } else {
                System.out.println("El equipo '" + teamName + "' no se encontró en la base de datos.");
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return teamId;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MotoGp());
    }
}


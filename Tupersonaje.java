package MotoGp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Tupersonaje extends JFrame {
    private JTextField estaturaField, edadField, nacionalidadField, dorsalField, NombreField;
    private JComboBox<String> equiposComboBox;

    public Tupersonaje() {
        setTitle("Equipos de MotoGP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBackground(Color.decode("#fafcff"));
        panel.setPreferredSize(new Dimension(1000, 600));

        panel.add(new JLabel("Estatura (cm):"));
        estaturaField = new JTextField(10);
        panel.add(estaturaField);

        panel.add(new JLabel("Edad:"));
        edadField = new JTextField(10);
        panel.add(edadField);

        panel.add(new JLabel("Nacionalidad:"));
        nacionalidadField = new JTextField(10);
        panel.add(nacionalidadField);

        panel.add(new JLabel("Dorsal:"));
        dorsalField = new JTextField(10);
        panel.add(dorsalField);

        panel.add(new JLabel("Nombre:"));
        NombreField = new JTextField(10);
        panel.add(NombreField);

        panel.add(new JLabel("Equipo:"));
        String[] equipos = {"Aprilia Racing", "Ducati Lenovo Team", "Gresini Racing MotoGP", "LCR Honda",
                "Monster Energy Yamaha MotoGP", "Pertamina Enduro VR46 Racing Team",
                "Pramac Racing", "Red Bull GASGAS Tech3", "Red Bull KTM Factory Racing",
                "Repsol Honda Team", "Trackhouse Racing"};
        equiposComboBox = new JComboBox<>(equipos);
        panel.add(equiposComboBox);

        JButton crearPilotoButton = new JButton("Crear Piloto");
        crearPilotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearPiloto();
            }
        });
        panel.add(crearPilotoButton);

        JButton eliminarPilotosButton = new JButton("Eliminar Todos los Pilotos");
        eliminarPilotosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteAllUsers();
                    JOptionPane.showMessageDialog(Tupersonaje.this, "Todos los pilotos han sido eliminados.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(Tupersonaje.this, "Error al eliminar pilotos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        panel.add(eliminarPilotosButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void crearPiloto() {
        // Obtener los datos del piloto del formulario
        int estatura = Integer.parseInt(estaturaField.getText());
        int edad = Integer.parseInt(edadField.getText());
        String nacionalidad = nacionalidadField.getText();
        int dorsal = Integer.parseInt(dorsalField.getText());
        String nombre = NombreField.getText();
        String equipoSeleccionado = (String) equiposComboBox.getSelectedItem();

        // Mostrar un mensaje con los datos del piloto (puedes quitar esto si prefieres)
        String mensaje = "Piloto creado:\n" +
                "Estatura: " + estatura + " cm\n" +
                "Edad: " + edad + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Dorsal: " + dorsal + "\n" +
                "Equipo seleccionado: " + equipoSeleccionado;
        JOptionPane.showMessageDialog(this, mensaje);

        // Intentar guardar el piloto en la base de datos
        try {
            addUser(estatura, edad, nacionalidad, dorsal, equipoSeleccionado, nombre);
            JOptionPane.showMessageDialog(this, "Piloto añadido con éxito a la base de datos.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir piloto a la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addUser(int estatura, int edad, String nacionalidad, int dorsal, String equipo, String nombre) throws SQLException {
        // Conectar a la base de datos
        String url = "jdbc:mysql://localhost:3306/MotoGp";
        String usuario = "root";
        String contraseña = "";
        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            // Crear y ejecutar la consulta SQL para insertar el piloto en la base de datos
            String sql = "INSERT INTO Creapiloto (estatura, edad, nacionalidad, dorsal, equipo, nombre) " +
                    "VALUES (" + estatura + ", " + edad + ", '" + nacionalidad + "', " + dorsal + ", '" + equipo + "', '" + nombre + "')";
            try (Statement statement = conexion.createStatement()) {
                statement.executeUpdate(sql);
            }
        }
    }

    public void deleteAllUsers() throws SQLException {
        // Conectar a la base de datos
        String url = "jdbc:mysql://localhost:3306/MotoGp";
        String usuario = "root";
        String contraseña = "";
        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            // Crear y ejecutar la consulta SQL para eliminar todos los pilotos de la base de datos
            String sql = "DELETE FROM Creapiloto";
            try (Statement statement = conexion.createStatement()) {
                statement.executeUpdate(sql);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tupersonaje::new);
    }
}

package MotoGp;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Clasificacion extends JFrame {
    private JButton volverMenuButton;

    public Clasificacion() {
        setTitle("Clasificación Pilotos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout()); // Panel principal con BorderLayout
        mainPanel.setBackground(Color.decode("#fafcff"));

        String[] columnas = {"Pilotos", "Pts.", "GP PortugalPOR", "GP ArgentinaARG", "GP Estados UnidosUSA", "GP EspañaESP", "GP FranciaFRA", "GP ItaliaITA", "GP AlemaniaGER", "GP Países BajosNED", "GP Reino UnidoGBR", "GP AustriaAUT", "GP IndiaIND", "GP JapónJPN", "GP IndonesiaINA", "GP AustraliaAUS", "GP TailandiaTHA", "GP MalasiaMAS", "GP CatarQAT", "GP EspañaESP"};
        Object[][] datos = {
            {"Francesco Bagnaia", 467, 25, 0, 0, 25, 0, 25, 20, 25, 20, 25, 0, 16, 0, 25, 20, 20, 16,0,25},
            {"Jorge Martin", 428, 9, 11, 0, 13, 20, 20, 25, 11, 10, 9, 16, 25, 20, 0, 11, 25, 13, 0, 0},
            {"Marco Bezzecchi", 329, 16, 25, 10, 0, 25, 8, 13, 20, 0, 16, 4, 20, 25, 0, 11, 10, 13, 0, 0},
            {"Brad Binder", 290, 10, 0, 3, 20, 10, 11, 0, 13, 16, 20, 0, 2, 13, 0, 10, 13, 16, 0, 13},
            {"Johann Zarco", 221, 15, 20, 9, 0, 16, 16, 16, 0, 7, 3, 13, 6, 10, 0, 0, 25, 6, 0, 16},
            {"Aleix Espargaro", 206, 11, 1, 0, 11, 11, 10, 0, 16, 25, 7, 25, 4, 0, 0, 6, 8, 8, 0, 8},
            {"Maverick Vinales", 204, 20, 4, 13, 0, 0, 4, 0, 0, 11, 10, 20, 11, 8, 0, 16, 5, 0, 5,6},
            {"Luca Marini", 201, 0, 8, 20, 10, 0, 13, 11, 9, 9, 13, 5, 7, 0, 0, 0, 4, 9, 0,7},
            {"Alex Marquez", 177, 12, 16, 0, 8, 0, 0, 9, 10, 0, 11, 10, 5, 0, 0, 0, 7, 0, 20, 0,10},
            {"Fabio Quartararo", 172, 8, 9, 16, 6, 9, 5, 3, 0, 1, 8, 9, 3, 16, 0, 16, 2, 11, 0, 5},
            {"Jack Miller", 163, 15, 10, 0, 16, 1, 9, 10, 0, 8, 1, 8, 0, 2, 0, 9, 9, 0, 8, 0},
            {"Fabio Di Giannantonio", 158, 0, 6, 7, 4, 8, 2, 7, 0, 3, 0, 6, 0, 0, 0, 13, 16, 7, 0,20},
            {"Franco Morbidelli", 102, 2, 13, 8, 5, 6, 6, 4, 7, 2, 5, 2, 1, 9, 0, 0, 0, 5, 0,9},
            {"Marc Marquez", 96, 7, 0, 0, 0, 2, 0, 0, 0, 4, 3, 9, 7, 0, 0, 1, 10, 3, 0,0},
            {"Enea Bastianini", 84, 0, 0, 0, 0, 0, 7, 8, 0, 0, 6, 0, 0, 0, 0, 8, 6, 3, 25,0},
            {"Miguel Oliveira", 76, 3, 0, 11, 0, 0, 0, 6, 0, 13, 0, 11, 10, 4, 0, 4, 3, 0, 0,0},
            {"Augusto Fernandez", 71, 3, 5, 6, 3, 13, 1, 5, 6, 5, 2, 7, 0, 0, 0, 0, 0, 0, 2,0},
            {"Takaaki Nakagami", 56, 4, 3, 1, 7, 7, 3, 2, 8, 0, 0, 1, 0, 5, 0, 5, 0, 2, 0,4},
            {"Alex Rins", 54, 6, 7, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0},
            {"Raul Fernandez", 51, 1, 2, 0, 1, 0, 0, 1, 4, 6, 0, 0, 8, 6, 0, 3, 0, 1, 0,11},
            {"Joan Mir", 26, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 4, 0, 0, 0},
            {"Pedro Acosta", 21, 0, 0, 0, 1, 2, 5, 1, 0 ,1, 5, 0, 0, 0, 1, 1, 1, 1, 1}
        };

        JTable table = new JTable(datos, columnas);
        table.setDefaultRenderer(Object.class, new MiRenderizador());

        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER); // Añadir la tabla al centro del panel principal

        // Configurar el botón de volver al menú
        configurarBotonVolverMenu();
        mainPanel.add(volverMenuButton, BorderLayout.SOUTH);

        add(mainPanel); // Agregar el panel principal al JFrame

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Clasificacion());
    }

    private void configurarBotonVolverMenu() {
        volverMenuButton = new JButton("Volver al Menú");
        volverMenuButton.setFont(new Font("Arial", Font.PLAIN, 18));
        volverMenuButton.setBackground(Color.WHITE);
        volverMenuButton.setForeground(Color.BLACK);
        volverMenuButton.addActionListener(e -> {
            dispose(); // Cierra la ventana actual
        });
    }

    private class MiRenderizador extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        	if (value instanceof Integer) {
        	int val = (Integer) value;
        	if (val == 25) {
        	c.setBackground(Color.RED);
        	} else if (val == 20) {
        	c.setBackground(Color.YELLOW);
        	} else if (val == 16) {
        	c.setBackground(Color.GREEN);
        	} else {
        	c.setBackground(table.getBackground());
        	}
        	} else {
        	c.setBackground(table.getBackground());
        	}
        	return c;
        	}
        	}
        	}



package MotoGp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Menu extends JFrame {
    private JButton motosGPButton, clasificacionButton, circuitosButton, creaTuPilotoButton, salirButton;

    public Menu() {
        setTitle("MotosGp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Crear panel personalizado para la imagen de fondo
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/Fotosgp/portada.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Usamos el layout absoluto para posicionar los botones manualmente
        backgroundPanel.setBounds(0, 0, screenSize.width, screenSize.height); // Ajustamos el tamaño del panel para que coincida con la pantalla
        add(backgroundPanel);

        // Configurar botón "MotosGP"
        motosGPButton = new JButton("MotosGP");
        configurarBoton(motosGPButton, Color.WHITE, Color.BLACK);
        motosGPButton.setBounds((screenSize.width - 200) / 2, (screenSize.height - 50 * 5 - 20 * 4) / 2, 200, 50); // Centrar vertical y horizontalmente
        backgroundPanel.add(motosGPButton);

        // Configurar botón "Clasificación"
        clasificacionButton = new JButton("Clasificación");
        configurarBoton(clasificacionButton, Color.WHITE, Color.BLACK);
        clasificacionButton.setBounds((screenSize.width - 200) / 2, motosGPButton.getY() + 70, 200, 50); // Centrar vertical y horizontalmente
        backgroundPanel.add(clasificacionButton);

        // Configurar botón "Circuitos"
        circuitosButton = new JButton("Circuitos");
        configurarBoton(circuitosButton, Color.WHITE, Color.BLACK);
        circuitosButton.setBounds((screenSize.width - 200) / 2, clasificacionButton.getY() + 70, 200, 50); // Centrar vertical y horizontalmente
        backgroundPanel.add(circuitosButton);

        // Configurar botón "Crea tu piloto"
        creaTuPilotoButton = new JButton("Crea tu piloto");
        configurarBoton(creaTuPilotoButton, Color.WHITE, Color.BLACK);
        creaTuPilotoButton.setBounds((screenSize.width - 200) / 2, circuitosButton.getY() + 70, 200, 50); // Centrar vertical y horizontalmente
        backgroundPanel.add(creaTuPilotoButton);

        // Configurar botón "Salir"
        salirButton = new JButton("Salir");
        configurarBoton(salirButton, Color.WHITE, Color.BLACK);
        salirButton.setBounds((screenSize.width - 200) / 2, creaTuPilotoButton.getY() + 70, 200, 50); // Centrar vertical y horizontalmente
        backgroundPanel.add(salirButton);

        // Ajustar el tamaño del JFrame para que se ajuste al contenido del JPanel
        setSize(screenSize);
        setLocationRelativeTo(null);
        setVisible(true); // Hacer visible el frame

        // Reproducir música al abrir el menú
        SoundManager.reproducirSonido();

        // Configurar acciones de los botones
        configurarAccionesBotones();
    }

    private void configurarBoton(JButton button, Color textColor, Color backgroundColor) {
        button.setFont(new Font("Kode Mono", Font.PLAIN, 20)); // Configurar fuente y tamaño
        button.setBackground(backgroundColor); // Configurar color de fondo
        button.setForeground(textColor); // Configurar color de texto
    }

    private void configurarAccionesBotones() {
        // Configurar acción del botón "MotosGP"
        motosGPButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.detenerSonido(); // Detener la música
                new MotoGp(); // Pasar a la sección MotoGp
                setVisible(false); // Ocultar el menú principal
            }
        });

        // Configurar acción del botón "Clasificación"
        clasificacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.detenerSonido(); // Detener la música
                new Clasificacion(); // Pasar a la sección Clasificación
            }
        });

        // Configurar acción del botón "Circuitos"
        circuitosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.detenerSonido(); // Detener la música
                new Circuitos(); // Pasar a la sección Circuitos
            }
        });

        // Configurar acción del botón "Crea tu piloto"
        creaTuPilotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.detenerSonido(); // Detener la música
                new Tupersonaje(); // Pasar a la sección Crea tu piloto
            }
        });

        // Configurar acción del botón "Salir"
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.detenerSonido(); // Detener la música
                System.exit(0); // Salir de la aplicación cuando se hace clic en el botón "Salir"
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }
}

// Clase SoundManager fuera de Menu para mejor reutilización
class SoundManager {
    private static Clip clip;
    private static boolean sonidoReproduciendose = false;

    public static void reproducirSonido() {
        if (!sonidoReproduciendose) {
            try {
                // Obtener el recurso del archivo de sonido
                URL url = SoundManager.class.getResource("/Fotosgp/Motos.wav");
                if (url == null) {
                    throw new FileNotFoundException("No se pudo encontrar el archivo de sonido.");
                }
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Repetir el sonido de fondo continuamente
                sonidoReproduciendose = true;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public static void detenerSonido() {
        if (sonidoReproduciendose && clip != null) {
            clip.stop();
            clip.close();
            sonidoReproduciendose = false;
        }
    }
}


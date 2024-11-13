package banca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VentanaConFondo_turbio extends JFrame {

    private static final long serialVersionUID = 1L;
    private Imagen[] panelImagenes = new Imagen[4];
    private int imagenActual = 0;
    private JPanel pantallaTextoProgresivo;
    private String nombreJugador;
    private Timer timerTexto;
    private String textoCompleto;
    private StringBuilder textoParcial = new StringBuilder();
    private boolean textoCompletado = false;

    public VentanaConFondo_turbio(String nombreJugador) {
        this.nombreJugador = nombreJugador;

        // Hacer que la ventana sea pantalla completa
        //setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana
        setBounds(400, 400, 800, 600);
        setUndecorated(true); // Elimina los bordes de la ventana, incluyendo los botones de cerrar, minimizar, maximizar
        
        setVisible(true); // Muestra la ventana
        this.setTitle("LoopingGame");

        // Crear la segunda pantalla en negro con texto progresivo
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                
                // Dibujar cada fragmento de texto progresivo
                int y = 80;
                for (String fragmento : textoParcial.toString().split("\\+")) {
                    g.drawString(fragmento, 50, y);
                    y += 20;
                }
                
                if (textoCompletado) {
                    g.setColor(Color.CYAN);
                    g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK);
        add(pantallaTextoProgresivo);

        // Texto completo que debe mostrarse progresivamente
        textoCompleto = "Nombre: " + nombreJugador + "+"
                + "---------------------------------------+"
                + "Pude encontrar todas las pistas que reunen el caso. . . Estas forman una pista la cual dice:+"
                + "+"
                + " ''No creas en todo lo que ves''. . . Esas palabras resonaron fuertemente en mi cabeza. +"
                + "                             ¿Que significan?+"
                + "+"
                + "- Jun; Recibe una llamada de su padre -+"
                + "+"
                + "+"
                + "- Hola, hijo. Llamabra para saber como ibas con el caso que actualmente estas investigando.+"
                + "  Nuevas noticias?+"
                + "+"
                + "Nuevamente yo, de repente, empecé a sentir que mi cabeza daba miles de vueltas. . .+"
                + "Mis parpados pesaban lo que parecian toneladas. Lentamente, mis ojos fueron+"
                + "                              A simplemente oscuridad. . .+"		
                + "---------------------------------------+";

        // Configurar el timer para mostrar el texto progresivamente
        timerTexto = new Timer(50, new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < textoCompleto.length()) {
                    textoParcial.append(textoCompleto.charAt(index));
                    index++;
                    repaint();
                } else {
                    timerTexto.stop();
                    textoCompletado = true;
                    repaint();
                }
            }
        });
        timerTexto.start();

        // Configurar el listener de teclas para mostrar las imágenes
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    // Ocultar pantalla de texto progresivo y mostrar imágenes
                    pantallaTextoProgresivo.setVisible(false);
                    add(panelImagenes[imagenActual]);
                    revalidate();
                    repaint();
                } else {
                    // Cambia de imagen si se presiona una tecla específica
                    switch (e.getKeyChar()) {
                        case '1':
                            cambiarImagen(0);
                            break;
                        case '2':
                            cambiarImagen(1);
                            break;
                        case '3':
                            cambiarImagen(2);
                            break;
                        case '4':
                            cambiarImagen(3);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        // Crear los paneles de imagen para las nuevas imágenes
        panelImagenes[0] = new Imagen("pantallaUno_turbio.png");
        panelImagenes[1] = new Imagen("pantallaDos_turbio.png");
        panelImagenes[2] = new Imagen("pantallaTres_turbio.png");
        panelImagenes[3] = new Imagen("pantallaCuatro_turbio.png");

        setFocusable(true);
    }

    private void cambiarImagen(int indice) {
        remove(panelImagenes[imagenActual]);
        imagenActual = indice;
        add(panelImagenes[imagenActual]);
        revalidate();
        repaint();
    }
}

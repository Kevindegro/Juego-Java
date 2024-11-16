// clase que representa una ventana con fondo y funcionalidad adicional
package banca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VentanaConFondo_turbio extends JFrame {

    private static final long serialVersionUID = 1L;
    // arreglo de paneles de imagen que representan diferentes fondos
    private Imagen[] panelImagenes = new Imagen[4];
    private int imagenActual = 0; // indice de la imagen actualmente visible
    private JPanel pantallaTextoProgresivo; // panel para mostrar texto progresivo
    private String nombreJugador; // nombre del jugador
    private Timer timerTexto; // temporizador para el texto progresivo
    private String textoCompleto; // texto completo que se mostrara de forma progresiva
    private StringBuilder textoParcial = new StringBuilder(); // texto que se ha mostrado hasta ahora
    private boolean textoCompletado = false; // indica si el texto ha sido completamente mostrado

    public VentanaConFondo_turbio(String nombreJugador) {
        this.nombreJugador = nombreJugador;

        // configurar la ventana
        setBounds(400, 400, 800, 600);
        setUndecorated(true); // quitar bordes de la ventana
        setVisible(true); // hacer visible la ventana
        this.setTitle("LoopingGame");

        // crear el panel para mostrar texto progresivo
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // llenar el fondo de color negro
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                // configurar el color y fuente del texto
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                
                // dibujar cada linea del texto progresivo
                int y = 80;
                for (String fragmento : textoParcial.toString().split("\\+")) {
                    g.drawString(fragmento, 50, y);
                    y += 20;
                }
                
                // si el texto esta completo, mostrar mensaje para continuar
                if (textoCompletado) {
                    g.drawString("pulsa cualquier tecla para continuar...", 50, 350);
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK);
        add(pantallaTextoProgresivo);

        // definir el texto completo que se mostrara
        textoCompleto = "nombre: " + nombreJugador + "+"
                + "---------------------------------------+"
                + "pude encontrar todas las pistas que reunen el caso. estas forman una pista la cual dice:+"
                + "'no creas en todo lo que ves'. esas palabras resonaron fuertemente en mi cabeza.+"
                + "                             ¿que significan?+"
                + "- jun; recibe una llamada de su padre -+"
                + "- hola, hijo. llamaba para saber como ibas con el caso que actualmente estas investigando.+"
                + "  nuevas noticias?+"
                + "nuevamente yo, de repente, empecé a sentir que mi cabeza daba vueltas. mis parpados pesaban toneladas. lentamente, mis ojos fueron a la oscuridad.+"
                + "---------------------------------------+";

        // configurar temporizador para mostrar el texto progresivamente
        timerTexto = new Timer(50, new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // agregar caracter al texto parcial hasta completar el texto
                if (index < textoCompleto.length()) {
                    textoParcial.append(textoCompleto.charAt(index));
                    index++;
                    repaint();
                } else {
                    // detener el temporizador y marcar texto como completado
                    timerTexto.stop();
                    textoCompletado = true;
                    repaint();
                }
            }
        });
        timerTexto.start();

        // configurar listener para manejar teclas presionadas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    // ocultar el texto progresivo y mostrar imagenes
                    pantallaTextoProgresivo.setVisible(false);
                    add(panelImagenes[imagenActual]);
                    revalidate();
                    repaint();
                } else {
                    // cambiar imagen segun la tecla presionada
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

        // inicializar paneles con diferentes imagenes
        panelImagenes[0] = new Imagen("pantallaUno_turbio.png");
        panelImagenes[1] = new Imagen("pantallaDos_turbio.png");
        panelImagenes[2] = new Imagen("pantallaTres_turbio.png");
        panelImagenes[3] = new Imagen("pantallaCuatro_turbio.png");

        setFocusable(true);
    }

    // metodo para cambiar la imagen visible
    private void cambiarImagen(int indice) {
        remove(panelImagenes[imagenActual]);
        imagenActual = indice;
        add(panelImagenes[imagenActual]);
        revalidate();
        repaint();
    }
}

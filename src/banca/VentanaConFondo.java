package banca;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;

public class VentanaConFondo extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Array de paneles de imagen, que representan las pantallas a mostrar.
    private Imagen[] panelImagenes = new Imagen[4];
    
    // Array de puntos interactivos (PuntosRojos) que se colocarán sobre las imágenes.
    private PuntoRojo[] puntosRojos = new PuntoRojo[6];
    
    // Índice de la imagen actualmente mostrada.
    private int imagenActual = 0;
    
    // Índice del punto interactivo actualmente activo.
    private int puntoActual = 0;
    
    // Pantalla inicial que muestra un texto introductorio.
    private JPanel pantallaInicial;
    
    // Pantalla que muestra un texto progresivo letra por letra.
    private JPanel pantallaTextoProgresivo;
    
    // Nombre del jugador, utilizado en los textos.
    private String nombreJugador;
    
    // Temporizador para animar el texto progresivo.
    private Timer timerTexto;
    
    // Texto completo que se mostrará progresivamente.
    private String textoCompleto;
    
    // Contenedor para el texto mostrado progresivamente.
    private StringBuilder textoParcial = new StringBuilder();
    
    // Bandera que indica si el texto progresivo ya fue mostrado completamente.
    private boolean textoCompletado = false;

    public VentanaConFondo(String nombreJugador) {
        this.nombreJugador = nombreJugador; // Guardar el nombre del jugador para personalización.

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 400); // Definir tamaño y posición inicial de la ventana.
        this.setTitle("LoopingGame");

        // Configuración de la pantalla inicial.
        pantallaInicial = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK); // Fondo negro.
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE); // Texto blanco.
                g.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente estándar.
                
                // Dibujar texto en líneas separadas, describiendo la situación inicial del juego.
                g.drawString("Un nuevo día encuentra tus ojos, " + nombreJugador + ".", 50, 80);
                g.drawString("Parado frente a una tabla donde yacen apoyados", 50, 100);
                g.drawString("todos los textos y fotos que has logrado recolectar,", 50, 120);
                g.drawString("comúnmente llamados 'pistas' en tu trabajo.", 50, 140);
                g.setColor(Color.CYAN);
                g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);
            }
        };
        pantallaInicial.setBackground(Color.BLACK); // Establecer el fondo de la pantalla inicial.
        add(pantallaInicial, BorderLayout.CENTER); // Añadir al contenedor principal.

        // Configuración de la pantalla de texto progresivo.
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK); // Fondo negro.
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.CYAN); // Texto en color cian.
                g.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente estándar.
                
                // Dibujar el texto progresivo línea por línea.
                int y = 80; // Posición inicial vertical.
                for (String fragmento : textoParcial.toString().split("\\+")) { // Separar texto por el carácter '+'.
                    g.drawString(fragmento, 50, y);
                    y += 20; // Incrementar posición para cada nueva línea.
                }

                // Mostrar mensaje adicional cuando el texto progresivo se complete.
                if (textoCompletado) {
                    g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK);

        // Texto completo a mostrar de forma progresiva, con '+' para separar líneas.
        textoCompleto = "Nombre: " + nombreJugador + "+" +
                        "---------------------------------------+" +
                        "Fecha: 11/02/-Desconocido-+" +
                        "---------------------------------------+" +
                        "Tutor materno: Fallecido+" +
                        "Tutor paterno: Fallecido+" +
                        "---------------------------------------+" +
                        "Características: Ojos almendra claros+" +
                        "1.80m, pelo rubio y holgado.+" +
                        "---------------------------------------+";

        // Configuración del temporizador para animar el texto progresivo.
        timerTexto = new Timer(50, new ActionListener() {
            private int index = 0; // Índice del carácter actual.

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < textoCompleto.length()) {
                    textoParcial.append(textoCompleto.charAt(index)); // Agregar carácter al texto parcial.
                    index++;
                    repaint(); // Actualizar la pantalla.
                } else {
                    timerTexto.stop(); // Detener el temporizador cuando se completa el texto.
                    textoCompletado = true; // Marcar el texto como completado.
                    repaint();
                }
            }
        });

        // Configuración de eventos de teclado para cambiar entre pantallas y manejar la interacción.
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaInicial.isVisible()) {
                    // Transición de la pantalla inicial a la pantalla de texto progresivo.
                    pantallaInicial.setVisible(false);
                    add(pantallaTextoProgresivo, BorderLayout.CENTER);
                    timerTexto.start(); // Iniciar la animación del texto progresivo.
                    revalidate();
                    repaint();
                } else if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    // Transición de la pantalla de texto progresivo a la pantalla de imágenes.
                    pantallaTextoProgresivo.setVisible(false);
                    add(panelImagenes[imagenActual], BorderLayout.CENTER);
                    revalidate();
                    repaint();
                } else {
                    // Cambiar imágenes según la tecla presionada (1 a 4).
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

        // Crear los paneles de imagen y asociarlos con puntos interactivos.
        panelImagenes[0] = new Imagen("pantallaUno.png");
        panelImagenes[1] = new Imagen("pantallaDos.png");
        panelImagenes[2] = new Imagen("pantallaTres.png");
        panelImagenes[3] = new Imagen("pantallaCuatro.png");

        for (Imagen panel : panelImagenes) {
            panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Añadir bordes vacíos para espaciar.
        }

        // Crear y configurar puntos interactivos sobre imágenes específicas.
        puntosRojos[0] = new PuntoRojo(panelImagenes[2], 300, 100, this);
        puntosRojos[1] = new PuntoRojo(panelImagenes[0], 100, 50, this);
        // Más puntos agregados de forma similar...

        setFocusable(true); // Hacer la ventana receptiva al teclado.
    }

    // Método para mostrar el siguiente punto interactivo.
    public void mostrarSiguientePunto() {
        if (puntoActual < puntosRojos.length - 1) {
            puntosRojos[puntoActual].setVisible(false); // Ocultar punto actual.
            puntoActual++; // Avanzar al siguiente punto.
            puntosRojos[puntoActual].setVisible(true); // Mostrar el siguiente punto.
            revalidate();
            repaint();
        } else {
            // Cuando se han completado todos los puntos, abrir una nueva ventana.
            abrirVentanaConFondo();
        }
    }

    // Método para abrir una nueva ventana al completar los puntos.
    private void abrirVentanaConFondo() {
        this.dispose(); // Cerrar la ventana actual.
        new VentanaConFondo_turbio(nombreJugador).setVisible(true); // Abrir la nueva ventana.
    }

    // Método para cambiar entre imágenes.
    private void cambiarImagen(int indice) {
        remove(panelImagenes[imagenActual]); // Quitar la imagen actual.
        imagenActual = indice; // Actualizar el índice de la imagen.
        add(panelImagenes[imagenActual], BorderLayout.CENTER); // Mostrar la nueva imagen.
        revalidate();
        repaint();
    }
}

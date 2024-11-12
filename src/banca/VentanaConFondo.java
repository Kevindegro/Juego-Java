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
    private Imagen[] panelImagenes = new Imagen[4];
    private PuntoRojo[] puntosRojos = new PuntoRojo[6];
    private int imagenActual = 0;
    private int puntoActual = 0;
    private JPanel pantallaInicial;
    private JPanel pantallaTextoProgresivo;
    private String nombreJugador;
    private Timer timerTexto;
    private String textoCompleto;
    private StringBuilder textoParcial = new StringBuilder();
    private boolean textoCompletado = false;

    public VentanaConFondo(String nombreJugador) {
        this.nombreJugador = nombreJugador;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 400);
        //setBounds(200, 200, 3000, 1000);
        this.setTitle("LoopingGame");

        // Crear pantalla inicial
        pantallaInicial = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                g.drawString("Un nuevo día encuentra tus ojos, " + nombreJugador + ".", 50, 80);
                g.drawString("Parado frente a una tabla donde yacen apoyados", 50, 100);
                g.drawString("todos los textos y fotos que has logrado recolectar,", 50, 120);
                g.drawString("comúnmente llamados 'pistas' en tu trabajo. Estas,", 50, 140);
                g.drawString("formando un bucle y alegoría sin gracia...", 50, 160);
                g.drawString("Bucle.... Como la vida monotona que te habia tocado vivir", 50, 180);
                g.drawString("Mientras lo que seguía esperando dentro tuyo f l o r e c í a . . .", 50, 200);
                g.drawString("Qué difícil me resulta explicar lo aburrido que es todo esto.", 50, 230);
                g.drawString("Nadie se cansa de ver. Nadie se cansa de oír", 50, 250);
                g.drawString("Lo que antes sucedió, vuelve a suceder;", 50, 270);
                g.drawString("lo que antes se hizo, vuelve a hacerse", 50, 290);
                g.setColor(Color.CYAN);
                g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);
            }
        };
        pantallaInicial.setBackground(Color.BLACK);
        add(pantallaInicial, BorderLayout.CENTER);

     // Crear la segunda pantalla en negro con texto progresivo
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                
                // Separar el texto en fragmentos y dibujar cada fragmento
                int y = 80; // Posición inicial vertical
                for (String fragmento : textoParcial.toString().split("\\+")) {
                    g.drawString(fragmento, 50, y);
                    y += 20; // Incremento para cada cambio de línea (cada "+")
                }
                
                if (textoCompletado) {
                    g.setColor(Color.CYAN);
                    g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK);

        // Texto completo que debe mostrarse progresivamente
        textoCompleto = "Nombre: " + nombreJugador + "+"
                + "---------------------------------------+"
                + "Fecha: 11/02/-Desconocido-+"
                + "---------------------------------------+"
                + "Tutor materno: Fallecido+"
                + "Tutor paterno: Fallecido+"
                + "---------------------------------------+"
                + "Características: Ojos almendra claros+"
                + "1.80m, pelo rubio y holgado.+"
                + "---------------------------------------+";

        // Configurar el timer para agregar letras de a poco
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


        // Configurar el listener de teclas para cambiar entre pantallas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaInicial.isVisible()) {
                    // Oculta la pantalla inicial y muestra la pantalla de texto progresivo
                    pantallaInicial.setVisible(false);
                    add(pantallaTextoProgresivo, BorderLayout.CENTER);
                    timerTexto.start();  // Iniciar la animación de texto progresivo
                    revalidate();
                    repaint();
                } else if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    // Oculta la pantalla de texto progresivo y muestra el contenido del juego
                    pantallaTextoProgresivo.setVisible(false);
                    add(panelImagenes[imagenActual], BorderLayout.CENTER);
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

        // Crear los paneles de imagen
        panelImagenes[0] = new Imagen("pantallaUno.png");
        panelImagenes[1] = new Imagen("pantallaDos.png");
        panelImagenes[2] = new Imagen("pantallaTres.png");
        panelImagenes[3] = new Imagen("pantallaCuatro.png");

        for (Imagen panel : panelImagenes) {
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        }

        // Crear puntos rojos en posiciones específicas
        puntosRojos[0] = new PuntoRojo(panelImagenes[2], 300, 100, this);
        puntosRojos[1] = new PuntoRojo(panelImagenes[0], 100, 50, this);
        puntosRojos[2] = new PuntoRojo(panelImagenes[3], 50, 100, this);
        puntosRojos[3] = new PuntoRojo(panelImagenes[1], 200, 200, this);
        puntosRojos[4] = new PuntoRojo(panelImagenes[2], 300, 50, this);
        puntosRojos[5] = new PuntoRojo(panelImagenes[3], 150, 150, this);

        for (int i = 0; i < puntosRojos.length; i++) {
            puntosRojos[i].setVisible(i == 0);
            puntosRojos[i].getParentPanel().add(puntosRojos[i]);
        }

        setFocusable(true);
    }

    public void mostrarSiguientePunto() {
        if (puntoActual < puntosRojos.length - 1) {
            puntosRojos[puntoActual].setVisible(false);
            puntoActual++;
            puntosRojos[puntoActual].setVisible(true);
            revalidate();
            repaint();
            if(puntoActual == 1) { ImagenPrimeraPista.mostrarVentanaPrimeraPista(); }
            if(puntoActual == 2) { ImagenPrimeraPista.VentanaSegundaPista.mostrarVentanaSegundaPista(); }
            if(puntoActual == 3) { ImagenPrimeraPista.VentanaTerceraPista.mostrarVentanaTerceraPista(); }
            if(puntoActual == 4) { ImagenPrimeraPista.VentanaCuartaPista.mostrarVentanaCuartaPista(); }
            if(puntoActual == 5) { ImagenPrimeraPista.VentanaQuintaPista.mostrarVentanaQuintaPista(); }
            
        } else {
            // Si el último PuntoRojo ha sido clickeado, abrir VentanaConFondo normal
            puntosRojos[puntoActual].setVisible(false);
            abrirVentanaConFondo();
        }
    }

    private void abrirVentanaConFondo() {
        // Cerrar la ventana actual
        this.dispose();
        
        // Crear una nueva instancia de VentanaConFondo
        VentanaConFondo_turbio nuevaVentana = new VentanaConFondo_turbio(nombreJugador);
        nuevaVentana.setVisible(true);
    }

    private void cambiarImagen(int indice) {
        remove(panelImagenes[imagenActual]);
        imagenActual = indice;
        add(panelImagenes[imagenActual], BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
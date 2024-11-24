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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class VentanaConFondo_turbio extends JFrame {

    private static final long serialVersionUID = 1L; //es un identificador unico
    private Imagen[] panelImagenes = new Imagen[4]; //otro array de imagenes
    private int imagenActual = 0; //otro detector de imagenes
    private JPanel pantallaTextoProgresivo; //el mismo texto progresivo
    private String nombreJugador; //importamos devuelta el nombre
    private Timer timerTexto; //otro timer
    private String textoCompleto; //devuelta el detector de textos
    private StringBuilder textoParcial = new StringBuilder(); //va a ver si el texto sigue (con el intex)
    private boolean textoCompletado = false; //si el texto esta completo va a ponerse e ntrue

    public VentanaConFondo_turbio(String nombreJugador) {
        this.nombreJugador = nombreJugador; //robamos el nombreJugador del menu xd

        VentanaTextitos.mostrarVentana(); // Llama a la ventana con campos de texto
        setExtendedState(JFrame.MAXIMIZED_BOTH); //maximiza la ventana
        setUndecorated(true); //elimina los bordes de la ventana
        setVisible(true); //muestra la ventana
        this.setTitle("LoopingGame"); //nombre del juego puesto arriba

        // Crear la segunda pantalla en negro con texto progresivo
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //dibujar otra vez todo
                super.paintComponent(g); //llamar a g para dibujar el fondo negro
                g.setColor(Color.BLACK); //fondo negro
                g.fillRect(0, 0, getWidth(), getHeight()); //fondo negro
                g.setColor(Color.CYAN); //ahora pasamos a color CYAAAAAAAN
                g.setFont(new Font("Arial", Font.PLAIN, 16)); //arial de fuente base

                // "dibujar" cada fragmento de texto progresivo
                int y = 80; //elegir la posición
                for (String fragmento : textoParcial.toString().split("\\+")) { //cada que haya + salto de línea
                    g.drawString(fragmento, 50, y);
                    y += 20; //sumamos 20 para que no se superpongan los textos
                }

                if (textoCompletado) { //esto aparece si el texto es completado
                    g.setColor(Color.CYAN);
                    g.drawString("Pulsa cualquier tecla para continuar...", 300, 1000);
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK); //todo a negro otra vez
        add(pantallaTextoProgresivo); //poner el texto ahora sí

        textoCompleto = "Nombre: " + nombreJugador + "+" +
                "20/11/2024 Ultima pista: - - -" + "+" +
                "---------------------------------------+" +
                "Pude encontrar todas las pistas que reúnen el caso... Esas forman una pista la cual dice:+" +
                "'No creas en todo lo que ves'... Esas palabras resonaron fuertemente en mi cabeza... "+"+"+
                "----------------- Recibes una llamada de tu padre -----------------" + "+" +
                "- Hola, hijo. Llamé para saber cómo ibas con el caso que actualmente estás investigando.+" +
                " Nuevas noticias?" + "+" +
                "- Nuevamente yo, de repente, empecé a sentir que mi cabeza daba miles de vueltas...+" +
                " Mis párpados pesaban lo que parecían toneladas. Lentamente, mis ojos fueron a simplemente oscuridad... "+"+"+
                "---------------------------------------+" +
                "Pulsa cualquier tecla para continuar";

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
        timerTexto.start(); //EMPEZAR

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    pantallaTextoProgresivo.setVisible(false);
                    add(panelImagenes[imagenActual]);
                    revalidate();
                    repaint();
                } else {
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

        // Inicializar imágenes
        panelImagenes[0] = new Imagen("pantallaUno_turbio.png");
        panelImagenes[1] = new Imagen("pantallaDos_turbio.png");
        panelImagenes[2] = new Imagen("pantallaTres_turbio.png");
        panelImagenes[3] = new Imagen("pantallaCuatro_turbio.png");

     // Añadir el botón al panelImagenes[1] cuando se muestre esa imagen
        JButton boton = new JButton(" ");
        boton.setBounds(160, 450, 80, 200); // Ubicación del botón (ajustar como necesites)
        boton.setOpaque(false); // Elimina el fondo del botón
        boton.setContentAreaFilled(false); // Hace el botón completamente transparente
        boton.setBorderPainted(false); // Quita los bordes del botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar una alerta
                JOptionPane.showMessageDialog(
                    VentanaConFondo_turbio.this, // Foco en la ventana principal
                    "¡¡¡DESPIERTA, NADA ES REAL!!!", 
                    "Easter egg", 
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Enfocar la ventana principal nuevamente después del diálogo
                VentanaConFondo_turbio.this.requestFocusInWindow();
            }
        });

        // Este método se llama cuando se cambia a la imagen 1
        panelImagenes[1].setLayout(null); // Usamos un layout nulo para posicionar el botón
        panelImagenes[1].add(boton); // Añadimos el botón al panel de la imagen
        panelImagenes[1].revalidate();
        panelImagenes[1].repaint();


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

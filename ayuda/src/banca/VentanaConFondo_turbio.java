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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
        //hacer que la ventana sea pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH); //maximiza la ventana
        setUndecorated(true); //elimina los bordes de la ventana
        //incluyendo los botones de - cuadrado x
        
        setVisible(true); //muestra la ventana
        this.setTitle("LoopingGame"); //nombre del juego puesto arriba

        //crear la segunda pantalla en negro con texto progresivo
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //dibujar otra vez todo
                super.paintComponent(g); //llamar a g para dibujar el fondo negro
                g.setColor(Color.BLACK); //fondo negro
                g.fillRect(0, 0, getWidth(), getHeight()); //fondo negro
                g.setColor(Color.CYAN); //ahora pasamos a color CYAAAAAAAN
                g.setFont(new Font("Arial", Font.PLAIN, 16));//arial de fuente base
                
                //"dibujar" cada fragmento de texto progresivo
                int y = 80; //elegir la posciion
                for (String fragmento : textoParcial.toString().split("\\+")) {//cada que haya
                	//+ salto de linea
                    g.drawString(fragmento, 50, y);
                    y += 20; //sumamos 20 para que cunado este + se baje y no se superpongan los
                    //textos
                }
                
                if (textoCompletado) {//esto aparece si el texto
                	//es completado, cuando esta variable sea true entonces aparece
                	//"pulsa cualquier tecla para continuar"
                    g.setColor(Color.CYAN);
                    g.drawString("Pulsa cualquier tecla para continuar...", 300, 1000);
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK); //todo a negro otra vez
        add(pantallaTextoProgresivo); //poner el texto ahora si

        //texto completo que debe mostrarse progresivamente
        textoCompleto = "Nombre: " + nombreJugador + "+"
        		+ "20/11/2024 Ultima pista: - - -" + "+"
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
                + "---------------------------------------+"
                + "Pulsa cualquier tecla para continuar";

        //configurar el timer para mostrar el texto progresivamente
        timerTexto = new Timer(50, new ActionListener() {
            private int index = 0; //ese es el index del private de antes

            @Override
            public void actionPerformed(ActionEvent e) { //llamar a e para el timer
                if (index < textoCompleto.length()) { //si es mas grande entonces sigue
                    textoParcial.append(textoCompleto.charAt(index));
                    index++; //va sumando para mostrar las siguientes letras
                    repaint(); //actualizxa
                } else {
                    timerTexto.stop(); //para el timer cuando complete todo el texto
                    textoCompletado = true; //aca va a mostrar el puilsa cualquier tecla
                    repaint(); //actualizar
                }
            }
        });
        timerTexto.start(); //EMPEZARRR

        //configurar el listener de teclas para mostrar las imágenes
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    //ocultar pantalla de texto progresivo y mostrar imagenes
                    pantallaTextoProgresivo.setVisible(false);
                    add(panelImagenes[imagenActual]); //nuevo array para nuevas pantallas
                    revalidate();
                    repaint(); //actualizar todo esto
                } else {
                    // Cambiar imagen con un switch
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

        // ponerle al array todos los nombres de las imagenes para llamarlas
        panelImagenes[0] = new Imagen("pantallaUno_turbio.png");
        panelImagenes[1] = new Imagen("pantallaDos_turbio.png");
        panelImagenes[2] = new Imagen("pantallaTres_turbio.png");
        panelImagenes[3] = new Imagen("pantallaCuatro_turbio.png");

        setFocusable(true);
    }
    
    private void cambiarImagen(int indice) {//este es el codigo que se usa para cambiar las
        remove(panelImagenes[imagenActual]);//imagenes una vez que presiones un numero (1 al 4)
        imagenActual = indice; //pones el valor de la imagen actual al indice (que es lo que cambia)
        add(panelImagenes[imagenActual]); //agarras la imagen
        revalidate(); //y revalidas
        repaint(); //y actualizas
    }

}
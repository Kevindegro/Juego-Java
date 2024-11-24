package banca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
//import para usar musica aca
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class VentanaConFondo extends JFrame {

    private static final long serialVersionUID = 1L;//es un identificador unico
    private Imagen[] panelImagenes = new Imagen[4];//es el array que se usa para las 4 imagenes
    private PuntoRojo[] puntosRojos = new PuntoRojo[6];//el array que se usa para los 4 puntos
    private int imagenActual = 0;//la imagen que actualmente se ve
    private int puntoActual = 0;//el punto que actualmente se ve
    private JPanel pantallaInicial;//la pantalla que aparece despues del menu (el texto)
    private JPanel pantallaTextoProgresivo; //el texto progresivo
    private String nombreJugador;//la variable sacada del menu para tu nombre
    private Timer timerTexto;//el timer que se usa para el texto progresivo
    private String textoCompleto;//una vez que el texto se muestre, esta variable se usa
    private StringBuilder textoParcial = new StringBuilder(); //va consturyendo el texto
    private boolean textoCompletado = false;//se pone en true al terminar el texto progresivo

    public VentanaConFondo(String nombreJugador) {
        this.nombreJugador = nombreJugador; //esto saca la variable del nombre jugador y la
        //pega en este codigo con el private de antes

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que tenga la - (cuadrado) y x
        setBounds(200, 200, 600, 400);//tamañño de la pantalla
        //setBounds(200, 200, 3000, 1000);
        this.setTitle("LoopingGame"); //nombre del juego que aparece en la barrita de arriba

        // Crear pantalla inicial
        pantallaInicial = new JPanel() { //array para la pantalla inicial
            @Override
            protected void paintComponent(Graphics g) {//estos son los cosos para dibujar
                super.paintComponent(g); //se llama a g (que es una funcion) para empezar a "dibujar"
                g.setColor(Color.BLACK); //se setea un color
                g.fillRect(0, 0, getWidth(), getHeight()); //hace el fondo negro
                g.setColor(Color.WHITE); //sete a color blanco
                g.setFont(new Font("Arial", Font.PLAIN, 16)); //el formato de letras
                g.drawString("Un nuevo día encuentra tus ojos, " + nombreJugador + ".", 50, 80); //primer texto
                g.drawString("Parado frente a una tabla donde yacen apoyados", 50, 100); //xd
                g.drawString("todos los textos y fotos que has logrado recolectar,", 50, 120); //se van moviendo las posiciones
                g.drawString("comúnmente llamados 'pistas' en tu trabajo. Estas,", 50, 140); //para que no se superpongan
                g.drawString("formando un bucle y alegoría sin gracia...", 50, 160);//los textos unos con otros
                g.drawString("Bucle.... Como la vida monotona que te habia tocado vivir", 50, 180);
                g.drawString("Mientras lo que seguía esperando dentro tuyo f l o r e c í a . . .", 50, 200);
                g.drawString("Qué difícil me resulta explicar lo aburrido que es todo esto.", 50, 230);
                g.drawString("Nadie se cansa de ver. Nadie se cansa de oír", 50, 250);
                g.drawString("Lo que antes sucedió, vuelve a suceder;", 50, 270);
                g.drawString("lo que antes se hizo, vuelve a hacerse", 50, 290);
                g.setColor(Color.CYAN); //una vez terminado, se pone en CYAAAAAAAN y pulsas cualquier
                g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);//letr apar acontinuar
            }
        };
        pantallaInicial.setBackground(Color.BLACK); //coloreamos todo a negro otra vez
        add(pantallaInicial, BorderLayout.CENTER); 

     // Crear la segunda pantalla en negro con texto progresivo
        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //usamos otra vez los graficos
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());//todo lo mismo que antes
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                
                //esto indica que se va a separar los textos cada que aparezca el "+"
                int y = 80; //este va a ser el inicio de la escritura
                for (String fragmento : textoParcial.toString().split("\\+")) {
                    g.drawString(fragmento, 50, y);
                    y += 20; //incrementar Y para cada cambio de linea (cada "+")
                }
                
                if (textoCompletado) { //cuando el texto este completado
                    g.setColor(Color.CYAN); //se setea a CYAAAAAAAAAN y se continua con cualquier
                    g.drawString("Pulsa cualquier tecla para continuar...", 50, 350);
                }//letra :v
            }
        };
        
        pantallaTextoProgresivo.setBackground(Color.BLACK); //seteas a negro

        //texto completo que debe mostrarse progresivamente
        textoCompleto = "Nombre: " + nombreJugador + "+"
                + "---------------------------------------+"
                + "Fecha: 11/02/-Desconocido-+"
                + "---------------------------------------+" //se hacen saltos de linea con el "+"
                + "Tutor materno: Fallecido+"
                + "Tutor paterno: Fallecido+"
                + "---------------------------------------+"
                + "Características: Ojos almendra claros+"
                + "1.80m, pelo rubio y holgado.+"
                + "---------------------------------------+";

        //este timer agrega letras cada milisegundo
        timerTexto = new Timer(50, new ActionListener() {
            private int index = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) { //usa a e (una funcion)
                if (index < textoCompleto.length()) {//si el texto completo aun no termina
                    textoParcial.append(textoCompleto.charAt(index));
                    index++; //se va sumando las letras
                    repaint();
                } else {
                    timerTexto.stop(); //sino, termina todo
                    textoCompletado = true; //se activa que se completo el texto
                    repaint(); //repaint para que aparezca el "pulsa cualquier tecla para continuar"
                } //y tambien para las nuevas letras cuando esta activo
            }
        });

        

        //configurar el listener de teclas para cambiar entre pantallas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pantallaInicial.isVisible()) {
                    //oculta la pantalla inicial y muestra la pantalla de texto progresivo
                    pantallaInicial.setVisible(false);
                    add(pantallaTextoProgresivo, BorderLayout.CENTER);
                    timerTexto.start();  //iniciar la "animacion" de texto progresivo
                    revalidate();
                    repaint();
                } else if (pantallaTextoProgresivo.isVisible() && textoCompletado) {
                    //oculta la pantalla de texto progresivo y muestra el contenido del juego
                    pantallaTextoProgresivo.setVisible(false); //setea en falso para quitarlo
                    add(panelImagenes[imagenActual], BorderLayout.CENTER);
                    revalidate();
                    repaint(); //repinta todo para que se actualice 
                } else {
                    //cambias de imagen mediante un switch
                    switch (e.getKeyChar()) {
                        case '1':
                            cambiarImagen(0); //xd
                            break;
                        case '2':
                            cambiarImagen(1); //xd
                            break;
                        case '3':
                            cambiarImagen(2); //xd
                            break;
                        case '4':
                            cambiarImagen(3); //xd
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        //llenar el array private con imagenes
        panelImagenes[0] = new Imagen("pantallaUno.png");
        panelImagenes[1] = new Imagen("pantallaDos.png");
        panelImagenes[2] = new Imagen("pantallaTres.png");
        panelImagenes[3] = new Imagen("pantallaCuatro.png");

        for (Imagen panel : panelImagenes) { //poner los bordes de las imagenes
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        }

        //crear los puntos rojos (las posiciones no funcionan MATENME)
        puntosRojos[0] = new PuntoRojo(panelImagenes[2], 300, 100, this);
        puntosRojos[1] = new PuntoRojo(panelImagenes[0], 100, 50, this);
        puntosRojos[2] = new PuntoRojo(panelImagenes[3], 50, 100, this);
        puntosRojos[3] = new PuntoRojo(panelImagenes[1], 200, 200, this);
        puntosRojos[4] = new PuntoRojo(panelImagenes[2], 300, 50, this);
        puntosRojos[5] = new PuntoRojo(panelImagenes[3], 150, 150, this);

        for (int i = 0; i < puntosRojos.length; i++) {
            puntosRojos[i].setVisible(i == 0); //compara el i con los niveles de puntosRojos
            puntosRojos[i].getParentPanel().add(puntosRojos[i]); //para ver que punto va 
        }

        setFocusable(true); //lo hace visible
    }

    public void mostrarSiguientePunto() { //muestra los siguientes
        if (puntoActual < puntosRojos.length - 1) { //si el maximo - 1 es mayor que el actual
            puntosRojos[puntoActual].setVisible(false); //saca el actual para pasar al siguiente
            puntoActual++;//que es este
            puntosRojos[puntoActual].setVisible(true); //y ahora si lo vuelve a mostrar
            revalidate();//se tiene que revalidar 
            repaint(); //y se tiene que repintar para mostrarlo en la pantalla
            if(puntoActual == 1) { ImagenPrimeraPista.mostrarVentanaPrimeraPista(); }
            if(puntoActual == 2) { ImagenPrimeraPista.VentanaSegundaPista.mostrarVentanaSegundaPista(); }
            if(puntoActual == 3) { ImagenPrimeraPista.VentanaTerceraPista.mostrarVentanaTerceraPista(); }
            if(puntoActual == 4) { ImagenPrimeraPista.VentanaCuartaPista.mostrarVentanaCuartaPista(); }
            if(puntoActual == 5) { ImagenPrimeraPista.VentanaQuintaPista.mostrarVentanaQuintaPista(); }
            //eso es lo que te revela las pistas que te sueltan los puntos
        } else {
            //si el ultimo punto es clickeado:
            puntosRojos[puntoActual].setVisible(false); //chau todos los puntos
            abrirVentanaConFondo(); //llamamos a la funcion para cerrar la ventana
        }
    }

    private void abrirVentanaConFondo() {
        //Cerrar la ventana actual
        this.dispose();
        
        // Crear una nueva instancia de VentanaConFondo
        VentanaConFondo_turbio nuevaVentana = new VentanaConFondo_turbio(nombreJugador);
        nuevaVentana.setVisible(true); //abrimos la nueva ventana, la mostramos y le damos
        //nuestro nombre
    }

    private void cambiarImagen(int indice) {
        remove(panelImagenes[imagenActual]); 
        imagenActual = indice; 
        add(panelImagenes[imagenActual], BorderLayout.CENTER); //añadimos nuevos paneles
        revalidate(); //revalidamos
        repaint(); //actualizamos
    }
}
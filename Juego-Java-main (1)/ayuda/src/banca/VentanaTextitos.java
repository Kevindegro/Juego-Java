package banca;

import javax.swing.*;

import menu.VentanaPrincipal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTextitos extends JFrame {

    private JPanel pantallaTextoProgresivo;//hacemos los privates con Jpanel para el timer
    private StringBuilder textoParcial = new StringBuilder(); //esto va a servir para el texto que se va escribiendo
    private String textoCompleto;
    private Timer timerTexto;
    private boolean textoCompletado = false;//detecta si estan las cosas completas
    
    public VentanaTextitos() {
        setTitle("Ventana con Campos de Texto");
        setSize(600, 400); // tamaño reducido de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); //layout para colocar componentes manualmente

        //crar el panel con la imagen de fondo
        JPanel panelConImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon("renglones.png"); // Reemplaza con la ruta de tu imagen
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConImagen.setLayout(null);
        panelConImagen.setBounds(0, 0, 600, 400); //tamaño papu
        add(panelConImagen); //añadir paneles papuuu

        //titulo
        JLabel tituloPrincipal = new JLabel("Recolectaste tus pistas? INSERTE PISTAS:");
        tituloPrincipal.setBounds(100, 20, 400, 20); //esta es la cosa que te deja escribir las pistas que recolectaste
        tituloPrincipal.setFont(new Font("Arial", Font.BOLD, 14));//fuente de letra
        panelConImagen.add(tituloPrincipal);//sin esto no se abre la ventana basciamente

        //etiqueta y campo de texto 1
        JLabel etiqueta1 = new JLabel("Entidad superior imaginaria (busca la primera pista)");
        etiqueta1.setBounds(100, 60, 400, 20); //primera pista "jefe"
        panelConImagen.add(etiqueta1); //usamos etiquetas como si fuera una ID incopiable

        JTextField campoTexto1 = new JTextField(); //estos son los lugares donde escribis
        campoTexto1.setBounds(100, 80, 150, 20); //tambien idenficados con una "ID" unica
        panelConImagen.add(campoTexto1);

        //detectar Enter en el campo de texto 1
        campoTexto1.addActionListener(e -> {
            String textoIngresado = campoTexto1.getText().trim();
            if ("Jefe".equalsIgnoreCase(textoIngresado) ||"jefe".equalsIgnoreCase(textoIngresado)) { //si pones la pista salta esto
                iniciarTextoProgresivo("Cuando desperte, vi que estaba en un lugar extraño+" +
                        "similar a donde sucedio el homicidio solo...+" +
                        "Que esta vez se veia todo... Grotesco...+" +
                        "---------------------------------------+" +
                        "A mi lado encontre un juego de mesa sobre detectives.+" +
                        "En mis ojos, pasaban visiones de pequeño con mi padre.+" +
                        "Fue un juego creado por el en donde se inspiro en su trabajo " +
                        "para que podamos divertirnos juntos.+");
            } else {
                mostrarMensajeError();
            } //este mensaje de error es super default por si es que te equivocas la palabra
        });

        //etiqueta y campo de texto 2
        JLabel etiqueta2 = new JLabel("¿Quien te ayuda a despejar tu mente con charlas?");
        etiqueta2.setBounds(100, 120, 400, 20); //Maria man, osea xd
        panelConImagen.add(etiqueta2);

        JTextField campoTexto2 = new JTextField();
        campoTexto2.setBounds(100, 140, 150, 20);
        panelConImagen.add(campoTexto2); //ID para la pista de Maria

        //detectar Enter en el campo de texto 2
        campoTexto2.addActionListener(e -> { //Aca la funcion para que despierte 
            String textoIngresado = campoTexto2.getText().trim();// compara si textoIngresado es igual al texto que se supone que tien que estar en el campo 2 entonces:
            if ("Maria".equalsIgnoreCase(textoIngresado)) { //sucede esto
                iniciarTextoProgresivo("Este mundo es muy extraño... Me siento perseguido, observado.+" +
                        "Por alguna razon, siento que ya estuve en este lugar.+" +
                        "Se siente tan familiar.+" +
                        "---------------------------------------+" +
                        "Esa maldita voz... Pense que se habia ido para siempre!!+"+
                        "Pero la estoy escuchando de nuevo..."+
                        "Tengo que buscar a Maria, +"+
                        "seguro sabra que hacer... Siempre lo hace.");
            } else {
                mostrarMensajeError(); //mensaje defauuuuult
            }
        });

        //etiqueta y campo de texto 3 esto ya es basicamente lo mismo de antes
        JLabel etiqueta3 = new JLabel("¿Esta bien tu cabeza? ¿Por que no tomas unas pastillas?");
        etiqueta3.setBounds(100, 180, 400, 20);
        panelConImagen.add(etiqueta3);

        JTextField campoTexto3 = new JTextField();
        campoTexto3.setBounds(100, 200, 150, 20);
        panelConImagen.add(campoTexto3);

        // Detectar Enter en el campo de texto 3
        campoTexto3.addActionListener(e -> {
            String textoIngresado = campoTexto3.getText().trim();
            if ("Trevicta".equalsIgnoreCase(textoIngresado) || "trevicta".equalsIgnoreCase(textoIngresado)) {
                iniciarTextoProgresivo("Vi una silueta corriendo apenas me vio.+" +
                        "Empeze a seguirla por alguna razon, los dos estuvimos"+
                        " en una persecucion por toda la ciudad+" +
                        "hasta que entro a una particular casa...+" +
                        "             MI CASA+" +
                        "Que hace entrando ahi?!?!?+" +
                        "Recorriendo mi propia casa en busca de esa cosa, yazco en frente de la+" +
                        "Ultima puerta de mi casa... Mi habitacion.+"+
                        "Un gran reflejo en el espejo era lo que me llamaba para mirarlo directamente.+"+
                        "Era el. Estaba en el espejo... ?+"+
                        "Mis parpados nuevamente pesaron y de a poco lo entendi.");
            } else {
                mostrarMensajeError();
            }
        });

        // Etiqueta y campo de texto 4
        JLabel etiqueta4 = new JLabel("La curiosidad premia al gato ¿Tu padre murio en?");
        etiqueta4.setBounds(100, 240, 400, 20);
        panelConImagen.add(etiqueta4);

        JTextField campoTexto4 = new JTextField();
        campoTexto4.setBounds(100, 260, 150, 20);
        panelConImagen.add(campoTexto4);

        // Detectar Enter en el campo de texto 4
        campoTexto4.addActionListener(e -> {
            String textoIngresado = campoTexto4.getText().trim();
            if ("persecucion".equalsIgnoreCase(textoIngresado) || "persecucion".equalsIgnoreCase(textoIngresado) || "persecución".equalsIgnoreCase(textoIngresado) || "Persecución".equalsIgnoreCase(textoIngresado)) {
                iniciarTextoProgresivo("Fuimos nosotros :)+"+
           "                 +"+
           "El espejo refleja una persona la cual asesino a otra.+"+
           "Curiosamente, el caso se hace moderadamente famoso y+"+
           "Un detective es mandado a investigar tu caso... Mediante "+
           "cabos que has dejado sueltos, "+
           "te conviertes en un asesino"+
           " +"+
           "buscado por una persona... +"+ 
           " +" +
           "Bien, detective. . . Estas buscando a un asesino e+" +
           "intentando resolver un homicidio! Tu jefe te ha encargado+"+
           "Esta mision de alto nivel... No falles a pesar de tus+"+
           "Dolores de cabeza. Exitos <3+"+
           "-Maria+"+
           " +"+
           "Loopingame+"+
           " +"+
            "                             Fin");
            } else {
                mostrarMensajeError();
            }
        });
    }

    private void iniciarTextoProgresivo(String texto) { //aca usamos las mismas funciones de
        JFrame frame = new JFrame("Texto Progresivo"); //texto progresivo que usamos para la
        frame.setSize(600, 400);//primera parte del juego
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //pintamos todo de negro
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight()); //ponemos las coords
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.PLAIN, 16));

                int y = 80;
                for (String fragmento : textoParcial.toString().split("\\+")) { //cada vez
                    g.drawString(fragmento, 50, y);//que pone + hace salto de linea y +20y
                    y += 20;
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK); //setear black
        frame.add(pantallaTextoProgresivo, BorderLayout.CENTER); //poner en el centro

        textoCompleto = texto; //vamos a detectar el texto
        textoParcial = new StringBuilder(); //compara con el string que esta completandose en el momento
        textoCompletado = false; //si esta completo esto es true

        timerTexto = new Timer(50, new ActionListener() { //timer puesto en 50
            private int index = 0; //index son las letras

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < textoCompleto.length()) { //compara las letras con texto completo
                    textoParcial.append(textoCompleto.charAt(index));
                    index++; //si no esta completo, suma una letra mas
                    pantallaTextoProgresivo.repaint(); //repinta para que aparezca
                } else {
                    timerTexto.stop(); //para y pone textoCompletado en true
                    textoCompletado = true;
                    pantallaTextoProgresivo.repaint(); //repinta para actualizar por si acaso
                }
            }
        });

        timerTexto.start();
        frame.setVisible(true);
    }

    private void mostrarMensajeError() { //error de cuando fallas la pista xd
        JOptionPane.showMessageDialog(this,
                "Texto incorrecto. Intenta de nuevo.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarVentana() { 
        SwingUtilities.invokeLater(() -> {
            VentanaTextitos ventana = new VentanaTextitos(); //ventanatextitos
            ventana.setVisible(true); //usamos esto para que funcione el lugar donde pones las
        });//pistas
    }

    public static void main(String[] args) {
        mostrarVentana();//todo el codigo crack
    }
}

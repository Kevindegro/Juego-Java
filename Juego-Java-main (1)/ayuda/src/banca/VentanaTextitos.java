package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTextitos extends JFrame {

    private JPanel pantallaTextoProgresivo;
    private StringBuilder textoParcial = new StringBuilder();
    private String textoCompleto;
    private Timer timerTexto;
    private boolean textoCompletado = false;

    public VentanaTextitos() {
        setTitle("Ventana con Campos de Texto");
        setSize(600, 400); // Tamaño reducido de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); // Layout nulo para colocar componentes manualmente

        // Crear el panel con la imagen de fondo
        JPanel panelConImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon("renglones.png"); // Reemplaza con la ruta de tu imagen
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConImagen.setLayout(null);
        panelConImagen.setBounds(0, 0, 600, 400);
        add(panelConImagen);

        // Título principal
        JLabel tituloPrincipal = new JLabel("Recolectaste tus pistas? INSERTE PISTAS:");
        tituloPrincipal.setBounds(100, 20, 400, 20);
        tituloPrincipal.setFont(new Font("Arial", Font.BOLD, 14));
        panelConImagen.add(tituloPrincipal);

        // Etiqueta y campo de texto 1
        JLabel etiqueta1 = new JLabel("Entidad superior imaginaria (busca la primera pista)");
        etiqueta1.setBounds(100, 60, 400, 20);
        panelConImagen.add(etiqueta1);

        JTextField campoTexto1 = new JTextField();
        campoTexto1.setBounds(100, 80, 150, 20);
        panelConImagen.add(campoTexto1);

        // Detectar Enter en el campo de texto 1
        campoTexto1.addActionListener(e -> {
            String textoIngresado = campoTexto1.getText().trim();
            if ("Jefe".equalsIgnoreCase(textoIngresado)) {
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
            }
        });

        // Etiqueta y campo de texto 2
        JLabel etiqueta2 = new JLabel("¿Quien te ayuda a despejar tu mente con charlas?");
        etiqueta2.setBounds(100, 120, 400, 20);
        panelConImagen.add(etiqueta2);

        JTextField campoTexto2 = new JTextField();
        campoTexto2.setBounds(100, 140, 150, 20);
        panelConImagen.add(campoTexto2);

        // Detectar Enter en el campo de texto 2
        campoTexto2.addActionListener(e -> {
            String textoIngresado = campoTexto2.getText().trim();
            if ("Maria".equalsIgnoreCase(textoIngresado)) {
                iniciarTextoProgresivo("Este mundo es muy extraño... Me siento perseguido, observado.+" +
                        "Por alguna razon, siento que ya estuve en este lugar.+" +
                        "Se siente tan familiar.+" +
                        "---------------------------------------+" +
                        "Esa maldita voz... Pense que se habia ido para siempre!!+"+
                        "Pero la estoy escuchando de nuevo..."+
                        "Tengo que buscar a Maria, +"+
                        "seguro sabra que hacer... Siempre lo hace.");
            } else {
                mostrarMensajeError();
            }
        });

        // Etiqueta y campo de texto 3
        JLabel etiqueta3 = new JLabel("¿Esta bien tu cabeza? ¿Por que no tomas unas pastillas?");
        etiqueta3.setBounds(100, 180, 400, 20);
        panelConImagen.add(etiqueta3);

        JTextField campoTexto3 = new JTextField();
        campoTexto3.setBounds(100, 200, 150, 20);
        panelConImagen.add(campoTexto3);

        // Detectar Enter en el campo de texto 3
        campoTexto3.addActionListener(e -> {
            String textoIngresado = campoTexto3.getText().trim();
            if ("Travicta".equalsIgnoreCase(textoIngresado) || "travicta".equalsIgnoreCase(textoIngresado)) {
                iniciarTextoProgresivo("Vi una silueta corriendo apenas me vio.+" +
                        "Empeze a seguirla por alguna razon, los dos estuvimos en una persecucion por toda la ciudad+" +
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
        JLabel etiqueta4 = new JLabel("¿T u  p a d r e  f a l l e c i o?");
        etiqueta4.setBounds(100, 240, 400, 20);
        panelConImagen.add(etiqueta4);

        JTextField campoTexto4 = new JTextField();
        campoTexto4.setBounds(100, 260, 150, 20);
        panelConImagen.add(campoTexto4);

        // Detectar Enter en el campo de texto 4
        campoTexto4.addActionListener(e -> {
            String textoIngresado = campoTexto4.getText().trim();
            if ("Si".equalsIgnoreCase(textoIngresado)) {
                iniciarTextoProgresivo("Fuimos nosotros :)+"+
            "                             Fin");
            } else {
                mostrarMensajeError();
            }
        });
    }

    private void iniciarTextoProgresivo(String texto) {
        JFrame frame = new JFrame("Texto Progresivo");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        pantallaTextoProgresivo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.PLAIN, 16));

                int y = 80;
                for (String fragmento : textoParcial.toString().split("\\+")) {
                    g.drawString(fragmento, 50, y);
                    y += 20;
                }
            }
        };
        pantallaTextoProgresivo.setBackground(Color.BLACK);
        frame.add(pantallaTextoProgresivo, BorderLayout.CENTER);

        textoCompleto = texto;
        textoParcial = new StringBuilder();
        textoCompletado = false;

        timerTexto = new Timer(50, new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < textoCompleto.length()) {
                    textoParcial.append(textoCompleto.charAt(index));
                    index++;
                    pantallaTextoProgresivo.repaint();
                } else {
                    timerTexto.stop();
                    textoCompletado = true;
                    pantallaTextoProgresivo.repaint();
                }
            }
        });

        timerTexto.start();
        frame.setVisible(true);
    }

    private void mostrarMensajeError() {
        JOptionPane.showMessageDialog(this,
                "Texto incorrecto. Intenta de nuevo.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            VentanaTextitos ventana = new VentanaTextitos();
            ventana.setVisible(true);
        });
    }

    public static void main(String[] args) {
        mostrarVentana();
    }
}

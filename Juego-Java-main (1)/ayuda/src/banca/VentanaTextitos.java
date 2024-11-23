package banca;

import javax.swing.*;
import java.awt.*;

public class VentanaTextitos extends JFrame {

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

        // Etiqueta y campo de texto 2
        JLabel etiqueta2 = new JLabel("¿Esta bien tu cabeza? ¿Por que no tomas unas pastillas?");
        etiqueta2.setBounds(100, 120, 400, 20);
        panelConImagen.add(etiqueta2);

        JTextField campoTexto2 = new JTextField();
        campoTexto2.setBounds(100, 140, 150, 20);
        panelConImagen.add(campoTexto2);

        // Etiqueta y campo de texto 3
        JLabel etiqueta3 = new JLabel("¿Quien te ayuda a despejar tu mente con charlas?");
        etiqueta3.setBounds(100, 180, 400, 20);
        panelConImagen.add(etiqueta3);

        JTextField campoTexto3 = new JTextField();
        campoTexto3.setBounds(100, 200, 150, 20);
        panelConImagen.add(campoTexto3);

        // Etiqueta y campo de texto 4
        JLabel etiqueta4 = new JLabel("¿T u  p a d r e  f a l l e c i o?");
        etiqueta4.setBounds(100, 240, 400, 20);
        panelConImagen.add(etiqueta4);

        JTextField campoTexto4 = new JTextField();
        campoTexto4.setBounds(100, 260, 150, 20);
        panelConImagen.add(campoTexto4);
    }

    public static void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            VentanaTextitos ventana = new VentanaTextitos();
            ventana.setVisible(true);
        });
    }
}

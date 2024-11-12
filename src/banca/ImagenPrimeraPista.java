package banca;

import javax.swing.*;
import java.awt.*;

public class ImagenPrimeraPista extends JFrame {

    private static final long serialVersionUID = 1L;

    public ImagenPrimeraPista() {
        setTitle("Primera Pista");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400); // Tamaño de la ventana (ajústalo según lo que necesites)

        // Cargar la imagen "primeraPista.png" desde el paquete "banca"
        ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/primeraPista.png"));

        // Crear un JLabel para mostrar la imagen
        JLabel labelImagen = new JLabel(imagenPista);
        labelImagen.setHorizontalAlignment(JLabel.CENTER);
        labelImagen.setVerticalAlignment(JLabel.CENTER);

        // Agregar la imagen a la ventana
        add(labelImagen, BorderLayout.CENTER);
        
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    // Método estático para mostrar la ventana de la primera pista
    public static void mostrarVentanaPrimeraPista() {
        SwingUtilities.invokeLater(() -> {
        	ImagenPrimeraPista ventana = new ImagenPrimeraPista();
            ventana.setVisible(true);
        });
    }

    // Clase interna para la segunda ventana (segunda pista)
    public static class VentanaSegundaPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaSegundaPista() {
            setTitle(" - Segunda Pista - ");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400); // Tamaño de la ventana (ajústalo según lo que necesites)

            // Cargar la imagen "segundaPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/segundaPista.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            // Agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        }

        // Método estático para mostrar la ventana de la segunda pista
        public static void mostrarVentanaSegundaPista() {
            SwingUtilities.invokeLater(() -> {
                VentanaSegundaPista ventana = new VentanaSegundaPista();
                ventana.setVisible(true);
            });
        }
    }
    
    
    
    
    
    // Clase interna para la tercera ventana (tercera pista)
    public static class VentanaTerceraPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaTerceraPista() {
            setTitle(" NO BORRAR LA IMAGEN - tercera pista - ");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400); // Tamaño de la ventana (ajústalo según lo que necesites)

            // Cargar la imagen "segundaPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/terceraPista.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            // Agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        }

        // Método estático para mostrar la ventana de la segunda pista
        public static void mostrarVentanaTerceraPista() {
            SwingUtilities.invokeLater(() -> {
            	VentanaTerceraPista ventana = new VentanaTerceraPista();
                ventana.setVisible(true);
            });
        }
    }
    
    
    
    // Clase interna para la cuarta ventana (cuarta pista)
    public static class VentanaCuartaPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaCuartaPista() {
            setTitle(" NO BORRAR LA IMAGEN - cuarta pista - ");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400); // Tamaño de la ventana (ajústalo según lo que necesites)

            // Cargar la imagen "segundaPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/cuartaPista.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            // Agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        }

        // Método estático para mostrar la ventana de la segunda pista
        public static void mostrarVentanaCuartaPista() {
            SwingUtilities.invokeLater(() -> {
            	VentanaCuartaPista ventana = new VentanaCuartaPista();
                ventana.setVisible(true);
            });
        }
    }
    
    
    
    
    // Clase interna para la quinta ventana (quinta pista)
    public static class VentanaQuintaPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaQuintaPista() {
            setTitle(" NO BORRAR LA IMAGEN - cuarta pista - ");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400); // Tamaño de la ventana (ajústalo según lo que necesites)

            // Cargar la imagen "segundaPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/quintaPista.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            // Agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        }

        // Método estático para mostrar la ventana de la segunda pista
        public static void mostrarVentanaQuintaPista() {
            SwingUtilities.invokeLater(() -> {
            	VentanaQuintaPista ventana = new VentanaQuintaPista();
                ventana.setVisible(true);
            });
        }
    }
}

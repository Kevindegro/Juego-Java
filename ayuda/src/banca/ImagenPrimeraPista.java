package banca;

import javax.swing.*;
import java.awt.*;
//este es el codigo que va a mostrar todas las pistas a futuro cuando presiones los puntos
//funciona con todos los mismos codigos
//cada pista es algo que te va a ayudar al final del juego
public class ImagenPrimeraPista extends JFrame {

    private static final long serialVersionUID = 1L; //es un identificador unico

    public ImagenPrimeraPista() {
        setTitle("NO BORRAR LA IMAGEN - Primera Pista -"); //primera pista nombre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //las podes cerrar si queres
        setSize(400, 400); //tamaño de la ventana de las pistas

        //la llama desde la carpeta
        ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/primeraPista.png"));

        //crear un JLabel para mostrar la imagen
        JLabel labelImagen = new JLabel(imagenPista);
        labelImagen.setHorizontalAlignment(JLabel.CENTER);
        labelImagen.setVerticalAlignment(JLabel.CENTER);

        //agregar la imagen a la ventana
        add(labelImagen, BorderLayout.CENTER);
        
        setLocationRelativeTo(null); //centrar la ventana en la pantalla
    }

    //este metodo es para mostrar la ventana de la pista
    public static void mostrarVentanaPrimeraPista() {
        SwingUtilities.invokeLater(() -> {
        	ImagenPrimeraPista ventana = new ImagenPrimeraPista();
            ventana.setVisible(true);
        });
    }

    // empezamos con los codigos de la segunda pista
    public static class VentanaSegundaPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaSegundaPista() {
            setTitle(" - Segunda Pista - "); //nombre de la pista
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // - cuadrado x
            setSize(400, 400); //tamaño de la segunda pista

            //cargamos para poder insertarla a la pista
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/segundaPista.png"));

            //JLabel para mostrarla
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            //agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); //centrar la ventana en la pantalla (de donde aparece)
        }

        //esto es para mostrar la segunda pista
        public static void mostrarVentanaSegundaPista() {
            SwingUtilities.invokeLater(() -> {
                VentanaSegundaPista ventana = new VentanaSegundaPista();
                ventana.setVisible(true);
            });
        }
    }
    
    
    
    
    
    //el codigo se repite para las 5 pistas
    public static class VentanaTerceraPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaTerceraPista() {
            setTitle(" NO BORRAR LA IMAGEN - tercera pista - "); //NO BORRES LA IMAGEN!!!!
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400); // Tamaño de la ventana (ajústalo según lo que necesites)

            // Cargar la imagen "terceraPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/terceraPista.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            // Agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        }

        // Método estático para mostrar la ventana de la tercera pista
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

            // Cargar la imagen "cuartaPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/cuartaPista.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            // Agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        }

        // Método estático para mostrar la ventana de la cuarta pista
        public static void mostrarVentanaCuartaPista() {
            SwingUtilities.invokeLater(() -> {
            	VentanaCuartaPista ventana = new VentanaCuartaPista();
                ventana.setVisible(true);
            });
        }
    }
    
    
    
    
    //quinta pista
    public static class VentanaQuintaPista extends JFrame {

        private static final long serialVersionUID = 1L;

        public VentanaQuintaPista() {
            setTitle("- cuarta pista - ");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400); //tamaño de la ventana

            //cargar la imagen "quintaPista.png" desde el paquete "banca"
            ImageIcon imagenPista = new ImageIcon(getClass().getResource("/banca/quintaPista.png"));

            //crear un JLabel para mostrar la imagen
            JLabel labelImagen = new JLabel(imagenPista);
            labelImagen.setHorizontalAlignment(JLabel.CENTER);
            labelImagen.setVerticalAlignment(JLabel.CENTER);

            //agregar la imagen a la ventana
            add(labelImagen, BorderLayout.CENTER);
            
            setLocationRelativeTo(null); //centrar la ventana en la pantalla
        }

        //metodo para mostrar la quinta pista
        public static void mostrarVentanaQuintaPista() {
            SwingUtilities.invokeLater(() -> {
            	VentanaQuintaPista ventana = new VentanaQuintaPista();
                ventana.setVisible(true);
            });
        }
    }
}

 //creador de todo
 package banca;


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ImageIcon imagenFondo;

    public Imagen(String nombreImagen) {
        setImagen(nombreImagen);
        this.setSize(600, 400);
    }

    // Método para establecer la imagen de fondo
    public void setImagen(String nombreImagen) {
        // Intentamos cargar la imagen desde el classpath
        java.net.URL imageURL = getClass().getResource(nombreImagen);
        if (imageURL != null) {
            imagenFondo = new ImageIcon(imageURL);
        } else {
            System.out.println("Imagen no encontrada: " + nombreImagen);
            imagenFondo = null; // Para evitar el NullPointerException
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension tamanio = getSize();
        if (imagenFondo != null) {
            g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, this);
        }
    }
}


//Intento medio raro
/*
package banca;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ImageIcon imagenFondo;
    private String nombreImagenActual; // Variable para almacenar el nombre de la imagen actual

    public Imagen(String nombreImagen) {
        setImagen(nombreImagen);
        this.setSize(600, 400);
    }

    // Método para establecer la imagen de fondo
    public void setImagen(String nombreImagen) {
        // Intentamos cargar la imagen desde el classpath
        java.net.URL imageURL = getClass().getResource(nombreImagen);
        if (imageURL != null) {
            imagenFondo = new ImageIcon(imageURL);
            nombreImagenActual = nombreImagen; // Almacena el nombre de la imagen actual
        } else {
            System.out.println("Imagen no encontrada: " + nombreImagen);
            imagenFondo = null; // Para evitar el NullPointerException
        }
    }

    // Método para obtener el nombre de la imagen actual
    public String getImagenNombre() {
        return nombreImagenActual;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension tamanio = getSize();
        if (imagenFondo != null) {
            g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, this);
        }
    }
}*/

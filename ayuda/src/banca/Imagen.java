 //creador de todo
 package banca;

//Este archivo sirve para mostrar las 4 imagenes que tenemos
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

    public void setImagen(String nombreImagen) {
        //carga la imagen desde un classpoint
        java.net.URL imageURL = getClass().getResource(nombreImagen);
        if (imageURL != null) {
            imagenFondo = new ImageIcon(imageURL);
        } else {
            System.out.println("Imagen no encontrada: " + nombreImagen);
            imagenFondo = null; //por si no se encuentra la imagen
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
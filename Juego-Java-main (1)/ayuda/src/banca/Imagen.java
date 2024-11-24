 //creador de todo
 package banca;

//Este archivo sirve para mostrar las 4 imagenes que tenemos
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends JPanel {

    private static final long serialVersionUID = 1L;//es un identificador unico
    private ImageIcon imagenFondo; //un private a futuro con las imagenes

    public Imagen(String nombreImagen) {
        setImagen(nombreImagen); //seteamos el nombreImagen
        this.setSize(600, 400); //el tamaño de las imagenes
    }

    public void setImagen(String nombreImagen) {
        //carga la imagen desde un classpoint
        java.net.URL imageURL = getClass().getResource(nombreImagen); //pide los njombres de las imagenes
        if (imageURL != null) { //compara las imagenes por si no hay
            imagenFondo = new ImageIcon(imageURL); //pega la imagen
        } else {
            System.out.println("Imagen no encontrada: " + nombreImagen); //si no la encuentra xd :v
            imagenFondo = null; //por si no se encuentra la imagen
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //esto es l oque pone las imagenes para que sean visibles
        Dimension tamanio = getSize(); //agarra el tamaño del public de arriba
        if (imagenFondo != null) { //compara para poder ponerlas
            g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, this);
            //llama a las funciones
        }
    }
}
package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage pantalla;

    public static void init() {
    	pantalla = Loader.imageLoger("/resources/pantallaUno.png");
        System.out.println("Cargando la imagen pa");
        if (pantalla == null) {
            System.out.println("La imagen no se pudo cargar.");
        }
    }
}

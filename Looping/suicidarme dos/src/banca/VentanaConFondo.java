//creador de todo
package banca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class VentanaConFondo extends JFrame {

    private static final long serialVersionUID = 1L;
    private Imagen panelImagen;
    private PuntoRojo puntoRojo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaConFondo frame = new VentanaConFondo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaConFondo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 400);

        // Crear el panel de imagen y establecer el borde
        panelImagen = new Imagen("pantallaUno.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(panelImagen, BorderLayout.CENTER);

        // Agregar el punto rojo solo en la pantalla inicial (pantallaUno)
        puntoRojo = new PuntoRojo(panelImagen);
        panelImagen.add(puntoRojo);
        
        // Agregar KeyListener para cambiar de imagen con teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1':
                        panelImagen.setImagen("pantallaUno.png");
                        puntoRojo.setVisible(true);  // Mostrar el punto rojo en pantallaUno
                        break;
                    case '2':
                        panelImagen.setImagen("pantallaDos.png");
                        puntoRojo.setVisible(false); // Ocultar el punto rojo en pantallaDos
                        break;
                    case '3':
                        panelImagen.setImagen("pantallaTres.png");
                        if (puntoRojo.isClicked()) {
                            puntoRojo.setVisible(true); // Mostrar el punto rojo en pantallaTres si fue clickeado
                        } else {
                            puntoRojo.setVisible(false);
                        }
                        break;
                    case '4':
                        panelImagen.setImagen("pantallaCuatro.png");
                        puntoRojo.setVisible(false); // Ocultar el punto rojo en pantallaCuatro
                        break;
                    default:
                        break;
                }
                // Actualizar el panel después de cambiar la imagen
                panelImagen.repaint();
            }
        });
        
        // Hacer que la ventana tenga foco para que capture las teclas
        setFocusable(true);
    }
}










//Especulacion futura
/*
package banca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class VentanaConFondo extends JFrame {

    private static final long serialVersionUID = 1L;
    private Imagen panelImagen;
    private PuntoRojo puntoRojo;
    private int indiceSecuencia = 0;

    // Secuencia de pantallas donde aparecerán los puntos rojos
    private final String[] secuenciaPantallas = {
        "pantallaUno.png", "pantallaTres.png", "pantallaUno.png", 
        "pantallaCuatro.png", "pantallaDos.png", "pantallaUno.png", "pantallaCuatro.png"
    };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaConFondo frame = new VentanaConFondo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaConFondo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 400);

        // Crear el panel de imagen y establecer el borde
        panelImagen = new Imagen("pantallaUno.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(panelImagen, BorderLayout.CENTER);

        // Crear el punto rojo en pantallaUno (primer punto de la secuencia)
        puntoRojo = new PuntoRojo(panelImagen);
        panelImagen.add(puntoRojo);
        
        // Agregar KeyListener para cambiar de imagen con teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char tecla = e.getKeyChar();
                if (tecla >= '1' && tecla <= '4') {
                    int numeroPantalla = Character.getNumericValue(tecla);
                    cambiarPantalla(numeroPantalla);
                }
            }
        });
        
        // Hacer que la ventana tenga foco para que capture las teclas
        setFocusable(true);
    }

    private void cambiarPantalla(int numeroPantalla) {
        String nombrePantalla = "pantalla" + numeroPantalla + ".png";
        panelImagen.setImagen(nombrePantalla);
        
        // Verificar si el punto debe aparecer en esta pantalla y si aún está en la secuencia
        if (indiceSecuencia < secuenciaPantallas.length && 
            nombrePantalla.equals(secuenciaPantallas[indiceSecuencia])) {
            
            puntoRojo.resetPunto(); // Reactivar el punto en la nueva pantalla
            puntoRojo.setVisible(true); // Hacer el punto visible
        } else {
            puntoRojo.setVisible(false); // Ocultar el punto si no corresponde a esta pantalla
        }
        
        panelImagen.repaint();
    }

    public void avanzarSecuencia() {
        indiceSecuencia++;
        if (indiceSecuencia < secuenciaPantallas.length) {
            String siguientePantalla = secuenciaPantallas[indiceSecuencia];
            panelImagen.setImagen(siguientePantalla);
            puntoRojo.resetPunto();
            puntoRojo.setVisible(true);
            panelImagen.repaint();
        }
    }
}
*/



//Intento medio raro
/*
package banca;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class VentanaConFondo extends JFrame {

    private static final long serialVersionUID = 1L;
    private Imagen panelImagen;
    private PuntoRojo puntoRojo;
    private int estadoPunto = 0; // Controla el orden de aparición de los puntos

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaConFondo frame = new VentanaConFondo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaConFondo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 600, 400);

        // Crear el panel de imagen y establecer el borde
        panelImagen = new Imagen("pantallaUno.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(panelImagen, BorderLayout.CENTER);

        // Crear el punto rojo y añadirlo al panel de imagen
        puntoRojo = new PuntoRojo(panelImagen, this); // Pasamos una referencia a la ventana para manejar el estado
        panelImagen.add(puntoRojo);
        
        // Agregar KeyListener para cambiar de imagen con teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1':
                        panelImagen.setImagen("pantallaUno.png");
                        actualizarPuntoRojo();
                        break;
                    case '2':
                        panelImagen.setImagen("pantallaDos.png");
                        actualizarPuntoRojo();
                        break;
                    case '3':
                        panelImagen.setImagen("pantallaTres.png");
                        actualizarPuntoRojo();
                        break;
                    case '4':
                        panelImagen.setImagen("pantallaCuatro.png");
                        actualizarPuntoRojo();
                        break;
                    default:
                        break;
                }
                panelImagen.repaint();
            }
        });
        
        setFocusable(true);
    }
    
    // Método que controla la aparición del punto rojo según el estado
    public void avanzarEstado() {
        estadoPunto++;
    }
    
    private void actualizarPuntoRojo() {
        // Controlar la aparición del punto en cada imagen según el estado
        switch (estadoPunto) {
            case 0: // Pantalla 1
                puntoRojo.setVisible(true);
                puntoRojo.setLocation(50, 50); // Ubicación del primer punto en pantallaUno
                break;
            case 1: // Pantalla 3
                if (panelImagen.getImagenNombre().equals("pantallaTres.png")) {
                    puntoRojo.setVisible(true);
                    puntoRojo.setLocation(100, 100); // Ubicación del punto en pantallaTres
                } else {
                    puntoRojo.setVisible(false);
                }
                break;
            case 2: // Pantalla 4
                if (panelImagen.getImagenNombre().equals("pantallaCuatro.png")) {
                    puntoRojo.setVisible(true);
                    puntoRojo.setLocation(150, 150); // Ubicación del punto en pantallaCuatro
                } else {
                    puntoRojo.setVisible(false);
                }
                break;
            case 3: // Pantalla 2
                if (panelImagen.getImagenNombre().equals("pantallaDos.png")) {
                    puntoRojo.setVisible(true);
                    puntoRojo.setLocation(200, 200); // Ubicación del punto en pantallaDos
                } else {
                    puntoRojo.setVisible(false);
                }
                break;
            case 4: // Pantalla 3 nuevamente
                if (panelImagen.getImagenNombre().equals("pantallaTres.png")) {
                    puntoRojo.setVisible(true);
                    puntoRojo.setLocation(250, 250); // Ubicación del punto en pantallaTres nuevamente
                } else {
                    puntoRojo.setVisible(false);
                }
                break;
            case 5: // Pantalla 1 final
                if (panelImagen.getImagenNombre().equals("pantallaUno.png")) {
                    puntoRojo.setVisible(true);
                    puntoRojo.setLocation(300, 300); // Ubicación del punto final en pantallaUno
                } else {
                    puntoRojo.setVisible(false);
                }
                break;
            default:
                puntoRojo.setVisible(false);
                break;
        }
    }
}
*/
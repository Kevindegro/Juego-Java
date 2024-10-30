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
    private Imagen panelImagenDos;
    private Imagen panelImagenTres;
    private Imagen panelImagenCuatro;
    
    private PuntoRojo puntoRojo;
    private PuntoRojo_2 puntoRojo_2;
    private PuntoRojo_3 puntoRojo_3;
    private PuntoRojo_4 puntoRojo_4;
    private PuntoRojo_5 puntoRojo_5;
    private PuntoRojo_6 puntoRojo_6;

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

        //crear el panel de imagen y establecer el borde
        
        //LA IMAGEN UNO
        panelImagen = new Imagen("pantallaUno.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        //LA IMAGEN DOS
        panelImagen = new Imagen("pantallaDos.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        //LA IMAGEN TRES
        panelImagen = new Imagen("pantallaTres.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        //LA IMAGEN CUATRO
        panelImagen = new Imagen("pantallaCuatro.png");
        panelImagen.setBorder(new EmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(panelImagen, BorderLayout.CENTER);

        // poner el punto rojo solo en la pantalla inicial (pantallaUno)
        puntoRojo = new PuntoRojo(panelImagen);
        panelImagen.add(puntoRojo);
        
        // para cambiar de imagen con teclas se usa keylistener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1':
                        panelImagen.setImagen("pantallaUno.png");
                        puntoRojo.setVisible(true);
                        break;
                    case '2':
                        panelImagen.setImagen("pantallaDos.png");
                        puntoRojo.setVisible(false); //Ocultamos el punto de la pantalla dos
                        break;
                    case '3':
                        panelImagen.setImagen("pantallaTres.png");
                        if (puntoRojo.isClicked()) {
                            puntoRojo.setVisible(true); //mostrar el punto rojo en pantallaTres si fue clickeado
                        } else {
                            puntoRojo.setVisible(false);
                        }
                        break;
                    case '4':
                        panelImagen.setImagen("pantallaCuatro.png");
                        puntoRojo.setVisible(false); //ocultas el punto rojo en pantallaCuatro
                        break;
                    default:
                        break;
                }
                //esto sirve para actualizar los paneles ya que solo no cambia, tenes que actualizar 
                panelImagen.repaint();
            }
        });
        
        //esto captura las teclas
        setFocusable(true);
    }
}

 //Creador de todo 
  package banca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PuntoRojo extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean clicked = false;

    public PuntoRojo(JPanel parentPanel) {
        setPreferredSize(new Dimension(10, 10));  // Tamaño del punto
        setBackground(Color.RED);
        setBounds(50, 50, 10, 10);  // Posición inicial del punto en pantallaUno
        setOpaque(true);

        // Escuchar clics de mouse para desaparecer el punto
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = true; // Marcar el punto como clickeado
                setVisible(false); // Ocultar el punto en pantallaUno
                parentPanel.repaint(); // Refrescar el panel
            }
        });
    }

    // Método para verificar si el punto ha sido clickeado
    public boolean isClicked() {
        return clicked;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());  // Dibujar el punto rojo como un círculo
    }
}












//especulacion futura
/*
package banca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PuntoRojo extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean activado = true;
    private VentanaConFondo ventana;

    public PuntoRojo(JPanel parentPanel) {
        setPreferredSize(new Dimension(10, 10));  // Tamaño del punto
        setBackground(Color.RED);
        setBounds(50, 50, 10, 10);  // Posición inicial del punto
        setOpaque(true);

        // Escuchar clics de mouse para desaparecer el punto y avanzar en la secuencia
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (activado) {
                    setVisible(false); // Ocultar el punto
                    activado = false;  // Desactivar el punto
                    ventana.avanzarSecuencia(); // Avanzar en la secuencia de pantallas
                }
            }
        });
    }

    // Método para reiniciar el punto
    public void resetPunto() {
        activado = true;
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (activado) {
            g.setColor(Color.RED);
            g.fillOval(0, 0, getWidth(), getHeight());  // Dibujar el punto rojo como un círculo
        }
    }
}
*/





//intento medio raro
/*
package banca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PuntoRojo extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean clicked = false;
    private VentanaConFondo ventana;

    public PuntoRojo(JPanel parentPanel, VentanaConFondo ventana) {
        this.ventana = ventana;
        setPreferredSize(new Dimension(10, 10));  // Tamaño del punto
        setBackground(Color.RED);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!clicked) {
                    clicked = true;
                    setVisible(false);
                    ventana.avanzarEstado(); // Avanzar el estado en VentanaConFondo
                    parentPanel.repaint(); // Refrescar el panel
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());  // Dibujar el punto rojo como un círculo
    }
}*/

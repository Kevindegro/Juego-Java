package banca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PuntoRojo extends JPanel {
    //creamos los privates
    private static final long serialVersionUID = 1L; //es un identificador unico
    private boolean clicked = false; //clicks que se van a detectar
    private JPanel parentPanel;
    private VentanaConFondo ventana; //llamamos a ventana

    public PuntoRojo(JPanel parentPanel, int x, int y, VentanaConFondo ventana) {
        this.parentPanel = parentPanel;
        this.ventana = ventana;
        setPreferredSize(new Dimension(10, 10));
        setBackground(Color.RED);
        setBounds(x, y, 10, 10); //esto controla la posicion de los puntos rojos
        //PERO NO FUNCIONA NO SE POR QUE
        setOpaque(true);

        //detecta el click del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = true; //lo pone en true ahora
                setVisible(false); //ocultar el punto rojo actual
                ventana.mostrarSiguientePunto(); // mostras el siguiente punto en la ventana
            }
        });
    }

    public boolean isClicked() {
        return clicked; //returnas el clicked
    }

    public JPanel getParentPanel() {
        return parentPanel; //returnas el parentPanel
    }

    @Override
    protected void paintComponent(Graphics g) { //esto es para pintar los puntos rojos
        super.paintComponent(g); //originalmente tendria que ser un script
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
    
}
 //Creador de todo 
  package banca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PuntoRojo_6 extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean clicked = false;

    public PuntoRojo_6(JPanel parentPanel) {
        setPreferredSize(new Dimension(10, 10));//tamaño
        setBackground(Color.RED);
        setBounds(50, 50, 10, 10);//lugar donde aparece
        setOpaque(true);

        //detecta tu click del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = true; //Marcar el punto como clickeado
                setVisible(false); 
                parentPanel.repaint();
            }
        });
    }


    public boolean isClicked() {
        return clicked;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());//dibujar el punto rojo
    }
}

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
    private JPanel parentPanel;
    private VentanaConFondo ventana;

    public PuntoRojo(JPanel parentPanel, int x, int y, VentanaConFondo ventana) {
        this.parentPanel = parentPanel;
        this.ventana = ventana;
        setPreferredSize(new Dimension(10, 10));
        setBackground(Color.RED);
        setBounds(x, y, 10, 10);  // Esto controla la posición de cada punto rojo
        setOpaque(true);

        // Detecta el click del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = true;
                setVisible(false); // Oculta el punto rojo actual
                ventana.mostrarSiguientePunto(); // Muestra el siguiente punto en secuencia
            }
        });
    }

    public boolean isClicked() {
        return clicked;
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}

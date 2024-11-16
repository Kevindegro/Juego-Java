package banca;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Clase que representa un punto rojo interactivo en la interfaz gráfica.
 * Los puntos rojos están asociados a un panel padre y permiten
 * interacciones mediante clics, desencadenando acciones específicas
 * en el flujo del juego.
 */
public class PuntoRojo extends JPanel {

    private static final long serialVersionUID = 1L;

    // Indica si el punto rojo ha sido clickeado.
    private boolean clicked = false;

    // Panel padre al que pertenece este punto rojo.
    private JPanel parentPanel;

    // Referencia a la ventana principal para coordinar las transiciones.
    private VentanaConFondo ventana;

    /**
     * Constructor que inicializa un punto rojo en una posición específica,
     * lo asocia a un panel padre y configura su interacción.
     * 
     * @param parentPanel Panel padre al que pertenece este punto rojo.
     * @param x Posición X del punto rojo dentro del panel.
     * @param y Posición Y del punto rojo dentro del panel.
     * @param ventana Referencia a la ventana principal para manejar el flujo del juego.
     */
    public PuntoRojo(JPanel parentPanel, int x, int y, VentanaConFondo ventana) {
        this.parentPanel = parentPanel;
        this.ventana = ventana;

        // Configurar dimensiones y propiedades visuales del punto rojo.
        setPreferredSize(new Dimension(10, 10)); // Tamaño estándar del punto.
        setBackground(Color.RED); // Color de fondo (rojo).
        setBounds(x, y, 10, 10); // Posición y tamaño del punto en el panel.
        setOpaque(true); // Hacer que el punto sea opaco.

        // Configuración de interacción mediante clics del mouse.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = true; // Marcar el punto como clickeado.
                setVisible(false); // Ocultar el punto después de ser clickeado.
                ventana.mostrarSiguientePunto(); // Notificar a la ventana principal para avanzar al siguiente punto.
            }
        });
    }

    /**
     * Método que devuelve si el punto rojo ha sido clickeado.
     * 
     * @return true si el punto fue clickeado, false en caso contrario.
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * Método que devuelve el panel padre al que pertenece este punto rojo.
     * 
     * @return El panel padre.
     */
    public JPanel getParentPanel() {
        return parentPanel;
    }

    /**
     * Método sobrescrito para dibujar el punto rojo en forma de óvalo.
     * 
     * @param g Objeto Graphics para realizar el dibujo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); // Configurar color rojo para el dibujo.
        g.fillOval(0, 0, getWidth(), getHeight()); // Dibujar un óvalo que ocupa todo el componente.
    }
}

package menu;

import banca.VentanaConFondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VentanaPrincipal extends javax.swing.JFrame {
    private Image[] fondos;
    private int imagenActual = 0;
    private Timer timer;
    public String nombre;

    public VentanaPrincipal() {
        // Cargar las imágenes que deseas rotar
        fondos = new Image[] {
            new ImageIcon(getClass().getResource("/banca/pantallaUno.png")).getImage(),
            new ImageIcon(getClass().getResource("/banca/pantallaDos.png")).getImage(),
            new ImageIcon(getClass().getResource("/banca/pantallaTres.png")).getImage(),
            new ImageIcon(getClass().getResource("/banca/pantallaCuatro.png")).getImage()
        };

        initComponents();
        agregarBotonesConAnimacion();

        // Configura el timer para cambiar la imagen cada 6000 milisegundos
        timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar a la siguiente imagen
                imagenActual = (imagenActual + 1) % fondos.length;
                repaint(); // Volver a pintar el panel para mostrar la nueva imagen
            }
        });
        timer.start(); // Iniciar el temporizador
    }

    private void agregarBotonesConAnimacion() {
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setPreferredSize(new Dimension(400, 100));
        
        JButton btnJugar = crearBoton("Jugar");
        JButton btnControles = crearBoton("Controles");
        JButton btnSalir = crearBoton("Salir");

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Iniciar VentanaConFondo con el nombre ingresado o "Jun" si está vacío
                String nombreJugador = (nombre != null && !nombre.isEmpty()) ? nombre : "Jun";
                VentanaConFondo ventanaJuego = new VentanaConFondo(nombreJugador);
                ventanaJuego.setVisible(true);
                dispose(); // Cierra la ventana principal
            }
        });

        btnControles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "------------------{Teclado}------------------" + '\n' + "Tecla 1, 2, 3 y 4 cambiar de camara" + '\n'+ "------------------{Mouse}------------------" + '\n' + "M1 para seleccionar las partes");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panelBotones.add(btnJugar);
        panelBotones.add(btnControles);
        panelBotones.add(btnSalir);

        this.add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack();
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(100, 40));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boton.setBackground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boton.setBackground(null);
            }
        });

        return boton;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondos[imagenActual], 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(panelFondo);

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemRegistro = new javax.swing.JMenuItem();
        jMenuItemSaludo = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ejemplo de Menú");

        jMenu1.setBackground(new java.awt.Color(0, 153, 153));
        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setForeground(new java.awt.Color(204, 255, 204));
        jMenu1.setText("Archivo");
        jMenu1.setOpaque(true);

        jMenuItemRegistro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRegistro.setText("Registrarte...");
        jMenuItemRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistroActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemRegistro);

        jMenuItemSaludo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSaludo.setText("Saludo");
        jMenuItemSaludo.setEnabled(false);
        jMenuItemSaludo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaludoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaludo);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSalir);

        jMenuBar1.add(jMenu1);
        setJMenuBar(jMenuBar1);
        pack();
    }

    private void jMenuItemRegistroActionPerformed(java.awt.event.ActionEvent evt) {
        nombre = JOptionPane.showInputDialog(this, "Escribe tu nombre: ");
        jMenuItemSaludo.setEnabled(nombre != null && !nombre.isEmpty());
    }

    private void jMenuItemSaludoActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Hola " + nombre);
    }

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemRegistro;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSaludo;
}
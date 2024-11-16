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

// clase principal que extiende JFrame
public class VentanaPrincipal extends javax.swing.JFrame {
    private Image[] fondos; // arreglo de imagenes para el fondo
    private int imagenActual = 0; // indice de la imagen que se muestra actualmente
    private Timer timer; // temporizador para cambiar las imagenes
    public String nombre; // variable para almacenar el nombre del usuario

    // constructor de la ventana principal
    public VentanaPrincipal() {
        // cargar las imagenes que se usaran como fondo
        fondos = new Image[] {
            new ImageIcon(getClass().getResource("/banca/pantallaUno.png")).getImage(),
            new ImageIcon(getClass().getResource("/banca/pantallaDos.png")).getImage(),
            new ImageIcon(getClass().getResource("/banca/pantallaTres.png")).getImage(),
            new ImageIcon(getClass().getResource("/banca/pantallaCuatro.png")).getImage()
        };

        initComponents(); // inicializar componentes de la interfaz
        agregarBotonesConAnimacion(); // agregar botones animados al panel

        // configurar temporizador para cambiar la imagen cada 6 segundos
        timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cambiar a la siguiente imagen
                imagenActual = (imagenActual + 1) % fondos.length;
                repaint(); // repintar el panel para mostrar la nueva imagen
            }
        });
        timer.start(); // iniciar el temporizador
    }

    // metodo para agregar botones con animacion
    private void agregarBotonesConAnimacion() {
        JPanel panelBotones = new JPanel(); // panel para los botones
        panelBotones.setOpaque(false); // hacerlo transparente
        panelBotones.setPreferredSize(new Dimension(400, 100)); // definir tamaño del panel
        
        // crear los botones
        JButton btnJugar = crearBoton("Jugar");
        JButton btnControles = crearBoton("Controles");
        JButton btnSalir = crearBoton("Salir");

        // configurar evento para el boton "jugar"
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // iniciar ventana de juego con el nombre del jugador
                String nombreJugador = (nombre != null && !nombre.isEmpty()) ? nombre : "Jun";
                VentanaConFondo ventanaJuego = new VentanaConFondo(nombreJugador);
                ventanaJuego.setVisible(true);
                dispose(); // cerrar la ventana principal
            }
        });

        // configurar evento para el boton "controles"
        btnControles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // mostrar un mensaje con los controles del juego
                JOptionPane.showMessageDialog(null, "------------------{Teclado}------------------" + '\n' + "Tecla 1, 2, 3 y 4 cambiar de camara" + '\n'+ "------------------{Mouse}------------------" + '\n' + "M1 para seleccionar las partes");
            }
        });

        // configurar evento para el boton "salir"
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // cerrar la ventana
            }
        });

        // agregar los botones al panel
        panelBotones.add(btnJugar);
        panelBotones.add(btnControles);
        panelBotones.add(btnSalir);

        // agregar el panel de botones al marco
        this.add(panelBotones, java.awt.BorderLayout.SOUTH);
        pack(); // ajustar tamaño de la ventana
    }

    // metodo para crear un boton con eventos de mouse
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto); // crear boton con texto
        boton.setPreferredSize(new Dimension(100, 40)); // definir tamaño del boton

        // agregar eventos de mouse para cambiar el color al presionar o soltar
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

    // metodo para inicializar componentes de la ventana
    @SuppressWarnings("unchecked")
    private void initComponents() {
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondos[imagenActual], 0, 0, getWidth(), getHeight(), this); // dibujar la imagen actual
            }
        };
        setContentPane(panelFondo); // establecer el panel de fondo

        // inicializar barra de menu
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemRegistro = new javax.swing.JMenuItem();
        jMenuItemSaludo = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // cerrar al salir
        setTitle("Ejemplo de Menú"); // titulo de la ventana

        // configurar menu "archivo"
        jMenu1.setBackground(new java.awt.Color(0, 153, 153));
        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setForeground(new java.awt.Color(204, 255, 204));
        jMenu1.setText("Archivo");
        jMenu1.setOpaque(true);

        // configurar opcion "registrarte"
        jMenuItemRegistro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRegistro.setText("Registrarte...");
        jMenuItemRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistroActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemRegistro);

        // configurar opcion "saludo"
        jMenuItemSaludo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSaludo.setText("Saludo");
        jMenuItemSaludo.setEnabled(false);
        jMenuItemSaludo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaludoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaludo);

        // configurar opcion "salir"
        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSalir);

        jMenuBar1.add(jMenu1); // agregar menu a la barra
        setJMenuBar(jMenuBar1); // establecer barra de menu
        pack(); // ajustar tamaño de la ventana
    }

    // metodo para manejar la opcion "registrarte"
    private void jMenuItemRegistroActionPerformed(java.awt.event.ActionEvent evt) {
        nombre = JOptionPane.showInputDialog(this, "Escribe tu nombre: "); // pedir nombre al usuario
        jMenuItemSaludo.setEnabled(nombre != null && !nombre.isEmpty()); // habilitar opcion "saludo" si el nombre es valido
    }

    // metodo para manejar la opcion "saludo"
    private void jMenuItemSaludoActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Hola " + nombre); // mostrar saludo con el nombre del usuario
    }

    // metodo para manejar la opcion "salir"
    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); // cerrar la ventana
    }

    // metodo principal para ejecutar la aplicacion
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true); // mostrar la ventana principal
            }
        });
    }

    // declaracion de componentes de la barra de menu
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemRegistro;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSaludo;
}

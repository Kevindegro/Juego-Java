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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


// clase principal que extiende JFrame
public class VentanaPrincipal extends javax.swing.JFrame {
	int num = 0;
    private Image[] fondos; // arreglo de imagenes para el fondo
    private int imagenActual = 0; // indice de la imagen que se muestra actualmente
    private Timer timer; // temporizador para cambiar las imagenes
    public String nombre; // variable para almacenar el nombre del usuario
    private Clip clipMusica;  // Variable para el clip de música

    public String exportarParaFinalDelJuego() {
        return nombre;
    }
    
    // constructor de la ventana principal
    public VentanaPrincipal() {
        // cargar las imagenes que se usaran como fondo
        fondos = new Image[] {
            new ImageIcon(getClass().getResource("/resources/pantallaUno.png")).getImage(),
            new ImageIcon(getClass().getResource("/resources/pantallaDos.png")).getImage(),
            new ImageIcon(getClass().getResource("/resources/pantallaTres.png")).getImage(),
            new ImageIcon(getClass().getResource("/resources/pantallaCuatro.png")).getImage()
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
        
        iniciarMusicaFondo();  // Iniciar la música de fondo

    }
    
    private void iniciarMusicaFondo() {
        try {
            File audioFile = new File(getClass().getResource("/resources/Menu_Music.wav").toURI());//no lo entiendo Kevin ayuda
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);//no lo entiendo Kevin ayuda
            clipMusica = AudioSystem.getClip(); //no lo entiendo Kevin ayuda
            clipMusica.open(audioInputStream); //no lo entiendo Kevin ayuda
            clipMusica.loop(Clip.LOOP_CONTINUOUSLY); // Reproducir en bucle
            clipMusica.start();
        } catch (Exception e) {
            e.printStackTrace();//no lo entiendo Kevin ayuda
        }
    }


    private void detenerMusica() {
        if (clipMusica != null && clipMusica.isRunning()) {
            clipMusica.stop();
            clipMusica.close();
        }
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

    //metodo para inicializar componentes de la ventana
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

        //iniciarr barra de menu
        jMenuBar1 = new javax.swing.JMenuBar(); 
        jMenu1 = new javax.swing.JMenu();
        jMenuItemRegistro = new javax.swing.JMenuItem();
        jMenuItemSaludo = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // cerrar al salir
        setTitle("Ejemplo de Menú"); //titulo de la ventana

        //configurar menu "archivo"
        jMenu1.setBackground(new java.awt.Color(0, 153, 153));
        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setForeground(new java.awt.Color(204, 255, 204));
        jMenu1.setText("Archivo");
        jMenu1.setOpaque(true);

        //configurar opcion "registrarte"
        jMenuItemRegistro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRegistro.setText("Registrarte...");
        jMenuItemRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistroActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemRegistro);

        //configurar opcion "saludo"
        jMenuItemSaludo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSaludo.setText("Saludo");
        jMenuItemSaludo.setEnabled(false);
        jMenuItemSaludo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaludoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSaludo);

        //configurar opcion "salir"
        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSalir);

        jMenuBar1.add(jMenu1); //agregar menu a la barra
        setJMenuBar(jMenuBar1); // establecer barra de menu
        pack(); //ajustar tamaño de la ventana
    }

    //metodo para manejar la opcion "registrarte"
    private void jMenuItemRegistroActionPerformed(java.awt.event.ActionEvent evt) {
        nombre = JOptionPane.showInputDialog(this, "Escribe tu nombre: "); // pedir nombre al usuario
        jMenuItemSaludo.setEnabled(nombre != null && !nombre.isEmpty()); // habilitar opcion "saludo" si el nombre es valido
    }

    //metodo para manejar la opcion "saludo"
    private void jMenuItemSaludoActionPerformed(java.awt.event.ActionEvent evt) {
    	if(num == 0 || num >= 8) {
        JOptionPane.showMessageDialog(this, "Hola " + nombre); //mostrar saludo con el nombre del usuario
        num = num + 1; //suma para que cambie el saludo, cuando esta al maximo explota xd
    }else if(num == 1) {
    	JOptionPane.showMessageDialog(this, "El padre de " + nombre + " era su figura a seguir, ya que este lo crio él solo cuando su madre los abandono.");
    	num = num + 1;
    }
    else if(num == 2) {
    	JOptionPane.showMessageDialog(this,  nombre + " lo admiraba mucho y cuando creciera queria ser un detective como el.");
    	num = num + 1;
    }
    else if(num == 3) {
    	JOptionPane.showMessageDialog(this, "El padre de " + nombre + " al saber el sueño de su hijo creo un juego sobre detectives. Con el que se la pasaban jugando por horas.");
    	num = num + 1;
    }
    else if(num == 4) {
    	JOptionPane.showMessageDialog(this, "Todo era felicidad en la vida de " + nombre + ", hasta que en una persecusión con un asesino su padre perdio la vida a manos del criminal.");
    	num = num + 1;
    }
    else if(num == 5) {
    	JOptionPane.showMessageDialog(this, "Desde ese momento empezo a ver cosas y " + nombre + " le decia a todo el mundo que su padre estaba con vida., que lo veia y hababa con él.");
    	num = num + 1;
    }
    else if(num == 6) {
    	JOptionPane.showMessageDialog(this, "Por esa y mas cosas que el decia 'ver', " + nombre + " estubo pasando de psicologo en psicologo. Hasta que le resetaron unas pastillas.");
    	num = num + 1;
    }
    else if(num == 7) {
    	JOptionPane.showMessageDialog(this, "Gracias a esas pastillas sus 'viciones' se desvanecieron, " + nombre +"sigue yendo a sesiones para asegurarse de curarse por completo.");
    	num = num + 1;
    }
    }

    //metodo para manejar la opcion "salir"
    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); //cerrar la ventana
    }

    //metodo principal para ejecutar la aplicacion
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true); //mostrar la ventana principal
            }
        });
    }

    //declaracion de componentes de la barra de menu
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemRegistro;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSaludo;
}

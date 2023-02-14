package Cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.JScrollPane;

public class Cliente extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextArea campochat;
	private JLabel nick;
	private static JButton botonenviar;
	private static JTextField campo1;
	private String IPSERVER = "";
	private JScrollPane scrollPane;
	private JComboBox ip;
	private String nick_usuario = "";
	private JPanel panel_1;
	private byte[] imgAEnviar;
	private String filename;
	private JButton btnArchivo;
	private static JLabel emoji1;
	private static JLabel emoji2;
	private static JLabel emoji5;
	private static JLabel emoji3;
	private static JLabel emoji4;
	private static JLabel emoji6;
	private static JLabel emoji7;
	private static JLabel emoji8;
	private static JLabel emoji9;
	private static JLabel emoji10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL");
		System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
		System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
		System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.getRootPane().setDefaultButton(botonenviar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			    Font customFont4Emoji = null;
			    Font customFont4Text = null;
				try {
					customFont4Emoji = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\OpenSansEmoji.ttf")).deriveFont(28f);
					customFont4Text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\OpenSansEmoji.ttf")).deriveFont(16f);
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    GraphicsEnvironment.getLocalGraphicsEnvironment();

				emoji1.setFont(customFont4Emoji);
				emoji2.setFont(customFont4Emoji);
				emoji3.setFont(customFont4Emoji);
				emoji4.setFont(customFont4Emoji);
				emoji5.setFont(customFont4Emoji);
				emoji6.setFont(customFont4Emoji);
				emoji7.setFont(customFont4Emoji);
				emoji8.setFont(customFont4Emoji);
				emoji9.setFont(customFont4Emoji);
				emoji10.setFont(customFont4Emoji);
				
				emoji1.setText("\uD83D\uDE00");
				emoji2.setText("\uD83D\uDE05");
				emoji3.setText("\uD83D\uDE02");
				emoji4.setText("\uD83D\uDE09");
				emoji5.setText("\uD83D\uDE0D");
				emoji6.setText("\uD83D\uDE18");
				emoji7.setText("\uD83D\uDE2D");
				emoji8.setText("\uD83D\uDE21");
				emoji9.setText("\uD83D\uDE08");
				emoji10.setText("\uD83D\uDCA9");
				
				campo1.setFont(customFont4Text);
				campochat.setFont(customFont4Text);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cliente() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 635);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(63, 99, 88));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imagen = new JLabel("");
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setBounds(0, 198, 344, 155);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/LogoLetrero(color-cremita).png"));
		Image imageAux1 = img.getImage();
		Image newImg = imageAux1.getScaledInstance(imagen.getWidth(), imagen.getHeight(), DO_NOTHING_ON_CLOSE);
		ImageIcon imgIcon = new ImageIcon(newImg);
		imagen.setIcon(imgIcon);
		contentPane.add(imagen);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(178, 10, 60, 60);
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/LogoIcono(color-cremita).png"));
		Image imageAux2 = img2.getImage();
		Image newImg2 = imageAux2.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
				DO_NOTHING_ON_CLOSE);
		ImageIcon imgIcon2 = new ImageIcon(newImg2);
		lblNewLabel.setIcon(imgIcon2);
		contentPane.add(lblNewLabel);

		campo1 = new JTextField();
		campo1.setFont(new Font("Monospaced", Font.PLAIN, 14));
		campo1.setBounds(10, 477, 267, 53);
		contentPane.add(campo1);
		campo1.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 324, 405);
		panel.setBackground(new Color(63, 99, 88));
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 324, 405);
		panel.add(scrollPane);

		campochat = new JTextArea();
		campochat.setLineWrap(true);
		scrollPane.setViewportView(campochat);
		campochat.setFont(new Font("Monospaced", Font.BOLD, 17));
		campochat.setForeground(Color.WHITE);
		campochat.setBackground(new Color(63, 99, 88));
		campochat.setForeground(new Color(252, 226, 173));
		campochat.setEditable(false);

		botonenviar = new JButton("");
		botonenviar.setBounds(344, 477, 47, 53);
		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/iconoEnviar.png"));
		Image imageAux3 = img3.getImage();
		Image newImg3 = imageAux3.getScaledInstance(botonenviar.getWidth(), botonenviar.getHeight(),
				DO_NOTHING_ON_CLOSE);
		ImageIcon imgIcon3 = new ImageIcon(newImg3);
		botonenviar.setIcon(imgIcon3);
		botonenviar.setBackground(new Color(138, 153, 148));
		EnviaTexto mievento = new EnviaTexto();
		botonenviar.addActionListener(mievento);
		contentPane.add(botonenviar);

		try {
			while (IPSERVER.isEmpty()) {
				IPSERVER = JOptionPane.showInputDialog("IP del servidor: ");
			}

			while (nick_usuario.isEmpty()) {
				nick_usuario = JOptionPane.showInputDialog("Nick: ");
			}
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "No se han introducido los datos necesarios.", "Alerta",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		nick = new JLabel();
		nick.setBackground(new Color(255, 255, 255));
		nick.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nick.setHorizontalAlignment(SwingConstants.CENTER);
		nick.setBounds(33, 31, 119, 20);
		nick.setForeground(new Color(252, 226, 173));
		nick.setText(nick_usuario);
		contentPane.add(nick);

		ip = new JComboBox();
		ip.setBounds(266, 31, 125, 20);
		contentPane.add(ip);

		JLabel lblNewLabel_1 = new JLabel("Nickname");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(51, 10, 88, 14);
		lblNewLabel_1.setForeground(new Color(252, 226, 173));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Online");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(289, 7, 81, 14);
		lblNewLabel_2.setForeground(new Color(252, 226, 173));
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Seleccione una opcion...",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					ip.remove(ip.getSelectedIndex());
					System.exit(0);
				} else {
					return;
				}
			}
		});
		btnNewButton.setForeground(new Color(63, 99, 88));
		btnNewButton.setBackground(new Color(252, 226, 173));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 541, 381, 43);
		contentPane.add(btnNewButton);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(252, 226, 173));
		panel_1.setBounds(344, 61, 47, 405);
		contentPane.add(panel_1);

		emoji1 = new JLabel("111");
		emoji1.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji1);
		emoji1.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE00");
            }
        });
		
		emoji2 = new JLabel("222");
		emoji2.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji2);
		emoji2.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE05");
            }
        });
		
		emoji3 = new JLabel("333");
		emoji3.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji3);
		emoji3.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE02");
            }
        });
		
		emoji4 = new JLabel("444");
		emoji4.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji4);
		emoji4.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE09");
            }
        });
		
		emoji5 = new JLabel("555");
		emoji5.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji5);
		emoji5.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE0D");
            }
        });
		
		emoji6 = new JLabel("666");
		emoji6.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji6);
		emoji6.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE18");
            }
        });
		
		emoji7 = new JLabel("777");
		emoji7.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji7);
		emoji7.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE2D");
            }
        });
		
		emoji8 = new JLabel("888");
		emoji8.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji8);
		emoji8.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE21");
            }
        });
		
		emoji9 = new JLabel("999");
		emoji9.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji9);
		emoji9.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDE08");
            }
        });
		
		emoji10 = new JLabel("1010");
		emoji10.setFont(new Font("Monospaced", Font.PLAIN, 26));
		panel_1.add(emoji10);
		
		btnArchivo = new JButton("");
		btnArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(Cliente.this);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    String absolutePath = selectedFile.getAbsolutePath();
				    filename = new File(absolutePath).getName();
				    try {
				    	imgAEnviar = Files.readAllBytes(selectedFile.toPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    
				    botonenviar.doClick();
				    
//				    InputStream in = new ByteArrayInputStream(imgAEnviar);
//				    BufferedImage bImageFromConvert = null;
//				    try {
//						bImageFromConvert = ImageIO.read(in);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				    
//				    String home = System.getProperty("user.home");
//				    File downloads = new File(home, "Downloads");
//				    
//				    String absolutePath = selectedFile.getAbsolutePath();
//				    filename = new File(absolutePath).getName();
//				    File outputFile = new File(downloads, filename);
//				    FileOutputStream fos;
//				    
//				    try {
//				    	fos = new FileOutputStream(outputFile);
//						fos.write(imgAEnviar);
//						fos.close();
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}				    
				}
			}
		});
		btnArchivo.setForeground(new Color(63, 99, 88));
		btnArchivo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnArchivo.setBackground(new Color(138, 153, 148));
		btnArchivo.setBounds(287, 477, 47, 53);
		ImageIcon img4 = new ImageIcon(this.getClass().getResource("/clip.png"));
		Image imageAux4 = img4.getImage();
		Image newImg4 = imageAux4.getScaledInstance(btnArchivo.getWidth(), btnArchivo.getHeight(),
				DO_NOTHING_ON_CLOSE);
		ImageIcon imgIcon4 = new ImageIcon(newImg4);
		btnArchivo.setIcon(imgIcon4);
		contentPane.add(btnArchivo);
		emoji10.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                campo1.setText(campo1.getText() + "\uD83D\uDCA9");
            }
        });

		Thread mihilo = new Thread(this);
		mihilo.start();

		addWindowListener(new EnvioOnline());

	}

	// ENVIO DE SENIAL ONLINE-----------------------------------
	class EnvioOnline extends WindowAdapter {
		public void windowOpened(WindowEvent e) {
			try {
				System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL");
				System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
				System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
				SSLSocketFactory sfact1 = (SSLSocketFactory) SSLSocketFactory.getDefault();
				SSLSocket misocket = (SSLSocket) sfact1.createSocket(IPSERVER, 9999);
				// Socket misocket = new Socket(IPSERVER, 9999);
				PaqueteEnvio datos = new PaqueteEnvio();
				datos.setMensaje(" online");
				ObjectOutputStream paquetedatos = new ObjectOutputStream(misocket.getOutputStream());
				System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL");
				System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
				System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
				paquetedatos.writeObject(datos);
				// misocket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	private class EnviaTexto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (imgAEnviar != null) {
				campochat.append("\nTu: '" + filename + "'\n");
			} else {
				campochat.append("\nTu: " + campo1.getText() + "\n");
			}
			try {
				System.setProperty("javax.net.ssl.keyStore", "certificados2/AlmacenSSL2");
				System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
				System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
				SSLSocketFactory sfact1 = (SSLSocketFactory) SSLSocketFactory.getDefault();
				SSLSocket misocket = (SSLSocket) sfact1.createSocket(IPSERVER, 9999);
				/// Socket misocket = new Socket(IPSERVER, 9999);
				PaqueteEnvio datos = new PaqueteEnvio();
				datos.setNick(nick.getText());
				datos.setIp(ip.getSelectedItem().toString());
				datos.setMensaje(campo1.getText());
				if (imgAEnviar != null) {
					datos.setFileContent(imgAEnviar);
					datos.setFilename(filename);
					imgAEnviar = null;
					datos.setMensaje("'" + filename + "'");
				} else {
					datos.setFileContent(null);
					datos.setFilename("NoHayFile");
				}
				ObjectOutputStream paquetedatos = new ObjectOutputStream(misocket.getOutputStream());
				System.setProperty("javax.net.ssl.keyStore", "certificados2/AlmacenSSL2");
				System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
				System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
				paquetedatos.writeObject(datos);
				campo1.setText("");
				// misocket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.setProperty("javax.net.ssl.keyStore", "certificados2/AlmacenSSL2");
			System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
			System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
			System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
			SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket servidorcliente = (SSLServerSocket) sfact.createServerSocket(9090);
			// ServerSocket servidorcliente = new ServerSocket(9090);
			SSLSocket cliente;
			PaqueteEnvio paqueterecibido;
			while (true) {
				cliente = (SSLSocket) servidorcliente.accept();
				System.setProperty("javax.net.ssl.keyStore", "certificados2/AlmacenSSL2");
				System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
				System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
				ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
				System.setProperty("javax.net.ssl.keyStore", "certificados2/AlmacenSSL2");
				System.setProperty("javax.net.ssl.keyStorePassword", "7654321");
				System.setProperty("javax.net.ssl.trustStore", "certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword", "1111111");
				paqueterecibido = (PaqueteEnvio) flujoentrada.readObject();
				if (!paqueterecibido.getMensaje().equals(" online")) {
					campochat.append("\n > " + paqueterecibido.nick + ": " + paqueterecibido.getMensaje() + "\n");
					
//					InputStream in = new ByteArrayInputStream(paqueterecibido.getFileContent());
//				    BufferedImage bImageFromConvert = null;
//				    try {
//						bImageFromConvert = ImageIO.read(in);
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				    
					if (paqueterecibido.getFileContent() != null) {
						String home = System.getProperty("user.home");
					    File downloads = new File(home, "Downloads");
					    
					    File outputFile = new File(downloads, paqueterecibido.getFilename());
					    FileOutputStream fos;
					    
					    try {
					    	fos = new FileOutputStream(outputFile);
							fos.write(paqueterecibido.getFileContent());
							fos.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					
				} else {
					ArrayList<String> ipsmenu = new ArrayList<String>();
					ipsmenu = paqueterecibido.getIps();
					ip.removeAllItems();
					for (String z : ipsmenu) {
						ip.addItem(z);
					}
				}
				cliente.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

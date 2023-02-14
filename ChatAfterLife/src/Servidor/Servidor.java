package Servidor;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cliente.PaqueteEnvio;
import javax.swing.JTextArea;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servidor extends JFrame implements Runnable {
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
		System.setProperty("javax.net.ssl.keyStorePassword","7654321");
		System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
		System.setProperty("javax.net.ssl.trustStorePassword","1111111");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */
	public Servidor() throws InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(5, 5, 424, 263);
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 424, 263);
		panel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("APAGAR SERVIDOR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(218, 279, 211, 33);
		contentPane.add(btnNewButton);

		Thread mihilo = new Thread(this);
		mihilo.start();
	}

	public void run() {
		try {
			System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
			System.setProperty("javax.net.ssl.keyStorePassword","7654321");
			System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
			System.setProperty("javax.net.ssl.trustStorePassword","1111111");
			SSLServerSocketFactory sfact=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket servidor=(SSLServerSocket) sfact.createServerSocket(9999);
			///ServerSocket servidor = new ServerSocket(9999);
			
			SSLSocketFactory sfact1=(SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket misocket;
			
			String ip, nick, mensaje;
			ArrayList<String> listaip = new ArrayList<String>();
			PaqueteEnvio paqueterecibido;
			
			while (true) {
				System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
				System.setProperty("javax.net.ssl.keyStorePassword","7654321");
				System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword","1111111");
				misocket = (SSLSocket) servidor.accept();
				System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
				System.setProperty("javax.net.ssl.keyStorePassword","7654321");
				System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword","1111111");
				
				ObjectInputStream paquetedatos = new ObjectInputStream(misocket.getInputStream());
				System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
				System.setProperty("javax.net.ssl.keyStorePassword","7654321");
				System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
				System.setProperty("javax.net.ssl.trustStorePassword","1111111");
				paqueterecibido = (PaqueteEnvio) paquetedatos.readObject();
				nick = paqueterecibido.getNick();
				ip = paqueterecibido.getIp();
				mensaje = paqueterecibido.getMensaje();
				if (!mensaje.equals(" online")) {
					textArea.append("\n" + nick + ": " + mensaje);
					
					SSLSocket enviadestinatario=(SSLSocket) sfact1.createSocket(ip, 9090);
					//Socket enviadestinatario = new Socket(ip, 9090);
					System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
					System.setProperty("javax.net.ssl.keyStorePassword","7654321");
					System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
					System.setProperty("javax.net.ssl.trustStorePassword","1111111");
					ObjectOutputStream paquetereenvio = new ObjectOutputStream(enviadestinatario.getOutputStream());
					System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
					System.setProperty("javax.net.ssl.keyStorePassword","7654321");
					System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
					System.setProperty("javax.net.ssl.trustStorePassword","1111111");
					paquetereenvio.writeObject(paqueterecibido);
//					paquetereenvio.close();
//					enviadestinatario.close();
					//misocket.close();
				} else {
					// --------------DETECCION USUARIOS ONLINE----------------
					InetAddress localizacion = misocket.getInetAddress();
					String ipremota = localizacion.getHostAddress();
					System.out.println("Online " + ipremota);
					listaip.add(ipremota);
					paqueterecibido.setIps(listaip);
					for (String z : listaip) {
						System.out.println("Array: " + z);
						SSLSocket enviadestinatario=(SSLSocket) sfact1.createSocket(z, 9090);
						//Socket enviadestinatario = new Socket(z, 9090);
						System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
						System.setProperty("javax.net.ssl.keyStorePassword","7654321");
						System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
						System.setProperty("javax.net.ssl.trustStorePassword","1111111");
						ObjectOutputStream paquetereenvio = new ObjectOutputStream(enviadestinatario.getOutputStream());
						System.setProperty("javax.net.ssl.keyStore", "certificados/AlmacenSSL"); 
						System.setProperty("javax.net.ssl.keyStorePassword","7654321");
						System.setProperty("javax.net.ssl.trustStore","certificados/UsuarioAlmacenSSL");
						System.setProperty("javax.net.ssl.trustStorePassword","1111111");
						paquetereenvio.writeObject(paqueterecibido);
//						paquetereenvio.close();
//						enviadestinatario.close();
						//misocket.close();
					}
					misocket.close();
					// -------------------------------------------------------
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
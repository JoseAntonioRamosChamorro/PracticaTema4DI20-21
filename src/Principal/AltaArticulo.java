package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AltaArticulo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDes;
	private JTextField texPrec;
	private JTextField textCant;
	private JButton btnVolver;
	private JButton btnAgregar;
	/**
	 * Create the frame.
	 */
	public AltaArticulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblDescp = new JLabel("Descripci\u00F3n");
		GridBagConstraints gbc_lblDescp = new GridBagConstraints();
		gbc_lblDescp.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescp.gridx = 4;
		gbc_lblDescp.gridy = 1;
		contentPane.add(lblDescp, gbc_lblDescp);

		textDes = new JTextField();
		GridBagConstraints gbc_textDes = new GridBagConstraints();
		gbc_textDes.insets = new Insets(0, 0, 5, 5);
		gbc_textDes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDes.gridx = 4;
		gbc_textDes.gridy = 2;
		contentPane.add(textDes, gbc_textDes);
		textDes.setColumns(10);

		JLabel lblPrec = new JLabel("Precio");
		GridBagConstraints gbc_lblPrec = new GridBagConstraints();
		gbc_lblPrec.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrec.gridx = 4;
		gbc_lblPrec.gridy = 3;
		contentPane.add(lblPrec, gbc_lblPrec);

		texPrec = new JTextField();
		GridBagConstraints gbc_texPrec = new GridBagConstraints();
		gbc_texPrec.insets = new Insets(0, 0, 5, 5);
		gbc_texPrec.fill = GridBagConstraints.HORIZONTAL;
		gbc_texPrec.gridx = 4;
		gbc_texPrec.gridy = 4;
		contentPane.add(texPrec, gbc_texPrec);
		texPrec.setColumns(10);

		JLabel lblCanti = new JLabel("Cantidad");
		GridBagConstraints gbc_lblCanti = new GridBagConstraints();
		gbc_lblCanti.insets = new Insets(0, 0, 5, 5);
		gbc_lblCanti.gridx = 4;
		gbc_lblCanti.gridy = 5;
		contentPane.add(lblCanti, gbc_lblCanti);

		textCant = new JTextField();
		GridBagConstraints gbc_textCant = new GridBagConstraints();
		gbc_textCant.insets = new Insets(0, 0, 5, 5);
		gbc_textCant.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCant.gridx = 4;
		gbc_textCant.gridy = 6;
		contentPane.add(textCant, gbc_textCant);
		textCant.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAgregar.gridx = 1;
		gbc_btnAgregar.gridy = 9;
		contentPane.add(btnAgregar, gbc_btnAgregar);

		btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.anchor = GridBagConstraints.SOUTH;
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 5;
		gbc_btnVolver.gridy = 9;
		contentPane.add(btnVolver, gbc_btnVolver);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

			}
		});
		btnAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Conectar BD
				Connection con = conectar();
				// Hacer el INSERT
				int respuesta = insertar(con, textDes.getText(), texPrec.getText(),textCant.getText());
				desconectar(con);
			}

			public int insertar(Connection con, String descripcion, String precio, String cantidad) {
				int respuesta = 0;

				try {
					// Creamos un STATEMENT para una consulta SQL INSERT.
					Statement sta = con.createStatement();
					String cadenaSQL = "INSERT INTO articulo (Descripcion,Precio,Cantidad) VALUES ('" + descripcion + "','"+ precio +"','"+ cantidad +"')";
					System.out.println(cadenaSQL);
					sta.executeUpdate(cadenaSQL);
					sta.close();
					JOptionPane.showMessageDialog(null, "Inserción lograda");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error en la Inserción");
					e.printStackTrace();
					respuesta = 1;
				}
				return respuesta;
			}
		});
		setVisible(true);
	}

	//conectar y desconectar  Connection con = conectar();  desconectar(con);
	public static Connection conectar()
	{
		String BD = "tienda";
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/"+BD+"?autoReconnect=true&useSSL=false";
		String user = "root2";
		String password = "12345678A";
		Connection con = null;
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD empresa
			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1-" + cnfe.getMessage());
		}

		return con;
	}
	public static void desconectar(Connection con)
	{
		try
		{
			con.close();
		}
		catch(Exception e) {}
	}
}

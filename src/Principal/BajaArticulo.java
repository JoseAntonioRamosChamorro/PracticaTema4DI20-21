package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Choice;
import javax.swing.JButton;

public class BajaArticulo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public BajaArticulo() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbArticulos = new JLabel("Articulos");
		GridBagConstraints gbc_lbArticulos = new GridBagConstraints();
		gbc_lbArticulos.insets = new Insets(0, 0, 5, 5);
		gbc_lbArticulos.gridx = 4;
		gbc_lbArticulos.gridy = 2;
		contentPane.add(lbArticulos, gbc_lbArticulos);
		
		Choice choice = new Choice();
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.gridwidth = 3;
		gbc_choice.insets = new Insets(0, 0, 5, 5);
		gbc_choice.gridx = 3;
		gbc_choice.gridy = 3;
		contentPane.add(choice, gbc_choice);
		
		JButton btnBorrar = new JButton("Borrar");
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorrar.gridx = 2;
		gbc_btnBorrar.gridy = 7;
		contentPane.add(btnBorrar, gbc_btnBorrar);
		
		JButton btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.gridx = 7;
		gbc_btnVolver.gridy = 7;
		contentPane.add(btnVolver, gbc_btnVolver);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

			}
		});
		
		choice.add("Seleccionar uno...");
		Connection con = conectar();
		// Rellenar el Choice
				String sqlSelect = "SELECT * FROM articulo";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						choice.add(rs.getInt("idArticulo")+
								"-"+rs.getString("Descripcion")+
								", "+rs.getString("Precio")+
								"€, "+rs.getString("Cantidad"));
					}
					rs.close();
					stmt.close();
				} catch (SQLException ex) {
					System.out.println("ERROR:al consultar");
					ex.printStackTrace();
				}
				// Cerrar la conexión
				desconectar(con);
		
		btnBorrar.addActionListener(new ActionListener() {
			Connection con = conectar();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Borrar
				String[] DNI=choice.getSelectedItem().split("-");
				int respuesta = borrar(con, Integer.parseInt(DNI[0]));
				// Mostramos resultado
				if(respuesta == 0)
				{
					System.out.println("Cliente eliminado");
				}
				else
				{
					System.out.println("Error en eliminación");
				}
				// Actualizar el Choice
				choice.removeAll();
				choice.add("Seleccionar uno...");
				String sqlSelect = "SELECT * FROM articulo";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						choice.add(rs.getInt("idArticulo")+
								"-"+rs.getString("Descripcion")+
								", "+rs.getString("Precio")+
								"€, "+rs.getString("Cantidad"));
					}
					rs.close();
					stmt.close();
				} catch (SQLException ex) {
					System.out.println("ERROR:al consultar");
					ex.printStackTrace();
				}
				// Desconectar
				desconectar(con);
				
			}
		});
		setVisible(true);
	}
	public int borrar(Connection con, int id)
	{
		int respuesta = 0;
		String sql = "DELETE FROM articulo WHERE idArticulo = " + id;
		System.out.println(sql);
		try 
		{
		
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			sta.executeUpdate(sql);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			System.out.println("ERROR:al hacer un Delete");
			ex.printStackTrace();
			respuesta = 1;
		}
	
		return respuesta;
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

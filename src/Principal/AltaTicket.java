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

import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AltaTicket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFecha;
	private JTextField textPrecio;
	/**
	 * Create the frame.
	 */
	public AltaTicket() {
		Connection con = conectar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lbFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lbFecha = new GridBagConstraints();
		gbc_lbFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lbFecha.gridx = 4;
		gbc_lbFecha.gridy = 1;
		contentPane.add(lbFecha, gbc_lbFecha);

		textFecha = new JTextField();
		GridBagConstraints gbc_textFecha = new GridBagConstraints();
		gbc_textFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFecha.gridx = 4;
		gbc_textFecha.gridy = 2;
		contentPane.add(textFecha, gbc_textFecha);
		textFecha.setColumns(10);

		JLabel lbArticulos = new JLabel("Articulos");
		GridBagConstraints gbc_lbArticulos = new GridBagConstraints();
		gbc_lbArticulos.insets = new Insets(0, 0, 5, 5);
		gbc_lbArticulos.gridx = 4;
		gbc_lbArticulos.gridy = 5;
		contentPane.add(lbArticulos, gbc_lbArticulos);

		Choice chArticulos = new Choice();
		GridBagConstraints gbc_chArticulos = new GridBagConstraints();
		gbc_chArticulos.insets = new Insets(0, 0, 5, 5);
		gbc_chArticulos.gridx = 4;
		gbc_chArticulos.gridy = 6;
		contentPane.add(chArticulos, gbc_chArticulos);

		JTextArea AreaArticulos = new JTextArea();
		AreaArticulos.setEditable(false);
		GridBagConstraints gbc_AreaArticulos = new GridBagConstraints();
		gbc_AreaArticulos.insets = new Insets(0, 0, 5, 5);
		gbc_AreaArticulos.fill = GridBagConstraints.BOTH;
		gbc_AreaArticulos.gridx = 4;
		gbc_AreaArticulos.gridy = 7;
		contentPane.add(AreaArticulos, gbc_AreaArticulos);

		JLabel lbTotal = new JLabel("PrecioTotal");
		GridBagConstraints gbc_lbTotal = new GridBagConstraints();
		gbc_lbTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lbTotal.gridx = 4;
		gbc_lbTotal.gridy = 9;
		contentPane.add(lbTotal, gbc_lbTotal);

		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setColumns(10);
		GridBagConstraints gbc_textPrecio = new GridBagConstraints();
		gbc_textPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_textPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPrecio.gridx = 4;
		gbc_textPrecio.gridy = 10;
		contentPane.add(textPrecio, gbc_textPrecio);

		JButton btnAgregar = new JButton("Agregar");
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAgregar.gridx = 1;
		gbc_btnAgregar.gridy = 14;
		contentPane.add(btnAgregar, gbc_btnAgregar);

		JButton btnCrear = new JButton("Crear Tiquet");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrear.gridx = 4;
		gbc_btnCrear.gridy = 14;
		contentPane.add(btnCrear, gbc_btnCrear);

		JButton btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 14;
		contentPane.add(btnVolver, gbc_btnNewButton);
		//botones
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

			}
		});

		btnAgregar.addActionListener(new ActionListener() {
			Connection con = conectar();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] AgregarLista=chArticulos.getSelectedItem().split("-");
				agregar(con,Integer.parseInt(AgregarLista[0]), AreaArticulos,textPrecio);


			}

			public void agregar(Connection con, int id, JTextArea areaArticulos, JTextField textPrecio) {

				String sqlSelect = "select * from articulo where idArticulo = " + id+";";
				try {

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						int total;
						int total2 =0;
						int total3;
						if(AreaArticulos.getText().length()==0)
						{
							AreaArticulos.setText(rs.getInt("Precio")+"€"+
									", "+rs.getString("Descripcion"));

							total = rs.getInt("Precio");

							total3 = total2 +total;

							String totalfinal = String.valueOf(total3);
							textPrecio.setText(totalfinal);
						}
						else
						{

							AreaArticulos.setText(AreaArticulos.getText() + "\n" +
									rs.getInt("Precio")+"€"+
									", "+rs.getString("Descripcion"));

							String suma1=textPrecio.getText();
							int suma2 = Integer.parseInt(suma1);
							total = rs.getInt("Precio");
							int suma3 =suma2 + total;

							String totalfinal = String.valueOf(suma3);
							textPrecio.setText(totalfinal);
						}
					}

					rs.close();
					stmt.close();
				} catch (SQLException ex) {
					System.out.println("ERROR:al consultar");
					ex.printStackTrace();
				}

			}
		});
		btnCrear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Connection con = conectar();
				// Hacer el INSERT
				String[] AgregarLista=chArticulos.getSelectedItem().split("-");
				int respuesta = insertar(con,textFecha.getText(),textPrecio.getText(),Integer.parseInt(AgregarLista[0]) );

			}

			private int insertar(Connection con, String fecha, String precio, int idfk) {
				int respuesta = 0;
				try 
				{
					// Creamos un STATEMENT para una consulta SQL INSERT.
					Statement sta = con.createStatement();
					String cadenaSQL = "INSERT INTO ticket (Fecha,PrecioTotal, idarticuloFK) VALUES "
							+ "('"+fecha+"', '"+precio+"', '"+idfk+"')";
					System.out.println(cadenaSQL);
					sta.executeUpdate(cadenaSQL);
					sta.close();
				} 
				catch (SQLException ex) 
				{
					System.out.println("ERROR:al hacer un Insert");
					ex.printStackTrace();
					respuesta = 1;
				}
				return respuesta;
				
			}
		});
		//Rellenar
		chArticulos.add("Seleccionar uno");
		rellenarchArticulos(con,chArticulos);
	}
	public void rellenarchArticulos(Connection con, Choice chArticulos) {
		String sqlSelect1 = "SELECT * FROM articulo";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect1);
			while (rs.next()) 
			{
				chArticulos.add(rs.getInt("idArticulo")+"-"+
						rs.getString("Descripcion")+
						"="+rs.getString("Precio")+"€");
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al consultar");
			ex.printStackTrace();
		}

		//final
		desconectar(con);
		setVisible(true);
	}
	//conectar y desconectar
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

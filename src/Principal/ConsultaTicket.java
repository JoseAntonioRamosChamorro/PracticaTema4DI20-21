package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

public class ConsultaTicket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ConsultaTicket() {
		Connection con = conectar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lbTickets = new JLabel("Lista Tickets");
		GridBagConstraints gbc_lbTickets = new GridBagConstraints();
		gbc_lbTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lbTickets.gridx = 5;
		gbc_lbTickets.gridy = 2;
		contentPane.add(lbTickets, gbc_lbTickets);

		JTextArea textAreaTickets = new JTextArea();
		textAreaTickets.setEditable(false);
		GridBagConstraints gbc_textAreaTickets = new GridBagConstraints();
		gbc_textAreaTickets.gridwidth = 5;
		gbc_textAreaTickets.gridheight = 4;
		gbc_textAreaTickets.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaTickets.fill = GridBagConstraints.BOTH;
		gbc_textAreaTickets.gridx = 3;
		gbc_textAreaTickets.gridy = 3;
		contentPane.add(textAreaTickets, gbc_textAreaTickets);

		JButton btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 5;
		gbc_btnVolver.gridy = 8;
		contentPane.add(btnVolver, gbc_btnVolver);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

			}
		});
		rellenarTextArea(con,textAreaTickets);
		setVisible(true);
		desconectar(con);
	}
	public void rellenarTextArea(Connection con, JTextArea t) {
		String sqlSelect = "SELECT * FROM ticket";
		try {

			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);

			while (rs.next()) 
			{
				if(t.getText().length()==0)
				{
					t.setText(rs.getInt("Fecha")+
							"-"+rs.getString("PrecioTotal")+" €");
				}
				else
				{
					t.setText(t.getText() + "\n" +
							rs.getInt("Fecha")+
							"-"+rs.getString("PrecioTotal")+" €");
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al consultar");
			ex.printStackTrace();


		}
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

package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Main extends JFrame {
	//Frames
	


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Main() {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblArticulos = new JLabel("Articulos");
		GridBagConstraints gbc_lblArticulos = new GridBagConstraints();
		gbc_lblArticulos.insets = new Insets(0, 0, 5, 5);
		gbc_lblArticulos.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblArticulos.gridx = 2;
		gbc_lblArticulos.gridy = 1;
		contentPane.add(lblArticulos, gbc_lblArticulos);

		JLabel lblTickets = new JLabel("Tickets");
		GridBagConstraints gbc_lblTickets = new GridBagConstraints();
		gbc_lblTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lblTickets.gridx = 7;
		gbc_lblTickets.gridy = 1;
		contentPane.add(lblTickets, gbc_lblTickets);

		JButton btnAltaA = new JButton("Alta");
		GridBagConstraints gbc_btnAltaA = new GridBagConstraints();
		gbc_btnAltaA.insets = new Insets(0, 0, 5, 5);
		gbc_btnAltaA.gridx = 2;
		gbc_btnAltaA.gridy = 3;
		contentPane.add(btnAltaA, gbc_btnAltaA);


		JButton btnAltaT = new JButton("Alta");
		GridBagConstraints gbc_btnAltaT = new GridBagConstraints();
		gbc_btnAltaT.insets = new Insets(0, 0, 5, 5);
		gbc_btnAltaT.gridx = 7;
		gbc_btnAltaT.gridy = 3;
		contentPane.add(btnAltaT, gbc_btnAltaT);

		JButton btnBaja = new JButton("Baja");
		GridBagConstraints gbc_btnBaja = new GridBagConstraints();
		gbc_btnBaja.insets = new Insets(0, 0, 5, 5);
		gbc_btnBaja.gridx = 2;
		gbc_btnBaja.gridy = 4;
		contentPane.add(btnBaja, gbc_btnBaja);

		JButton btnCon = new JButton("Consulta");
		GridBagConstraints gbc_btnCon = new GridBagConstraints();
		gbc_btnCon.insets = new Insets(0, 0, 5, 5);
		gbc_btnCon.gridx = 2;
		gbc_btnCon.gridy = 5;
		contentPane.add(btnCon, gbc_btnCon);

		JButton btnConT = new JButton("Consulta");
		GridBagConstraints gbc_btnConT = new GridBagConstraints();
		gbc_btnConT.insets = new Insets(0, 0, 5, 5);
		gbc_btnConT.gridx = 7;
		gbc_btnConT.gridy = 5;
		contentPane.add(btnConT, gbc_btnConT);

		JButton btnMd = new JButton("Modificaci\u00F3n");
		GridBagConstraints gbc_btnMd = new GridBagConstraints();
		gbc_btnMd.insets = new Insets(0, 0, 0, 5);
		gbc_btnMd.gridx = 2;
		gbc_btnMd.gridy = 6;
		contentPane.add(btnMd, gbc_btnMd);
		btnAltaA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AltaArticulo AA = new AltaArticulo();
				
			}
		});
		btnAltaT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AltaTicket AT = new AltaTicket();
				
			}
		});
		btnCon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConsultaArticulo CA = new ConsultaArticulo();
				
			}
		});
		btnConT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConsultaTicket CT = new ConsultaTicket();
				
			}
		});
		btnBaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BajaArticulo BA = new BajaArticulo();
			}
		});
		btnMd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ModificarArticulo MA = new ModificarArticulo();
			}
		});

	}

}

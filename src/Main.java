import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class Main {

	private JFrame frame;
	private JTextField sifra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 734, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 11, 215, 118);
		frame.getContentPane().add(scrollPane);

		JTextArea besedilo = new JTextArea();
		scrollPane.setViewportView(besedilo);

		JButton odpri_datoteko = new JButton("Odpri");
		odpri_datoteko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Odpri of = new Odpri();

				try {
					of.PickMe();
				} catch (Exception e) {
					e.printStackTrace();
				}
				besedilo.setText(of.sb.toString());
			}
		});
		odpri_datoteko.setBounds(77, 140, 89, 23);
		frame.getContentPane().add(odpri_datoteko);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(437, 11, 215, 118);
		frame.getContentPane().add(scrollPane_1);

		JTextArea zamenjano = new JTextArea();
		scrollPane_1.setViewportView(zamenjano);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(437, 168, 215, 118);
		frame.getContentPane().add(scrollPane_2);

		JTextArea nazaj = new JTextArea();
		scrollPane_2.setViewportView(nazaj);

		sifra = new JTextField();
		sifra.setBounds(86, 200, 103, 30);
		frame.getContentPane().add(sifra);
		sifra.setColumns(10);

		JLabel lblifra = new JLabel("\u0160ifra :");
		lblifra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblifra.setBounds(45, 202, 66, 22);
		frame.getContentPane().add(lblifra);

		JButton zasifriraj = new JButton("Za\u0161ifriraj");
		zasifriraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ";
				String matricaCrk;
				String kljuc = sifra.getText().toUpperCase();
				String tekst = besedilo.getText().toUpperCase();
				char[] arrayBesedila = tekst.toCharArray();
				for (int i = 0; i < arrayBesedila.length; i++) {
					char trenutniKljuc = kljuc.toCharArray()[i % kljuc.length()];
					System.out.println(trenutniKljuc);
					matricaCrk = obrniCrke(trenutniKljuc);
					System.out.println(matricaCrk);
					if (arrayBesedila[i] == (char) 65279)
						continue;
					if (arrayBesedila[i] >= 'A') {
						int M = abeceda.indexOf(arrayBesedila[i]);
						arrayBesedila[i] = matricaCrk.charAt(M);
					} else if (arrayBesedila[i] >= '0' && arrayBesedila[i] <= '9') {
						int M = arrayBesedila[i] - '0';
						int K = kljuc.length();
						int modul = 10;
						arrayBesedila[i] = (char) ('0' + (M + K) % modul);
					}
				}

				String zasifrirano = new String(arrayBesedila);

				zamenjano.setText(zasifrirano);

			}
		});
		zasifriraj.setBounds(77, 241, 89, 23);
		frame.getContentPane().add(zasifriraj);

		JButton odsifriraj = new JButton("Od\u0161ifriraj");
		odsifriraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ";
				String matricaCrk;
				String zasifrirano = zamenjano.getText().toUpperCase();
				String kljuc = sifra.getText().toUpperCase();
				char[] arrayBesedila = zasifrirano.toCharArray();
				for (int i = 0; i < arrayBesedila.length; i++) {
					char trenutniKljuc = kljuc.toCharArray()[i % kljuc.length()];
					matricaCrk = obrniCrke(trenutniKljuc);
					if (arrayBesedila[i] == (char) 65279)
						continue;
					if (arrayBesedila[i] >= 'A') {
						int C = matricaCrk.indexOf(arrayBesedila[i]);
						arrayBesedila[i] = abeceda.charAt(C);
					} else if (arrayBesedila[i] >= '0' && arrayBesedila[i] <= '9') {
						int M = arrayBesedila[i] - '0';
						int K = kljuc.length();
						int modul = 10;
						arrayBesedila[i] = (char) ('0' + (M + modul - K) % modul);
					}
				}

				String desifrirano = new String(arrayBesedila);

				nazaj.setText(desifrirano);
			}
		});
		odsifriraj.setBounds(77, 290, 89, 23);
		frame.getContentPane().add(odsifriraj);

	}

	private String obrniCrke(char prviChar) {
		String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ";
		String str = abeceda + abeceda;
		int stevilka = abeceda.indexOf(prviChar);
		if (stevilka < 0)
			return abeceda;
		return str.substring(stevilka, stevilka + abeceda.length());
	}

}



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;

public class Odpri {


	JFileChooser fileChooser = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	String ime1 ;

	public void PickMe() throws Exception {
		fileChooser.setMultiSelectionEnabled(true);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			java.io.File file = fileChooser.getSelectedFile();
			ime1=file.getName();
			Scanner input = new Scanner(file, "UTF-8");

		

			while (input.hasNext()) {
				sb.append(input.nextLine());
				sb.append("\n");
			}
			input.close();
		} else {
			sb.append("Nobena datoteka ni bila izbrana!");
		}
	}
	


		
	


}
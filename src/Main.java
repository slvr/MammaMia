import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	public static void main(String args[]){
		JFrame applicationFrame = new JFrame();
		JPanel applicationPanel = new JPanel();
		applicationFrame.setTitle("Mamma Mia Pizza");
		applicationPanel.setLayout(new BoxLayout(applicationPanel, BoxLayout.Y_AXIS));
		JLabel applicationUserLabel = new JLabel("Customer Name: ");
		JTextField applicationUserText = new JTextField();
		applicationPanel.add(applicationUserLabel);
		applicationPanel.add(applicationUserText);
		JButton applicationButton = new JButton("Begin Order");
		applicationButton.addActionListener((ActionEvent e) -> {
			if(applicationUserText.getText().equals("")){
				JOptionPane.showMessageDialog(applicationFrame.getComponent(0), "Please enter a name");
			}
			else{
				
			}
		});
		applicationFrame.getRootPane().setDefaultButton(applicationButton);
		applicationPanel.add(applicationButton);
		applicationFrame.add(applicationPanel);
		applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applicationFrame.pack();
		applicationFrame.setResizable(true);
		applicationFrame.setLocationRelativeTo(null);
		applicationFrame.setVisible(true);
	}
}
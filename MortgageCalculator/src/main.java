import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class main {
	
	static JFrame frame = new JFrame();
	static JTextField field1, field2, field3, field4;
	static JTextField[] arrayfield = new JTextField[4];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 field1 = new JTextField();
	     field2 = new JTextField();
	     field3 = new JTextField();
	     field4 = new JTextField();

		frame.setTitle("Mortgage-Calculator");
		frame.setSize(550,450);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		

		ImageIcon icon = new ImageIcon("Icon.jpg");
        Image image = icon.getImage();
        frame.setIconImage(image);
		
		
		Font font = new Font("Javanese Text",Font.ITALIC,25);
		
		
		field1.setBounds(10, 35, 350, 35);
		field1.setText("Property Price/Only-Values");
		field1.setToolTipText("If You're Not Buying A Property, Enter The Amount Left On Your Mortgage.");		
		field2.setBounds(10, 125, 350, 35);
		field2.setText("Deposit (Optional)/Only-Value");
		field3.setToolTipText("If You're Remortgaging, This Does Not Apply");
		field3.setBounds(10, 225, 350, 35);
		field3.setText("Mortgage term/Only-Values");
		field3.setToolTipText("The average period for repayment of a mortgage is 25 years.");
		field4.setBounds(10, 325, 350, 35);
		field4.setText("Interest rate/Only-Values");
		field4.setToolTipText("You Can Use The Current Interest Rate As A Default.");
	
		arrayfield[0] = field1;
		arrayfield[1] = field2;
		arrayfield[2] = field3;
		arrayfield[3] = field4;
		for(int i =0; i < arrayfield.length; i++) {
			arrayfield[i].setFont(font);
			frame.add(arrayfield[i]);
		}
		
		JButton repayment = new JButton("Repayment");
		repayment.setFont(font);
		repayment.setFocusable(false);
		repayment.setToolTipText("Calculate Repayment");
		repayment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				calculateRepayment();
				
			}			
		});
		JButton intrest = new JButton("Intrest-Only");
		intrest.setFont(font);
		intrest.setFocusable(false);
		intrest.setToolTipText("Calculate Intrest");
		intrest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				calcultateIntrest();
			}			
		});
		
		repayment.setBounds(400, 50, 100, 50);
		intrest.setBounds(400, 150, 100, 50);
		frame.add(intrest);
		frame.add(repayment);
		
		
		
		frame.setVisible(true);
	}

	
	private static void calculateRepayment() {
		// TODO Auto-generated method stub
		
		try {
			
		
		int P = Integer.parseInt(field1.getText());  // Property Price
		int D = Integer.parseInt(field2.getText());  // Deposit
		int n = Integer.parseInt(field3.getText());  // Mortgage Term in years
		double r1 = Double.parseDouble(field4.getText());  // Interest Rate in percentage
		

		final int L = P - D;  // Loan Amount
		final double r = r1 / 100.0;  // Annual Interest Rate as a decimal
		final double rm = r / 12.0;  // Monthly Interest Rate
		final int nm = n * 12;  // Total Number of Payments (months)

		final double MP1 = L * rm * Math.pow(1 + rm, nm);
		final double MP2 = Math.pow(1 + rm, nm) - 1;
		final double MP = MP1 / MP2;
		
		final double TP = MP * nm;
		
		JOptionPane.showMessageDialog(null, "Monthly Payment = " + String.format("%,.2f", MP) + "\nTotal Payment = " + String.format("%,.2f", TP));
		
		}
		catch (ArithmeticException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please Input Numerical Values Only!!!");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An Unexpected Error Has Occured "+ e.getMessage());
		}
	}
	
	private static synchronized void calcultateIntrest() {
		// TODO Auto-generated method stub
		try {
			
		int P = Integer.parseInt(field1.getText());  // Property Price
		int D = Integer.parseInt(field2.getText());  // Deposit
		int N = Integer.parseInt(field3.getText());  // Mortgage Term in years
		double r1 = Double.parseDouble(field4.getText());  // Interest Rate in percentage
		

		final int L = P - D;  // Loan Amount
		final double r = r1 / 100.0;  // Annual Interest Rate as a decimal
		final double rm = r / 12.0;  // Monthly Interest Rate
		final int nm = N * 12;  // Total Number of Payments (months)
		
		final double MP1 = L * rm * Math.pow(1 + rm, nm);
		final double MP2 = Math.pow(1 + rm, nm) - 1;
		final double MP = MP1 / MP2;
		
		final double MI = L * rm;
		final double TI = (MP * nm) - L;
		
		JOptionPane.showMessageDialog(null, "Monthly Intrest = " + String.format("%,.2f", MI) + "\nTotal Intrest = " + String.format("%,.2f", TI));
	
		}
		catch (ArithmeticException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please Input Numerical Values Only!!!");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An Unexpected Error Has Occured "+ e.getMessage());
		}
		
	}
	
	
	
	
	private static void exitApp() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
		
	}
}

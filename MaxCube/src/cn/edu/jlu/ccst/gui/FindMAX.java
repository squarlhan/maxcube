package cn.edu.jlu.ccst.gui;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;

import org.jgap.IChromosome;

import cn.edu.jlu.ccst.ga.FindMaxCube;
import cn.edu.jlu.ccst.ga.MaxFunction;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class FindMAX {

	private JFrame frmFindmax;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private FileDialog fileDialog; 
//	private String addr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindMAX window = new FindMAX();
					window.frmFindmax.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FindMAX() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFindmax = new JFrame();
		frmFindmax.setTitle("FindMAX");
		frmFindmax.setBounds(100, 100, 600, 420);
		frmFindmax.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFindmax.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmFindmax.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFileAddr = new JLabel("File Addr:");
		lblFileAddr.setBounds(6, 11, 60, 16);
		panel.add(lblFileAddr);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(72, 6, 191, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileDialog = new FileDialog(frmFindmax);  
		        fileDialog.show(); 
		        textField.setText(fileDialog.getDirectory()+fileDialog.getFile());
//		        addr = fileDialog.getFile();
			}
		});
		button.setBounds(263, 6, 31, 29);
		panel.add(button);
		
		JLabel lblXmax = new JLabel("X_MAX:");
		lblXmax.setBounds(5, 150, 61, 16);
		panel.add(lblXmax);
		
		JLabel lblYmax = new JLabel("Y_MAX:");
		lblYmax.setBounds(6, 186, 61, 16);
		panel.add(lblYmax);
		
		JLabel lblZmax = new JLabel("Z_MAX:");
		lblZmax.setBounds(6, 225, 61, 16);
		panel.add(lblZmax);
		
		textField_1 = new JTextField();
		textField_1.setBounds(72, 145, 191, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(72, 181, 191, 26);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(72, 220, 191, 26);
		panel.add(textField_3);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(5, 267, 61, 16);
		panel.add(lblX);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(5, 295, 61, 16);
		panel.add(lblY);
		
		JLabel lblZ = new JLabel("Z:");
		lblZ.setBounds(5, 323, 61, 16);
		panel.add(lblZ);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(72, 262, 191, 26);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(72, 290, 191, 26);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(72, 318, 191, 26);
		panel.add(textField_6);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_8.setText("");
				textField_7.setText("");
				textField_6.setText("");
				textField_5.setText("");
				textField_4.setText("");
				textField_3.setText("");
				textField_2.setText("");
				textField_1.setText("");
				textField.setText("");
			}
		});
		btnReset.setBounds(6, 351, 81, 29);
		panel.add(btnReset);
		
		JLabel lblMaxiter = new JLabel("MAX_ITER:");
		lblMaxiter.setBounds(6, 51, 81, 16);
		panel.add(lblMaxiter);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(99, 47, 164, 26);
		panel.add(textField_7);
		
		JLabel lblPopulation = new JLabel("POPULATION:");
		lblPopulation.setBounds(6, 89, 91, 16);
		panel.add(lblPopulation);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(99, 85, 164, 26);
		panel.add(textField_8);
		
		JTextArea textArea = new JTextArea();
//		JPanel panel_1 = new JPanel();
//		panel_1.add(textArea);
		JScrollPane panel_1 = new JScrollPane(textArea);
		frmFindmax.getContentPane().add(panel_1);
//		frmFindmax.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		panel_1.setLayout(new ScrollPaneLayout());
//		panel_1.setLayout(null);		
		
		

		
		JButton btnRun = new JButton("RUN");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextAreaOutputStream out = new JTextAreaOutputStream (textArea);
				System.setOut (new PrintStream (out));//璁剧疆杈撳嚭閲嶅畾鍚� 
				System.setErr(new PrintStream(out));//灏嗛敊璇緭鍑轰篃閲嶅畾鍚�,鐢ㄤ簬e.pritnStackTrace
				
				FindMaxCube se = new FindMaxCube();

				int pop_size = Integer.parseInt(textField_8.getText().trim());
				int max_ite = Integer.parseInt(textField_7.getText().trim());
				int chrom_size = 3;
				int i = Integer.parseInt(textField_1.getText().trim());
				int j = Integer.parseInt(textField_2.getText().trim());
				int k = Integer.parseInt(textField_3.getText().trim());
				int x = Integer.parseInt(textField_4.getText().trim());
				int y = Integer.parseInt(textField_5.getText().trim());
				int z = Integer.parseInt(textField_6.getText().trim());
				int[] window = {x,y,z};
				int[] left = {0,0,0};
				int[] right = {i-window[0], j-window[1], k-window[2]};
				String addr = textField.getText().trim();
				
				double[][][] pt = se.readpressure(i, j, k, addr);
				for (int a = 0; a <= 0; a++) {
					IChromosome fittest = se.runga(max_ite, chrom_size, pop_size, left, right, new MaxFunction(pt, window));
					System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
					System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
					System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
					System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
					System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
					System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
					System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
					System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
					System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
					System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
					System.out.println((int)(fittest.getGene(2).getAllele())+window[2]-1 + "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
					System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
					System.out.println((int)(fittest.getGene(2).getAllele())+window[2]-1 + "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
					System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
					System.out.println((int)(fittest.getGene(2).getAllele())+window[2] -1+ "	");
					
					System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
					System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
					System.out.println((int)(fittest.getGene(2).getAllele())+window[2]-1 + "	");
				}
				
			}
		});
		btnRun.setBounds(200, 351, 81, 29);
		panel.add(btnRun);
	}
}

class JTextAreaOutputStream extends OutputStream
{
    private final JTextArea destination;


    public JTextAreaOutputStream (JTextArea destination)
    {
        if (destination == null)
            throw new IllegalArgumentException ("Destination is null");


        this.destination = destination;
    }
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException
    {
        final String text = new String (buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable ()
            {
                @Override
                public void run() 
                {
                    destination.append (text);
                }
            });
    }
    @Override
    public void write(int b) throws IOException
    {
        write (new byte [] {(byte)b}, 0, 1);
    }
}

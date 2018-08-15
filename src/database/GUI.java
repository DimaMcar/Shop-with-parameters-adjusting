package database;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;

public class FrameMain {

	private JFrame frame;
	private JTextField textField_Model;
	private JTextField textField_Weight;
	private JTextField textField_Size;
	private JTextField textField_Resolution;
	private JTextField textField_Search;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	LinkedList struct;
	Datebase base;
	long currentID;
	private JButton btn_ResolutionNext_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain window = new FrameMain();
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
	public FrameMain() {
		initialize();
		frame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we) {
				  System.out.println("bye");
				  struct.save();
				    System.exit(0);
				  }
				});
	}
	

    


	/**
	 * Initialize the contents of the frame.
	 */

	
	private void initialize() {
		
		JButton btn_ModelPrev = new JButton("- \u043C\u043E\u0434\u0435\u043B\u044C"),
				btn_ModelNext = new JButton("+ \u043C\u043E\u0434\u0435\u043B\u044C"),
				btn_SizePrev = new JButton("- \u0440\u043E\u0437\u043C\u0456\u0440"),
				btn_SizeNext = new JButton("+ \u0440\u043E\u0437\u043C\u0456\u0440"),
				btn_resolutionPrev = new JButton("- \u0434\u0456\u0430\u0433\u043E\u043D\u0430\u043B\u044C"),
				btn_ResolutionNext = new JButton("+ \u0434\u0456\u0430\u0433\u043E\u043D\u0430\u043B\u044C"),
				btn_WeightPrev = new JButton("- \u0432\u0430\u0433\u0430"),
				btn_WeightNext = new JButton("+ \u0432\u0430\u0433\u0430");


		frame = new JFrame();
		frame.setBounds(100, 100, 740, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		class Check {
			//@SuppressWarnings("null")
			private void check() {
					if (struct.goNext(currentID, "model") == -1) btn_ModelNext.setEnabled(false);
					else btn_ModelNext.setEnabled(true);
					if (struct.goNext(currentID, "weight") == -1) btn_WeightNext.setEnabled(false);
					else btn_WeightNext.setEnabled(true);
					if (struct.goNext(currentID, "size") == -1) btn_SizeNext.setEnabled(false);
					else btn_SizeNext.setEnabled(true);
					if (struct.goNext(currentID, "resolution") == -1) btn_ResolutionNext.setEnabled(false);
					else btn_ResolutionNext.setEnabled(true);
					if (struct.goPrev(currentID, "model") == -1) btn_ModelPrev.setEnabled(false);
					else btn_ModelPrev.setEnabled(true);
					if (struct.goPrev(currentID, "weight") == -1) btn_WeightPrev.setEnabled(false);
					else btn_WeightPrev.setEnabled(true);
					if (struct.goPrev(currentID, "size") == -1) btn_SizePrev.setEnabled(false);
					else btn_SizePrev.setEnabled(true);
					if (struct.goPrev(currentID, "resolution") == -1) btn_resolutionPrev.setEnabled(false);
					else btn_resolutionPrev.setEnabled(true);
				}
			}
		
		btn_SizePrev.addActionListener(new ActionListener() {
		
					public void actionPerformed(ActionEvent e) {
						currentID = struct.goPrev(currentID, "size");
						System.out.println(currentID);
						textField_Model.setText(base.getElement("model", currentID));
						textField_Weight.setText(base.getElement("weight", currentID));
						textField_Size.setText(base.getElement("size", currentID));
						textField_Resolution.setText(base.getElement("resolution", currentID));
						
						
						
						new Check().check();
					}
				});
	
		btn_SizeNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = struct.goNext(currentID, "size");
				System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
				
				
				
				new Check().check();
			}
		});
		textField_Model = new JTextField();
		textField_Model.setBounds(187, 139, 86, 20);
		frame.getContentPane().add(textField_Model);
		textField_Model.setColumns(10);
		
		textField_Weight = new JTextField();
		textField_Weight.setBounds(287, 139, 86, 20);
		frame.getContentPane().add(textField_Weight);
		textField_Weight.setColumns(10);
		
		textField_Size = new JTextField();
		textField_Size.setBounds(383, 139, 86, 20);
		frame.getContentPane().add(textField_Size);
		textField_Size.setColumns(10);
		
		textField_Resolution = new JTextField();
		textField_Resolution.setBounds(479, 139, 122, 20);
		frame.getContentPane().add(textField_Resolution);
		textField_Resolution.setColumns(10);
		

		btn_ModelPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentID = struct.goPrev(currentID, "model");
				System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
								
				
				new Check().check();
				
			}
		});
		btn_ModelPrev.setBounds(187, 182, 89, 23);
		frame.getContentPane().add(btn_ModelPrev);
		
		
		btn_ModelNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = struct.goNext(currentID, "model");
				System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
				
				
				
				new Check().check();
				
				//btn_ModelPrev.setEnabled(true);
				
			}
		});
		btn_ModelNext.setBounds(184, 94, 89, 23);
		frame.getContentPane().add(btn_ModelNext);
		
		
		btn_SizePrev.setBounds(380, 182, 89, 23);
		frame.getContentPane().add(btn_SizePrev);
		
		
		btn_SizeNext.setBounds(380, 94, 89, 23);
		frame.getContentPane().add(btn_SizeNext);
		
		
		btn_resolutionPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = struct.goPrev(currentID, "resolution");
				System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
				
				
				
				new Check().check();
			}
		});
		btn_resolutionPrev.setBounds(476, 182, 125, 23);
		frame.getContentPane().add(btn_resolutionPrev);
		
		
		btn_ResolutionNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentID = struct.goNext(currentID, "resolution");
				//System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
				
				
				
				new Check().check();
				
			}
		});
		btn_ResolutionNext.setBounds(476, 94, 125, 23);
		frame.getContentPane().add(btn_ResolutionNext);
		
		
		btn_WeightPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = struct.goPrev(currentID, "weight");
				System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
				
				
				
				new Check().check();
			}
		});
		btn_WeightPrev.setBounds(287, 182, 89, 23);
		frame.getContentPane().add(btn_WeightPrev);
		
		
		btn_WeightNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = struct.goNext(currentID, "weight");
				System.out.println(currentID);
				textField_Model.setText(base.getElement("model", currentID));
				textField_Weight.setText(base.getElement("weight", currentID));
				textField_Size.setText(base.getElement("size", currentID));
				textField_Resolution.setText(base.getElement("resolution", currentID));
				
				
				
				new Check().check();
			}
		});
		btn_WeightNext.setBounds(287, 94, 89, 23);
		frame.getContentPane().add(btn_WeightNext);
		
		JButton btn_Change = new JButton("\u0412\u043D\u0435\u0441\u0442\u0438 \u0437\u043C\u0456\u043D\u0438");
		btn_Change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				struct.set(currentID, "model", textField_Model.getText());
				struct.set(currentID, "weight", textField_Weight.getText());
				struct.set(currentID, "size", textField_Size.getText());
				struct.set(currentID, "resolution", textField_Resolution.getText());
				
				new Check().check();
			}
		});
		btn_Change.setBounds(162, 11, 132, 46);
		frame.getContentPane().add(btn_Change);
		
		JButton btn_Add = new JButton("\u0414\u043E\u0434\u0430\u0442\u0438");
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentID = struct.add("Model", "");
				struct.set(currentID, "model", textField_Model.getText());
				struct.set(currentID, "weight", textField_Weight.getText());
				struct.set(currentID, "size", textField_Size.getText());
				struct.set(currentID, "resolution", textField_Resolution.getText());
				new Check().check();
			}
		});
		btn_Add.setBounds(328, 11, 125, 46);
		frame.getContentPane().add(btn_Add);
		
		JRadioButton rdbtn_Weight = new JRadioButton("\u0432\u0430\u0433\u0430");
		buttonGroup.add(rdbtn_Weight);
		rdbtn_Weight.setBounds(127, 276, 109, 23);
		frame.getContentPane().add(rdbtn_Weight);
		
		JButton btn_Delete = new JButton("\u0412\u0438\u0434\u0430\u043B\u0438\u0442\u0438");
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				struct.delete(currentID);
				
				currentID = struct.getMinID("model");
				textField_Model.setText(base.getElement("Model", currentID));
				textField_Weight.setText(base.getElement("Weight", currentID));
				textField_Size.setText(base.getElement("Size", currentID));
				textField_Resolution.setText(base.getElement("Resolution", currentID));
				
				new Check().check();
			}
		});
		btn_Delete.setBounds(492, 11, 125, 46);
		frame.getContentPane().add(btn_Delete);
		
		JRadioButton rdbtn_Model = new JRadioButton("\u043C\u043E\u0434\u0435\u043B\u044C");
		buttonGroup.add(rdbtn_Model);
		rdbtn_Model.setBounds(127, 250, 109, 23);
		frame.getContentPane().add(rdbtn_Model);
		
		JRadioButton rdbtn_Size = new JRadioButton("\u0440\u043E\u0437\u043C\u0456\u0440");
		buttonGroup.add(rdbtn_Size);
		rdbtn_Size.setBounds(127, 302, 109, 23);
		frame.getContentPane().add(rdbtn_Size);
		
		JRadioButton rdbtn_Resolution = new JRadioButton("\u0434\u0456\u0430\u0433\u043E\u043D\u0430\u043B\u044C");
		buttonGroup.add(rdbtn_Resolution);
		rdbtn_Resolution.setBounds(127, 328, 109, 23);
		frame.getContentPane().add(rdbtn_Resolution);
		
		JButton btn_Search = new JButton("\u0428\u0423\u041A\u0410\u0422\u0418");
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = textField_Search.getText();
				String field = "model";
				if (rdbtn_Model.isSelected()) field = "model";
				else if (rdbtn_Weight.isSelected()) field = "weight";
				else if (rdbtn_Size.isSelected()) field = "size";
				else if (rdbtn_Resolution.isSelected()) field = "resolution";
				
				currentID = struct.search(field, value);
				
				textField_Model.setText(base.getElement("Model", currentID));
				textField_Weight.setText(base.getElement("Weight", currentID));
				textField_Size.setText(base.getElement("Size", currentID));
				textField_Resolution.setText(base.getElement("Resolution", currentID));
				
				new Check().check();
				
			}
		});
		btn_Search.setBounds(437, 255, 164, 70);
		frame.getContentPane().add(btn_Search);
		
		textField_Search = new JTextField();
		textField_Search.setBounds(261, 276, 138, 30);
		frame.getContentPane().add(textField_Search);
		textField_Search.setColumns(10);
		
		Path file = Paths.get("LinkedList.txt");
		struct = new LinkedList(file);
		base = new Datebase("monitors");
		
		currentID = struct.getMinID("model");
		
		
		textField_Model.setText(base.getElement("Model", currentID));
		textField_Weight.setText(base.getElement("Weight", currentID));
		textField_Size.setText(base.getElement("Size", currentID));
		textField_Resolution.setText(base.getElement("Resolution", currentID));
		
		JButton btn_Max = new JButton("MIN");
		btn_Max.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String value = textField_Search.getText();
				String field = "model";
				if (rdbtn_Model.isSelected()) field = "model";
				else if (rdbtn_Weight.isSelected()) field = "weight";
				else if (rdbtn_Size.isSelected()) field = "size";
				else if (rdbtn_Resolution.isSelected()) field = "resolution";
				
				currentID = struct.getMin(field);
				
				textField_Model.setText(base.getElement("Model", currentID));
				textField_Weight.setText(base.getElement("Weight", currentID));
				textField_Size.setText(base.getElement("Size", currentID));
				textField_Resolution.setText(base.getElement("Resolution", currentID));
				
				
				new Check().check();
			}
		});
		btn_Max.setBounds(625, 250, 89, 23);
		frame.getContentPane().add(btn_Max);
		
		JButton btnMax = new JButton("MAX");
		btnMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String field = "model";
				if (rdbtn_Model.isSelected()) field = "model";
				else if (rdbtn_Weight.isSelected()) field = "weight";
				else if (rdbtn_Size.isSelected()) field = "size";
				else if (rdbtn_Resolution.isSelected()) field = "resolution";
				
				currentID = struct.getMax(field);
				
				textField_Model.setText(base.getElement("Model", currentID));
				textField_Weight.setText(base.getElement("Weight", currentID));
				textField_Size.setText(base.getElement("Size", currentID));
				textField_Resolution.setText(base.getElement("Resolution", currentID));
				
				
				new Check().check();
			}
		});
		btnMax.setBounds(625, 302, 89, 23);
		frame.getContentPane().add(btnMax);
		
		new Check().check();
		
		
		

		

		

		
	}
}

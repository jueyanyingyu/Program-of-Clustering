package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import mnist.MnistReader;
import yangyikun.Identifier;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FileChooserPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -441596498884729792L;
	private JTextField textField2;
	private JTextField textField3;

	/**
	 * Create the panel.
	 */
	public FileChooserPanel(CardLayout flt, JPanel fp, Identifier id) {
		setLayout(null);
		JLabel label0 = new JLabel("选择label文件  ：");
		label0.setBounds(14, 13, 131, 18);
		setLayout(null);
		add(label0);

		JTextField textField0 = new JTextField();
		textField0.setBounds(203, 10, 106, 24);
		add(textField0);
		textField0.setColumns(10);

		JButton button0 = new JButton("...");
		button0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser0 = new JFileChooser();
				filechooser0.setCurrentDirectory(new File("."));
				filechooser0.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser0.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "*.idx1-ubyte";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory() || f.getName().endsWith(".idx1-ubyte"))
							return true;
						return false;
					}
				});
				filechooser0.showOpenDialog(null);
				textField0.setText(filechooser0.getSelectedFile().getPath());

			}
		});
		button0.setBounds(323, 9, 57, 27);
		add(button0);

		JLabel label1 = new JLabel("选择image文件  ：");
		label1.setBounds(14, 50, 131, 18);
		add(label1);

		JTextField textField1 = new JTextField();
		textField1.setBounds(203, 47, 106, 24);
		add(textField1);
		textField1.setColumns(10);

		JButton button1 = new JButton("...");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser0 = new JFileChooser();
				filechooser0.setCurrentDirectory(new File("."));
				filechooser0.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser0.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "*.idx3-ubyte";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory() || f.getName().endsWith(".idx3-ubyte"))
							return true;
						return false;
					}
				});
				filechooser0.showOpenDialog(null);
				textField1.setText(filechooser0.getSelectedFile().getPath());

			}
		});
		button1.setBounds(323, 46, 57, 27);
		add(button1);

		JLabel label2 = new JLabel("添加K值：");
		label2.setBounds(14, 87, 131, 18);
		add(label2);

		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setText("1");
		textField2.setBounds(203, 84, 50, 24);
		add(textField2);
		textField2.setColumns(10);

		JSlider slider0 = new JSlider();
		slider0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField2.setText(Integer.toString(slider0.getValue()));
			}
		});
		slider0.setValue(1);
		slider0.setMinimum(1);
		slider0.setMaximum(10);
		slider0.setMinorTickSpacing(1);
		slider0.setMajorTickSpacing(1);
		slider0.setBounds(263, 82, 117, 26);
		add(slider0);
				
		JLabel label3 = new JLabel("识别张数：");
		label3.setBounds(14, 126, 133, 18);
		add(label3);
		
		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setText("500");
		textField3.setBounds(203, 123, 50, 24);
		add(textField3);
		textField3.setColumns(10);
		
		JSlider slider1 = new JSlider();
		slider1.setValue(1);
		slider1.setMinimum(1);
		slider1.setMaximum(20);
		slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField3.setText(Integer.toString(slider1.getValue()*500));
			}
		});
		slider1.setBounds(263, 118, 117, 26);
		add(slider1);
		
		JButton button = new JButton("识别");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField0.getText().equals("") && !textField1.getText().equals("")) {
					List<int[][]> image = MnistReader.getImages(textField1.getText());
					int[] label = MnistReader.getLabels(textField0.getText());
					if (label.length == image.size()) {
						List<int[][]> testImageList = image.subList(0, slider1.getValue()*500);
						int[] testLabel = new int[slider1.getValue()*500];
						int[] identify = id.Identify(testImageList,Integer.valueOf(textField2.getText()));
						
						for (int i = 0;i<slider1.getValue()*500;i++) {
							testLabel[i] = label[i];
						}
						fp.add(new TestPanel(identify,testLabel));
						flt.next(fp);
					} else {
						new ErrorFrame("label文件与image文件不匹配！");
					}
					
				}
			}
		});
		button.setBounds(153, 171, 113, 27);
		add(button);

	}

}

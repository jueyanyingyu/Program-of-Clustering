package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import com.google.gson.Gson;

import mnist.MnistReader;
import yangyikun.Identifier;
import yangyikun.TrainData;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MethodChooserPanel extends JPanel {
	List<int[][]> image;
	int[] label;
	public CardLayout flt;
	public JPanel fp;
	public Identifier id;

	private JTextField textField0;
	private JTextField textField1;
	private JTextField textField2;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8046628404583664430L;

	/**
	 * Create the panel.
	 */
	public MethodChooserPanel(CardLayout flt, JPanel fp) {
		this.flt = flt;
		this.fp = fp;
		setLayout(null);

		JLabel label0 = new JLabel("选择模式          ：");
		label0.setBounds(14, 13, 125, 18);
		add(label0);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "选择已生成的训练数据", "选择mnist数据重新训练" }));
		comboBox.setBounds(153, 10, 211, 24);
		comboBox.setSelectedIndex(-1);
		add(comboBox);

		JLabel label1 = new JLabel("选择json文件   ：");
		label1.setBounds(14, 51, 125, 18);
		label1.setVisible(false);
		add(label1);

		this.textField0 = new JTextField();
		textField0.setBounds(153, 48, 175, 24);
		textField0.setVisible(false);
		add(textField0);
		textField0.setColumns(10);

		JButton button0 = new JButton("...");
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser0 = new JFileChooser();
				filechooser0.setCurrentDirectory(new File("."));
				filechooser0.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser0.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "*.json";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory() || f.getName().endsWith(".json"))
							return true;
						return false;
					}
				});
				filechooser0.showOpenDialog(null);
				textField0.setText(filechooser0.getSelectedFile().getPath());
			}
		});
		button0.setBounds(333, 47, 31, 27);
		button0.setVisible(false);
		add(button0);

		JLabel label2 = new JLabel("选择label文件  ：");
		label2.setBounds(14, 51, 125, 18);
		label2.setVisible(false);
		add(label2);

		this.textField1 = new JTextField();
		textField1.setBounds(153, 47, 175, 24);
		textField1.setVisible(false);
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
				textField1.setText(filechooser0.getSelectedFile().getPath());

			}
		});
		button1.setBounds(333, 47, 31, 27);
		button1.setVisible(false);
		add(button1);

		JLabel label3 = new JLabel("选择image文件：");
		label3.setBounds(14, 82, 125, 18);
		label3.setVisible(false);
		add(label3);

		this.textField2 = new JTextField();
		textField2.setBounds(153, 85, 175, 24);
		textField2.setVisible(false);
		add(textField2);
		textField2.setColumns(10);

		JButton button2 = new JButton("...");
		button2.addActionListener(new ActionListener() {

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
				textField2.setText(filechooser0.getSelectedFile().getPath());

			}
		});
		button2.setBounds(333, 84, 31, 27);
		button2.setVisible(false);
		add(button2);

		JButton button3 = new JButton("下一步");
		button3.setBounds(153, 135, 113, 27);
		add(button3);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 0) {
					label1.setVisible(true);
					textField0.setVisible(true);
					button0.setVisible(true);

					label2.setVisible(false);
					textField1.setVisible(false);
					button1.setVisible(false);
					label3.setVisible(false);
					textField2.setVisible(false);
					button2.setVisible(false);

					update();
				} else {
					label2.setVisible(true);
					textField1.setVisible(true);
					button1.setVisible(true);
					label3.setVisible(true);
					textField2.setVisible(true);
					button2.setVisible(true);

					label1.setVisible(false);
					textField0.setVisible(false);
					button0.setVisible(false);

					update();
				}
			}
		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField0.getText().equals("")) {
					readTrain();
					fp.add(new FileChooserPanel(flt, fp, id));
					flt.next(fp);
				} else if (!textField1.getText().equals("") && !textField2.getText().equals("")) {
					getLabelImage();
					if (label.length == image.size()) {
						fp.add(new TrainningPanel(flt, fp, label, image));
						flt.next(fp);
					} else {
						new ErrorFrame("label文件与image文件不匹配！");
					}

				}
			}
		});
	}

	private void update() {
		this.revalidate();
	}

	private void readTrain() {
		FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(textField0.getText());
			Reader reader = new InputStreamReader(fileStream, "UTF-8");
			Gson gson = new Gson();
			TrainData trainData = gson.fromJson(reader, TrainData.class);
			Identifier identifier = new Identifier(trainData);
			id = identifier;
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			new ErrorFrame("json文件错误！");
		}
	}

	private void getLabelImage() {
		this.label = MnistReader.getLabels(textField1.getText());
		this.image = MnistReader.getImages(textField2.getText());
	}
}

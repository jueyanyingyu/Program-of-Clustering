package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;

import yangyikun.Identifier;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

public class TrainningPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3955558700786171573L;
	public CardLayout flt;
	public JPanel fp;
	private JTextField textField2;

	/**
	 * Create the panel.
	 */
	public TrainningPanel(CardLayout flt, JPanel fp, int[] label, List<int[][]> image) {
		this.flt = flt;
		this.fp = fp;
		setLayout(null);

		JLabel label0 = new JLabel("图像处理步长");
		label0.setBounds(14, 13, 106, 18);
		add(label0);

		JComboBox<String> comboBox0 = new JComboBox<String>();
		String[] comboboxList = new String[] { "1", "2", "4", "7", "14" };
		comboBox0.setModel(new DefaultComboBoxModel<String>(comboboxList));
		comboBox0.setSelectedIndex(2);
		comboBox0.setBounds(171, 10, 211, 24);
		add(comboBox0);

		JLabel label1 = new JLabel("单数字聚类k值");
		label1.setBounds(14, 50, 106, 18);
		add(label1);

		JTextField textField0 = new JTextField();
		textField0.setEditable(false);
		textField0.setText("10");
		textField0.setBounds(171, 47, 60, 24);
		add(textField0);
		textField0.setColumns(10);

		JSlider slider0 = new JSlider();
		slider0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField0.setText(Integer.toString(slider0.getValue()));
			}
		});
		slider0.setValue(10);
		slider0.setMinimum(1);
		slider0.setBounds(236, 47, 146, 26);
		add(slider0);

		JLabel label3 = new JLabel("聚类循环次数");
		label3.setBounds(14, 87, 106, 18);
		add(label3);

		JTextField textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setText("1");
		textField1.setBounds(171, 84, 60, 24);
		add(textField1);
		textField1.setColumns(10);

		JSlider slider1 = new JSlider();
		slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField1.setText(Integer.toString(slider1.getValue()));
			}
		});
		slider1.setValue(1);
		slider1.setMinorTickSpacing(2);
		slider1.setMajorTickSpacing(2);
		slider1.setMaximum(20);
		slider1.setMinimum(1);
		slider1.setBounds(236, 86, 146, 26);
		add(slider1);

		JButton button = new JButton("开始训练");
		button.setBounds(158, 183, 113, 27);
		add(button);
		
		JLabel label4 = new JLabel("训练张数");
		label4.setBounds(14, 124, 72, 18);
		add(label4);
				
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setText("500");
		textField2.setBounds(171, 121, 60, 24);
		add(textField2);
		textField2.setColumns(10);
		
		JSlider slider2 = new JSlider();
		slider2.setMinorTickSpacing(500);
		slider2.setMajorTickSpacing(500);
		slider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField2.setText(Integer.toString(slider2.getValue()*500));
			}
		});
		slider2.setMinimum(1);
		slider2.setMaximum(label.length/500);
		slider2.setValue(1);
		slider2.setBounds(236, 121, 146, 26);
		add(slider2);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<int[][]> trainImgaeList = image.subList(0, slider2.getValue()*500);
					int[] trainLabelList = new int[slider2.getValue()*500];
					for (int i = 0 ;i<slider2.getValue()*500;i++) {
						trainLabelList[i] = label[i];
					}
					Identifier id = new Identifier(trainImgaeList, trainLabelList, Integer.valueOf(comboboxList[comboBox0.getSelectedIndex()]),
							slider0.getValue(), slider1.getValue());
					fp.add(new FileChooserPanel(flt, fp, id));
					flt.next(fp);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

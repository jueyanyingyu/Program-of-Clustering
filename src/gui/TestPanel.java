package gui;

import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

public class TestPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8258948237835481312L;
	private JTextField textField0;
	private JTextField textField1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TestPanel(int[] identify, int[] label) {
		int[][] numList = new int[10][3];
		for (int i = 0; i < 10; i++) {
			numList[i][0] = i;
		}
		int count = 0;
		for (int i = 0; i < identify.length; i++) {
			numList[label[i]][1]++;
			if (identify[i] == label[i]) {
				numList[label[i]][2]++;
				count++;
			}
		}
		setLayout(null);

		JLabel label0 = new JLabel("总数：");
		label0.setBounds(40, 13, 60, 18);
		add(label0);

		textField0 = new JTextField();
		textField0.setEditable(false);
		textField0.setBounds(114, 10, 50, 24);
		textField0.setText(Integer.toString(label.length));
		add(textField0);
		textField0.setColumns(10);

		JLabel label1 = new JLabel("正确率：");
		label1.setBounds(178, 13, 60, 18);
		add(label1);

		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setBounds(252, 10, 50, 24);
		DecimalFormat d = new DecimalFormat("#.00");
		textField1.setText(d.format((double)count/label.length*100)+"%");
		add(textField1);
		textField1.setColumns(10);

		Object[] columnNames = { "数字", "总数", "正确率" };
		Object[][] rowData = new Object[10][3];
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<2;j++) {
				rowData[i][j] = (Object)numList[i][j];
			}
			DecimalFormat df = new DecimalFormat("#.00");
			rowData[i][2] = (Object)(df.format((double)numList[i][2]/(double)numList[i][1]*100.0)+"%");
		}
		table = new JTable(rowData,columnNames);
		table.getTableHeader().setBounds(40,42, 198, 18);
		table.setBounds(40, 60, 198, 160);
		add(table.getTableHeader());
		add(table);

	}
}
